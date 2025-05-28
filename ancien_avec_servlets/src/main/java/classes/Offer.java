package classes;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Offer {
	int id;
    private int departure;
    private int destination;
    private Timestamp date; // Change type to Timestamp
    private Time time;
    private int id_car;
    private int nbplaces;
    private float price;
    private String comment;
    private String state;
    
    
	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public Offer(int id, int departure, int destination, Timestamp date, Time time, int id_car, int nbplaces,
			float price, String comment, String state) {
		super();
		this.id = id;
		this.departure = departure;
		this.destination = destination;
		this.date = date;
		this.time = time;
		this.id_car = id_car;
		this.nbplaces = nbplaces;
		this.price = price;
		this.comment = comment;
		this.state = state;
	}




	public Offer(int departure, int destination, Timestamp date, Time time, int id_car, int nbplaces, float price,
			String comment, String state) {
		super();
		this.departure = departure;
		this.destination = destination;
		this.date = date;
		this.time = time;
		this.id_car = id_car;
		this.nbplaces = nbplaces;
		this.price = price;
		this.comment = comment;
		this.state = state;
	}
	

	public int getDeparture() {
		return departure;
	}


	public void setDeparture(int departure) {
		this.departure = departure;
	}


	public int getDestination() {
		return destination;
	}


	public void setDestination(int destination) {
		this.destination = destination;
	}


	public Timestamp getDate() {
		return date;
	}


	public void setDate(Timestamp date) {
		this.date = date;
	}


	public Time getTime() {
		return time;
	}


	public void setTime(Time time) {
		this.time = time;
	}


	public int getId_car() {
		return id_car;
	}


	public void setId_car(int id_car) {
		this.id_car = id_car;
	}


	public int getNbplaces() {
		return nbplaces;
	}


	public void setNbplaces(int nbplaces) {
		this.nbplaces = nbplaces;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Offer [departure=" + departure + ", destination=" + destination + ", date=" + date + ", time=" + time
				+ ", id_car=" + id_car + ", nbplaces=" + nbplaces + ", price=" + price + ", comment=" + comment
				+ ", state=" + state + "]";
	}
	
	
	
	
	
	
	
}
