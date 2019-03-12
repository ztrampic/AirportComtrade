package com.comtrade.sistemskaoperacija.rezervacija;

import java.util.ArrayList;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Let;
import com.comtrade.domen.Rezervacija;
import com.comtrade.konekcija.Konekcija;

public class SistemskaOperacijaVratiListuRezervacijaZaId {
	
	public List<Rezervacija> izvrsiSistemskuOperacijuVratiListuRezervacijaZaId(int id) {
		List<Rezervacija> list = new ArrayList<>();
		try {
			pokreniTransakciju();
			list = izvrsiKonkretnuOperaciju(id);
			potvrdiTransakciju();
		} catch (Exception e) {
			ponistiTransakciju();
		} finally {
			zatvoriKonekciju();
		}
		return list;

	}

	private List<Rezervacija> izvrsiKonkretnuOperaciju(int id) {
		IBroker ib = new Broker();
		List<Rezervacija> list = ib.vratiListuRezervacijaZaId(id);
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
