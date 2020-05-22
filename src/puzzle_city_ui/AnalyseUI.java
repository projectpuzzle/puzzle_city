package puzzle_city_ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import puzzle_city_client.Client;
import puzzle_city_client_model.ApiEnum;
import puzzle_city_client_model.SendPackage;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class AnalyseUI {

	private JFrame frame;
	Client client;
	private String numberOfAirSensor;
	private JLabel jl1;
	private JLabel jl2;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					//adress, port
					AnalyseUI window = new AnalyseUI(new Client("127.0.0.1", 10000));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public AnalyseUI(Client client) {
		this.client = client;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 664, 439);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 8));
		
		JButton btnNewButton = new JButton("Number of residents");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		
		
		
		JButton btnNewButton_1 = new JButton("Number of air quality sensors");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeNumberOfAirSensor();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(true);
				frame.dispose();
			}
		});
		panel.add(btnNewButton_1);
		
		jl1 = new JLabel();
		panel.add(jl1);
		
		JButton btnNewButton_2 = new JButton("Number of stations");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_4 = new JButton("Number of bollards");
		panel.add(btnNewButton_4);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("Compare");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_5 = new JButton("New button");
		panel.add(btnNewButton_5);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton_7 = new JButton("New button");
		panel.add(btnNewButton_7);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		panel.add(lblNewLabel_6);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBounds(626, 319, 48, 25);
		frame.getContentPane().add(btnNewButton_6);
	}
	
	
	@SuppressWarnings("unchecked")
	private void takeNumberOfAirSensor() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		HashMap<String, String> requestclient = new HashMap<String, String>();
		HashMap<String, String> responseserver = new HashMap<String, String>();
		
		requestclient.put("operation_type", "getnumberofairsensor");
		
		String jsonrequestclient = mapper.writeValueAsString(requestclient);
		String jsonresponseserver = "";

		client.sendMessage(jsonrequestclient);
		
		while( (jsonresponseserver = this.client.getMessage()) != null) {
			
			
			responseserver = mapper.readValue(jsonresponseserver, HashMap.class);
			
			if((responseserver.get("response_type")).equals("getnumberofairsensor") ) 
			{
				jl1.setText((responseserver.get("values")));
			}
			
		} 			
		//CLOSE
	}
	
	public JFrame getJFrame() {
		return frame;
	}
	
}
