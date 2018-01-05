package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoObjects.Employee;
import daoObjects.Event;
import daos.EventDao;
import utils.HtmlPageCreator;

public class ViewEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewEventServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("employee");
		if (emp == null || emp.getType() == null) {
			response.sendRedirect("/trms/LoginServlet");
			return;
		}

		String requestUri = request.getRequestURI();
		System.out.println("RequestURI: " + requestUri);
		String requestUrl = requestUri.substring(request.getContextPath().length());
		System.out.println("RequestURL: " + requestUrl);
		String eventId = requestUrl.substring(requestUrl.indexOf('/', 2) + 1);
		System.out.println(eventId);

		HtmlPageCreator hpc = new HtmlPageCreator();
		String htmlPage = null;

		htmlPage = hpc.createEventDetails(emp, Integer.parseInt(eventId));

		response.getWriter().write(htmlPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("employee");
		if (emp == null || emp.getType() == null) {
			response.sendRedirect("/trms/LoginServlet");
			return;
		}
		
		String requestUri = request.getRequestURI();
		System.out.println("RequestURI: " + requestUri);
		String requestUrl = requestUri.substring(request.getContextPath().length());
		System.out.println("RequestURL: " + requestUrl);
		String eventId = requestUrl.substring(requestUrl.indexOf('/', 2) + 1);
		System.out.println(eventId);
		
		EventDao eventDao = new EventDao();
		Event event = eventDao.getById(Integer.parseInt(eventId));
		event.setRecievedGrade(Double.parseDouble(request.getParameter("submitgrade")));
		eventDao.update(event);
		
	}
}
