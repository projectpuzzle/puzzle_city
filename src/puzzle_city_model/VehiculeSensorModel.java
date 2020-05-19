package puzzle_city_model;

public class VehiculeSensorModel {

	private int ID;
	private String Address;

	public VehiculeSensorModel() {
		super();
	}

	public VehiculeSensorModel(int ID, String Address) {
		super();
		this.ID = ID;
		this.Address = Address;

	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

}
