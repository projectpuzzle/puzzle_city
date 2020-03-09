package puzzle_city_connectionPool;


public class TestFile {


		public static void main(String[] args) throws ClassNotFoundException {
			DataSource ds = new DataSource();
			//ds.showTest();
			//ds.addTest("Hamza", "jalit" , 10);
			//ds.deleteTestByserver("Hamza");
			ds.updateTest("afak", "omar", 60);
			
		}
	}
