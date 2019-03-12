package com.comtrade.nitServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.comtrade.enumi.AdresaPort;
import com.comtrade.nitObradaKlijenta.NitObradaKlijent;

public class NitServer extends Thread{
	
	@Override
	public void run(){
		pokreniServer();
	}

	private void pokreniServer() {
		try {
			ServerSocket ss=new ServerSocket(AdresaPort.PORT.getPort());
			while(true){
				Socket soket=ss.accept();
				NitObradaKlijent n=new NitObradaKlijent();
				n.setSoket(soket);
				n.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
