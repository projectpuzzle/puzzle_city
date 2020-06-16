package puzzle_city_dto;


import java.awt.Point;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_commons.RandomPoint;
import puzzle_city_model.ApiResponse;

public class TramwayProvider {

	JDBCConnection dbconn;
	static Connection conn;
	static Statement st;
	
	public TramwayProvider() {
		// TODO Auto-generated constructor stub
		System.out.println("create a connection");
		dbconn = new JDBCConnection();
		conn = dbconn.setConnection();
		System.out.println("create connection successfully");
	}
	

	//create
	public static ApiResponse createAndUpdate(JSONObject record) {
		try {
			String sql = "select * from tblbudgetstation where bIdCity = " + record.getInt("ID");
        	ResultSet rs = st.executeQuery(sql);        	

    		PreparedStatement pstmt  ;

            int ID =  record.getInt("ID");
            int Budget = record.getInt("Value");
            int ValStation = record.getInt("ValueStation");
            int NumberMaxStation = record.getInt("NumberMaxStation");
            int RadiusMin = record.getInt("Radius");
            //long date_of_birth = Date.valueOf(date).getTime();
    		if(rs.next() == false) {
    			//create
    			pstmt = conn.prepareStatement("INSERT INTO tblbudgetstation values (?, ?, ?, ?,? )");


                pstmt.setDouble(1, ID);
                pstmt.setInt(2, Budget);
                pstmt.setInt(3, ValStation);
                pstmt.setInt(4, NumberMaxStation);
                pstmt.setInt(5, RadiusMin);
    			
    		}else {
    			//update
    			pstmt = conn.prepareStatement("UPDATE tblbudgetstation SET bValue = ?, bValueStation = ?,bNumberMaxStation = ?,bRadius = ?  WHERE bIdCity = ?");

                pstmt.setInt(1, Budget);
                pstmt.setInt(2, ValStation);
                pstmt.setInt(3, NumberMaxStation);
                pstmt.setInt(4, RadiusMin);
                pstmt.setInt(5, ID);
    		} 
			
            
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
	
	//get byID 
	public static ApiResponse getTramWayByCityID(int id) {
		try {
			
        	String sql = "select * from tblcity JOIN tblbudgetstation on tblcity.cId = tblbudgetstation.bIdCity where tblcity.cId = " + id;
        //	System.out.println(sql);

    		st =  conn.createStatement();
        	ResultSet rs = st.executeQuery(sql);        	

    		JSONArray tramwayAll = new JSONArray();
    		if(rs.next() == false) {
        		return new ApiResponse(false, tramwayAll, "Not Found");
    		}else {
    			 do {
                	JSONObject resItem = new JSONObject();                	

                    resItem.put("ID", rs.getInt("bIdCity"));
                    resItem.put("ValueStation",  rs.getString("bValueStation") );
                    resItem.put("Value", rs.getFloat("bValue") );
                    resItem.put("NumberMaxStation", rs.getFloat("bNumberMaxStation") );  
                    resItem.put("Radius", rs.getFloat("bRadius") );             
                    resItem.put("Width", (int) rs.getDouble("cWidth") );     
                    resItem.put("Height",  (int) rs.getDouble("cHeight") );        
                    tramwayAll.put(resItem);                    
                }	while(rs.next());
    			 
    			 JSONObject data = new JSONObject();
    			 data.put("tramways", tramwayAll);
    			// get point
    			 data.put("points", getStationByCityID(id));
    			 
        		return new ApiResponse(true, data, "Success");
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
	

	//get byID 
	private static JSONArray getStationByCityID(int id) {
		try {
			
        	String sql = "select * from tblstation where sIdCity = " + id;
        //	System.out.println(sql);

    		st =  conn.createStatement();
        	ResultSet rs = st.executeQuery(sql);        	

    		JSONArray stationAll = new JSONArray();
    		if(rs.next() == false) {
        		return stationAll;
    		}else {
    			 do {
                	JSONObject resItem = new JSONObject();                	

                    resItem.put("ID", rs.getInt("sId"));
                    resItem.put("Name",  rs.getString("sName") );
                    resItem.put("IdCity", rs.getInt("sIdCity") );
                    resItem.put("Lat", rs.getInt("sLat") );
                    resItem.put("Long", rs.getInt("sLong") );
                    resItem.put("IdLine", rs.getInt("sIdLine") );
                    resItem.put("Position", rs.getInt("sPosition") );          
                    stationAll.put(resItem);                    
                }	while(rs.next());
        		return stationAll;
			}     	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;

		}
	}

	//create station
	public static ApiResponse createAndUpdateStation(Point[] record, int cityID) {
		try {
			//get cityID
			//delete all by city ID
            int ID =  cityID;
			String sql = "DELETE FROM tblstation where sIdCity = " + ID;
			//System.out.println(sql);
			
        	 st.executeUpdate(sql);       

        	// add new point
        	String query = "INSERT INTO tblstation values ";
        	for (int i = 0; i < record.length; i++) {
        		Point pointAdd = record[i];
                Double pointX = pointAdd.getX();
                Double pointY = pointAdd.getY();
                
                if(i>0) {
            		query = query + " , ";
                }

        		String queryItem = " (null,'point' , "+ID+" , "+pointX+" ,"+pointY+"  ,0 ,0 ) ";
        		query = query + queryItem;
			}

			System.out.println(query);
        	PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        	            
        	// add success
        	return new ApiResponse(true, null, "Add success");
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
	
	public static ApiResponse randomStation(int cityID) {
		//get width height
		try {
			String sql = "select * from tblcity JOIN tblbudgetstation on tblcity.cId = tblbudgetstation.bIdCity where tblcity.cId = " + cityID;
        	//System.out.println(sql);

    		st =  conn.createStatement();
        	ResultSet rs = st.executeQuery(sql);        	
        	if(rs.next() == false) {

            	return new ApiResponse(true, null, "Add success");
    		}else {
            	JSONObject resItem = new JSONObject();                	

            	int Height = (int)  rs.getDouble("cHeight") ;
            	int Width = (int) rs.getDouble("cWidth") ;
            	int Budget = rs.getInt("bValue") ;
            	int ValueStation = rs.getInt("bValueStation") ;
            	int maxPoint = ((int) Budget/ValueStation);
            	int r = rs.getInt("bRadius") ;
            	//random
            	RandomPoint newRandom = new RandomPoint(Width, Height, maxPoint, r, 0, 2*Math.PI);
            	Point[] points = newRandom.getListPoint(); 
            	if(points.length > 0) {
            		// update tblstation
            		createAndUpdateStation(points,cityID);            		
            	}
                //return list Station
            	return new ApiResponse(true, getTramWayByCityID(cityID), "Random success");
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
}