package puzzle_city_ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_client.Client;
import puzzle_city_client_model.ApiEnum;
import puzzle_city_client_model.SendPackage;
import puzzle_city_client_model.SensorQualityAirTable;

public class SensorAirList {

	public JFrame frame;
	private JTable tblsensorair;
	public Client client;// = new Client("127.0.0.1", 4000);

	List<Object[]> list = new ArrayList<>();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 * 
	 * @wbp.parser.constructor
	 */
	public SensorAirList(Client socket) {
		client = socket;
		initialize();

		getSensorAirData();
	}

	public SensorAirList(JTable tblsensorair) {

		this.tblsensorair = tblsensorair;
	}

	/**
	 * Initialize the contents of the frame.
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
		SensorQualityAirTable tcb = new SensorQualityAirTable();

//		//set data  for table	
		// add city
		JPanel panel_cityinfo_1 = new JPanel();
		panel_cityinfo_1.setLayout(null);
		panel_cityinfo_1.setBounds(10, 64, 644, 364);
		panel.add(panel_cityinfo_1);
		// list city
		JPanel panel_cityinfo = new JPanel();
		panel_cityinfo.setBounds(-10, 0, 644, 364);
		panel_cityinfo_1.add(panel_cityinfo);
		panel_cityinfo.setLayout(null);

		JLabel lblListCity = new JLabel("Air quality sensors list");
		lblListCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblListCity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblListCity.setBounds(230, 25, 189, 27);
		panel_cityinfo.add(lblListCity);

		tblsensorair = new JTable(new DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, },
				new String[] { "id", "address", "Action" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		tblsensorair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tblsensorair.getSelectedRow();
				int id = Integer.parseInt(list.get(row)[0].toString());
				String address = list.get(row)[1].toString();
				int no2 = (int) list.get(row)[2];
				int pm10 = (int) list.get(row)[3];
				int o3 = (int) list.get(row)[4];
				boolean alert = (boolean) list.get(row)[5];
				int alertId = (int) list.get(row)[7];
				boolean isActivated = (boolean) list.get(row)[8];
				// System.out.println("ppp" + globalModel.getColumnCount());
				ConfigSensorAir cS = new ConfigSensorAir(client, id, address, no2, pm10, o3, alert, alertId,
						isActivated);
				cS.frame.setVisible(true);
				frame.dispose();

			}
		});

		// tao scrollpane roi cho table chui vao thi table thi tieu de moi hien thi
		JScrollPane jsp = new JScrollPane(tblsensorair);
		jsp.setBounds(81, 95, 482, 108);
		panel_cityinfo.add(jsp);

		JButton btnCreateButton = new JButton("Add new sensor ");
		btnCreateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateSensorAir saAdd = new CreateSensorAir(client);
				saAdd.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCreateButton.setBackground(Color.WHITE);
		btnCreateButton.setBounds(99, 294, 139, 23);
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
		btnCancel.setBounds(423, 294, 121, 23);
		panel_cityinfo.add(btnCancel);

		JLabel lblClickOnThe = new JLabel("*Click on the desired sensor for more details");
		lblClickOnThe.setForeground(Color.BLACK);
		lblClickOnThe.setHorizontalAlignment(SwingConstants.LEFT);
		lblClickOnThe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblClickOnThe.setBounds(202, 225, 265, 26);
		panel_cityinfo.add(lblClickOnThe);

		JButton btnThresholdDetails = new JButton("Threshold details\r\n");
		btnThresholdDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReglementationFrance rf = new ReglementationFrance(client);
				rf.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnThresholdDetails.setBackground(Color.WHITE);
		btnThresholdDetails.setBounds(259, 294, 139, 23);
		panel_cityinfo.add(btnThresholdDetails);

		JLabel lblNewLabel = new JLabel("Air Quality Sensors Manager System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(161, 26, 337, 27);
		panel.add(lblNewLabel);

	}

	private void getSensorAirData() {
		// TODO Auto-generated method stub
		client.setResponseData(null);
		SendPackage sendP = new SendPackage();
		sendP.setApi(ApiEnum.SENSORAIR_FIND_ALL);
		client.setSendP(sendP);
		JSONObject res = null;
		while (res == null) {

			res = client.getResponseData();
			System.out.println("waiting:" + res);
			if (res != null) {

				// System.out.println("waiting:" + res);

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
		// globalModel = new DefaultTableModel();
		DefaultTableModel model = new DefaultTableModel();
		String[] columnNames = { "Address", "Alert status", "Alert time" };
		model.setColumnIdentifiers(columnNames);

		ArrayList arrRows = new ArrayList();
		for (int i = 0; i < jArray.length(); i++) {
			JSONObject jb;
			try {
				jb = jArray.getJSONObject(i);

				JSONObject alerteModel = jb.getJSONObject("alerteModel");
				boolean isAlerted = alerteModel.getBoolean("alert");
				Object[] rowData = { jb.getString("address"), isAlerted ? "Alerted" : "Not alerted",
						isAlerted ? alerteModel.getString("date") : "---" };

				Object[] globalrowData = { jb.getInt("id"), jb.getString("address"), jb.getInt("no2"),
						jb.getInt("pm10"), jb.getInt("o3"), alerteModel.getBoolean("alert"),
						alerteModel.has("date") ? alerteModel.getString("date") : "---", alerteModel.get("id"),
						jb.getBoolean("activated")

				};
				// globalModel.addRow(globalrowData);
				list.add(globalrowData);
				model.addRow(rowData);
				arrRows.clear();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		tblsensorair.setModel(model);
//		tblsensorair.getColumn("Delete").setCellRenderer(new ButtonRenderer());
//		tblsensorair.getColumn("Delete").setCellEditor(
//		        new ButtonEditor(new JCheckBox()));
//		tblsensorair.setVisible(true);   

	}
}
