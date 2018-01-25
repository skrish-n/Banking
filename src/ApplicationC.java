
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ApplicationC
 */
@WebServlet("/ApplicationC")
public class ApplicationC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplicationC() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses = request.getSession(true);
		String nm = request.getParameter("usrname");
		String pass = request.getParameter("pass");
		String cookieCheck = request.getParameter("cookieCheck");
		System.out.println("name in AC:" + nm);
		System.out.println("session obj name:" + ses.getAttribute("username"));
		UserDao obj = new UserDao();
		obj.setName(nm);
		obj.setPass(pass);
		HashMap hm = (HashMap) obj.isValid();
		// request.setAttribute("usrname", nm);
		// request.setAttribute("map", hm);
		ses.setAttribute("map", hm);
		ses.setAttribute("username", nm);
		ses.setAttribute("sescount",0 );
		if (hm.containsKey("role")) {
			if (cookieCheck!=null) {
				
				// String nmCheck = "nmCheck";
				Cookie uname = new Cookie("nmCheck", nm);
				uname.setMaxAge(60 * 60 * 24);
				// String psCheck = "psCheck";
				Cookie ps = new Cookie("psCheck", pass);
				ps.setMaxAge(60 * 60 * 24);
				response.addCookie(uname);
				response.addCookie(ps);
			}
		response.sendRedirect("HomeUI");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("LoginUI");
			rd.forward(request, response);
		}
	}
}
