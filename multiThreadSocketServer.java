import java.net.*;
import java.io.*;
import java.util.*;

import multithread.workerServerThread;

public class multiThreadSocketServer {

	public static void main(String[] args) throws Exception{

		try {
			ServerSocket server = new ServerSocket(4200);
			int ct = 0;
			System.out.println("Server has started");
			
			while(true) {
				ct++;
				Socket serverClient = server.accept();
				System.out.println("Client No: " + ct + " has connected!");
				
				workerServerThread sct = new workerServerThread(serverClient, ct);
				sct.start();
			}
		} catch(Exception E) {
			System.err.println("Server exception is: " + E.toString());
			E.printStackTrace();
		}
	}

}
