package puzzle_city_ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_client.Client;
import puzzle_city_client_model.ApiEnum;
import puzzle_city_client_model.SendPackage;
import puzzle_city_client_model.VehiculeSensorTable;

public class VehiculeSensorList {

	public JFrame frame;
	private JTable VehiculeSensorTable;
	public Client client;// = new Client("127.0.0.1", 4000);
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public VehiculeSensorList(Client socket) {
		client = socket;
		initialize();

		getVechiculeSensorData();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		System.out.println("initialize");
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 664, 439);
		frame.getContentPane().add(panel);

		// table
		VehiculeSensorTable t = new VehiculeSensorTable();

//		//set data  for table	
		// add vehicule sensor
		JPanel panel_cityinfo_1 = new JPanel();
		panel_cityinfo_1.setLayout(null);
		panel_cityinfo_1.setBounds(10, 64, 644, 364);
		panel.add(panel_cityinfo_1);
		// list vehicule sensor
		JPanel panel_cityinfo = new JPanel();
		panel_cityinfo.setBounds(-10, 0, 644, 364);
		panel_cityinfo_1.add(panel_cityinfo);
		panel_cityinfo.setLayout(null);

		JLabel lblListCity = new JLabel("Vehicule sensor list");
		lblListCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblListCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListCity.setBounds(241, 28, 164, 27);
		panel_cityinfo.add(lblListCity);
		VehiculeSensorTable = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Address" }) {
			boolean[] columnEditables = new boolean[] { false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		JButton btnCreateButton = new JButton("Add new vehicule sensor ");
		btnCreateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateVehiculeSensor saAdd = new CreateVehiculeSensor(client);
				saAdd.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCreateButton.setBackground(Color.WHITE);
		btnCreateButton.setBounds(48, 294, 179, 23);
		panel_cityinfo.add(btnCreateButton);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard db = new Dashboard();
				db.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(390, 294, 121, 23);
		panel_cityinfo.add(btnCancel);

		JButton btnCancel_1 = new JButton("Configure");
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigVehiculeSensor cf = new ConfigVehiculeSensor(client);
				cf.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCancel_1.setBackground(Color.WHITE);
		btnCancel_1.setBounds(241, 294, 121, 23);
		panel_cityinfo.add(btnCancel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 68, 543, 201);
		panel_cityinfo.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Veihcule Sensor Manager System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(159, 11, 337, 27);
		panel.add(lblNewLabel);

	}

	private void getVechiculeSensorData() {
		// TODO Auto-generated method stub
		client.setResponseData(null);
		SendPackage sendP = new SendPackage();
		sendP.setApi(ApiEnum.VEHICULESENSOR_FIND_ALL);
		client.setSendP(sendP);
		JSONObject res = null;
		while (res == null) {
			res = client.getResponseData();
			System.out.println("waiting:" + res);
			if (res != null) {
				// if success true - get data bind to table
				System.out.println(res.toString());
				boolean sMess;
				try {
					sMess = res.getBoolean("success");
					if (sMess) {
						bindDataToTable(res.getJSONArray("data"));
					} else {
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//

		client.setResponseData(null);
	}

	private void bindDataToTable(JSONArray jArray) {
		// TODO Auto-generated method stub
		DefaultTableModel model = new DefaultTableModel();
		String[] columnNames = { "ID", "Address" };
		model.setColumnIdentifiers(columnNames);

		ArrayList arrRows = new ArrayList();
		for (int i = 0; i < jArray.length(); i++) {
			JSONObject jb;
			try {
				jb = jArray.getJSONObject(i);

				Object[] rowData = { jb.getInt("ID"), jb.getString("Address"),

				};

				model.addRow(rowData);
				arrRows.clear();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		VehiculeSensorTable.setModel(model);

	}
}