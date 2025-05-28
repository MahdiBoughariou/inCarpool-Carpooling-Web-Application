package classes;

public class Brand {
	
	private int id;
	private String name;
	
	
	
	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Brand(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Brand(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
