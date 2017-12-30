import java.util.List;

import daoObjects.Employee;
import daoObjects.Event;
import daoObjects.EventType;
import daos.EmployeeDao;
import daos.EventDao;

public class ManualTest {

	public static void main(String[] args) {
		//testEmployeeDao();
		//testEventDao();
		testReimbursementDao();
	}
	
	private static void testReimbursementDao() {
		
	}

	private static void testEventDao() {
		EventDao eventDao = new EventDao();
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

//		Event newEvent = new Event(96, "NEW_EVENT", EventType.OTHER, "IGNORE ME", new Date(System.currentTimeMillis()),new  Date(System.currentTimeMillis()), "NEVER",
//				"NOWHERE", 0.0, GradingFormat.LETTER_GRADE, GradeLetter.F, 0.0);
//		eventDao.add(newEvent);
//		Event getNewEvent = eventDao.getById(newEvent.getId());
//		System.out.println(getNewEvent);
	}

	private static void testEmployeeDao() {
		EmployeeDao employeeDao = new EmployeeDao();
		Employee codeMonkee = employeeDao.getById(5);

		System.out.println(codeMonkee.toString());
		System.out.println(employeeDao.getEmployeeWithLogin(codeMonkee.getEmail(), "password"));
		System.out.println(employeeDao.getDirectSupervisorForEmployee(codeMonkee));
		System.out.println(employeeDao.getDirectSupervisorForEmployeeId(codeMonkee.getId()));
		System.out.println();
		codeMonkee.setAvailableReimbursement(1000);
		employeeDao.update(codeMonkee);
		codeMonkee = employeeDao.getById(5);
		System.out.println(codeMonkee.toString());

	}
}
