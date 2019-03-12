package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Avion implements OpstiDomen, Serializable {
	private int id_avion;
	private String oznaka;
	private String model_avion;
	private int broj_sedista;
	private int id_aviokompanija;
	
	

	public int getId_avion() {
		return id_avion;
	}

	public void setId_avion(int id_avion) {
		this.id_avion = id_avion;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getModel_avion() {
		return model_avion;
	}

	public void setModel_avion(String model_avion) {
		this.model_avion = model_avion;
	}

	public int getBroj_sedista() {
		return broj_sedista;
	}

	public void setBroj_sedista(int broj_sedista) {
		this.broj_sedista = broj_sedista;
	}

	public int getId_aviokompanija() {
		return id_aviokompanija;
	}

	public void setId_aviokompanija(int id_aviokompanija) {
		this.id_aviokompanija = id_aviokompanija;
	}

	@Override
	public String vratiNazivZaUnos() {
		// TODO Auto-generated method stub
		return "values ('"+getOznaka()+"','"+getModel_avion()+"','"+getBroj_sedista()+"','"+getId_aviokompanija()+"')";
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return " avion ";
	}

	@Override
	public List<OpstiDomen> napuniListuZaSelect(ResultSet rs) {
		List<OpstiDomen>list=new ArrayList<>();
		try {
			while(rs.next()){
				int id_avion = rs.getInt("id_avion");
				String oznaka=rs.getString("oznaka");
				String model_avion=rs.getString("model_avion");
				int broj_sedista=rs.getInt("broj_sedista");
				int id_aviokompanija=rs.getInt("id_aviokompanija");
				Avion a=new Avion();
				a.setId_avion(id_avion);
				a.setOznaka(oznaka);
				a.setModel_avion(model_avion);
				a.setBroj_sedista(broj_sedista);
				a.setId_aviokompanija(id_aviokompanija);
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
		return null;
	}

	@Override
	public String vratiNazivTabeleZaBrisanje(OpstiDomen od) {
		// TODO Auto-generated method stub
		return " where "+""+od.vratiNazivTabele()+".id_avion"+"="+""+getId_avion()+"";
	}

	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (oznaka, model_avion, broj_sedista, id_aviokompanija) ";
	}

}
