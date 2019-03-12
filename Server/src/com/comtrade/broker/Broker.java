package com.comtrade.broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.comtrade.domen.Adresa;
import com.comtrade.domen.CenaKlasa;
import com.comtrade.domen.Let;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.Profil;
import com.comtrade.domen.Rezervacija;
import com.comtrade.domen.Zahtev;
import com.comtrade.konekcija.Konekcija;

public class Broker implements IBroker {

	@Override
	public void upisi(OpstiDomen od) {

		String upit = " insert into " + "" + od.vratiNazivTabele() + "" + od.vratiNazivKolona() + ""
				+ od.vratiNazivZaUnos();
		Statement st;
		try {
			st = Konekcija.getInstanca().getKonekcija().createStatement();
			st.execute(upit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void obrisi(OpstiDomen od) throws Exception {
		String upit = " delete from " + "" + od.vratiNazivTabele() + "" + od.vratiNazivTabeleZaBrisanje(od);
		Statement st = Konekcija.getInstanca().getKonekcija().createStatement();
		st.execute(upit);
	}

	@Override
	public void izmeni(OpstiDomen od) {
		String upit = " update " + "" + od.vratiNazivTabele() + "" + od.vratiNazivTabeleZaIzmenu(od);
		Statement st;
		try {
			st = Konekcija.getInstanca().getKonekcija().createStatement();
			st.execute(upit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<OpstiDomen> uzmiSveIzTabele(OpstiDomen od) {
		List<OpstiDomen> list = null;
		String upit = "select * from " + od.vratiNazivTabele() + "";
		Statement st;
		try {
			st = Konekcija.getInstanca().getKonekcija().createStatement();
			ResultSet rs = st.executeQuery(upit);
			list = od.napuniListuZaSelect(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int vratiVrednost(String user, String pass) {
		String upit = "select * from profil where username = ? and password = ?";
		PreparedStatement ps;
		int vrednost = 0;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setString(1, user);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				vrednost = rs.getInt("id_status");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vrednost;
	}

	@Override
	public int vratiProfil(String user, String pass) {
		String upit = "select * from profil where username = ? and password = ?";
		PreparedStatement ps;
		int profil = 0;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setString(1, user);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				profil = rs.getInt("id_profil");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return profil;
	}

	@Override
	public int vratiVrednostMax(int id_kompanije) {
		String upit = "SELECT broj_poletanja,broj_sletanja,broj_pisti, (broj_poletanja+broj_sletanja)*broj_pisti AS maximum FROM karakteristike_aerodroma WHERE id_aerodroma =  ?";
		PreparedStatement ps;
		int max = 0;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, id_kompanije);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				max = rs.getInt("maximum");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return max;
	}

	@Override
	public int vratiMaxPoletanja(int id) {
		String upit = "SELECT broj_poletanja,broj_pisti,(broj_poletanja*broj_pisti) AS max_polet FROM karakteristike_aerodroma WHERE id_aerodroma = ?";
		PreparedStatement ps;
		int maxPolet = 0;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				maxPolet = rs.getInt("max_polet");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maxPolet;
	}

	@Override
	public List<Let> vratiListuLetovaKOA(int id_avio) {
		List<Let> list = new ArrayList<>();
		String upit = "select * from let WHERE id_aviona = ? order by datum";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, id_avio);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Let l = new Let();
				int id_leta = rs.getInt("id_leta");
				String oznaka = rs.getString("oznaka");
				int id_iz = rs.getInt("id_aerodrom_iz");
				int id_za = rs.getInt("id_aerodrom_za");
				int id_a = rs.getInt("id_aviona");
				String datum = String.valueOf(rs.getDate("datum"));
				String vreme = String.valueOf(rs.getTime("vreme"));
				int id_aviokomp = rs.getInt("id_aviokompanije");
				l.setId_aviona(id_a);
				l.setDatum(datum);
				l.setId_aerodroma_iz(id_iz);
				l.setId_aerodroma_za(id_za);
				l.setId_leta(id_leta);
				l.setOznaka(oznaka);
				l.setVreme(vreme);
				l.setId_aviokompanije(id_aviokomp);
				list.add(l);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public int vratiVrednostBrojaLetovaZaDatum(String datum, int id) {
		String upit = "select COUNT(1) FROM let WHERE datum = DATE( ? ) AND id_aerodrom_iz = ? ";
		PreparedStatement ps;
		int brojPolet = 0;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setString(1, datum);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				brojPolet = rs.getInt("COUNT(1)");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return brojPolet;
	}

	@Override
	public int vratiMaxSletanja(int id) {
		String upit = "SELECT broj_sletanja,broj_pisti,(broj_sletanja*broj_pisti) AS max_slet FROM karakteristike_aerodroma WHERE id_aerodroma = ?";
		PreparedStatement ps;
		int maxSlet = 0;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				maxSlet = rs.getInt("max_slet");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maxSlet;
	}

	@Override
	public int vratiVrednostBrojaSletanjaZaDatum(String datum, int id) {
		String upit = "select COUNT(1) FROM let WHERE datum = DATE( ? ) AND id_aerodrom_za = ? ";
		PreparedStatement ps;
		int brojsletanja = 0;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setString(1, datum);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				brojsletanja = rs.getInt("COUNT(1)");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return brojsletanja;
	}

	@Override
	public List<Zahtev> vratiListuAktivnihZahteva(int status_zahteva) {
		List<Zahtev> lista = new ArrayList<>();
		String upit = "SELECT * FROM zahtev WHERE status_zahteva = ?";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, status_zahteva);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Zahtev z = new Zahtev();
				int id = rs.getInt("id_zahtev");
				String zahtev = rs.getString("zahtev_txt");
				z.setId_zahteva(id);
				z.setZahtev_txt(zahtev);
				lista.add(z);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}

	@Override
	public List<Let> vratiListuTrazenogLeta(Let let) {
		List<Let> lista = new ArrayList<>();
		String upit = "SELECT * FROM `let` WHERE id_aerodrom_iz = ? AND id_aerodrom_za = ? AND datum = ?";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, let.getId_aerodroma_iz());
			ps.setInt(2, let.getId_aerodroma_za());
			ps.setString(3, let.getDatum());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Let l = new Let();
				int id_let = rs.getInt("id_leta");
				String oznaka = rs.getString("oznaka");
				int id_aero_iz = rs.getInt("id_aerodrom_iz");
				int id_aero_za = rs.getInt("id_aerodrom_za");
				int id_avio = rs.getInt("id_aviona");
				String datume = String.valueOf(rs.getDate("datum"));
				String vreme = String.valueOf(rs.getTime("vreme"));
				int id_aviokomp = rs.getInt("id_aviokompanije");
				l.setId_leta(id_let);
				l.setOznaka(oznaka);
				l.setId_aerodroma_iz(id_aero_iz);
				l.setId_aerodroma_za(id_aero_za);
				l.setDatum(datume);
				l.setVreme(vreme);
				l.setId_aviona(id_avio);
				l.setId_aviokompanije(id_aviokomp);
				lista.add(l);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}

	@Override
	public CenaKlasa vratiCenuKlasaZaIzabraniLet(CenaKlasa cena) {

		CenaKlasa ck = new CenaKlasa();
		int id_leta = cena.getId_leta();
		int id_avio = cena.getId_aviokompanije();
		String upit = "SELECT * FROM cena_klasa WHERE id_leta = ? AND id_aviokompanija = ? ";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, id_leta);
			ps.setInt(2, id_avio);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id_ck = rs.getInt("id_cena_klasa");
				int id = rs.getInt("id_leta");
				int Id1 = rs.getInt("id_aviokompanija");
				double eko = rs.getDouble("ekonomska");
				double biz = rs.getDouble("biznis");
				double aKl = rs.getDouble("a_klasa");
				double odrasli = rs.getDouble("odrasli");
				double deca = rs.getDouble("deca");
				double index1 = rs.getDouble("30dana");
				double index2 = rs.getDouble("15dana");
				double index3 = rs.getDouble("2dana");
				int br_karta = rs.getInt("broj_karata");
				ck.setId(id_ck);
				ck.setId_leta(id);
				ck.setId_aviokompanije(Id1);
				ck.setEkonomska(eko);
				ck.setBiznis(biz);
				ck.setA_klasa(aKl);
				ck.setDeca(deca);
				ck.setOdrasli(odrasli);
				ck.setDan30(index1);
				ck.setDan15(index2);
				ck.setDan2(index3);
				ck.setBroj_karata(br_karta);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ck;
	}

	@Override
	public String vratiNazivKompanijeRadnika(int idRadnika) {
		String naziv = null;
		String upit = "SELECT `naziv_aviokompanije` FROM `radnik` WHERE id_profila = ?";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, idRadnika);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				naziv = rs.getString("naziv_aviokompanije");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return naziv;
	}

	@Override
	public Adresa vratiAdresuZaId(int id) {
		Adresa a = new Adresa();
		String upit = "SELECT `id_adresa`, `naziv_ulice_broj`, `naziv_grada`, `naziv_drzave` FROM `adresa` WHERE id_adresa = ?";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id_a = rs.getInt("id_adresa");
				String ulica = rs.getString("naziv_ulice_broj");
				String grad = rs.getString("naziv_grada");
				String drzava = rs.getString("naziv_drzave");
				a.setId_adresa(id_a);
				a.setNaziv_ulice_broj(ulica);
				a.setNaziv_grada(grad);
				a.setNaziv_drzave(drzava);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}

	@Override
	public Profil vratiProfilZaId(int id) {
		Profil p = new Profil();
		String upit = "SELECT `id_profil`, `ime`, `prezime`, `e_mail` FROM `profil` WHERE id_profil = ? ";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id_a = rs.getInt("id_profil");
				String ime = rs.getString("ime");
				String prezime = rs.getString("prezime");
				String email = rs.getString("e_mail");
				p.setE_mail(email);
				p.setIme(ime);
				p.setPrezime(prezime);
				p.setId_profil(id_a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return p;
	}

	@Override
	public List<Let> vratiListuTrazenogLetaZaKompaniju(Let let) {
		List<Let> lista = new ArrayList<>();
		String upit = "SELECT * FROM `let` WHERE id_aerodrom_iz = ? AND id_aerodrom_za = ? AND datum = ? AND id_aviokompanije = ?";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, let.getId_aerodroma_iz());
			ps.setInt(2, let.getId_aerodroma_za());
			ps.setString(3, let.getDatum());
			ps.setInt(4, let.getId_aviokompanije());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Let l = new Let();
				int id_let = rs.getInt("id_leta");
				String oznaka = rs.getString("oznaka");
				int id_aero_iz = rs.getInt("id_aerodrom_iz");
				int id_aero_za = rs.getInt("id_aerodrom_za");
				int id_avio = rs.getInt("id_aviona");
				String datume = String.valueOf(rs.getDate("datum"));
				String vreme = String.valueOf(rs.getTime("vreme"));
				int id_aviokomp = rs.getInt("id_aviokompanije");
				l.setId_leta(id_let);
				l.setOznaka(oznaka);
				l.setId_aerodroma_iz(id_aero_iz);
				l.setId_aerodroma_za(id_aero_za);
				l.setDatum(datume);
				l.setVreme(vreme);
				l.setId_aviona(id_avio);
				l.setId_aviokompanije(id_aviokomp);
				lista.add(l);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}

	@Override
	public List<Let> vratiListuPovratnihLetovaZaKompaniju(Let let) {
		List<Let> lista = new ArrayList<>();
		String upit = "SELECT * FROM `let` WHERE id_aerodrom_iz = ? AND id_aerodrom_za = ? AND datum >= ? AND id_aviokompanije = ?";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, let.getId_aerodroma_iz());
			ps.setInt(2, let.getId_aerodroma_za());
			ps.setString(3, let.getDatum());
			ps.setInt(4, let.getId_aviokompanije());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Let l = new Let();
				int id_let = rs.getInt("id_leta");
				String oznaka = rs.getString("oznaka");
				int id_aero_iz = rs.getInt("id_aerodrom_iz");
				int id_aero_za = rs.getInt("id_aerodrom_za");
				int id_avio = rs.getInt("id_aviona");
				String datume = String.valueOf(rs.getDate("datum"));
				String vreme = String.valueOf(rs.getTime("vreme"));
				int id_aviokomp = rs.getInt("id_aviokompanije");
				l.setId_leta(id_let);
				l.setOznaka(oznaka);
				l.setId_aerodroma_iz(id_aero_iz);
				l.setId_aerodroma_za(id_aero_za);
				l.setDatum(datume);
				l.setVreme(vreme);
				l.setId_aviona(id_avio);
				l.setId_aviokompanije(id_aviokomp);
				lista.add(l);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}

	@Override
	public Profil vratiProfilZaEMail(String email) {
		Profil p = new Profil();
		String upit = "SELECT `id_profil`, `ime`, `prezime`, `e_mail`, `id_status`, `istorija_kupovina` FROM `profil` WHERE e_mail = ? ";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id_a = rs.getInt("id_profil");
				String ime = rs.getString("ime");
				String prezime = rs.getString("prezime");
				String email1 = rs.getString("e_mail");
				int status = rs.getInt("id_status");
				double istorija = rs.getDouble("istorija_kupovina");
				p.setE_mail(email1);
				p.setIme(ime);
				p.setPrezime(prezime);
				p.setId_profil(id_a);
				p.setIstorija_kupovina(istorija);
				p.setId_status(status);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return p;
	}

	@Override
	public List<Rezervacija> vratiListuRezervacijaZaId(int id) {
		List<Rezervacija> list = new ArrayList<>();
		String upit = "SELECT * FROM `rezervacija` WHERE id_profila = ?";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id_rez = rs.getInt("id_rezervacije");
				int id_profila = rs.getInt("id_profila");
				int id_leta = rs.getInt("id_leta");
				int brojKarata = rs.getInt("broj_karata");
				int id_avioK = rs.getInt("id_aviokompanija");
				double cena = rs.getDouble("cena");
				Rezervacija rez = new Rezervacija();
				rez.setId_rezervacije(id_rez);
				rez.setId_profila(id_profila);
				rez.setId_leta(id_leta);
				rez.setBroj_karata(brojKarata);
				rez.setId_aviokompanije(id_avioK);
				rez.setCena(cena);
				list.add(rez);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<Let> vratiListuLetovaOrderVreme(int id, String datum) {
		List<Let> list = new ArrayList<>();
		String upit = "SELECT * FROM `let` WHERE datum = ? AND id_aviokompanije = ? ORDER BY `let`.`datum`,let.vreme ASC";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setString(1, datum);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Let l = new Let();
				int id_let = rs.getInt("id_leta");
				String oznaka = rs.getString("oznaka");
				int id_aero_iz = rs.getInt("id_aerodrom_iz");
				int id_aero_za = rs.getInt("id_aerodrom_za");
				int id_avio = rs.getInt("id_aviona");
				String datume = String.valueOf(rs.getDate("datum"));
				String vreme = String.valueOf(rs.getTime("vreme"));
				int id_aviokomp = rs.getInt("id_aviokompanije");
				l.setId_leta(id_let);
				l.setOznaka(oznaka);
				l.setId_aerodroma_iz(id_aero_iz);
				l.setId_aerodroma_za(id_aero_za);
				l.setDatum(datume);
				l.setVreme(vreme);
				l.setId_aviona(id_avio);
				l.setId_aviokompanije(id_aviokomp);
				list.add(l);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	@Override
	public List<Rezervacija> vratiListuRezervacijaZaIdLeta(int id) {
		List<Rezervacija> list = new ArrayList<>();
		String upit = "SELECT * FROM `rezervacija` WHERE id_leta = ?";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id_rez = rs.getInt("id_rezervacije");
				int id_profila = rs.getInt("id_profila");
				int id_leta = rs.getInt("id_leta");
				int brojKarata = rs.getInt("broj_karata");
				int id_avioK = rs.getInt("id_aviokompanija");
				double cena = rs.getDouble("cena");
				Rezervacija rez = new Rezervacija();
				rez.setId_rezervacije(id_rez);
				rez.setId_profila(id_profila);
				rez.setId_leta(id_leta);
				rez.setBroj_karata(brojKarata);
				rez.setId_aviokompanije(id_avioK);
				rez.setCena(cena);
				list.add(rez);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<Let> vratiListuLetovaDanasSaAerodroma(int id, String datum) {
		List<Let> list = new ArrayList<>();
		String upit = "SELECT * FROM `let` WHERE datum = ? AND id_aerodrom_iz = ? ORDER BY `let`.`datum`,let.vreme ASC";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setString(1, datum);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Let l = new Let();
				int id_let = rs.getInt("id_leta");
				String oznaka = rs.getString("oznaka");
				int id_aero_iz = rs.getInt("id_aerodrom_iz");
				int id_aero_za = rs.getInt("id_aerodrom_za");
				int id_avio = rs.getInt("id_aviona");
				String datume = String.valueOf(rs.getDate("datum"));
				String vreme = String.valueOf(rs.getTime("vreme"));
				int id_aviokomp = rs.getInt("id_aviokompanije");
				l.setId_leta(id_let);
				l.setOznaka(oznaka);
				l.setId_aerodroma_iz(id_aero_iz);
				l.setId_aerodroma_za(id_aero_za);
				l.setDatum(datume);
				l.setVreme(vreme);
				l.setId_aviona(id_avio);
				l.setId_aviokompanije(id_aviokomp);
				list.add(l);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<Let> vratiListuSletanjaDanasNaAerodrom(int id, String datum) {
		List<Let> list = new ArrayList<>();
		String upit = "SELECT * FROM `let` WHERE datum = ? AND id_aerodrom_za = ? ORDER BY `let`.`datum`,let.vreme ASC";
		PreparedStatement ps;
		try {
			ps = Konekcija.getInstanca().getKonekcija().prepareStatement(upit);
			ps.setString(1, datum);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Let l = new Let();
				int id_let = rs.getInt("id_leta");
				String oznaka = rs.getString("oznaka");
				int id_aero_iz = rs.getInt("id_aerodrom_iz");
				int id_aero_za = rs.getInt("id_aerodrom_za");
				int id_avio = rs.getInt("id_aviona");
				String datume = String.valueOf(rs.getDate("datum"));
				String vreme = String.valueOf(rs.getTime("vreme"));
				int id_aviokomp = rs.getInt("id_aviokompanije");
				l.setId_leta(id_let);
				l.setOznaka(oznaka);
				l.setId_aerodroma_iz(id_aero_iz);
				l.setId_aerodroma_za(id_aero_za);
				l.setDatum(datume);
				l.setVreme(vreme);
				l.setId_aviona(id_avio);
				l.setId_aviokompanije(id_aviokomp);
				list.add(l);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

}
