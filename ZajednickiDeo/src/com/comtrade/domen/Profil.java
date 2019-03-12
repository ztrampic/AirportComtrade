package com.comtrade.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Profil implements OpstiDomen, Serializable{
	
	private int id_profil;
	private String ime;
	private String prezime;
	private String e_mail;
	private int id_status;
	private String username;
	private String password;
	private double istorija_kupovina;
	
	
	public int getId_profil() {
		return id_profil;
	}

	public void setId_profil(int id_profil) {
		this.id_profil = id_profil;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public int getId_status() {
		return id_status;
	}

	public void setId_status(int id_status) {
		this.id_status = id_status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getIstorija_kupovina() {
		return istorija_kupovina;
	}

	public void setIstorija_kupovina(double istorija_kupovina) {
		this.istorija_kupovina = istorija_kupovina;
	}

	@Override
	public String vratiNazivZaUnos() {
		// TODO Auto-generated method stub
		return "values ('"+getIme()+"','"+getPrezime()+"','"+getE_mail()+"','"+getId_status()+"','"+getUsername()+"','"+getPassword()+"','"+getIstorija_kupovina()+"')";
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "profil";
	}

	@Override
	public List<OpstiDomen> napuniListuZaSelect(ResultSet rs) {
		List<OpstiDomen>list =new ArrayList<>();
		try {
			while(rs.next()){
				int id_profil=rs.getInt("id_profil");
				String ime=rs.getString("ime");
				String prezime=rs.getString("prezime");
				String e_mail=rs.getString("e_mail");
				int id_status=rs.getInt("id_status");
				String user=rs.getString("username");
				String pass=rs.getString("password");
				double istorija=rs.getDouble("istorija_kupovina");
				Profil p=new Profil();
				p.setId_profil(id_profil);
				p.setIme(ime);
				p.setPrezime(prezime);
				p.setE_mail(e_mail);
				p.setId_status(id_status);
				p.setUsername(user);
				p.setPassword(pass);
				p.setIstorija_kupovina(istorija);
				list.add(p);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
			return list;
	}

	@Override
	public String vratiNazivTabeleZaIzmenu(OpstiDomen od) {
		// TODO Auto-generated method stub
		return " SET `istorija_kupovina` ="+""+"'"+getIstorija_kupovina()+"'"+""+" WHERE `profil`.`id_profil` = "+getId_profil();
	}

	@Override
	public String vratiNazivTabeleZaBrisanje(OpstiDomen od) {
		// TODO Auto-generated method stub
		return " where "+""+od.vratiNazivTabele()+".id_profil"+"="+""+getId_profil()+"";
	}

	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (ime, prezime, e_mail, id_status, username, password, istorija_kupovina) ";
	}

	@Override
	public String toString() {
		return "Profil [id_profil=" + id_profil + ", ime=" + ime + ", prezime=" + prezime + ", e_mail=" + e_mail
				+ ", id_status=" + id_status + ", username=" + username + ", password=" + password
				+ ", istorija_kupovina=" + istorija_kupovina + "]";
	}
	
}
