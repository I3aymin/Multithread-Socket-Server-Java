package multithread;
import java.net.*;
import java.io.*;

public class workerServerThread extends Thread {

	Socket serverClient;
	int clientNo;
	
	public workerServerThread(Socket inSocket, int counter) {
		serverClient = inSocket;
		clientNo = counter;
	}
	
	public void run() {
		try {
			DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
			DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
			
			String clientMsg = "", serverMsg = "";
			
			while(!clientMsg.contentEquals("End")) {
				clientMsg = inStream.readUTF();
				
				outStream.writeUTF(serverMsg);
				outStream.flush();
			}
			inStream.close();
			outStream.close();
			serverClient.close();
		} catch(Exception Ex) {
			System.err.println("Worker Thread exception is: " + Ex.toString());
			Ex.printStackTrace();
		} finally {
			System.out.println("Client - " + clientNo + " has left!");
		}
	}
}
