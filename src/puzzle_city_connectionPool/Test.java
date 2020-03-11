package puzzle_city_connectionPool;

public class Test {
	private int DB;
	private String Client;
	private String server;

	public Test(String server, String client, int dB) {
		super();
		this.DB = dB;
		this.Client = client;
		this.server = server;
	}

	public int getDB() {
		return DB;
	}

	public void setDB(int dB) {
		DB = dB;
	}

	public String getClient() {
		return Client;
	}

	public void setClient(String client) {
		Client = client;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	@Override
	public String toString() {
		return "Test [ Client = " + Client + " " + ", Server = " + server + ", Database = " + DB + " ]  \n ";
	}

}
