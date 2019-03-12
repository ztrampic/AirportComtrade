package com.comtrade.sistemskaoperacija.profil;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Profil;
import com.comtrade.konekcija.Konekcija;

public class SistemskaOperacijaVratiProfilZaId {
	
	public Profil izvrsiSistemskuOperacijuVratiProfilZaId(int id) {
		Profil profil = new Profil();
		try {
			pokreniTransakciju();
			profil = izvrsiKonkretnuOperaciju(id);
			potvrdiTransakciju();
		} catch (Exception e) {
			ponistiTransakciju();
		} finally {
			zatvoriKonekciju();
		}
		return profil;

	}

	private Profil izvrsiKonkretnuOperaciju(int id) {
		IBroker ib = new Broker();
		Profil p= ib.vratiProfilZaId(id);
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
