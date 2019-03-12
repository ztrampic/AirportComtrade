package com.comtrade.view.admin;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import com.comtrade.domen.Aerodrom;
import com.comtrade.domen.Avion;
import com.comtrade.domen.Let;
import com.comtrade.domen.Zahtev;
import com.comtrade.kontroler.korisnickoginterfejsa.KontrolerKI;
import com.comtrade.transfer.TransferKlasa;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.JTextArea;

public class AdminFormaAdministracijaLetova extends JFrame {

	private List<Aerodrom> listaAerodroma = new ArrayList<>();
	private List<Let> listaLetova = new ArrayList<>();
	private List<Avion> listaAviona = new ArrayList<>();
	private List<Zahtev> listaZahteva = new ArrayList<>();
	private int id_aerodroma;
	private int id_polaznogAerodroma;
	private int id_dolaznogAerodroma;
	private int maximumLetovaZaAerodrom;
	private int maximumPoletanjaZaDan;
	private int id_leta;
	private int id_zahtev;
	private JTable tableLetovi;
	private JTable tableAvion;
	private DefaultTableModel dtmLet = new DefaultTableModel();
	private DefaultTableModel dtmAvion = new DefaultTableModel();
	private DefaultTableModel dtmIFZahtev = new DefaultTableModel();
	private JTextField tfMaxBrojLetovaZaIzabraniAerodrom;
	private JTextField tfOznakaLeta;
	private JTextField tfIdAviona;
	private JTextField tfSat;
	private JComboBox<String> comboBoxAerodrom;
	private JComboBox comboBoxPolazniAerodrom;
	private JComboBox comboBoxDolazniAerodrom;
	private ButtonGroup bg = new ButtonGroup();
	private JButton btnNewButton;
	private JDateChooser dateChooser;
	private JTextField tfMinut;
	private JTextField tfMaxPoletanja;
	private JTextField tfMaxSletanja;
	private JLabel lblDanasnjiDatum;
	private JTable tableIFZahtev;
	private JTextField tfIdAvKomp;

	public AdminFormaAdministracijaLetova() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(635, 250, 845, 618);
		getContentPane().setLayout(null);

		JInternalFrame internalFrame = new JInternalFrame("Zahtev za Let");
		internalFrame.setBounds(244, 212, 575, 198);
		getContentPane().add(internalFrame);
		internalFrame.getContentPane().setLayout(null);

		JButton btnIFZatvor = new JButton("Zatvori");
		btnIFZatvor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				internalFrame.setVisible(false);
			}
		});
		btnIFZatvor.setBounds(460, 134, 89, 23);
		internalFrame.getContentPane().add(btnIFZatvor);

		JButton btnIFUcitajZahtev = new JButton("Ucitaj Zahtev");
		btnIFUcitajZahtev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaZahteva = ucitajListuZahteva(0);
				postaviZahteveUTabelu();

			}
		});
		btnIFUcitajZahtev.setBounds(443, 12, 106, 23);
		internalFrame.getContentPane().add(btnIFUcitajZahtev);

		JButton btnRealizujZahtev = new JButton("Realizuj zahtev");
		btnRealizujZahtev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Zahtev z = new Zahtev();
				z.setId_zahteva(id_zahtev);
				z.setStatus_zahteva(1);
				realizujZahtev(z);
				listaZahteva = ucitajListuZahteva(0);
				postaviZahteveUTabelu();
			}

		});
		btnRealizujZahtev.setBounds(443, 46, 106, 23);
		internalFrame.getContentPane().add(btnRealizujZahtev);

		tableIFZahtev = new JTable(dtmIFZahtev);
		tableIFZahtev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red = tableIFZahtev.getSelectedRow();
				id_zahtev = (int) tableIFZahtev.getModel().getValueAt(red, 5);

			}
		});
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 407, 146);
		internalFrame.getContentPane().add(scrollPane_2);
		scrollPane_2.setViewportView(tableIFZahtev);
		Object[] koloneIFZahtev = { "Oznaka", "Od", "Do", "Datum", "Status", "Id" };
		dtmIFZahtev.addColumn(koloneIFZahtev[0]);
		dtmIFZahtev.addColumn(koloneIFZahtev[1]);
		dtmIFZahtev.addColumn(koloneIFZahtev[2]);
		dtmIFZahtev.addColumn(koloneIFZahtev[3]);
		dtmIFZahtev.addColumn(koloneIFZahtev[4]);
		dtmIFZahtev.addColumn(koloneIFZahtev[5]);

		internalFrame.setVisible(false);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 829, 21);
		getContentPane().add(menuBar);

		JMenu mnNazad = new JMenu("Nazad");
		mnNazad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminFormaPocetna afp = new AdminFormaPocetna();
				afp.setVisible(true);
				dispose();
			}
		});
		menuBar.add(mnNazad);
		JMenu mnNewMenu = new JMenu("Zahtev za let");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				internalFrame.setVisible(true);
			}
		});
		menuBar.add(mnNewMenu);

		comboBoxAerodrom = new JComboBox<String>();
		comboBoxAerodrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv = comboBoxAerodrom.getSelectedItem().toString();
				for (Aerodrom a : listaAerodroma) {
					if (naziv.equals(a.getNaziv())) {
						id_aerodroma = a.getId_aerodroma();
					}
				}

			}
		});
		comboBoxAerodrom.setBounds(244, 438, 160, 20);
		getContentPane().add(comboBoxAerodrom);

		tfMaxBrojLetovaZaIzabraniAerodrom = new JTextField();
		tfMaxBrojLetovaZaIzabraniAerodrom.setEditable(false);
		tfMaxBrojLetovaZaIzabraniAerodrom.setBounds(414, 504, 52, 20);
		getContentPane().add(tfMaxBrojLetovaZaIzabraniAerodrom);
		tfMaxBrojLetovaZaIzabraniAerodrom.setColumns(10);

		btnNewButton = new JButton("Unos Termina");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vratiMaximumLetovaPoAerodromu(id_aerodroma);
				vratiMaximumPoletanja(id_aerodroma);
				tfMaxBrojLetovaZaIzabraniAerodrom.setText(String.valueOf(maximumLetovaZaAerodrom));
				tfMaxPoletanja.setText(String.valueOf(maximumPoletanjaZaDan));
			}
		});
		btnNewButton.setBounds(244, 469, 103, 23);
		getContentPane().add(btnNewButton);

		tableLetovi = new JTable(dtmLet);
		tableLetovi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red = tableLetovi.getSelectedRow();
				String idLeta = String.valueOf(tableLetovi.getModel().getValueAt(red, 0));
				id_leta = Integer.parseInt(idLeta);
				String datumLetaIzTabele = String.valueOf(tableLetovi.getModel().getValueAt(red, 6));

			}
		});
		JScrollPane scrollPane = new JScrollPane(tableLetovi);
		scrollPane.setBounds(244, 227, 575, 171);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(tableLetovi);

		JLabel lblVreme = new JLabel("Vreme");
		lblVreme.setBounds(10, 228, 63, 14);
		getContentPane().add(lblVreme);

		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setBounds(10, 259, 63, 14);
		getContentPane().add(lblDatum);

		JLabel lblNewLabel = new JLabel("Oznaka Let");
		lblNewLabel.setBounds(10, 74, 75, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblOd = new JLabel("Od");
		lblOd.setBounds(10, 99, 75, 14);
		getContentPane().add(lblOd);

		JLabel lblDo = new JLabel("Do");
		lblDo.setBounds(10, 124, 75, 14);
		getContentPane().add(lblDo);

		JLabel lblIdAviona = new JLabel("Id Aviona");
		lblIdAviona.setBounds(10, 159, 75, 14);
		getContentPane().add(lblIdAviona);

		JLabel lblIdAvkomp = new JLabel("Id Av.Komp");
		lblIdAvkomp.setBounds(10, 184, 75, 14);
		getContentPane().add(lblIdAvkomp);

		tfIdAviona = new JTextField();
		tfIdAviona.setEditable(false);
		tfIdAviona.setBounds(84, 157, 44, 17);
		getContentPane().add(tfIdAviona);
		tfIdAviona.setColumns(10);

		tfIdAvKomp = new JTextField();
		tfIdAvKomp.setEditable(false);
		tfIdAvKomp.setColumns(10);
		tfIdAvKomp.setBounds(84, 184, 44, 17);
		getContentPane().add(tfIdAvKomp);

		JRadioButton radioButRedovan = new JRadioButton("Redovna linija");
		radioButRedovan.setSelected(true);
		radioButRedovan.setBounds(10, 280, 109, 23);
		getContentPane().add(radioButRedovan);
		bg.add(radioButRedovan);

		JRadioButton radioButCharter = new JRadioButton("Charter");
		radioButCharter.setBounds(129, 280, 109, 23);
		getContentPane().add(radioButCharter);
		bg.add(radioButCharter);

		tableAvion = new JTable(dtmAvion);
		JScrollPane scrollPane_1 = new JScrollPane(tableAvion);
		tableAvion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int red = tableAvion.getSelectedRow();
				String oznaka = tableAvion.getModel().getValueAt(red, 1).toString();
				tfOznakaLeta.setText(oznaka);
				String idAviona = tableAvion.getModel().getValueAt(red, 0).toString();
				tfIdAviona.setText(idAviona);
				String id_AvioKompanije = tableAvion.getValueAt(red, 4).toString();
				tfIdAvKomp.setText(id_AvioKompanije);
				System.out.println(id_AvioKompanije);

			}
		});
		scrollPane_1.setBounds(244, 66, 575, 122);
		getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(tableAvion);

		tfOznakaLeta = new JTextField();
		tfOznakaLeta.setEditable(false);
		tfOznakaLeta.setBounds(84, 71, 150, 20);
		getContentPane().add(tfOznakaLeta);
		tfOznakaLeta.setColumns(10);

		dateChooser = new JDateChooser();
		dateChooser.setBackground(Color.LIGHT_GRAY);
		dateChooser.setBounds(83, 253, 151, 20);
		getContentPane().add(dateChooser);

		comboBoxPolazniAerodrom = new JComboBox();
		comboBoxPolazniAerodrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv = comboBoxPolazniAerodrom.getSelectedItem().toString();
				for (Aerodrom a : listaAerodroma) {
					if (naziv.equals(a.getNaziv())) {
						id_polaznogAerodroma = a.getId_aerodroma();
					}
				}
			}
		});
		comboBoxPolazniAerodrom.setBounds(84, 99, 150, 20);
		getContentPane().add(comboBoxPolazniAerodrom);

		comboBoxDolazniAerodrom = new JComboBox();
		comboBoxDolazniAerodrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv = comboBoxDolazniAerodrom.getSelectedItem().toString();
				for (Aerodrom a : listaAerodroma) {
					if (naziv.equals(a.getNaziv())) {
						id_dolaznogAerodroma = a.getId_aerodroma();
					}
				}
			}
		});
		comboBoxDolazniAerodrom.setBounds(84, 124, 150, 20);
		getContentPane().add(comboBoxDolazniAerodrom);

		tfSat = new JTextField();
		tfSat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9')) {
					e.consume();
				}
				if (tfSat.getText().length() >= 2) {
					e.consume();
				}
			}
		});
		tfSat.setBounds(83, 222, 30, 20);
		getContentPane().add(tfSat);
		tfSat.setColumns(10);

		tfMinut = new JTextField();
		tfMinut.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9')) {
					e.consume();
				}
				if (tfMinut.getText().length() >= 2) {
					e.consume();
				}
			}
		});
		tfMinut.setColumns(10);
		tfMinut.setBounds(123, 222, 30, 20);
		getContentPane().add(tfMinut);

		JButton btnNewButton_1 = new JButton("Unos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.setVisible(false);
				if (!tfIdAviona.getText().equals("") && !tfMinut.getText().equals("") && !tfSat.getText().equals("")) {
					if (radioButCharter.isSelected()) {
						SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
						String datum = s.format(dateChooser.getDate());
						String vreme = tfSat.getText() + ":" + tfMinut.getText() + ":" + "00";
						String oznaka = tfOznakaLeta.getText();
						int id_iz = id_polaznogAerodroma;
						int id_za = id_dolaznogAerodroma;
						int id_aviona = Integer.parseInt(tfIdAviona.getText());
						int id_avioKompanije = Integer.parseInt(tfIdAvKomp.getText().toString());
						Let let = new Let();
						let.setDatum(datum);
						let.setId_aerodroma_iz(id_iz);
						let.setId_aerodroma_za(id_za);
						let.setId_aviona(id_aviona);
						let.setOznaka(oznaka);
						let.setVreme(vreme);
						let.setId_aviokompanije(id_avioKompanije);
						if (id_iz == id_za) {
							JOptionPane.showMessageDialog(null, "Odaberite gde avion leti");
						} else {
							sacuvajLetUBazu(let);
							ucitajListuLetova();
							postaviLetoveUtabelu(listaLetova);
						}
					} else {
						SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
						try {
							String pikovaniDatum = format.format(dateChooser.getDate());
							Date odDatuma = format.parse(pikovaniDatum);
							Date doDatuma = format.parse("31.03.2019");// jbg
							LocalDate start = odDatuma.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
							LocalDate kraj = doDatuma.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
							for (LocalDate datum = start; datum.isBefore(kraj); datum = datum.plusDays(1)) {
								String vreme = tfSat.getText() + ":" + tfMinut.getText() + ":" + "00";
								String oznaka = tfOznakaLeta.getText();
								int id_avioKompanije = Integer.parseInt(tfIdAvKomp.getText().toString());
								int id_iz = id_polaznogAerodroma;
								int id_za = id_dolaznogAerodroma;
								int id_aviona = Integer.parseInt(tfIdAviona.getText());
								Let let = new Let();
								let.setDatum(String.valueOf(datum));
								let.setId_aerodroma_iz(id_iz);
								let.setId_aerodroma_za(id_za);
								let.setId_aviona(id_aviona);
								let.setOznaka(oznaka);
								let.setVreme(vreme);
								let.setId_aviokompanije(id_avioKompanije);
								if (id_iz == id_za) {
									JOptionPane.showMessageDialog(null, "Odaberite gde avion leti");
								} else {
									sacuvajLetUBazu(let);
									ucitajListuLetova();
									postaviLetoveUtabelu(listaLetova);
								
								}
							}
							JOptionPane.showMessageDialog(null, "Uspesno dodati letovi");
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Popunite sva polja");
				}
			}
		});
		btnNewButton_1.setBounds(10, 310, 89, 23);
		getContentPane().add(btnNewButton_1);

		JButton btnObrisi = new JButton("Obrisi");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Let let = new Let();
				let.setId_leta(id_leta);
				obrisiLet(let);
				ucitajListuLetova();
				postaviLetoveUtabelu(listaLetova);

			}
		});
		btnObrisi.setBounds(10, 375, 89, 23);
		getContentPane().add(btnObrisi);

		tfMaxPoletanja = new JTextField();
		tfMaxPoletanja.setEditable(false);
		tfMaxPoletanja.setColumns(10);
		tfMaxPoletanja.setBounds(414, 438, 52, 20);
		getContentPane().add(tfMaxPoletanja);

		tfMaxSletanja = new JTextField();
		tfMaxSletanja.setEditable(false);
		tfMaxSletanja.setColumns(10);
		tfMaxSletanja.setBounds(414, 470, 52, 20);
		getContentPane().add(tfMaxSletanja);

		Date danasnjiDatum = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datum = sdf.format(danasnjiDatum);

		lblDanasnjiDatum = new JLabel(datum);
		lblDanasnjiDatum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDanasnjiDatum.setBounds(740, 21, 89, 31);
		getContentPane().add(lblDanasnjiDatum);

		JButton btnProveraZavrsenihLetova = new JButton("Proveri zavrsene letove");
		btnProveraZavrsenihLetova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				proveraZavrsenihLetova();

			}
		});
		btnProveraZavrsenihLetova.setBounds(10, 344, 180, 20);
		getContentPane().add(btnProveraZavrsenihLetova);

		Object[] koloneAvio = { "Id", "Oznaka", "Broj sedista", "Model", "Id Aviokompanije" };
		dtmAvion.addColumn(koloneAvio[0]);
		dtmAvion.addColumn(koloneAvio[1]);
		dtmAvion.addColumn(koloneAvio[2]);
		dtmAvion.addColumn(koloneAvio[3]);
		dtmAvion.addColumn(koloneAvio[4]);

		Object[] kolone = { "Id Leta", "Oznaka", "Id_Od", "Id_Do", "Id_Avion", "Vreme", "Datum", "Tip" };
		dtmLet.addColumn(kolone[0]);
		dtmLet.addColumn(kolone[1]);
		dtmLet.addColumn(kolone[2]);
		dtmLet.addColumn(kolone[3]);
		dtmLet.addColumn(kolone[4]);
		dtmLet.addColumn(kolone[5]);
		dtmLet.addColumn(kolone[6]);
		
		JLabel lblSlikaPozadina = new JLabel("");
		lblSlikaPozadina.setBounds(0, 0, 829, 648);
		getContentPane().add(lblSlikaPozadina);
		Image slika1 = new ImageIcon(this.getClass().getResource("/Slika10.jpg")).getImage();
		lblSlikaPozadina.setIcon(new ImageIcon(slika1));

		ucitajAerodrome();
		postaviAerodromeUCbox();
		postaviDolazneAerodromeUcbox();
		postaviPolazneAerodromeuCbox();
		ucitajAvione();
		postaviAvioneUtabelu();
		ucitajListuLetova();
		postaviLetoveUtabelu(listaLetova);

	}

	private void proveraZavrsenihLetova() {
		dtmLet.setRowCount(0);
		List<Let> listaZaBrisanjeLetova = new ArrayList<>();
		String datum = lblDanasnjiDatum.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse(datum);
			for (Let l : listaLetova) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				Date datumProvera = sdf1.parse(String.valueOf(l.getDatum()));
				if (datumProvera.before(d)) {
					Let l1 = new Let();
					l1.setId_aviokompanije(l.getId_aviokompanije());
					l1.setDatum(l.getDatum());
					l1.setId_aerodroma_iz(l.getId_aerodroma_iz());
					l1.setId_aerodroma_za(l.getId_aerodroma_za());
					l1.setId_aviona(l.getId_aviona());
					l1.setId_leta(l.getId_leta());
					l1.setOznaka(l.getOznaka());
					listaZaBrisanjeLetova.add(l1);
					postaviLetoveUtabelu(listaZaBrisanjeLetova);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void obrisiLet(Let let) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().obrisiLet(let);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void postaviLetoveUtabelu(List<Let> list) {
		dtmLet.setRowCount(0);
		for (Let let : list) {
			Object[] red = { let.getId_leta(), let.getOznaka(), let.getId_aerodroma_iz(), let.getId_aerodroma_za(),
					let.getId_aviona(), let.getVreme(), let.getDatum() };
			dtmLet.addRow(red);
		}

	}

	private void ucitajListuLetova() {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajListuLetova();
			listaLetova = (List<Let>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sacuvajLetUBazu(Let let) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().posaljiLet(let);
			//JOptionPane.showMessageDialog(null, "Let je unet u bazu.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void postaviAvioneUtabelu() {
		for (Avion a : listaAviona) {
			Object[] red = { a.getId_avion(), a.getOznaka(), a.getBroj_sedista(), a.getModel_avion(),
					a.getId_aviokompanija() };
			dtmAvion.addRow(red);
		}

	}

	private void postaviAerodromeUCbox() {
		for (Aerodrom a : listaAerodroma) {
			comboBoxAerodrom.addItem(a.getNaziv());
		}
	}

	private void postaviPolazneAerodromeuCbox() {
		for (Aerodrom a : listaAerodroma) {
			comboBoxPolazniAerodrom.addItem(a.getNaziv());
		}
	}

	private void postaviDolazneAerodromeUcbox() {
		for (Aerodrom a : listaAerodroma) {
			comboBoxDolazniAerodrom.addItem(a.getNaziv());
		}
	}

	private void ucitajAerodrome() {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajListuAerodroma();
			listaAerodroma = (List<Aerodrom>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void vratiMaximumLetovaPoAerodromu(int id_aerodroma) {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().vratiMaximumLetovaPoAerodromu(id_aerodroma);
			maximumLetovaZaAerodrom = (int) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void vratiMaximumPoletanja(int id) {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().vratiMaximumPoletanja(id);
			maximumPoletanjaZaDan = (int) tf.getServer_odgovor();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void ucitajAvione() {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajListuAviona();
			listaAviona = (List<Avion>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void osveziFieldove() {
		tfSat.setText("");
		tfMinut.setText("");
		tfOznakaLeta.setText("");

	}

	private void realizujZahtev(Zahtev z) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().izmeniZahtev(z);
			JOptionPane.showMessageDialog(null, "Zahtev realizovan");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<Zahtev> ucitajListuZahteva(int status_zahteva) {
		List<Zahtev> list = new ArrayList<>();
		TransferKlasa tf;
		try {
			tf = KontrolerKI.vratiListuAktivnihZahteva(status_zahteva);
			list = (List<Zahtev>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private void postaviZahteveUTabelu() {
		dtmIFZahtev.setRowCount(0);
		for (Zahtev z : listaZahteva) {
			String zahtev = z.getZahtev_txt();
			int id = z.getId_zahteva();
			while (zahtev.endsWith("@")) {
				String[] niz = zahtev.split(";");
				String oznaka = niz[1];
				String Od = niz[2];
				String Do = niz[3];
				String datum = niz[0];
				String status = niz[4];
				Object[] red = { oznaka, Od, Do, datum, status, id };
				dtmIFZahtev.addRow(red);
				break;
			}
		}
	}
}
