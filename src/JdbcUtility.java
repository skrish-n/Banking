import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtility {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("Driver not loaded");
		}
	}

	public static Connection getConnection() {

		Connection con = null;
		try {
			String url = "jdbc:oracle:thin:@//localhost:1521/XE";
			String usrid = "SKRISH";
			String pass = "saikrishnan";
			con = DriverManager.getConnection(url, usrid, pass);
			System.out.println("ttttttttttttttttttttttttt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void close(Connection con, Statement st) {
		try {
			if (con != null) {
				con.close();
			}
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
