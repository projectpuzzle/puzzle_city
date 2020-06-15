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

public class ReglementationFrance {

	public JFrame frame;
	private JTable tblalerthistory;
	public Client client;// = new Client("127.0.0.1", 4000);
	private int id;
	private String address;
	private int no2;
	private int pm10;
	private int o3;
	private boolean alert;
	private int alert_id;
	
	List<Object[]> list=new ArrayList<>();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					System.out.println("vao main");
//					
//					CityList window = new CityList();
//					window.frame.setVisible(true);
//
//					System.out.println("Load data");
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.constructor
	 */
	public ReglementationFrance(Client socket,int id, String address, int no2, int pm10, int o3, boolean alert,
			int alert_id) {
		this.id = id;
		this.address = address;
		this.no2 = no2;
		this.pm10 = pm10;
		this.o3 = o3;
		this.alert = alert;
		this.alert_id = alert_id;
		client = socket;
		initialize();

//		getSensorAirData();
	}

	public ReglementationFrance(JTable tblalerthistory) {

		this.tblalerthistory = tblalerthistory;
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

		JLabel lblListCity = new JLabel("\r\nThe main values \u200B\u200Bmentioned in French regulations (Threshold) :\r\n\r\n");
		lblListCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblListCity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblListCity.setBounds(68, 27, 512, 27);
		panel_cityinfo.add(lblListCity);

		tblalerthistory = new JTable(new DefaultTableModel(
			new Object[][] {
				{"quality goals", "A.A* : 30 \u00B5g/m\u00B3.", null, "A.A* : 40 \u00B5g/m\u00B3."},
				{"information threshold", null, null, null},
				{"alert threshold", null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"", "PM10", "O3", "NO2"
			}
		));
		tblalerthistory.setForeground(new Color(25, 25, 112));

		tblalerthistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tblalerthistory.getSelectedRow();
				int id = Integer.parseInt(list.get(row)[0].toString());
				String address = list.get(row)[1].toString();
				int no2 = (int)list.get(row)[2];
				int pm10 =(int) list.get(row)[3];
				int o3 = (int)list.get(row)[4];
				boolean alert = (boolean)list.get(row)[5];
				int alertId=(int)list.get(row)[7];
			//	System.out.println("ppp" + globalModel.getColumnCount());
				ConfigSensorAir cS = new ConfigSensorAir(client, id, address, no2, pm10, o3, alert,alertId);
				cS.frame.setVisible(true);
				frame.dispose();

			}
		});

		// tao scrollpane roi cho table chui vao thi table thi tieu de moi hien thi
		JScrollPane jsp = new JScrollPane(tblalerthistory);
		jsp.setBounds(80, 66, 500, 220);
		panel_cityinfo.add(jsp);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigSensorAir cS = new ConfigSensorAir(client, id, address, no2, pm10, o3, alert,alert_id);
				cS.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(257, 302, 121, 23);
		panel_cityinfo.add(btnCancel);

		JLabel lblNewLabel = new JLabel("Air Quality Sensors Details\r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(159, 11, 337, 27);
		panel.add(lblNewLabel);

	}

//	private void getSensorAirData() {
//		// TODO Auto-generated method stub
//		client.setResponseData(null);
//		SendPackage sendP = new SendPackage();
//		sendP.setApi(ApiEnum.SENSORAIR_FIND_ALL);
//		client.setSendP(sendP);
//		JSONObject res = null;
//		while (res == null) {
//
//			res = client.getResponseData();
//			System.out.println("waiting:" + res);
//			if (res != null) {
//
//				// System.out.println("waiting:" + res);
//
//				// if success true - get data bind to table
//				System.out.println(res.toString());
//				boolean sMess;
//				try {
//					sMess = res.getBoolean("success");
//					if (sMess) {
//						bindDataToTable(res.getJSONArray("data"));
//					} else {
//					}
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		//
//
//		client.setResponseData(null);
//	}
//
//	private void bindDataToTable(JSONArray jArray) {
//		//globalModel = new DefaultTableModel();
//		DefaultTableModel model = new DefaultTableModel();
//		String[] columnNames = { "Address",  "Alert status" ,"Alert time"};
//		model.setColumnIdentifiers(columnNames);
//
//		ArrayList arrRows = new ArrayList();
//		for (int i = 0; i < jArray.length(); i++) {
//			JSONObject jb;
//			try {
//				jb = jArray.getJSONObject(i);
//
//				JSONObject alerteModel = jb.getJSONObject("alerteModel");
//				boolean isAlerted=alerteModel.getBoolean("alert");
//				Object[] rowData = { jb.getString("address"), isAlerted?"Alerted":"Not alerted", isAlerted?alerteModel.getString("date"):"---"};
//							
//				Object[] globalrowData = { jb.getInt("id"), jb.getString("address"), jb.getInt("no2"), jb.getInt("pm10"),
//									jb.getInt("o3"), alerteModel.getBoolean("alert"), alerteModel.has("date")?alerteModel.getString("date"):"---",	alerteModel.get("id")		
//               
//				};
//              //globalModel.addRow(globalrowData);
//				list.add(globalrowData);
//				model.addRow(rowData);
//				arrRows.clear();
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		tblalerthistory.setModel(model);
////		tblalerthistory.getColumn("Delete").setCellRenderer(new ButtonRenderer());
////		tblalerthistory.getColumn("Delete").setCellEditor(
////		        new ButtonEditor(new JCheckBox()));
////		tblalerthistory.setVisible(true);   
//
//	}
}