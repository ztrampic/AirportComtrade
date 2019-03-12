package com.comtrade.kontroler.korisnickoginterfejsa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.comtrade.enumi.Konstante;
import com.comtrade.komunikacija.Komunikacija;
import com.comtrade.transfer.TransferKlasa;

public class KontrolerKI {
	public static KontrolerKI instanca;

	private KontrolerKI() {

	}

	public static KontrolerKI getInstanca() {
		if (instanca == null) {
			instanca = new KontrolerKI();
		}
		return instanca;
	}

	public TransferKlasa posaljiAdresu(Adresa adresa) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.SACUVAJ_ADRESU);
		tf.setKlijent_slanje(adresa);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;

	}

	public TransferKlasa ucitajListuAdresa() throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_ADRESA);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;

	}

	public TransferKlasa izmeniAdresu(Adresa adresa) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.IZMENI_ADRESU);
		tf.setKlijent_slanje(adresa);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa obrisiAdresu(Adresa adresa) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.OBRISI_ADRESU);
		tf.setKlijent_slanje(adresa);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa posaljiAvioKompaniju(AvioKompanija ak) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.SACUVAJ_AVIOKOMPANIJU);
		tf.setKlijent_slanje(ak);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajListuAvioKompanija() throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_AVIOKOMPANIJA);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajListuAviona() throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_AVIONA);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa posaljiAvion(Avion a) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.SACUVAJ_AVION);
		tf.setKlijent_slanje(a);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa obrisiAvion(Avion a) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.OBRISI_AVION);
		tf.setKlijent_slanje(a);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajListuAerodroma() throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_AERODROMA);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa posaljiAerodrom(Aerodrom a) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.SACUVAJ_AERODROM);
		tf.setKlijent_slanje(a);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa obrisiAerodrom(Aerodrom a) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.OBRISI_AERODROM);
		tf.setKlijent_slanje(a);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajListuStatusaProfila() throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_STATUS_PROFILA);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa registracijaProfila(Profil p) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.SACUVAJ_PROFIL);
		tf.setKlijent_slanje(p);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajListuProfila() throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_PROFILA);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa posaljiStatusProfila(StatusProfila sp) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.SACUVAJ_STATUS_PROFILA);
		tf.setKlijent_slanje(sp);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa upisiRadnikaUBazu(Radnik r) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.SACUVAJ_RADNIKA);
		tf.setKlijent_slanje(r);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa vratiVrednostStatusaProfila(Profil p) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_STATUS_ZA_LOGIN);
		tf.setKlijent_slanje(p);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa vratiProfilZaLogin(Profil profilZaProveru) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_PROFIL_ZA_LOGIN);
		tf.setKlijent_slanje(profilZaProveru);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor=Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa posaljiKarakteristikeAerodroma(KarakteristikeAerodroma ka) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.SACUVAJ_KARAKTERISTIKE_AERODROMA);
		tf.setKlijent_slanje(ka);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor=Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajListuKarakteristikaAerodroma() throws ClassNotFoundException, IOException {
		TransferKlasa tf=new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_KARAKTERISTIKA_AERODROMA);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa izmeniKarakteristikeAerodroma(KarakteristikeAerodroma ka) throws ClassNotFoundException, IOException {
		TransferKlasa tf=new TransferKlasa();
		tf.setOperacija(Konstante.IZMENA_KARAKTERISTIKA_AERODROMA);
		tf.setKlijent_slanje(ka);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor=Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa vratiMaximumLetovaPoAerodromu(int id_aerodroma) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.MAXIMUM_LETOVA_NA_AERODROMU);
		tf.setKlijent_slanje(id_aerodroma);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor=Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa posaljiLet(Let let) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.SACUVAJ_LET);
		tf.setKlijent_slanje(let);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor=Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajListuLetova() throws ClassNotFoundException, IOException {
		TransferKlasa tf=new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_LETOVA);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa obrisiLet(Let let) throws ClassNotFoundException, IOException {
		TransferKlasa tf=new TransferKlasa();
		tf.setKlijent_slanje(let);
		tf.setOperacija(Konstante.OBRISI_LET);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa vratiMaximumPoletanja(int id) throws ClassNotFoundException, IOException {
		TransferKlasa tf=new TransferKlasa();
		tf.setOperacija(Konstante.MAXIMUM_POLETANJA_ZA_DAN);
		tf.setKlijent_slanje(id);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor=Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}
	
	public TransferKlasa vratiMaximumSletanja(int id) throws ClassNotFoundException, IOException {
		TransferKlasa tf=new TransferKlasa();
		tf.setOperacija(Konstante.MAXIMUM_SLETANJA_ZA_DAN);
		tf.setKlijent_slanje(id);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor=Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajListuLetaKonkretnogAviona(int id_aviona) throws ClassNotFoundException, IOException {
		TransferKlasa tf=new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_LETA_KONKRETNOG_AVIONA);
		tf.setKlijent_slanje(id_aviona);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor=Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa vratiBrojPoletanjaZaOdabraniDatum(String datum, int id_aerodroma) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_BROJ_ZAUZETIH_POLETANJA);
		String id=String.valueOf(id_aerodroma);
		List<String >lista = new ArrayList<>();
		lista.add(datum);
		lista.add(id);
		tf.setKlijent_slanje(lista);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa vratiBrojSletanjaZaOdabraniDatum(String datum, int id_aerodroma) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_BROJ_ZAUZETIH_SLETANJA);
		String id=String.valueOf(id_aerodroma);
		List<String >lista = new ArrayList<>();
		lista.add(datum);
		lista.add(id);
		tf.setKlijent_slanje(lista);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return null;
	}

	public TransferKlasa posaljiZahtev(Zahtev z) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.SACUVAJ_ZAHTEV);
		tf.setKlijent_slanje(z);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa izmeniZahtev(Zahtev z) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.REALIZACIJA_ZAHTEVA);
		tf.setKlijent_slanje(z);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor= Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public static TransferKlasa vratiListuAktivnihZahteva(int status_zahteva) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_AKTIVNIH_ZAHTEVA);
		tf.setKlijent_slanje(status_zahteva);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajListuPutnikovogLeta(Let l) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.PRETRAGA_LET);
		tf.setKlijent_slanje(l);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa upisiCenu(CenaKlasa ck) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.UPISI_CENU_LETA);
		tf.setKlijent_slanje(ck);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa vratiCenuKlasaZaIzabraniLet(CenaKlasa ck) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_CENU_KLASA_ZA_IZABRANI_LET);
		tf.setKlijent_slanje(ck);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa upisiRezervaciju(Rezervacija r) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.SACUVAJ_REZERVACIJU);
		tf.setKlijent_slanje(r);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa izmeniCeneKarataBrojMesta(CenaKlasa update) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.IZMENI_PREOSTALI_BROJ_MESTA);
		tf.setKlijent_slanje(update);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa text = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return text;
	}

	public TransferKlasa izmeniKontoNaProfilu(Profil p) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.IZMENA_KONTA);
		tf.setKlijent_slanje(p);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa vratiNazivAvioKompanije(int id_profil) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_NAZIV_KOMPANIJE_RADNIKA);
		tf.setKlijent_slanje(id_profil);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajAdresuZaId(int id) throws ClassNotFoundException, IOException {
		TransferKlasa tf =new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_ADRESU_ZA_ID);
		tf.setKlijent_slanje(id);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor=Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajProfilZaId(int id_profil) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_PROFIL_ZA_ID);
		tf.setKlijent_slanje(id_profil);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa vratiListuLetovaKompanije(Let l) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_LETOVA_AVIOKOMPANIJE_ZA_DATUM);
		tf.setKlijent_slanje(l);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa vratiListuPovratnihLetovaKompanije(Let let) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_POVRATNIH_LETOVA_AVIOKOMPANIJE_ZA_DATUM);
		tf.setKlijent_slanje(let);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajProfilZaEmail(String email) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_PROFIL_ZA_EMAIL);
		tf.setKlijent_slanje(email);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajRezervacijeZaIdProfila(int id) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_REZERVACIJA_ZA_ID);
		tf.setKlijent_slanje(id);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor=Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa vratiListuLetovaByDatum(int id ,String datum) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_LETOVA_VREME_DATUM);
		String idKomp=String.valueOf(id);
		List<String >lista = new ArrayList<>();
		lista.add(datum);
		lista.add(idKomp);
		tf.setKlijent_slanje(lista);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajRezervacijeZaIdLeta(int idLeta) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_REZERVACIJA_ZA_ID_LETA);
		tf.setKlijent_slanje(idLeta);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor=Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa obrisiRezervaciju(Rezervacija r) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.OBRISI_REZERVACIJU);
		tf.setKlijent_slanje(r);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa obrisiProfil(Profil p) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.OBRISI_RPROFIL);
		tf.setKlijent_slanje(p);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajListuLetovaZaIdAerodroma(int id, String datum) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_LETOVA_SA_AERODROMA_ZA_DATUM);
		String idAerodroma=String.valueOf(id);
		List<String >lista = new ArrayList<>();
		lista.add(datum);
		lista.add(idAerodroma);
		tf.setKlijent_slanje(lista);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}

	public TransferKlasa ucitajListuLetovaZaIdAerodromaDolasci(int id, String datum) throws ClassNotFoundException, IOException {
		TransferKlasa tf = new TransferKlasa();
		tf.setOperacija(Konstante.VRATI_LISTU_SLETANJA_AERODROM_ZA_DATUM);
		String idAerodroma=String.valueOf(id);
		List<String >lista = new ArrayList<>();
		lista.add(datum);
		lista.add(idAerodroma);
		tf.setKlijent_slanje(lista);
		Komunikacija.getInstanca().posaljiPodatkeNaServer(tf);
		TransferKlasa odgovor = Komunikacija.getInstanca().prihvatiPodatkeSaServera();
		return odgovor;
	}







	

}
