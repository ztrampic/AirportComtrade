package com.comtrade.sistemskaoperacija.aerodrom.karakteristike;

import java.util.HashMap;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.KarakteristikeAerodroma;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaVratiListuKarakteristikaAerodroma extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) {
		HashMap<String, Object>map=(HashMap<String, Object>) obj;
		OpstiDomen od=new KarakteristikeAerodroma();
		od=(OpstiDomen) map.get("transfer");
		IBroker ib=new Broker();
		List<OpstiDomen>op=ib.uzmiSveIzTabele(od);
		map.put("transfer_list", op);
		

	}

}
