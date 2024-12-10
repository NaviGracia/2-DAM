import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    private static final String host = "localhost";
    private static final int port = 1337;
    public static void main(String[] args) throws Exception {
        //Parte Servidor
        Server srv = new Server(port);
        Thread tServer = new Thread(srv);
        tServer.run();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        //Parte Cliente
        Client c = new Client(host, port);
        if (!c.connect()) {
            System.out.println("Error: Can't connect to the server.");
            return;
        }
        c.send("Hola Mundo!");
        String ans = c.receive();
        System.out.println(ans);

        //CLIENTE 2
        Client c2 = new Client(host, port);
        if (!c2.connect()) {
            System.out.println("Error: Can't connect to the server.");
            return;
        }
        c2.send("Aniram al ne atsila!");
        ans = c2.receive();
        System.out.println(ans);
    }
}

class Server implements Runnable{
    private ServerSocket server = null;
    private Socket client = null;
    int port = 0;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run(){
        InputStreamReader isr = null;
        BufferedReader reader = null;
        PrintWriter pw = null;
        OutputStream os = null;

        System.out.println("INFO: Server launching...");

        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("ERROR: Unable to open socket on TCP " + port);
            return;
        }

        while (true) {
            try {
                client = server.accept();
                System.out.println("SERVER: Connection stablished");

                isr = new InputStreamReader(client.getInputStream());
                reader = new BufferedReader(isr);

                //Read Message
                System.out.println("SERVER: Waiting...");
                String message = reader.readLine();
                System.out.println("SERVER: Message received");

                //Get Answer
                String answer = getAnswer(message);

                //Write Answer
                os = client.getOutputStream();
                pw = new PrintWriter(os);
                pw.write(answer);
                pw.flush();
                System.out.println("SERVER: Message Sent");

                //Close Handlers
                pw.close();
                reader.close();
                isr.close();
                client.close();
            } catch (IOException e) {
                // TODO: handle exception
                System.out.println("SERVER: Failed connecting to client");
            }
        }
    }

    private String getAnswer(String message){
        return new StringBuilder(message).reverse().toString();
    }
    
}

class Client{
    String host = "";
    int port = 0;

    private Socket socket = null;
    private InputStreamReader isr = null;
    private BufferedReader bfr = null;

    final String errorMSG = "CLIENT ERROR";
    
    public Client(String host, int port){
        this.host = host;
        this.port = port;
    }

    public boolean connect(){
        try {
            socket = new Socket(host, port);
            System.out.println("CLIENT: Connected");
            return true;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public String receive(){
        try {
            isr = new InputStreamReader(socket.getInputStream());
            bfr = new BufferedReader(isr);
            String ans = bfr.readLine();
            System.out.println("CLIENT: Message Received");
            bfr.close();
            isr.close();
            return ans;
        } catch (Exception e) {
            // TODO: handle exception
            return errorMSG;
        }
    }

    public boolean send(String message){
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(message);
            pw.flush();
            System.out.println("CLIENT: Message Sent.");
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }
}

/*
 * Código Original
 * import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
	
	private static final int port = 1337;
	private static final String host = "localhost";
	
	public static void main(String[] args) {
		// Launch server
		Server srv = new Server(port);
		Thread tServer = new Thread(srv);
		tServer.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Create client
		Client c = new Client(host, port);
		if(!c.connect()) {
			System.out.println("ERROR: Can't connect to server.");
			return;
		}
		
		c.send("Hola Mundo");
		
		System.out.println(c.receive());
		
	}

}

class Server implements Runnable{
    private ServerSocket server = null;
    private Socket client = null;
    int port = 0;
    
	public Server(int port) {
		this.port = port;
	}
	
	@Override
	public void run(){

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader bf = null;
        PrintWriter pw = null;
        OutputStream os = null;
        
        System.out.println("INFO: Server launching...");

        try {
        	server = new ServerSocket(port);
        } catch (IOException e) {
        	System.out.println("ERROR: Unable to open socket on TCP " + port);
        	return;
        }
        
        while (true){
        	try {
				client = server.accept();
	            System.out.println("OK: Conection stablished!");
				
	            is = client.getInputStream();
	            isr = new InputStreamReader(is);
	            bf = new BufferedReader(isr);
	            
	            // Read message
				System.out.println("SERVER: Waiting...");
				String message = bf.readLine();
				System.out.println("SERVER: Message received");
				
				// Get answer
				String answer = getAnswer(message);
				
				// Write answer
				os = client.getOutputStream();
	            pw = new PrintWriter(os);
	            pw.write(answer);
	            pw.flush();
				System.out.println("SERVER: Message sent");
	            
	            // Close handlers
				pw.close();
				os.close();
				bf.close();
				isr.close();
				is.close();
				client.close();
	            
			} catch (IOException e) {
	        	System.out.println("ERROR: Failed connecting to client");
				e.printStackTrace();
			}
        }
	}

	private String getAnswer(String message) {
		return new StringBuilder(message).reverse().toString();
	}
}

class Client {
	String host = "";
	int port = 0;
	Socket socket = null;
	InputStreamReader isr = null;
	BufferedReader bfr = null;
	final String errorMSG = "ERROR";
	
    public Client(String host, int port){
    	this.host = host;
    	this.port = port;
    }
    
    public boolean connect() {
        try {
			socket = new Socket(host, port);
			System.out.println("CLIENT: Connected");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public boolean send(String message) {
    	try {	        
	        PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(message);
            pw.flush();  
    		System.out.println("CLIENT: Message sent.");          
            return true;	        
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public String receive() {
		try {
			isr = new InputStreamReader(socket.getInputStream());
	        bfr = new BufferedReader(isr);
	        String ans = bfr.readLine();
			System.out.println("CLIENT: Message received");
	        return ans;
		} catch (IOException e) {
			e.printStackTrace();
			return errorMSG;
		}
    }
}
 */