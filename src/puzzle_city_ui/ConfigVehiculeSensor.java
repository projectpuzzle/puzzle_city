package puzzle_city_ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_client.Client;
import puzzle_city_client_model.ApiEnum;
import puzzle_city_client_model.SendPackage;

public class ConfigVehiculeSensor {

	public JFrame frame;
	private JTextField txtNb_vehicule_max;
	private int ID;
	private JLabel lbtMess;

	public Client client;// = new Client("127.0.0.1", 4000);

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @wbp.parser.constructor
	 */
	public ConfigVehiculeSensor(Client socket) {
		client = socket;
		initialize();
	}
	
	public ConfigVehiculeSensor() {
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 664, 439);
		frame.getContentPane().add(panel);

		JPanel panel_cityinfo = new JPanel();
		panel_cityinfo.setBounds(10, 64, 644, 364);
		panel.add(panel_cityinfo);
		panel_cityinfo.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Vehicule number treshold");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 11, 179, 14);
		panel_cityinfo.add(lblNewLabel_1);

		txtNb_vehicule_max = new JTextField();
		txtNb_vehicule_max.setBounds(212, 11, 315, 20);
		panel_cityinfo.add(txtNb_vehicule_max);
		txtNb_vehicule_max.setColumns(10);

//		JLabel lblNewLabel_1_1 = new JLabel("Latitude");
//		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblNewLabel_1_1.setBounds(10, 39, 179, 14);
//		panel_cityinfo.add(lblNewLabel_1_1);
//		
//		txtLat = new JTextField();
//		txtLat.setColumns(10);
//		txtLat.setBounds(212, 39, 315, 20);
//		panel_cityinfo.add(txtLat);
////		
//		JLabel lblNewLabel_1_1_1 = new JLabel("Longitude");
//		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblNewLabel_1_1_1.setBounds(10, 67, 179, 14);
//		panel_cityinfo.add(lblNewLabel_1_1_1);
//		
//		txtLong = new JTextField();
//		txtLong.setColumns(10);
//		txtLong.setBounds(212, 67, 315, 20);
//		panel_cityinfo.add(txtLong);
//		
//		txtHeight = new JTextField();
//		txtHeight.setColumns(10);
//		txtHeight.setBounds(212, 95, 315, 20);
//		panel_cityinfo.add(txtHeight);
//		
//		JLabel lblNewLabel_1_1_1_1 = new JLabel("Height");
//		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblNewLabel_1_1_1_1.setBounds(10, 95, 179, 14);
//		panel_cityinfo.add(lblNewLabel_1_1_1_1);
//		
//		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Width");
//		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblNewLabel_1_1_1_1_1.setBounds(10, 126, 179, 14);
//		panel_cityinfo.add(lblNewLabel_1_1_1_1_1);
//		
//		txtWidth = new JTextField();
//		txtWidth.setColumns(10);
//		txtWidth.setBounds(212, 126, 315, 20);
//		panel_cityinfo.add(txtWidth);
//		
//		txtMapZoom = new JTextField();
//		txtMapZoom.setColumns(10);
//		txtMapZoom.setBounds(212, 154, 315, 20);
//		panel_cityinfo.add(txtMapZoom);
//		
//		JLabel lbtMapZoom = new JLabel("Map Zoom");
//		lbtMapZoom.setHorizontalAlignment(SwingConstants.RIGHT);
//		lbtMapZoom.setBounds(10, 154, 179, 14);
//		panel_cityinfo.add(lbtMapZoom);

		JButton btnCreate = new JButton("Update");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateThresholdInfo();
			}
		});
		btnCreate.setBounds(212, 185, 89, 23);
		panel_cityinfo.add(btnCreate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehiculeSensorList sa = new VehiculeSensorList(client);
				sa.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCancel.setBounds(314, 185, 89, 23);
		panel_cityinfo.add(btnCancel);

		JLabel lblNewLabel = new JLabel("Configure your Vehicule Sensor");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(199, 13, 285, 27);
		panel.add(lblNewLabel);
	}

	private void getTresholdData() {
		// TODO Auto-generated method stub		
		client.setResponseData(null);
		SendPackage sendP = new SendPackage();
		sendP.setApi(ApiEnum.THRESHOLD_FIND_ALL);		
		client.setSendP(sendP);
		JSONObject res = null;
		while(res == null) {
			res = client.getResponseData();
			System.out.println("waiting:"+res);
			if(res!= null) {
				// if success true - get data bind to table 
				System.out.println(res.toString());
				boolean sMess;
				try {
					sMess = res.getBoolean("success");				
					if(sMess) {
						//bindDataToTable(res.getJSONArray("data"));
					}else {						
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void setDataToField(JSONObject res) {
		// TODO Auto-generated method stub
		try {
			txtNb_vehicule_max.setText(res.getString("Nb_vehicule_max"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	private void updateTresholdInfo() {
		if(true) {	
			try {
				client.setResponseData(null);		
				JSONObject bodyItem = new JSONObject();
				bodyItem.put("Nb_vehicule_max",  Nb_vehicule_max);

				SendPackage sendPa = new SendPackage();
				sendPa.setApi(ApiEnum.THRESHOLD_UPDATE);		
				sendPa.setBody(bodyItem);
				client.setSendP(sendPa);

				JSONObject res = null;
				while(res == null) {
					res = client.getResponseData();
					System.out.println("wait res:"+res);
					if(res!= null) {
						// if success 
						boolean sMess = res.getBoolean("success");
						if(sMess) {
							lbtMess.setText("Update Success");
						}else {
							lbtMess.setText("Error :"+res.getString("msg") );						
						}
						System.out.println("Return:"+res.toString());
					}
				} 
				getTresholdData();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}