import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

public class DatabaseAPI {

	private final String SERVER = "jdbc:mysql://140.119.19.73:9306/";
	private final String USER = "TG07";
	private final String PASSWORD = "mw6df7";
	private final String CONFIG = "?useUnicode=true&characterEncoding=utf8";
	private final String DATABASE = "TG07";
	private Connection conn = null;

	public DatabaseAPI() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(SERVER, USER, PASSWORD);
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("No suitable driver found");
		} catch (SQLException e) {
			e.getMessage();
		}

	}

	public ResultSet getResult(String sql) throws SQLException {
		Connection conn = null;
		Statement stat = null;
		try {
			conn = DriverManager.getConnection(SERVER + DATABASE + CONFIG, USER, PASSWORD);
			stat = conn.createStatement();
			stat.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		} finally {
			conn.close();
		}
		return stat.getResultSet();
	}

	public int getUserID(String name) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(SERVER + DATABASE + CONFIG, USER, PASSWORD);
			PreparedStatement ps = conn.prepareStatement("SELECT ID_Number FROM ID_User WHERE User_Name=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (!rs.equals(null)) {
				rs.next();
				return rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		} finally {
			conn.close();
		}
		return -1;
	}

	public String getUserInfo(int id, String columnLabel) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		String result = "";
		try {
			conn = DriverManager.getConnection(SERVER + DATABASE + CONFIG, USER, PASSWORD);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Personal_Info WHERE ID_Number=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.equals(null)) {
				while (rs.next()) {
					switch(columnLabel) {
					case "Age":
						result = rs.getInt(columnLabel) + "";
						break;
					case "ID_Number":
						result = rs.getInt(columnLabel) + "";
						break;
					case "Height":
						result = rs.getDouble(columnLabel) + "";
						break;
					case "Weight":
						result = rs.getDouble(columnLabel) + "";
						break;
					case "Sex":
						result = rs.getString(columnLabel) + "";
						break;
					case "Date":
						result = rs.getDate(columnLabel) + "";
						break;
				}
			}
			return result;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		} finally {
			conn.close();
		}
		return null;
	}

	public String[] getColName(String sql) throws SQLException {
		String[] ary = null;
		try {
			conn = DriverManager.getConnection(SERVER + DATABASE + CONFIG, USER, PASSWORD);
			Statement stat = conn.createStatement();
			stat.executeQuery(sql);
			ResultSet result = stat.getResultSet();
			ResultSetMetaData metaData = result.getMetaData();
			int columnCount = metaData.getColumnCount();
			ary = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				ary[i - 1] = metaData.getColumnLabel(i);
			}
			result.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		} finally {
			conn.close();
		}
		return ary;
	}

	public Object[][] getData(String sql) throws SQLException {
		Object[][] ary = null;
		try {
			conn = DriverManager.getConnection(SERVER + DATABASE + CONFIG, USER, PASSWORD);
			Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stat.executeQuery(sql);
			ResultSet result = stat.getResultSet();
			ResultSetMetaData metaData = result.getMetaData();

			int columnCount = metaData.getColumnCount();
			result.last();
			int rowCount = result.getRow();
			result.beforeFirst();

			ary = new Object[rowCount][columnCount];

			int j = 0;
			while (result.next()) {
				for (int i = 1; i <= columnCount; i++) {
					ary[j][i - 1] = result.getString(i);
				}
				j++;
			}

			result.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		} finally {
			conn.close();
		}
		return ary;
	}

	/** Update the body info. of the user */
	public void updateInfo(String sex, int user, Date date, double height, double weight, int age) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(SERVER + DATABASE + CONFIG, USER, PASSWORD);
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO Personal_Info(ID_Number, Date, Height, Weight, Sex, Age)values(?, ?, ?, ?, ?, ?)");
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			ps.setInt(1, user);
			ps.setDate(2, sqlDate);
			ps.setDouble(3, height);
			ps.setDouble(4, weight);
			ps.setString(5, sex);
			ps.setInt(6, age);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		} finally {
			conn.close();
		}
	}

	/** Update the daily record of the user */
	public void updateDailyRecord(int user, Date date, float protein, float carbohydrate, float fat,
			double calorieBurned) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(SERVER + DATABASE + CONFIG, USER, PASSWORD);
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO Daily_Record(ID_Number, Date, Protein, Carbohydrate, Fat, Calorie_burned)values(?, ?, ?, ?, ?, ?)");
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			ps.setInt(1, user);
			ps.setDate(2, sqlDate);
			ps.setFloat(3, protein);
			ps.setFloat(4, carbohydrate);
			ps.setFloat(5, fat);
			ps.setDouble(6, calorieBurned);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		} finally {
			conn.close();
		}
	}

	public boolean signupUser(String name) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(SERVER + DATABASE + CONFIG, USER, PASSWORD);
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM ID_User");
			while (rs.next()) {
				if (rs.getString(2).equals(name)) {
					JOptionPane.showMessageDialog(null, "您已註冊過。", "警告", JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}
			PreparedStatement ps2 = conn.prepareStatement("INSERT INTO ID_User(User_Name)values(?)");
			ps2.setString(1, name);
			ps2.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		} finally {
			conn.close();
		}
		return true;
	}

	public String getName(String id) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(SERVER + DATABASE + CONFIG, USER, PASSWORD);
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM ID_User");
			while (rs.next()) {
				if (rs.getInt("ID_Number") == (Integer.parseInt(id))) {
					JOptionPane.showMessageDialog(null, "您已註冊過。", "警告", JOptionPane.WARNING_MESSAGE);
					return rs.getString("User_Name");
				}
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			return null;
		} finally {
			conn.close();
		}
	}
}
