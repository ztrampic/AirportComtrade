package com.comtrade.sistemskaoperacija.profil;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Profil;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaRegistrujProfil extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) {
		try {
			Profil p = (Profil) obj;
			IBroker ib=new Broker();
			ib.upisi(p);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
