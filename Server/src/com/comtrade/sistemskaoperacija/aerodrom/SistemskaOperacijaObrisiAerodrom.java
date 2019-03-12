package com.comtrade.sistemskaoperacija.aerodrom;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Aerodrom;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaObrisiAerodrom extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) {
		try {
			Aerodrom a=(Aerodrom) obj;
			IBroker ib=new Broker();
			ib.obrisi(a);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
