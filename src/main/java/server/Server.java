package server;

import DSEshop.Admin;
import client.Client2server;
import misc.Settings;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.RunnableFuture;


/**
 * Created by Daria on 30.10.16.
 */
public class Server {
    // a unique ID for each connection
    private static int uniqueId;
    // an ArrayList to keep the list of the client
    private ArrayList<ClientThread> threadPool;
    // if I am in a GUI
    private ServerGUI serverGUI;
    // the port number to listen for connection
    private int port;
    // the boolean that will be turned of to stop the server
    private boolean keepGoing;


    public Server(int port) {
        this(port, null);
    }

    public Server(int port, ServerGUI sg) {
        // GUI or not
        this.serverGUI = sg;
        // the port
        this.port = port;
        // ArrayList for the client list
        threadPool = new ArrayList<ClientThread>();
    }

    public void start() {
        keepGoing = true;
		/* create socket server and wait for connection requests */
        try
        {
            // the socket used by the server
            ServerSocket serverSocket = new ServerSocket(port);

            // infinite loop to wait for connections
            while(keepGoing)
            {
                // format message saying we are waiting
                System.out.println("Server waiting for Clients on port " + port + ".");

                Socket socket = serverSocket.accept();  	// accept connection
                // if I was asked to stop
                if(!keepGoing)
                    break;
                ClientThread clientThread = new ClientThread(socket);  // make a thread of it
                threadPool.add(clientThread);						   // save it in the ArrayList
                clientThread.start();
            }
            // I was asked to stop
            try {
                serverSocket.close();
                for (ClientThread ct : threadPool) {
                    try {
                        ct.ois.close();
                        ct.oos.close();
                        ct.socket.close();
                    } catch (IOException ioE) {
                    }
                }
            }
            catch(Exception e) {
                System.out.println("Exception closing the server and clients: " + e);
            }
        }
        // something went bad
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    /*
     * For the GUI to stop the server
     * */
    protected void stop() {
        keepGoing = false;
        // connect to myself as client to exit statement
        // Socket socket = serverSocket.accept();
        try {
            new Socket("localhost", port);
        }
        catch(Exception e) {
        }
    }

    /*
     *  To run as a console application just open a console window and:
     * > java Server
     * > java Server portNumber
     * If the port number is not specified 1500 is used
     */

    /** One instance of this thread will run for each client */
    class ClientThread extends Thread {
        // the socket where to listen/talk
        Socket socket;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        // my unique id (easier for deconnection)
        int id;
        // the Username of the client
        Client2server c2s;

        // Constructor
        ClientThread(Socket socket) {
            // a unique id
            id = ++uniqueId;
            this.socket = socket;
			/* Creating both Data Stream */
            System.out.println("Thread trying to create Object Input/Output Streams");
            try
            {
                // create output first
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois  = new ObjectInputStream(socket.getInputStream());
                // read the username

            }
            catch (IOException e) {
                System.out.println("Exception creating new Input/output Streams: " + e);
                return;
            }
        }

        // what will run forever
        public void run() {
            // to loop until LOGOUT
            boolean keepGoing = true;
            while(keepGoing) {
                // read a String (which is an object)
                try {
                    c2s = (Client2server) ois.readObject();
                }
                catch (IOException e) {
                    System.out.println("Exception reading Streams: " + e);
                    break;
                }
                catch(ClassNotFoundException e2) {
                    break;
                }
                // the messaage part of the ChatMessage
                String message = c2s.getMessage();

                String[] parts = message.split("-");

                Server2client mess;

                // Switch on the type of message receive
                switch(c2s.getType()) {
                    case Settings.REGISTER_EVENT:
                        System.out.println(" REGISTER BUTTON PRESSED " + message);

                        if (Admin.getInstance().register(parts[0], parts[1])) {
                            mess = new Server2client(Settings.SUCCESS, parts[0]);
                        }
                        else{
                            mess = new Server2client(Settings.FAILURE, parts[0]);
                        }
                        writeMsg(mess);
                        break;
                    case Settings.LOGIN_EVENT:
                        System.out.println(" LOGIN BUTTON PRESSED " + message);

                        if (Admin.getInstance().login(parts[0], parts[1])) {
                            mess = new Server2client(Settings.SUCCESS, parts[0]);
                        }
                        else{
                            mess = new Server2client(Settings.FAILURE, parts[0]);
                        }
                        writeMsg(mess);
                        break;
                    case Settings.ADDTOWISH_EVENT:
                        System.out.println(" ADD TO WISHLIST BUTTON PRESSED " + message);
                            mess = new Server2client(Settings.SUCCESS, parts[0]);
                            writeMsg(mess);
                        break;
                }


            }

            close();
        }

        // try to close everything
        private void close() {
            // try to close the connection
            try {
                if(oos != null) oos.close();
            }
            catch(Exception e) {}
            try {
                if(ois != null) ois.close();
            }
            catch(Exception e) {};
            try {
                if(socket != null) socket.close();
            }
            catch (Exception e) {}
        }

        //Write a String to the client output stream

        private boolean writeMsg(Server2client msg) {
            // if client is still connected send the message to it
            if(!socket.isConnected()) {
                close();
                return false;
            }
            // write the message to the stream
            try {
                oos.writeObject(msg);
            }
            // if an error occurs, do not abort just inform the user
            catch(IOException e) {
                System.out.println("Error sending message.");
                System.out.println(e.toString());
            }
            return true;
        }
    }
}
