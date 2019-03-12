package com.comtrade.sisstemskaoperacija.let;

import java.util.HashMap;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Let;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaVratiListuLetova extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) {
		
		HashMap<String, Object>map=(HashMap<String, Object>) obj;
		OpstiDomen od=new Let();
		od=(OpstiDomen) map.get("transfer");
		IBroker ib=new Broker();
		List<OpstiDomen>op=ib.uzmiSveIzTabele(od);
		map.put("transfer_list", op);

	}

}
