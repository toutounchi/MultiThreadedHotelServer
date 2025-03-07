import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HotelServer {

    public static void main (String[]args) throws IOException {
        Hotel hotel = new Hotel();

        final int hotelServerPort = 8090;
        ServerSocket server = new ServerSocket(hotelServerPort);
        System.out.println("Waiting for Client to connect ....");
        try
        {
            /*
            Waiting for new client connections in an infinite loop
             */
           while (true)
           {
               Socket s = server.accept();
               System.out.println("Client connected");
               // Create a thread of Runnable HotelService to run in parallel
               HotelService service = new HotelService(s, hotel);
               Thread t = new Thread(service);
               t.start();
           }
        }
        finally
        {
            server.close();
        }
    }
}
