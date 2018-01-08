CREATE OR REPLACE TRIGGER validateReimbursements
BEFORE INSERT ON Reimbursements
FOR EACH ROW
  BEGIN
    --Validate Request made at least 1 Week in Advance
    IF Events.StartDate - DateSubmitted < 7
    THEN
      SELECT ReimbursementStatuses.ID
      INTO ReimbursementStatus
      FROM ReimbursementStatuses
      WHERE ReimbursementStatuses.ReimbursementStatus = 'Denied';
      INSERT INTO ReimbursementNotes (Reimbursement, NoteReason, Note) VALUES (ID, 2, 'Event Starting in less than 1 Week');
    END IF;
  END;
/


CREATE OR REPLACE TRIGGER approveReimbursements
AFTER UPDATE ON ManagementApprovals
FOR EACH ROW
  BEGIN
    IF :OLD.DirectSupervisor <> :NEW.DirectSupervisor
    THEN
      :NEW.DirectSupervisorDate = SYSDATE;
    END IF;
    
    IF :OLD.DepartmentHead <> :NEW.DepartmentHead
    THEN
      :NEW.DepartmentHeadDate = SYSDATE;
    END IF;
    
    IF :OLD.BenefitsCoordinator <> :NEW.BenefitsCoordinator
    THEN
      :NEW.BenefitsCoordinatorDate = SYSDATE;
      EXECUTE approveReimbursement(:NEW.ID);
    END IF;
  END;
/


CREATE OR REPLACE PROCEDURE approveReimbursement( approvalsID IN INT)
IS
  reimID INT;
  CURSOR approvedReimbursement IS
    SELECT ID
    FROM ManagementApprovals
    WHERE DirectSupervisorDate IS NOT NULL AND 
      DepartmentHeadDate IS NOT NULL AND
      BenefitsCoordinatorDate IS NOT NULL AND
      ID = approvalsID;
    
  
BEGIN
  OPEN approvedReimbursement;
  FETCH approvedReimbursement INTO reimID;
  IF approvedReimbursement % NOTFOUND THEN
      reimID := 0;
  END IF;
  CLOSE approvedReimbursement;
  
  IF reimID <> 0
  THEN
    UPDATE Reimbursements
  END IF;
END;
/



/********************************
*Create Procedures and Functions*
********************************/
CREATE OR REPLACE FUNCTION getProjectedAmount (eventID INT) RETURN NUMBER
IS
  coverageCost NUMBER;
  CURSOR costCursor IS
    SELECT Events.Cost * EventTypes.PercentCovered
    FROM Events, EventTypes 
    WHERE Events.ID = eventID AND EventTypes.ID = Events.EventType;
BEGIN
  OPEN costCursor;
  FETCH costCursor INTO coverageCost;
  CLOSE costCursor;
  
  RETURN coverageCost;
END;
/


CREATE OR REPLACE PROCEDURE event_updatestatus(
rei_id IN NUMBER
)
AS
    rei Reimbursements%ROWTYPE;
    CURSOR c_rei IS SELECT * FROM Reimbursements WHERE ID = rei_id;
    CURSOR c_gls (eve_id VARCHAR2) IS SELECT MinPercentage FROM GradeLetterScores WHERE ID = (SELECT RecievedGrade FROM Events WHERE ID = eve_id);
    passPercent NUMBER;
    gradepassed BOOLEAN;
BEGIN
    OPEN c_rei;    
    FETCH c_rei INTO rei;
    CLOSE c_rei;
    
    OPEN c_gls (rei.Event);
    FETCH c_gls INTO passPercent;    
    CLOSE c_gls;
    
    END event_updatestatus;
/