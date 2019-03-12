package com.comtrade.kontroler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.comtrade.domen.Adresa;
import com.comtrade.domen.Aerodrom;
import com.comtrade.domen.AvioKompanija;
import com.comtrade.domen.Avion;
import com.comtrade.domen.CenaKlasa;
import com.comtrade.domen.KarakteristikeAerodroma;
import com.comtrade.domen.Let;
import com.comtrade.domen.Profil;
import com.comtrade.domen.Radnik;
import com.comtrade.domen.Rezervacija;
import com.comtrade.domen.StatusProfila;
import com.comtrade.domen.Zahtev;
import com.comtrade.sisstemskaoperacija.let.SistemskaOperacijVratiListuLetovaOrderVreme;
import com.comtrade.sisstemskaoperacija.let.SistemskaOperacijaBrisanjeLeta;
import com.comtrade.sisstemskaoperacija.let.SistemskaOperacijaUnosLeta;
import com.comtrade.sisstemskaoperacija.let.SistemskaOperacijaVratiListuLetova;
import com.comtrade.sisstemskaoperacija.let.SistemskaOperacijaVratiListuLetovaDanasSaAerodroma;
import com.comtrade.sisstemskaoperacija.let.SistemskaOperacijaVratiListuLetovaKOA;
import com.comtrade.sisstemskaoperacija.let.SistemskaOperacijaVratiListuLetovaZaKompaniju;
import com.comtrade.sisstemskaoperacija.let.SistemskaOperacijaVratiListuPovratnihLetovaZaKompaniju;
import com.comtrade.sisstemskaoperacija.let.SistemskaOperacijaVratiListuSletanjaDanasNaAerodrom;
import com.comtrade.sisstemskaoperacija.let.SistemskaOperacijaVratiListuTrazenogLeta;
import com.comtrade.sisstemskaoperacija.radnik.SistemskaOperacijaUnosRadnika;
import com.comtrade.sisstemskaoperacija.radnik.SistemskaOperacijaVratiNazivKompanijeRadnika;
import com.comtrade.sistemskaoperacija.OpstaSistemskaOperacija;
import com.comtrade.sistemskaoperacija.adresa.SistemskaOperacijaBrisiAdresu;
import com.comtrade.sistemskaoperacija.adresa.SistemskaOperacijaIzmeniAdresu;
import com.comtrade.sistemskaoperacija.adresa.SistemskaOperacijaUnosAdresa;
import com.comtrade.sistemskaoperacija.adresa.SistemskaOperacijaVratiAdresuZaId;
import com.comtrade.sistemskaoperacija.adresa.SistemskaOperacijaVratiSveAdrese;
import com.comtrade.sistemskaoperacija.aerodrom.SistemskaOperacijaObrisiAerodrom;
import com.comtrade.sistemskaoperacija.aerodrom.SistemskaOperacijaUnosAerodroma;
import com.comtrade.sistemskaoperacija.aerodrom.SistemskaOperacijaVratiBrojPoletanjaZaDatum;
import com.comtrade.sistemskaoperacija.aerodrom.SistemskaOperacijaVratiBrojSletanjaZaDatum;
import com.comtrade.sistemskaoperacija.aerodrom.SistemskaOperacijaVratiMaxLetovaAerodroma;
import com.comtrade.sistemskaoperacija.aerodrom.SistemskaOperacijaVratiMaximumPoletanja;
import com.comtrade.sistemskaoperacija.aerodrom.SistemskaOperacijaVratiMaximumSletanja;
import com.comtrade.sistemskaoperacija.aerodrom.SistemskaOperacijaVratiSveAerodrome;
import com.comtrade.sistemskaoperacija.aerodrom.karakteristike.SistemskaOperacijaIzmenaKarakteristikaAerodroma;
import com.comtrade.sistemskaoperacija.aerodrom.karakteristike.SistemskaOperacijaUnosKarakteristikaAerodroma;
import com.comtrade.sistemskaoperacija.aerodrom.karakteristike.SistemskaOperacijaVratiListuKarakteristikaAerodroma;
import com.comtrade.sistemskaoperacija.aviokompanija.SistemskaOperacijaUnosAvioKompanije;
import com.comtrade.sistemskaoperacija.aviokompanija.SistemskaOperacijaVratiSveAvioKompanije;
import com.comtrade.sistemskaoperacija.avion.SistemskaOperacijaBrisanjeAviona;
import com.comtrade.sistemskaoperacija.avion.SistemskaOperacijaUnosAviona;
import com.comtrade.sistemskaoperacija.avion.SistemskaOperacijaVratiSveAvione;
import com.comtrade.sistemskaoperacija.cene.SistemskaOperacijaIzmenaBrojaMestaNaLetu;
import com.comtrade.sistemskaoperacija.cene.SistemskaOperacijaSacuvajCene;
import com.comtrade.sistemskaoperacija.cene.SistemskaOperacijaVratiKlasuCenaZaLet;
import com.comtrade.sistemskaoperacija.profil.SistemskaOperacijaBrisanjeProfila;
import com.comtrade.sistemskaoperacija.profil.SistemskaOperacijaIzmenaKontaNaProfilu;
import com.comtrade.sistemskaoperacija.profil.SistemskaOperacijaLogovanje;
import com.comtrade.sistemskaoperacija.profil.SistemskaOperacijaProfilLogovanje;
import com.comtrade.sistemskaoperacija.profil.SistemskaOperacijaRegistrujProfil;
import com.comtrade.sistemskaoperacija.profil.SistemskaOperacijaVratiProfilZaEMail;
import com.comtrade.sistemskaoperacija.profil.SistemskaOperacijaVratiProfilZaId;
import com.comtrade.sistemskaoperacija.rezervacija.SistemskaOperacijaBrisanjeRezervacije;
import com.comtrade.sistemskaoperacija.rezervacija.SistemskaOperacijaSacuvajRezervaciju;
import com.comtrade.sistemskaoperacija.rezervacija.SistemskaOperacijaVratiListuRezervacijaZaId;
import com.comtrade.sistemskaoperacija.rezervacija.SistemskaOperacijaVratiListuRezervacijaZaIdLeta;
import com.comtrade.sistemskaoperacija.statusprofila.SistemskaOperacijaUnosStatusProfila;
import com.comtrade.sistemskaoperacija.statusprofila.SistemskaOperacijaVratiSveStatuseProfila;
import com.comtrade.sistemskaoperacija.zahtev.SistemskaOperacijaRealizujZahtev;
import com.comtrade.sistemskaoperacija.zahtev.SistemskaOperacijaSacuvajZahtev;
import com.comtrade.sistemskaoperacija.zahtev.SistemskaOperacijaVratiListuAktivnihZahteva;






public class Kontroler {
	private static volatile Kontroler instanca;
	private static Object mutex = new Object();
	
	private Kontroler(){
		
	}
	
	public static Kontroler getInstanca(){
		Kontroler result=instanca;
		if(result==null){
			synchronized (mutex) {
				result = instanca;
				if(result==null){
					instanca=result=new Kontroler();			
				}
			}	
		}
		return instanca;
	}

	public void sacuvajAdresu(Adresa a) {
		OpstaSistemskaOperacija op=new SistemskaOperacijaUnosAdresa();
		op.izvrsiSistemskuOperaciju(a);
		
	}
	
	public void obrisiAdresu(Adresa a) {
		OpstaSistemskaOperacija op=new SistemskaOperacijaBrisiAdresu();
		op.izvrsiSistemskuOperaciju(a);
	}
	
	public void izmeniAdresu(Adresa a){
		OpstaSistemskaOperacija op=new SistemskaOperacijaIzmeniAdresu();
		op.izvrsiSistemskuOperaciju(a);
	}
	
	public List<Adresa>vratiListuSvihAdresa(){	
		List<Adresa>list=new ArrayList<>(); 
		Map<String, Object>map = new HashMap<>();
		map.put("transfer", new Adresa());
		OpstaSistemskaOperacija op=new SistemskaOperacijaVratiSveAdrese();
		op.izvrsiSistemskuOperaciju(map);
		list=(List<Adresa>) map.get("transfer_list");
		return list;
		
	}
	
	public void sacuvajAvioKompaniju(AvioKompanija a){
		OpstaSistemskaOperacija op=new SistemskaOperacijaUnosAvioKompanije();
		op.izvrsiSistemskuOperaciju(a);
	}
	
	public List<AvioKompanija> vratiListuSvihAvioKompanija(){
		List<AvioKompanija>list=new ArrayList<>(); 
		Map<String, Object>map = new HashMap<>();
		map.put("transfer", new AvioKompanija());
		OpstaSistemskaOperacija op=new SistemskaOperacijaVratiSveAvioKompanije();
		op.izvrsiSistemskuOperaciju(map);
		list=(List<AvioKompanija>) map.get("transfer_list");
		return list;
	
	}

	public List<Avion> vratiListuSvihAviona() {
		List<Avion>list=new ArrayList<>();
		Map<String, Object>map=new HashMap<>();
		map.put("transfer", new Avion());
		OpstaSistemskaOperacija op=new SistemskaOperacijaVratiSveAvione();
		op.izvrsiSistemskuOperaciju(map);
		list= (List<Avion>) map.get("transfer_list");
		return list;
	}

	public void sacuvajAvion(Avion a) {
		OpstaSistemskaOperacija op=new SistemskaOperacijaUnosAviona();
		op.izvrsiSistemskuOperaciju(a);
	}

	public void obrisiAvion(Avion a) {
		OpstaSistemskaOperacija op=new SistemskaOperacijaBrisanjeAviona();
		op.izvrsiSistemskuOperaciju(a);
		
	}

	public List<Aerodrom> vratiListuSvihAerodroma() {
		List<Aerodrom>list=new ArrayList<>();
		Map<String, Object>map=new HashMap<>();
		map.put("transfer",	new Aerodrom());
		OpstaSistemskaOperacija op=new SistemskaOperacijaVratiSveAerodrome();
		op.izvrsiSistemskuOperaciju(map);
		list=(List<Aerodrom>) map.get("transfer_list");
		return list;
	}

	public void sacuvajAerodrom(Aerodrom a) {
		OpstaSistemskaOperacija op=new SistemskaOperacijaUnosAerodroma();
		op.izvrsiSistemskuOperaciju(a);
		
	}

	public void obrisiAerodrom(Aerodrom a) {
		OpstaSistemskaOperacija op=new SistemskaOperacijaObrisiAerodrom();
		op.izvrsiSistemskuOperaciju(a);
		
	}

	public List<StatusProfila> vratiListuSvihStatusa() {
		List<StatusProfila>list=new ArrayList<>();
		Map<String, Object>map=new HashMap<>();
		map.put("transfer", new StatusProfila());
		OpstaSistemskaOperacija op=new SistemskaOperacijaVratiSveStatuseProfila();
		op.izvrsiSistemskuOperaciju(map);
		list=(List<StatusProfila>) map.get("transfer_list");
		return list;
	}

	public void registrujProfil(Profil profil) {
		OpstaSistemskaOperacija op = new SistemskaOperacijaRegistrujProfil();
		op.izvrsiSistemskuOperaciju(profil);
		
	}

	public List<Profil> vratiListuSvihProfila() {
		List<Profil>list=new ArrayList<>();
		Map<String, Object>map=new HashMap<>();
		map.put("transfer", new Profil());
		OpstaSistemskaOperacija op=new SistemskaOperacijaVratiSveStatuseProfila();
		op.izvrsiSistemskuOperaciju(map);
		list=(List<Profil>) map.get("transfer_list");
		return list;
	}

	public void sacuvajStatusProfila(StatusProfila sp) {
		OpstaSistemskaOperacija op=new SistemskaOperacijaUnosStatusProfila();
		op.izvrsiSistemskuOperaciju(sp);
		
	}

	public void sacuvajRadnika(Radnik radnik) {
		OpstaSistemskaOperacija op=new SistemskaOperacijaUnosRadnika();
		op.izvrsiSistemskuOperaciju(radnik);
		
	}

	public int vratiVrednostStatusaProfila(Profil p){
		SistemskaOperacijaLogovanje sop=new SistemskaOperacijaLogovanje();
		int vrednost = sop.izvrsiSistemskuOperacijuLogovanje(p);
		return vrednost;
	}

	public int vratiProvereniProfil(Profil profilProvera1) {
		SistemskaOperacijaProfilLogovanje sopl=new SistemskaOperacijaProfilLogovanje();
		int profil = sopl.izvrsiSistemskuOperacijuProfilLogovanje(profilProvera1);
		return profil;
	}

	public void sacuvajKarakteristikeAerodroma(KarakteristikeAerodroma karakter) {
		OpstaSistemskaOperacija op = new SistemskaOperacijaUnosKarakteristikaAerodroma();
		op.izvrsiSistemskuOperaciju(karakter);
		
	}

	public List<KarakteristikeAerodroma> vratiListuSvihKarakteristikaAerodroma() {
		List<KarakteristikeAerodroma>list=new ArrayList<>();
		Map<String, Object>map=new HashMap<>();
		map.put("transfer", new KarakteristikeAerodroma());
		OpstaSistemskaOperacija op=new SistemskaOperacijaVratiListuKarakteristikaAerodroma();
		op.izvrsiSistemskuOperaciju(map);
		list=(List<KarakteristikeAerodroma>) map.get("transfer_list");
		return list;
	}

	public void izmeniKarakteristikeAerodroma(KarakteristikeAerodroma kara) {
		OpstaSistemskaOperacija op= new SistemskaOperacijaIzmenaKarakteristikaAerodroma();
		op.izvrsiSistemskuOperaciju(kara);
		
	}

	public int vratiMaximumLetovaAerodroma(int idAerodroma) {
		SistemskaOperacijaVratiMaxLetovaAerodroma max=new SistemskaOperacijaVratiMaxLetovaAerodroma();
		int vrednostMax=max.izvrsiSistemskaOperacijaVratiMaxLetovaAerodroma(idAerodroma);
		return vrednostMax;
	}

	public void sacuvajLet(Let let) {
		OpstaSistemskaOperacija op=new SistemskaOperacijaUnosLeta();
		op.izvrsiSistemskuOperaciju(let);
	}

	public List<Let> vratiListuLetova() {
		List<Let>list=new ArrayList<>();
		Map<String, Object>map=new HashMap<>();
		map.put("transfer", new Let());
		OpstaSistemskaOperacija op=new SistemskaOperacijaVratiListuLetova();
		op.izvrsiSistemskuOperaciju(map);
		list=(List<Let>) map.get("transfer_list");
		return list;
	}

	public void obrisiLet(Let letZaObrisati) {
		OpstaSistemskaOperacija op =new SistemskaOperacijaBrisanjeLeta();
		op.izvrsiSistemskuOperaciju(letZaObrisati);
		
	}

	public int VratiMaXPoletanja(int id_aero) {
		SistemskaOperacijaVratiMaximumPoletanja maxPolet= new SistemskaOperacijaVratiMaximumPoletanja();
		int vrednostMaxPolet=maxPolet.izvrsiSistemskaOperacijaVratiMaximumPoletanja(id_aero);
		return vrednostMaxPolet;
	}
	
	public int vratiMaxSletanja(int id_aero_slet) {
		SistemskaOperacijaVratiMaximumSletanja maxSlet = new SistemskaOperacijaVratiMaximumSletanja();
		int vrednostMaxSlet = maxSlet.izvrsiSistemskaOperacijaVratiMaximumSletanja(id_aero_slet);
		return vrednostMaxSlet;
	}

	public List<Let> vratiListuLetaKonkretnogAviona(int id_avio) {
		SistemskaOperacijaVratiListuLetovaKOA koa=new SistemskaOperacijaVratiListuLetovaKOA();
		List<Let>list=koa.izvrsiSistemskuOperacijuVratiListuLetovaKOA(id_avio);
		return list;
	}

	public int vratiBrojPoletanjaZaDatum(String datum, int id) {
		SistemskaOperacijaVratiBrojPoletanjaZaDatum bop=new SistemskaOperacijaVratiBrojPoletanjaZaDatum();
		int  brojPoletanja=bop.izvrsiSistemskuOperacijuVratiBrojPoletanjaZaDatum(datum,id);
		return brojPoletanja;
	}

	public int vratiBrojSletanjaZaDatum(String datum, int id) {
		SistemskaOperacijaVratiBrojSletanjaZaDatum bos = new SistemskaOperacijaVratiBrojSletanjaZaDatum();
		int brojSletanja = bos.izvrsiSistemskuOperacijuVratiBrojSletanjaZaDatum(datum,id);
		return brojSletanja;
	}

	public void sacuvajZahtev(Zahtev zahtev) {
		OpstaSistemskaOperacija op = new SistemskaOperacijaSacuvajZahtev();
		op.izvrsiSistemskuOperaciju(zahtev);
		
	}

	public void realizujZahtev(Zahtev zahtevR) {
		OpstaSistemskaOperacija op = new SistemskaOperacijaRealizujZahtev();
		op.izvrsiSistemskuOperaciju(zahtevR);
		
	}

	public List<Zahtev> vratiListuAktivnihZahteva(int status_zahteva) {
		SistemskaOperacijaVratiListuAktivnihZahteva az= new SistemskaOperacijaVratiListuAktivnihZahteva();
		List<Zahtev> lista = az.izvrsiSistemskuOperacijuVratiListuAktivnihZahteva(status_zahteva);
		return lista;
	}

	public List<Let> vratiListuTrazenogLeta(Let letPretraga) {
		SistemskaOperacijaVratiListuTrazenogLeta tl = new SistemskaOperacijaVratiListuTrazenogLeta();
		List<Let> listaLeta = tl.izvrsiSistemskuOperacijuVratiListuTrazenogLeta(letPretraga);
		return listaLeta;
	}

	public void sacuvajCene(CenaKlasa cena) {
		OpstaSistemskaOperacija op =new SistemskaOperacijaSacuvajCene();
		op.izvrsiSistemskuOperaciju(cena);
		
	}

	public CenaKlasa vratiCenuKlasaZaIZabraniLet(CenaKlasa cenaK) {
		SistemskaOperacijaVratiKlasuCenaZaLet cena=new SistemskaOperacijaVratiKlasuCenaZaLet();
		CenaKlasa odgovor = cena.izvrsiSistemskuOperacijuVratiKlasuCenaZaLet(cenaK);
		return odgovor;
	}

	public void sacuvajRezervaciju(Rezervacija rezervacija) {
		OpstaSistemskaOperacija op = new SistemskaOperacijaSacuvajRezervaciju();
		op.izvrsiSistemskuOperaciju(rezervacija);
		
	}

	public void izmeniBrojMesta(CenaKlasa brojMesta) {
		OpstaSistemskaOperacija op = new SistemskaOperacijaIzmenaBrojaMestaNaLetu();
		op.izvrsiSistemskuOperaciju(brojMesta);
		
	}

	public void izmeniKontoNaProfilu(Profil kontoP) {
		OpstaSistemskaOperacija op =new SistemskaOperacijaIzmenaKontaNaProfilu();
		op.izvrsiSistemskuOperaciju(kontoP);
		
	}

	public String vratiNazivKompanijeRadnika(int idRadnika) {
		SistemskaOperacijaVratiNazivKompanijeRadnika kompRadnika= new SistemskaOperacijaVratiNazivKompanijeRadnika();
		String naziv = kompRadnika.izvrsiSistemskuOperacijuVratiNazivKompanijeRadnika(idRadnika);
		return naziv;
	}

	public Adresa vratiAdresuZaID(int id_adrese) {
		SistemskaOperacijaVratiAdresuZaId sva=new SistemskaOperacijaVratiAdresuZaId();
		Adresa adresa=sva.izvrsiSistemskuOperacijuVratiAdresuZaId(id_adrese);
		return adresa;
	}

	public Profil vratiProfilZaId(int id_profil) {
		SistemskaOperacijaVratiProfilZaId sop =new SistemskaOperacijaVratiProfilZaId();
		Profil profil = sop.izvrsiSistemskuOperacijuVratiProfilZaId(id_profil);
		return profil;
	}

	public List<Let> vratiListuLetovaZaKompaniju(Let letPretragaSalter) {
		SistemskaOperacijaVratiListuLetovaZaKompaniju vll=new SistemskaOperacijaVratiListuLetovaZaKompaniju();
		List<Let>lista = vll.izvrsiSistemskuOperacijuVratiListuLetovaZaKompaniju(letPretragaSalter);
		return lista;
	}

	public List<Let> vratiListuPovratnihLetovaZaKompaniju(Let letPretragaSalterPovratni) {
		SistemskaOperacijaVratiListuPovratnihLetovaZaKompaniju vilp= new SistemskaOperacijaVratiListuPovratnihLetovaZaKompaniju();
		List<Let>lista = vilp.izvrsiSistemskuOperacijuVratiListuPovratnihLetovaZaKompaniju(letPretragaSalterPovratni);
		return lista;
	}

	public Profil vratiProfilZaEMail(String email) {
		SistemskaOperacijaVratiProfilZaEMail sopam = new SistemskaOperacijaVratiProfilZaEMail();
		Profil profil = sopam.izvrsiSistemskuOperacijuVratiProfilZaEMail(email);
		return profil;
	}

	public List<Rezervacija> vratiListuRezervacijaZaId(int id) {
		SistemskaOperacijaVratiListuRezervacijaZaId sov=new SistemskaOperacijaVratiListuRezervacijaZaId();
		List<Rezervacija> list= sov.izvrsiSistemskuOperacijuVratiListuRezervacijaZaId(id);
		return list;
	}

	public List<Let> vratiListuLetovaKompanijeOrdrByVreme(int id, String datum) {
		SistemskaOperacijVratiListuLetovaOrderVreme vlos =new SistemskaOperacijVratiListuLetovaOrderVreme();
		List<Let>list = vlos.izvrsiSistemskuOperacijuVratiListuLetovaOrderVreme(id,datum);
		return list;
	}

	public List<Rezervacija> vratiListuRezervacijaZaIdLeta(int idLetovanja) {
		SistemskaOperacijaVratiListuRezervacijaZaIdLeta letovanja=new SistemskaOperacijaVratiListuRezervacijaZaIdLeta();
		List<Rezervacija> list= letovanja.izvrsiSistemskuOperacijuVratiListuRezervacijaZaIdLeta(idLetovanja);
		return list;
	}

	public void obrisiRezervaciju(Rezervacija rezBris) {
		OpstaSistemskaOperacija op = new SistemskaOperacijaBrisanjeRezervacije();
		op.izvrsiSistemskuOperaciju(rezBris);
	}

	public void obrisiProfil(Profil profBris) {
		OpstaSistemskaOperacija op =new SistemskaOperacijaBrisanjeProfila();
		op.izvrsiSistemskuOperaciju(profBris);
	}

	public List<Let> vratiListuLetovaAerodromaDanas(int id, String datum) {
		SistemskaOperacijaVratiListuLetovaDanasSaAerodroma sisoa=new SistemskaOperacijaVratiListuLetovaDanasSaAerodroma();
		List<Let>list = sisoa.izvrsiSistemskuOperacijuVratiListuLetovaDanasSaAerodroma(id,datum);
		return list;
	}

	public List<Let> vratiListuSletanjaAerodromaDanas(int id, String datum) {
		SistemskaOperacijaVratiListuSletanjaDanasNaAerodrom sd = new SistemskaOperacijaVratiListuSletanjaDanasNaAerodrom();
		List<Let>list = sd.izvrsiSistemskuOperacijuVratiListuSletanjaDanasNaAerodrom(id,datum);
		return list;
	}

	

	

	

	
		
}
