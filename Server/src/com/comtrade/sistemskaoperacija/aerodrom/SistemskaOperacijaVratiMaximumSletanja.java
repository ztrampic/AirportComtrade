package com.comtrade.sistemskaoperacija.aerodrom;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.konekcija.Konekcija;

public class SistemskaOperacijaVratiMaximumSletanja {
	public int izvrsiSistemskaOperacijaVratiMaximumSletanja(Object obj) {
		int vrednost = 0;
		try {
			pokreniTransakciju();
			vrednost = izvrsiKonkretnuOperaciju(obj);
			potvrdiTransakciju();
		} catch (Exception e) {
			ponistiTransakciju();
		} finally {
			zatvoriKonekciju();
		}
		return vrednost;

	}

	private int izvrsiKonkretnuOperaciju(Object obj) {
		int id=(int) obj;
		IBroker ib = new Broker();
		int vrednost = ib.vratiMaxSletanja(id);
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
