package com.comtrade.sisstemskaoperacija.let;

import java.util.ArrayList;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Let;
import com.comtrade.konekcija.Konekcija;

public class SistemskaOperacijaVratiListuLetovaZaKompaniju {
	public List<Let> izvrsiSistemskuOperacijuVratiListuLetovaZaKompaniju(Let let) {
		List<Let> list = new ArrayList<>();
		try {
			pokreniTransakciju();
			list = izvrsiKonkretnuOperaciju(let);
			potvrdiTransakciju();
		} catch (Exception e) {
			ponistiTransakciju();
		} finally {
			zatvoriKonekciju();
		}
		return list;

	}

	private List<Let> izvrsiKonkretnuOperaciju(Let let) {
		IBroker ib = new Broker();
		List<Let> list = ib.vratiListuTrazenogLetaZaKompaniju(let);
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
