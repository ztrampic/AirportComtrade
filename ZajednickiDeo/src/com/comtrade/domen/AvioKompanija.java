package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AvioKompanija implements OpstiDomen, Serializable {

	private int id_aviokompanija;
	private String naziv;
	private int pib;
	private int adresa;

	public int getId_aviokompanija() {
		return id_aviokompanija;
	}

	public void setId_aviokompanija(int id_aviokompanija) {
		this.id_aviokompanija = id_aviokompanija;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getPib() {
		return pib;
	}

	public void setPib(int pib) {
		this.pib = pib;
	}

	public int getAdresa() {
		return adresa;
	}

	public void setAdresa(int adresa) {
		this.adresa = adresa;
	}

	@Override
	public String vratiNazivZaUnos() {
		// TODO Auto-generated method stub
		return "values ('" + getNaziv() + "','" + getPib() + "','" + getAdresa() + "')";
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "aviokompanija";
	}

	@Override
	public List<OpstiDomen> napuniListuZaSelect(ResultSet rs) {
		List<OpstiDomen> list = new ArrayList<>();
		try {
			while (rs.next()) {
				int id_aviokompanija = rs.getInt("id_aviokompanija");
				String naziv = rs.getString("naziv_aviokompanija");
				int pib = rs.getInt("pib");
				int id_adresa = rs.getInt("id_adresa");
				AvioKompanija a = new AvioKompanija();
				a.setId_aviokompanija(id_aviokompanija);
				a.setNaziv(naziv);
				a.setPib(pib);
				a.setAdresa(id_adresa);
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
		return null;
	}

	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (naziv_aviokompanija, pib, id_adresa) ";
	}

}
