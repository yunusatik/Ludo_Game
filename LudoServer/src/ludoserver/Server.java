/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludoserver;

import game.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yunus Emre Atik (yunusemre.atik@stu.fsm.edu.tr)
 */
public class Server {

    public static ServerSocket serverSocket;
    public static int port = 0;
    public static ServerThread runThread;

    public static int IdClient = 0;
    public static ArrayList<SClient> Clients = new ArrayList<>();

    public static Semaphore kilit = new Semaphore(1, true);
    public static boolean isClosed = false;

    public static void Start(int ServerPort) {
        try {
            Server.port = ServerPort;
            Server.serverSocket = new ServerSocket(Server.port);
            Server.isClosed = true;

            Server.runThread = new ServerThread();
            Server.runThread.start();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void Send(SClient client, Message msg) {
        try {
            client.sOutput.writeObject(msg);

        } catch (IOException ex) {
            
        }

    }

}

class ServerThread extends Thread {

    @Override
    public void run() {
        while (Server.isClosed) {
            try {
                System.out.println("Client Bekleniyor...");
                Socket clientSocket = Server.serverSocket.accept();
                System.out.println("Client Geldi...");

                SClient nclient = new SClient(clientSocket, Server.IdClient);
                Server.IdClient++;
                Server.Clients.add(nclient);
                nclient.listenThread.start();

            } catch (IOException ex) {
                Server.isClosed = false;
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
