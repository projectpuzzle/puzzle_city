package puzzle_city_client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_client_model.ApiEnum;
import puzzle_city_client_model.SendPackage;
import puzzle_city_ui.CityAddNew;
import puzzle_city_ui.CityList;
import puzzle_city_ui.Dashboard;

public class Puzzle_main {
	static Client client;
	public static void main(String args[]) {
		client = new Client("172.31.249.155", 4000);
		client.start();
		//System.out.println("call view");
		CityList windowCityList  = new CityList(client);
		windowCityList.frame.setVisible(true);
		// new Puzzle_main().getCityData();
	}
	
	

	public void getCityData() {
		// TODO Auto-generated method stub
		client.setResponseData(null);
		SendPackage sendP = new SendPackage();
		sendP.setApi(ApiEnum.CITY_FIND_ALL);		
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
						JSONArray jArray = res.getJSONArray("data");
						if(jArray.length()>0) {
							System.out.println("select last city");
							int cID = jArray.getJSONObject(jArray.length()-1).getInt("ID");
							Dashboard ctDetail = new Dashboard(client, cID);
							ctDetail.frame.setVisible(true);
						}else{
							System.out.println("Add new");
							CityAddNew ctAdd =	new CityAddNew(client);
							ctAdd.frame.setVisible(true);
						};
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
	
}
