package puzzle_city_ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_client.Client;
import puzzle_city_client_model.ApiEnum;
import puzzle_city_client_model.SendPackage;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JTextPane;

public class ConfigSensorAir {

	public JFrame frame;
	private JTextField said;
	private int id;
	private String address;
	private int no2;
	private int pm10;
	private int o3;
	private boolean alert;
	private int alert_id;

	public Client client;// = new Client("127.0.0.1", 4000);
	private JTable table;
	JPanel panel_cityinfo;
	private JLabel errorM;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.constructor
	 */

	public ConfigSensorAir(Client socket, int id, String address, int no2, int pm10, int o3, boolean alert,
			int alert_id) {
		this.id = id;
		this.address = address;
		this.no2 = no2;
		this.pm10 = pm10;
		this.o3 = o3;
		this.alert = alert;
		this.alert_id = alert_id;
		client = socket;
		initialize(id, address, no2, pm10, o3, alert);
	}

	public ConfigSensorAir() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id, String address, int no2, int pm10, int o3, boolean alert) {
		setType(Type.UTILITY);
		panel_cityinfo = new JPanel();
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 664, 439);
		frame.getContentPane().add(panel);

		panel_cityinfo.setBounds(10, 55, 644, 364);
		panel.add(panel_cityinfo);
		panel_cityinfo.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Sensor Address :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 11, 105, 14);
		panel_cityinfo.add(lblNewLabel_1);

		said = new JTextField();
		said.setBounds(136, 11, 315, 20);
		if (id != 0) {
			said.setText(address);

		}

		panel_cityinfo.add(said);
		said.setColumns(10);

		JButton btnCreate = new JButton("Update");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateSensorInfo();
			}
		});
		btnCreate.setBounds(152, 318, 89, 23);
		panel_cityinfo.add(btnCreate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnCancel.setBounds(415, 318, 89, 23);
		panel_cityinfo.add(btnCancel);

		table = new JTable();
		table.setBounds(51, 111, 1, 1);
		panel_cityinfo.add(table);

		JButton btnCreate_1 = new JButton("Delete");
		btnCreate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnCreate_1.setBounds(280, 318, 89, 23);
		panel_cityinfo.add(btnCreate_1);

		JLabel lblNewLabel_1_1 = new JLabel("Configure threshold :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(-44, 111, 179, 14);

		panel_cityinfo.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("NO\u2082");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setBounds(-18, 139, 194, 14);
		panel_cityinfo.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("PM10\r\n");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setBounds(0, 175, 179, 14);
		panel_cityinfo.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel(" O\u2083");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setBounds(0, 211, 179, 14);
		panel_cityinfo.add(lblNewLabel_1_4);

		JButton btnMore = new JButton("simulate");
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//simulate();
			}
		});
		btnMore.setBounds(327, 84, 74, 23);
		panel_cityinfo.add(btnMore);
		
		JButton btnMore_1 = new JButton("show alert history");
		btnMore_1.setHorizontalAlignment(SwingConstants.LEADING);
		btnMore_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				AlertHistory a = new AlertHistory(client, id, address, no2, pm10, o3, alert, id);
				a.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnMore_1.setBounds(461, 10, 142, 23);
		panel_cityinfo.add(btnMore_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Sensor Status : \r\n");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_2.setBounds(10, 64, 105, 14);
		panel_cityinfo.add(lblNewLabel_1_1_2);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("ON");
		chckbxNewCheckBox.setBounds(136, 60, 97, 23);
		panel_cityinfo.add(chckbxNewCheckBox);
		
		JSlider slider = new JSlider();
		slider.setValue(70);
		slider.setBounds(212, 133, 179, 31);
		panel_cityinfo.add(slider);
		
		JSlider slider_1 = new JSlider();
		slider_1.setValue(40);
		slider_1.setBounds(212, 164, 179, 31);
		panel_cityinfo.add(slider_1);
		
		JSlider slider_2 = new JSlider();
		slider_2.setValue(10);
		slider_2.setBounds(212, 206, 179, 31);
		panel_cityinfo.add(slider_2);
		
		JLabel errorM = new JLabel("Error syntax :\r\n error unrecognized expression");
		errorM.setFont(new Font("Tahoma", Font.BOLD, 13));
		errorM.setForeground(Color.RED);
		errorM.setBounds(177, 266, 411, 58);
		panel_cityinfo.add(errorM);
		errorM.setVisible(false);
		
		JButton btnCreate_1_1 = new JButton("threshold details\r\n");
		btnCreate_1_1.setHorizontalAlignment(SwingConstants.LEADING);
		btnCreate_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReglementationFrance rf = new ReglementationFrance(client, id, address, no2, pm10, o3, alert, id);
				rf.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCreate_1_1.setBounds(237, 249, 133, 23);
		panel_cityinfo.add(btnCreate_1_1);

		JLabel lblNewLabel = new JLabel("Air Quality Sensor Details");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(214, 11, 240, 27);
		panel.add(lblNewLabel);

	}

//	void simulate() {
//		this.no2 = (int) (Math.random() * 100);
//		this.pm10 = (int) (Math.random() * 100);
//		this.o3 = (int) (Math.random() * 100);
//		this.alert = this.no2 >= 80 || this.pm10 >= 80 || this.o3 >= 80;
//		progressBar.setValue(no2);
//		progressBar_1.setValue(pm10);
//		progressBar_2.setValue(o3);
//		//lblNewLabel_1_1_1.setVisible(alert);
//		// initialize(id, address, no2, pm10, o3, alert);
//
//	}

	private void setType(Type utility) {
		// TODO Auto-generated method stub

	}

	private void setDataToField(JSONObject res) {
		// TODO Auto-generated method stub
		try {
			said.setText(res.getString("address"));
//			txtHeight.setText(String.valueOf( res.getDouble("Height")));
//			txtWidth.setText(String.valueOf( res.getDouble("Width")));
//			txtLat.setText(String.valueOf( res.getDouble("CenterLat")));
//			txtLong.setText(String.valueOf( res.getDouble("CenterLong")));
//			txtMapZoom.setText(String.valueOf( res.getInt("MapZoom")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateSensorInfo() {
		try {
			client.setResponseData(null);
			JSONObject bodyItem = new JSONObject();
			bodyItem.put("id", "" + this.id);
			bodyItem.put("address", "" + said.getText());
//			bodyItem.put("no2", "" + progressBar.getValue());
//			bodyItem.put("pm10", "" + progressBar_1.getValue());
//			bodyItem.put("o3", "" + progressBar_2.getValue());
			bodyItem.put("alert", alert);
			bodyItem.put("alert_id", alert_id);
           
			SendPackage sendPa = new SendPackage();
			sendPa.setApi(ApiEnum.SENSORAIR_UPDATE);
			sendPa.setBody(bodyItem);
			client.setSendP(sendPa);

			JSONObject res = null;

			while (res == null) {
				// res = client.getResponseData();
				res = client.getResponseData();
				System.out.println("wait res:" + res);
				if (res != null) {
					// if success

					boolean sMess = res.getBoolean("success");
					if (sMess) {
						errorM.setText(res.getString("msg"));
					} else {
						errorM.setText("Error :" + res.getString("msg"));
					}
					System.out.println("tra ve:" + res.toString());
				}
			}
			cancel();

//			getSensorInfo();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void cancel() {
		SensorAirList sa = new SensorAirList(client);
		sa.frame.setVisible(true);
		frame.dispose();
	}
	public void fill() 
    { 
        int i = 0; 
        try { 
            while (i <= 100) { 
            	//progressBar.setValue(i + 10); 
                Thread.sleep(1000); 
                i += 20; 
            } 
        } 
        catch (Exception e) { 
        } 
    } 

	void delete() {

		try {
			client.setResponseData(null);
			JSONObject bodyItem = new JSONObject();
			bodyItem.put("id", "" + this.id);
            bodyItem.put("alert_id", alert_id);
			SendPackage sendPa = new SendPackage();
			sendPa.setApi(ApiEnum.SENSORAIR_DELETE);
			sendPa.setBody(bodyItem);
			client.setSendP(sendPa);

			JSONObject res = null;
			while (res == null) {
				res = client.getResponseData();
				// chof
				System.out.println("wait res:" + res);

				if (res == null) {
					// if success

					boolean sMess = res.getBoolean("success");
					if (sMess) {
						errorM.setText(res.getString("msg"));
					} else {
						errorM.setText("Error :" + res.getString("msg"));
					}
					System.out.println("tra ve:" + res.toString());
				}
			}
			cancel();

//			getSensorInfo();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
