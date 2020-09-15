import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class InetServer {

    public static void main(String a[]) throws IOException {

        int q_len = 6;
        int port = 1565;
        Socket sock;

        ServerSocket servsock = new ServerSocket(port, q_len);

        System.out.println("Clark Elliott's Inet server 1.8 starting up, listening at port 1565.\n");

        while (true) {

            sock = servsock.accept();
            new Worker(sock).start();
        }
    }
}