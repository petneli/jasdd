package client;

import DSEshop.Admin;
import misc.Settings;
import server.Server2client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Daria on 30.10.16.
 */
public class Client {
    // for I/O
    private ObjectInputStream ois;		// to read from the socket
    private ObjectOutputStream oos;		// to write on the socket
    private Socket socket;

    // if I use a GUI or not
    private ClientGUI cg;

    // the server, the port and the username
    private String server;
    private int port;

    public Client(String server, int port) {
        this.server = server;
        this.port = port;
    }

    public Client(ClientGUI cg, String server, int port) {
        // save if we are in GUI mode or not
        this.cg = cg;
        this.server = server;
        this.port = port;
    }

    /*
	 * To start the dialog
	 */
    public boolean start() {
        // try to connect to the server
        try {
            socket = new Socket(server, port);
        }
        // if it failed not much I can so
        catch(Exception exc) {
            System.out.println("Error connectiong to server:" + exc);
            return false;
        }

        String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();

		/* Creating both Data Stream */
        try
        {
            ois  = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException eIO) {
            System.out.println("Exception creating new Input/output Streams: " + eIO);
            return false;
        }
        // creates the Thread to listen from the server
        new ListenFromServer().start();

        // success we inform the caller that it worked
        return true;
    }
    /*
 * To send a message to the server
 */
    void sendMessage(Client2server msg) {
        try {
            oos.writeObject(msg);
            String result = null;
            switch(msg.getType()) {
                case Settings.REGISTER_EVENT:
                    result = "SENT REGISTER EVENT";
                    break;
                case Settings.LOGIN_EVENT:
                    result = "SENT LOGIN EVENT";
                    break;
                case Settings.ADDTOWISH_EVENT:
                    result = "SENT ADD TO WISHLIST EVENT";
                    break;
            }
            System.out.println("MESSAGE SENT: " + result + ", " + msg.getMessage());
        }
        catch(IOException e) {
            System.out.println("Exception writing to server: " + e);
        }
    }

    /*
 * When something goes wrong
 * Close the Input/Output streams and disconnect not much to do in the catch clause
 */
    private void disconnect() {
        try {
            if(ois != null) ois.close();
            if(oos != null) oos.close();
            if(socket != null) socket.close();
        }
        catch(Exception e) {}

        /* inform the GUI
        if(cg != null)
            cg.connectionFailed(); */

    }


    class ListenFromServer extends Thread {

        public void run() {
            while (true) {
                try {
                    Server2client msg = (Server2client) ois.readObject();
                    String result = null;
                    switch(msg.getType()) {
                        case Settings.SUCCESS:
                            result = "SUCCESS";
                            break;
                        case Settings.FAILURE:
                            result = "FAILURE";
                            break;
                        case Settings.ERROR:
                            result = "ERROR";
                            break;
                    }
                    System.out.println("MESSAGE RECEIVED : " + result + ", " + msg.getMessage());

                } catch (IOException e) {
                    System.out.println("Server has close the connection: " + e);
                    if (cg != null)
                        System.out.println("Connection Failed.");
                    break;
                }
                // can't happen with a String object but need the catch anyhow
                catch (ClassNotFoundException e2) {
                }
            }

        }
    }
}

