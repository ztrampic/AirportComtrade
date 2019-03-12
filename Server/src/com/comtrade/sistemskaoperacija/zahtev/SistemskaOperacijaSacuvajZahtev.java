package com.comtrade.sistemskaoperacija.zahtev;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Zahtev;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaSacuvajZahtev extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) throws Exception {
		Zahtev zahtev = (Zahtev) obj;
		IBroker ib=new Broker();
		ib.upisi(zahtev);

	}

}
