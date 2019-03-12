package com.comtrade.sistemskaoperacija.avion;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Avion;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaUnosAviona extends OpstaSistemskaOperacija{

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) {
		try{
			Avion a=(Avion) obj;
			IBroker ib= new Broker();
			ib.upisi(a);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
