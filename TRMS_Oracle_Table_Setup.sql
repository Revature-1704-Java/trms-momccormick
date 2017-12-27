/*******************
*RESET THE DATABASE*
*******************/
DROP TABLE Attachments;
DROP TABLE ReimbursementNotes;
DROP TABLE NoteReasons;
DROP TABLE Reimbursements;
DROP TABLE ApprovalDates;
DROP TABLE Employees;
DROP TABLE EmployeeTypes;
DROP TABLE Events;
DROP TABLE EventTypes;
DROP TABLE Grades;
DROP TABLE GradingFormats;
DROP TABLE GradeScores;
DROP TABLE ReimbursementStatuses;

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
  ReimbursementStatus INT NOT NULL,
  BenefitsCoordinator INT,
  ApprovalDates INT UNIQUE NOT NULL,
  AmountAwarded NUMBER(6,2),
  CONSTRAINT PK_Reimbursements PRIMARY KEY (ID)
);

CREATE TABLE ApprovalDates
(
  ID INT NOT NULL,
  DirectSupervisor DATE,
  DepartmentHead DATE,
  BenefitsCoordinator DATE,
  CONSTRAINT PK_ApprovalDates PRIMARY KEY (ID)
);

CREATE TABLE Employees
(
  ID INT NOT NULL,
  FirstName VARCHAR2(35) NOT NULL,
  LastName VARCHAR2(35) NOT NULL,
  Email VARCHAR2(100) UNIQUE NOT NULL, --firstname.lastname@domain
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
  Time VARCHAR2(8) NOT NULL, --HH:mm AM/PM
  Location VARCHAR2(175) NOT NULL,
  Cost NUMBER(6,2) NOT NULL,
  Grade INT UNIQUE NOT NULL,
  CONSTRAINT PK_Events PRIMARY KEY (ID)
);

CREATE TABLE EventTypes --REFERENCE TABLE
(
  ID INT NOT NULL,
  EventType VARCHAR2(70) UNIQUE NOT NULL,
  PercentCovered NUMBER(2,1) NOT NULL,
  CONSTRAINT PK_EventTypes PRIMARY KEY (ID)
);

CREATE TABLE Grades
(
  ID INT NOT NULL,
  GradingFormat INT NOT NULL,
  Passing INT NOT NULL, --Presentations get Grades as well
  Recieved NUMBER(4,2), --Points #/#
  CONSTRAINT PK_Grades PRIMARY KEY (ID)
);

CREATE TABLE GradingFormats --REFERENCE TABLE
(
  ID INT NOT NULL,
  GradingFormat VARCHAR2(35) UNIQUE NOT NULL, 
  CONSTRAINT PK_GradingFormats PRIMARY KEY (ID)
);

CREATE TABLE GradeScores --REFERENCE TABLE
(
  ID INT NOT NULL,
  GradeLetter VARCHAR2(2) UNIQUE NOT NULL,
  MinPercentage NUMBER(2,1) UNIQUE NOT NULL,
  MaxPercentage NUMBER(2,1) UNIQUE NOT NULL,
  CONSTRAINT PK_GradeScores PRIMARY KEY (ID)
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
ALTER TABLE Attachments ADD CONSTRAINT FK_Reimbursement_1 FOREIGN KEY (Reimbursement) REFERENCES Reimbursements (ID);
ALTER TABLE ReimbursementNotes ADD CONSTRAINT FK_Reimbursement_2 FOREIGN KEY (Reimbursement) REFERENCES Reimbursements (ID);
ALTER TABLE ReimbursementNotes ADD CONSTRAINT FK_NoteReason FOREIGN KEY (NoteReason) REFERENCES NoteReasons (ID);ALTER TABLE Reimbursements ADD CONSTRAINT FK_Employee FOREIGN KEY (Employee) REFERENCES Employees (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_Event FOREIGN KEY (Event) REFERENCES Events (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_ReimbursementStatus FOREIGN KEY (ReimbursementStatus) REFERENCES ReimbursementStatuses (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_ApprovalDates FOREIGN KEY (ApprovalDates) REFERENCES ApprovalDates (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_BenefitsCoordinator FOREIGN KEY (BenefitsCoordinator) REFERENCES Employees (ID);
ALTER TABLE Employees ADD CONSTRAINT FK_EmployeeType FOREIGN KEY (EmployeeType) REFERENCES EmployeeTypes (ID);
ALTER TABLE Employees ADD CONSTRAINT FK_DirectSupervisor_1 FOREIGN KEY (DirectSupervisor) REFERENCES Employees (ID);
ALTER TABLE Employees ADD CONSTRAINT FK_DepartmentHead_1 FOREIGN KEY (DepartmentHead) REFERENCES Employees (ID);
ALTER TABLE Events ADD CONSTRAINT FK_EventType FOREIGN KEY (EventType) REFERENCES EventTypes (ID);
ALTER TABLE Events ADD CONSTRAINT FK_Grade FOREIGN KEY (Grade) REFERENCES Grades (ID);
ALTER TABLE Grades ADD CONSTRAINT FK_GradingFormat FOREIGN KEY (GradingFormat) REFERENCES GradingFormats (ID);
ALTER TABLE Grades ADD CONSTRAINT FK_Passing FOREIGN KEY (Passing) REFERENCES GradeScores (ID);


/*************************
*Add Reference Table Data*
*************************/
INSERT INTO NoteReasons (ID, NoteReason) VALUES (1,'Reimbursement Amount Exceeded');
INSERT INTO NoteReasons (ID, NoteReason) VALUES (2,'Reimbursement Denied');

INSERT INTO EmployeeTypes (ID, EmployeeType) VALUES (1,'Standard');
INSERT INTO EmployeeTypes (ID, EmployeeType) VALUES (2,'Direct Supervisor');
INSERT INTO EmployeeTypes (ID, EmployeeType) VALUES (3,'Department Head');
INSERT INTO EmployeeTypes (ID, EmployeeType) VALUES (4,'Benefits Coordinator');

INSERT INTO EventTypes (ID, EventType, PercentCovered) VALUES (1,'University Courses',0.80);
INSERT INTO EventTypes (ID, EventType, PercentCovered) VALUES (2,'Seminars',0.60);
INSERT INTO EventTypes (ID, EventType, PercentCovered) VALUES (3,'Certification Preparation Classes',0.75);
INSERT INTO EventTypes (ID, EventType, PercentCovered) VALUES (4,'Certification Exam',1.00);
INSERT INTO EventTypes (ID, EventType, PercentCovered) VALUES (5,'Technical Training',0.90);
INSERT INTO EventTypes (ID, EventType, PercentCovered) VALUES (6,'Other',0.30);

INSERT INTO GradingFormats (ID, GradingFormat) VALUES (1,'Letter Grade');
INSERT INTO GradingFormats (ID, GradingFormat) VALUES (2,'Presentation');

INSERT INTO GradeScores (ID, GradeLetter, MinPercentage, MaxPercentage) VALUES (1,'A',0.9,1.0);
INSERT INTO GradeScores (ID, GradeLetter, MinPercentage, MaxPercentage) VALUES (2,'B',0.8,0.9);
INSERT INTO GradeScores (ID, GradeLetter, MinPercentage, MaxPercentage) VALUES (3,'C',0.7,0.8);
INSERT INTO GradeScores (ID, GradeLetter, MinPercentage, MaxPercentage) VALUES (4,'D',0.6,0.7);
INSERT INTO GradeScores (ID, GradeLetter, MinPercentage, MaxPercentage) VALUES (5,'F',0.0,0.6);

INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (1,'Initial Approval Pending');
INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (2,'Grade Pending');
INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (3,'Management Approval Pending');
INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (4,'Awarded');
INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (5,'Canceled');
INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (6,'Urgent');
INSERT INTO ReimbursementStatuses (ID, ReimbursementStatus) VALUES (7,'Denied');
