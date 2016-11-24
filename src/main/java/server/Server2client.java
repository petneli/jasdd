package server;

import java.io.Serializable;

/**
 * Created by Daria on 30.10.16.
 */
public class Server2client implements Serializable{


    private static final long serialVersionUID = -568955473901911222L;
    private int type;
        private String message;

        public Server2client(int type, String message) {
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
