package puzzle_city_model;

import java.util.Date;

public class AlertHistoryModel {

	private AlertModel alertModel;
	private Date dateAlert;
	

	public AlertHistoryModel(Date dateAlert) {
		super();
		
		this.dateAlert = dateAlert;
		
	}

	
	public AlertModel getAlertModel() {
		return alertModel;
	}

	public void setAlertModel(AlertModel alertModel) {
		this.alertModel = alertModel;
	}

	public Date getDateAlert() {
		return dateAlert;
	}

	public void setDateAlert(Date dateAlert) {
		this.dateAlert = dateAlert;
	}



}
