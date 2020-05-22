package puzzle_city_commons;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import puzzle_city_dto.AnalyseProvider;
import puzzle_city_dto.CityProvider;
import puzzle_city_model.CityModel;
import puzzle_city_dto.SensorQualityAirProvider;
import puzzle_city_model.ApiResponse;

public class Router {

	public static String createCity(JSONObject city) {
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

	// capteur de qualit� d'air
	public static String createSensorAir(JSONObject sensorAir) {
		return SensorQualityAirProvider.create(sensorAir).toString();
	}

	public static String updateSensorAir(JSONObject sensorAir) {
		return SensorQualityAirProvider.update(sensorAir).toString();
	}

	public static String findAllSensorAir() {

		JSONObject mapper = new JSONObject();
		try {
			SensorQualityAirProvider sensorAir = new SensorQualityAirProvider();
			return sensorAir.getAll().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String findOneSensorAirById(int sensorAirID) {

		try {
			return SensorQualityAirProvider.getByID(sensorAirID).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void deleteSensorQualityAirById(int id, int alert_id) {
		SensorQualityAirProvider.deleteSensorQualityAirById(id, alert_id);
	}

	/*
	// Vehicule sensor
	public static String createVehiculeSensor(JSONObject VehiculeSensor) {
		return VehiculeSensorProvider.create(VehiculeSensor).toString();
	}

	public static String updateVehiculeSensor(JSONObject VehiculeSensor) {
		return VehiculeSensorProvider.update(VehiculeSensor).toString();
	}

	public static String findAllVehiculeSensor() {

		JSONObject mapper = new JSONObject();
		try {
			VehiculeSensorProvider VehiculeSensor = new VehiculeSensorProvider();
			return VehiculeSensor.getAll().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String findOneVehiculeSensorById(int VehiculeSensorID) {

		try {
			return VehiculeSensorProvider.getByID(VehiculeSensorID).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void deleteVehiculeSensorById(int ID, int alert_ID) {
		VehiculeSensorProvider.deleteVehiculeSensorById(ID, alert_ID);
	}
*/
	// analyse
	public static int CountAirSensor() {
		return AnalyseProvider.getNumberOfSensor();
	}

	// input {api:"CITYSave",body:{}}
	public static String router(JSONObject input) {
		String api;
		JSONObject body;
		try {
			api = input.get("api").toString();
			// Converting the Object to JSONString

			switch (api) {
			// CITY
			case "CITY_CREATE":
				return createCity((JSONObject) input.get("body"));

			case "CITY_UPDATE":
				return updateCity((JSONObject) input.get("body"));

			case "CITY_GET_ONE":
				body = input.getJSONObject("body");
				// System.out.println(body.getString("ID"));
				return findOneCityByID((int) body.getInt("ID"));

			case "CITY_FIND_ALL":
				return findAllCity();
			case "SENSORAIR_CREATE":
				return createSensorAir((JSONObject) input.get("body"));

			case "SENSORAIR_UPDATE":
				return updateSensorAir((JSONObject) input.get("body"));
			case "SENSORAIR_FIND_ONE":
				body = input.getJSONObject("body");

				return findOneSensorAirById((int) body.getInt("ID"));

			case "SENSORAIR_FIND_ALL":
				return findAllSensorAir();

			case "SENSORAIR_DELETE":
				body = input.getJSONObject("body");
				deleteSensorQualityAirById((int) body.getInt("id"), (int) body.getInt("alert_id"));
				return "DELETED";

			case "ANALYSE_AIR_SENSOR_NUMBER":
				return String.valueOf(CountAirSensor());

			default:
				return new ApiResponse(false, null, "Not found API").toString();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				return new ApiResponse(false, null, e.getMessage()).toString();
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

				return null;
			}
		}

	}


	public static String route() {
		String s = String.valueOf(CountAirSensor());
		return s;
	}
}

		
