INSERT INTO Events (Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade) VALUES ('AUniversityCourse',1,'A University Course',TO_DATE('2017-01-01','yyyy-mm-dd'),TO_DATE('2017-01-15','yyyy-mm-dd'),'12:00 PM','Location1',100,1,3);
INSERT INTO Events (Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade) VALUES ('ASeminar',2,'A Seminar',TO_DATE('2017-02-01','yyyy-mm-dd'),TO_DATE('2017-02-01','yyyy-mm-dd'),'01:00 PM','Location2',75,2,3);
INSERT INTO Events (Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade) VALUES ('ACertificationPreperationClass',3,'A Certification Preperation Class',TO_DATE('2017-03-01','yyyy-mm-dd'),TO_DATE('2017-03-15','yyyy-mm-dd'),'02:00 PM','Location3',50,1,3);
INSERT INTO Events (Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade) VALUES ('ACertificationExam',4,'A Certification Exam',TO_DATE('2017-04-01','yyyy-mm-dd'),TO_DATE('2017-04-01','yyyy-mm-dd'),'03:00 PM','Location4',150,1,3);
INSERT INTO Events (Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade) VALUES ('SomeTechnicalTraining',5,'Some Technical Training',TO_DATE('2017-05-01','yyyy-mm-dd'),TO_DATE('2017-05-15','yyyy-mm-dd'),'04:00 PM','Location5',200,2,3);
INSERT INTO Events (Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade) VALUES ('Other',6,'Some Other Event',TO_DATE('2017-06-01','yyyy-mm-dd'),TO_DATE('2017-06-15','yyyy-mm-dd'),'05:00 PM','Location6',25,2,3);

INSERT INTO Employees (FirstName,LastName,Email,Password,EmployeeType) VALUES ('Victor','Rathmell','Victor.Rathmell@mail.com','SzDT1sq',1);
INSERT INTO Employees (FirstName,LastName,Email,Password,EmployeeType) VALUES ('Bellanca','Brass','Bellanca.Brass@mail.com','J6j2l6qq8U0',1);
INSERT INTO Employees (FirstName,LastName,Email,Password,EmployeeType) VALUES ('Judi','Goricke','Judi.Goricke@mail.com','6P0ik8cnKa',1);
INSERT INTO Employees (FirstName,LastName,Email,Password,EmployeeType) VALUES ('Abby','Colwill','Abby.Colwill@mail.com','a5N10Vha0egd',1);
INSERT INTO Employees (FirstName,LastName,Email,Password,EmployeeType) VALUES ('Jania','Irwin','Jania.Irwin@mail.com','UkhubWZNA',1);
INSERT INTO Employees (FirstName,LastName,Email,Password,EmployeeType) VALUES ('Drusi','Skegg','Drusi.Skegg@mail.com','C86GqRZB',1);
INSERT INTO Employees (FirstName,LastName,Email,Password,EmployeeType) VALUES ('Aldridge','MacCurtain','Aldridge.MacCurtain@mail.com','iTcBGzKjAZ8',1);
INSERT INTO Employees (FirstName,LastName,Email,Password,EmployeeType) VALUES ('Alford','Jans','Alford.Jans@mail.com','NeSXE15e2Bg',1);
INSERT INTO Employees (FirstName,LastName,Email,Password,EmployeeType) VALUES ('Lindy','Raywood','Lindy.Raywood@mail.com','ffvWvXZRxlI',1);
INSERT INTO Employees (FirstName,LastName,Email,Password,EmployeeType) VALUES ('Merry','Sleeny','Merry.Sleeny@mail.com','owhYxVDIVTV',1);


INSERT INTO ManagementApprovals(ID) VALUES (1);


INSERT INTO Reimbursements (Employee, DateSubmitted, Event, WorkTimeMissed, Justification, ProjectedAmount, ReimbursementStatus, ManagementApprovals) VALUES (1,TO_DATE('2017-01-01','yyyy-mm-dd'),1,'4 Weeks','JUSTIFIED',0,1,1);
INSERT INTO Reimbursements (Employee, DateSubmitted, Event, WorkTimeMissed, Justification, ProjectedAmount, ReimbursementStatus, ManagementApprovals) VALUES (1,TO_DATE('2017-01-01','yyyy-mm-dd'),2,'2 Days','JUSTIFIED',0,1,2);
INSERT INTO Reimbursements (Employee, DateSubmitted, Event, WorkTimeMissed, Justification, ProjectedAmount, ReimbursementStatus, ManagementApprovals) VALUES (1,TO_DATE('2017-01-01','yyyy-mm-dd'),3,'1 Week','JUSTIFIED',0,1,3);
INSERT INTO Reimbursements (Employee, DateSubmitted, Event, WorkTimeMissed, Justification, ProjectedAmount, ReimbursementStatus, ManagementApprovals) VALUES (1,TO_DATE('2017-01-01','yyyy-mm-dd'),4,'1 Day','JUSTIFIED',0,1,4);
INSERT INTO Reimbursements (Employee, DateSubmitted, Event, WorkTimeMissed, Justification, ProjectedAmount, ReimbursementStatus, ManagementApprovals) VALUES (1,TO_DATE('2017-01-01','yyyy-mm-dd'),5,'2 Weeks','JUSTIFIED',0,1,5);
INSERT INTO Reimbursements (Employee, DateSubmitted, Event, WorkTimeMissed, Justification, ProjectedAmount, ReimbursementStatus, ManagementApprovals) VALUES (1,TO_DATE('2017-01-01','yyyy-mm-dd'),6,'4 Days','JUSTIFIED',0,1,6);


Attachments;
ReimbursementNotes;
Reimbursements;
ManagementApprovals;