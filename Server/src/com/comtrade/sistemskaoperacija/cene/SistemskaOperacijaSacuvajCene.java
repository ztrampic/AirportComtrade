package com.comtrade.sistemskaoperacija.cene;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.CenaKlasa;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaSacuvajCene extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) throws Exception {
			try {
				CenaKlasa ck = (CenaKlasa) obj ;
				IBroker ib =new Broker();
				ib.upisi(ck);
			} catch (Exception e) {
				// TODO: handle exception
			}

	}

}
