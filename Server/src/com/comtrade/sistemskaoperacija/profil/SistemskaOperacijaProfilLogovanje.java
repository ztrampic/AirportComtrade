package com.comtrade.sistemskaoperacija.profil;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Profil;
import com.comtrade.konekcija.Konekcija;

public class SistemskaOperacijaProfilLogovanje {
	
	public int izvrsiSistemskuOperacijuProfilLogovanje(Object obj){
		int profil = 0;
		try {
			pokreniTransakciju();
			profil=izvrsiKonkretnuOperaciju(obj);
			potvrdiTransakciju();
		} catch (Exception e) {
			ponistiTransakciju();
		}
		finally {
			zatvoriKonekciju();
		}
		return profil;
	
	
}
private int izvrsiKonkretnuOperaciju(Object obj){
	Profil p=(Profil) obj;
	String user=p.getUsername();
	String pass=p.getPassword();
	IBroker ib=new Broker();
	int profil =ib.vratiProfil(user, pass);
	return profil;
	
	
}
private void zatvoriKonekciju() {
	Konekcija.getInstanca().zatvoriKonekciju();
	
}
private void ponistiTransakciju() {
	Konekcija.getInstanca().ponistiTransakciju();
	
	
}
private void potvrdiTransakciju() {
	Konekcija.getInstanca().potvrdiTransakciju();
	
}
private void pokreniTransakciju() {
	Konekcija.getInstanca().pokreniTransakciju();
	
}
}
