package portscanner;

import java.util.Scanner;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.regex.Pattern;
import java.util.regex.*;

public class PortScanner {


    private static void Port(String host, int portbr) {
        Thread thread = new Thread(() -> {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(host, portbr), 5000);
                System.out.printf("[+] %d is an open port\n", portbr);
                socket.close();
            } catch (IOException e) {
                 //System.out.println("Error1"); 
            }
        });
        thread.start();
    }
    private static final Pattern IP = Pattern.compile
        ("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    

    public static boolean validate(final String host) {
        return IP.matcher(host).matches();
    }
    private static final Pattern DOMAIN = Pattern.compile
        ( "^((?!-)[A-Za-z0-9-]" + "{1,63}(?<!-)\\.)"+ "+[A-Za-z]{2,6}");
    
    
    public static boolean validate1(final String dom) {
        return DOMAIN.matcher(dom).matches();
    }
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter an IP address or domain: ");
        System.out.println("Example: 127.0.0.1 or www.google.com");

        String host = input.nextLine();
        if (host.isEmpty() || validate(host) == false && validate1(host) == false) {
            do {
                System.out.println("You have not entered an address or domain");
                
                System.out.println("---------------------------");
                System.out.println("Enter an IP address or domain: ");
                host = input.nextLine();
                
            } while (host.isEmpty() || validate(host) == false && validate1(host) == false);
        }
        System.out.println("Host:" + host);
        for (int portbr = 1; portbr <= 65535; portbr++) {
            Port(host, portbr);
            if(portbr==65535)
            {
                System.out.println("!SCAN COMPLETED!");
            }
        }
    }

}
