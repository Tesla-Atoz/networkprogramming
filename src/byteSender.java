import java.util.*;
import java.io.*;
import java.net.*;

public class byteSender{
    public static void main(String [] args) throws IOException{

//       Declaring variables needed;
        InetAddress ip = InetAddress.getLocalHost();
        int port = 8888;
        Scanner sc = new Scanner(System.in);

//      Opens a socket for connection;
        Socket s = new Socket(ip,port);

//        Declaring Input/output streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        while(true){
            System.out.println("Enter the message to be sent: ");
            String data = sc.nextLine();
            String res = new String();

//            Data in each frame is stuffed by 'F' at the beginning and the end;
            data = 'F' + data + 'F';

            for (int i=0; i< data.length(); i++)
            {

//                stuff 'E' if 'F' is found in the data to be sent
                if (data.charAt(i)== 'F' && i!=0 && i!= (data.length()-1))
                {
                    res = res + 'E'+ data.charAt(i);
                }
//                stuff 'E' if 'E' is found in the data to be sent
                else if (data.charAt(i) =='E')
                {
                    res = res +'E' + data.charAt(i);
                }
                else {
                    res = res + data.charAt(i);
                }
            }
            System.out.println("The data begin sent (with byte stuffed) is: "+ res);

//            send the data to receiver
            dos.writeUTF(res);

            System.out.println("Sending the message");

            if (dis.readUTF().equals("success")){
                System.out.println("Thanks for the feedback server!");
            }

//            End messaging
            dos.writeUTF("Bye");
            break;

        }

//        close all connections
        s.close();
        dis.close();
        dos.close();

    }
}
