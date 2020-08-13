import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDB {
	Connection conn = null;
	Statement statement = null;

	public SQLiteDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:JrCodingChallengeMS3.sqlite"); //open connection here
			System.out.println("Connected to database.");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void createTable() throws SQLException {
		statement = conn.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS UserInfo " + "(first_name TEXT NOT NULL," + "last_name TEXT NOT NULL,"
				+ "email TEXT NOT NULL," + "gender TEXT NOT NULL," + "image TEXT NOT NULL," + "bank TEXT NOT NULL,"
				+ "trans TEXT NOT NULL," + "bool1 TEXT NOT NULL," + "bool2 TEXT NOT NULL," + "city TEXT NOT NULL)";

		statement.executeUpdate(sql);
		statement.close();
		System.out.println("Table created successfully");
	}

	public void insertData(User user) throws SQLException {
		conn.setAutoCommit(true);
		statement = conn.createStatement();
		String first_name = user.firstName;
		String last_name = user.lastName;
		String email = user.email;
		String gender = user.gender;
		String image = user.image;
		String bank = user.bank;
		String trans = user.transaction;
		String bool1 = user.bool1;
		String bool2 = user.bool2;
		String city = user.city;

		String sql1 = "INSERT INTO UserInfo (first_name,last_name,email,gender,image,bank,trans,bool1,bool2,city)"
				+ "VALUES ('" + first_name + "','" + last_name + "','" + email + "','" + gender + "','" + image + "','"
				+ bank + "','" + trans + "','" + bool1 + "','" + bool2 + "','" + city + "');";

		statement.executeUpdate(sql1);
		statement.close();
	}

	public void last() {
		try {
			statement = conn.createStatement();
			String sql2 = "SELECT COUNT(*) AS Total FROM UserInfo";
			ResultSet rs = statement.executeQuery(sql2);
			while (rs.next()) {

				int count = rs.getInt(1);
				System.out.println(count); //
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

//

//method that could close a connection