

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// request.getSession(false) WON'T create a new session if DNE
		// request.getSession(true) WILL create a new session if DNE
		// equivalent to request.getSession();
		HttpSession session = request.getSession(true);
		
		String username = (String) session.getAttribute("username");
		
		if(username == null) {
			response.sendRedirect("/index.html");
		}
		
		response.getWriter().append(username);
	}

}
