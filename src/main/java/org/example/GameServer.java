package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public GameServer() {
    }

    public static void main(String[] args) throws IOException {
        ServerSocket servsok = new ServerSocket(3000);/*am pornit servarul si astept sa se conecteaza utilizatorul*/
        System.out.println("Server on!");
        System.out.println("Serverul asteapta sa se conecteze utilizatorii...");
        while (true) {
            Socket sok = null;

            try {
                sok = servsok.accept();
                System.out.println("S-a conectat jucatorul " + sok);
                DataInputStream dis = new DataInputStream(sok.getInputStream());
                DataOutputStream dos = new DataOutputStream(sok.getOutputStream());

                Thread t = new ClientThread(sok, dis, dos);
                t.start();

            } catch (Exception e) {
                sok.close();
                e.printStackTrace();
            }
        }
    }
}

