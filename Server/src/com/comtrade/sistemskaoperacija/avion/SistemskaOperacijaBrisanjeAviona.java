package com.comtrade.sistemskaoperacija.avion;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Avion;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaBrisanjeAviona extends OpstaSistemskaOperacija{

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) throws Exception {
		// TODO Auto-generated method stub
			
			Avion a=(Avion) obj;
			IBroker ib=new Broker();
			ib.obrisi(a);
		
		
			
		
	}

}
