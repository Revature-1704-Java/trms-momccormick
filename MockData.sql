INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade) VALUES (0,'AUniversityCourse',1,'A University Course',TO_DATE('2017-01-08','yyyy-mm-dd'),TO_DATE('2017-01-15','yyyy-mm-dd'),'12:00 PM','Location1',100,1,3);
INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade) VALUES (1,'ASeminar',2,'A Seminar',TO_DATE('2017-02-08','yyyy-mm-dd'),TO_DATE('2017-02-08','yyyy-mm-dd'),'01:00 PM','Location2',75,2,3);
INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade) VALUES (2,'ACertificationPreperationClass',3,'A Certification Preperation Class',TO_DATE('2017-03-08','yyyy-mm-dd'),TO_DATE('2017-03-15','yyyy-mm-dd'),'02:00 PM','Location3',50,1,3);
INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade) VALUES (3,'ACertificationExam',4,'A Certification Exam',TO_DATE('2017-04-08','yyyy-mm-dd'),TO_DATE('2017-04-08','yyyy-mm-dd'),'03:00 PM','Location4',150,1,3);
INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade) VALUES (4,'SomeTechnicalTraining',5,'Some Technical Training',TO_DATE('2017-05-08','yyyy-mm-dd'),TO_DATE('2017-05-15','yyyy-mm-dd'),'04:00 PM','Location5',200,2,3);
INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade) VALUES (5,'Other',6,'Some Other Event',TO_DATE('2017-06-08','yyyy-mm-dd'),TO_DATE('2017-06-15','yyyy-mm-dd'),'05:00 PM','Location6',25,2,3);

INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType) VALUES (0,'Merry','Sleeny','Merry.Sleeny@mail.com','owhYxVDIVTV',3);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType) VALUES (1,'Victor','Rathmell','Victor.Rathmell@mail.com','SzDT1sq',3);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor,DepartmentHead) VALUES (2,'Bellanca','Brass','Bellanca.Brass@mail.com','J6j2l6qq8U0',2,0,0);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor,DepartmentHead) VALUES (3,'Judi','Goricke','Judi.Goricke@mail.com','6P0ik8cnKa',2,1,0);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor) VALUES (4,'Abby','Colwill','Abby.Colwill@mail.com','a5N10Vha0egd',4,2);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor) VALUES (5,'Jania','Irwin','Jania.Irwin@mail.com','UkhubWZNA',4,3);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor,DepartmentHead,AvailableReimbursement) VALUES (6,'Drusi','Skegg','Drusi.Skegg@mail.com','C86GqRZB',1,2,0,1000);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor,DepartmentHead,AvailableReimbursement) VALUES (7,'Aldridge','MacCurtain','Aldridge.MacCurtain@mail.com','iTcBGzKjAZ8',1,3,1,1000);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor,DepartmentHead,AvailableReimbursement) VALUES (8,'Alford','Jans','Alford.Jans@mail.com','NeSXE15e2Bg',1,2,0,1000);
INSERT INTO Employees (ID,FirstName,LastName,Email,Password,EmployeeType,DirectSupervisor,DepartmentHead,AvailableReimbursement) VALUES (9,'Lindy','Raywood','Lindy.Raywood@mail.com','ffvWvXZRxlI',1,0,0,1000);


INSERT INTO ManagementApprovals(ID) VALUES (0);
INSERT INTO ManagementApprovals(ID) VALUES (1);
INSERT INTO ManagementApprovals(ID) VALUES (2);
INSERT INTO ManagementApprovals(ID) VALUES (3);
INSERT INTO ManagementApprovals(ID) VALUES (4);
INSERT INTO ManagementApprovals(ID) VALUES (5);


INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,ReimbursementStatus,ManagementApprovals) VALUES (0,6,TO_DATE('2017-01-01','yyyy-mm-dd'),0,'4 Weeks','JUSTIFIED1',80,1,0);
INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,ReimbursementStatus,ManagementApprovals) VALUES (1,7,TO_DATE('2017-02-01','yyyy-mm-dd'),1,'2 Days','JUSTIFIED2',45,1,1);
INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,ReimbursementStatus,ManagementApprovals) VALUES (2,8,TO_DATE('2017-03-01','yyyy-mm-dd'),2,'1 Week','JUSTIFIED3',37.5,1,2);
INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,ReimbursementStatus,ManagementApprovals) VALUES (3,9,TO_DATE('2017-04-01','yyyy-mm-dd'),3,'1 Day','JUSTIFIED4',150,1,3);
INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,ReimbursementStatus,ManagementApprovals) VALUES (4,6,TO_DATE('2017-05-01','yyyy-mm-dd'),4,'2 Weeks','JUSTIFIED5',180,1,4);
INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,ReimbursementStatus,ManagementApprovals) VALUES (5,7,TO_DATE('2017-06-01','yyyy-mm-dd'),5,'4 Days','JUSTIFIED6',7.5,1,5);
