package com.comtrade.sistemskaoperacija.aerodrom;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.konekcija.Konekcija;

public class SistemskaOperacijaVratiBrojSletanjaZaDatum {
	
	public int izvrsiSistemskuOperacijuVratiBrojSletanjaZaDatum(String datum, int id) {
		int vrednost = 0;
		try {
			pokreniTransakciju();
			vrednost = izvrsiKonkretnuOperaciju(datum,id);
			potvrdiTransakciju();
		} catch (Exception e) {
			ponistiTransakciju();
		} finally {
			zatvoriKonekciju();
		}
		return vrednost;

	}

	private int izvrsiKonkretnuOperaciju(String datum, int id) {
		IBroker ib = new Broker();
		int vrednost = ib.vratiVrednostBrojaSletanjaZaDatum(datum,id);
		return vrednost;

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
