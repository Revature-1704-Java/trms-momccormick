package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoObjects.Employee;
import daoObjects.Reimbursement;
import daos.ReimbursementDao;
import utils.AjaxStrings;
import utils.SessionAttribute;

public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuilder sb = new StringBuilder();
		
		String action = request.getParameter(AjaxStrings.ACTION);
		
		if (action.equals(AjaxStrings.REIMBURSEMENTS_FOR_EMPLOYEE)) {
			Employee emp = (Employee) request.getSession().getAttribute(SessionAttribute.EMPLOYEE);
			
			if(emp == null) {
				return;
			}
			
			ReimbursementDao dao = new ReimbursementDao();
			List<Reimbursement> reimbursements = dao.getAllForEmployee(emp);
			
			sb.append("<reimbursements>");
			for (Reimbursement rei : reimbursements) {
				sb.append("<reimbursement>");
				sb.append("<date_submitted>" + rei.getDateSubmitted() + "</date_submitted>");
				sb.append("<work_time_missed>" + rei.getWorkTimeMissed() + "</work_time_missed>");
				sb.append("<justification>" + rei.getJustification() + "</justification>");
				sb.append("<projected_amount>" + rei.getProjectedAmount() + "</projected_amount>");
				sb.append("<reimbursement_status>" + rei.getReimbursementStatus() + "</reimbursement_status>");
				sb.append("<amount_awarded>" + rei.getAmountAwarded() + "</amount_awarded>");
				sb.append("</reimbursement>");
			}
			sb.append("</reimbursements>");
		}
		
		response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(sb.toString());
	}

}
