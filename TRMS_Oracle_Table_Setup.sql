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

CREATE TABLE Attachments
(
  ID INT NOT NULL,
  Reimbursement INT NOT NULL,
  AttachmentPath VARCHAR2(160) UNIQUE NOT NULL,
  CONSTRAINT PK_Attachments PRIMARY KEY (ID)
);

CREATE TABLE Reimbursements
(
  ID INT NOT NULL,
  Employee INT NOT NULL,
  DateSubmitted DATE NOT NULL,
  Event INT UNIQUE NOT NULL,
  WorkTimeMissed VARCHAR2(160) NOT NULL,
  Justification VARCHAR2(160) NOT NULL,
  ProjectedAmount NUMBER(6,2) NOT NULL,
  ReimbursementStatus INT NOT NULL,
  ManagementApprovals INT NOT NULL,
  AmountAwarded NUMBER(6,2) NOT NULL,
  CONSTRAINT PK_Reimbursements PRIMARY KEY (ID)
);

CREATE TABLE Employees
(
  ID INT NOT NULL,
  FirstName VARCHAR2(160) NOT NULL,
  LastName VARCHAR2(160) NOT NULL,
  Email VARCHAR2(160) NOT NULL,
  Password VARCHAR2(160) NOT NULL,
  EmployeeType INT NOT NULL,
  DirectSupervisor INT NOT NULL,
  DepartmentHead INT NOT NULL,
  CONSTRAINT PK_Employees PRIMARY KEY (ID)
);

CREATE TABLE EmployeeTypes
(
  ID INT NOT NULL,
  EmployeeType VARCHAR2(160) UNIQUE NOT NULL,
  CONSTRAINT PK_EmployeeTypes PRIMARY KEY (ID)
);

CREATE TABLE Events
(
  ID INT NOT NULL,
  Name VARCHAR2(160) NOT NULL,
  EventType INT NOT NULL,
  Description VARCHAR2(160) NOT NULL,
  StartDate DATE NOT NULL,
  EndDate DATE NOT NULL,
  Time TIMESTAMP NOT NULL,
  Location VARCHAR2(160) NOT NULL,
  Cost NUMBER(6,2) NOT NULL,
  GradingFormat INT NOT NULL,
  PassingGrade INT NOT NULL,
  GradeRecieved NUMBER(4,2) NOT NULL,
  CONSTRAINT PK_Events PRIMARY KEY (ID)
);

CREATE TABLE EventTypes
(
  ID INT NOT NULL,
  EventType VARCHAR2(160) UNIQUE NOT NULL,
  CostPercentCovered NUMBER(2,1) NOT NULL,
  CONSTRAINT PK_EventTypes PRIMARY KEY (ID)
);

CREATE TABLE GradingFormats
(
  ID INT NOT NULL,
  GradingFormat VARCHAR2(160) UNIQUE NOT NULL, 
  CONSTRAINT PK_GradingFormats PRIMARY KEY (ID)
);

CREATE TABLE GradeLetters
(
  ID INT NOT NULL,
  GradeLetter VARCHAR2(160) UNIQUE NOT NULL,
  MinPercentage NUMBER(2,1) UNIQUE NOT NULL,
  MaxPercentage NUMBER(2,1) UNIQUE NOT NULL,
  CONSTRAINT PK_GradeLetters PRIMARY KEY (ID)
);

CREATE TABLE ReimbursementStatuses
(
  ID INT NOT NULL,
  ReimbursementStatus VARCHAR2(160) UNIQUE NOT NULL,
  CONSTRAINT PK_ReimbursementStatuses PRIMARY KEY (ID)
);

CREATE TABLE ManagementApprovals
(
  ID INT NOT NULL,
  DirectSupervisor INT NOT NULL,
  DepartmentHead INT NOT NULL,
  BenefitsCoordinator INT NOT NULL,
  DirectSupervisorDate DATE NOT NULL,
  DepartmentHeadDate DATE NOT NULL,
  BenefitsCoordinatorDate DATE NOT NULL,
  CONSTRAINT PK_ManagementApprovals PRIMARY KEY (ID)
);

CREATE TABLE ReimbursementNotes
(
  ID INT NOT NULL,
  Reimbursement INT UNIQUE NOT NULL,
  NoteReason INT NOT NULL,
  Note VARCHAR2(160) NOT NULL,
  CONSTRAINT PK_ReimbursementNotes PRIMARY KEY (ID)
);

CREATE TABLE NoteReasons
(
  ID INT NOT NULL,
  NoteReason VARCHAR2(160) UNIQUE NOT NULL,
  CONSTRAINT PK_NoteReasons PRIMARY KEY (ID)
);

ALTER TABLE Attachments ADD CONSTRAINT FK_Reimbursement_1 FOREIGN KEY (Reimbursement) REFERENCES Reimbursements (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_Employee FOREIGN KEY (Employee) REFERENCES Employees (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_Event FOREIGN KEY (Event) REFERENCES Events (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_ReimbursementStatus FOREIGN KEY (ReimbursementStatus) REFERENCES ReimbursementStatuses (ID);
ALTER TABLE Reimbursements ADD CONSTRAINT FK_ManagementApprovals FOREIGN KEY (ManagementApprovals) REFERENCES ManagementApprovals (ID);
ALTER TABLE Employees ADD CONSTRAINT FK_EmployeeType FOREIGN KEY (EmployeeType) REFERENCES EmployeeTypes (ID);
ALTER TABLE Employees ADD CONSTRAINT FK_DirectSupervisor_1 FOREIGN KEY (DirectSupervisor) REFERENCES Employees (ID);
ALTER TABLE Employees ADD CONSTRAINT FK_DepartmentHead_1 FOREIGN KEY (DepartmentHead) REFERENCES Employees (ID);
ALTER TABLE Events ADD CONSTRAINT FK_EventType FOREIGN KEY (EventType) REFERENCES EventTypes (ID);
ALTER TABLE Events ADD CONSTRAINT FK_GradingFormat FOREIGN KEY (GradingFormat) REFERENCES GradingFormats (ID);
ALTER TABLE Events ADD CONSTRAINT FK_GradeCutoff FOREIGN KEY (PassingGrade) REFERENCES GradeLetters (ID);
ALTER TABLE ManagementApprovals ADD CONSTRAINT FK_DirectSupervisor_2 FOREIGN KEY (DirectSupervisor) REFERENCES Employees (ID);
ALTER TABLE ManagementApprovals ADD CONSTRAINT FK_DepartmentHead_2 FOREIGN KEY (DepartmentHead) REFERENCES Employees (ID);
ALTER TABLE ManagementApprovals ADD CONSTRAINT FK_BenefitsCoordinator FOREIGN KEY (BenefitsCoordinator) REFERENCES Employees (ID);
ALTER TABLE ReimbursementNotes ADD CONSTRAINT FK_Reimbursement_2 FOREIGN KEY (Reimbursement) REFERENCES Reimbursements (ID);
ALTER TABLE ReimbursementNotes ADD CONSTRAINT FK_NoteReason FOREIGN KEY (NoteReason) REFERENCES NoteReasons (ID);