package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Adresa implements OpstiDomen,Serializable{
	private int id_adresa;
	private String naziv_ulice_broj;
	private String naziv_grada;
	private String naziv_drzave;
	
	public int getId_adresa() {
		return id_adresa;
	}
	public void setId_adresa(int id_adresa) {
		this.id_adresa = id_adresa;
	}
	public String getNaziv_ulice_broj() {
		return naziv_ulice_broj;
	}
	public void setNaziv_ulice_broj(String naziv_ulice_broj) {
		this.naziv_ulice_broj = naziv_ulice_broj;
	}
	public String getNaziv_grada() {
		return naziv_grada;
	}
	public void setNaziv_grada(String naziv_grada) {
		this.naziv_grada = naziv_grada;
	}
	public String getNaziv_drzave() {
		return naziv_drzave;
	}
	public void setNaziv_drzave(String naziv_drzave) {
		this.naziv_drzave = naziv_drzave;
	}
	@Override
	public String vratiNazivZaUnos() {
		// TODO Auto-generated method stub
		return "values ('"+getNaziv_ulice_broj()+"','"+getNaziv_grada()+"','"+getNaziv_drzave()+"')";
	}
	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "adresa";
	}
	@Override
	public List<OpstiDomen> napuniListuZaSelect(ResultSet rs) {
		List<OpstiDomen>list =new ArrayList<>();
		try {
			while(rs.next()){
				int id_adresa=rs.getInt("id_adresa");
				String naziv_ulice_broj=rs.getString("naziv_ulice_broj");
				String naziv_grada=rs.getString("naziv_grada");
				String naziv_drzave=rs.getString("naziv_drzave");
				Adresa a=new Adresa();
				a.setId_adresa(id_adresa);
				a.setNaziv_ulice_broj(naziv_ulice_broj);
				a.setNaziv_grada(naziv_grada);
				a.setNaziv_drzave(naziv_drzave);
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public String vratiNazivTabeleZaIzmenu(OpstiDomen od) {
		// TODO Auto-generated method stub
		return " SET "+""+"naziv_ulice_broj"+"="+"'"+getNaziv_ulice_broj()+"'"+","+"naziv_grada"+"="+"'"+getNaziv_grada()+"'"+","+"naziv_drzave"+"="+"'"+getNaziv_drzave()+"'"+" WHERE "+od.vratiNazivTabele()+".id_adresa"+"="+" "+getId_adresa();
	}     												
	@Override
	public String vratiNazivTabeleZaBrisanje(OpstiDomen od) {
		// TODO Auto-generated method stub
		return " where "+""+od.vratiNazivTabele()+".id_adresa"+"="+""+getId_adresa()+"";
	}
	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (naziv_ulice_broj, naziv_grada, naziv_drzave) ";
	}

	
}
