import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Customer {
    public int idCustomer;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String address;
    public int idAgency;
    public String cin;
    
    // Generalized constructor
   public Customer(int idCustomer, String firstName, String lastName, String email, String phone, String address, int idAgency, String cin) {
        this.idCustomer = idCustomer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.idAgency = idAgency;
        this.cin = cin;
    }
   public static List<Customer> fetchCustomersByAgency(int idAgency) {
	    String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	    String user = "ag1";
	    String passwd = "jamal123";
	    List<Customer> customers = new ArrayList<Customer>();

	    try {
	        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	        Connection con = DriverManager.getConnection(url, user, passwd);

	        String query = "SELECT * FROM CUSTOMER WHERE ID_AGENCY = ?";
	        PreparedStatement pstmt = con.prepareStatement(query);
	        pstmt.setInt(1, idAgency);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int idCustomer = rs.getInt("ID_CUSTOMER");
	            String fname = rs.getString("FNAME");
	            String lname = rs.getString("LNAME");
	            String email = rs.getString("EMAIL");
	            String phone = rs.getString("PHONE");
	            String address = rs.getString("ADDRESS");
	            String cin = rs.getString("CIN");

	            Customer customer = new Customer(idCustomer, fname, lname, email, phone, address, idAgency, cin);
	            customers.add(customer);
	        }

	        rs.close();
	        pstmt.close();
            con.commit();
            con.close();
	    } catch (SQLException ex) {
	        System.err.println("SQLException: " + ex.getMessage());
	    }

	    return customers;
	}
   public static Customer getCustomerById(int idCustomer) {
	    String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	    String user = "ag1";
	    String passwd = "jamal123";
	    Customer customer = null;

	    try {
	        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	        Connection con = DriverManager.getConnection(url, user, passwd);

	        String query = "SELECT * FROM customer WHERE ID_CUSTOMER = ?";
	        PreparedStatement pstmt = con.prepareStatement(query);
	        pstmt.setInt(1, idCustomer);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            String firstName = rs.getString("FNAME");
	            String lastName = rs.getString("LNAME");
	            String email = rs.getString("EMAIL");
	            String phoneNumber = rs.getString("PHONE");
	            String address = rs.getString("ADDRESS");
	            int idAgency = rs.getInt("ID_AGENCY");
	            String cin = rs.getString("CIN");

	            customer = new Customer(idCustomer, firstName, lastName, email, phoneNumber, address, idAgency, cin);
	        }

	        rs.close();
	        pstmt.close();
	        con.close();
	    } catch (SQLException ex) {
	        System.err.println("SQLException: " + ex.getMessage());
	    }

	    return customer;
	}

   public static void main(String[] args) {
	   if (Integer.parseInt(args[0]) == 1) {
		    int idAgency = Integer.parseInt(args[1]);
		    List<Customer> customers = Customer.fetchCustomersByAgency(idAgency);
		    Gson gson = new Gson();
		    String customersJson = gson.toJson(customers);
		    System.out.print(customersJson);
		}else if(Integer.parseInt(args[0]) == 5){
			Customer cus = Customer.getCustomerById(Integer.parseInt(args[1]));
			System.out.print(cus.firstName+" "+cus.lastName);
		}
   }
}
