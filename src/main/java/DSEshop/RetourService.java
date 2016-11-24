package DSEshop;

/**
 * Created by Daria on 26.10.16.
 */
public class RetourService {
    private String department;

    public RetourService(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
