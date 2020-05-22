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

	public AnalyseProvider() {
		// TODO Auto-generated constructor stub
		dbconn = new JDBCConnection();
		conn = dbconn.setConnection();
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
}
