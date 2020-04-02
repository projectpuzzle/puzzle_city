package puzzle_city_connectionPool;


public class Test {
	private int DB;
	private String Client;
	private String server;
	private CrudEnum crud;

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Test(CrudEnum crud) {
		super();
		this.crud = crud;
	}

	public Test(int dB, CrudEnum crud) {
		super();
		DB = dB;
		this.crud = crud;
	}

	public Test(String server, String client, int dB) {
		super();
		this.DB = dB;
		this.Client = client;
		this.server = server;
	}
	

	public Test(int dB, String client, String server, CrudEnum crud) {
		super();
		DB = dB;
		Client = client;
		this.server = server;
		this.crud = crud;
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

	public CrudEnum getCrud() {
		return crud;
	}

	public void setCrud(CrudEnum crud) {
		this.crud = crud;
	}

	@Override
	public String toString() {
		return "Test [DB=" + DB + ", Client=" + Client + ", server=" + server + ", crud=" + crud + "]";
	}

	

}
