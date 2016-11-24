package client;

import java.io.Serializable;

/**
 * Created by Daria on 30.10.16.
 */
public class Client2server implements Serializable{

    private static final long serialVersionUID = -2524256510169831113L;
    private int type;
    private String message;

    public Client2server(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
