package DSEshop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sebastian on 25/11/2016.
 */
@XmlRootElement
public class Dummy {
    private String name;
    private String passwd;

    public Dummy(){}

    public Dummy(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    @XmlElement
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
