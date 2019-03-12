package com.comtrade.nitObradaKlijenta;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
import com.comtrade.kontroler.Kontroler;
import com.comtrade.transfer.TransferKlasa;

public class NitObradaKlijent extends Thread {
	private Socket soket;

	public Socket getSoket() {
		return soket;
	}
	public void setSoket(Socket soket) {
		this.soket = soket;
	}
	@Override
	public void run(){
		while(true){
			try {
				ObjectInputStream inSoket=new ObjectInputStream(soket.getInputStream());
				TransferKlasa tf=(TransferKlasa) inSoket.readObject();
				obradaPodataka(tf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
	private void obradaPodataka(TransferKlasa tf) {
		switch (tf.getOperacija()) {
		case SACUVAJ_ADRESU:
				Adresa a=(Adresa) tf.getKlijent_slanje();
				TransferKlasa tf1=new TransferKlasa();;
			try{
				Kontroler.getInstanca().sacuvajAdresu(a);
				tf1.setServer_odgovor_poruka("Adresa uspesno sacuvana.");
			}catch (Exception e) {
				}
				posaljiPodatkeKlijentu(tf1);
			break;
		case SACUVAJ_AVIOKOMPANIJU:
				AvioKompanija ak=(AvioKompanija) tf.getKlijent_slanje();
				TransferKlasa tf2=new TransferKlasa();
			try {
				Kontroler.getInstanca().sacuvajAvioKompaniju(ak);
				tf2.setServer_odgovor_poruka("Kompanija uspesno sacuvana.");
			} catch (Exception e) {
				}
				posaljiPodatkeKlijentu(tf2);
			break;
		case VRATI_LISTU_ADRESA:
				List<Adresa> listaZaSlanje=Kontroler.getInstanca().vratiListuSvihAdresa();
				TransferKlasa tf3=new TransferKlasa();
				tf3.setServer_odgovor(listaZaSlanje);
				posaljiPodatkeKlijentu(tf3);
			break;
		case OBRISI_ADRESU:
				Adresa a1= (Adresa) tf.getKlijent_slanje();
				TransferKlasa tf4=new TransferKlasa();
			try{
				Kontroler.getInstanca().obrisiAdresu(a1);
			}catch (Exception e) {
				tf4.setServer_odgovor_poruka("Adresa u upotrebi ne moze se obrisati");
			}
				posaljiPodatkeKlijentu(tf4);
			break;
		case VRATI_LISTU_AVIOKOMPANIJA:
				List<AvioKompanija>listaAvioKompanija=Kontroler.getInstanca().vratiListuSvihAvioKompanija();
				TransferKlasa tf5=new TransferKlasa();
				tf5.setServer_odgovor(listaAvioKompanija);
				posaljiPodatkeKlijentu(tf5);
			break;
		case IZMENI_ADRESU:
				Adresa a2=(Adresa) tf.getKlijent_slanje();
				TransferKlasa tf6=new TransferKlasa();
			try{
				Kontroler.getInstanca().izmeniAdresu(a2);
				tf6.setServer_odgovor_poruka("Adresa uspesno izmenjena.");
			}catch (Exception e) {
				e.printStackTrace();
			}
				posaljiPodatkeKlijentu(tf6);
			break;
		case VRATI_LISTU_AVIONA:
				List<Avion>listaAviona=Kontroler.getInstanca().vratiListuSvihAviona();
				TransferKlasa tf7= new TransferKlasa();
				tf7.setServer_odgovor(listaAviona);
				posaljiPodatkeKlijentu(tf7);
			break;
		case SACUVAJ_AVION:
				Avion avion=(Avion) tf.getKlijent_slanje();
				TransferKlasa tf8=new TransferKlasa();
				try {
					Kontroler.getInstanca().sacuvajAvion(avion);
					tf8.setServer_odgovor_poruka("Avion uspesno sacuvan");
				} catch (Exception e) {
					// TODO: handle exception
				}
				posaljiPodatkeKlijentu(tf8);
			break;
		case OBRISI_AVION:
				Avion avion1=(Avion) tf.getKlijent_slanje();
				TransferKlasa tf9=new TransferKlasa();
				try {
					Kontroler.getInstanca().obrisiAvion(avion1);
					tf9.setServer_odgovor_poruka("Uspesno obrisan avion");
				} catch (Exception e) {
					tf9.setServer_odgovor_poruka("Avion u upotrebi ne moze se obrisati");
				}
				posaljiPodatkeKlijentu(tf9);
			break;
		case VRATI_LISTU_AERODROMA:
				List<Aerodrom>listaAerodroma=Kontroler.getInstanca().vratiListuSvihAerodroma();
				TransferKlasa tf10=new TransferKlasa();
				tf10.setServer_odgovor(listaAerodroma);
				posaljiPodatkeKlijentu(tf10);
			break;
		case SACUVAJ_AERODROM:
				Aerodrom aerodrom=(Aerodrom) tf.getKlijent_slanje();
				TransferKlasa tf11=new TransferKlasa();
				try {
					Kontroler.getInstanca().sacuvajAerodrom(aerodrom);
					tf11.setServer_odgovor_poruka("Aerodrom uspesno sacuvan");
				} catch (Exception e) {
					// TODO: handle exception
				}
				posaljiPodatkeKlijentu(tf11);
			break;
		case OBRISI_AERODROM:
				Aerodrom aerodrom1=(Aerodrom) tf.getKlijent_slanje();
				TransferKlasa tf12=new TransferKlasa();
				try {
					Kontroler.getInstanca().obrisiAerodrom(aerodrom1);
				} catch (Exception e) {
					tf12.setServer_odgovor_poruka("Neuspesno");
				}
				posaljiPodatkeKlijentu(tf12);
		case VRATI_LISTU_STATUS_PROFILA:
				List<StatusProfila>listaStatusa=Kontroler.getInstanca().vratiListuSvihStatusa();
				TransferKlasa tf13=new TransferKlasa();
				tf13.setServer_odgovor(listaStatusa);
				posaljiPodatkeKlijentu(tf13);
			break;
		case SACUVAJ_PROFIL:
				Profil profil=(Profil) tf.getKlijent_slanje();
				TransferKlasa tf14=new TransferKlasa();
				try {
				Kontroler.getInstanca().registrujProfil(profil);
				tf14.setServer_odgovor_poruka("OK");
				} catch (Exception e) {
					// TODO: handle exception
				}
				posaljiPodatkeKlijentu(tf14);
			break;
		case VRATI_LISTU_PROFILA:
				List<Profil>listaProfila=Kontroler.getInstanca().vratiListuSvihProfila();
				TransferKlasa tf15=new TransferKlasa();
				tf15.setServer_odgovor(listaProfila);
				posaljiPodatkeKlijentu(tf15);
			break;
		case SACUVAJ_STATUS_PROFILA:
				StatusProfila sp=(StatusProfila) tf.getKlijent_slanje();
				TransferKlasa tf16=new TransferKlasa();
				tf16.setServer_odgovor_poruka("Nov status profila sacuvan");
				try {
					Kontroler.getInstanca().sacuvajStatusProfila(sp);
				} catch (Exception e) {
					// TODO: handle exception
				}
				posaljiPodatkeKlijentu(tf16);
			break;
		case SACUVAJ_RADNIKA:
				Radnik radnik=(Radnik) tf.getKlijent_slanje();
				TransferKlasa tf17=new TransferKlasa();
				try {
					Kontroler.getInstanca().sacuvajRadnika(radnik);
					tf17.setServer_odgovor_poruka("Radnik unesen u bazu");
				} catch (Exception e) {
					// TODO: handle exception
				}
				posaljiPodatkeKlijentu(tf17);
			break;
		case VRATI_STATUS_ZA_LOGIN:
				Profil profilProvera=(Profil) tf.getKlijent_slanje();
				TransferKlasa tf18 = null;
				try {
					int vrednost=Kontroler.getInstanca().vratiVrednostStatusaProfila(profilProvera);
					tf18=new TransferKlasa();
					tf18.setServer_odgovor(vrednost);
				} catch (Exception e) {
					tf18.setServer_odgovor_poruka("Nisu dobri podaci"); 
				}
				posaljiPodatkeKlijentu(tf18);
			break;
		case VRATI_PROFIL_ZA_LOGIN:
				Profil profilProvera1=(Profil) tf.getKlijent_slanje();
				TransferKlasa tf19=null;
				try {
					int provereniProfil=Kontroler.getInstanca().vratiProvereniProfil(profilProvera1);
					tf19=new TransferKlasa();
					tf19.setServer_odgovor(provereniProfil);
				} catch (Exception e) {
					// TODO: handle exception
				}posaljiPodatkeKlijentu(tf19);
			break;
		case SACUVAJ_KARAKTERISTIKE_AERODROMA:
				KarakteristikeAerodroma karakter=(KarakteristikeAerodroma) tf.getKlijent_slanje();
				TransferKlasa tf20 = new TransferKlasa();
				try {
					Kontroler.getInstanca().sacuvajKarakteristikeAerodroma(karakter);
					tf20.setServer_odgovor_poruka("Podaci sacuvni");
				} catch (Exception e) {
					// TODO: handle exception
				}
				posaljiPodatkeKlijentu(tf20);
			break;
		case VRATI_LISTU_KARAKTERISTIKA_AERODROMA:
				List<KarakteristikeAerodroma>listaKarakteristika=Kontroler.getInstanca().vratiListuSvihKarakteristikaAerodroma();
				TransferKlasa tf21=new TransferKlasa();
				tf21.setServer_odgovor(listaKarakteristika);
				posaljiPodatkeKlijentu(tf21);
			break;
		case IZMENA_KARAKTERISTIKA_AERODROMA:
				KarakteristikeAerodroma kara=(KarakteristikeAerodroma) tf.getKlijent_slanje();
				TransferKlasa tf22=new TransferKlasa();
				Kontroler.getInstanca().izmeniKarakteristikeAerodroma(kara);
				tf22.setServer_odgovor_poruka("Uspesno izmenjeni podaci");
				posaljiPodatkeKlijentu(tf22);
			break;
		case MAXIMUM_LETOVA_NA_AERODROMU:
				int idAerodroma=(int) tf.getKlijent_slanje();
				TransferKlasa tf23=new TransferKlasa();
				int max=Kontroler.getInstanca().vratiMaximumLetovaAerodroma(idAerodroma);
				tf23.setServer_odgovor(max);
				posaljiPodatkeKlijentu(tf23);
			break;
		case SACUVAJ_LET:
				Let let=(Let) tf.getKlijent_slanje();
				TransferKlasa tf24=new TransferKlasa();
				Kontroler.getInstanca().sacuvajLet(let);
				tf24.setServer_odgovor_poruka("Podaci sacuvani");
				posaljiPodatkeKlijentu(tf24);
			break;
		case VRATI_LISTU_LETOVA:
				List<Let>listaLetova=Kontroler.getInstanca().vratiListuLetova();
				TransferKlasa tf25=new TransferKlasa();
				tf25.setServer_odgovor(listaLetova);
				posaljiPodatkeKlijentu(tf25);
			break;
		case OBRISI_LET:
				Let letZaObrisati=(Let) tf.getKlijent_slanje();
				TransferKlasa tf26=new TransferKlasa();
				Kontroler.getInstanca().obrisiLet(letZaObrisati);
				tf26.setServer_odgovor_poruka("Let je obrisan");
				posaljiPodatkeKlijentu(tf26);
			break;
		case MAXIMUM_POLETANJA_ZA_DAN:
				int id_aero=(int) tf.getKlijent_slanje();
				TransferKlasa tf27=new TransferKlasa();
				int maxPolet=Kontroler.getInstanca().VratiMaXPoletanja(id_aero);
				tf27.setServer_odgovor(maxPolet);
				posaljiPodatkeKlijentu(tf27);
			break;	
		case VRATI_LISTU_LETA_KONKRETNOG_AVIONA:
				int id_avio=(int) tf.getKlijent_slanje();
				TransferKlasa tf28=new TransferKlasa();
				List<Let>listaLeta1=Kontroler.getInstanca().vratiListuLetaKonkretnogAviona(id_avio);
				tf28.setServer_odgovor(listaLeta1);
				posaljiPodatkeKlijentu(tf28);
			break;
		case VRATI_BROJ_ZAUZETIH_POLETANJA:
				List<String>listaString = new ArrayList<>();
				listaString = (List<String>) tf.getKlijent_slanje();
				String datumZaProveru = listaString.get(0);
				int id_aerodromaZaProveru = Integer.parseInt(listaString.get(1));
				TransferKlasa tf29 = new TransferKlasa();
				int brojPoletanjaZaDatum = Kontroler.getInstanca().vratiBrojPoletanjaZaDatum(datumZaProveru,id_aerodromaZaProveru);
				tf29.setServer_odgovor(brojPoletanjaZaDatum);
				posaljiPodatkeKlijentu(tf29);
			break;
		case MAXIMUM_SLETANJA_ZA_DAN:
				int id_aero_slet = (int) tf.getKlijent_slanje();
				TransferKlasa tf30=new TransferKlasa();
				int maxSleta=Kontroler.getInstanca().vratiMaxSletanja(id_aero_slet);
				tf30.setServer_odgovor(maxSleta);
				posaljiPodatkeKlijentu(tf30);
			break;
		case VRATI_BROJ_ZAUZETIH_SLETANJA:
				List<String>listaString1 = new ArrayList<>();
				listaString1 = (List<String>) tf.getKlijent_slanje();
				String datumZaProveruSletanja= listaString1.get(0);
				int id_aerodromaZaProveruSletanja = Integer.parseInt(listaString1.get(1));
				TransferKlasa tf31 = new TransferKlasa();
				int brojSletanjaZaDatum = Kontroler.getInstanca().vratiBrojSletanjaZaDatum(datumZaProveruSletanja,id_aerodromaZaProveruSletanja);
				tf31.setServer_odgovor(brojSletanjaZaDatum);
				posaljiPodatkeKlijentu(tf31);
			break;
		case SACUVAJ_ZAHTEV:
				Zahtev zahtev = (Zahtev) tf.getKlijent_slanje();
				TransferKlasa tf32 = new TransferKlasa();
				Kontroler.getInstanca().sacuvajZahtev(zahtev);
				tf32.setServer_odgovor_poruka("Uspesno poslat zahtev");
				posaljiPodatkeKlijentu(tf32);
			break;
		case REALIZACIJA_ZAHTEVA:
				Zahtev zahtevR=(Zahtev) tf.getKlijent_slanje();
				TransferKlasa tf33 = new TransferKlasa();
				Kontroler.getInstanca().realizujZahtev(zahtevR);
				tf33.setServer_odgovor_poruka("Zahtev realizovan");
				posaljiPodatkeKlijentu(tf33);
			break;
		case VRATI_LISTU_AKTIVNIH_ZAHTEVA:
				int status_zahteva = (int) tf.getKlijent_slanje();
				List<Zahtev>listaZahteva = Kontroler.getInstanca().vratiListuAktivnihZahteva(status_zahteva);
				TransferKlasa tf34 = new TransferKlasa();
				tf34.setServer_odgovor(listaZahteva);
				posaljiPodatkeKlijentu(tf34);
			break;
		case PRETRAGA_LET:
				Let letPretraga=(Let) tf.getKlijent_slanje();
				List<Let>listPretraga=Kontroler.getInstanca().vratiListuTrazenogLeta(letPretraga);
				TransferKlasa tf35=new TransferKlasa();
				tf35.setServer_odgovor(listPretraga);
				posaljiPodatkeKlijentu(tf35);
			break;
		case UPISI_CENU_LETA:
				CenaKlasa cena = (CenaKlasa) tf.getKlijent_slanje();
				TransferKlasa tf36 = new TransferKlasa();
				Kontroler.getInstanca().sacuvajCene(cena);
				tf36.setServer_odgovor_poruka("Cene uspesno sacuvane");
				posaljiPodatkeKlijentu(tf36);
			break;
		case VRATI_CENU_KLASA_ZA_IZABRANI_LET:
				CenaKlasa cenaK=(CenaKlasa) tf.getKlijent_slanje();
				TransferKlasa tf37=new TransferKlasa();
				CenaKlasa cenovnik=Kontroler.getInstanca().vratiCenuKlasaZaIZabraniLet(cenaK);
				tf37.setServer_odgovor(cenovnik);
				posaljiPodatkeKlijentu(tf37);
			break;
		case SACUVAJ_REZERVACIJU:
				Rezervacija rezervacija = (Rezervacija) tf.getKlijent_slanje();
				TransferKlasa tf38 = new TransferKlasa();
				Kontroler.getInstanca().sacuvajRezervaciju(rezervacija);
				tf38.setServer_odgovor_poruka("Uspesna rezervacija Leta");
				posaljiPodatkeKlijentu(tf38);
			break;
		case IZMENI_PREOSTALI_BROJ_MESTA:
				CenaKlasa brojMesta = (CenaKlasa) tf.getKlijent_slanje();
				TransferKlasa tf39=new TransferKlasa();
				Kontroler.getInstanca().izmeniBrojMesta(brojMesta);
				tf39.setServer_odgovor("Uspesna Rezervacija");
				posaljiPodatkeKlijentu(tf39);
			break;	
		case IZMENA_KONTA:
				Profil kontoP=(Profil) tf.getKlijent_slanje();
				Kontroler.getInstanca().izmeniKontoNaProfilu(kontoP);
				TransferKlasa tf40 = new TransferKlasa();
				tf40.setServer_odgovor_poruka("Ok");
				posaljiPodatkeKlijentu(tf40);
			break;
		case VRATI_NAZIV_KOMPANIJE_RADNIKA:
				int idRadnika = (int) tf.getKlijent_slanje();
				String nazivKompanije = Kontroler.getInstanca().vratiNazivKompanijeRadnika(idRadnika); 
				TransferKlasa tf41 = new TransferKlasa();
				tf41.setServer_odgovor(nazivKompanije);
				posaljiPodatkeKlijentu(tf41);
			break;
		case VRATI_ADRESU_ZA_ID:
				int id_adrese=(int) tf.getKlijent_slanje();
				Adresa adresaAK=Kontroler.getInstanca().vratiAdresuZaID(id_adrese);
				TransferKlasa tf42 =new TransferKlasa();
				tf42.setServer_odgovor(adresaAK);
				posaljiPodatkeKlijentu(tf42);
			break;
		case VRATI_PROFIL_ZA_ID:
				int id_profil =(int) tf.getKlijent_slanje();
				Profil proZaId =Kontroler.getInstanca().vratiProfilZaId(id_profil);
				TransferKlasa tf43 =new TransferKlasa();
				tf43.setServer_odgovor(proZaId);
				posaljiPodatkeKlijentu(tf43);
			break;
		case VRATI_LISTU_LETOVA_AVIOKOMPANIJE_ZA_DATUM:
				Let letPretragaSalter = (Let) tf.getKlijent_slanje();
				List<Let>listaLetovaSalter = Kontroler.getInstanca().vratiListuLetovaZaKompaniju(letPretragaSalter);
				TransferKlasa tf44 =new TransferKlasa();
				tf44.setServer_odgovor(listaLetovaSalter);
				posaljiPodatkeKlijentu(tf44);
			break;
		case VRATI_LISTU_POVRATNIH_LETOVA_AVIOKOMPANIJE_ZA_DATUM:
				Let letPretragaSalterPovratni = (Let) tf.getKlijent_slanje();
				List<Let>listaLetovaSalterPovratni = Kontroler.getInstanca().vratiListuPovratnihLetovaZaKompaniju(letPretragaSalterPovratni);
				TransferKlasa tf45 =new TransferKlasa();
				tf45.setServer_odgovor(listaLetovaSalterPovratni);
				posaljiPodatkeKlijentu(tf45);
			break;
		case VRATI_PROFIL_ZA_EMAIL:
				String email = (String) tf.getKlijent_slanje();
				Profil profilZaMail = Kontroler.getInstanca().vratiProfilZaEMail(email);
				TransferKlasa tf46 = new TransferKlasa();
				tf46.setServer_odgovor(profilZaMail);
				posaljiPodatkeKlijentu(tf46);
			break;
		case VRATI_LISTU_REZERVACIJA_ZA_ID:
				int id = (int) tf.getKlijent_slanje();
				List<Rezervacija>listaRezervacija= Kontroler.getInstanca().vratiListuRezervacijaZaId(id);
				TransferKlasa tf47=new TransferKlasa();
				tf47.setServer_odgovor(listaRezervacija);
				posaljiPodatkeKlijentu(tf47);
			break;
		case VRATI_LISTU_LETOVA_VREME_DATUM:
				List<Let>listaLetovaOrderByVreme = new ArrayList<>();
				List<String>transferString = (List<String>) tf.getKlijent_slanje();
				String datumProveraVreme= transferString.get(0);
				int idKompanijeVreme = Integer.parseInt(transferString.get(1));
				listaLetovaOrderByVreme = Kontroler.getInstanca().vratiListuLetovaKompanijeOrdrByVreme(idKompanijeVreme,datumProveraVreme);
				TransferKlasa tf48 =new TransferKlasa();
				tf48.setServer_odgovor(listaLetovaOrderByVreme);
				posaljiPodatkeKlijentu(tf48);
			break;
		case VRATI_LISTU_REZERVACIJA_ZA_ID_LETA:
				int idLetovanja = (int) tf.getKlijent_slanje();
				List<Rezervacija>listaRezervacijaZaIdLet= Kontroler.getInstanca().vratiListuRezervacijaZaIdLeta(idLetovanja);
				TransferKlasa tf49=new TransferKlasa();
				tf49.setServer_odgovor(listaRezervacijaZaIdLet);
				posaljiPodatkeKlijentu(tf49);
			break;
		case OBRISI_REZERVACIJU:
				Rezervacija rezBris = (Rezervacija) tf.getKlijent_slanje();
				TransferKlasa tf50=new TransferKlasa();
				Kontroler.getInstanca().obrisiRezervaciju(rezBris);
				tf50.setServer_odgovor_poruka("Karta uspesno odstampana i rezervacija realizovana");
				posaljiPodatkeKlijentu(tf50);
			break;
		case OBRISI_RPROFIL:
				Profil profBris = (Profil) tf.getKlijent_slanje();
				TransferKlasa tf51 =new TransferKlasa();
				Kontroler.getInstanca().obrisiProfil(profBris);
				tf51.setServer_odgovor_poruka("Privremeni profil obrisan");
				posaljiPodatkeKlijentu(tf51);
			break;
		case VRATI_LISTU_LETOVA_SA_AERODROMA_ZA_DATUM:
				List<Let>listaLetovaAerodromaZaDanas = new ArrayList<>();
				List<String>transferStringova = (List<String>) tf.getKlijent_slanje();
				String datumProveraDanas= transferStringova.get(0);
				int idAerodromaDanas = Integer.parseInt(transferStringova.get(1));
				listaLetovaAerodromaZaDanas = Kontroler.getInstanca().vratiListuLetovaAerodromaDanas(idAerodromaDanas,datumProveraDanas);
				TransferKlasa tf52 =new TransferKlasa();
				tf52.setServer_odgovor(listaLetovaAerodromaZaDanas);
				posaljiPodatkeKlijentu(tf52);
			break;
			case VRATI_LISTU_SLETANJA_AERODROM_ZA_DATUM:
				List<Let>listaSletanja = new ArrayList<>();
				List<String>transferStr = (List<String>) tf.getKlijent_slanje();
				String datumSletanja= transferStr.get(0);
				int sletanjeNa = Integer.parseInt(transferStr.get(1));
				listaSletanja = Kontroler.getInstanca().vratiListuSletanjaAerodromaDanas(sletanjeNa,datumSletanja);
				TransferKlasa tf53 =new TransferKlasa();
				tf53.setServer_odgovor(listaSletanja);
				posaljiPodatkeKlijentu(tf53);
			break;
		default:
			break;
		}
		
	}
	private void posaljiPodatkeKlijentu(TransferKlasa tf1) {
		ObjectOutputStream outSoket;
		try {
			outSoket = new ObjectOutputStream(soket.getOutputStream());
			outSoket.writeObject(tf1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
