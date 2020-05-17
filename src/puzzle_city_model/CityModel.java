package puzzle_city_model;

public class CityModel {
	int ID;
    String name ;
    Float height ;
    Float width;
    Float centerLat ;
    Float centerLong ;
    int mapZoom   ;
	
    
    public CityModel(int ID,String name, Float Height, Float Width, Float CenterLat, Float CenterLong, int MapZoom) {
		// TODO Auto-generated constructor stub
    	super();
    	this.ID = ID;
    	this.name = name;
    	this.height = Height;
    	this.width = Width;
    	this.centerLat = CenterLat;
    	this.centerLong = CenterLong;
    	this.mapZoom = MapZoom;
    	
	}
    
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public Float getWidth() {
		return width;
	}
	public void setWidth(Float width) {
		this.width = width;
	}
	public Float getCenterLat() {
		return centerLat;
	}
	public void setCenterLat(Float centerLat) {
		this.centerLat = centerLat;
	}
	public Float getCenterLong() {
		return centerLong;
	}
	public void setCenterLong(Float centerLong) {
		this.centerLong = centerLong;
	}
	public int getMapZoom() {
		return mapZoom;
	}
	public void setMapZoom(int mapZoom) {
		this.mapZoom = mapZoom;
	}
}
