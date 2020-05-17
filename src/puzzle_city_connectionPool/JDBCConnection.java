package puzzle_city_connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnectionPool {
	Collection<Connection> connections = new ArrayList<Connection>();
	ConnectionFileReader connectionFileReader = new ConnectionFileReader();

	public void fill(String driver, String url, String user, String password) throws ClassNotFoundException {

		try {

			Class.forName(connectionFileReader.getDriver());
			Connection con = DriverManager.getConnection(connectionFileReader.getUrl(), connectionFileReader.getUser(),
					connectionFileReader.getPassword());
			connections.add(con);

		} catch (SQLException ex) {
			Logger.getLogger(JDBCConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public Connection getConnectionByUrl(String url) {
		for (Connection con : connections) {
			String url2;
			try {
				url2 = con.getMetaData().getURL();
				if (url2.equals(url)) {
					return con;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void replace(Connection c) {
		for (Connection c1 : connections) {
			if (c1 == c)
				return;
		}
		connections.add(c);
	}

// test //
	public void closeAllConnections() {
		for (Connection c : connections) {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Test> showTest() {

		ArrayList<Test> retour = new ArrayList<Test>();

		try {

			Connection con = DriverManager.getConnection(connectionFileReader.getUrl(), connectionFileReader.getUser(),
					connectionFileReader.getPassword());
			System.out.println("Connection established");
			PreparedStatement pt = con.prepareStatement("select * from Test");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				int DB = rs.getInt(3);
				String Client = rs.getString(1);
				String server = rs.getString(2);
				retour.add(new Test(Client, server, DB));
			}
		} catch (SQLException ex) {
			System.out.println("error " + ex.getMessage());

		}
		return retour;
	}

	public void deleteTestByDB(int DB) {
		try {
			Connection con = DriverManager.getConnection(connectionFileReader.getUrl(), connectionFileReader.getUser(),
					connectionFileReader.getPassword());
			System.out.println("Connection established");

			PreparedStatement pt = con.prepareStatement("delete from Test where DB like ?");
			pt.setInt(1, DB);
			pt.execute();
		} catch (SQLException ex) {
			System.out.println("error " + ex.getMessage());
		}

	}

	public void addTest(Test t) throws ClassNotFoundException {
		try {


			Connection con = DriverManager.getConnection(connectionFileReader.getUrl(), connectionFileReader.getUser(),
					connectionFileReader.getPassword());
			System.out.println("Connection established");

		
			PreparedStatement pstm = con.prepareStatement( "insert into Test(client,server,DB) values (?,?,?)");
			pstm.setString(1, t.getClient());
			pstm.setString(2, t.getServer());
			pstm.setInt(3, t.getDB());
			pstm.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void updateTest(String Client, String server, int DB) {
		try {
			Connection con = DriverManager.getConnection(connectionFileReader.getUrl(), connectionFileReader.getUser(),
					connectionFileReader.getPassword());
			System.out.println("Connection established");

			PreparedStatement pstm = con.prepareStatement(" UPDATE Test SET Client = ? , server = ? WHERE DB = ? ");
			pstm.setString(1, Client);
			pstm.setString(2, server);
			pstm.setInt(3, DB);
			pstm.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("error " + ex.getMessage());
		}
	}
}