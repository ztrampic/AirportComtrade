package com.comtrade.sistemskaoperacija.aviokompanija;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.AvioKompanija;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaUnosAvioKompanije extends OpstaSistemskaOperacija{

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) {
		try{
			AvioKompanija ak= (AvioKompanija) obj;
			IBroker ib=new Broker();
			ib.upisi(ak);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
