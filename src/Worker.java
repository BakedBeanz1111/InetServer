import java.io.*;	//Get the input output libraries
import java.net.*;	//Get the java networking libraries

//Worker class extends Thread class
class Worker extends Thread {

    Socket sock;
    Worker (Socket s) {sock = s;} //I'm not entirely sure what this syntax means yet but I'm reading into it

    public void run() {

        PrintStream out = null;
        BufferedReader in = null;

        try {

            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintStream(sock.getOutputStream());

            try {

                String name;
                name = in.readLine();
                System.out.println("Looking up " + name);
                printRemoteAddress(name, out);
            }

            catch (IOException x) {

                System.out.println("Server read error");
                x.printStackTrace();
            }

            sock.close();
        }
        catch (IOException ioe) {

            System.out.println(ioe);
        }
    }

    static void printRemoteAddress(String name, PrintStream out){

        try{

            out.println("Looking up " + name + "...");

            InetAddress machine = InetAddress.getByName(name);

            out.println("Host Name: " + machine.getHostName());
            out.println("Host IP: " + toText(machine.getAddress()));
        }
        catch(UnknownHostException ex){

            out.println("Failed in attempt to look up " + name);
        }
    }

    static String toText(byte ip[]) {

        StringBuffer result = new StringBuffer();

        for(int i = 0; i < ip.length; i++){

            if(i>0)
                result.append(".");
            result.append(0xff & ip[i]);
        }

        return result.toString();
    }
}