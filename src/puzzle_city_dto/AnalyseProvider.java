package puzzle_city_dto;

import java.io.*;
import java.awt.print.Printable;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.text.html.HTMLEditorKit.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_model.AlertModel;
import puzzle_city_model.ApiResponse;
import puzzle_city_model.SensorQualityAirModel;

public class AnalyseProvider {

	JDBCConnection dbconn;
	static Connection conn;
	static Statement st;

	public AnalyseProvider() {
		// TODO Auto-generated constructor stub
		System.out.println("create a connection to db");
		dbconn = new JDBCConnection();
		conn = dbconn.setConnection();
		System.out.println("create connection successfully");
	}

	public static int getNumberOfSensor() {
		try {

			Statement st = conn.createStatement(); 
			String sql = "select count(*) from tblcity ";
			ResultSet rs = st.executeQuery(sql);

			System.out.println("Number of sensors : " + rs.getInt(1));
			return rs.getInt(1);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
	
	//get byID 
	public static ApiResponse getInfoAnalyse(int id) {
		try {
			String sql =  "select (SELECT COUNT(*) AS CountStation from tblstation WHERE sIdCity ="+ id +") AS CountStation"
		//+		",(SELECT COUNT(*) AS CountSensor from tblsensorair where isActivated=1) AS CountSensor"
					;
			st =  conn.createStatement();
			ResultSet rs = st.executeQuery(sql);        	

			JSONArray cityAll = new JSONArray();
			if(rs.next() == false) {
				return new ApiResponse(false, cityAll, "Not Found");
			}else {
				do {
					JSONObject resItem = new JSONObject();                	

					resItem.put("CountStation", rs.getInt("CountStation"));
			//		resItem.put("CountSensor",  rs.getInt("CountSensor") );
					                   
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
}
