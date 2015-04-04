package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

public class MyServer {

	ArrayList clientOutputStream;
	
	public class ClientHandler implements Runnable{
		
		BufferedReader reader;
		Socket sock;
		
		public  ClientHandler(Socket clientSocket) {
			// TODO Auto-generated constructor stub
			sock = clientSocket;
			try {
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(isReader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String message;
			try {
				while((message=reader.readLine())!=null){
					System.out.println("read "+message);
					tellEveryone(message);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args){
		new MyServer().go();
	}
	
	public void go(){
		clientOutputStream = new ArrayList();
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			while(true){
				Socket cliSocket = serverSocket.accept();
				PrintWriter writer = new PrintWriter(cliSocket.getOutputStream());
				clientOutputStream.add(writer);
				
				Thread t = new Thread(new ClientHandler(cliSocket));
				t.start();
				System.out.println("got a connection");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void tellEveryone(String message){
		Iterator it = clientOutputStream.iterator();
		while(it.hasNext()){
			PrintWriter writer = (PrintWriter)it.next();
			writer.println(message);
			writer.flush();
		}
	}
}
