package puzzle_city_dto;

import java.sql.*;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_model.ApiResponse;

public class CityProvider {

	JDBCConnection dbconn;
	static Connection conn;
	static Statement st;
	
	public CityProvider() {
		// TODO Auto-generated constructor stub
		System.out.println("create a connection to db");
		dbconn = new JDBCConnection();
		conn = dbconn.setConnection();
		System.out.println("create connection successfully");
	}
	
	//get all 
	public static ApiResponse getAll() {
		try {
			
        	st =  conn.createStatement();
        	String sql = "select * from tblcity";
        	ResultSet rs = st.executeQuery(sql);
        	
    		JSONArray cityAll = new JSONArray();
            while(rs.next()){
            	JSONObject resItem = new JSONObject();           	
            	 resItem.put("ID", rs.getInt("cId"));  
            	 
            	 resItem.put("Name", rs.getString("cName"));        	
            	 resItem.put("Height", rs.getFloat("cHeight"));        	
            	 resItem.put("Width", rs.getFloat("cWidth"));        	
            	 resItem.put("CenterLat", rs.getFloat("cCenterLat"));	
            	 resItem.put("CenterLong", rs.getFloat("cCenterLong"));	
            	 resItem.put("MapZoom", rs.getInt("cMapZoom"));
                
                
                cityAll.put(resItem);
                
            }
            ApiResponse resturn = new ApiResponse(true, cityAll, "Success");
            System.out.println("return data:"+resturn.toString());
    		return resturn;
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
        	String sql = "select * from tblcity where cId = " + id;
        	ResultSet rs = st.executeQuery(sql);        	

    		JSONArray cityAll = new JSONArray();
    		if(rs.next() == false) {
        		return new ApiResponse(false, cityAll, "Not Found");
    		}else {
    			 do {
                	JSONObject resItem = new JSONObject();                	

                    resItem.put("ID", rs.getInt("cId"));
                    resItem.put("Name",  rs.getString("cName") );
                    resItem.put("Height", rs.getFloat("cHeight") );
                    resItem.put("Width", rs.getFloat("cWidth") );
                    resItem.put("CenterLat", rs.getFloat("cCenterLat") );
                    resItem.put("CenterLong", rs.getFloat("cCenterLong") );
                    resItem.put("MapZoom",  rs.getInt("cMapZoom") );                    
                    cityAll.put(resItem);                    
                }	while(rs.next());
        		return new ApiResponse(true, cityAll, "Success");
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
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tblcity values (null, ?, ?, ?, ?, ?, ?  )");
	         					
            String Name =  record.getString("name");
            Double Height = record.getDouble("height");
            Double Width = record.getDouble("width");
            Double CenterLat = record.getDouble("centerLat");
            Double CenterLong = record.getDouble("centerLong");
            int MapZoom = record.getInt("mapZoom");
            //long date_of_birth = Date.valueOf(date).getTime();
            pstmt.setString(1, Name);
            pstmt.setDouble(2, Height);
            pstmt.setDouble(3, Width);
            pstmt.setDouble(4, CenterLat);
            pstmt.setDouble(5, CenterLong);
            pstmt.setInt(6, MapZoom);
            
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
            
			PreparedStatement pstmt = conn.prepareStatement("UPDATE tblcity SET cName = ?, cHeight = ?,cWidth = ?, cCenterLat = ? ,cCenterLong = ? ,cMapZoom =?  WHERE cId = ?");
			System.out.println(record);
            int ID =  record.getInt("ID");

            String Name =  record.getString("name");
            Double Height = record.getDouble("height");
            Double Width = record.getDouble("width");
            Double CenterLat = record.getDouble("centerLat");
            Double CenterLong = record.getDouble("centerLong");
            int MapZoom = record.getInt("mapZoom");
            //long date_of_birth = Date.valueOf(date).getTime();
            pstmt.setString(1, Name);
            pstmt.setDouble(2, Height);
            pstmt.setDouble(3, Width);
            pstmt.setDouble(4, CenterLat);
            pstmt.setDouble(5, CenterLong);
            pstmt.setInt(6, MapZoom);
            pstmt.setInt(7, ID);
            
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
	public static ApiResponse delete() {
		try {
			st =  conn.createStatement();
        	String sql = "DELETE FROM tblcity WHERE 1";
        	st.executeUpdate(sql);  
        	return new ApiResponse (true, null, "Delete success");
		}catch (Exception e) {
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
	//main for test
}
