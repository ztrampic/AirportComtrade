package com.comtrade.sistemskaoperacija.profil;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Profil;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaIzmenaKontaNaProfilu extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) throws Exception {
		Profil p =(Profil) obj;
		IBroker ib = new Broker();
		ib.izmeni(p);

	}

}
