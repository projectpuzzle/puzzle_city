package puzzle_city_server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import puzzle_city_connectionPool.DataSource;
import puzzle_city_connectionPool.Test;

public class Server extends Thread {
	// initialize socket and input stream
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in = null;
	private ObjectOutputStream output;
	DataSource dataSource;

	// constructor with port
	public Server(int port) {
		// starts server and waits for a connection
		try {
			dataSource = new DataSource();
			server = new ServerSocket(port);
			System.out.println("Server started on port " + port);

			System.out.println("Waiting for a client ...");

			socket = server.accept();
			System.out.println("Client accepted");

			// takes input from the client socket
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			String line = "";

			// reads message from client until "Over" is sent
			while (!line.equals("0")) {
				ObjectMapper mapper = new ObjectMapper();

				try {
					line = in.readUTF();
					Test test = mapper.readValue(line, Test.class);
					String response = crud(test);
					System.out.println(test.toString());
					// safina lklb
					DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
					// write object to Socket
					oos.writeUTF(response);

				} catch (IOException i) {
					System.out.println(i);
				}
			}
			System.out.println("Closing connection");

			// close connection
			socket.close();
			in.close();
		} catch (IOException i) {
			System.out.println(i);
		}
	}

	String saveTest(Test test) {
		try {
			dataSource.addTest(test.getClient(), test.getServer(), test.getDB());
			return "saved successfully";
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	String deleteTest(Test test) {
		dataSource.deleteTestByDB(test.getDB());
	return "deleted successfully";
		
	}

	String updateTest(Test test) {
		dataSource.updateTest(test.getClient(), test.getServer(), test.getDB());
		return "updated successfully";

	}

	String findAll() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(dataSource.findAll());
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	String crud(Test test) {
		String crudEnum = test.getCrud().toString();

		// Converting the Object to JSONString

		switch (crudEnum) {
		
		case "SAVE":
			return saveTest(test);

		case "UPDATE":
            return updateTest(test);

		case "DELETE":
			return deleteTest(test);
			
		case "FIND_ALL":
			return findAll();
			
		default:
			return null;
		}
	}
}

