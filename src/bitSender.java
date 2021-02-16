import java.io.*;
import java.net.*;

import java.util.Scanner;

public class bitSender{
    public static void main(String [] args) throws IOException{
        InetAddress ip = InetAddress.getLocalHost();
//      Open a socket for connection
        Socket socket = new Socket(ip,8888);

//        Declaring the i/o streams

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

//        Taking input via scanner
        Scanner sc = new Scanner(System.in);

//        Takes input of unstuffed data from user
        System.out.println("Enter the data: ");
        String data = sc.nextLine();

        int count = 0;
        String s = "";

        for (int i=0;i<data.length()-1;i++){
            char ch = data.charAt(i);

            if (ch== '1'){
//          Count the number of consecutive 1's
                count++;
                if (count <5){
                    s +=ch;
                }
                else{
//                add one '0' after 5 consecutive 1's
                    s = s+ch +'0';
                    count=0;
                }
            }
            else{
                s+=ch;
                count=0;
            }
        }

//        add flag byte in the beginning and end of the stuffed data
        s = "01111110" + s + "01111110";

      System.out.println("data stuffed in client : " + s);
      System.out.println("Sending data to server for unstuffing");
      dos.writeUTF(s);

      socket.close();
      dis.close();
      dos.close();

    }
}
