package puzzle_city_dto;

import java.awt.print.Printable;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.text.html.HTMLEditorKit.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_model.ApiResponse;
import puzzle_city_model.VehiculeSensorModel;

public class VehiculeSensorProvider {

	JDBCConnection dbconn;
	static Connection conn;
	static Statement st;

	public VehiculeSensorProvider() {
		// TODO Auto-generated constructor stub
		dbconn = new JDBCConnection();
		conn = dbconn.setConnection();
	}

	// get all
	public static puzzle_city_model.ApiResponse getAll() {
		try {

			st = conn.createStatement();
			String sql = "select * from tblvehiculesensor";
			ResultSet rs = st.executeQuery(sql);

			ArrayList<VehiculeSensorModel> vehiculeAll = new ArrayList<VehiculeSensorModel>();

			while (rs.next()) {
				JSONObject resItem = new JSONObject();

				int ID = rs.getInt("ID");
				String Address = rs.getString("Address");
				// boolean isOpen = rs.getBoolean("isOpen");

				vehiculeAll.add(new VehiculeSensorModel(ID, Address));

			}
			ApiResponse ret = new ApiResponse(true, vehiculeAll, "Success");
			System.out.println("Tra du lieu:" + ret.toString());
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

	// get byID !
	public static ApiResponse getByID(int ID) {
		try {

			st = conn.createStatement();
			String sql = "select * from tblvehiculesensor where ID = ? ";
			ResultSet rs = st.executeQuery(sql);

			JSONArray VehiculeSensor = new JSONArray();
			if (rs.next() == false) {
				return new ApiResponse(false, VehiculeSensor, "Not Found");
			} else {
				do {
					JSONObject resItem = new JSONObject();

					resItem.put("ID", rs.getLong("ID"));
					resItem.put("Address", rs.getString("Address"));
//	                     resItem.put("isOpen",  rs.getBoolean("isOpen") );

					VehiculeSensor.put(resItem);
				} while (rs.next());
				return new ApiResponse(true, VehiculeSensor, "Success");
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

	// create
	public static ApiResponse create(JSONObject record) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tblvehiculesensor values (null,?)");

			String Address = record.getString("Address");
			// Boolean isOpen = record.getBoolean("isOpen");
			pstmt.setString(1, Address);
			// pstmt.setBoolean(2, true);

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

	// update
	public static ApiResponse update(JSONObject record) {
		try {

			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE tblvehiculesensor SET Address = ? ,isOpen = ?  WHERE ID = ?");
			System.out.println(record);
			int ID = record.getInt("ID");

			String Address = record.getString("Address");
//                 Boolean isOpen = record.getBoolean("isOpen");	          
			// long date_of_birth = Date.valueOf(date).getTime();
			pstmt.setString(1, Address);
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

	public static void deleteVehiculeSensorByID(int ID) {
		try {

			PreparedStatement pt = conn.prepareStatement("delete from tblvehiculesensor where ID like ?");
			pt.setInt(1, ID);
			pt.execute();
		} catch (SQLException ex) {
			System.out.println("error " + ex.getMessage());
		}

	}
}