package com.comtrade.salter;

import java.awt.Image;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.comtrade.domen.Adresa;
import com.comtrade.domen.Aerodrom;
import com.comtrade.domen.AvioKompanija;
import com.comtrade.domen.CenaKlasa;
import com.comtrade.domen.Let;
import com.comtrade.domen.Profil;
import com.comtrade.domen.Rezervacija;
import com.comtrade.kontroler.korisnickoginterfejsa.KontrolerKI;
import com.comtrade.putnik.PutnikForma;
import com.comtrade.transfer.TransferKlasa;
import com.comtrade.view.panel.PanelSlikaRezervacija;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLayeredPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class SalterForma extends JFrame {

	private JPanel contentPane;
	private List<AvioKompanija> listaAvioKompanije = new ArrayList<>();
	private List<Aerodrom> listaAerodroma = new ArrayList<>();
	private List<Let> korpa = new ArrayList<>();
	private AvioKompanija avioKompanija;
	private Adresa adresaKompanije;
	private PanelSlikaRezervacija panelRezervacija;
	private PanelSlikaRezervacija panelPrijava;
	private JLayeredPane layeredPane;
	private JTextField tfKonacnaCena;
	private Profil profilRadnika;
	private JComboBox comboBoxIz;
	private JComboBox comboBoxZa;
	private JTable tabelaLetova;
	private JTable tabelaLetovaDolazni;
	private JTable tabelaKorpa;
	private JTable tabelaRezervacija;
	private JTable tabelaLetovaByVreme;
	private DefaultTableModel dtmLetovi = new DefaultTableModel();
	private DefaultTableModel dtmLetoviDolazni = new DefaultTableModel();
	private DefaultTableModel dtmKorpa = new DefaultTableModel();
	private DefaultTableModel dtmRezervacija = new DefaultTableModel();
	private DefaultTableModel dtmLetoviByVreme = new DefaultTableModel();
	private JDateChooser dateChooserPolazak;
	private int id_aerodroma_iz;
	private int id_aerodroma_za;
	private int id_rezervacije;
	private int status_profila;
	private JCheckBox chekBoxPovratni;
	private CenaKlasa odgovor;
	private JLabel lblDanasnjiDatum;
	private ButtonGroup bg = new ButtonGroup();
	private JCheckBox chekBoxDeca;
	private JRadioButton radioBtnEkonomska;
	private JRadioButton radioBtnBiznis;
	private JRadioButton radioBtnAKlasa;
	private JTextField tfBrojKarata;
	private JLabel lblPrezime;
	private JLabel lblEmail;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfEmail;
	private JButton btnGostPutnikUnosProfila;
	private JButton btnUcitaj;
	private int id_status_korisnika = 6;
	private JTextField tfIdProfila;
	private JTextField tfKontoProfila;
	private JTextField tfPrijavaEmail;
	private JButton btnPretragaRezervacija;
	private JTextField tfImePrijava;
	private JTextField tfPrezimePrijava;
	private JTextField tfProfilPrijava;
	private JButton btnUcitajRezervacije;
	private JButton btnStampaj;
	private JTextField tfStatusProfila;
	

	public SalterForma(int id_profil) {
		setResizable(false);
		listaAerodroma = ucitajAerodrome();
		listaAvioKompanije = ucitajAvioKompanije();
		String nazivAvioKompanijeRadnika = vratiKompanijuRadnika(id_profil);
		avioKompanija = postaviPodatkeAvioKompanije(nazivAvioKompanijeRadnika);
		adresaKompanije = ucitajAdresuZaId(avioKompanija.getAdresa());
		profilRadnika = ucitajProfilZaId(id_profil);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 1000, 625);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Salter Rezervacije");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				promenaPanela(panelRezervacija);
			}
		});
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("Salter Prijava");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				promenaPanela(panelPrijava);
			}
		});
		menuBar.add(mnNewMenu_1);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image slika1 = new ImageIcon(this.getClass().getResource("/slika10.jpg")).getImage();

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(21, 51, 953, 476);
		contentPane.add(layeredPane);

		panelPrijava = new PanelSlikaRezervacija();
		panelPrijava.setBounds(0, 0, 953, 476);
		layeredPane.add(panelPrijava);
		panelPrijava.setLayout(null);

		tfPrijavaEmail = new JTextField();
		tfPrijavaEmail.setHorizontalAlignment(SwingConstants.CENTER);
		tfPrijavaEmail.setBounds(36, 43, 193, 20);
		panelPrijava.add(tfPrijavaEmail);
		tfPrijavaEmail.setColumns(10);

		btnPretragaRezervacija = new JButton("Pretraga");
		btnPretragaRezervacija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfPrijavaEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Unesite podatke klijenta");
				} else {
					String email = tfPrijavaEmail.getText();
					Profil prof = ucitajProfilZaEmail(email);
					String ime = prof.getIme();
					String prezime = prof.getPrezime();
					String email2213 = prof.getE_mail();
					int id = prof.getId_profil();
					status_profila = prof.getId_status();
					double konto = prof.getIstorija_kupovina();
					if (email2213 != null) {
						tfImePrijava.setText(ime);
						tfPrezimePrijava.setText(prezime);
						tfProfilPrijava.setText(String.valueOf(id));
						tfStatusProfila.setText(String.valueOf(status_profila));
					} else {
						JOptionPane.showMessageDialog(null, "Ne postoji profil sa tim mailom");
					}
				}
				tfPrijavaEmail.setText("");
			}
		});
		btnPretragaRezervacija.setBounds(239, 42, 89, 23);
		panelPrijava.add(btnPretragaRezervacija);

		tfImePrijava = new JTextField();
		tfImePrijava.setEditable(false);
		tfImePrijava.setHorizontalAlignment(SwingConstants.CENTER);
		tfImePrijava.setBounds(36, 140, 160, 20);
		panelPrijava.add(tfImePrijava);
		tfImePrijava.setColumns(10);

		tfPrezimePrijava = new JTextField();
		tfPrezimePrijava.setEditable(false);
		tfPrezimePrijava.setHorizontalAlignment(SwingConstants.CENTER);
		tfPrezimePrijava.setColumns(10);
		tfPrezimePrijava.setBounds(36, 171, 160, 20);
		panelPrijava.add(tfPrezimePrijava);

		JLabel lblNewLabel = new JLabel("Podaci o putniku");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(36, 100, 193, 29);
		panelPrijava.add(lblNewLabel);

		tabelaRezervacija = new JTable(dtmRezervacija);
		tabelaRezervacija.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red =tabelaRezervacija.getSelectedRow();
				id_rezervacije = Integer.parseInt(tabelaRezervacija.getValueAt(red, 0).toString());
			}
		});
		JScrollPane scrollPane_3 = new JScrollPane(tabelaRezervacija);
		scrollPane_3.setBounds(36, 236, 405, 116);
		panelPrijava.add(scrollPane_3);
		scrollPane_3.setViewportView(tabelaRezervacija);

		tfProfilPrijava = new JTextField();
		tfProfilPrijava.setHorizontalAlignment(SwingConstants.CENTER);
		tfProfilPrijava.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfProfilPrijava.setEditable(false);
		tfProfilPrijava.setBounds(152, 69, 44, 20);
		panelPrijava.add(tfProfilPrijava);
		tfProfilPrijava.setColumns(10);

		JLabel lblBrprofila = new JLabel("Br.Profila:");
		lblBrprofila.setForeground(Color.BLACK);
		lblBrprofila.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBrprofila.setBounds(36, 65, 89, 29);
		panelPrijava.add(lblBrprofila);

		btnUcitajRezervacije = new JButton("Ucitaj");
		btnUcitajRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfProfilPrijava.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nema nista!!!");
				} else {
					int id = Integer.parseInt(tfProfilPrijava.getText().toString());
					List<Rezervacija> listaRezervacija = new ArrayList<>();
					listaRezervacija = ucitajRezervacijeZaIdProfila(id);
					popuniTabeluRezervacije(listaRezervacija);
				}
			}
		});
		btnUcitajRezervacije.setBounds(36, 202, 89, 23);
		panelPrijava.add(btnUcitajRezervacije);
		
		JLabel lblNewLabel_1 = new JLabel("Danasnj letovi:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(478, 191, 130, 29);
		panelPrijava.add(lblNewLabel_1);
		
		tabelaLetovaByVreme = new JTable(dtmLetoviByVreme);
		tabelaLetovaByVreme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red = tabelaLetovaByVreme.getSelectedRow();
				int idLeta = Integer.parseInt(tabelaLetovaByVreme.getValueAt(red, 0).toString());
				List<Rezervacija>list = vratiListuRezervacijaZaIDLeta(idLeta);
				popuniTabeluRezervacije(list);
			}
		});
		JScrollPane scrollPane_4 = new JScrollPane(tabelaLetovaByVreme);
		scrollPane_4.setBounds(478, 236, 465, 116);
		panelPrijava.add(scrollPane_4);
		scrollPane_4.setViewportView(tabelaLetovaByVreme);
		
		btnStampaj = new JButton("Stampaj kartu i realizuj rezervaciju");
		btnStampaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int profbris=Integer.parseInt(tfProfilPrijava.getText().toString());
				if(id_rezervacije!=0){
					Rezervacija r = new Rezervacija();
					r.setId_rezervacije(id_rezervacije);
					realizujRezervaciju(r);
					dtmRezervacija.setRowCount(0);
					if(status_profila==6){
						Profil p = new Profil();
						p.setId_profil(profbris);
						obrisiPrivremeniProfil(p);
						tfProfilPrijava.setText("");
						tfStatusProfila.setText("");
						tfImePrijava.setText("");
						tfPrezimePrijava.setText("");
					}
				}
			}
		});
		btnStampaj.setBounds(36, 363, 405, 20);
		panelPrijava.add(btnStampaj);
		
		tfStatusProfila = new JTextField();
		tfStatusProfila.setHorizontalAlignment(SwingConstants.CENTER);
		tfStatusProfila.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfStatusProfila.setEditable(false);
		tfStatusProfila.setColumns(10);
		tfStatusProfila.setBounds(206, 69, 23, 20);
		panelPrijava.add(tfStatusProfila);
		Object [] byvreme={"Id Leta","Oznaka","Iz","Za","Datum","Vreme"};
		dtmLetoviByVreme.addColumn(byvreme[0]);
		dtmLetoviByVreme.addColumn(byvreme[1]);
		dtmLetoviByVreme.addColumn(byvreme[2]);
		dtmLetoviByVreme.addColumn(byvreme[3]);
		dtmLetoviByVreme.addColumn(byvreme[4]);
		dtmLetoviByVreme.addColumn(byvreme[5]);
		
		

		panelRezervacija = new PanelSlikaRezervacija();
		panelRezervacija.setBounds(0, 0, 953, 476);
		layeredPane.add(panelRezervacija);
		panelRezervacija.setLayout(null);

		tfKonacnaCena = new JTextField();
		tfKonacnaCena.setFont(new Font("Tahoma", Font.BOLD, 15));
		tfKonacnaCena.setEditable(false);
		tfKonacnaCena.setBounds(610, 415, 52, 20);
		panelRezervacija.add(tfKonacnaCena);
		tfKonacnaCena.setColumns(10);

		dateChooserPolazak = new JDateChooser();
		dateChooserPolazak.setBounds(285, 11, 121, 20);
		panelRezervacija.add(dateChooserPolazak);

		JLabel lblIme = new JLabel("Ime");
		lblIme.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblIme.setBounds(661, 77, 89, 20);
		panelRezervacija.add(lblIme);

		comboBoxIz = new JComboBox();
		comboBoxIz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv = comboBoxIz.getSelectedItem().toString();
				for (Aerodrom a : listaAerodroma) {
					if (naziv.equals(a.getNaziv())) {
						id_aerodroma_iz = a.getId_aerodroma();
						System.out.println(id_aerodroma_iz);
					}
				}
			}
		});
		comboBoxIz.setBounds(10, 11, 121, 20);
		panelRezervacija.add(comboBoxIz);
		postaviAerodromeUComboBox(comboBoxIz);

		comboBoxZa = new JComboBox();
		comboBoxZa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv = comboBoxZa.getSelectedItem().toString();
				for (Aerodrom a : listaAerodroma) {
					if (naziv.equals(a.getNaziv())) {
						id_aerodroma_za = a.getId_aerodroma();
						System.out.println(id_aerodroma_za);
					}
				}
			}
		});
		comboBoxZa.setBounds(141, 11, 121, 20);
		panelRezervacija.add(comboBoxZa);
		postaviAerodromeUComboBox(comboBoxZa);

		JButton btnPretragaLetova = new JButton("Pretraga");
		btnPretragaLetova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtmLetovi.setRowCount(0);
				dtmLetoviDolazni.setRowCount(0);
				if (dateChooserPolazak.getDate() != null) {
					if (chekBoxPovratni.isSelected()) {
						List<Let> listaOdlaznihLetova = new ArrayList<>();
						List<Let> listaDolaznihLetova = new ArrayList<>();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String datum = sdf.format(dateChooserPolazak.getDate());
						Let l = new Let();
						l.setDatum(datum);
						l.setId_aviokompanije(avioKompanija.getId_aviokompanija());
						l.setId_aerodroma_iz(id_aerodroma_iz);
						l.setId_aerodroma_za(id_aerodroma_za);
						listaOdlaznihLetova = vratiListuLetovaKompanije(l);
						postaviPodatkeUTabeluOdlazniLetovi(listaOdlaznihLetova);

						Let l2 = new Let();
						l2.setId_aviokompanije(avioKompanija.getId_aviokompanija());
						l2.setId_aerodroma_iz(id_aerodroma_za);
						l2.setId_aerodroma_za(id_aerodroma_iz);
						l2.setDatum(datum);
						listaDolaznihLetova = vratiListuPovratnihLetovaKompanije(l2);
						postaviPodatkeUTabeluDolazniLetovi(listaDolaznihLetova);

					} else {
						List<Let> listaOdlaznihLetova = new ArrayList<>();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String datum = sdf.format(dateChooserPolazak.getDate());
						Let l = new Let();
						l.setDatum(datum);
						l.setId_aviokompanije(avioKompanija.getId_aviokompanija());
						l.setId_aerodroma_iz(id_aerodroma_iz);
						l.setId_aerodroma_za(id_aerodroma_za);
						listaOdlaznihLetova = vratiListuLetovaKompanije(l);
						postaviPodatkeUTabeluOdlazniLetovi(listaOdlaznihLetova);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Izaberite datum polaska");
				}

			}
		});
		btnPretragaLetova.setBounds(10, 47, 111, 23);
		panelRezervacija.add(btnPretragaLetova);

		tabelaLetova = new JTable(dtmLetovi);
		tabelaLetova.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red = tabelaLetova.getSelectedRow();
				int id_leta = Integer.parseInt(tabelaLetova.getValueAt(red, 0).toString());
				CenaKlasa ck = new CenaKlasa();
				ck.setId_aviokompanije(avioKompanija.getId_aviokompanija());
				ck.setId_leta(id_leta);
				odgovor = ucitajCenuKlasaZaLet(ck);
				Let letZaKorpu = new Let();
				letZaKorpu.setId_leta(id_leta);
				letZaKorpu.setId_aviokompanije(avioKompanija.getId_aviokompanija());
				letZaKorpu.setOznaka(tabelaLetova.getValueAt(red, 1).toString());
				letZaKorpu.setDatum(tabelaLetova.getValueAt(red, 5).toString());
				letZaKorpu.setVreme(tabelaLetova.getValueAt(red, 6).toString());
				korpa.add(letZaKorpu);
				postaviPodatkeUtabeluKorpa();
				korpa.removeAll(korpa);
			}
		});
		JScrollPane scrollPane = new JScrollPane(tabelaLetova);
		scrollPane.setBounds(10, 81, 536, 124);
		panelRezervacija.add(scrollPane);
		scrollPane.setViewportView(tabelaLetova);

		chekBoxPovratni = new JCheckBox("Povratni");
		chekBoxPovratni.setBounds(449, 11, 97, 23);
		panelRezervacija.add(chekBoxPovratni);

		tabelaLetovaDolazni = new JTable(dtmLetoviDolazni);
		tabelaLetovaDolazni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red = tabelaLetovaDolazni.getSelectedRow();
				int id_leta = Integer.parseInt(tabelaLetovaDolazni.getValueAt(red, 0).toString());
				CenaKlasa ck = new CenaKlasa();
				ck.setId_aviokompanije(avioKompanija.getId_aviokompanija());
				ck.setId_leta(id_leta);
				odgovor = ucitajCenuKlasaZaLet(ck);
				Let letZaKorpu = new Let();
				letZaKorpu.setId_leta(id_leta);
				letZaKorpu.setId_aviokompanije(avioKompanija.getId_aviokompanija());
				letZaKorpu.setOznaka(tabelaLetovaDolazni.getValueAt(red, 1).toString());
				letZaKorpu.setDatum(tabelaLetovaDolazni.getValueAt(red, 5).toString());
				letZaKorpu.setVreme(tabelaLetovaDolazni.getValueAt(red, 6).toString());
				korpa.add(letZaKorpu);
				postaviPodatkeUtabeluKorpa();
				korpa.removeAll(korpa);
			}
		});
		JScrollPane scrollPane_1 = new JScrollPane(tabelaLetovaDolazni);
		scrollPane_1.setBounds(10, 227, 536, 124);
		panelRezervacija.add(scrollPane_1);
		scrollPane_1.setViewportView(tabelaLetovaDolazni);

		tabelaKorpa = new JTable(dtmKorpa);
		tabelaKorpa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red = tabelaKorpa.getSelectedRow();
				String datumLeta = tabelaKorpa.getValueAt(red, 2).toString();
				izracunajCenu(odgovor, datumLeta);
			}
		});
		JScrollPane scrollPane_2 = new JScrollPane(tabelaKorpa);
		scrollPane_2.setBounds(610, 347, 333, 57);
		panelRezervacija.add(scrollPane_2);
		scrollPane_2.setViewportView(tabelaKorpa);

		chekBoxDeca = new JCheckBox("Deca");
		chekBoxDeca.setBounds(723, 291, 109, 23);
		panelRezervacija.add(chekBoxDeca);

		radioBtnEkonomska = new JRadioButton("Ekonomska");
		radioBtnEkonomska.setSelected(true);
		radioBtnEkonomska.setBounds(612, 317, 109, 23);
		panelRezervacija.add(radioBtnEkonomska);
		bg.add(radioBtnEkonomska);

		radioBtnBiznis = new JRadioButton("Biznis");
		radioBtnBiznis.setBounds(723, 317, 109, 23);
		panelRezervacija.add(radioBtnBiznis);
		bg.add(radioBtnBiznis);

		radioBtnAKlasa = new JRadioButton("A Klasa");
		radioBtnAKlasa.setBounds(834, 317, 109, 23);
		panelRezervacija.add(radioBtnAKlasa);
		bg.add(radioBtnAKlasa);

		tfBrojKarata = new JTextField();
		tfBrojKarata.setBounds(912, 417, 31, 20);
		panelRezervacija.add(tfBrojKarata);
		tfBrojKarata.setColumns(10);

		lblPrezime = new JLabel("Prezime");
		lblPrezime.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPrezime.setBounds(661, 108, 89, 20);
		panelRezervacija.add(lblPrezime);

		lblEmail = new JLabel("E-Mail*");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblEmail.setBounds(661, 50, 89, 20);
		panelRezervacija.add(lblEmail);

		tfIme = new JTextField();
		tfIme.setBounds(768, 79, 175, 20);
		panelRezervacija.add(tfIme);
		tfIme.setColumns(10);

		tfPrezime = new JTextField();
		tfPrezime.setColumns(10);
		tfPrezime.setBounds(768, 110, 175, 20);
		panelRezervacija.add(tfPrezime);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(768, 48, 175, 20);
		panelRezervacija.add(tfEmail);

		btnGostPutnikUnosProfila = new JButton("Unesi");
		btnGostPutnikUnosProfila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tfEmail.getText().equals("") && !tfIme.getText().equals("") && !tfPrezime.getText().equals("")) {
					Profil p = new Profil();
					p.setE_mail(tfEmail.getText().toString());
					p.setIme(tfIme.getText().toString());
					p.setPrezime(tfPrezime.getText());
					p.setId_status(id_status_korisnika);
					upisiProfil(p);
					Profil prof = ucitajProfilZaEmail(tfEmail.getText().toString());
					tfIdProfila.setText(String.valueOf(prof.getId_profil()));
				} else {
					JOptionPane.showMessageDialog(null, "Popunite polja");
				}
			}
		});
		btnGostPutnikUnosProfila.setBounds(768, 140, 120, 20);
		panelRezervacija.add(btnGostPutnikUnosProfila);

		btnUcitaj = new JButton("Ucitaj");
		btnUcitaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tfEmail.getText().equals("")) {
					String email = tfEmail.getText().toString();
					Profil p = ucitajProfilZaEmail(email);
					String ime = p.getIme();
					String prezime = p.getPrezime();
					String email2213 = p.getE_mail();
					int id = p.getId_profil();
					double konto = p.getIstorija_kupovina();
					if (email2213 != null) {
						tfEmail.setText(email2213);
						tfIme.setText(ime);
						tfPrezime.setText(prezime);
						tfIdProfila.setText(String.valueOf(id));
						tfKontoProfila.setText(String.valueOf(konto));
					} else {
						JOptionPane.showMessageDialog(null, "Proverite mail ili registrujte putnika");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Popunite obavezno polje");
				}

			}
		});
		btnUcitaj.setBounds(768, 166, 120, 18);
		panelRezervacija.add(btnUcitaj);

		tfIdProfila = new JTextField();
		tfIdProfila.setEditable(false);
		tfIdProfila.setBounds(898, 141, 45, 18);
		panelRezervacija.add(tfIdProfila);
		tfIdProfila.setColumns(10);

		JButton btnRezervisi = new JButton("Rezervisi");
		btnRezervisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!tfIdProfila.getText().equals("") && !tfBrojKarata.getText().equals("")
						&& !tfKonacnaCena.getText().equals("")) {
					int brojKarata = odgovor.getBroj_karata();
					int kolicinaKarata = Integer.parseInt(tfBrojKarata.getText().toString());
					if ((brojKarata - kolicinaKarata) < 0) {
						JOptionPane.showMessageDialog(null, "Nema slobodnih mesta na tom letu");
					} else {
						Rezervacija rez = new Rezervacija();
						int idProfil = Integer.parseInt(tfIdProfila.getText().toString());
						int idLeta = odgovor.getId_leta();
						int idKompanije = avioKompanija.getId_aviokompanija();
						double cenaKarte = Double.parseDouble(tfKonacnaCena.getText().toString());
						rez.setId_profila(idProfil);
						rez.setId_leta(idLeta);
						rez.setBroj_karata(kolicinaKarata);
						rez.setId_aviokompanije(idKompanije);
						rez.setCena(cenaKarte);
						upisiRezervacijuKarte(rez);

						double konto = Double.parseDouble(tfKontoProfila.getText());
						double noviKonto = (konto + (cenaKarte * kolicinaKarata));

						Profil p = new Profil();
						p.setId_profil(idProfil);
						p.setIstorija_kupovina(noviKonto);
						izmeniIstorijuKupovinaNaProfilu(p);

						int preostaleKarte = odgovor.getBroj_karata();
						int noviBrojKarata = (preostaleKarte - kolicinaKarata);
						odgovor.setBroj_karata(noviBrojKarata);
						IzmeniCenuKlaseBrojKarata(odgovor);

						dtmKorpa.setRowCount(0);
						korpa.removeAll(korpa);
						ocistiFieldove();

					}
				} else {
					JOptionPane.showMessageDialog(null, "Popunite obavezna polja");
				}

			}
		});
		btnRezervisi.setBounds(723, 416, 109, 23);
		panelRezervacija.add(btnRezervisi);

		tfKontoProfila = new JTextField();
		tfKontoProfila.setEditable(false);
		tfKontoProfila.setBounds(898, 165, 45, 20);
		panelRezervacija.add(tfKontoProfila);
		tfKontoProfila.setColumns(10);
		Object[] koloneR = { "Id Rezervacije", "ID leta", "Broj Karata", "Cena" };
		dtmRezervacija.addColumn(koloneR[0]);
		dtmRezervacija.addColumn(koloneR[1]);
		dtmRezervacija.addColumn(koloneR[2]);
		dtmRezervacija.addColumn(koloneR[3]);

		Object[] kolone2 = { "Id Leta", "Oznaka Leta", "Datum", "Vreme" };
		dtmKorpa.addColumn(kolone2[0]);
		dtmKorpa.addColumn(kolone2[1]);
		dtmKorpa.addColumn(kolone2[2]);
		dtmKorpa.addColumn(kolone2[3]);

		Object[] kolone1 = { "idLeta", "Oznaka", "Iz", "Za", "idAviona", "Datum", "Vreme" };
		dtmLetoviDolazni.addColumn(kolone1[0]);
		dtmLetoviDolazni.addColumn(kolone1[1]);
		dtmLetoviDolazni.addColumn(kolone1[2]);
		dtmLetoviDolazni.addColumn(kolone1[3]);
		dtmLetoviDolazni.addColumn(kolone1[4]);
		dtmLetoviDolazni.addColumn(kolone1[5]);
		dtmLetoviDolazni.addColumn(kolone1[6]);

		Object[] kolone = { "idLeta", "Oznaka", "Iz", "Za", "idAviona", "Datum", "Vreme" };
		dtmLetovi.addColumn(kolone[0]);
		dtmLetovi.addColumn(kolone[1]);
		dtmLetovi.addColumn(kolone[2]);
		dtmLetovi.addColumn(kolone[3]);
		dtmLetovi.addColumn(kolone[4]);
		dtmLetovi.addColumn(kolone[5]);
		dtmLetovi.addColumn(kolone[6]);

		JLabel lblTekst = new JLabel("");
		lblTekst.setForeground(Color.WHITE);
		lblTekst.setFont(new Font("Snap ITC", Font.BOLD, 15));
		lblTekst.setBounds(33, 538, 491, 16);
		contentPane.add(lblTekst);
		lblTekst.setText(adresaKompanije.getNaziv_ulice_broj() + " " + adresaKompanije.getNaziv_grada() + " "
				+ adresaKompanije.getNaziv_drzave() + " " + "PIB : " + avioKompanija.getPib());

		JLabel lblImeKompanije = new JLabel("");
		lblImeKompanije.setHorizontalAlignment(SwingConstants.CENTER);
		lblImeKompanije.setForeground(new Color(0, 0, 0));
		lblImeKompanije.setFont(new Font("Snap ITC", Font.BOLD, 29));
		lblImeKompanije.setBounds(279, 11, 463, 29);
		contentPane.add(lblImeKompanije);
		lblImeKompanije.setText(avioKompanija.getNaziv().toString());

		JLabel lblInfoRadnika = new JLabel("");
		lblInfoRadnika.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInfoRadnika.setForeground(Color.WHITE);
		lblInfoRadnika.setFont(new Font("Snap ITC", Font.BOLD, 15));
		lblInfoRadnika.setBounds(483, 538, 491, 16);
		contentPane.add(lblInfoRadnika);
		lblInfoRadnika
				.setText(profilRadnika.getIme() + " " + profilRadnika.getPrezime() + " " + profilRadnika.getE_mail());

		lblDanasnjiDatum = new JLabel("");
		lblDanasnjiDatum.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanasnjiDatum.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDanasnjiDatum.setBounds(872, 11, 102, 29);
		contentPane.add(lblDanasnjiDatum);
		Date danasnjiDatum = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datum = sdf.format(danasnjiDatum);
		lblDanasnjiDatum.setText(datum);

		JLabel lblPozadina = new JLabel("");
		lblPozadina.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPozadina.setBounds(0, 0, 994, 586);
		contentPane.add(lblPozadina);
		lblPozadina.setIcon(new ImageIcon(slika1));
		
		List<Let>listZaLetoveByvreme = ucitajListuLetovaByDatum(avioKompanija.getId_aviokompanija(), lblDanasnjiDatum.getText().toString());
		postaviPodatkeUTabeluLetoviByVreme(listZaLetoveByvreme);

	}

	protected void obrisiPrivremeniProfil(Profil p) {
		try {
			TransferKlasa tf =KontrolerKI.getInstanca().obrisiProfil(p);
			tf.getServer_odgovor_poruka();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void realizujRezervaciju(Rezervacija r) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().obrisiRezervaciju(r);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka().toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected List<Rezervacija> vratiListuRezervacijaZaIDLeta(int idLeta) {
		List<Rezervacija> list = new ArrayList<>();
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().ucitajRezervacijeZaIdLeta(idLeta);
			list = (List<Rezervacija>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	protected void IzmeniCenuKlaseBrojKarata(CenaKlasa odgovor2) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().izmeniCeneKarataBrojMesta(odgovor2);
			tf.getServer_odgovor_poruka();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void popuniTabeluRezervacije(List<Rezervacija> listaRezervacija) {
		dtmRezervacija.setRowCount(0);
		for (Rezervacija r : listaRezervacija) {
			Object[] red = { r.getId_rezervacije(), r.getId_leta(), r.getBroj_karata(), r.getCena() };
			dtmRezervacija.addRow(red);
		}
	}

	protected List<Rezervacija> ucitajRezervacijeZaIdProfila(int id) {
		List<Rezervacija> list = new ArrayList<>();
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().ucitajRezervacijeZaIdProfila(id);
			list = (List<Rezervacija>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	protected void ocistiFieldove() {
		dtmLetovi.setRowCount(0);
		dtmLetoviDolazni.setRowCount(0);
		tfEmail.setText("");
		tfIdProfila.setText("");
		tfIme.setText("");
		tfPrezime.setText("");
		tfKonacnaCena.setText("");
		tfKontoProfila.setText("");
		tfBrojKarata.setText("");
		

	}

	protected void izmeniIstorijuKupovinaNaProfilu(Profil p) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().izmeniKontoNaProfilu(p);
			tf.getServer_odgovor_poruka();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void upisiRezervacijuKarte(Rezervacija rez) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().upisiRezervaciju(rez);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka().toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Profil ucitajProfilZaEmail(String email) {
		Profil profil = null;
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajProfilZaEmail(email);
			profil = (Profil) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profil;
	}

	protected void upisiProfil(Profil p) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().registracijaProfila(p);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void izracunajCenu(CenaKlasa odgovor, String datum) {
		double cenaEkonomske;
		double cenaBiznis;
		double cenaAKlase;

		double index1 = odgovor.getDan30();
		double index2 = odgovor.getDan15();
		double index3 = odgovor.getDan2();

		String danasnjiDatum = lblDanasnjiDatum.getText().toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int razlikaDana = 0;
		try {
			Date datumLeta = sdf.parse(datum);
			Date datumDanas = sdf.parse(danasnjiDatum);
			long daniIzmedju = datumLeta.getTime() - datumDanas.getTime();
			razlikaDana = (int) TimeUnit.DAYS.convert(daniIzmedju, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (razlikaDana >= 30) {
			if (radioBtnEkonomska.isSelected()) {
				cenaEkonomske = odgovor.getEkonomska() * index1;

				if (chekBoxDeca.isSelected()) {
					cenaEkonomske = odgovor.getEkonomska() * odgovor.getDeca() * index1;
				}
				tfKonacnaCena.setText(String.valueOf(cenaEkonomske));

			} else if (radioBtnBiznis.isSelected()) {
				cenaBiznis = odgovor.getBiznis() * index1;
				if (chekBoxDeca.isSelected()) {
					cenaBiznis = odgovor.getBiznis() * odgovor.getDeca() * index1;
				}
				tfKonacnaCena.setText(String.valueOf(cenaBiznis));

			} else if (radioBtnAKlasa.isSelected()) {
				cenaAKlase = odgovor.getA_klasa() * index1;
				if (chekBoxDeca.isSelected()) {
					cenaAKlase = odgovor.getA_klasa() * odgovor.getDeca() * index1;
				}
				tfKonacnaCena.setText(String.valueOf(cenaAKlase));
			}
		} else if (razlikaDana < 30 && razlikaDana >= 15) {
			if (radioBtnEkonomska.isSelected()) {
				cenaEkonomske = odgovor.getEkonomska() * index2;

				if (chekBoxDeca.isSelected()) {
					cenaEkonomske = odgovor.getEkonomska() * odgovor.getDeca() * index2;
				}
				tfKonacnaCena.setText(String.valueOf(cenaEkonomske));

			} else if (radioBtnBiznis.isSelected()) {
				cenaBiznis = odgovor.getBiznis() * index2;
				if (chekBoxDeca.isSelected()) {
					cenaBiznis = odgovor.getBiznis() * odgovor.getDeca() * index2;
				}
				tfKonacnaCena.setText(String.valueOf(cenaBiznis));

			} else if (radioBtnAKlasa.isSelected()) {
				cenaAKlase = odgovor.getA_klasa() * index2;
				if (chekBoxDeca.isSelected()) {
					cenaAKlase = odgovor.getA_klasa() * odgovor.getDeca() * index2;
				}
				tfKonacnaCena.setText(String.valueOf(cenaAKlase));
			}
		} else if (razlikaDana < 15 && razlikaDana >= 2) {
			if (radioBtnEkonomska.isSelected()) {
				cenaEkonomske = odgovor.getEkonomska() * 1;

				if (chekBoxDeca.isSelected()) {
					cenaEkonomske = odgovor.getEkonomska() * odgovor.getDeca() * 1;
				}
				tfKonacnaCena.setText(String.valueOf(cenaEkonomske));

			} else if (radioBtnBiznis.isSelected()) {
				cenaBiznis = odgovor.getBiznis() * 1;
				if (chekBoxDeca.isSelected()) {
					cenaBiznis = odgovor.getBiznis() * odgovor.getDeca() * 1;
				}
				tfKonacnaCena.setText(String.valueOf(cenaBiznis));

			} else if (radioBtnAKlasa.isSelected()) {
				cenaAKlase = odgovor.getA_klasa() * 1;
				if (chekBoxDeca.isSelected()) {
					cenaAKlase = odgovor.getA_klasa() * odgovor.getDeca() * 1;
				}
				tfKonacnaCena.setText(String.valueOf(cenaAKlase));
			}
		} else if (razlikaDana <= 2) {
			if (radioBtnEkonomska.isSelected()) {
				cenaEkonomske = odgovor.getEkonomska() * index3;

				if (chekBoxDeca.isSelected()) {
					cenaEkonomske = odgovor.getEkonomska() * odgovor.getDeca() * index3;
				}
				tfKonacnaCena.setText(String.valueOf(cenaEkonomske));

			} else if (radioBtnBiznis.isSelected()) {
				cenaBiznis = odgovor.getBiznis() * index3;
				if (chekBoxDeca.isSelected()) {
					cenaBiznis = odgovor.getBiznis() * odgovor.getDeca() * index3;
				}
				tfKonacnaCena.setText(String.valueOf(cenaBiznis));

			} else if (radioBtnAKlasa.isSelected()) {
				cenaAKlase = odgovor.getA_klasa() * index3;
				if (chekBoxDeca.isSelected()) {
					cenaAKlase = odgovor.getA_klasa() * odgovor.getDeca() * index3;
				}
				tfKonacnaCena.setText(String.valueOf(cenaAKlase));
			}
		}

	}

	protected CenaKlasa ucitajCenuKlasaZaLet(CenaKlasa ck) {
		CenaKlasa cenaKlasa = null;
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().vratiCenuKlasaZaIzabraniLet(ck);
			cenaKlasa = (CenaKlasa) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cenaKlasa;
	}

	protected void postaviPodatkeUTabeluOdlazniLetovi(List<Let> letovi) {
		dtmLetovi.setRowCount(0);
		for (Let l : letovi) {
			Object[] redovi = { l.getId_leta(), l.getOznaka(), vratiNazivAerodromaZaId(l.getId_aerodroma_iz()),
					vratiNazivAerodromaZaId(l.getId_aerodroma_za()), l.getId_aviona(), l.getDatum(), l.getVreme(),
					l.getId_aviokompanije() };
			dtmLetovi.addRow(redovi);
		}

	}

	protected void postaviPodatkeUTabeluDolazniLetovi(List<Let> letovi) {
		dtmLetoviDolazni.setRowCount(0);
		for (Let l : letovi) {
			Object[] redovi = { l.getId_leta(), l.getOznaka(), vratiNazivAerodromaZaId(l.getId_aerodroma_iz()),
					vratiNazivAerodromaZaId(l.getId_aerodroma_za()), l.getId_aviona(), l.getDatum(), l.getVreme(),
					l.getId_aviokompanije() };
			dtmLetoviDolazni.addRow(redovi);
		}
		
	}
		protected void postaviPodatkeUTabeluLetoviByVreme(List<Let> letovi) {
			dtmLetoviByVreme.setRowCount(0);
			for (Let l : letovi) {
				Object[] redovi = { l.getId_leta(), l.getOznaka(), vratiNazivAerodromaZaId(l.getId_aerodroma_iz()),
						vratiNazivAerodromaZaId(l.getId_aerodroma_za()), l.getDatum(), l.getVreme(),
						l.getId_aviokompanije() };
				dtmLetoviByVreme.addRow(redovi);
			}

	}

	private Object vratiNazivAerodromaZaId(int id) {
		String naziv = null;
		for (Aerodrom a : listaAerodroma) {
			if (id == a.getId_aerodroma()) {
				naziv = a.getNaziv();
			}
		}
		return naziv;
	}

	protected List<Let> vratiListuLetovaKompanije(Let l) {
		List<Let> list = new ArrayList<>();
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().vratiListuLetovaKompanije(l);
			list = (List<Let>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	protected List<Let> vratiListuPovratnihLetovaKompanije(Let let) {
		List<Let> list = new ArrayList<>();
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().vratiListuPovratnihLetovaKompanije(let);
			list = (List<Let>) tf.getServer_odgovor();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	private List<Aerodrom> ucitajAerodrome() {
		List<Aerodrom> list = new ArrayList<>();
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajListuAerodroma();
			list = (List<Aerodrom>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	private List<AvioKompanija> ucitajAvioKompanije() {
		List<AvioKompanija> list = null;
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().ucitajListuAvioKompanija();
			list = (List<AvioKompanija>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private AvioKompanija postaviPodatkeAvioKompanije(String nazivAvioKompanijeRadnika) {
		AvioKompanija avio = new AvioKompanija();
		for (AvioKompanija a : listaAvioKompanije) {
			if (nazivAvioKompanijeRadnika.equals(a.getNaziv())) {
				avio.setAdresa(a.getAdresa());
				avio.setId_aviokompanija(a.getId_aviokompanija());
				avio.setNaziv(a.getNaziv());
				avio.setPib(a.getPib());
			}
		}
		return avio;

	}

	private String vratiKompanijuRadnika(int id_profil) {
		String naziv = null;
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().vratiNazivAvioKompanije(id_profil);
			naziv = (String) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return naziv;
	}

	public Adresa ucitajAdresuZaId(int id) {
		Adresa adresa = new Adresa();
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajAdresuZaId(id);
			adresa = (Adresa) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adresa;
	}

	private Profil ucitajProfilZaId(int id_profil) {
		Profil profil = new Profil();
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajProfilZaId(id_profil);
			profil = (Profil) tf.getServer_odgovor();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return profil;
	}

	public void promenaPanela(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();

	}

	private void postaviAerodromeUComboBox(JComboBox combo) {
		combo.addItem("----------------");
		for (Aerodrom a : listaAerodroma) {
			combo.addItem(a.getNaziv());
		}
	}

	private void postaviPodatkeUtabeluKorpa() {
		dtmKorpa.setRowCount(0);
		for (Let l : korpa) {
			Object[] red = { l.getId_leta(), l.getOznaka(), l.getDatum(), l.getVreme() };
			dtmKorpa.addRow(red);
		}
	}
	
	private List<Let> ucitajListuLetovaByDatum(int id,String datum){
		List<Let>list=new ArrayList<>();
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().vratiListuLetovaByDatum(id,datum);
			list = (List<Let>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
}
