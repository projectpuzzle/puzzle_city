package puzzle_city_connectionPool;

import java.sql.Connection;
import java.util.ArrayList;

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

	public  void showTest() {
		// ArrayList<Test> list =jDBCConnectionPool.showTest();

		for (Test test : jDBCConnectionPool.showTest()) {
			System.out.println(test.toString());
		}

		// System.out.println(jDBCConnectionPool.showTest());
	}

	public static ArrayList<Test> findAll() {
		// ArrayList<Test> list =jDBCConnectionPool.showTest();
		ArrayList<Test> list = new ArrayList<>();
		for (Test test : jDBCConnectionPool.showTest()) {
			list.add(test);
		}
		return list;
		// System.out.println(jDBCConnectionPool.showTest());
	}

	public static void addTest(String Client, String server, int DB) throws ClassNotFoundException {
		Test t = new Test(Client, server, DB);
		jDBCConnectionPool.addTest(t);
		//showTest();
	}

	public static void deleteTestByDB(int DB) {
		jDBCConnectionPool.deleteTestByDB(DB);
		//showTest();
	}

	public static void updateTest(String Client, String server, int DB) {
		jDBCConnectionPool.updateTest(Client, server, DB);
		//showTest();
	}
}
