import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class HotelClient {

    public static void main (String[] args) throws IOException
    {
        String user;
        int selection;
        final int hotelServerPort = 8090;
        Socket s = new Socket("localhost", hotelServerPort);

        InputStream inStream = s.getInputStream();
        OutputStream outStream = s.getOutputStream();

        DataInputStream in = new DataInputStream(inStream);
        DataOutputStream out = new DataOutputStream(outStream);

        Scanner input = new Scanner(System.in);

        System.out.println("WelCome To Hotel Langara!!");
        System.out.println("Please enter your first name without any space");
        user = input.nextLine();
        out.writeUTF("USER");
        out.flush();

        out.writeUTF(user);
        out.flush();

        System.out.println("Sending to server ...");
        System.out.println(in.readUTF());

        do
        {
            selection = menue(input);
            switch (selection)
            {
                case 1:
                    out.writeUTF("AVAIL");
                    out.flush();
                    System.out.println(in.readUTF());
                    break;
                case 2:
                    System.out.println("Please enter the starting date");
                    int start = input.nextInt();
                    System.out.println("please enter the end date");
                    int end = input.nextInt();

                    if (start > 31 || end > 31 || start < 1 || end < 1) {
                        System.out.println("invalid date");
                        break;
                    }
                    else
                    {
                        out.writeUTF("RESERVE");
                        out.writeInt(start);
                        out.writeInt(end);
                        out.flush();
                        System.out.println(in.readUTF());
                    }
                    break;
                case 3:
                    out.writeUTF("CANCEL");
                    out.flush();
                    System.out.println(in.readUTF());
                    break;
                case 4:
                    out.writeUTF("QUIT");
                    out.flush();
                    System.out.println(in.readUTF());
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        } while (selection != 4);

        System.out.println("Thanks for Reserving with Hotel Langara");
        System.out.println("Here are the dates you reserved the room:");

    }
    public static int menue(Scanner input)
    {
        System.out.println("Please enter one of the following numbers");
        System.out.println("[1] Current availability \t [2] Reserve The room");
        System.out.println("[3] Cancel A current Reservation \t [4] Quit");
        return input.nextInt();
    }

}
