package com.comtrade.sistemskaoperacija.zahtev;

import java.util.ArrayList;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Let;
import com.comtrade.domen.Zahtev;
import com.comtrade.konekcija.Konekcija;

public class SistemskaOperacijaVratiListuAktivnihZahteva {
	
	public List<Zahtev> izvrsiSistemskuOperacijuVratiListuAktivnihZahteva(Object obj) {
		List<Zahtev> list = new ArrayList<>();
		try {
			pokreniTransakciju();
			list = izvrsiKonkretnuOperaciju(obj);
			potvrdiTransakciju();
		} catch (Exception e) {
			ponistiTransakciju();
		} finally {
			zatvoriKonekciju();
		}
		return list;

	}

	private List<Zahtev> izvrsiKonkretnuOperaciju(Object obj) {
		int status_zahteva = (int) obj;
		IBroker ib = new Broker();
		List<Zahtev> list = ib.vratiListuAktivnihZahteva(status_zahteva);
		return list;

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
