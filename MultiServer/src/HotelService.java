import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HotelService implements Runnable{
    private Socket s;
    private String user;
    DataInputStream in;
    DataOutputStream out;
    private Hotel hotel;

    public HotelService (Socket aSocket, Hotel ahotel)
    {
        s = aSocket;
        hotel = ahotel;
    }

    @Override
    public void run(){
        /*
        creating in and out data input and output streams
         */
        try {
            try{
                in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());
                doService();
            }
            finally {
                s.close();
            }
        }
        catch(IOException exception){
            exception.printStackTrace();
        }
    }
    public void doService() throws IOException
    {
        /*
        process commands in an infinite loop until a 'QUIT' or an invalid command
         */
        while (true){
            String command = in.readUTF();
            System.out.println(command);
            boolean status = executeCommand(command);
            if (! status) {
                break;
            }
        }
    }
    public boolean executeCommand(String command) throws IOException {
        // If the command was USER, read user's name next
        if (command.equals("USER")) {
            user = in.readUTF();
            out.writeUTF("Hello, " + user);
            out.flush();
        }
        // If the command was RESERVE, read two integers for start and end date
        else if (command.equals("RESERVE")){
            int start = in.readInt();
            int last = in.readInt();
            // Check if the hotel has availability for that date
            if (hotel.requestReservation(user, start, last)) {
                out.writeUTF("Reservation for " + user + ", from: " + start + ", to: " + last);
                out.flush();
            } else {
                out.writeUTF("Reservation unsuccessful for " + user + ", from: " + start + ", to: " + last);
                out.flush();
            }
        }
        // If the command was CANCEL, cancel the reservation for the user in this thread
        else if (command.equals("CANCEL")){
            if (hotel.cancelReservation(user)) {
                out.writeUTF("Reservations cancelled for: " + user);
                out.flush();
            } else {
                out.writeUTF("No reservations for: " + user);
                out.flush();
            }
        }
        // Check the availabilities through the AVAIL command
        else if (command.equals("AVAIL")) {
            out.writeUTF(hotel.toString());
            out.flush();
        }
        // QUIT if the command was given
        else if (command.equals("QUIT")) {
            out.writeUTF("Closing Connection");
            out.flush();
            return false;
        } else {
            out.writeUTF("Invalid command: Closing Connection");
            out.flush();
            return false;
        }
        return true;
    }
}
