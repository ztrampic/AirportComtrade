package com.comtrade.sisstemskaoperacija.let;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Let;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaBrisanjeLeta extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) throws Exception {
		
			Let let = (Let) obj;
			IBroker ib=new Broker();
			ib.obrisi(let);
		

	}

}
