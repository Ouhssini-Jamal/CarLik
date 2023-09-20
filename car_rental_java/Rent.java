import java.sql.Date;
import java.text.SimpleDateFormat;


import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Rent {
    public int idRent;
    public int idCar;
    public int idCustomer;
    public Date startDate;
    public Date endDate;
    public double totalCost;
    public String name;
    public String car;
    public Rent(int idRent, int idCar, int idCustomer, Date startDate, Date endDate, double totalCost) {
        this.idRent = idRent;
        this.idCar = idCar;
        this.idCustomer = idCustomer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.name = null;
        this.car = null;
    }
    public void save() {
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
        String user = "ag1";
        String passwd = "jamal123";

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection(url, user, passwd);
            String query = "INSERT INTO rent (ID_RENT, ID_CAR, ID_CUSTOMER, START_DATE, END_DATE, TOTAL_COST) "
                    + "VALUES (SQNUMRENT.nextval, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idCar);
            pstmt.setInt(2, idCustomer);
            pstmt.setDate(3, startDate);
            pstmt.setDate(4, endDate);
            pstmt.setDouble(5, totalCost);

            pstmt.executeUpdate();
            con.commit();
            pstmt.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    public static List<Rent> fetchRentsByAgency(int idAgency) {
        List<Rent> rents = new ArrayList<Rent>();
        // Fetch cars by agency using the existing method
        List<Car> cars = Car.fetchCarsByAgency(idAgency);

        // Establish JDBC connection
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
        String user = "ag1";
        String passwd = "jamal123";

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection(url, user, passwd);

            // Iterate over the cars and retrieve the associated rents
            for (Car car : cars) {
                int idCar = car.idCar;
                String query = "SELECT * FROM RENT WHERE ID_CAR = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setInt(1, idCar);
                ResultSet rs = pstmt.executeQuery();

                // Iterate over the result set and create Rent objects
                while (rs.next()) {
                    int idRent = rs.getInt("ID_RENT");
                    int idCustomer = rs.getInt("ID_CUSTOMER");
                    Date startDate = rs.getDate("START_DATE");
                    Date endDate = rs.getDate("END_DATE");
                    double totalCost = rs.getDouble("TOTAL_COST");

                    Rent rent = new Rent(idRent, idCar, idCustomer, startDate, endDate, totalCost);
                    rents.add(rent);
                }

                rs.close();
    	        pstmt.close();
            }

            con.commit();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return rents;
    }
    
    public static void main(String[] args) {
    	if (Integer.parseInt(args[0]) == 1) {
    	    int idAgency = Integer.parseInt(args[1]);
    	    List<Rent> rents = Rent.fetchRentsByAgency(idAgency);
    	    Gson gson = new Gson();
    	    for (Rent rent : rents) {
    	        Car car = Car.getCarById(rent.idCar);
    	        Customer customer = Customer.getCustomerById(rent.idCustomer);
    	        rent.car = car.brand+"("+car.model+")" ;
    	        rent.name = customer.firstName+ " " + customer.lastName;
    	    }
    	    String rentsJson = gson.toJson(rents);
    	    System.out.print(rentsJson);
    	   }
    		else if (Integer.parseInt(args[0]) == 2) {
    		 	Car car = new Car();
    	        int idCar = Integer.parseInt(args[1]);
    	        int idCustomer = Integer.parseInt(args[2]);
    	        String startDateString = args[3];
    	        String endDateString = args[4];
    	        // Parse startDateString and endDateString to SQL Date format
    	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	        Date startDate = null;
    	        Date endDate = null;
    	        try {
    	            java.util.Date parsedStartDate = dateFormat.parse(startDateString);
    	            java.util.Date parsedEndDate = dateFormat.parse(endDateString);
    	            startDate = new Date(parsedStartDate.getTime());
    	            endDate = new Date(parsedEndDate.getTime());
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	            return;
    	        }
    	        car = Car.getCarById(idCar);
    	        long millisecondsPerDay = 24 * 60 * 60 * 1000;
                long days = (endDate.getTime() - startDate.getTime()) / millisecondsPerDay;
                double totalCost = car.price * days;
    	        Rent rent = new Rent(1,idCar, idCustomer, startDate, endDate, totalCost);
    	        car.update("AVAILABILITY_STATUS", "Rented");
    	        rent.save();
    	        System.out.print("valid");
    	    }
    }
    
}
