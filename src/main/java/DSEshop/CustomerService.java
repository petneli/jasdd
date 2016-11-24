package DSEshop;

/**
 * Created by Daria on 26.10.16.
 */
public class CustomerService {
    private String department;

    public CustomerService(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
