package puzzle_city_client;

import puzzle_city_ui.CityList;

public class Puzzle_main {

	public static void main(String args[]) {

		Client client = new Client("127.0.0.1", 4000);
		client.start();
		//System.out.println("call view");
		CityList windowCityList  = new CityList(client);
		windowCityList.frame.setVisible(true);

	}
}