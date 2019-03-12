package com.comtrade.sisstemskaoperacija.radnik;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.CenaKlasa;
import com.comtrade.konekcija.Konekcija;

public class SistemskaOperacijaVratiNazivKompanijeRadnika {
		
	public String izvrsiSistemskuOperacijuVratiNazivKompanijeRadnika(int idRadnika) {
		String naziv = null;
		try {
			pokreniTransakciju();
			naziv = izvrsiKonkretnuOperaciju(idRadnika);
			potvrdiTransakciju();
		} catch (Exception e) {
			ponistiTransakciju();
		} finally {
			zatvoriKonekciju();
		}
		return naziv;

	}

	

	private String izvrsiKonkretnuOperaciju(int idRadnika) {
		IBroker ib =new Broker();
		String naziv = ib.vratiNazivKompanijeRadnika(idRadnika);
		return naziv;
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
