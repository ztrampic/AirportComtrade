package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public class Rezervacija implements OpstiDomen, Serializable {
	
	private int id_rezervacije;
	private int id_profila;
	private int id_leta;
	private int broj_karata;
	private int id_aviokompanije;
	private double cena;
		
	
	
	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getId_aviokompanije() {
		return id_aviokompanije;
	}

	public void setId_aviokompanije(int id_aviokompanije) {
		this.id_aviokompanije = id_aviokompanije;
	}

	public int getId_rezervacije() {
		return id_rezervacije;
	}

	public void setId_rezervacije(int id_rezervacije) {
		this.id_rezervacije = id_rezervacije;
	}

	public int getId_profila() {
		return id_profila;
	}

	public void setId_profila(int id_profila) {
		this.id_profila = id_profila;
	}

	public int getId_leta() {
		return id_leta;
	}

	public void setId_leta(int id_leta) {
		this.id_leta = id_leta;
	}

	public int getBroj_karata() {
		return broj_karata;
	}

	public void setBroj_karata(int broj_karata) {
		this.broj_karata = broj_karata;
	}

	@Override
	public String vratiNazivZaUnos() {
		// TODO Auto-generated method stub
		return "values ('"+getId_profila()+"','"+getId_leta()+"','"+getBroj_karata()+"','"+getId_aviokompanije()+"','"+getCena()+"')";
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "rezervacija";
	}

	@Override
	public List<OpstiDomen> napuniListuZaSelect(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vratiNazivTabeleZaIzmenu(OpstiDomen od) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vratiNazivTabeleZaBrisanje(OpstiDomen od) {
		// TODO Auto-generated method stub
		return " where "+""+od.vratiNazivTabele()+".id_rezervacije"+"="+""+getId_rezervacije()+"";
	}

	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (id_profila, id_leta, broj_karata, id_aviokompanija, cena ) ";
	}

}
