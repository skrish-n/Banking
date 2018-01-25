

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Transaction
 */
@WebServlet("/Transaction")
public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accno = request.getParameter("accno");
		String amount = request.getParameter("amt");
	//	String username = (String) request.getAttribute("usrname");
		UserDao obj = new UserDao();
		obj.setAccno(accno);
		obj.setAmount(amount);
		obj.setName("sai");
		
		HashMap hm = (HashMap) obj.isValidTransfer();

		String html = "<html><head><body><h1>TRANSACTION RECIEPT</h1></br></br>";
		if (hm.containsKey("error")) {

			html += "<h2>" + hm.get("error") + "</h2>";
		} 
		
		else {
			html += "<h2>Transaction successfully completed</h2></br></br>";
			html += "<h4>Reciepint name:" + hm.get("name") + "</h4></br>";
			html += "<h4>Reciepient phone:" + hm.get("phone") + "</h4></br>";
			html += "<h4>Amount transfered:" + amount + "</h4></br>";
		}
		html += "</body></head></html>";
		PrintWriter out = response.getWriter();
		out.print(html);

	}
	
}


