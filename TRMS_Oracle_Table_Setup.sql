/********************************
*Drop Objects for Database Reset*
********************************/
DROP TABLE Attachments;
DROP TABLE ReimbursementNotes;
DROP TABLE NoteReasons;
DROP TABLE Reimbursements;
DROP TABLE ManagementApprovals;
DROP TABLE Employees;
DROP TABLE EmployeeTypes;
DROP TABLE Events;
DROP TABLE EventTypes;
DROP TABLE GradingFormats;
DROP TABLE GradeLetters;
DROP TABLE ReimbursementStatuses;
DROP SEQUENCE seqPK_Attachments;
DROP SEQUENCE seqPK_ReimbursementNotes;
DROP SEQUENCE seqPK_NoteReasons;
DROP SEQUENCE seqPK_Reimbursements;
DROP SEQUENCE seqPK_ManagementApprovals;
DROP SEQUENCE seqPK_Employees;
DROP SEQUENCE seqPK_EmployeeTypes;
DROP SEQUENCE seqPK_Events;
DROP SEQUENCE seqPK_EventTypes;
DROP SEQUENCE seqPK_GradingFormats;
DROP SEQUENCE seqPK_GradeLetters;
DROP SEQUENCE seqPK_ReimbursementStatuses;

/***********************
*Create Database Tables*
***********************/
CREATE TABLE Attachments
(
  ID INT NOT NULL,
  Reimbursement INT NOT NULL,
  AttachmentPath VARCHAR2(260) UNIQUE NOT NULL, --maxPath
  CONSTRAINT PK_Attachments PRIMARY KEY (ID)
);

CREATE TABLE ReimbursementNotes
(
  ID INT NOT NULL,
  Reimbursement INT UNIQUE NOT NULL,
  NoteReason INT NOT NULL,
  Note VARCHAR2(140) NOT NULL,
  CONSTRAINT PK_ReimbursementNotes PRIMARY KEY (ID)
);

CREATE TABLE NoteReasons
(
  ID INT NOT NULL,
  NoteReason VARCHAR2(70) UNIQUE NOT NULL,
  CONSTRAINT PK_NoteReasons PRIMARY KEY (ID)
);

CREATE TABLE Reimbursements
(
  ID INT NOT NULL,
  Employee INT NOT NULL,
  DateSubmitted DATE NOT NULL,
  Event INT UNIQUE NOT NULL,
  WorkTimeMissed VARCHAR2(140) NOT NULL,
  Justification VARCHAR2(140) NOT NULL,
  ProjectedAmount NUMBER(6,2) NOT NULL,
  ReimbursementStatus INT NOT NULL,
  ManagementApprovals INT NOT NULL,
  AmountAwarded NUMBER(6,2),
  CONSTRAINT PK_Reimbursements PRIMARY KEY (ID)
);

CREATE TABLE ManagementApprovals
(
  ID INT NOT NULL,
  DirectSupervisor INT,
  DirectSupervisorDate DATE,
  DepartmentHead INT,
  DepartmentHeadDate DATE,
  BenefitsCoordinator INT,
  BenefitsCoordinatorDate DATE,
  CONSTRAINT PK_ManagementApprovals PRIMARY KEY (ID)
);

CREATE TABLE Employees
(
  ID INT NOT NULL,
  FirstName VARCHAR2(35) NOT NULL,
  LastName VARCHAR2(35) NOT NULL,
  Email VARCHAR2(100) NOT NULL, --firstname.lastname@domain
  Password VARCHAR2(128) NOT NULL,
  EmployeeType INT NOT NULL,
  DirectSupervisor INT,
  DepartmentHead INT,
  CONSTRAINT PK_Employees PRIMARY KEY (ID)
);

CREATE TABLE EmployeeTypes
(
  ID INT NOT NULL,
  EmployeeType VARCHAR2(100) UNIQUE NOT NULL,
  CONSTRAINT PK_EmployeeTypes PRIMARY KEY (ID)
);

CREATE TABLE Events
(
  ID INT NOT NULL,
  Name VARCHAR2(100) NOT NULL,
  EventType INT NOT NULL,
  Description VARCHAR2(140) NOT NULL,
  StartDate DATE NOT NULL,
  EndDate DATE NOT NULL,
  Time TIMESTAMP NOT NULL,
  Location VARCHAR2(175) NOT NULL,
  Cost NUMBER(6,2) NOT NULL,
  GradingFormat INT NOT NULL,
  PassingGrade INT NOT NULL,
  GradeRecieved NUMBER(4,2),
  CONSTRAINT PK_Events PRIMARY KEY (ID)
);

CREATE TABLE EventTypes
(
  ID INT NOT NULL,
  EventType VARCHAR2(70) UNIQUE NOT NULL,
  CostPercentCovered NUMBER(2,1) NOT NULL,
  CONSTRAINT PK_EventTypes PRIMARY KEY (ID)
);

CREATE TABLE GradingFormats
(
  ID INT NOT NULL,
  GradingFormat VARCHAR2(35) UNIQUE NOT NULL, 
  CONSTRAINT PK_GradingFormats PRIMARY KEY (ID)
);

CREATE TABLE GradeLetters
(
  ID INT NOT NULL,
  GradeLetter VARCHAR2(2) UNIQUE NOT NULL,
  MinPercentage NUMBER(2,1) UNIQUE NOT NULL,
  MaxPercentage NUMBER(2,1) UNIQUE NOT NULL,
  CONSTRAINT PK_GradeLetters PRIMARY KEY (ID)
);

CREATE TABLE ReimbursementStatuses
(
  ID INT NOT NULL,
  ReimbursementStatus VARCHAR2(35) UNIQUE NOT NULL,
  CONSTRAINT PK_ReimbursementStatuses PRIMARY KEY (ID)
);


/****************************
*Add Foriegn Key Constraints*
****************************/
ALTER TABLE Attachments ADD CONSTRAINT FK_Reimbursement_1 FOREIGN KEY (Reimbursement) REFERENCES Reimbursements (ID);
ALTER TABLE ReimbursementNotes ADD CONSTRAINT FK_Reimbursement_2 FOREIGN KEY (Reimbursement) REFERENCES Reimbursements (ID);
ALTER TABLE ReimbursementNotes ADD CONSTRAINT FK_NoteReason FOREIGN KEY (NoteReason) REFERENCES NoteReasons (ID);ALTER TABLE Reimbursements ADD CONSTRAINT FK_Employee FOREIGN KEY (Employee) REFERENCES Employees (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_Event FOREIGN KEY (Event) REFERENCES Events (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_ReimbursementStatus FOREIGN KEY (ReimbursementStatus) REFERENCES ReimbursementStatuses (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_ManagementApprovals FOREIGN KEY (ManagementApprovals) REFERENCES ManagementApprovals (ID);
ALTER TABLE ManagementApprovals ADD CONSTRAINT FK_DirectSupervisor_2 FOREIGN KEY (DirectSupervisor) REFERENCES Employees (ID);
ALTER TABLE ManagementApprovals ADD CONSTRAINT FK_DepartmentHead_2 FOREIGN KEY (DepartmentHead) REFERENCES Employees (ID);
ALTER TABLE ManagementApprovals ADD CONSTRAINT FK_BenefitsCoordinator FOREIGN KEY (BenefitsCoordinator) REFERENCES Employees (ID);
ALTER TABLE Employees ADD CONSTRAINT FK_EmployeeType FOREIGN KEY (EmployeeType) REFERENCES EmployeeTypes (ID);
ALTER TABLE Employees ADD CONSTRAINT FK_DirectSupervisor_1 FOREIGN KEY (DirectSupervisor) REFERENCES Employees (ID);
ALTER TABLE Employees ADD CONSTRAINT FK_DepartmentHead_1 FOREIGN KEY (DepartmentHead) REFERENCES Employees (ID);
ALTER TABLE Events ADD CONSTRAINT FK_EventType FOREIGN KEY (EventType) REFERENCES EventTypes (ID);
ALTER TABLE Events ADD CONSTRAINT FK_GradingFormat FOREIGN KEY (GradingFormat) REFERENCES GradingFormats (ID);
ALTER TABLE Events ADD CONSTRAINT FK_GradeCutoff FOREIGN KEY (PassingGrade) REFERENCES GradeLetters (ID);


/*************************
*Add Reference Table Data*
*************************/
INSERT INTO NoteReasons (ID, NoteReason) VALUES (1,'Reimbursement Amount Exceeded');
INSERT INTO NoteReasons (ID, NoteReason) VALUES (2,'Reimbursement Denied');

INSERT INTO EmployeeTypes (ID, EmployeeType) VALUES (1,'Standard');
INSERT INTO EmployeeTypes (ID, EmployeeType) VALUES (2,'Direct Supervisor');
INSERT INTO EmployeeTypes (ID, EmployeeType) VALUES (3,'Department Head');
INSERT INTO EmployeeTypes (ID, EmployeeType) VALUES (4,'Benefits Coordinator');

INSERT INTO EventTypes (ID, EventType, CostPercentCovered) VALUES (1,'University Courses',0.80);
INSERT INTO EventTypes (ID, EventType, CostPercentCovered) VALUES (2,'Seminars',0.60);
INSERT INTO EventTypes (ID, EventType, CostPercentCovered) VALUES (3,'Certification Preparation Classes',0.75);
INSERT INTO EventTypes (ID, EventType, CostPercentCovered) VALUES (4,'Certification',1.00);
INSERT INTO EventTypes (ID, EventType, CostPercentCovered) VALUES (5,'Technical Training',0.90);
INSERT INTO EventTypes (ID, EventType, CostPercentCovered) VALUES (6,'Other',0.30);

INSERT INTO GradingFormats (ID, GradingFormat) VALUES (1,'Letter Grade');
INSERT INTO GradingFormats (ID, GradingFormat) VALUES (2,'Presentation');

INSERT INTO GradeLetters (ID, GradeLetter, MinPercentage, MaxPercentage) VALUES (1,'A',0.9,1.0);
INSERT INTO GradeLetters (ID, GradeLetter, MinPercentage, MaxPercentage) VALUES (2,'B',0.8,0.9);
INSERT INTO GradeLetters (ID, GradeLetter, MinPercentage, MaxPercentage) VALUES (3,'C',0.7,0.8);
INSERT INTO GradeLetters (ID, GradeLetter, MinPercentage, MaxPercentage) VALUES (4,'D',0.6,0.7);
INSERT INTO GradeLetters (ID, GradeLetter, MinPercentage, MaxPercentage) VALUES (5,'F',0.0,0.6);

INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (1,'Pending');
INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (2,'Grade Pending');
INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (3,'Approval Pending');
INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (4,'Awarded');
INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (5,'Canceled');
INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (6,'Urgent');
INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (7,'Denied');


/********************************************
*Create Primary Key Sequences and Procedures*
********************************************/
CREATE SEQUENCE seqPK_Attachments;
CREATE OR REPLACE TRIGGER inc_Attachments
BEFORE INSERT ON Attachments
FOR EACH ROW
  BEGIN
    SELECT seqPK_Attachments.NEXTVAL
    INTO :NEW.ID
    FROM DUAL;
  END;
/

CREATE SEQUENCE seqPK_ReimbursementNotes;
CREATE OR REPLACE TRIGGER inc_ReimbursementNotes
BEFORE INSERT ON ReimbursementNotes
FOR EACH ROW
  BEGIN
    SELECT seqPK_ReimbursementNotes.NEXTVAL
    INTO   :NEW.ID
    FROM   DUAL;
  END;
/

CREATE SEQUENCE seqPK_NoteReasons;
CREATE OR REPLACE TRIGGER inc_NoteReasons
BEFORE INSERT ON NoteReasons
FOR EACH ROW
  BEGIN
    SELECT seqPK_NoteReasons.NEXTVAL
    INTO   :NEW.ID
    FROM   DUAL;
  END;
/

CREATE SEQUENCE seqPK_Reimbursements;
CREATE OR REPLACE TRIGGER inc_Reimbursements
BEFORE INSERT ON Reimbursements
FOR EACH ROW
  BEGIN
    SELECT seqPK_Reimbursements.NEXTVAL
    INTO   :NEW.ID
    FROM   DUAL;
  END;
/

CREATE SEQUENCE seqPK_ManagementApprovals;
CREATE OR REPLACE TRIGGER inc_ManagementApprovals
BEFORE INSERT ON ManagementApprovals
FOR EACH ROW
  BEGIN
    SELECT seqPK_ManagementApprovals.NEXTVAL
    INTO   :NEW.ID
    FROM   DUAL;
  END;
/

CREATE SEQUENCE seqPK_Employees;
CREATE OR REPLACE TRIGGER inc_Employees
BEFORE INSERT ON Employees
FOR EACH ROW
  BEGIN
    SELECT seqPK_Employees.NEXTVAL
    INTO   :NEW.ID
    FROM   DUAL;
  END;
/

CREATE SEQUENCE seqPK_EmployeeTypes;
CREATE OR REPLACE TRIGGER inc_EmployeeTypes
BEFORE INSERT ON EmployeeTypes
FOR EACH ROW
  BEGIN
    SELECT seqPK_EmployeeTypes.NEXTVAL
    INTO   :NEW.ID
    FROM   DUAL;
  END;
/

CREATE SEQUENCE seqPK_Events;
CREATE OR REPLACE TRIGGER inc_Events
BEFORE INSERT ON Events
FOR EACH ROW
  BEGIN
    SELECT seqPK_Events.NEXTVAL
    INTO   :NEW.ID
    FROM   DUAL;
  END;
/

CREATE SEQUENCE seqPK_EventTypes;
CREATE OR REPLACE TRIGGER inc_EventTypes
BEFORE INSERT ON EventTypes
FOR EACH ROW
  BEGIN
    SELECT seqPK_EventTypes.NEXTVAL
    INTO   :NEW.ID
    FROM   DUAL;
  END;
/

CREATE SEQUENCE seqPK_GradingFormats;
CREATE OR REPLACE TRIGGER inc_GradingFormats
BEFORE INSERT ON GradingFormats
FOR EACH ROW
  BEGIN
    SELECT seqPK_GradingFormats.NEXTVAL
    INTO   :NEW.ID
    FROM   DUAL;
  END;
/

CREATE SEQUENCE seqPK_GradeLetters;
CREATE OR REPLACE TRIGGER inc_GradeLetters
BEFORE INSERT ON GradeLetters
FOR EACH ROW
  BEGIN
    SELECT seqPK_GradeLetters.NEXTVAL
    INTO   :NEW.ID
    FROM   DUAL;
  END;
/

CREATE SEQUENCE seqPK_ReimbursementStatuses;
CREATE OR REPLACE TRIGGER inc_ReimbursementStatuses
BEFORE INSERT ON ReimbursementStatuses
FOR EACH ROW
  BEGIN
    SELECT seqPK_ReimbursementStatuses.NEXTVAL
    INTO   :NEW.ID
    FROM   DUAL;
  END;
/