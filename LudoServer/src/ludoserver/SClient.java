/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludoserver;

import game.Message;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static game.Message.Message_Type.RivalPlay;

/**
 *
 * @author Yunus Emre Atik (yunusemre.atik@stu.fsm.edu.tr)
 */
public class SClient {

    int id;
    public String name;

    Socket socket;
    ObjectOutputStream sOutput;
    ObjectInputStream sInput;

    Listen listenThread;
    PairingThread pairThread;

    SClient rival;

    public boolean paired = false;
    public boolean isConnected = false;
    public boolean rivalDegisken = true;

    SClient(Socket clientSocket, int IdClient) {
        this.socket = clientSocket;
        this.id = IdClient;
        try {
            this.sOutput = new ObjectOutputStream(this.socket.getOutputStream());
            this.sInput = new ObjectInputStream(this.socket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.listenThread = new Listen(this);
        this.pairThread = new PairingThread(this);
        this.isConnected = true;
    }

}

class Listen extends Thread {

    SClient sClient;

    Listen(SClient sClient) {
        this.sClient = sClient;
    }

    @Override
    public void run() {
        while (sClient.isConnected) {
            try {
                Message received = (Message) (sClient.sInput.readObject());

                switch (received.type) {
                    case Name:
                        sClient.name = received.content.toString();
                        sClient.pairThread.start();
                        break;
                    case Text:
                        Server.Send(sClient.rival, received);
                        break;
                    case RivalPlay:
                        Server.Send(sClient.rival, received);
                        break;
                    case Dice:
                        Server.Send(sClient.rival, received);
                        break;
                    case Btn1:
                        Server.Send(sClient.rival, received);
                        break;
                    case Btn2:
                        Server.Send(sClient.rival, received);
                        break;
                    case Btn3:
                        Server.Send(sClient.rival, received);
                        break;
                    case RivalBtn1:
                        Server.Send(sClient.rival, received);
                        break;
                    case RivalBtn2:
                        Server.Send(sClient.rival, received);
                        break;
                    case RivalBtn3:
                        Server.Send(sClient.rival, received);
                        break;
                    case DiceDurum:
                        Server.Send(sClient.rival, received);
                        break;
                    case OynamaDurum:
                        Server.Send(sClient.rival, received);
                        break;
                    case Disconnect:
                        Server.Clients.remove(sClient);
                        break;
                    case Sira:
                        Server.Send(sClient.rival, received);
                        break;
                }

            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("'" + sClient.name + "'" + " Client'i Kapandı");

                this.sClient.isConnected = false;
                Server.Clients.remove(sClient);
                break;

            }

        }

    }
}

class PairingThread extends Thread {

    SClient sClient;

    PairingThread(SClient sClient) {
        this.sClient = sClient;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PairingThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (sClient.isConnected && sClient.paired == false) {
            try {
                Server.kilit.acquire(1);
                while (sClient.rivalDegisken) {
                    for (SClient clnt : Server.Clients) {
                        if (this.sClient != clnt && clnt.rival == null) {
                            clnt.paired = true;
                            clnt.rival = sClient;

                            sClient.paired = true;
                            sClient.rival = clnt;
                            sClient.rivalDegisken = false;

                            break;
                        }
                    }
                    sleep(1000);
                }
                Message msg1 = new Message(Message.Message_Type.RivalConnected);
                msg1.content = sClient.name;
                Server.Send(sClient.rival, msg1);   //Rival'e kendi ismimi gonderiyorum

                Message msg2 = new Message(Message.Message_Type.RivalConnected);
                msg2.content = sClient.rival.name;
                Server.Send(sClient, msg2);         //Kendime Rival ismini gonderiyorum

                Server.kilit.release(1);
            } catch (InterruptedException ex) {
                System.out.println("Error in Pairing");
                Logger.getLogger(PairingThread.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }
}
