/*******************
*RESET THE DATABASE*
*******************/
DROP TABLE ReimbursementNotes;
DROP TABLE NoteReasons;
DROP TABLE Reimbursements;
DROP TABLE Employees;
DROP TABLE EmployeeTypes;
DROP TABLE Events;
DROP TABLE EventTypes;
DROP TABLE GradingFormats;
DROP TABLE GradeLetterScores;
DROP TABLE ReimbursementStatuses;
DROP SEQUENCE seqPK_ReimbursementNotes;
DROP SEQUENCE seqPK_Reimbursements;
DROP SEQUENCE seqPK_Employees;
DROP SEQUENCE seqPK_Events;

/***********************
*Create Database Tables*
***********************/
CREATE TABLE ReimbursementNotes
(
  ID INT NOT NULL,
  Reimbursement INT UNIQUE NOT NULL,
  NoteReason INT NOT NULL,
  Note VARCHAR2(140) NOT NULL,
  CONSTRAINT PK_ReimbursementNotes PRIMARY KEY (ID)
);

CREATE TABLE NoteReasons --REFERENCE TABLE
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
  BenefitsCoordinator INT,
  DirectSupervisorApproveDate DATE,
  DepartmentHeadApproveDate DATE,
  BenefitsCoordinatorApproveDate DATE,
  ReimbursementStatus INT NOT NULL,
  AttachmentDirectory VARCHAR2(260),
  AmountAwarded NUMBER(6,2),
  CONSTRAINT PK_Reimbursements PRIMARY KEY (ID)
);

CREATE TABLE Employees
(
  ID INT NOT NULL,
  FirstName VARCHAR2(35) NOT NULL,
  LastName VARCHAR2(35) NOT NULL,
  Email VARCHAR2(100) UNIQUE NOT NULL,--firstname.lastname@domain
  Password VARCHAR2(128) NOT NULL,
  EmployeeType INT NOT NULL,
  DirectSupervisor INT,
  DepartmentHead INT,
  AvailableReimbursement NUMBER(6,2),
  CONSTRAINT PK_Employees PRIMARY KEY (ID)
);

CREATE TABLE EmployeeTypes --REFERENCE TABLE
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
  Time VARCHAR2(8) NOT NULL,--HH:mm AM/PM
  Location VARCHAR2(175) NOT NULL,
  Cost NUMBER(6,2) NOT NULL,
  GradingFormat INT NOT NULL,
  PassingGrade INT NOT NULL,--Presentations get Grades as well
  RecievedGrade NUMBER(4,2),--Points #/#
  CONSTRAINT PK_Events PRIMARY KEY (ID)
);

CREATE TABLE EventTypes --REFERENCE TABLE
(
  ID INT NOT NULL,
  EventType VARCHAR2(70) UNIQUE NOT NULL,
  PercentCovered NUMBER(2,1) NOT NULL,
  CONSTRAINT PK_EventTypes PRIMARY KEY (ID)
);

CREATE TABLE GradingFormats --REFERENCE TABLE
(
  ID INT NOT NULL,
  GradingFormat VARCHAR2(35) UNIQUE NOT NULL,
  CONSTRAINT PK_GradingFormats PRIMARY KEY (ID)
);

CREATE TABLE GradeLetterScores --REFERENCE TABLE
(
  ID INT NOT NULL,
  GradeLetter VARCHAR2(2) UNIQUE NOT NULL,
  MinPercentage NUMBER(2,1) UNIQUE NOT NULL,
  MaxPercentage NUMBER(2,1) UNIQUE NOT NULL,
  CONSTRAINT PK_GradeLetterScores PRIMARY KEY (ID)
);

CREATE TABLE ReimbursementStatuses --REFERENCE TABLE
(
  ID INT NOT NULL,
  ReimbursementStatus VARCHAR2(35) UNIQUE NOT NULL,
  CONSTRAINT PK_ReimbursementStatuses PRIMARY KEY (ID)
);


/****************************
*Add Foriegn Key Constraints*
****************************/
ALTER TABLE ReimbursementNotes ADD CONSTRAINT FK_Reimbursement_2 FOREIGN KEY (Reimbursement) REFERENCES Reimbursements (ID);
ALTER TABLE ReimbursementNotes ADD CONSTRAINT FK_NoteReason FOREIGN KEY (NoteReason) REFERENCES NoteReasons (ID);ALTER TABLE Reimbursements ADD CONSTRAINT FK_Employee FOREIGN KEY (Employee) REFERENCES Employees (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_Event FOREIGN KEY (Event) REFERENCES Events (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_ReimbursementStatus FOREIGN KEY (ReimbursementStatus) REFERENCES ReimbursementStatuses (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_BenefitsCoordinator FOREIGN KEY (BenefitsCoordinator) REFERENCES Employees (ID);
ALTER TABLE Employees ADD CONSTRAINT FK_EmployeeType FOREIGN KEY (EmployeeType) REFERENCES EmployeeTypes (ID);
ALTER TABLE Employees ADD CONSTRAINT FK_DirectSupervisor_1 FOREIGN KEY (DirectSupervisor) REFERENCES Employees (ID);
ALTER TABLE Employees ADD CONSTRAINT FK_DepartmentHead_1 FOREIGN KEY (DepartmentHead) REFERENCES Employees (ID);
ALTER TABLE Events ADD CONSTRAINT FK_EventType FOREIGN KEY (EventType) REFERENCES EventTypes (ID);
ALTER TABLE Events ADD CONSTRAINT FK_GradingFormat FOREIGN KEY (GradingFormat) REFERENCES GradingFormats (ID);
ALTER TABLE Events ADD CONSTRAINT FK_Passing FOREIGN KEY (PassingGrade) REFERENCES GradeLetterScores (ID);


/*************************
*Add Reference Table Data*
*************************/
INSERT INTO NoteReasons (ID,NoteReason) VALUES (1,'Reimbursement Denied');
INSERT INTO NoteReasons (ID,NoteReason) VALUES (2,'Reimbursement Amount Exceeded');

INSERT INTO EmployeeTypes (ID,EmployeeType) VALUES (1,'Standard');
INSERT INTO EmployeeTypes (ID,EmployeeType) VALUES (2,'Benefits Coordinator');
INSERT INTO EmployeeTypes (ID,EmployeeType) VALUES (3,'Direct Supervisor');
INSERT INTO EmployeeTypes (ID,EmployeeType) VALUES (4,'Department Head');

INSERT INTO EventTypes (ID,EventType,PercentCovered) VALUES (1,'University Course',0.80);
INSERT INTO EventTypes (ID,EventType,PercentCovered) VALUES (2,'Seminar',0.60);
INSERT INTO EventTypes (ID,EventType,PercentCovered) VALUES (3,'Certification Preparation Classe',0.75);
INSERT INTO EventTypes (ID,EventType,PercentCovered) VALUES (4,'Certification Exam',1.00);
INSERT INTO EventTypes (ID,EventType,PercentCovered) VALUES (5,'Technical Training',0.90);
INSERT INTO EventTypes (ID,EventType,PercentCovered) VALUES (6,'Other',0.30);

INSERT INTO GradingFormats (ID,GradingFormat) VALUES (1,'Letter Grade');
INSERT INTO GradingFormats (ID,GradingFormat) VALUES (2,'Presentation');

INSERT INTO GradeLetterScores (ID,GradeLetter,MinPercentage,MaxPercentage) VALUES (1,'A',0.9,1.0);
INSERT INTO GradeLetterScores (ID,GradeLetter,MinPercentage,MaxPercentage) VALUES (2,'B',0.8,0.9);
INSERT INTO GradeLetterScores (ID,GradeLetter,MinPercentage,MaxPercentage) VALUES (3,'C',0.7,0.8);
INSERT INTO GradeLetterScores (ID,GradeLetter,MinPercentage,MaxPercentage) VALUES (4,'D',0.6,0.7);
INSERT INTO GradeLetterScores (ID,GradeLetter,MinPercentage,MaxPercentage) VALUES (5,'F',0.0,0.6);

INSERT INTO ReimbursementStatuses (ID,ReimbursementStatus) VALUES (1,'Pending');
INSERT INTO ReimbursementStatuses (ID,ReimbursementStatus) VALUES (2,'Grade Pending');
INSERT INTO ReimbursementStatuses (ID,ReimbursementStatus) VALUES (3,'Approval Pending');
INSERT INTO ReimbursementStatuses (ID,ReimbursementStatus) VALUES (4,'Awarded');
INSERT INTO ReimbursementStatuses (ID,ReimbursementStatus) VALUES (5,'Cancelled');
INSERT INTO ReimbursementStatuses (ID,ReimbursementStatus) VALUES (6,'Urgent');
INSERT INTO ReimbursementStatuses (ID,ReimbursementStatus) VALUES (7,'Denied');


/*****************************
*Create Primary Key Sequences*
*****************************/
CREATE SEQUENCE seqPK_ReimbursementNotes;
CREATE SEQUENCE seqPK_Reimbursements;
CREATE SEQUENCE seqPK_Employees;
CREATE SEQUENCE seqPK_Events;



/****************
*Create Triggers*
****************/
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


/********************
*Add Mock Table Data*
********************/
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor,DepartmentHead,AvailableReimbursement) VALUES (1,'Department','Head','department.head@email.com','password',4,null,null,null);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor,DepartmentHead,AvailableReimbursement) VALUES (2,'Direct','Supervisor','direct.supervisor@email.com','password',3,1,1,null);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor,DepartmentHead,AvailableReimbursement) VALUES (3,'BenCo','Supervisor','ben.co.supervisor@email.com','password',3,null,null,null);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor,DepartmentHead,AvailableReimbursement) VALUES (4,'Benefits','Coordinator','benefits.cordinator@email.com','password',2,3,null,null);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor,DepartmentHead,AvailableReimbursement) VALUES (5,'Code','Monkee','code.monkee@mail.com','password',1,2,1,1000);

INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade,RecievedGrade) VALUES (1,'AUniversityCourse',1,'A University Course',TO_DATE('2017-01-08','yyyy-mm-dd'),TO_DATE('2017-01-15','yyyy-mm-dd'),'12:00 PM','Location1',100,1,3,null);
INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade,RecievedGrade) VALUES (2,'ASeminar',2,'A Seminar',TO_DATE('2017-02-08','yyyy-mm-dd'),TO_DATE('2017-02-08','yyyy-mm-dd'),'01:00 PM','Location2',75,2,3,null);
INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade,RecievedGrade) VALUES (3,'ACertificationPreperationClass',3,'A Certification Preperation Class',TO_DATE('2017-03-08','yyyy-mm-dd'),TO_DATE('2017-03-15','yyyy-mm-dd'),'02:00 PM','Location3',50,1,3,null);
INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade,RecievedGrade) VALUES (4,'ACertificationExam',4,'A Certification Exam',TO_DATE('2017-04-08','yyyy-mm-dd'),TO_DATE('2017-04-08','yyyy-mm-dd'),'03:00 PM','Location4',150,1,3,null);
INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade,RecievedGrade) VALUES (5,'SomeTechnicalTraining',5,'Some Technical Training',TO_DATE('2017-05-08','yyyy-mm-dd'),TO_DATE('2017-05-15','yyyy-mm-dd'),'04:00 PM','Location5',200,2,3,null);
INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade,RecievedGrade) VALUES (6,'Other',6,'Some Other Event',TO_DATE('2017-06-08','yyyy-mm-dd'),TO_DATE('2017-06-15','yyyy-mm-dd'),'05:00 PM','Location6',25,2,3,null);

INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,BenefitsCoordinator,DirectSupervisorApproveDate,DepartmentHeadApproveDate,BenefitsCoordinatorApproveDate,ReimbursementStatus) VALUES (1,5,TO_DATE('2017-01-01','yyyy-mm-dd'),1,'4 Weeks','JUSTIFIED1',80,null,null,null,null,1);
INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,BenefitsCoordinator,DirectSupervisorApproveDate,DepartmentHeadApproveDate,BenefitsCoordinatorApproveDate,ReimbursementStatus) VALUES (2,5,TO_DATE('2017-02-01','yyyy-mm-dd'),2,'2 Days','JUSTIFIED2',45,null,null,null,null,1);
INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,BenefitsCoordinator,DirectSupervisorApproveDate,DepartmentHeadApproveDate,BenefitsCoordinatorApproveDate,ReimbursementStatus) VALUES (3,5,TO_DATE('2017-03-01','yyyy-mm-dd'),3,'1 Week','JUSTIFIED3',37.5,null,null,null,null,1);
INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,BenefitsCoordinator,DirectSupervisorApproveDate,DepartmentHeadApproveDate,BenefitsCoordinatorApproveDate,ReimbursementStatus) VALUES (4,5,TO_DATE('2017-04-01','yyyy-mm-dd'),4,'1 Day','JUSTIFIED4',150,null,null,null,null,1);
INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,BenefitsCoordinator,DirectSupervisorApproveDate,DepartmentHeadApproveDate,BenefitsCoordinatorApproveDate,ReimbursementStatus) VALUES (5,5,TO_DATE('2017-05-01','yyyy-mm-dd'),5,'2 Weeks','JUSTIFIED5',180,null,null,null,null,1);
INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,BenefitsCoordinator,DirectSupervisorApproveDate,DepartmentHeadApproveDate,BenefitsCoordinatorApproveDate,ReimbursementStatus) VALUES (6,5,TO_DATE('2017-06-01','yyyy-mm-dd'),6,'4 Days','JUSTIFIED6',7.5,null,null,null,null,1);