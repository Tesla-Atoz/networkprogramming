import java.net.*;
import java.util.*;
import java.io.*;

public class bitReceiver{
    public static void main(String [] args) throws IOException{

//        binfing the port to a serversocket
        ServerSocket ss = new ServerSocket(8888);
//        accepting the connection's request
        Socket socket = ss.accept();
//        Declaring i/o stream variables
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

//        Receiving the string from client which needs to be stuffed
        String s = dis.readUTF();
        System.out.println("Stuffed dat from client : " + s);

        System.out.println("Unstuffed data is :");

        int count = 0;

//        remove the stuffed bits
        for(int i =8 ;i<s.length()-8;i++){
            char ch = s.charAt(i);
            if (ch=='1'){
                count++;
                System.out.print(ch);

                if (count == 5){
                    i++;
                    count =0;
                }
            }
            else {
//                print the unstuffed data
                System.out.print(ch);
//                we only need to maintain count of consecutve 1;s
                count = 0;

            }
        }
        System.out.println();
//      close all connections
        socket.close();
        dis.close();
        dos.close();

    }
}