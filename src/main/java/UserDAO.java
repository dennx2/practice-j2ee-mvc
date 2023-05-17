import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	Connection con;
	
	public UserDAO() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String dburl = "jdbc:mysql://localhost:3306/a4_jaydenn";
			String uname = "root";
			String password = "root";
			
			this.con = DriverManager.getConnection(dburl, uname, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public boolean validateUser(UserModel user) throws Exception {
		
		String selectQuery = "select * from user where username = (?) and password = (?);";
		
		// test use
//		String selectQuery = "select * from user;";
		PreparedStatement pst = con.prepareStatement(selectQuery);
		
		pst.setString(1, user.getUsername());
		pst.setString(2, user.getPassword());
		
		ResultSet rs = pst.executeQuery();
		
		// test use
//		if (rs.next()) {
//			System.out.println(rs.getString(1));
//			System.out.println(rs.getString(2));
//			System.out.println(selectQuery);
//			
//			return true;
//		}
//		else {
//			System.out.println(user.getUsername() + "and" + user.getPassword());
//			return false;
//		}
			
		
		return rs.next();
		
	}
	
	
}
