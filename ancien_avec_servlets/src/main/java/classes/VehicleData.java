package classes;

public class VehicleData {
	private int id;
    private String licensePlate;
    private Brand brand;
    private String model;
    private String state;
    
    
    

	public VehicleData() {
	    super();
	    this.brand = new Brand(); // Initialize brand object
	}



	public VehicleData(int id,String licensePlate, Brand brand, String model) {
	    super();
	    this.id = id;
	    this.licensePlate= licensePlate;
	    this.brand = new Brand(); // Initialize brand object
	    this.brand.setId(brand.getId());
	    this.brand.setName(brand.getName());
	    this.model = model;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand.setId(brand.getId());
		this.brand.setName(brand.getName());
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
		return "VehicleData [id=" + id + ", licensePlate=" + licensePlate + ", brand=" + brand.getId() + ", model=" + model
				+ ", state=" + state + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

    
    
}
