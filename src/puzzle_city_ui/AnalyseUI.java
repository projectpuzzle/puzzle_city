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

	public JFrame frame;
	Client client;
	private int cityID;
	private String numberOfAirSensor;
	private JLabel lbtSensors;
	private JLabel jl2;
	private JLabel lblStations ;
	private JLabel lblBollards;
	private JLabel lblDistance;
	
	/**
	 * Create the application.
	 */
	
	public AnalyseUI(Client client, int cID) {
		this.client = client;
		this.cityID = cID;
		initialize();
		
		getCityInfo();
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
		
		JLabel lblResidents = new JLabel("");
		lblResidents.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblResidents);
		
		JLabel lblNewLabel_1 = new JLabel("The number of sensors installed in the city");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		lbtSensors = new JLabel();
		lbtSensors.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbtSensors);
		
		JLabel lblTheNumberOf_1 = new JLabel("The number of Stations");
		lblTheNumberOf_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTheNumberOf_1);
		
		lblStations = new JLabel("");
		lblStations.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblStations);
		
		JLabel lblNewLabel_7 = new JLabel("The number of bollards");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_7);
		
		lblBollards = new JLabel("");
		lblBollards.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblBollards);
		
		JButton btnNewButton_3 = new JButton("Comparison");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblNewLabel_8 = new JLabel("Distance of public transit");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_8);
		
		lblDistance = new JLabel("");
		lblDistance.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDistance);
		
		JLabel lblExceedingRateOf = new JLabel("Rate of pollution");
		lblExceedingRateOf.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblExceedingRateOf);
		
		JLabel lblRatePollution = new JLabel("");
		lblRatePollution.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblRatePollution);
		
		JLabel lblExceedingRateOf_1 = new JLabel("Exceeding rate of pollution");
		lblExceedingRateOf_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblExceedingRateOf_1);
		
		JLabel lblExceeding = new JLabel("");
		panel.add(lblExceeding);
		
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
				lbtSensors.setText((responseserver.get("values")));
			}
			
		} 			
		//CLOSE
	}
	
	public JFrame getJFrame() {
		return frame;
	}
	
	private void getCityInfo() {
		try {
			client.setResponseData(null);		
			JSONObject bodyItem = new JSONObject();
			bodyItem.put("ID", "" +cityID);

			SendPackage sendPa = new SendPackage();
			sendPa.setApi(ApiEnum.ANALYSE_ONE_CITY);		
			sendPa.setBody(bodyItem);
			client.setSendP(sendPa);

			JSONObject res = null;
			while(res == null) {
				res = client.getResponseData();

				System.out.println("wait res:"+res);
				if(res!= null) {
					// if success true - get data bind to table 
					setDataToField((res.getJSONArray("data")).getJSONObject(0));
				}
			} 			
			//CLOSE

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setDataToField(JSONObject res) {
		// TODO Auto-generated method stub
		try {
			//lbtSensors.setText(res.getString("CountSensor"));
			lblStations.setText(""+ res.getInt("CountStation"));
	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
}
