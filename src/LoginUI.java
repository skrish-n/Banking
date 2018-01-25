
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginUI
 */
@WebServlet("/LoginUI")
public class LoginUI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginUI() {
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
		PrintWriter out = response.getWriter();
		System.out.println("Entering into program");
		String s = "";
		String uname = "";
		String ps = "";
		if (request.getAttribute("usrname") != null)
			uname = (String) request.getAttribute("usrname");
		String html = "<html><head><body>";
		if (request.getAttribute("error") != null) {
			HashMap hm = (HashMap) request.getAttribute("error");
			s = (String) hm.get(1);
			System.out.println("This is s:"+s);
		}
		
		Cookie[] cookieList = request.getCookies();
		if (cookieList!= null) {
			int i = 0;
			while (i < cookieList.length) {
				if (cookieList[i].getName().equalsIgnoreCase("nmCheck"))
					uname = cookieList[i++].getValue();
				if (cookieList[i].getName().equalsIgnoreCase("psCheck"))
					ps = cookieList[i++].getValue();
				i++;
			}
			System.out.println("username cookie:....."+uname);
			System.out.println("password cookie:......."+ps);
		}
		html += "<h1>Bank login</h1>";
		html += "<form action=\"ApplicationC\" method=\"post\">\r\n"
				+ "User Name:<input type=\"text\" name=\"usrname\" value='" + uname + "'></br>\r\n"
				+ "Password:<input type=\"password\" name=\"pass\" value='" + ps + "'></br>\r\n"
				+ "<input type=\"checkbox\" name=\"cookieCheck\" />Remember me.</br>"
				+ "<input type=\"submit\" value=\"Login\"</br>\r\n" + "</form>";
		html += "<h3>" + s + "</h3>";
		html += "</body></head></html>";

		out.print(html);
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
