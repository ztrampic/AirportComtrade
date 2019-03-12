package com.comtrade.domen;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Let implements OpstiDomen, Serializable {
	private int id_leta;
	private String oznaka;
	private int id_aerodroma_iz;
	private int id_aerodroma_za;
	private int id_aviona;
	private String datum;
	private String vreme;
	private int id_aviokompanije;
	
	
	
	public int getId_aviokompanije() {
		return id_aviokompanije;
	}

	public void setId_aviokompanije(int id_aviokompanije) {
		this.id_aviokompanije = id_aviokompanije;
	}

	public int getId_leta() {
		return id_leta;
	}

	public void setId_leta(int id_leta) {
		this.id_leta = id_leta;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public int getId_aerodroma_iz() {
		return id_aerodroma_iz;
	}

	public void setId_aerodroma_iz(int id_aerodroma_iz) {
		this.id_aerodroma_iz = id_aerodroma_iz;
	}

	public int getId_aerodroma_za() {
		return id_aerodroma_za;
	}

	public void setId_aerodroma_za(int id_aerodroma_za) {
		this.id_aerodroma_za = id_aerodroma_za;
	}

	public int getId_aviona() {
		return id_aviona;
	}

	public void setId_aviona(int id_aviona) {
		this.id_aviona = id_aviona;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	@Override
	public String vratiNazivZaUnos() {
		// TODO Auto-generated method stub
		return "values ('"+getOznaka()+"','"+getId_aerodroma_iz()+"','"+getId_aerodroma_za()+"','"+getId_aviona()+"','"+getDatum()+"','"+getVreme()+"','"+getId_aviokompanije()+"')";
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "let";
	}

	@Override
	public List<OpstiDomen> napuniListuZaSelect(ResultSet rs) {
		List<OpstiDomen>list=new ArrayList<>();
		try {
			while(rs.next()){
				int id_leta=rs.getInt("id_leta");
				String oznaka=rs.getString("oznaka");
				int id_iz=rs.getInt("id_aerodrom_iz");
				int id_za=rs.getInt("id_aerodrom_za");
				int id_aviona=rs.getInt("id_aviona");
				String datum=rs.getString("datum");
				String vreme=rs.getString("vreme");
				int id_kom = rs.getInt("id_aviokompanije");
				Let let=new Let();
				let.setDatum(datum);
				let.setId_aerodroma_iz(id_iz);
				let.setId_aerodroma_za(id_za);
				let.setId_aviona(id_aviona);
				let.setId_leta(id_leta);
				let.setOznaka(oznaka);
				let.setVreme(vreme);
				let.setId_aviokompanije(id_kom);
				list.add(let);
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
		return " where "+""+od.vratiNazivTabele()+".id_leta"+"="+""+getId_leta()+"";
	}

	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (oznaka, id_aerodrom_iz, id_aerodrom_za, id_aviona, datum, vreme, id_aviokompanije) ";
	}

	

}
