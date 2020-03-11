package puzzle_city_connectionPool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public final class ConnectionFileReader {

	private String driver;
	private String url;
	private String user;
	private String password;
	private static ConnectionFileReader instance = null;

	public ConnectionFileReader(String driver, String url, String user, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;

	}

	public ConnectionFileReader() {
		Properties p = new Properties();
		try {
			FileInputStream f = new FileInputStream(
					"C:\\Users\\etudiant\\Documents\\workspace-spring-tool-suite-4-4.4.0.RELEASE\\puzzlecity_project\\src\\puzzle_city_connectionPool\\ConnectionFile.xml");
			p.loadFromXML(f);

			this.url = (String) p.get("jdbc.url")
					+ "?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
			this.driver = (String) p.get("jdbc.driver");
			this.user = (String) p.get("jdbc.user");
			this.password = (String) p.get("jdbc.password");

			System.out.println("url : " + this.url);
			System.out.println("driver : " + this.driver);
			System.out.println("user : " + this.user);
			System.out.println(this.password);

			System.out.println("waiting for the connection to be established");
		}

		catch (Exception e) {
			;
		}
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}