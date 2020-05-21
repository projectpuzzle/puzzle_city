package puzzle_city_model;

public class VehiculeSensorModel {

	private int ID;
	private String Address;
 
	private AlertModel alerteModel;



/*	public VehiculeSensorModel(int ID, String Address) {
		super();
		this.ID = ID;
		this.Address = Address;
	
	}
	*/

	public VehiculeSensorModel(int ID, String Address, AlertModel alerteModel) {
		super();
		this.ID = ID;
		this.Address = Address;
		this.alerteModel = alerteModel;
	}

	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = ID;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public AlertModel getAlerteModel() {
		return alerteModel;
	}

	public void setAlerteModel(AlertModel alerteModel) {
		this.alerteModel = alerteModel;
	}


}