package com.comtrade.sistemskaoperacija.profil;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Profil;
import com.comtrade.konekcija.Konekcija;

public class SistemskaOperacijaVratiProfilZaEMail {
	
	public Profil izvrsiSistemskuOperacijuVratiProfilZaEMail(String email) {
		Profil profil = new Profil();
		try {
			pokreniTransakciju();
			profil = izvrsiKonkretnuOperaciju(email);
			potvrdiTransakciju();
		} catch (Exception e) {
			ponistiTransakciju();
		} finally {
			zatvoriKonekciju();
		}
		return profil;

	}

	private Profil izvrsiKonkretnuOperaciju(String email) {
		IBroker ib = new Broker();
		Profil p= ib.vratiProfilZaEMail(email);
		return p;

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
