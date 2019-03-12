package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KarakteristikeAerodroma implements OpstiDomen,Serializable {
	
	private int id_aerodroma;
	private int broj_poletanja;
	private int broj_sletanja;
	private int broj_pisti;
	private String oznaka;
	private String opis;
	
	
	
	public int getId_aerodroma() {
		return id_aerodroma;
	}

	public void setId_aerodroma(int id_aerodroma) {
		this.id_aerodroma = id_aerodroma;
	}

	public int getBroj_poletanja() {
		return broj_poletanja;
	}

	public void setBroj_poletanja(int broj_poletanja) {
		this.broj_poletanja = broj_poletanja;
	}

	public int getBroj_sletanja() {
		return broj_sletanja;
	}

	public void setBroj_sletanja(int broj_sletanja) {
		this.broj_sletanja = broj_sletanja;
	}

	public int getBroj_pisti() {
		return broj_pisti;
	}

	public void setBroj_pisti(int broj_pisti) {
		this.broj_pisti = broj_pisti;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@Override
	public String vratiNazivZaUnos() {
		// TODO Auto-generated method stub
		return "values ('"+getId_aerodroma()+"','"+getBroj_poletanja()+"','"+getBroj_sletanja()+"','"+getBroj_pisti()+"','"+getOznaka()+"','"+getOpis()+"')";
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "karakteristike_aerodroma";
	}

	@Override
	public List<OpstiDomen> napuniListuZaSelect(ResultSet rs) {
		List<OpstiDomen>list = new ArrayList<>();
		try {
			while(rs.next()){
				int id_aerodroma = rs.getInt("id_aerodroma");
				int broj_poletanja=rs.getInt("broj_poletanja");
				int broj_sletanja = rs.getInt("broj_sletanja");
				int broj_pisti=rs.getInt("broj_pisti");
				String oznaka=rs.getString("oznaka");
				String opis=rs.getString("opis");
				KarakteristikeAerodroma ka=new KarakteristikeAerodroma();
				ka.setBroj_pisti(broj_pisti);
				ka.setBroj_poletanja(broj_poletanja);
				ka.setBroj_sletanja(broj_sletanja);
				ka.setId_aerodroma(id_aerodroma);
				ka.setOznaka(oznaka);
				ka.setOpis(opis);
				list.add(ka);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public String vratiNazivTabeleZaIzmenu(OpstiDomen od) {
		// TODO Auto-generated method stub
		return " SET "+""+"broj_poletanja"+"="+"'"+getBroj_poletanja()+"'"+","+"broj_sletanja"+"="+"'"+getBroj_sletanja()+"'"+","+"broj_pisti"+"="+"'"+getBroj_pisti()+"'"+","+"oznaka"+"="+"'"+getOznaka()+"'"+","+"opis"+"="+"'"+getOpis()+"'"+" WHERE "+od.vratiNazivTabele()+".id_aerodroma"+"="+" "+getId_aerodroma();
	}

	@Override
	public String vratiNazivTabeleZaBrisanje(OpstiDomen od) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (id_aerodroma, broj_poletanja, broj_sletanja, broj_pisti, oznaka, opis) ";
	}

}
