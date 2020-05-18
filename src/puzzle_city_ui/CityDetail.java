package puzzle_city_ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_client.Client;
import puzzle_city_client_model.ApiEnum;
import puzzle_city_client_model.SendPackage;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CityDetail {

	public JFrame frame;
	private JTextField txtCityName;
	private JTextField txtLat;
	private JTextField txtLong;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private JTextField txtMapZoom;
	private int cityID;
	private JLabel lbtMess;

	public 	Client client ;//= new Client("127.0.0.1", 4000);
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public CityDetail(Client socket, int id) {
		client = socket;
		cityID = id;
		initialize();
		getCityInfo();
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
		
		JButton btnMenuDashboard = new JButton("Dashboard");
		btnMenuDashboard.setBounds(10, 64, 166, 23);
		panel.add(btnMenuDashboard);
		
		JButton btnMenuCityInfomation = new JButton("City Infomation");
		btnMenuCityInfomation.setBackground(Color.DARK_GRAY);
		btnMenuCityInfomation.setForeground(Color.WHITE);
		btnMenuCityInfomation.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMenuCityInfomation.setBounds(10, 98, 166, 23);
		panel.add(btnMenuCityInfomation);
		
		JButton btnMenuTramwayStation = new JButton("Tramway Station");
		btnMenuTramwayStation.setBounds(10, 132, 166, 23);
		panel.add(btnMenuTramwayStation);
		
		JButton btnMenuBollards = new JButton("Bollards");
		btnMenuBollards.setBounds(10, 166, 166, 23);
		panel.add(btnMenuBollards);
		
		JPanel panel_cityinfo = new JPanel();
		panel_cityinfo.setBounds(186, 64, 468, 364);
		panel.add(panel_cityinfo);
		panel_cityinfo.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("City Name:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 11, 97, 14);
		panel_cityinfo.add(lblNewLabel_1);
		
		txtCityName = new JTextField();
		txtCityName.setBounds(119, 8, 232, 20);
		panel_cityinfo.add(txtCityName);
		txtCityName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Latitude");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(10, 39, 97, 14);
		panel_cityinfo.add(lblNewLabel_1_1);
		
		txtLat = new JTextField();
		txtLat.setColumns(10);
		txtLat.setBounds(119, 36, 232, 20);
		panel_cityinfo.add(txtLat);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Longitude");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setBounds(10, 67, 97, 14);
		panel_cityinfo.add(lblNewLabel_1_1_1);
		
		txtLong = new JTextField();
		txtLong.setColumns(10);
		txtLong.setBounds(119, 64, 232, 20);
		panel_cityinfo.add(txtLong);
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		txtHeight.setBounds(119, 92, 232, 20);
		panel_cityinfo.add(txtHeight);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Height");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setBounds(10, 95, 97, 14);
		panel_cityinfo.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Width");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setBounds(10, 126, 97, 14);
		panel_cityinfo.add(lblNewLabel_1_1_1_1_1);
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		txtWidth.setBounds(119, 123, 232, 20);
		panel_cityinfo.add(txtWidth);
		
		txtMapZoom = new JTextField();
		txtMapZoom.setColumns(10);
		txtMapZoom.setBounds(119, 151, 232, 20);
		panel_cityinfo.add(txtMapZoom);
		
		JLabel lbtMapZoom = new JLabel("Map Zoom");
		lbtMapZoom.setHorizontalAlignment(SwingConstants.RIGHT);
		lbtMapZoom.setBounds(10, 154, 97, 14);
		panel_cityinfo.add(lbtMapZoom);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCityInfo();
			}
		});
		btnUpdate.setBounds(119, 182, 89, 23);
		panel_cityinfo.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard cityList = new Dashboard(client,cityID);
				cityList.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCancel.setBounds(221, 182, 89, 23);
		panel_cityinfo.add(btnCancel);
		
		lbtMess = new JLabel("");
		lbtMess.setBounds(119, 212, 315, 51);
		panel_cityinfo.add(lbtMess);
		
		JLabel lblNewLabel = new JLabel("City Manager System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(214, 11, 197, 27);
		panel.add(lblNewLabel);
	}

	
	private void getCityInfo() {
		try {
			client.setResponseData(null);		
			JSONObject bodyItem = new JSONObject();
			bodyItem.put("ID", "" +cityID);
			
			SendPackage sendPa = new SendPackage();
			sendPa.setApi(ApiEnum.CITY_GET_ONE);		
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
			txtCityName.setText(res.getString("Name"));
			txtHeight.setText(String.valueOf( res.getDouble("Height")));
			txtWidth.setText(String.valueOf( res.getDouble("Width")));
			txtLat.setText(String.valueOf( res.getDouble("CenterLat")));
			txtLong.setText(String.valueOf( res.getDouble("CenterLong")));
			txtMapZoom.setText(String.valueOf( res.getInt("MapZoom")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	private void updateCityInfo() {
		try {			
			client.setResponseData(null);		
			JSONObject bodyItem = new JSONObject();
			bodyItem.put("ID", "" +cityID);
			bodyItem.put("name", "" +txtCityName.getText());
			bodyItem.put("height", Double.parseDouble( txtHeight.getText()));
			bodyItem.put("width", txtWidth.getText());
			bodyItem.put("centerLat", txtLat.getText());
			bodyItem.put("centerLong", "" +txtLong.getText());
			bodyItem.put("mapZoom", "" + txtMapZoom.getText());
			
			SendPackage sendPa = new SendPackage();
			sendPa.setApi(ApiEnum.CITY_UPDATE);		
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
						lbtMess.setText( res.getString("msg"));
					}else {
						lbtMess.setText("Error :"+res.getString("msg") );						
					}
					System.out.println("tra ve:"+res.toString());
				}
			} 
			getCityInfo();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
