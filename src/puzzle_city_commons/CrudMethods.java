package puzzle_city_commons;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import puzzle_city_connectionPool.DataSource;
import puzzle_city_connectionPool.Test;

public class CrudMethods {
	
	public static  String saveTest(Test test) {
		try {
			DataSource.addTest(test.getClient(), test.getServer(), test.getDB());
			return "saved successfully";
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public static String deleteTest(Test test) {

		DataSource.deleteTestByDB(test.getDB());
		return "deleted successfully";

	}

	public static String updateTest(Test test) {

		DataSource.updateTest(test.getClient(), test.getServer(), test.getDB());
		return "updated successfully";

	}

	public static String findAll() {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(DataSource.findAll());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String crud(Test test) {
		String crudEnum = test.getCrud().toString();

		// Converting the Object to JSONString

		switch (crudEnum) {

		case "SAVE":
			return saveTest(test);

		case "UPDATE":
			return updateTest(test);

		case "DELETE":
			return deleteTest(test);

		case "FIND_ALL":
			return findAll();

		default:
			return null;
		}
	}
}
