package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatusProfila implements OpstiDomen,Serializable{
	
	private int id_status;
	private String naziv;
	
	
	public int getId_status() {
		return id_status;
	}
	public void setId_status(int id_status) {
		this.id_status = id_status;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	@Override
	public String vratiNazivZaUnos() {
		// TODO Auto-generated method stub
		return "values ('"+getNaziv()+"')";
	}
	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "status_korisnika";
	}
	@Override
	public List<OpstiDomen> napuniListuZaSelect(ResultSet rs) {
		List<OpstiDomen>list=new ArrayList<>();
		try {
			while(rs.next()){
				int id_status=rs.getInt("id_status_korisnika");
				String naziv=rs.getString("naziv_statusa");
				StatusProfila sp=new StatusProfila();
				sp.setId_status(id_status);
				sp.setNaziv(naziv);
				list.add(sp);
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
		return null;
	}
	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " ( naziv_statusa ) ";
	}
	
}
