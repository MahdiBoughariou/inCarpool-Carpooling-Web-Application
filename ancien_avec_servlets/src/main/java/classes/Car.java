package classes;

public class Car {
	int id;
	int id_user;
	String platenumber;
	int brand_id;
	String model;
	String state;
	
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Car(String platenumber, int brand_id, String model, String state) {
		super();
		this.platenumber = platenumber;
		this.brand_id = brand_id;
		this.model = model;
		this.state = state;
	}



	public Car(int id, int id_user, String platenumber, int brand_id, String model, String state) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.platenumber = platenumber;
		this.brand_id = brand_id;
		this.model = model;
		this.state = state;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getId_user() {
		return id_user;
	}



	public void setId_user(int id_user) {
		this.id_user = id_user;
	}



	public String getPlatenumber() {
		return platenumber;
	}



	public void setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
	}



	public int getBrand_id() {
		return brand_id;
	}



	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	@Override
	public String toString() {
		return "Car [id=" + id + ", id_user=" + id_user + ", platenumber=" + platenumber + ", brand_id=" + brand_id
				+ ", model=" + model + ", state=" + state + "]";
	}
	
	
	

}
