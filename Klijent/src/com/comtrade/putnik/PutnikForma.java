package com.comtrade.putnik;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.comtrade.domen.Aerodrom;
import com.comtrade.domen.AvioKompanija;
import com.comtrade.domen.Avion;
import com.comtrade.domen.CenaKlasa;
import com.comtrade.domen.Let;
import com.comtrade.domen.Profil;
import com.comtrade.domen.Rezervacija;
import com.comtrade.kontroler.korisnickoginterfejsa.KontrolerKI;
import com.comtrade.transfer.TransferKlasa;
import com.comtrade.view.panel.PanelSlika;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PutnikForma extends JFrame {

	private JPanel contentPane;
	private List<Profil> listaProfila = new ArrayList<>();
	private List<Aerodrom> listaAerodroma = new ArrayList<>();
	private List<AvioKompanija> listaAvioKompanija = new ArrayList<>();
	private List<Let> korpa = new ArrayList<>();
	private List<Avion> listaAviona = new ArrayList<>();
	private JLabel lblTrenutniKorisnik;
	private JTextField tfKonto;
	private JLayeredPane layeredPane;
	private JComboBox cboxPolazak;
	private JComboBox cboxDolazak;
	private int id_aerodrom_polazni = 0;
	private int id_aerodrom_dolazni = 0;
	private int razlikaDana;
	private JDateChooser dateChooserDatumPolazak;
	private JDateChooser dateChooserDatumDolazak;
	private JTable tabelaLetovaSaPolaznogAerodroma;
	private JTable tabelaLetovaSaDolaznogAerodroma;
	private DefaultTableModel dtmLetovaSaPolaznogAerodroma = new DefaultTableModel();
	private DefaultTableModel dtmLetovaSaDolaznogAerodroma = new DefaultTableModel();
	private DefaultTableModel dtmKorpa = new DefaultTableModel();
	private JButton btnPretragaLeta;
	private JCheckBox checkBoxPresedanje;
	private PanelSlika panel1;
	private PanelSlika panel;
	private JTable tabelaKorpa;
	private ButtonGroup bg = new ButtonGroup();
	private JTextField tfBrojKarata;
	private JTextField tfKonacnaCena;
	private JRadioButton radioBtnEkonomska;
	private JRadioButton radioBtnBiznis;
	private JRadioButton radioBtnAKlasa;
	private JCheckBox chekBoxDeca;
	private JLabel lblDanasnjiDatum;
	private JTextField tfIDLETA;
	private JTextField tfIDAVIOKOMPANIJE;
	private JTextField tfBROJMESTA;
	private JLabel lblBrKarata;
	private JLabel lblText;
	private CenaKlasa odgovor;

	public PutnikForma(int id_profil) {
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(635, 250, 782, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		vratiListuProfila();

		lblTrenutniKorisnik = new JLabel("");
		lblTrenutniKorisnik.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTrenutniKorisnik.setFont(new Font("Times New Roman", lblTrenutniKorisnik.getFont().getStyle() & ~Font.BOLD,
				lblTrenutniKorisnik.getFont().getSize() + 4));
		lblTrenutniKorisnik.setBounds(606, 11, 170, 26);
		contentPane.add(lblTrenutniKorisnik);

		tfKonto = new JTextField();
		tfKonto.setEditable(false);
		tfKonto.setBounds(680, 503, 86, 20);
		contentPane.add(tfKonto);
		tfKonto.setColumns(10);

		for (Profil p : listaProfila) {
			if (id_profil == p.getId_profil()) {
				String ime = p.getIme();
				String prezime = p.getPrezime();
				double konto = p.getIstorija_kupovina();
				lblTrenutniKorisnik.setText(ime + " " + " " + prezime + " ");
				tfKonto.setText(String.valueOf(konto));
			}
		}
		Image slika1 = new ImageIcon(this.getClass().getResource("/slika10.jpg")).getImage();

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(27, 65, 739, 395);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
		panel = new PanelSlika();
		panel.setBounds(0, 0, 739, 400);
		layeredPane.add(panel);
		panel.setVisible(true);
		panel.setLayout(null);

		cboxPolazak = new JComboBox();
		cboxPolazak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String naziv = cboxPolazak.getSelectedItem().toString();
				for (Aerodrom a : listaAerodroma) {
					if (naziv.equals(a.getNaziv())) {
						id_aerodrom_polazni = a.getId_aerodroma();
						
					}
				}
			}
		});
		cboxPolazak.setBounds(39, 11, 141, 20);
		panel.add(cboxPolazak);

		cboxDolazak = new JComboBox();
		cboxDolazak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv = cboxDolazak.getSelectedItem().toString();
				for (Aerodrom a : listaAerodroma) {
					if (naziv.equals(a.getNaziv())) {
						id_aerodrom_dolazni = a.getId_aerodroma();
						
					}
				}
			}
		});
		cboxDolazak.setBounds(206, 11, 141, 20);
		panel.add(cboxDolazak);

		dateChooserDatumPolazak = new JDateChooser();
		dateChooserDatumPolazak.setBounds(385, 11, 116, 20);
		panel.add(dateChooserDatumPolazak);

		dateChooserDatumDolazak = new JDateChooser();
		dateChooserDatumDolazak.setBounds(511, 11, 116, 20);
		panel.add(dateChooserDatumDolazak);

		tabelaLetovaSaPolaznogAerodroma = new JTable(dtmLetovaSaPolaznogAerodroma);
		JScrollPane scrollPane = new JScrollPane(tabelaLetovaSaPolaznogAerodroma);
		tabelaLetovaSaPolaznogAerodroma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int red = tabelaLetovaSaPolaznogAerodroma.getSelectedRow();
				int id = Integer.parseInt(tabelaLetovaSaPolaznogAerodroma.getValueAt(red, 0).toString());
				String oznaka = tabelaLetovaSaPolaznogAerodroma.getValueAt(red, 1).toString();
				int id_iz = postaviIdAerodroma(tabelaLetovaSaPolaznogAerodroma.getValueAt(red, 2).toString());
				int id_za = postaviIdAerodroma(tabelaLetovaSaPolaznogAerodroma.getValueAt(red, 3).toString());
				String datum = tabelaLetovaSaPolaznogAerodroma.getValueAt(red, 4).toString();
				String vreme = tabelaLetovaSaPolaznogAerodroma.getValueAt(red, 5).toString();
				String nazivAvioKompanije = tabelaLetovaSaPolaznogAerodroma.getValueAt(red, 6).toString();
				int id_avioKompanije = vratiIdAviokompanijeZaNaziv(nazivAvioKompanije);
				int id_aviona = vratiIdAvionaZaIdAvioKompanije(id_avioKompanije);
				Let let = new Let();
				let.setDatum(datum);
				let.setId_aerodroma_iz(id_iz);
				let.setId_aerodroma_za(id_za);
				let.setId_leta(id);
				let.setOznaka(oznaka);
				let.setDatum(datum);
				let.setVreme(vreme);
				let.setId_aviona(id_aviona);
				int opcije = JOptionPane.showConfirmDialog(null, "Dodaj let u korpu?", "Korpa",
						JOptionPane.YES_NO_OPTION);
				if (opcije == 0) {
					korpa.add(let);
					postaviPodatkeUtabeluKorpa();
				} else {

				}

			}

		});
		scrollPane.setBounds(13, 62, 716, 123);
		panel.add(scrollPane);
		scrollPane.setViewportView(tabelaLetovaSaPolaznogAerodroma);

		tabelaLetovaSaDolaznogAerodroma = new JTable(dtmLetovaSaDolaznogAerodroma);
		JScrollPane scrollPane_1 = new JScrollPane(tabelaLetovaSaDolaznogAerodroma);
		tabelaLetovaSaDolaznogAerodroma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int red = tabelaLetovaSaDolaznogAerodroma.getSelectedRow();
				int id = Integer.parseInt(tabelaLetovaSaDolaznogAerodroma.getValueAt(red, 0).toString());
				String oznaka = tabelaLetovaSaDolaznogAerodroma.getValueAt(red, 1).toString();
				int id_iz = postaviIdAerodroma(tabelaLetovaSaDolaznogAerodroma.getValueAt(red, 2).toString());
				int id_za = postaviIdAerodroma(tabelaLetovaSaDolaznogAerodroma.getValueAt(red, 3).toString());
				String datum = tabelaLetovaSaDolaznogAerodroma.getValueAt(red, 4).toString();
				String vreme = tabelaLetovaSaDolaznogAerodroma.getValueAt(red, 5).toString();
				String nazivAvioKompanije = tabelaLetovaSaDolaznogAerodroma.getValueAt(red, 6).toString();
				int id_avioKompanije = vratiIdAviokompanijeZaNaziv(nazivAvioKompanije);
				int id_aviona = vratiIdAvionaZaIdAvioKompanije(id_avioKompanije);
				Let let = new Let();
				let.setDatum(datum);
				let.setId_aerodroma_iz(id_iz);
				let.setId_aerodroma_za(id_za);
				let.setId_leta(id);
				let.setOznaka(oznaka);
				let.setDatum(datum);
				let.setVreme(vreme);
				let.setId_aviona(id_aviona);
				int opcije = JOptionPane.showConfirmDialog(null, "Dodaj let u korpu?", "Korpa",
						JOptionPane.YES_NO_OPTION);
				if (opcije == 0) {
					korpa.add(let);
					postaviPodatkeUtabeluKorpa();

				} else {

				}

			}
		});
		scrollPane_1.setBounds(10, 222, 719, 123);
		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(tabelaLetovaSaDolaznogAerodroma);

		btnPretragaLeta = new JButton("Pretraga");
		btnPretragaLeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkBoxPresedanje.isSelected()) {

				} else {
					if (dateChooserDatumDolazak.getDate() != null && dateChooserDatumPolazak.getDate() != null) {
						List<Let> listPolazak = new ArrayList<>();
						List<Let> listaDolaznih = new ArrayList<>();
						Let letPolazak = new Let();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String datum = sdf.format(dateChooserDatumPolazak.getDate());
						int id = id_aerodrom_polazni;
						int id2 = id_aerodrom_dolazni;
						letPolazak.setDatum(datum);
						letPolazak.setId_aerodroma_iz(id);
						letPolazak.setId_aerodroma_za(id2);

						Let letDolazak = new Let();
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
						String datum1 = sdf1.format(dateChooserDatumDolazak.getDate());
						letDolazak.setDatum(datum1);
						letDolazak.setId_aerodroma_iz(id_aerodrom_dolazni);
						letDolazak.setId_aerodroma_za(id_aerodrom_polazni);
						listPolazak = ucitajListuIzabranogLeta(letPolazak);
						if (listPolazak.size() == 0) {
							JOptionPane.showMessageDialog(null, "Nema letova za tu destinaciju");
							/*
							 * int opcije = JOptionPane.showConfirmDialog(null,
							 * "Presedanje", "Ok", JOptionPane.YES_NO_OPTION);
							 * if(opcije == JOptionPane.YES_OPTION){
							 * System.out.println("ok");
							 * 
							 * }else{ System.out.println("no"); }
							 */ }
						listaDolaznih = ucitajListuIzabranogLeta(letDolazak);
						if (listaDolaznih.size() == 0) {
							JOptionPane.showMessageDialog(null, "Nema povratnih letova za tu destinaciju");
						}
						postaviPodatkeUtabeluPolzni(listPolazak);
						postaviPodatkeUtabeluDolazak(listaDolaznih);
					} else {
						JOptionPane.showMessageDialog(null, "Izaberite datume trazenog leta");
					}
				}

			}
		});
		btnPretragaLeta.setBounds(637, 10, 89, 23);
		panel.add(btnPretragaLeta);

		checkBoxPresedanje = new JCheckBox("Sa presedanjem");
		checkBoxPresedanje.setBounds(13, 192, 122, 23);
		panel.add(checkBoxPresedanje);

		panel1 = new PanelSlika();
		panel1.setBounds(0, 0, 739, 400);
		layeredPane.add(panel1);
		panel1.setLayout(null);

		tabelaKorpa = new JTable(dtmKorpa);
		tabelaKorpa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red = tabelaKorpa.getSelectedRow();
				int id_leta = Integer.parseInt(tabelaKorpa.getValueAt(red, 2).toString());
				int id_komp = Integer.parseInt(tabelaKorpa.getValueAt(red, 3).toString());
				CenaKlasa ck = new CenaKlasa();
				ck.setId_leta(id_leta);
				ck.setId_aviokompanije(id_komp);
				odgovor = vratiCenuKlasaZaIzabraniLet(ck);

				tfIDLETA.setText(String.valueOf(odgovor.getId_leta()));
				tfIDAVIOKOMPANIJE.setText(String.valueOf(odgovor.getId_aviokompanije()));
				tfBROJMESTA.setText(String.valueOf(odgovor.getBroj_karata()));

				String datumLeta = tabelaKorpa.getValueAt(red, 4).toString();
				izracunajCene(odgovor, datumLeta);

			}
		});
		JScrollPane scrollPaneKorpa = new JScrollPane(tabelaKorpa);
		scrollPaneKorpa.setBounds(141, 166, 424, 114);
		panel1.add(scrollPaneKorpa);
		scrollPaneKorpa.setViewportView(tabelaKorpa);

		chekBoxDeca = new JCheckBox("Deca");
		chekBoxDeca.setBounds(299, 137, 89, 23);
		panel1.add(chekBoxDeca);

		tfBrojKarata = new JTextField();
		tfBrojKarata.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c < '0' || c > '9') {
					e.consume();
				}
			}
		});
		tfBrojKarata.setColumns(10);
		tfBrojKarata.setBounds(526, 291, 39, 26);
		panel1.add(tfBrojKarata);

		tfKonacnaCena = new JTextField();
		tfKonacnaCena.setEditable(false);
		tfKonacnaCena.setFont(new Font("Tahoma", Font.BOLD, 15));
		tfKonacnaCena.setBounds(141, 291, 85, 28);
		panel1.add(tfKonacnaCena);
		tfKonacnaCena.setColumns(10);

		radioBtnEkonomska = new JRadioButton("Ekonomska");
		radioBtnEkonomska.setSelected(true);
		radioBtnEkonomska.setBounds(208, 86, 89, 23);
		panel1.add(radioBtnEkonomska);
		bg.add(radioBtnEkonomska);

		radioBtnBiznis = new JRadioButton("Biznis");
		radioBtnBiznis.setBounds(299, 86, 89, 23);
		panel1.add(radioBtnBiznis);
		bg.add(radioBtnBiznis);

		radioBtnAKlasa = new JRadioButton("A klasa");
		radioBtnAKlasa.setBounds(390, 86, 89, 23);
		panel1.add(radioBtnAKlasa);
		bg.add(radioBtnAKlasa);

		JButton btnRezervacija = new JButton("Rezervacija");
		btnRezervacija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!tfIDLETA.getText().equals("") && !tfIDAVIOKOMPANIJE.getText().equals("")
						&& !tfKonacnaCena.getText().equals("") && !tfBrojKarata.getText().equals("")) {
						if(odgovor.getBroj_karata()==0){
							JOptionPane.showMessageDialog(null, "Nema karata za taj let");
						}else{
							Rezervacija r = new Rezervacija();
							int brojKarata = Integer.parseInt(tfBrojKarata.getText().toString());
							int id_leta = Integer.parseInt(tfIDLETA.getText().toString());
							int id_avioko = Integer.parseInt(tfIDAVIOKOMPANIJE.getText().toString());
							double cena = Double.parseDouble(tfKonacnaCena.getText().toString());
							r.setBroj_karata(brojKarata);
							r.setId_leta(id_leta);
							r.setId_profila(id_profil);
							r.setId_aviokompanije(id_avioko);
							r.setCena(cena);
							upisiRezervacijuKarte(r);
				
							int brojSlobodnihMesta =Integer.parseInt(tfBROJMESTA.getText().toString());
							int preostaliBrojKarata = (brojSlobodnihMesta-brojKarata);
							odgovor.setBroj_karata(preostaliBrojKarata);
							IzmeniCenuKlaseBrojKarata(odgovor);
							
							double konto =Double.parseDouble(tfKonto.getText().toString());
							double noviKonto = (konto+(cena*brojKarata));
							Profil p = new Profil();
							p.setId_profil(id_profil);
							p.setIstorija_kupovina(noviKonto);
							izmeniIstorijuKupovinaNaProfilu(p);
				
							dtmKorpa.setRowCount(0);
							korpa.removeAll(korpa);
							tfKonto.setText(String.valueOf(noviKonto));
							System.out.println(preostaliBrojKarata);
						}
							} else {
								JOptionPane.showMessageDialog(null, "Izaberite kartu iz tabele i njene parametre");
					}
				
			}
		});
		btnRezervacija.setBounds(299, 337, 108, 23);
		panel1.add(btnRezervacija);

		Date danasnjiDatum = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datum = sdf.format(danasnjiDatum);
		lblDanasnjiDatum = new JLabel(datum);
		lblDanasnjiDatum.setForeground(Color.WHITE);
		lblDanasnjiDatum.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDanasnjiDatum.setBounds(10, 11, 124, 23);
		panel1.add(lblDanasnjiDatum);

		tfIDLETA = new JTextField();
		tfIDLETA.setEditable(false);
		tfIDLETA.setBounds(721, 366, 8, 23);
		panel1.add(tfIDLETA);
		tfIDLETA.setColumns(10);

		tfIDAVIOKOMPANIJE = new JTextField();
		tfIDAVIOKOMPANIJE.setEditable(false);
		tfIDAVIOKOMPANIJE.setColumns(10);
		tfIDAVIOKOMPANIJE.setBounds(685, 366, 8, 23);
		panel1.add(tfIDAVIOKOMPANIJE);

		lblBrKarata = new JLabel("Broj Karata :");
		lblBrKarata.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBrKarata.setBounds(416, 291, 100, 25);
		panel1.add(lblBrKarata);

		lblText = new JLabel("Klikom na tabelu proverite sve opcije cene karte za let");
		lblText.setForeground(Color.BLACK);
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblText.setBounds(141, 116, 424, 14);
		panel1.add(lblText);

		tfBROJMESTA = new JTextField();
		tfBROJMESTA.setEditable(false);
		tfBROJMESTA.setColumns(10);
		tfBROJMESTA.setBounds(703, 366, 8, 23);
		panel1.add(tfBROJMESTA);

		panel1.setVisible(true);

		Object[] kolone2 = { "Oznaka Leta", "Aviokompanija", "Id Leta", "Id Aviokompanije", "Datum" };
		dtmKorpa.addColumn(kolone2[0]);
		dtmKorpa.addColumn(kolone2[1]);
		dtmKorpa.addColumn(kolone2[2]);
		dtmKorpa.addColumn(kolone2[3]);
		dtmKorpa.addColumn(kolone2[4]);

		Object[] kolone0 = { "Id", "Oznaka", "Od", "Do", "Datum", "Vreme", "Aviokompanjia" };
		dtmLetovaSaPolaznogAerodroma.addColumn(kolone0[0]);
		dtmLetovaSaPolaznogAerodroma.addColumn(kolone0[1]);
		dtmLetovaSaPolaznogAerodroma.addColumn(kolone0[2]);
		dtmLetovaSaPolaznogAerodroma.addColumn(kolone0[3]);
		dtmLetovaSaPolaznogAerodroma.addColumn(kolone0[4]);
		dtmLetovaSaPolaznogAerodroma.addColumn(kolone0[5]);
		dtmLetovaSaPolaznogAerodroma.addColumn(kolone0[6]);

		Object[] kolone = { "Id", "Oznaka", "Od", "Do", "Datum", "Vreme", "Aviokompanija" };
		dtmLetovaSaDolaznogAerodroma.addColumn(kolone[0]);
		dtmLetovaSaDolaznogAerodroma.addColumn(kolone[1]);
		dtmLetovaSaDolaznogAerodroma.addColumn(kolone[2]);
		dtmLetovaSaDolaznogAerodroma.addColumn(kolone[3]);
		dtmLetovaSaDolaznogAerodroma.addColumn(kolone[4]);
		dtmLetovaSaDolaznogAerodroma.addColumn(kolone[5]);
		dtmLetovaSaDolaznogAerodroma.addColumn(kolone0[6]);

		JButton btnNewButton = new JButton("Pretraga letova");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				promenaPanela(panel);
			}
		});
		btnNewButton.setBounds(27, 31, 123, 23);
		contentPane.add(btnNewButton);

		JButton btnPregledCenaIzabranih = new JButton("Pregled cena izabranih letova");
		btnPregledCenaIzabranih.setBounds(160, 31, 213, 23);
		contentPane.add(btnPregledCenaIzabranih);

		JLabel lblSlikaPozadina = new JLabel("");
		lblSlikaPozadina.setBounds(0, 0, 776, 534);
		contentPane.add(lblSlikaPozadina);
		lblSlikaPozadina.setIcon(new ImageIcon(slika1));
		btnPregledCenaIzabranih.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				promenaPanela(panel1);
				
			}
		});

		listaAerodroma = ucitajListuAerodroma();
		postaviAerodromeUCbox();
		listaAvioKompanija = ucitajListuAviokompanija();
		listaAviona = ucitajListuAviona();

	}


	protected void izmeniIstorijuKupovinaNaProfilu(Profil p) {
			try {
				TransferKlasa tf =  KontrolerKI.getInstanca().izmeniKontoNaProfilu(p);
				tf.getServer_odgovor_poruka();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}


	protected void IzmeniCenuKlaseBrojKarata(CenaKlasa odgovor) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().izmeniCeneKarataBrojMesta(odgovor);
			tf.getServer_odgovor_poruka();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void upisiRezervacijuKarte(Rezervacija r) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().upisiRezervaciju(r);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka().toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void izracunajCene(CenaKlasa odgovor, String datum) {
		double cenaEkonomske;
		double cenaBiznis;
		double cenaAKlase;

		double index1 = odgovor.getDan30();
		double index2 = odgovor.getDan15();
		double index3 = odgovor.getDan2();

		String danasnjiDatum = lblDanasnjiDatum.getText().toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

	private CenaKlasa vratiCenuKlasaZaIzabraniLet(CenaKlasa ck) {
		TransferKlasa tf;
		CenaKlasa cena = null;
		try {
			tf = KontrolerKI.getInstanca().vratiCenuKlasaZaIzabraniLet(ck);
			cena = (CenaKlasa) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cena;
	}

	private List<Avion> ucitajListuAviona() {
		List<Avion> list = new ArrayList<>();
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().ucitajListuAviona();
			list = (List<Avion>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private List<AvioKompanija> ucitajListuAviokompanija() {
		List<AvioKompanija> list = new ArrayList<>();
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

	private void postaviPodatkeUtabeluDolazak(List<Let> listaDolaznih) {
		dtmLetovaSaDolaznogAerodroma.setRowCount(0);
		for (Let l : listaDolaznih) {
			Object[] red = { l.getId_leta(), l.getOznaka(), postaviNazivAerodroma(l.getId_aerodroma_iz()),
					postaviNazivAerodroma(l.getId_aerodroma_za()), l.getDatum(), l.getVreme(),
					vratiNazivAviokompanijeZaId(vratiIdAvioKompanijeZaIdAviona(l.getId_aviona())) };
			dtmLetovaSaDolaznogAerodroma.addRow(red);
		}

	}

	private void postaviPodatkeUtabeluPolzni(List<Let> list) {
		dtmLetovaSaPolaznogAerodroma.setRowCount(0);
		for (Let l : list) {
			Object[] red = { l.getId_leta(), l.getOznaka(), postaviNazivAerodroma(l.getId_aerodroma_iz()),
					postaviNazivAerodroma(l.getId_aerodroma_za()), l.getDatum(), l.getVreme(),
					vratiNazivAviokompanijeZaId(vratiIdAvioKompanijeZaIdAviona(l.getId_aviona())) };
			dtmLetovaSaPolaznogAerodroma.addRow(red);
		}

	}

	private String vratiNazivAviokompanijeZaId(int id_kompanije) {
		String naziv = null;
		for (AvioKompanija ak : listaAvioKompanija) {
			if (id_kompanije == ak.getId_aviokompanija()) {
				naziv = ak.getNaziv();
			}
		}
		return naziv;
	}

	private int vratiIdAviokompanijeZaNaziv(String naziv) {
		int id = 0;
		for (AvioKompanija a : listaAvioKompanija) {
			if (naziv.equals(a.getNaziv())) {
				id = a.getId_aviokompanija();
			}
		}
		return id;

	}

	private int vratiIdAvioKompanijeZaIdAviona(int id_aviona) {
		int id_komp = 0;
		for (Avion a : listaAviona) {
			if (id_aviona == a.getId_avion()) {
				id_komp = a.getId_aviokompanija();
			}
		}
		return id_komp;
	}

	private int vratiIdAvionaZaIdAvioKompanije(int id_komp) {
		int id_avio = 0;
		for (Avion a : listaAviona) {
			if (id_komp == a.getId_aviokompanija()) {
				id_avio = a.getId_avion();
			}
		}
		return id_avio;
	}

	private Object postaviNazivAerodroma(int id_aerodroma_iz) {
		String naziv = null;
		for (Aerodrom a : listaAerodroma) {
			if (a.getId_aerodroma() == id_aerodroma_iz) {
				naziv = a.getNaziv();
			}
		}
		return naziv;
	}

	private int postaviIdAerodroma(String naziv) {
		int id = 0;
		for (Aerodrom a : listaAerodroma) {
			if (a.getNaziv().equals(naziv)) {
				id = a.getId_aerodroma();
			}
		}
		return id;
	}

	protected List<Let> ucitajListuIzabranogLeta(Let l) {
		List<Let> list = new ArrayList<>();
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajListuPutnikovogLeta(l);
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

	private void postaviAerodromeUCbox() {
		cboxDolazak.addItem("-------------------------");
		cboxPolazak.addItem("-------------------------");
		for (Aerodrom a : listaAerodroma) {
			cboxDolazak.addItem(a.getNaziv());
			cboxPolazak.addItem(a.getNaziv());
		}

	}

	private List<Aerodrom> ucitajListuAerodroma() {
		List<Aerodrom> list = null;
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().ucitajListuAerodroma();
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

	private void vratiListuProfila() {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().ucitajListuProfila();
			listaProfila = (List<Profil>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void promenaPanela(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();

	}

	private void postaviPodatkeUtabeluKorpa() {
		dtmKorpa.setRowCount(0);
		for (Let l : korpa) {
			Object[] red = { l.getOznaka(),
					vratiNazivAviokompanijeZaId(vratiIdAvioKompanijeZaIdAviona(l.getId_aviona())), l.getId_leta(),
					vratiIdAvioKompanijeZaIdAviona(l.getId_aviona()), l.getDatum() };
			dtmKorpa.addRow(red);
		}

	}
}
