package com.comtrade.sistemskaoperacija.profil;

import java.util.HashMap;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.Profil;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;

public class SistemskaOperacijaVratiListuProfila extends OpstaSistemskaOperacija{

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(Object obj) {
		try {
			HashMap<String, Object>map=(HashMap<String, Object>) obj;
			OpstiDomen od=new Profil();
			od=(OpstiDomen) map.get("transfer");
			IBroker ib = new Broker();
			List<OpstiDomen>op=ib.uzmiSveIzTabele(od);
			map.put("transfer_list", op);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
