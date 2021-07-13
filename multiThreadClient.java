package multithread;
import java.net.*;
import java.io.*;

public class multiThreadClient {

	public static void main(String[] args) throws Exception{

		try {
			Socket socket = new Socket("127.0.0.1", 4200);
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
			
			String clientMsg = "", serverMsg = "";
			while(!clientMsg.contentEquals("End")) {
				clientMsg = bReader.readLine();
				outStream.writeUTF(clientMsg);
				outStream.flush();
				
				serverMsg = inStream.readUTF();
				System.out.println(serverMsg);
			}
			outStream.close();
			inStream.close();
			socket.close();
		} catch(Exception E) {
			System.err.println("Client exception is: " + E.toString());
			E.printStackTrace();
		}
	}

}
