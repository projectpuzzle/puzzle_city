package puzzle_city_client;

// A Java program for a Client 
import java.net.*;

import org.codehaus.jackson.map.ObjectMapper;

import puzzle_city_connectionPool.Test;

import java.io.*;

public class Client {
	// initialize socket and input output streams
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream out = null;

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

		// string to read message from input
		String line = "";

		// keep reading until "Over" is input
		while (!line.equals("Over")) {
			try {
				line = input.readLine();
				String jsonObject = getJsonFromObject(line);
				out.writeUTF(jsonObject);
				
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

	private String getJsonFromObject(String line) {
		// Creating object of Organisation

				int db = Integer.valueOf(line.split(",")[0]);
				String client = line.split(",")[1];
				String server = line.split(",")[2];
				Test org = new Test(server, client, db);

				// Insert the data into the object

				// Creating Object of ObjectMapper define in Jakson Api
				ObjectMapper Obj = new ObjectMapper();

				try {

					// get Oraganisation object as a json string
					String jsonStr = Obj.writeValueAsString(org);

					// Displaying JSON String
					return jsonStr;
				}

				catch (IOException e) {
					e.printStackTrace();
				}
				return null;
		
	}

//	public static void main(String args[]) {
//		Client client = new Client("127.0.0.1", 4000);
//	}
}
