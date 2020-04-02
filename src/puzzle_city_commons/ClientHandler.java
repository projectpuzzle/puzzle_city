package puzzle_city_commons;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.codehaus.jackson.map.ObjectMapper;

import puzzle_city_connectionPool.Test;

public class ClientHandler implements Runnable {

	private final Socket socket;
	private DataInputStream in = null;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// takes input from the client socket
		String line = "";

	

		try {
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			while (!line.equals("0")) {
				ObjectMapper mapper = new ObjectMapper();
// reponse du serveur pour le client format json
				line = in.readUTF();
				Test test = mapper.readValue(line, Test.class);
				String response = CrudMethods.crud(test);
				System.out.println(test.toString());
				DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
				// write object to Socket
		
				oos.writeUTF(response);

			}
			System.out.println("Closing connection");

			socket.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

