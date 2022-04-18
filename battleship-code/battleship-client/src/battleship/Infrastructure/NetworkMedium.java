/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.Infrastructure;

import battleship.GUI.MainWindow;
import battleship.GUI.MainWindowListener;

import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetAddress;
import java.net.UnknownHostException;



public class NetworkMedium {

    private CommMsg msg;




    private DataInputStream  in   = null;
    private DataOutputStream out     = null;

    public NetworkMedium(CommMsg m, DataOutputStream o, DataInputStream i)
    {
        msg = m;
        out = o;
        in = i;
    }

    public NetworkMedium()
    {
        msg = new CommMsg();
    }

    public void setMove(int x, int y, int id)
    {
        msg = new CommMsg(x,y,id);
    }

    public int getMoveX()
    {
        return msg.getMoveX();
    }

    public int getMoveY()
    {
        return msg.getMoveY();
    }




    public void setHit(boolean b)
    {
        msg.setHit(b);
    }

    public boolean hit()
    {
        return msg.hit();
    }

    public void setID(int id){
        msg.setID(id);
    }

    public void send()
    {
        try {

            String strMsg = _toString(msg);

            byte[] b = strMsg.getBytes();

            System.out.println("Sent: "+ strMsg);

            out.write(b);


        } catch (IOException ex) {
            Logger.getLogger(NetworkMedium.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recieve() {




        String received = receive(in);




        System.out.println("The string received is: " + received);
        //THE STRING RECEIVED IS SENT IN THE FOLLOWING FORMAT

        // bool win, bool hit, movex, movey, int shipid, bool hasgone

        String[] myStrings = received.split(",");
        msg.setWin(Boolean.parseBoolean(myStrings[0]));
        msg.setHit(Boolean.parseBoolean(myStrings[1]));
        msg.setMove(Integer.parseInt(myStrings[2]),Integer.parseInt(myStrings[3]));
        msg.setID(Integer.parseInt(myStrings[4]));
        msg.setTurnStatus(Boolean.parseBoolean(myStrings[5]));




//        } catch (IOException ex) {
//            Logger.getLogger(NetworkMedium.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(NetworkMedium.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    }

    public void setWin(boolean b)
    {
        msg.setWin(b);
    }

    public boolean win()
    {
        return msg.win();
    }

    public String getIP(){
        // localhost ip
        String ip = "127.0.0.0";
        return ip;

    }

    public int getID() {
        return msg.getID();
    }

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