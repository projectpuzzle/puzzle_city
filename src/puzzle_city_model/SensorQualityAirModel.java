package puzzle_city_model;

public class SensorQualityAirModel {

	private int id;
	private String address;

	public SensorQualityAirModel() {
		super();
	}

	public SensorQualityAirModel(int id, String address) {
		super();
		this.id = id;
		this.address = address;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
