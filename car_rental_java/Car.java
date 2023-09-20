import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Car {
    public int idCar;
    public int idAgency;
    public String brand;
    public String model;
    public int year;
    public String color;
    public double price;
    public String availabilityStatus;
    public String image;

    public Car(int idCar, int idAgency, String brand, String model, int year, String color, double price, String availabilityStatus, String image) {
        this.idCar = idCar;
        this.idAgency = idAgency;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
        this.availabilityStatus = availabilityStatus;
        this.image = image;
    }
    public Car() {
        // Default constructor
    }
    public static List<Car> fetchCarsByAgency(int idAgency) {
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
        String user = "ag1";
        String passwd = "jamal123";
        List<Car> cars = new ArrayList<Car>();

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection(url, user, passwd);

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM CAR WHERE ID_AGENCY = ?");
            pstmt.setInt(1, idAgency);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int idCar = rs.getInt("ID_CAR");
                String brand = rs.getString("BRAND");
                String model = rs.getString("MODEL");
                int year = rs.getInt("YEAR");
                String color = rs.getString("COLOR");
                double price = rs.getDouble("PRICE");
                String availabilityStatus = rs.getString("AVAILABILITY_STATUS");
                String image = rs.getString("IMAGE");

                Car car = new Car(idCar, idAgency, brand, model, year, color, price, availabilityStatus, image);
                cars.add(car);
            }

            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return cars;
    }

    public void save() {
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
        String user = "ag1";
        String passwd = "jamal123";

        try {
        	 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
             Connection con = DriverManager.getConnection(url, user, passwd);
            String query = "INSERT INTO car (ID_CAR, ID_AGENCY, BRAND, MODEL, YEAR, COLOR, PRICE, AVAILABILITY_STATUS, IMAGE) "
                    + "VALUES (sqNumCar.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idAgency);
            pstmt.setString(2, brand);
            pstmt.setString(3, model);
            pstmt.setInt(4, year);
            pstmt.setString(5, color);
            pstmt.setDouble(6, price);
            pstmt.setString(7, availabilityStatus);
            pstmt.setString(8, image);

            pstmt.executeUpdate();
            con.commit();
            pstmt.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    public void update(String attributeName, Object attributeValue) {
    	
    	if (attributeValue.equals("null") | attributeValue.equals(0.00) | attributeValue.equals(0)) {
    		 System.out.println(0);
    } else {
    	 String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
  	    String user = "ag1";
  	    String passwd = "jamal123";

  	    try {
  	    	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
             Connection con = DriverManager.getConnection(url, user, passwd);
  	        String query = "UPDATE car SET " + attributeName + " = ? WHERE id_car = ?";

  	        PreparedStatement pstmt = con.prepareStatement(query);
  	        pstmt.setObject(1, attributeValue);
  	        pstmt.setInt(2, idCar);

  	        int rowsUpdated = pstmt.executeUpdate();
  	        if (rowsUpdated > 0) {
  	            System.out.println("Car updated successfully.");
  	        } else {
  	            System.out.println("invalid");
  	        }
  	      pstmt.close();
          con.commit();
          con.close();
  	    } catch (SQLException ex) {
  	        System.err.println("SQLException: " + ex.getMessage());
  	    }
    }
  }
    public static Car getCarById(int idCar) {
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
        String user = "ag1";
        String passwd = "jamal123";
        Car car = null;

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection(url, user, passwd);

            String query = "SELECT * FROM car WHERE ID_CAR = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idCar);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int idAgency = rs.getInt("ID_AGENCY");
                String brand = rs.getString("BRAND");
                String model = rs.getString("MODEL");
                int year = rs.getInt("YEAR");
                String color = rs.getString("COLOR");
                double price = rs.getDouble("PRICE");
                String availabilityStatus = rs.getString("AVAILABILITY_STATUS");
                String image = rs.getString("IMAGE");

                car = new Car(idCar, idAgency, brand, model, year, color, price, availabilityStatus, image);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return car;
    }

    public void deleteCar(int carId) {
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
        String user = "ag1";
        String passwd = "jamal123";

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection(url, user, passwd);
            String query = "DELETE FROM car WHERE id_car = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, carId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Car deleted successfully.");
            } else {
                System.out.println("invalid");
            }
            pstmt.close();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        if (Integer.parseInt(args[0]) == 1) {
            int idAgency = Integer.parseInt(args[1]);
            List<Car> cars = Car.fetchCarsByAgency(idAgency);
            Gson gson = new Gson();
            String carsJson = gson.toJson(cars);
            System.out.print(carsJson);
        } else if (Integer.parseInt(args[0]) == 2) {
            // Parse the command-line arguments into respective data types
            int idAgency = Integer.parseInt(args[1]);
            String brand = args[2];
            String model = args[3];
            int year = Integer.parseInt(args[4]);
            String color = args[5];
            double price = Double.parseDouble(args[6]);
            String image = args[7];
            // Create a new Car object using the command-line arguments
            Car car = new Car(1, idAgency, brand, model, year, color, price, "Available", image);
            car.save();
            System.out.print("valid");
        }else if (Integer.parseInt(args[0]) == 3){
        	int idCar = Integer.parseInt(args[1]);
        	int idAgency = Integer.parseInt(args[2]);
            String brand = args[3];
            String model = args[4];
            int year = Integer.parseInt(args[5]);
            String color = args[6];
            double price = Double.parseDouble(args[7]);
            String AvailabilityStatus = args[8];
            String image = args[9];
            Car car = new Car(idCar, idAgency, brand, model, year, color, price, AvailabilityStatus, image);
            car.update("BRAND", brand);
            car.update("Model", model);
            car.update("YEAR", year);
	        car.update("COLOR", color);
	        car.update("PRICE", price);
	        car.update("AVAILABILITY_STATUS", AvailabilityStatus);
	        car.update("IMAGE", image);
            
        }else if (Integer.parseInt(args[0]) == 4){
        	Car car = new Car();
            car.deleteCar(Integer.parseInt(args[1]));
            System.out.print("valid");
        }else if (Integer.parseInt(args[0]) == 5){
        	Car car = Car.getCarById(Integer.parseInt(args[1]));
        	System.out.print(car.brand+"("+car.model+")");
        }
    }
    
}
