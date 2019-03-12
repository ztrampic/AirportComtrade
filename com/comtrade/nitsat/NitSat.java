package com.comtrade.nitsat;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class NitSat extends Thread {
	private JLabel label;
	
	public NitSat(JLabel label) {
		super();
		this.label = label;
	}
	public void run() {
		SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");
		while(true) {
			
			label.setText(sdf.format(new Date()));
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
