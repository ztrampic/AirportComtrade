package com.comtrade.sisstemskaoperacija.radnik;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Radnik;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaUnosRadnika extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) {
		try {
			Radnik r=(Radnik) obj;
			IBroker ib=new Broker();
			ib.upisi(r);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
