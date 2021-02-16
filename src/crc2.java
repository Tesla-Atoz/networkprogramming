import java.io.*;

public class crc2{

    static String div(String code, String gen) {

        int pointer = gen.length();
        String result = code.substring(0, pointer);

        String remainder = "";

        for (int i = 0; i < gen.length(); i++) {
            if (result.charAt(i) == gen.charAt(i)) remainder += "0";
            else remainder += "1";
        }
        while (pointer < code.length()) {
            if (remainder.charAt(0) == '0') {
                remainder = remainder.substring(1, remainder.length());
                remainder = remainder + String.valueOf(code.charAt(pointer));
                pointer++;
            }
            result = remainder;
            remainder = "";
            for (int i = 0; i < gen.length(); i++) {
                if (result.charAt(i) == gen.charAt(i)) remainder += "0";
                else remainder += "1";
            }
        }
        return remainder.substring(1,remainder.length());
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter generator");
        String gen = br.readLine();

        System.out.println("Enter the data which needs to get transmitted");
        String data = br.readLine();
        String code = data;
//        appending the l-1 number of 0's to the code.
        while(code.length() < (data.length()+gen.length() -1)){
            code = code + "0";
        }

        code = data + div(code,gen);
        System.out.println("The transmitted Code Word is: " + code);
        System.out.println("Please enter the received Code Word: ");
        String rec = br.readLine();
        if(Integer.parseInt(div(rec,gen)) == 0)
            System.out.println("The received code word contains no errors.");
        else
            System.out.println("The received code word contains errors.");
    }

}
