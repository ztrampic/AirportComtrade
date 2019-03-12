package com.comtrade.broker;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.domen.Adresa;
import com.comtrade.domen.CenaKlasa;
import com.comtrade.domen.Let;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.Profil;
import com.comtrade.domen.Rezervacija;
import com.comtrade.domen.Zahtev;


public interface IBroker {
	public void upisi(OpstiDomen od);
	public List<OpstiDomen> uzmiSveIzTabele(OpstiDomen od);
	public void obrisi(OpstiDomen od) throws Exception;
	public void izmeni(OpstiDomen od) throws SQLException;
	public int vratiVrednost(String user, String pass);
	public int vratiProfil(String user,String pass);
	public int vratiVrednostMax(int id);
	public int vratiMaxPoletanja(int id);
	public List<Let> vratiListuLetovaKOA(int id_avio);
	public int vratiVrednostBrojaLetovaZaDatum(String datum, int id);
	public int vratiMaxSletanja(int id);
	public int vratiVrednostBrojaSletanjaZaDatum(String datum, int id);
	public List<Zahtev> vratiListuAktivnihZahteva(int status_zahteva);
	public List<Let> vratiListuTrazenogLeta(Let let);
	public CenaKlasa vratiCenuKlasaZaIzabraniLet(CenaKlasa cena);
	public String vratiNazivKompanijeRadnika(int idRadnika);
	public Adresa vratiAdresuZaId(int id);
	public Profil vratiProfilZaId(int id);
	public List<Let> vratiListuTrazenogLetaZaKompaniju(Let let);
	public List<Let> vratiListuPovratnihLetovaZaKompaniju(Let let);
	public Profil vratiProfilZaEMail(String email);
	public List<Rezervacija> vratiListuRezervacijaZaId(int id);
	public List<Let> vratiListuLetovaOrderVreme(int id, String datum);
	public List<Rezervacija> vratiListuRezervacijaZaIdLeta(int id);
	public List<Let> vratiListuLetovaDanasSaAerodroma(int id, String datum);
	public List<Let> vratiListuSletanjaDanasNaAerodrom(int id, String datum);
	
	
	
	
}
