package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public class Radnik implements OpstiDomen,Serializable {
	
	private String naziv_aviokompanije;
	private int id_profila;
	

	public String getNaziv_aviokompanije() {
		return naziv_aviokompanije;
	}

	public void setNaziv_aviokompanije(String naziv_aviokompanije) {
		this.naziv_aviokompanije = naziv_aviokompanije;
	}

	public int getId_profila() {
		return id_profila;
	}

	public void setId_profila(int id_profila) {
		this.id_profila = id_profila;
	}

	@Override
	public String vratiNazivZaUnos() {
		// TODO Auto-generated method stub
		return "values ('"+getNaziv_aviokompanije()+"','"+getId_profila()+"')";
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "radnik";
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
		return null;
	}

	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (naziv_aviokompanije, id_profila) ";
	}

}
