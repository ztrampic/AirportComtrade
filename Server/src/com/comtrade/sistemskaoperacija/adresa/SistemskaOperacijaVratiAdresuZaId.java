package com.comtrade.sistemskaoperacija.adresa;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Adresa;
import com.comtrade.domen.CenaKlasa;
import com.comtrade.konekcija.Konekcija;

public class SistemskaOperacijaVratiAdresuZaId {
	
	public Adresa izvrsiSistemskuOperacijuVratiAdresuZaId(int id) {
		Adresa adresa = new Adresa();
		try {
			pokreniTransakciju();
			adresa = izvrsiKonkretnuOperaciju(id);
			potvrdiTransakciju();
		} catch (Exception e) {
			ponistiTransakciju();
		} finally {
			zatvoriKonekciju();
		}
		return adresa;

	}

	private Adresa izvrsiKonkretnuOperaciju(int id) {
		
		IBroker ib = new Broker();
		Adresa a= ib.vratiAdresuZaId(id);
		return a;

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
