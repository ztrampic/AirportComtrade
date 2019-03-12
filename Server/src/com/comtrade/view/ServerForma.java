package com.comtrade.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.comtrade.nitServer.NitServer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerForma {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ServerForma window = new ServerForma();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerForma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnPokreniServer = new JButton("Pokreni Server");
		btnPokreniServer.setBounds(52, 32, 290, 148);
		btnPokreniServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pokreniServer();
				
			}

			private void pokreniServer() {
				NitServer ns=new NitServer();
				ns.start();
			}

			
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnPokreniServer);
	}

}
