package org.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatClientGui {

	private JFrame frmClient;
	public static JEditorPane editorPaneToServer;
	public static JEditorPane editorPaneFromServer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClientGui window = new ChatClientGui();
					window.frmClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChatClientGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClient = new JFrame();
		frmClient.setTitle("Client");
		frmClient.setBounds(100, 100, 837, 478);
		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClient.getContentPane().setLayout(null);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setBounds(693, 11, 89, 23);
		frmClient.getContentPane().add(btnConnect);
		
		JScrollPane scrollPaneFromServer = new JScrollPane();
		scrollPaneFromServer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneFromServer.setBounds(25, 50, 766, 152);
		frmClient.getContentPane().add(scrollPaneFromServer);
		
		editorPaneFromServer = new JEditorPane();
		scrollPaneFromServer.setViewportView(editorPaneFromServer);
		
		JLabel lblFromServer = new JLabel("From Server");
		lblFromServer.setBounds(24, 30, 80, 14);
		frmClient.getContentPane().add(lblFromServer);
		
		JScrollPane scrollPaneToServer = new JScrollPane();
		scrollPaneToServer.setBounds(25, 238, 766, 94);
		frmClient.getContentPane().add(scrollPaneToServer);
		
		editorPaneToServer = new JEditorPane();
		scrollPaneToServer.setViewportView(editorPaneToServer);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//to server
				
			}
		});
		btnSubmit.setBounds(693, 348, 89, 23);
		frmClient.getContentPane().add(btnSubmit);
		
		JLabel lblToServer = new JLabel("To Server");
		lblToServer.setBounds(25, 213, 150, 14);
		frmClient.getContentPane().add(lblToServer);
	}
}
