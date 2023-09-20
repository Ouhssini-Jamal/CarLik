
public class Employee {
        public int idEmployee;
        public int idAgency;
        public String fname;
        public String lname;
        public String email;
        public String phone;
        public String address;
        public String cin;
        public String password;

        public Employee(int idEmployee, int idAgency, String fname, String lname, String email, String phone, String address, String cin, String password) {
            this.idEmployee = idEmployee;
            this.idAgency = idAgency;
            this.fname = fname;
            this.lname = lname;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.cin = cin;
            this.password = password;
        }
    }