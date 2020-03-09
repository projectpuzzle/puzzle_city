package puzzle_city_connectionPool;

import java.sql.Connection;

public class DataSource {
	public static JDBCConnectionPool jDBCConnectionPool = new JDBCConnectionPool();

	public static void fill(String driver, String url, String user, String password) {
		try {
			jDBCConnectionPool.fill(driver, url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnectionByUrl(String url) {
		return jDBCConnectionPool.getConnectionByUrl(url);
	}

	public static void replace(Connection c) {
		jDBCConnectionPool.replace(c);
	}

	public static void closeAllConnections() {
		jDBCConnectionPool.closeAllConnections();
	}

	public void showTest() {
		System.out.println(jDBCConnectionPool.showTest());
	}

	public void addTest(String Client, String server,int DB) {
		Test t = new Test(Client, server, DB);
		jDBCConnectionPool.addTest(t);
		showTest();
	}

	public void deleteTestByserver(String Client) {
		jDBCConnectionPool.deleteTestByserver(Client);
		showTest();
	}

	public void updateTest(String Client, String server,int DB) {
		jDBCConnectionPool.updateTest(Client,server,DB);
		showTest();
	}
}
