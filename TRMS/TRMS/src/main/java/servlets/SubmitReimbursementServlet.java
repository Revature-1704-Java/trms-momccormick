package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoObjects.Employee;
import daoObjects.EmployeeType;
import daoObjects.Event;
import daoObjects.EventType;
import daoObjects.GradeLetter;
import daoObjects.GradingFormat;
import daoObjects.Reimbursement;
import daoObjects.ReimbursementStatus;
import daos.EventDao;
import daos.ReimbursementDao;
import utils.HtmlPageCreator;

public class SubmitReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SubmitReimbursementServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee emp = (Employee) request.getSession().getAttribute("employee");
		if (emp == null || emp.getType() != EmployeeType.STANDARD) {
			response.sendRedirect("/trms/LoginServlet");
			return;
		}

		HtmlPageCreator hpc = new HtmlPageCreator();
		String htmlPage = null;

		htmlPage = hpc.createReimbursementSubmission(emp);

		response.getWriter().write(htmlPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("employee");
		if (emp == null || emp.getType() != EmployeeType.STANDARD) {
			response.sendRedirect("/trms/LoginServlet");
			return;
		}

		Event event = new Event(0);
		event.setName(request.getParameter("eventname"));
		event.setEventType(EventType.getById(Integer.parseInt(request.getParameter("eventtype"))));
		event.setDescription(request.getParameter("description"));

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
		try {
			event.setStartDate(new Date(sdf1.parse(request.getParameter("startdate")).getTime()));
			event.setEndDate(new Date(sdf1.parse(request.getParameter("enddate")).getTime()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		event.setTime(request.getParameter("time"));
		event.setLocation(request.getParameter("location"));
		event.setCost(Double.parseDouble(request.getParameter("cost")));
		event.setGradingFormat(GradingFormat.getById(Integer.parseInt(request.getParameter("gradingformats"))));
		event.setPassingGrade(GradeLetter.getById(Integer.parseInt(request.getParameter("passinggrade"))));
		
		EventDao eventDao = new EventDao();
		eventDao.add(event);
		event = eventDao.persist(event);

		Reimbursement reimbursement = new Reimbursement(0);
		reimbursement.setEmployee(emp);
		reimbursement.setEvent(event);
		reimbursement.setWorkTimeMissed(request.getParameter("timemissed"));
		reimbursement.setJustification(request.getParameter("justification"));
		reimbursement.setDateSubmitted(new Date(System.currentTimeMillis()));
		reimbursement.setProjectedAmount(event.getCost()*event.getEventType().getPercentCovered());
		
		if(event.getEndDate().getTime() - event.getStartDate().getTime() < 7) {
			reimbursement.setReimbursementStatus(ReimbursementStatus.URGENT);
		}
		else {
			reimbursement.setReimbursementStatus(ReimbursementStatus.PENDING);
		}
		
		
		ReimbursementDao reimbursementDao = new ReimbursementDao();
		reimbursementDao.add(reimbursement);

		response.sendRedirect("/trms/HomeServlet");
	}
}
