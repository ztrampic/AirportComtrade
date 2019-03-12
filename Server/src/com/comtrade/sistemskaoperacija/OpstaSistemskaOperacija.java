package com.comtrade.sistemskaoperacija;

import com.comtrade.konekcija.Konekcija;

public abstract class OpstaSistemskaOperacija {
	
	public void izvrsiSistemskuOperaciju(Object ob){
		try{
			pokreniTransakciju();
			izvrsiKonkretnuSistemskuOperaciju(ob);
			potvrdiTransakciju();
		}catch (Exception e) {
			ponistiTransakciju();
		}
		finally{
			zatvoriKonekciju();
		}
	}
	private void zatvoriKonekciju() {
		Konekcija.getInstanca().zatvoriKonekciju();
		
	}
	private void ponistiTransakciju() {
		Konekcija.getInstanca().ponistiTransakciju();
		
		
	}
	private void potvrdiTransakciju() {
		Konekcija.getInstanca().potvrdiTransakciju();
		
	}
	private void pokreniTransakciju() {
		Konekcija.getInstanca().pokreniTransakciju();
		
	}
	
	public abstract void izvrsiKonkretnuSistemskuOperaciju(Object obj) throws Exception;
	

	
}
