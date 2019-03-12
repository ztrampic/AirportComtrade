package com.comtrade.sistemskaoperacija.profil;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Profil;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaBrisanjeProfila extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) throws Exception {
		// TODO Auto-generated method stub
		try {
			Profil p =(Profil) obj;
			IBroker ib = new Broker();
			ib.obrisi(p);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
