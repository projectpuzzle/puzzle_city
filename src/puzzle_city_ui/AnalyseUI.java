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
import javax.swing.SwingConstants;

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
		
		JLabel lblTheNumberOf = new JLabel("The number of residents");
		lblTheNumberOf.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTheNumberOf);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("The number of sensors installed in the city");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		jl1 = new JLabel();
		jl1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(jl1);
		
		JLabel lblTheNumberOf_1 = new JLabel("The number of Stations");
		lblTheNumberOf_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTheNumberOf_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_7 = new JLabel("The number of bollards");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("Comparison");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblNewLabel_8 = new JLabel("Distance of public transit");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_4);
		
		JLabel lblExceedingRateOf = new JLabel("Rate of pollution");
		lblExceedingRateOf.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblExceedingRateOf);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_5);
		
		JLabel lblExceedingRateOf_1 = new JLabel("Exceeding rate of pollution");
		lblExceedingRateOf_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblExceedingRateOf_1);
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_6);
		panel.add(btnNewButton_3);
		
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
