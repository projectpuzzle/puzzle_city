package puzzle_city_dto;


import java.awt.print.Printable;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.text.html.HTMLEditorKit.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_model.ApiResponse;
import puzzle_city_model.SensorQualityAirModel;

public class SensorQualityAirProvider {

	 	JDBCConnection dbconn;
	 	static Connection conn;
	 	static Statement st;
	 	
	 	public SensorQualityAirProvider() {
	 		// TODO Auto-generated constructor stub
	 		dbconn = new JDBCConnection();
	 		conn = dbconn.setConnection();
	 	}
	 	
	 	//get all 
	 	public static puzzle_city_model.ApiResponse getAll() {
	 		try {
	 			
	         	st =  conn.createStatement();
	         	String sql = "select * from tblsensorair";
	         	ResultSet rs = st.executeQuery(sql);
	         	
	         	 ArrayList<SensorQualityAirModel> airAll = new ArrayList<SensorQualityAirModel>();

	             while(rs.next()){
	             	JSONObject resItem = new JSONObject();           	
	                 
	               int ID = rs.getInt("Id");
	               String address= rs.getString("address");
	               //boolean isOpen = rs.getBoolean("isOpen");
	                 
	                 
	                 
	                 airAll.add(new SensorQualityAirModel(ID,address));
	                 
	             }
	             ApiResponse ret = new ApiResponse(true, airAll, "Success");
	             System.out.println("Tra du lieu:"+ret.toString());
	     		return ret;
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

	 	//get byID 
	 	public static ApiResponse getByID(int id) {
	 		try {
	 			
	         	st =  conn.createStatement();
	         	String sql = "select * from tblsensorair where Id = ? ";
	         	ResultSet rs = st.executeQuery(sql);        	

	     		JSONArray sensorAir = new JSONArray();
	     		if(rs.next() == false) {
	         		return new ApiResponse(false, sensorAir , "Not Found");
	     		}else {
	     			 do {
	                 	JSONObject resItem = new JSONObject();                	

	                     resItem.put("ID", rs.getLong("id"));
	                     resItem.put("address",  rs.getString("address") );
//	                     resItem.put("isOpen",  rs.getBoolean("isOpen") );

	                                   
	                    sensorAir.put(resItem);                    
	                 }	while(rs.next());
	         		return new ApiResponse(true, sensorAir, "Success");
	 			}     	
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

	 	//create
	 	public static ApiResponse create(JSONObject record) {
	 		try {
	 			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tblsensorair values (null,?)");
	 	         					
	             String address =  record.getString("address");
	          //  Boolean isOpen = record.getBoolean("isOpen");
	             //long date_of_birth = Date.valueOf(date).getTime();
	             pstmt.setString(1, address);
	             //pstmt.setBoolean(2, true);
	            
	             pstmt.executeUpdate();
	             
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

	 	//update
	 	public static ApiResponse update(JSONObject record) {
	 		try {        	
	             
	 			PreparedStatement pstmt = conn.prepareStatement("UPDATE tblsensorair SET address = ? ,isOpen = ?  WHERE Id = ?");
	 			System.out.println(record);
	             int ID =  record.getInt("id");

	             String address =  record.getString("address");
//                 Boolean isOpen = record.getBoolean("isOpen");	          
	             //long date_of_birth = Date.valueOf(date).getTime();
	             pstmt.setString(1, address);
//	             pstmt.setBoolean(2, isOpen);
	          
	             pstmt.setInt(3, ID);
	             
	             pstmt.executeUpdate();
	             
	         	// add success
	         	return new ApiResponse(true, null, "Update success");
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
	 	public static void deleteSensorQualityAirById(int Id) {
			try {

				PreparedStatement pt = conn.prepareStatement("delete from tblsensorair where id like ?");
				pt.setInt(1, Id);
				pt.execute();
			} catch (SQLException ex) {
				System.out.println("error " + ex.getMessage());
			}

		}
}
