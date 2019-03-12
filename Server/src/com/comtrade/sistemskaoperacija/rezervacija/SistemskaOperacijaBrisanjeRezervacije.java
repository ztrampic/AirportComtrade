package com.comtrade.sistemskaoperacija.rezervacija;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Rezervacija;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaBrisanjeRezervacije extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) throws Exception {
		try {
			Rezervacija r= (Rezervacija) obj;
			IBroker ib =new Broker();
			ib.obrisi(r);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
