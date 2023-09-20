import java.sql.Date;
import java.text.SimpleDateFormat;
import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Dash {
    public int cur;
    public double erns;
    public int nb_rents;

    public Dash(int cur, double erns, int nb_rents) {
        this.cur = cur;
        this.erns = erns;
        this.nb_rents = nb_rents;
    }
    public static double calculateTotalCostByAgency(int idAgency) {
        List<Rent> rents = Rent.fetchRentsByAgency(idAgency);
        double totalCost = 0.0;
        for (Rent rent : rents) {
            totalCost += rent.totalCost;
        }
        return totalCost;
    }
    public static int calculateNumberOfRentsByAgency(int idAgency) {
        List<Rent> rents = Rent.fetchRentsByAgency(idAgency);
        return rents.size();
    }
    public static int calculateNumberOfCurrentlyRentedCarsByAgency(int idAgency) {
        List<Car> cars = Car.fetchCarsByAgency(idAgency);
        int count = 0;
      
        for (Car car : cars) {
            if (car.availabilityStatus.equals("Rented")){
                count++;
            }
        }
        
        return count;
    }

    public static void main(String[] args) {
    	int id = Integer.parseInt(args[0]);
    	Dash d = new Dash(Dash.calculateNumberOfCurrentlyRentedCarsByAgency(id),Dash.calculateTotalCostByAgency(id),Dash.calculateNumberOfRentsByAgency(id));
    	Gson gson = new Gson();
		String ds = gson.toJson(d);
		System.out.print(ds);
    }
}
