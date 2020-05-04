package org.example;

import java.io.*;
import java.net.Socket;


class ClientThread extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket sok;

    public ClientThread(Socket s, DataInputStream dis, DataOutputStream dos) throws IOException {
        this.sok = s;
        this.dis = dis;
        this.dos = dos;

    }

    @Override
    public void run() {

                try {

                    String received = this.dis.readUTF();
                    String toreturn;
                    /*in caz in care primeste comanda stop se opreste serverul*/
                    if (received.equals("Stop")) {
                        System.out.println("Jucatorul " + this.sok + " incearca sa opreasca conexiunea...");
                        System.out.println("Se inchide conexiunea");
                        toreturn = "server off!";
                        this.dos.writeUTF(toreturn);
                        dos.flush();
                        this.sok.close();
                        System.out.println("Conexiune inchisa!");
                        System.exit(0);

                    }

                    if (received.equals("Exit")) {
                        System.out.println("S-a deconectat " + this.sok);
                        this.sok.close();


                    }

                } catch (IOException var7) {
                    var7.printStackTrace();
                }


            try {
                this.dis.close();
                this.dos.close();
            } catch (IOException var6) {
                var6.printStackTrace();
            }


    }
}

