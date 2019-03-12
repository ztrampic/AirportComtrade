package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public class Zahtev implements OpstiDomen,Serializable{
	private int id_zahteva;
	private String zahtev_txt;
	private int status_zahteva;
	public int getId_zahteva() {
		return id_zahteva;
	}
	public void setId_zahteva(int id_zahteva) {
		this.id_zahteva = id_zahteva;
	}
	public String getZahtev_txt() {
		return zahtev_txt;
	}
	public void setZahtev_txt(String zahtev_txt) {
		this.zahtev_txt = zahtev_txt;
	}
	public int getStatus_zahteva() {
		return status_zahteva;
	}
	public void setStatus_zahteva(int status_zahteva) {
		this.status_zahteva = status_zahteva;
	}
	@Override
	public String vratiNazivZaUnos() {
		// TODO Auto-generated method stub
		return "values ('"+getZahtev_txt()+"','"+getStatus_zahteva()+"')";
	}
	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "zahtev";
	}
	@Override
	public List<OpstiDomen> napuniListuZaSelect(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String vratiNazivTabeleZaIzmenu(OpstiDomen od) {
		// TODO Auto-generated method stub
		return " SET status_zahteva = "+"'"+getStatus_zahteva()+"'"+" WHERE "+od.vratiNazivTabele()+".id_zahtev = "+getId_zahteva();
	}
	@Override
	public String vratiNazivTabeleZaBrisanje(OpstiDomen od) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (zahtev_txt, status_zahteva) ";
	}
	@Override
	public String toString() {
		return " zahtev_txt ";
	}
	
	
}
