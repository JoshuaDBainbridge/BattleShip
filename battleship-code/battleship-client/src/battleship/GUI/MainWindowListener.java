//
//package battleship.GUI;
//
//import battleship.Infrastructure.CommMsg;
//import com.oracle.tools.packager.IOUtils;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.*;
//import java.net.*;
//import java.nio.charset.StandardCharsets;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
//
//public class MainWindowListener implements ActionListener {
//
//
//    MainWindow mw;
//    JFrame gui;
//    int choice;
//    Socket socket;
//    int count = 0;
//    private OutputStream socketOutput;
//    private InputStream socketInput;
//    private Socket theSocket;
//    int numClients = 0;
//
//    public MainWindowListener(MainWindow m)
//    {
//        mw = m;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent ae) {
//        if(ae.getActionCommand().equals("Join"))
//            setUpClient();
//        else if(ae.getActionCommand().equals("Host")) {
//            mw.canvas.menuGraphics.drawString("Please wait while the connection"
//                    , 700, 475);
//            mw.canvas.menuGraphics.drawString("to the opponent is established.",
//                    700, 490);
//            mw.canvas.update(mw.getCanvas().getGraphics());
//            setUpServer();
//        }
//
//
//
//
//
//        else if(ae.getActionCommand().equals("Exit")){
//            try
//            {
//              closeGUI();
//            }
//            catch (Exception e)
//            {
//              mw.dispose();
//            }
//        }
//    }
//
//
//    // given in class
//    public class ConnectionThread extends Thread
//    {
//        public ConnectionThread()
//        {
//
//        }
//        public void run()
//        {
//            try
//            {
//                byte[] readBuffer = new byte[256];
//                for (int i=0;i<256;i++)
//                    readBuffer[i]=0;
//                while(true)
//                {
//                    socketInput.read(readBuffer);
//                    String decoded = new String(readBuffer, "UTF-8");
//                    System.out.println("Got:" + decoded);
//                }
//            }
//            catch (Exception e)
//            {
//                System.out.println(e.toString());
//            }
//        }
//    }
//
//
//
//
//
//
//    // here we need to connect to remote server
//
//    private void setUpServer()
//    {
//        ServerSocket servSock = null;
//        Socket sock = null;
//        CommMsg msg = null;
//
//
//
//        mw.choice = 1;
//        choice = 1;
//
//
//        try {
//            servSock = new ServerSocket(3000);
//            System.out.println();
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindowListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        System.out.println("Waiting for clients to connect");
//
//        // waiting for two clients to connect
//        while(numClients!= 2) {
//            try {
//                sock = servSock.accept();
//                System.out.println("Client: " + numClients +" connected");
//                // if we can accept a connetion on the socket we have one more client connected so we can continue waiting
//                numClients++;
//            } catch (IOException ex) {
//                Logger.getLogger(MainWindowListener.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        System.out.println("2 clients have sucessfully connected");
//
//        try {
//            mw.out = new ObjectOutputStream(sock.getOutputStream());
//            mw.in = new ObjectInputStream(sock.getInputStream());
//
//
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindowListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        try {
//            msg = (CommMsg) mw.in.readObject();
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindowListener.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(MainWindowListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("Opponent has connected");
//
//        mw.canvas.setStreams(mw.in, mw.out, mw.choice);
//    }
//
//
//    private void setUpClient()
//    {
//        Socket sock = null;
//        CommMsg msg = null;
//        mw.choice = 0;
//        choice = 0;
//        int c;
//
//       String input = "35.183.26.69";
//
//        System.out.println("Attempting to connect to " + input);
//
//
//        try {
//            sock =  new Socket(input, 3000);
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(MainWindowListener.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindowListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        try {
//            mw.out = new ObjectOutputStream(sock.getOutputStream());
//            mw.in = new ObjectInputStream(sock.getInputStream());
//
//
//            DataInputStream in = new DataInputStream(mw.in);
//            System.out.println("Server says " + in.readUTF());
//            if(in.readUTF() == "1"){
//
//            }
//
//
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindowListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//
//
//        System.out.println("Success! Beginning game.");
//        msg = new CommMsg();
//
//        try {
//            mw.out.writeObject(msg);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindowListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        mw.canvas.setStreams(mw.in, mw.out, mw.choice);
//    }
//
//    public ObjectInputStream getInputStream()
//    {
//        return mw.in;
//    }
//
//    public ObjectOutputStream getOutputStream()
//    {
//        return mw.out;
//    }
//
//    public int getChoice()
//    {
//        return mw.choice;
//    }
//
//    public void registerGUI(JFrame g)
//    {
//        gui = g;
//    }
//
//    public void closeGUI() throws FileNotFoundException, IOException
//    {
//        // Shutdown
//        gui.dispose();
//    }
//}

package battleship.GUI;
import battleship.Infrastructure.CommMsg;
import com.oracle.tools.packager.IOUtils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;


public class MainWindowListener implements ActionListener {


    MainWindow mw;
    JFrame gui;
    int choice;
    Socket socket;
    int count = 0;
    private OutputStream socketOutput;
    private InputStream socketInput;
    private Socket theSocket;


    public MainWindowListener(MainWindow m)
    {
        mw = m;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Join"))
            setUpClient();
         else if (ae.getActionCommand().equals("Exit")) {
            try {
                closeGUI();
            } catch (Exception e) {
                mw.dispose();
            }
        }
    }





    private void setUpClient()
    {
        Socket sock = null;
        CommMsg msg = null;
        mw.choice = 0;


        // this is the lightsail server's ip address

        String input = "99.79.122.12";


        System.out.println("Attempting to connect to " + input);

        try {
            sock =  new Socket(input, 3000);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainWindowListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowListener.class.getName()).log(Level.SEVERE, null, ex);
    }

        try {

            msg = new CommMsg();

            mw.out =  new DataOutputStream(sock.getOutputStream());
            mw.in =  new DataInputStream(sock.getInputStream());



           // we dont want to move on until the other client has connected
           while(true){
               String receiver = receive(mw.in);
               String test = "Please wait";





               if(!receiver.equals(test)){
                   break;
               }
               // this means we are player one, we need to set some sort of id

               choice = 1;

           }

           if(choice != 1){
               choice = 2;
           }




        } catch (IOException ex) {
            Logger.getLogger(MainWindowListener.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Success! Beginning game.");


        mw.canvas.setStreams(mw.in, mw.out, choice);
    }

    public DataInputStream getInputStream()
    {
        return mw.in;
    }

    public DataOutputStream getOutputStream()
    {
        return mw.out;
    }

    public int getChoice()
    {
        return mw.choice;
    }

    public void registerGUI(JFrame g)
    {
        gui = g;
    }

    public void closeGUI() throws FileNotFoundException, IOException
    {
        // Shutdown
        gui.dispose();
    }

    // helper function used to turn my msg into a string
    public String _toString (CommMsg msg){

        String strMsg = "";

        // ordered wanted is (bool win, bool hit, move x, move y, shipID)

        // Turning out object into a string and the values are seperated by commas

        strMsg = strMsg.concat(String.valueOf(msg.win()));

        strMsg = strMsg.concat(",");

        strMsg = strMsg.concat(String.valueOf(msg.hit()));

        strMsg = strMsg.concat(",");

        strMsg = strMsg.concat(String.valueOf(msg.getMoveX()));

        strMsg = strMsg.concat(",");

        strMsg = strMsg.concat(String.valueOf(msg.getMoveY()));

        strMsg = strMsg.concat(",");

        strMsg = strMsg.concat(String.valueOf(msg.getID()));

        strMsg = strMsg.concat(",");

        strMsg = strMsg.concat(String.valueOf(msg.hasGone()));


        return strMsg;
    }

    public String receive(DataInputStream in){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte buffer[] = new byte[1024];
        try {
            baos.write(buffer, 0 , in.read(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte [] result = baos.toByteArray();

        String str = "";

        for (int i: result) {
            str = str.concat(Character.toString((char) i));
        }
        return str;
    }
}

