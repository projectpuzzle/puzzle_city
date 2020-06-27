package puzzle_city_ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.TableView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_client.Client;
import puzzle_city_client_model.ApiEnum;
import puzzle_city_client_model.VehiculeSensorTable;
import puzzle_city_model.ApiResponse;
import puzzle_city_client_model.SendPackage;

import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VehiculeSensorList {

	public JFrame frame;
	private JTable tblvehiculesensor;
	private JLabel lbtMess;
	private int State, ID;
	private JTextField txtAddress;
	public 	Client client ;//= new Client("127.0.0.1", 4000);
	
	List<Object[]> list=new ArrayList<>();

	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					System.out.println("main");
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
	 * @wbp.parser.constructor
	 */
	public VehiculeSensorList(Client socket) {
		client = socket;
		initialize();

		getVehiculeSensorData();
	}
	
	public VehiculeSensorList(JTable tblvehiculesensor) {

		this.tblvehiculesensor = tblvehiculesensor;
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
		//list city
		JPanel panel_cityinfo = new JPanel();
		panel_cityinfo.setBounds(10, 64, 644, 364);
		panel.add(panel_cityinfo);
		panel_cityinfo.setLayout(null);
		
		JLabel lblListVehiculeSensor = new JLabel("Vehicule sensor list");
		lblListVehiculeSensor.setHorizontalAlignment(SwingConstants.LEFT);
		lblListVehiculeSensor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListVehiculeSensor.setBounds(10, 11, 128, 27);
		panel_cityinfo.add(lblListVehiculeSensor);
		
		//Button cancel
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setSize(121, 23);
		btnCancel.setLocation(492, 294);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard db = new Dashboard();
				db.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(492, 294, 121, 23);
		panel_cityinfo.add(btnCancel);
		
		//button to page config
		JButton btnConfig = new JButton("Configurations");
		btnConfig.setBackground(Color.WHITE);
		btnConfig.setSize(121, 23);
		btnConfig.setLocation(257, 294);
		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigVehiculeSensor cvs = new ConfigVehiculeSensor(client);
				cvs.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(390, 294, 121, 23);
		panel_cityinfo.add(btnConfig);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateSensorInfo();
			}
		});
		btnUpdate.setBounds(284, 261, 89, 23);
		panel_cityinfo.add(btnUpdate);
		
		//table
		VehiculeSensorTable tv = new VehiculeSensorTable();
		//tblvehiculesensor = new JTable(tv);
		tblvehiculesensor = new JTable(new DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, },
				new String[] { "ID", "Address", "State" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		// 
        JScrollPane jsp = new JScrollPane(tblvehiculesensor);
        jsp.setBounds(20, 49, 593, 199);
		panel_cityinfo.add(jsp);

		JButton btnCreateButton = new JButton("Add new vehicule sensor");
		btnCreateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateVehiculeSensor ctAdd = new CreateVehiculeSensor(client);
				ctAdd.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCreateButton.setBackground(Color.WHITE);
		btnCreateButton.setBounds(20, 295, 187, 23);
		panel_cityinfo.add(btnCreateButton);
		

//		//set data  for table	
		// add vehicule sensor
		JPanel panel_cityinfo_1 = new JPanel();
		panel_cityinfo_1.setLayout(null);
		panel_cityinfo_1.setBounds(10, 64, 644, 364);
		panel.add(panel_cityinfo_1);
		
		JLabel lblListCity_1 = new JLabel("Vehicule sensor list");
		lblListCity_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblListCity_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListCity_1.setBounds(10, 11, 99, 27);
		panel_cityinfo_1.add(lblListCity_1);
		
		JLabel lblNewLabel = new JLabel("Vehicule Sensor Manager System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(315, 13, 307, 27);
		panel.add(lblNewLabel);
		
	}
	
	private void getVehiculeSensorData() {
		// TODO Auto-generated method stub		
		client.setResponseData(null);
		SendPackage sendP = new SendPackage();
		sendP.setApi(ApiEnum.VEHICULESENSOR_FIND_ALL);		
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
						bindDataToTable(res.getJSONArray("data"));
					}else {						
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
		String[] columnNames = {
				  "ID",  "Address", "State"
			    };
		model.setColumnIdentifiers(columnNames);

		ArrayList arrRows = new ArrayList();  
		for (int i = 0; i < jArray.length(); i++) {
            JSONObject jb;
			try {
				jb = jArray.getJSONObject(i);
			
				Object[] rowData = {
					jb.getInt("ID"),
					jb.getString("Address"),
					jb.getBoolean("State")
				};
	    		
				model.addRow(rowData);
				arrRows.clear();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	

		tblvehiculesensor.setModel(model);
		
	}
	
	public static ApiResponse UpdateVehiculeSensor(JSONObject record) {
		try {		
			String sql = "select * from tblvehiculesensor where ID = " + record.getInt("ID");

				System.out.println(sql);
				ResultSet rs = st.executeQuery(sql);        	
	
	    		PreparedStatement pstmt  ;
                int ID =  record.getInt("ID");
                String Address = record.getString("Address");
                boolean State = record.getBoolean("State");
	    			//update
	    		pstmt = conn.prepareStatement("UPDATE tblvehiculesensor SET Address = ?, State = ?  WHERE ID = ?");
		        pstmt.setInt(1, ID);
	            pstmt.setString(2, Address);
	            pstmt.setBoolean(3, State);
				
	            
	            pstmt.executeUpdate();
	            // random Station
	            randomStation(ID);
	        	// add success
	        	return new ApiResponse(true, null, "Create success");

   
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				return new ApiResponse(false, null, e.getMessage());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}

		}
		
	}
}