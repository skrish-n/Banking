
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AppVisitCount
 *
 */
@WebListener
public class visitCount implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public visitCount() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		Connection con = JdbcUtility.getConnection();
		ServletContext context = arg0.getServletContext();
		String userCount = (String) context.getAttribute("userCount");
		
		int count = Integer.parseInt(userCount);
		String query = "update hitcount set count=" + count;
		try {
			Statement st = con.createStatement();
			st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		Connection con = JdbcUtility.getConnection();
		String query = "select count from hitcount";
		ServletContext context = arg0.getServletContext();
		int count = 0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				count = rs.getInt("count");
			}
			String userCount = String.valueOf(count);
			context.setAttribute("userCount", userCount);
			System.out.println("listenerr!!!!!!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
