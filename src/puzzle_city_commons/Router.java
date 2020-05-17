package puzzle_city_commons;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_dto.CityProvider;
import puzzle_city_model.CityModel;

import puzzle_city_model.ApiResponse;

public class Router {
	
	public static  String createCity(JSONObject city) {
		return CityProvider.create(city).toString();
	}

	public static String deleteCity(JSONObject city) {		
		return CityProvider.create(city).toString(); 
	}

	public static String updateCity(JSONObject city) {
		return CityProvider.update(city).toString(); 
	}

	public static String findAllCity() {

		JSONObject mapper = new JSONObject();
		try {
			CityProvider doituongCity = new CityProvider();
			return doituongCity.getAll().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static String findOneCityByID(int cityID) {

		try {
			return CityProvider.getByID(cityID).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// input {api:"CITYSave",body:{}}
	public static String router(JSONObject input) {
		String api;
		JSONObject body;
		try {
			api = input.get("api").toString();
			// Converting the Object to JSONString
	
			switch (api) {
			//CITY
			case "CITY_CREATE":
				return createCity((JSONObject) input.get("body"));
	
			case "CITY_UPDATE":
				return updateCity((JSONObject) input.get("body"));
	
			case "CITY_GET_ONE":
				body = input.getJSONObject("body");
				//System.out.println(body.getString("ID"));
				return findOneCityByID((int) body.getInt("ID"));
	
			case "CITY_FIND_ALL":
				return findAllCity();
			
	
			default:
				return  new ApiResponse(false, null, "Not found API").toString();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				return  new ApiResponse(false, null, e.getMessage()).toString();
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

				return null;
			}
		}
	}
}
