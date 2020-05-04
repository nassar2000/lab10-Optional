package org.example;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.*;
        import java.net.*;
        import java.util.Scanner;

public class GameClient extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    public GameClient() {

        // set flow layout for the frame
        this.getContentPane().setLayout(new BorderLayout());
        JFrame jframe = new JFrame("JFrame Title Example");

        JPanel panel2 = new JPanel();
        JButton exit = new JButton("Exit");
        JButton stop = new JButton("Stop");

        panel2.add(exit);
        panel2.add(stop);

        exit.addActionListener(this);
        exit.setActionCommand("Exit");

        stop.addActionListener(this);
        stop.setActionCommand("Stop");



        getContentPane().add(BorderLayout.NORTH, panel2);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();


        try
        {
            Scanner scnr = new Scanner(System.in);
            /*initializam ip-ul si portul serverului */
            String ip = "localhost";
            int port=3000;
            /*stabilim conexiunea la portul initializat si la adresa ip*/
            Socket sok = new Socket(ip, port);


            DataInputStream dis = new DataInputStream(sok.getInputStream());
            DataOutputStream dos = new DataOutputStream(sok.getOutputStream());

            /*un loop pt a preloa mesg de la server si comenzele de la client*/
            loop: while (true)
            {

                dos.writeUTF(action);   /*trimitem comandda la server dar ramne salvata in toSend*/

                /*verificam daca e Exit daca da se inchide clientul*/
                if(action.equals("Exit"))
                {
                    sok.close();  /*inchidem conexiunea la server*/
                    System.out.println("Conexiune inchisa!");
                    System.exit(0);
                    break;

                }



                String received;  /*primim raspunsul de la server */
                String [][]receivedString;
                switch(action){ case "Stop":  /*daca raspunsul e stop atunci serverul se va opri*/
                    received = dis.readUTF();
                    System.out.println(received);
                    System.exit(0);
                    break loop;

                }

            }

            /*inchidem comunicarea cu servarul citirea si trimiterea mesg la server
             * inchidem preloarea date de la tastatura*/
            scnr.close();
            dis.close();
            dos.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private static void createAndShowGUI() {

        //Create and set up the window.

        JFrame frame = new GameClient();


        //Display the window.
        frame.setTitle("five-in-a-row");

        frame.pack();

        frame.setVisible(true);

        frame.setSize(400, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) throws IOException {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });



    }

}

