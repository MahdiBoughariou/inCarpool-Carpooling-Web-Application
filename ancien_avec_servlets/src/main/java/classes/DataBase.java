package classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/covoiturage";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // SQL query to select all brands
    private static final String SELECT_ALL_BRANDS_QUERY = "SELECT id, name FROM brands";
    private static final String SELECT_ALL_VEHICLES_QUERY = "SELECT id, platenumber, id_brand, model FROM car WHERE id_user = ?";
    private static final String SELECT_ALL_CITIES_QUERY = "SELECT id, name FROM city";
    private static final String SELECT_UPCOMING_OFFERS_QUERY = 
    	    "SELECT id, departure, destination, date, time, id_car, nbplaces, price, comment " +
    	    "FROM offer " +
    	    "WHERE nbplaces > 0 AND date >= CURRENT_DATE() AND state = 'available' " +
    	    "ORDER BY date ASC, time ASC " +
    	    "LIMIT 10";


    private static final String SELECT_ALL_OFFERS_QUERY = "SELECT * FROM offer WHERE departure = ? AND destination = ? AND nbplaces >= ? AND (date = ? OR ? IS NULL) AND state = ? ";

    
    private static final String SELECT_USER_BY_CAR_QUERY = 
    	    "SELECT u.firstname, u.lastname, u.phone " +
    	    "FROM user u " +
    	    "JOIN car c ON u.id = c.id_user " +
    	    "WHERE c.id = ?";
    private static final String SELECT_CAR_INFO_QUERY = 
    		"SELECT platenumber,id_brand,model,state " +
            "FROM car " +
            "WHERE id=?";
    private static final String SELECT_BRAND_INFO_QUERY =
    		"SELECT name "
    		+ "FROM brands "
    		+ "WHERE id=?";
    
    private static final String SELECT_CITY_INFO_QUERY=
    		"SELECT name "
    		+ "FROM city "
    		+ "WHERE id=?";
    public static List<Brand> getAllBrands() {
        List<Brand> brands = new ArrayList<>();

        try (
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Create a statement
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL_BRANDS_QUERY);
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
        ) {
            // Iterate over the result set and populate the list of brands
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Brand brand = new Brand(id, name);
                brands.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return brands;
    }
    public static List<VehicleData> getAllVehicles(int id_user) {
    	List<VehicleData> vehicles = new ArrayList<>();
        try (
        	
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Create a statement
        	
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL_VEHICLES_QUERY);

        ) {
        	statement.setInt(1, id_user);
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            // Iterate over the result set and populate the list of brands
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_brand = resultSet.getInt("id_brand");
                String model = resultSet.getString("model");
                String platenumber = resultSet.getString("platenumber");
           
            // Create a statement
           String sql = "SELECT name FROM brands WHERE id = ?";
           PreparedStatement preparedStatement = conn.prepareStatement(sql);
           
           preparedStatement.setInt(1, id_brand);
           ResultSet brand_rs = preparedStatement.executeQuery();
           brand_rs.next();
           String brand_name = brand_rs.getString("name");
           Brand brand= new Brand(id_brand, brand_name);
           VehicleData vehicle = new VehicleData(id,platenumber, brand,model);
           vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(vehicles.toString()); 
        return vehicles;
    	
    	
    }
    
    public static List<City> getAllCities() {
        List<City> cities = new ArrayList<>();

        try (
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Create a statement
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL_CITIES_QUERY);
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
        ) {
            // Iterate over the result set and populate the list of brands
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                City city = new City(id, name);
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return cities;
    }
    
    public static List<City> getAllCitiesExcept(int diffid) {
        List<City> cities = new ArrayList<>();

        try (
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Create a statement
            PreparedStatement statement = conn.prepareStatement("SELECT id, name FROM city WHERE id <> ?");
        	
            
        ) {
        	statement.setInt(1, diffid);
            // Execute the query
        	ResultSet resultSet = statement.executeQuery();
            // Iterate over the result set and populate the list of brands
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                City city = new City(id, name);
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return cities;
    }
    
    public static List<Offer> getUpcomingOffers() {
        List<Offer> offers = new ArrayList<>();

        try (
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Create a statement
            PreparedStatement statement = conn.prepareStatement(SELECT_UPCOMING_OFFERS_QUERY);
        ) {
            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and populate the list of offers
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int departure = resultSet.getInt("departure");
                int destination = resultSet.getInt("destination");
                Timestamp date = resultSet.getTimestamp("date");
                Time time = resultSet.getTime("time");
                int idCar = resultSet.getInt("id_car");
                int availablePlaces = resultSet.getInt("nbplaces");
                float price = resultSet.getFloat("price");
                String comment = resultSet.getString("comment");
                String state = resultSet.getString("state");

                Offer offer = new Offer(id, departure, destination, date, time, idCar, availablePlaces, price, comment, state);
                offers.add(offer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return offers;
    }

    public static List<Offer> getFilteredOffers(int departure, int destination, int nbPlaces, Date date) {
        List<Offer> offers = new ArrayList<>();

        try (
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Create a statement
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL_OFFERS_QUERY);
        ) {
            // Set parameters for the prepared statement
          
                statement.setInt(1, departure);
                statement.setInt(2, destination);
                statement.setInt(3, nbPlaces);
                statement.setDate(4, date);
                statement.setDate(5, date);
                statement.setString(6, "available");

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and populate the list of offers
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int dep = resultSet.getInt("departure");
                int dest = resultSet.getInt("destination");
                Timestamp offerDate = resultSet.getTimestamp("date");
                Time time = resultSet.getTime("time");
                int idCar = resultSet.getInt("id_car");
                int availablePlaces = resultSet.getInt("nbplaces");
                float price = resultSet.getFloat("price");
                String comment = resultSet.getString("comment");
                String state = resultSet.getString("state");

                Offer offer = new Offer(id, dep, dest, offerDate, time, idCar, availablePlaces, price, comment, state);
                offers.add(offer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return offers;
    }
    
    public static User getUserInfoForCar(int carId) {
        User user = null;

        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = conn.prepareStatement(SELECT_USER_BY_CAR_QUERY);
        ) {
            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String phone = resultSet.getString("phone");
                user = new User(firstname, lastname, phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static Car getCarInfo(int carId) {
        Car car = null;

        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = conn.prepareStatement(SELECT_CAR_INFO_QUERY);
        ) {
            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String plateNumber = resultSet.getString("platenumber");
                int brandId = resultSet.getInt("id_brand");
                String model = resultSet.getString("model");
                String state = resultSet.getString("state");
                // Assuming you have a Brand class and a Car class
                // You might need to fetch brand name from another table using brandId
                // If you have a Brand class, you can create a Brand object here and set it for the car
                car = new Car(plateNumber, brandId, model, state); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(car.toString());
        return car;
    }

    public static String getBrandName(int brandId) {
        String brandName = null;

        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = conn.prepareStatement(SELECT_BRAND_INFO_QUERY);
        ) {
            statement.setInt(1, brandId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                brandName = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return brandName;
    }
    
    public static String getCityName(int cityId) {
        String cityName = null;

        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = conn.prepareStatement(SELECT_CITY_INFO_QUERY);
        ) {
            statement.setInt(1, cityId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cityName = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cityName;
    }


}
