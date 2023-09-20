import com.google.gson.Gson;
import java.sql.*;
import java.io.*;

public class Login {

	
    private static String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static String user = "ag1";
    private static String passwd = "jamal123";

    public static void connexion(String c, String p) {
        Gson gson = new Gson(); // Create a Gson object

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection(url, user, passwd);

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE CIN = ? AND PASSWORD = ?");
            pstmt.setString(1, c);
            pstmt.setString(2, p);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee(rs.getInt("ID_EMPLOYEE"), rs.getInt("ID_AGENCY"), rs.getString("FNAME"), rs.getString("LNAME"), rs.getString("EMAIL"), rs.getString("PHONE"), rs.getString("ADDRESS"), rs.getString("CIN"), rs.getString("PASSWORD"));
                String employeeJson = gson.toJson(employee);
                System.out.print(employeeJson);
            } else {
                System.out.print("Invalid");
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String cin = args[0];
        String password = args[1];
        connexion(cin, password);
    }
}
