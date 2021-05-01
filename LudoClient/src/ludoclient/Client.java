/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludoclient;

import game.Game;
import game.Message;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yunus Emre Atik (yunusemre.atik@stu.fsm.edu.tr)
 */
public class Client {

    public static Socket socket;
    public static ObjectInputStream sInput;
    public static ObjectOutputStream sOutput;

    public static boolean isConnected = false;
    public static Listen listenMe;

    public static void Start(String ip, int port) {
        try {
            System.out.println("Server'e Baglaniyorum");
            Client.socket = new Socket(ip, port);
            System.out.println("Baglandim");
            System.out.println("Searching for Rival");
            Client.isConnected = true;

            Client.sInput = new ObjectInputStream(Client.socket.getInputStream());
            Client.sOutput = new ObjectOutputStream(Client.socket.getOutputStream());

            Client.listenMe = new Listen();
            Client.listenMe.start();

            Message msg = new Message(Message.Message_Type.Name);
            msg.content = Game.ThisGame.txt_Name_Girdi.getText();
            Client.Send(msg);

        } catch (IOException ex) {
            System.out.println("Server'e Baglanilamadi");
        }
    }

    public static void Display(String msg) {

        System.out.println(msg);

    }

    public static void Stop() {
        try {
            if (Client.socket != null) {
                Client.isConnected = false;
                Client.listenMe.stop();
                Client.socket.close();
                Client.sOutput.flush();
                Client.sOutput.close();

                Client.sInput.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void Send(Message msg) {
        try {
            Client.sOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

class Listen extends Thread {

    @Override
    public void run() {
        while (Client.isConnected) {
            try {
                Message received = (Message) (Client.sInput.readObject());

                switch (received.type) {
                    case Name:
                        break;
                    case RivalConnected:
                        String c1 = Game.ThisGame.txt_Name_Girdi.getText();
                        String c2 = received.content.toString();
                        Game.ThisGame.txt_Rival_Name.setText(received.content.toString());
                        Game.ThisGame.txt_Receive_Name.setText(received.content.toString());
                        Game.ThisGame.txt_Competitors.setText(c1 + " VS " + c2);

                        Game.ThisGame.btn_Dice.setEnabled(true);
                        Game.ThisGame.btn_Send_Message.setEnabled(true);
                        //Game.ThisGame.tmr_slider.start();
                        break;
                    case Text:
                        Game.ThisGame.txt_Receive.setText(received.content.toString());
                        break;
                    case RivalPlay:
                        Game.RivalSelection = (int) received.content;
                        break;
                    case Dice:
                        Game.ThisGame.rival_Zar_Sonucu = (int) received.content;
                        System.out.println("Rival'in Zar Sonucu : " + Game.ThisGame.rival_Zar_Sonucu);
                        break;
                    case Btn1:
                        ActionEvent evt = null;
                        Game.ThisGame.btn_Red1_Click(evt);
                        break;
                    case Btn2:
                        ActionEvent evt1 = null;
                        Game.ThisGame.btn_Red2_Click(evt1);
                        break;
                    case Btn3:
                        ActionEvent evt2 = null;
                        Game.ThisGame.btn_Red3_Click(evt2);
                        break;
                    case RivalBtn1:
                        Game.ThisGame.btn_Blue1.setLocation(50, 140);
                        Game.ThisGame.btn_Blue1.setVisible(true);
                        Game.ThisGame.btn_Blue1.setEnabled(true);
                        break;
                    case RivalBtn2:
                        Game.ThisGame.btn_Blue2.setLocation(90, 140);
                        break;
                    case RivalBtn3:
                        Game.ThisGame.btn_Blue3.setLocation(130, 140);
                        break;
                    case DiceDurum:
                        Game.ThisGame.btn_Dice.setEnabled(true);
                        break;
                    case OynamaDurum:
                        Game.ThisGame.rival_oynama_Durumu = received.content.toString();
                        break;
                }

            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Kapandım");
                Client.Stop();
                break;
            }
            //Client.Stop();

        }

    }
}
