package com.comtrade.konekcija;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Konekcija {
	
	public static Konekcija instanca;

	private Connection konekcija;
	public Connection getKonekcija() {
		return konekcija;
	}

	private Konekcija(){
		ucitajDriver();
	}
	
	private void ucitajDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static Konekcija getInstanca(){
		if(instanca==null){
			instanca=new Konekcija();
		}
		return instanca;
	}
	public void pokreniTransakciju(){
		try {
			konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/comtradezavrsniprojekataerodrom","root",""	);
			konekcija.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void potvrdiTransakciju(){
		try {
			konekcija.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ponistiTransakciju(){
		try {
			konekcija.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void zatvoriKonekciju(){
		try {
			konekcija.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
