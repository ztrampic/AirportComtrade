package com.comtrade.sistemskaoperacija.aerodrom.karakteristike;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.KarakteristikeAerodroma;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaUnosKarakteristikaAerodroma extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) {
			try {
				KarakteristikeAerodroma ka= (KarakteristikeAerodroma) obj;
				IBroker ib=new Broker();
				ib.upisi(ka);
			} catch (Exception e) {
				// TODO: handle exception
			}

	}

}
