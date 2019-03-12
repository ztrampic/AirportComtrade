package com.comtrade.sistemskaoperacija.adresa;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Adresa;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaUnosAdresa extends OpstaSistemskaOperacija{

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) {
		try{
			Adresa a=(Adresa) obj;
			IBroker ib=new Broker();
			ib.upisi(a);		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
