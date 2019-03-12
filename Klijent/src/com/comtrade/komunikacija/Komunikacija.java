package com.comtrade.komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.comtrade.enumi.AdresaPort;
import com.comtrade.transfer.TransferKlasa;

public class Komunikacija {

	private static Komunikacija instanca;
	private Socket soket;

	private Komunikacija() {
		try {
			soket = new Socket(AdresaPort.ADRESA.getAdresa(), AdresaPort.PORT.getPort());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Komunikacija getInstanca() {
		if (instanca == null) {
			instanca = new Komunikacija();
		}
		return instanca;
	}

	public void posaljiPodatkeNaServer(TransferKlasa tf) {
		ObjectOutputStream outSoket;
		try {
			outSoket = new ObjectOutputStream(soket.getOutputStream());
			outSoket.writeObject(tf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public TransferKlasa prihvatiPodatkeSaServera() throws ClassNotFoundException, IOException {
		ObjectInputStream inSoket = null;
		try {
			inSoket = new ObjectInputStream(soket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (TransferKlasa) inSoket.readObject();

	}

}
