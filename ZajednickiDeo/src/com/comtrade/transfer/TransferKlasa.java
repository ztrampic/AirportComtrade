package com.comtrade.transfer;

import java.io.Serializable;

import com.comtrade.enumi.Konstante;

public class TransferKlasa implements Serializable{
	private Konstante operacija;
	private Object klijent_slanje;
	private Object server_odgovor;
	private String server_odgovor_poruka;
	
	public Konstante getOperacija() {
		return operacija;
	}
	public void setOperacija(Konstante k) {
		this.operacija = k;
	}
	public Object getKlijent_slanje() {
		return klijent_slanje;
	}
	public void setKlijent_slanje(Object klijent_slanje) {
		this.klijent_slanje = klijent_slanje;
	}
	public Object getServer_odgovor() {
		return server_odgovor;
	}
	public void setServer_odgovor(Object server_odgovor) {
		this.server_odgovor = server_odgovor;
	}
	public String getServer_odgovor_poruka() {
		return server_odgovor_poruka;
	}
	public void setServer_odgovor_poruka(String server_odgovor_poruka) {
		this.server_odgovor_poruka = server_odgovor_poruka;
	}
	
}
