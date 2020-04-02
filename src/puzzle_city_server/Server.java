package puzzle_city_server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import puzzle_city_commons.ClientHandler;
import puzzle_city_connectionPool.DataSource;

public class Server extends Thread {

	// initialize socket and input stream
	private Socket socket = null;
	private ServerSocket server = null;

	DataSource dataSource;

	// constructor with port
	public Server(int port) {

		// starts server and waits for a connection
		try {
			dataSource = new DataSource();
			server = new ServerSocket(port);
			server.setReuseAddress(true);
			System.out.println("Server started on port " + port);

			System.out.println("Waiting for a client ...");
			while (true) {
				socket = server.accept();

				System.out.println("New client connected " + socket.getInetAddress().getHostAddress());
				ClientHandler clientSock = new ClientHandler(socket);
				new Thread(clientSock).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	}
