package com.comtrade.sisstemskaoperacija.let;

import java.util.ArrayList;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Let;
import com.comtrade.konekcija.Konekcija;

public class SistemskaOperacijaVratiListuSletanjaDanasNaAerodrom {
	public List<Let> izvrsiSistemskuOperacijuVratiListuSletanjaDanasNaAerodrom(int id,String datum) {
		List<Let> list = new ArrayList<>();
		try {
			pokreniTransakciju();
			list = izvrsiKonkretnuOperaciju(id,datum);
			potvrdiTransakciju();
		} catch (Exception e) {
			ponistiTransakciju();
		} finally {
			zatvoriKonekciju();
		}
		return list;

	}

	private List<Let> izvrsiKonkretnuOperaciju(int id,String datum) {
		IBroker ib = new Broker();
		List<Let> list = ib.vratiListuSletanjaDanasNaAerodrom(id, datum);
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
