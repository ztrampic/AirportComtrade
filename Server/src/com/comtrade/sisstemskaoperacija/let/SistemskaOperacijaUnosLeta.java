package com.comtrade.sisstemskaoperacija.let;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Let;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaUnosLeta extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) {
		try {
			Let let=(Let) obj;
			IBroker ib=new Broker();
			ib.upisi(let);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
