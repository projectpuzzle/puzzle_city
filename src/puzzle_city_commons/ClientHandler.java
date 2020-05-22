package puzzle_city_commons;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import org.json.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientHandler implements Runnable {

	private Socket socket;
	private DataInputStream in = null;
	private PrintWriter outmsg;
	private BufferedReader inmsg;

	public ClientHandler(Socket socket) throws IOException {
		this.socket = socket;
		outmsg = new PrintWriter(socket.getOutputStream(), true);
		inmsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	@Override
	public void run() {
		// takes input from the client socket
		try {
			handler();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		 * String line = "";
		 * 
		 * try {
		 * 
		 * in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		 * System.out.println("  New client connected  in host   :    " +
		 * socket.getInetAddress().getHostAddress() + "     Id Client   :   " +
		 * in.readUTF());
		 * 
		 * while (!line.equals("0")) { String response = ""; try { line = in.readUTF();
		 * if (!line.equals("0")) { JSONObject input; System.out.println(line); input =
		 * new JSONObject(line); System.out.println("line:" + line); if
		 * (input.get("api").toString() == "CLOSE_CONNECTION") { line = "0";
		 * System.out.println("Client close connection"); } else { // reponse du serveur
		 * pour le client format json response = Router.router(input);
		 * System.out.println(response.toString()); } } } catch (JSONException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } DataOutputStream oos =
		 * new DataOutputStream(socket.getOutputStream()); // write object to Socket
		 * 
		 * oos.writeUTF(response);
		 * 
		 * } System.out.println("Closing connection");
		 * 
		 * socket.close(); in.close(); } catch (IOException e) { e.printStackTrace(); }
		 */

	}

	public void sendMessage(String msg) throws IOException {
		outmsg.println(msg);
	}

	public String getMessage() throws IOException {
		String resp;
		if((resp = inmsg.readLine()) != null)
		{
			return resp;
		}else
		{
			return "erreur getMessage()";
		}
		
		
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void handler() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		HashMap<String, String> responsetoclient = new HashMap<String, String>();
		String jsonresponsetoclient = "";
		String request;
		if ((request = getMessage()) != null) {

			HashMap<String, String> requestclient = mapper.readValue(request, HashMap.class);

			if ((requestclient.get("operation_type")).equals("getnumberofairsensor")) {
				responsetoclient.put("response_type", "getnumberofairsensor");
				String s = Router.route();
				responsetoclient.put("values", s);
				jsonresponsetoclient = mapper.writeValueAsString(responsetoclient);

			}

			System.out.println("Closing connection");
		}
//CLOSE
		socket.close();
		inmsg.close();
		outmsg.close();
	}

}
