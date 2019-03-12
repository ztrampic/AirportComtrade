package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public class CenaKlasa implements OpstiDomen, Serializable {

	private int id;
	private int id_leta;
	private int id_aviokompanije;
	private double ekonomska;
	private double biznis;
	private double a_klasa;
	private double odrasli;
	private double deca;
	private double dan30;
	private double dan15;
	private double dan2;
	private int broj_karata;

	public int getBroj_karata() {
		return broj_karata;
	}

	public void setBroj_karata(int broj_karata) {
		this.broj_karata = broj_karata;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_leta() {
		return id_leta;
	}

	public void setId_leta(int id_leta) {
		this.id_leta = id_leta;
	}

	public int getId_aviokompanije() {
		return id_aviokompanije;
	}

	public void setId_aviokompanije(int id_aviokompanije) {
		this.id_aviokompanije = id_aviokompanije;
	}

	public double getEkonomska() {
		return ekonomska;
	}

	public void setEkonomska(double ekonomska) {
		this.ekonomska = ekonomska;
	}

	public double getBiznis() {
		return biznis;
	}

	public void setBiznis(double biznis) {
		this.biznis = biznis;
	}

	public double getA_klasa() {
		return a_klasa;
	}

	public void setA_klasa(double a_klasa) {
		this.a_klasa = a_klasa;
	}

	public double getOdrasli() {
		return odrasli;
	}

	public void setOdrasli(double odrasli) {
		this.odrasli = odrasli;
	}

	public double getDeca() {
		return deca;
	}

	public void setDeca(double deca) {
		this.deca = deca;
	}

	public double getDan30() {
		return dan30;
	}

	public void setDan30(double dan30) {
		this.dan30 = dan30;
	}

	public double getDan15() {
		return dan15;
	}

	public void setDan15(double dan15) {
		this.dan15 = dan15;
	}

	public double getDan2() {
		return dan2;
	}

	public void setDan2(double dan2) {
		this.dan2 = dan2;
	}

	@Override
	public String vratiNazivZaUnos() {
		// TODO Auto-generated method stub
		return "values ('" + getId_leta() + "','" + getId_aviokompanije() + "','" + getEkonomska() + "','" + getBiznis()
				+ "','" + getA_klasa() + "','" + getOdrasli() + "','" + getDeca() + "','" + getDan30() + "','"
				+ getDan15() + "','" + getDan2() + "','" + getBroj_karata() + "')";
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "cena_klasa";
	}

	@Override
	public List<OpstiDomen> napuniListuZaSelect(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vratiNazivTabeleZaIzmenu(OpstiDomen od) {
		// TODO Auto-generated method stub
		return " SET `broj_karata` ="+""+"'"+getBroj_karata()+"'"+""+" WHERE `cena_klasa`.`id_cena_klasa` = "+getId();
	}

	@Override
	public String vratiNazivTabeleZaBrisanje(OpstiDomen od) {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (id_leta, id_aviokompanija, ekonomska, biznis, a_klasa, odrasli, deca, 30dana, 15dana, 2dana, broj_karata) ";
	}

}
