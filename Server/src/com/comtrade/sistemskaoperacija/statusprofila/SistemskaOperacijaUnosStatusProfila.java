package com.comtrade.sistemskaoperacija.statusprofila;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.StatusProfila;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaUnosStatusProfila extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) {
		try {
			StatusProfila sp=(StatusProfila) obj;
			IBroker ib=new Broker();
			ib.upisi(sp);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
