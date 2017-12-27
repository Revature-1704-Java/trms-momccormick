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


/*****************************
*Create Primary Key Sequences*
*****************************/
DROP SEQUENCE seqPK_Attachments;
DROP SEQUENCE seqPK_ReimbursementNotes;
DROP SEQUENCE seqPK_Reimbursements;
DROP SEQUENCE seqPK_ManagementApprovals;
DROP SEQUENCE seqPK_Employees;
DROP SEQUENCE seqPK_Events;
CREATE SEQUENCE seqPK_Attachments;
CREATE SEQUENCE seqPK_ReimbursementNotes;
CREATE SEQUENCE seqPK_Reimbursements;
CREATE SEQUENCE seqPK_ManagementApprovals;
CREATE SEQUENCE seqPK_Employees;
CREATE SEQUENCE seqPK_Events;

/****************
*Create Triggers*
****************/
CREATE OR REPLACE TRIGGER inc_Attachments
BEFORE INSERT ON Attachments
FOR EACH ROW
  BEGIN
    :NEW.ID := seqPK_Attachments.NEXTVAL;
  END;
/

CREATE OR REPLACE TRIGGER inc_ReimbursementNotes
BEFORE INSERT ON ReimbursementNotes
FOR EACH ROW
  BEGIN
    :NEW.ID := seqPK_ReimbursementNotes.NEXTVAL;
  END;
/

CREATE OR REPLACE TRIGGER inc_Reimbursements
BEFORE INSERT ON Reimbursements
FOR EACH ROW
  BEGIN
    :NEW.ID := seqPK_Reimbursements.NEXTVAL;
    
    :NEW.ProjectedAmount = getProjectedAmount(:NEW.ID)
  END;
/

CREATE OR REPLACE TRIGGER inc_ManagementApprovals
BEFORE INSERT ON ManagementApprovals
FOR EACH ROW
  BEGIN
    :NEW.ID := seqPK_ManagementApprovals.NEXTVAL;
  END;
/

CREATE OR REPLACE TRIGGER inc_Employees
BEFORE INSERT ON Employees
FOR EACH ROW
  BEGIN
    :NEW.ID := seqPK_Employees.NEXTVAL;
    
    IF :NEW.AvailableReimbursement IS NULL
    THEN
      :NEW.AvailableReimbursement := 1000;
    END IF;
  END;
/

CREATE OR REPLACE TRIGGER inc_Events
BEFORE INSERT ON Events
FOR EACH ROW
  BEGIN
    :NEW.ID := seqPK_Events.NEXTVAL;
  END;
/
