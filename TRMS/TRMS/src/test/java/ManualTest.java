import java.sql.Date;
import java.util.List;

import daoObjects.Employee;
import daoObjects.Event;
import daoObjects.EventType;
import daoObjects.GradeLetter;
import daoObjects.GradingFormat;
import daoObjects.Reimbursement;
import daoObjects.ReimbursementStatus;
import daos.EmployeeDao;
import daos.EventDao;
import daos.ReimbursementDao;

public class ManualTest {

	static EmployeeDao employeeDao = new EmployeeDao();
	static EventDao eventDao = new EventDao();
	static ReimbursementDao reimbursementDao = new ReimbursementDao();

	static Event dummyEvent;
	static Employee dummyEmployee;

	public static void main(String[] args) {
		dummyEvent = new Event(99, "NEW_EVENT", EventType.OTHER, "IGNORE ME", new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()), "NEVER", "NOWHERE", 0.0, GradingFormat.LETTER_GRADE,
				GradeLetter.F, 0.0);
		dummyEmployee = employeeDao.getById(5);

		// testEmployeeDao();
		// testEventDao();
		// testReimbursementDao();
	}

	private static void testReimbursementDao() {
		Reimbursement reimbursement = reimbursementDao.getById(0);

		System.out.println(reimbursement);
		System.out.println();

		List<Reimbursement> allReimbursements = reimbursementDao.getAllForEmployeeId(5);
		for (Reimbursement r : allReimbursements) {
			System.out.println(r.toString());
		}
		System.out.println();

		Employee departmentHead = employeeDao.getById(0);
		List<Reimbursement> subordinateReimbursements = reimbursementDao.getAllForSubordinatesOf(departmentHead);
		for (Reimbursement r : subordinateReimbursements) {
			System.out.println(r.toString());
		}
		System.out.println();

		Employee benefitsCoordinator = employeeDao.getById(4);
		List<Reimbursement> assignedReimbursements = reimbursementDao
				.getAllWithAssignedBenefitsCoordinator(benefitsCoordinator);
		for (Reimbursement r : assignedReimbursements) {
			System.out.println(r.toString());
		}
		System.out.println();

		List<Reimbursement> pendingReimbursements = reimbursementDao
				.getAllWithReimbursementStatus(ReimbursementStatus.PENDING);
		for (Reimbursement r : pendingReimbursements) {
			System.out.println(r.toString());
		}
		System.out.println();

		eventDao.add(dummyEvent);
		Reimbursement newReimbursement = new Reimbursement(99, dummyEmployee, dummyEvent, "ALL THE TIME",
				"Just Be Cause");
		System.out.println(newReimbursement);
		reimbursementDao.add(newReimbursement);
		Reimbursement addedReimbursement = reimbursementDao.getById(99);
		System.out.println(addedReimbursement);

	}

	private static void testEventDao() {
		Event universityCourse = eventDao.getById(0);

		System.out.println(universityCourse);
		universityCourse.setDescription("A_UNIVERSITY_COURSE");
		eventDao.update(universityCourse);
		Event updatedEvent = eventDao.getById(0);
		System.out.println(updatedEvent);

		System.out.println();

		List<Event> allEvents = eventDao.getAll();
		for (Event event : allEvents) {
			System.out.println(event);
		}
		System.out.println();

		List<Event> seminars = eventDao.getAllOfType(EventType.SEMINAR);
		for (Event event : seminars) {
			System.out.println(event);
		}

		// Event newEvent = new Event(96, "NEW_EVENT", EventType.OTHER, "IGNORE ME", new
		// Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),
		// "NEVER",
		// "NOWHERE", 0.0, GradingFormat.LETTER_GRADE, GradeLetter.F, 0.0);
		// eventDao.add(newEvent);
		// Event getNewEvent = eventDao.getById(newEvent.getId());
		// System.out.println(getNewEvent);
	}

	private static void testEmployeeDao() {

		System.out.println(dummyEmployee.toString());
		System.out.println(employeeDao.getEmployeeWithLogin(dummyEmployee.getEmail(), "password"));
		System.out.println(employeeDao.getDirectSupervisorForEmployee(dummyEmployee));
		System.out.println(employeeDao.getDirectSupervisorForEmployeeId(dummyEmployee.getId()));
		System.out.println();
		dummyEmployee.setAvailableReimbursement(1000);
		employeeDao.update(dummyEmployee);
		dummyEmployee = employeeDao.getById(5);
		System.out.println(dummyEmployee.toString());

	}
}
