package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Aerodrom implements OpstiDomen,Serializable{
	
	private int id_aerodroma;
	private String naziv;
	private int id_adresa;
	
	
	public int getId_aerodroma() {
		return id_aerodroma;
	}
	public void setId_aerodroma(int id_aerodroma) {
		this.id_aerodroma = id_aerodroma;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getId_adresa() {
		return id_adresa;
	}
	public void setId_adresa(int id_adresa) {
		this.id_adresa = id_adresa;
	}
	@Override
	public String vratiNazivZaUnos() {
		// TODO Auto-generated method stub
		return " values ('"+getNaziv()+"','"+getId_adresa()+"') ";
	}
	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return " aerodrom ";
	}
	@Override
	public List<OpstiDomen> napuniListuZaSelect(ResultSet rs) {
		List<OpstiDomen>list=new ArrayList<>();
		try {
			while(rs.next()){
				int id_aerodrom=rs.getInt("id_aerodrom");
				String naziv=rs.getString("naziv");
				int id_adresa=rs.getInt("id_adresa");
				Aerodrom a=new Aerodrom();
				a.setId_aerodroma(id_aerodrom);
				a.setNaziv(naziv);
				a.setId_adresa(id_adresa);
				list.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	@Override
	public String vratiNazivTabeleZaIzmenu(OpstiDomen od) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String vratiNazivTabeleZaBrisanje(OpstiDomen od) {
		// TODO Auto-generated method stub
		return " where "+""+od.vratiNazivTabele()+".id_aerodrom"+"="+""+getId_aerodroma()+"";
	}
	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (naziv, id_adresa) ";
	}
	
		
	
	
	
	
}
