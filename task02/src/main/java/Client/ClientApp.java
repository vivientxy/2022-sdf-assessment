package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp 
{
    public static void main( String[] args )
    {
        // set up host and port variables
        String host = "localhost";
        String port = "80";
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;

        // set up name and email variables
        String name = "Tang Xin Yi, Vivien";
        String email = "vivientxy@hotmail.com";

        try {
            // create socket and IO streams
            socket = new Socket(host, Integer.parseInt(port));
            os = socket.getOutputStream();
            is = socket.getInputStream();
            // ObjectOutputStream oos = new ObjectOutputStream(os);
            // ObjectInputStream ois = new ObjectInputStream(is);
            DataOutputStream oos = new DataOutputStream(os);
            DataInputStream ois = new DataInputStream(is);

            // read message sent by server
            String message = ois.readUTF();

            // get request ID
            String[] messageSplit = message.split(" ");
            String requestID = messageSplit[0];

            // process integers to get average value
            String[] integers = messageSplit[1].split(",");
            int count = 0;
            float totalSum = 0;
            for (String numString : integers) {
                totalSum += Integer.parseInt(numString);
                count ++;
            }
            float averageInt = totalSum / count;

            // write values back to server
            oos.writeUTF(requestID);
            oos.writeUTF(name);
            oos.writeUTF(email);
            oos.writeFloat(averageInt);

            // read boolean sent by server
            boolean message2 = ois.readBoolean();

            if (message2) {
                // SUCCESS
                System.out.println("SUCCESS");
                os.close();
                is.close();
                socket.close();
            } else {
                // FAIL
                String errorMessage = ois.readUTF();
                System.out.println("FAILED");
                System.out.println(errorMessage);
                os.close();
                is.close();
                socket.close();
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        
    }
}
