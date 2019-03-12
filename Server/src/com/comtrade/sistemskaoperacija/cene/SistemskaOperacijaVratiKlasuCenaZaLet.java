package com.comtrade.sistemskaoperacija.cene;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.CenaKlasa;
import com.comtrade.konekcija.Konekcija;

public class SistemskaOperacijaVratiKlasuCenaZaLet {
	
	public CenaKlasa izvrsiSistemskuOperacijuVratiKlasuCenaZaLet(CenaKlasa cenaK) {
		CenaKlasa cenaKlasa = null;
		try {
			pokreniTransakciju();
			cenaKlasa = izvrsiKonkretnuOperaciju(cenaK);
			potvrdiTransakciju();
		} catch (Exception e) {
			ponistiTransakciju();
		} finally {
			zatvoriKonekciju();
		}
		return cenaKlasa;

	}

	private CenaKlasa izvrsiKonkretnuOperaciju(CenaKlasa cena) {
		
		IBroker ib = new Broker();
		CenaKlasa ck= ib.vratiCenuKlasaZaIzabraniLet(cena);
		return ck;

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
