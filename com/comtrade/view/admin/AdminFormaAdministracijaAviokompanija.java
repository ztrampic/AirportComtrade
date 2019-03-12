package com.comtrade.view.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.comtrade.domen.Adresa;
import com.comtrade.domen.AvioKompanija;
import com.comtrade.domen.Avion;
import com.comtrade.kontroler.korisnickoginterfejsa.KontrolerKI;
import com.comtrade.transfer.TransferKlasa;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JMenuBar;

public class AdminFormaAdministracijaAviokompanija extends JFrame {

	private JPanel contentPane;
	private JTextField tfUlica;
	private JTextField tfGrad;
	private JTextField tfDrzava;
	private JTable tabelaAdresa;
	private DefaultTableModel dtmAdresa = new DefaultTableModel();
	private DefaultTableModel dtmAvioKompanija = new DefaultTableModel();
	private DefaultTableModel dtmAviona = new DefaultTableModel();
	private JTextField tfNaziv;
	private JTextField tfIDAdresa;
	private JTextField tfPib;
	private List<Adresa> listaAdresa = new ArrayList<>();
	private List<AvioKompanija> listaAvioKompanija = new ArrayList<>();
	private List<Avion> listaAviona = new ArrayList<>();
	private int id_adresa;
	private int id_aviokompanija;
	private int id_aviona;
	private JTable tabelaAvioKompanija;
	private JTable tabelaAviona;
	private JTextField tfOznak;
	private JTextField tfModel;
	private JTextField tfBrojSedista;
	private JTextField tfIDAKompanije;

	public AdminFormaAdministracijaAviokompanija() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(635, 250, 666, 594);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu nazad = new JMenu("Nazad");
		menuBar.add(nazad);
		nazad.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				AdminFormaPocetna afp = new AdminFormaPocetna();
				afp.setVisible(true);
				dispose();

			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUlicaIBroj = new JLabel("Ulica i broj");
		lblUlicaIBroj.setBounds(10, 11, 60, 25);
		contentPane.add(lblUlicaIBroj);

		JLabel lblGrad = new JLabel("Grad");
		lblGrad.setBounds(10, 41, 60, 25);
		contentPane.add(lblGrad);

		JLabel lblDrzava = new JLabel("Drzava");
		lblDrzava.setBounds(10, 77, 60, 25);
		contentPane.add(lblDrzava);

		tfUlica = new JTextField();
		tfUlica.setBounds(102, 13, 148, 20);
		contentPane.add(tfUlica);
		tfUlica.setColumns(10);

		tfGrad = new JTextField();
		tfGrad.setColumns(10);
		tfGrad.setBounds(102, 43, 148, 20);
		contentPane.add(tfGrad);

		tfDrzava = new JTextField();
		tfDrzava.setColumns(10);
		tfDrzava.setBounds(102, 79, 148, 20);
		contentPane.add(tfDrzava);

		JButton btnUnesiAdresu = new JButton("Dodaj");
		btnUnesiAdresu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tfUlica.getText().equals("") && !tfGrad.getText().equals("") && !tfDrzava.getText().equals("")) {
					Adresa adresa = new Adresa();
					adresa.setNaziv_ulice_broj(tfUlica.getText());
					adresa.setNaziv_grada(tfGrad.getText());
					adresa.setNaziv_drzave(tfDrzava.getText());
					osveziFieldove();
					posaljiAdresu(adresa);
					ucitajAdrese();
					postaviPodatkeUtabeluAdresa();
				} else {
					JOptionPane.showMessageDialog(null, "Niste popunili polja.");
				}
			}
		});
		btnUnesiAdresu.setBounds(10, 129, 72, 23);
		contentPane.add(btnUnesiAdresu);

		JButton btnObrisiAdresu = new JButton("Obrisi");
		btnObrisiAdresu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Adresa adresa = new Adresa();
				adresa.setId_adresa(id_adresa);
				osveziFieldove();
				obrisiAdresu(adresa);
				ucitajAdrese();
				postaviPodatkeUtabeluAdresa();

			}
		});

		JButton btnIzmeniAdresu = new JButton("Izmeni");
		btnIzmeniAdresu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!tfUlica.getText().equals("") && !tfGrad.getText().equals("") && !tfDrzava.getText().equals("")) {
					Adresa adresa = new Adresa();
					adresa.setNaziv_ulice_broj(tfUlica.getText());
					adresa.setNaziv_grada(tfGrad.getText());
					adresa.setNaziv_drzave(tfDrzava.getText());
					adresa.setId_adresa(id_adresa);
					izmeniAdresu(adresa);
					ucitajAdrese();
					postaviPodatkeUtabeluAdresa();
					osveziFieldove();
				} else {
					JOptionPane.showMessageDialog(null, "Niste popunili polja");
				}
			}
		});
		btnIzmeniAdresu.setBounds(92, 129, 77, 23);
		contentPane.add(btnIzmeniAdresu);
		btnObrisiAdresu.setBounds(179, 129, 72, 23);
		contentPane.add(btnObrisiAdresu);

		tabelaAdresa = new JTable(dtmAdresa);
		JScrollPane scrollPane = new JScrollPane(tabelaAdresa);
		scrollPane.setBounds(289, 15, 357, 161);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(tabelaAdresa);
		Object[] kolone = { "Id", "Ulica", "Grad", "Drzava" };
		dtmAdresa.addColumn(kolone[0]);
		dtmAdresa.addColumn(kolone[1]);
		dtmAdresa.addColumn(kolone[2]);
		dtmAdresa.addColumn(kolone[3]);
		tabelaAdresa.removeColumn(tabelaAdresa.getColumnModel().getColumn(0));
		tabelaAdresa.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int red = tabelaAdresa.getSelectedRow();
				String id = tabelaAdresa.getModel().getValueAt(red, 0).toString();
				id_adresa = Integer.parseInt(id);
				tfIDAdresa.setText(String.valueOf(id_adresa));
				String ulica = tabelaAdresa.getModel().getValueAt(red, 1).toString();
				String grad = tabelaAdresa.getModel().getValueAt(red, 2).toString();
				String drzava = tabelaAdresa.getModel().getValueAt(red, 3).toString();
				tfUlica.setText(ulica);
				tfGrad.setText(grad);
				tfDrzava.setText(drzava);
			}
		});

		tabelaAvioKompanija = new JTable(dtmAvioKompanija);
		JScrollPane scrollPane_1 = new JScrollPane(tabelaAvioKompanija);
		scrollPane_1.setBounds(289, 199, 357, 136);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(tabelaAvioKompanija);
		Object[] kolone1 = { "IDAvioKompanije", "Naziv", "PIB", "ID Adrese" };
		dtmAvioKompanija.addColumn(kolone1[0]);
		dtmAvioKompanija.addColumn(kolone1[1]);
		dtmAvioKompanija.addColumn(kolone1[2]);
		dtmAvioKompanija.addColumn(kolone1[3]);
		tabelaAvioKompanija.removeColumn(tabelaAvioKompanija.getColumnModel().getColumn(0));
		tabelaAvioKompanija.removeColumn(tabelaAvioKompanija.getColumnModel().getColumn(2));
		tabelaAvioKompanija.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int red1 = tabelaAvioKompanija.getSelectedRow();
				String id = tabelaAvioKompanija.getModel().getValueAt(red1, 0).toString();
				id_aviokompanija = Integer.parseInt(id);
				tfIDAKompanije.setText(String.valueOf(id_aviokompanija));
				PostaviPodatkeUTabeluAvion(id_aviokompanija);

			}
		});

		tabelaAviona = new JTable(dtmAviona);
		JScrollPane scrollPane_2 = new JScrollPane(tabelaAviona);
		scrollPane_2.setBounds(10, 371, 357, 136);
		contentPane.add(scrollPane_2);
		scrollPane_2.setViewportView(tabelaAviona);
		Object[] kolone2 = { "id_aviona", "Oznaka", "Model", "Kapacitet putnika", "Id Aviokompanije" };
		dtmAviona.addColumn(kolone2[0]);
		dtmAviona.addColumn(kolone2[1]);
		dtmAviona.addColumn(kolone2[2]);
		dtmAviona.addColumn(kolone2[3]);
		dtmAviona.addColumn(kolone2[4]);
		tabelaAviona.removeColumn(tabelaAviona.getColumnModel().getColumn(0));
		tabelaAviona.removeColumn(tabelaAviona.getColumnModel().getColumn(3));
		tabelaAviona.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int red2 = tabelaAviona.getSelectedRow();
				String id = tabelaAviona.getModel().getValueAt(red2, 0).toString();
				id_aviona = Integer.parseInt(id);
			}
		});

		JLabel lblNaziv = new JLabel("Naziv");
		lblNaziv.setBounds(10, 212, 60, 20);
		contentPane.add(lblNaziv);

		JLabel lblPib = new JLabel("Pib");
		lblPib.setBounds(10, 243, 48, 20);
		contentPane.add(lblPib);

		JLabel lblIdAdrese = new JLabel("Id adrese");
		lblIdAdrese.setBounds(10, 281, 72, 20);
		contentPane.add(lblIdAdrese);

		tfNaziv = new JTextField();
		tfNaziv.setBounds(102, 212, 148, 20);
		contentPane.add(tfNaziv);
		tfNaziv.setColumns(10);

		tfIDAdresa = new JTextField();
		tfIDAdresa.setEditable(false);
		tfIDAdresa.setColumns(10);
		tfIDAdresa.setBounds(102, 281, 38, 20);
		contentPane.add(tfIDAdresa);

		tfPib = new JTextField();
		tfPib.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9')) {
					e.consume();
				}
				if (tfPib.getText().length() > 8) {
					e.consume();
				}
			}
		});
		tfPib.setColumns(10);
		tfPib.setBounds(102, 243, 148, 20);
		contentPane.add(tfPib);

		JButton btnUnesiAvioKompani = new JButton("Dodaj");
		btnUnesiAvioKompani.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!tfNaziv.getText().equals("") && !tfPib.getText().equals("") && !tfIDAdresa.getText().equals("")) {
					AvioKompanija ak = new AvioKompanija();
					ak.setNaziv(tfNaziv.getText());
					ak.setPib(Integer.parseInt(tfPib.getText()));
					ak.setAdresa(Integer.parseInt(tfIDAdresa.getText()));
					osveziFieldove2();
					osveziFieldove();
					posaljiAviokompaniju(ak);
					ucitajAvioKompanije();
					postaviPodatkeUTabeluAviokompanije();
				} else {
					JOptionPane.showMessageDialog(null, "Niste popunli polja");
				}
			}
		});
		btnUnesiAvioKompani.setBounds(10, 312, 269, 23);
		contentPane.add(btnUnesiAvioKompani);

		JLabel lblText1 = new JLabel("Izaberi iz tabele adresu avio kompanije");
		lblText1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblText1.setBounds(10, 187, 309, 14);
		contentPane.add(lblText1);

		JLabel lblText2 = new JLabel("Unos aviona za izabranu avio kompaniju");
		lblText2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblText2.setBounds(337, 346, 309, 14);
		contentPane.add(lblText2);

		JLabel lblOznaka = new JLabel("Oznaka");
		lblOznaka.setBounds(377, 372, 72, 14);
		contentPane.add(lblOznaka);

		JLabel lblModelAviona = new JLabel("Model Aviona");
		lblModelAviona.setBounds(377, 397, 89, 14);
		contentPane.add(lblModelAviona);

		JLabel lblBrojSedista = new JLabel("broj sedista");
		lblBrojSedista.setBounds(377, 422, 89, 14);
		contentPane.add(lblBrojSedista);

		JLabel lblIdAvioKompanije = new JLabel("Vlasnik Aviona");
		lblIdAvioKompanije.setBounds(377, 447, 89, 14);
		contentPane.add(lblIdAvioKompanije);

		tfOznak = new JTextField();
		tfOznak.setBounds(498, 369, 148, 20);
		contentPane.add(tfOznak);
		tfOznak.setColumns(10);

		tfModel = new JTextField();
		tfModel.setColumns(10);
		tfModel.setBounds(498, 394, 148, 20);
		contentPane.add(tfModel);

		tfBrojSedista = new JTextField();
		tfBrojSedista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9')) {
					e.consume();
				}
			}
		});
		tfBrojSedista.setColumns(10);
		tfBrojSedista.setBounds(498, 419, 148, 20);
		contentPane.add(tfBrojSedista);

		tfIDAKompanije = new JTextField();
		tfIDAKompanije.setEditable(false);
		tfIDAKompanije.setColumns(10);
		tfIDAKompanije.setBounds(498, 444, 38, 20);
		contentPane.add(tfIDAKompanije);

		JButton btnUnosAviona = new JButton("Dodaj");
		btnUnosAviona.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tfOznak.getText().equals("") && !tfModel.getText().equals("")
						&& !tfIDAKompanije.getText().equals("") && !tfBrojSedista.getText().equals("")) {
					Avion a = new Avion();
					String oznaka = tfOznak.getText();
					String model = tfModel.getText();
					int broj_sedista = Integer.parseInt(tfBrojSedista.getText());
					int idAvioK = Integer.parseInt(tfIDAKompanije.getText());
					a.setOznaka(oznaka);
					a.setModel_avion(model);
					a.setBroj_sedista(broj_sedista);
					a.setId_aviokompanija(idAvioK);
					posaljiAvion(a);
					ucitajAvione();
					PostaviPodatkeUTabeluAvion(id_aviokompanija);
					osveziFieldove3();
				} else {
					JOptionPane.showMessageDialog(null, "Polja su prazna");
				}
			}
		});
		btnUnosAviona.setBounds(377, 484, 72, 23);
		contentPane.add(btnUnosAviona);

		JButton btnObrisi = new JButton("Obrisi");
		btnObrisi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Avion a = new Avion();
				a.setId_avion(id_aviona);
				obrisiAvion(a);
				ucitajAvione();
				PostaviPodatkeUTabeluAvion(id_aviokompanija);
			}
		});
		btnObrisi.setBounds(574, 484, 72, 23);
		contentPane.add(btnObrisi);
		

		JLabel lblSlikaPozadina = new JLabel("");
		lblSlikaPozadina.setBounds(0, 0, 721, 648);
		contentPane.add(lblSlikaPozadina);
		Image slika1 = new ImageIcon(this.getClass().getResource("/Slika10.jpg")).getImage();
		lblSlikaPozadina.setIcon(new ImageIcon(slika1));

		ucitajAdrese();
		ucitajAvioKompanije();
		ucitajAvione();

		postaviPodatkeUtabeluAdresa();
		postaviPodatkeUTabeluAviokompanije();

	}

	private void osveziFieldove3() {
		tfOznak.setText("");
		tfModel.setText("");
		tfBrojSedista.setText("");

	}

	private void osveziFieldove() {
		tfDrzava.setText("");
		tfGrad.setText("");
		tfUlica.setText("");

	}

	private void osveziFieldove2() {
		tfNaziv.setText("");
		tfPib.setText("");
		tfIDAdresa.setText("");

	}

	private void posaljiAdresu(Adresa adresa) {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().posaljiAdresu(adresa);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void obrisiAdresu(Adresa adresa) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().obrisiAdresu(adresa);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void izmeniAdresu(Adresa adresa) {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().izmeniAdresu(adresa);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void postaviPodatkeUtabeluAdresa() {
		dtmAdresa.setRowCount(0);
		for (Adresa a : listaAdresa) {
			Object[] red = { a.getId_adresa(), a.getNaziv_ulice_broj(), a.getNaziv_grada(), a.getNaziv_drzave() };
			dtmAdresa.addRow(red);
		}
	}

	private void ucitajAdrese() {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajListuAdresa();
			listaAdresa = (List<Adresa>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void posaljiAviokompaniju(AvioKompanija a) {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().posaljiAvioKompaniju(a);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka().toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void postaviPodatkeUTabeluAviokompanije() {
		dtmAvioKompanija.setRowCount(0);
		for (AvioKompanija a : listaAvioKompanija) {
			Object[] red1 = { a.getId_aviokompanija(), a.getNaziv(), a.getPib(), a.getAdresa() };
			dtmAvioKompanija.addRow(red1);
		}
	}

	private void ucitajAvioKompanije() {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajListuAvioKompanija();
			listaAvioKompanija = (List<AvioKompanija>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void posaljiAvion(Avion a) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().posaljiAvion(a);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void obrisiAvion(Avion a) {
		TransferKlasa tf = null;
		try {
			tf = KontrolerKI.getInstanca().obrisiAvion(a);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka().toString());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		PostaviPodatkeUTabeluAvion(id_aviona);

	}

	private void PostaviPodatkeUTabeluAvion(int id) {
		dtmAviona.setRowCount(0);
		for (Avion a : listaAviona) {
			if (a.getId_aviokompanija() == id) {
				Object[] red = { a.getId_avion(), a.getOznaka(), a.getModel_avion(), a.getBroj_sedista(),
						a.getId_aviokompanija() };
				dtmAviona.addRow(red);
			}
		}

	}
}
