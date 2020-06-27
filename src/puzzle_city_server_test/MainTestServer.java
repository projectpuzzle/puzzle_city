package puzzle_city_server_test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_dto.TramwayProvider;

public class MainTestServer {
	public static void main(String[] args) {
		// test create tramwayprovider
		System.out.println("Start test 1");

		 try (FileReader reader = new FileReader("src/puzzle_city_server_test/tramway_create_data.json"))
        { 
			 String fileContent= "";
			 int i;    
	          while((i=reader.read())!=-1)   {	 
		          fileContent += (char)i;
	          } 
	          reader.close();    			 
	          JSONObject list = new JSONObject(fileContent);
	          

	      		TramwayProvider tramway = new TramwayProvider();
	          System.out.println( tramway.createAndUpdate(list).toString());
	          
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// end test 1

		// test 2
		System.out.println("Start test 2");
		
		// end test 2

		// test 3
		System.out.println("Start test 3");
		
		// end test 3
	}
}
