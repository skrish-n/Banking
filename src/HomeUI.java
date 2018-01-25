
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomeUI
 */
@WebServlet("/HomeUI")
public class HomeUI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeUI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// TODO Auto-generated method stub
		String name = "";
		String role = "";
		int userCount = 0;
		System.out.println("home ui");
		ServletContext context = getServletContext();
		String temp = (String) context.getAttribute("userCount");
     System.out.println("temp="+temp);
		if (temp != null) {
			String count = (String) context.getAttribute("userCount");

			userCount = Integer.parseInt(count);
			++userCount;
			count = String.valueOf(userCount);
			System.out.println("usercount:" + userCount);
			context.setAttribute("userCount", count);
		} else {
			context.setAttribute("userCount", "1");
			userCount = 1;
		}
		/*
		 * if (request.getAttribute("usrname") != null) { name = (String)
		 * request.getAttribute("usrname");
		 * System.out.println("username recieved!!!!!!!!!!"); }
		 */
		HttpSession ses = request.getSession(false);
		if (ses != null) {
			name = (String) ses.getAttribute("username");
			// count=(int) ses.getAttribute("sescount");
			// count++;
			// .setAttribute("sescount",count);
			/*
			 * if (request.getAttribute("map") != null) { HashMap hm = (HashMap)
			 * request.getAttribute("map"); role = (String) hm.get("role");
			 * System.out.println("role recieved!!!!!!!!!");
			 * System.out.println("role:"+role); }
			 */
			if (ses.getAttribute("map") != null) {
				HashMap hm = (HashMap) ses.getAttribute("map");
				role = (String) hm.get("role");
			}
			if (role.equals("user")) {
				String change = "<a href=\"ChangePasswordUI\">Change Password</a>";
				String logOut = "<a href=\"Logout\">Logout</a>";
				// request.setAttribute("user", name);
				String html = "<html><head><body>";
				html += "<h1>User:" + name + "Role:" + role + " </br> Welcome to the home page</br></br>";
				html += "<h2><u>Transaction</u></h2>\r\n" + "<form action=\"Transaction\" method=\"post\">\r\n"
						+ "Account Number:<input type=\"text\" name=\"accno\"></br>\r\n"
						+ "Amount:<input type=\"text\" name=\"amt\"></br>\r\n"
						+ "<input type=\"submit\" value=\"submit\"</br></form>";
				html += change;
				html += "</br>" + logOut + "</br>";
				html += "Number of users logged in:" + userCount;
				html += "</body></head></html>";
				out.print(html);

			}
		} else {
			System.out.println("redirecting from homeUi");
			response.sendRedirect("LoginUI");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
