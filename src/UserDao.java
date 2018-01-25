import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;



public class UserDao {
	String name;
	String pass;
	String accno;
	String amount;

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public HashMap isValid() {
		HashMap hm = new HashMap();
		Connection con = JdbcUtility.getConnection(); 
		Statement st = null;
		String query;
		query = "select * from banking where name='" + name + "' and password='" + pass + "'";
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			// System.out.println("hhhhhhhhhhhhhhhhhh");
			
			if (rs.next()) {
				// System.out.println("hereeeeeeeee");
				String role=rs.getString("role");
				hm.put("role",role);
			}
			else{
				hm.put("error", "The login credentials are invalid");
				return hm;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtility.close(con, st);
		}
		// System.out.println("eeeeeeeeeeeeeeeeeeeee");
		return hm;

	}

	public HashMap isValidTransfer() {
		HashMap hm = new HashMap();
		Connection con = JdbcUtility.getConnection();
		Statement st = null;
		String query, query1, tempQuery, query2;
		int bal = 0;                                  // Used to store the balance of both the customers
		int userAcno = 0;            // Account number of user
		String nm = "";                               // name of the recipient
		double ph = 0;                   // phone number of recipient
		int ac = Integer.parseInt(accno);        // Account number received
		tempQuery = "select * from account_details where name='"+ name +"'";    //query to obtain the balance and account number of the user
		System.out.println("********************NAme:"+name);
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(tempQuery);

			while (rs.next()) {
				bal = rs.getInt("balance");
				userAcno = rs.getInt("account_number");
			}
			

			int temp = bal - Integer.parseInt(amount); // Amount to be deducted from user
														 
			if (temp <= 0) {
				hm.put("error", "Insuffecient balance");
				return hm;
			} 
			else {
				query = "select * from account_details where account_number=" + ac;   //query to obtain the details of recipient
                System.out.println("account number"+"select * from account_details where account_number=" + ac);
				rs = st.executeQuery(query);

				if (rs.next()) {
					System.out.println("---------------------");
					ph = rs.getDouble("phone_number");
					nm = rs.getString("name");
					bal = rs.getInt("balance");
					System.out.println("phone:"+ph+"name:"+nm+"balance:"+bal);
				} 
				else {
					hm.put("error", "Invalid account number");
					return hm;
				}
                con.setAutoCommit(false);
				//query2 = "update account_details set balance=" + temp + " where account_number=" + userAcno; 
				query2 = "update account_details set balance=? where account_number= ?";
				PreparedStatement ps=con.prepareStatement(query2);
				ps.setInt(1,temp);
				ps.setInt(2,userAcno);
				ps.addBatch();
				temp = Integer.parseInt(amount) + bal;
				//query1 = "update account_details set balance=" + temp + " where account_number=" + ac;       //updating recipient database
				ps.setInt(1,temp);
				ps.setInt(2,ac);
				ps.addBatch();
				ps.executeBatch();
				con.commit();
				hm.put("name", nm);                      //adding name and 
				hm.put("phone", ph); 					//phone number
				// tempQuery="select * from account_details where
				// name='"+name+"'";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hm;
	}
}
