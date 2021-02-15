import java.io.*;
import java.net.*;



public class byteReceiver{
    public static void main(String [] args) throws IOException{

//        Open a socket for connection
//        server socket for binding to a port number

        ServerSocket ss = new ServerSocket(8888);

//        Used to block until a client connects to the server
        Socket socket = ss.accept();

//        Declaring the i/o streams as earlier
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        while (true){

            String out = new String();

//            Used to read the data sent by the client
            String res = dis.readUTF();
            System.out.println("Message Received ....Successfully");
            System.out.println("The Stuffed Message is : " + res);

            for (int i=1 ;i<res.length()-1;i++){

//                if data contains a 'D' or 'F' do not unstuff it.

                if (res.charAt(i) == 'D' || res.charAt(i)=='F'){
                    out = out + res.charAt(i);
                }
                else if (res.charAt(i) == 'E' && res.charAt(i+1)== 'E'){
                    out = out + 'E';
                    i++;
                }
            }
            System.out.println("The destuffed message is :" + out );
            dos.writeUTF("success");

            String ch = dis.readUTF();
            if (ch.equals("Bye")) {
                System.out.println("The messaging is over ...Exiting");
                break;
            }
        }
//        close the opened connections;
        socket.close();
        dis.close();
        dos.close();

    }
}