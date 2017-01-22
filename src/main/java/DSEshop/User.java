package DSEshop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Daria on 26.10.16.
 */
@XmlRootElement
public class User implements Serializable{

    private static final long serialVersionUID = 4816916944840634004L;

    private int userID;
    private String userName;
    private  String password;

    public User(int userID, String userName, String password)
    {
        this.userID = userID;
        this.userName= userName;
        this.password= password;

    }
    @XmlElement
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    @XmlElement
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @XmlElement
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
