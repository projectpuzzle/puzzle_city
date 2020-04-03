package puzzle_city_client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
// A Java program for a Client 
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;

import puzzle_city_connectionPool.CrudEnum;
import puzzle_city_connectionPool.Test;

public class Client {
	// initialize socket and input output streams
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream out = null;
	private Test testToPersist = new Test();


	// constructor to put ip address and port
	public Client(String address, int port) {
		// establish a connection
		try {
			socket = new Socket(address, port);
			System.out.println("Connected");

			// takes input from terminal
			input = new DataInputStream(System.in);

			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException u) {
			System.out.println(u);
		} catch (IOException i) {
			System.out.println(i);
		}
		showClientId();
		while (this.testToPersist != null) {

			try {
				testToPersist = showMenu();

				String jsonObject = getJsonFromObject(testToPersist);

				out.writeUTF(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// safina chof lmok
			try {
				DataInputStream oos = new DataInputStream(socket.getInputStream());
				String msg = oos.readUTF();
				System.out.println(msg);
			} catch (IOException i) {
				System.out.println(i);
			}
		}

		// close the connection
		try {
			input.close();
			out.close();
			socket.close();
		} catch (IOException i) {
			System.out.println(i);
		}
	}

	private void showClientId() {
		try {
			System.out.println("Please enter id of the client ");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner scanner = new Scanner(System.in);
			String line = scanner.nextLine();
			out.writeUTF(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	String getJsonFromObject(Test test) {
		// Creating Object of ObjectMapper define in Jakson Api
		ObjectMapper Obj = new ObjectMapper();
		try {
			String jsonStr = Obj.writeValueAsString(test);
			return jsonStr;
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Test getTestToPersist() {
		return testToPersist;
	}

	public void setTestToPersist(Test testToPersist) {
		this.testToPersist = testToPersist;
	}

	public Test showMenu() {
		int userChoice = 0;

		/*********************************************************/
		do {
			String selection;
			userChoice = menu();
			String jsonObject;
			int db;
			String client;
			String server;

			Scanner input = new Scanner(System.in);
			switch (userChoice) {
			case 1:
				System.out.println("Press in the console : DB,client,server");

				selection = input.nextLine();
				db = Integer.valueOf(selection.split(",")[0]);
				client = selection.split(",")[1];
				server = selection.split(",")[2];
				return new Test(db, client, server, CrudEnum.SAVE);

			case 2:
				System.out.println("Press in the console : DB,client,server");

				selection = input.nextLine();
				db = Integer.valueOf(selection.split(",")[0]);
				client = selection.split(",")[1];
				server = selection.split(",")[2];
				return new Test(db, client, server, CrudEnum.UPDATE);

			case 3:
				System.out.println("Press in the console : DB");

				selection = input.nextLine();
				db = Integer.valueOf(selection.split(",")[0]);
				return new Test(db, CrudEnum.DELETE);

			case 4:

				return new Test(CrudEnum.FIND_ALL);

			}
		} while (userChoice != 5);
		return null;
	}

	public int menu() {

		int selection;
		Scanner input = new Scanner(System.in);

		/***************************************************/
		System.out.println("---------------------------------------------------");

		System.out.println("            Choose from these choices");
		System.out.println("***************************************************");
		System.out.println(" Press 1 to Add a TEST ");
		System.out.println(" Press 2 to Update a TEST ");
		System.out.println(" Press 3 to Delete a TEST ");
		System.out.println(" Press 4 to Show all TEST ");
		System.out.println(" Press 5 to quit ");
		System.out.println("***************************************************");
		selection = input.nextInt();
		return selection;
	}

	public static void main(String args[]) {

		Client client = new Client("172.31.249.155", 3999);

	}
}
