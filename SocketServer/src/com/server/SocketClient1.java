package com.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class SocketClient1 {
	JTextField outgoing;
	JTextArea incoming;
	PrintWriter writer;
	Socket socket;
	BufferedReader reader;

	public void go() {
		JFrame frame = new JFrame("Test");
		JPanel mainPanel = new JPanel();
		outgoing = new JTextField(20);
		incoming=new JTextArea(15,50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		JScrollPane qScoPane =new JScrollPane(incoming);
		qScoPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScoPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		mainPanel.add(incoming);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		setNetWorking();
		Thread thread = new Thread(new IncomingReader());
		thread.start();
		frame.setSize(400, 500);
		frame.setVisible(true);
	}

	private void setNetWorking() {
		try {
			socket = new Socket("localhost", 5000);
			InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(socket.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public class SendButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			writer.println(outgoing.getText());
			writer.flush();
			outgoing.setText("");			
			
			outgoing.requestDefaultFocus();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SocketClient1().go();
	}

	public class IncomingReader implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String message;
			try {
				while((message=reader.readLine())!=null){
					incoming.append(message+"\n");
					System.out.println(message);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
