package com.comtrade.view.admin;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.comtrade.domen.Adresa;
import com.comtrade.domen.Aerodrom;
import com.comtrade.domen.KarakteristikeAerodroma;
import com.comtrade.kontroler.korisnickoginterfejsa.KontrolerKI;
import com.comtrade.transfer.TransferKlasa;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class AdminFormaAdministracijaAerodrom extends JFrame {

	private JPanel contentPane;
	private List<Adresa> listaAdresa = new ArrayList<>();
	private List<Aerodrom> listaAerodroma = new ArrayList<>();
	private List<KarakteristikeAerodroma>listaKarakteristika=new ArrayList<>();
	private JTextField tfNaziv;
	private JTextField tfUlicaBroj;
	private JTextField tfGrad;
	private JTextField tfDrzava;
	private JTable table;
	private DefaultTableModel dtmAerodrom = new DefaultTableModel();
	private int id_adresa;
	private int id_aerodrom;
	private JTextField tfBrojPoletanja;
	private JTextField tfBrojSletanja;
	private JTextField tfBrojPisti;
	private JTextField tfOznaka;

	public AdminFormaAdministracijaAerodrom() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(675, 290, 668, 429);

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

		JLabel lblNaziv = new JLabel("Naziv");
		lblNaziv.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNaziv.setBounds(10, 11, 79, 20);
		contentPane.add(lblNaziv);

		tfNaziv = new JTextField();
		tfNaziv.setBounds(107, 13, 174, 20);
		contentPane.add(tfNaziv);
		tfNaziv.setColumns(10);

		JLabel lblUlicaIBroj = new JLabel("Ulica i broj");
		lblUlicaIBroj.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUlicaIBroj.setBounds(10, 42, 79, 20);
		contentPane.add(lblUlicaIBroj);

		JLabel lblGrad = new JLabel("Grad");
		lblGrad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGrad.setBounds(10, 78, 79, 20);
		contentPane.add(lblGrad);

		JLabel lblDrzava = new JLabel("Drzava");
		lblDrzava.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDrzava.setBounds(10, 109, 79, 20);
		contentPane.add(lblDrzava);

		tfUlicaBroj = new JTextField();
		tfUlicaBroj.setColumns(10);
		tfUlicaBroj.setBounds(107, 44, 174, 20);
		contentPane.add(tfUlicaBroj);

		tfGrad = new JTextField();
		tfGrad.setColumns(10);
		tfGrad.setBounds(107, 80, 174, 20);
		contentPane.add(tfGrad);

		tfDrzava = new JTextField();
		tfDrzava.setColumns(10);
		tfDrzava.setBounds(107, 111, 174, 20);
		contentPane.add(tfDrzava);

		JButton btnUnesi = new JButton("Unos");
		btnUnesi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tfUlicaBroj.getText().equals("") && !tfGrad.getText().equals("") && !tfDrzava.getText().equals("")
						&& !tfNaziv.getText().equals("")) {
					Adresa a = new Adresa();
					String ulica = tfUlicaBroj.getText();
					String grad = tfGrad.getText();
					String drzava = tfDrzava.getText();
					a.setNaziv_ulice_broj(ulica);
					a.setNaziv_grada(grad);
					a.setNaziv_drzave(drzava);
					posaljiAdresu(a);
					ucitajAdrese();
					for (Adresa a1 : listaAdresa) {
						if (ulica.equals(a1.getNaziv_ulice_broj()) && grad.equals(a1.getNaziv_grada())
								&& drzava.equals(a1.getNaziv_drzave())) {
							id_adresa = a1.getId_adresa();
						}
					}
					Aerodrom aerodrom = new Aerodrom();
					String naziv = tfNaziv.getText();
					aerodrom.setNaziv(naziv);
					aerodrom.setId_adresa(id_adresa);
					posaljiAerodrom(aerodrom);
					ucitajAerodrome();
					postaviPodatkeUtabeluAerodrom();
					osveziFieldove();
				} else {
					JOptionPane.showMessageDialog(null, "Niste popunili polja");
				}

			}
		});
		btnUnesi.setBounds(10, 140, 72, 23);
		contentPane.add(btnUnesi);

		table = new JTable(dtmAerodrom);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(291, 15, 349, 114);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Karakteristike aerodroma:");
		lblNewLabel.setBounds(10, 174, 176, 23);
		contentPane.add(lblNewLabel);

		JLabel lblIspisIzabranogAerodroma = new JLabel("");
		lblIspisIzabranogAerodroma.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIspisIzabranogAerodroma.setBounds(173, 174, 122, 23);
		contentPane.add(lblIspisIzabranogAerodroma);

		JLabel lblBrojPoletanja = new JLabel("Broj Poletanja");
		lblBrojPoletanja.setBounds(10, 208, 99, 14);
		contentPane.add(lblBrojPoletanja);

		JLabel lblBrojSletanja = new JLabel("Broj Sletanja");
		lblBrojSletanja.setBounds(10, 233, 99, 14);
		contentPane.add(lblBrojSletanja);

		JLabel lblBrojPisti = new JLabel("Broj Pisti");
		lblBrojPisti.setBounds(10, 258, 99, 14);
		contentPane.add(lblBrojPisti);

		tfBrojPoletanja = new JTextField();
		tfBrojPoletanja.setBounds(107, 208, 79, 14);
		tfBrojPoletanja.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9')) {
					e.consume();
				}
			}
		});
		contentPane.add(tfBrojPoletanja);
		tfBrojPoletanja.setColumns(10);

		tfBrojSletanja = new JTextField();
		tfBrojSletanja.setColumns(10);
		tfBrojSletanja.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9')) {
					e.consume();
				}
			}
		});
		tfBrojSletanja.setBounds(107, 233, 79, 14);
		contentPane.add(tfBrojSletanja);

		tfBrojPisti = new JTextField();
		tfBrojPisti.setColumns(10);
		tfBrojPisti.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9')) {
					e.consume();
				}
			}
		});
		tfBrojPisti.setBounds(107, 258, 79, 14);
		contentPane.add(tfBrojPisti);

		JLabel lblOpisAerodroma = new JLabel("Opis Aerodroma");
		lblOpisAerodroma.setBounds(196, 208, 99, 14);
		contentPane.add(lblOpisAerodroma);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(196, 231, 444, 91);
		contentPane.add(textArea);

		JButton btnUnosKarakteristika = new JButton("Unesi");
		btnUnosKarakteristika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(provera() == false){
					if (id_aerodrom != 0 && !tfBrojPisti.getText().equals("") && !tfBrojPoletanja.getText().equals("")
							&& !tfBrojSletanja.getText().equals("") && !tfOznaka.getText().equals("")) {
						int broj_poletanja = Integer.parseInt(tfBrojPoletanja.getText());
						int broj_sletanja = Integer.parseInt(tfBrojSletanja.getText());
						int broj_pisti = Integer.parseInt(tfBrojPisti.getText());
						String oznaka = tfOznaka.getText();
						String opis = textArea.getText();
						KarakteristikeAerodroma ka = new KarakteristikeAerodroma();
						ka.setBroj_pisti(broj_pisti);
						ka.setBroj_poletanja(broj_poletanja);
						ka.setBroj_sletanja(broj_sletanja);
						ka.setId_aerodroma(id_aerodrom);
						ka.setOpis(opis);
						ka.setOznaka(oznaka);
						posaljiKarakteristikeAerodroma(ka);
					}else{
						JOptionPane.showMessageDialog(null, "Niste popunili polja");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Za izabrani aerodrom ste uneli podatke mozete ih samo menjati");
				}
				osveziFieldove();

			}
		});
		btnUnosKarakteristika.setBounds(10, 308, 99, 14);
		contentPane.add(btnUnosKarakteristika);

		JButton btnIzmeni = new JButton("Izmeni");
		btnIzmeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (id_aerodrom != 0 && !tfBrojPisti.getText().equals("") && !tfBrojPoletanja.getText().equals("")
						&& !tfBrojSletanja.getText().equals("") && !tfOznaka.getText().equals("")){
					int broj_poletanja = Integer.parseInt(tfBrojPoletanja.getText());
					int broj_sletanja = Integer.parseInt(tfBrojSletanja.getText());
					int broj_pisti = Integer.parseInt(tfBrojPisti.getText());
					String oznaka = tfOznaka.getText();
					String opis = textArea.getText();
					KarakteristikeAerodroma ka = new KarakteristikeAerodroma();
					ka.setBroj_pisti(broj_pisti);
					ka.setBroj_poletanja(broj_poletanja);
					ka.setBroj_sletanja(broj_sletanja);
					ka.setId_aerodroma(id_aerodrom);
					ka.setOpis(opis);
					ka.setOznaka(oznaka);
					izmeniKarakteristikeAerodroma(ka);
				}else{
					JOptionPane.showMessageDialog(null, "Popunite polja za izmenu");
				}
				osveziFieldove();
			}
		});
		btnIzmeni.setBounds(10, 330, 99, 14);
		contentPane.add(btnIzmeni);

		JLabel lblOznaka = new JLabel("Oznaka");
		lblOznaka.setBounds(10, 283, 99, 14);
		contentPane.add(lblOznaka);

		tfOznaka = new JTextField();
		tfOznaka.setColumns(10);
		tfOznaka.setBounds(107, 283, 79, 14);
		contentPane.add(tfOznaka);
		
		JLabel lblSlikaPozadina = new JLabel("");
		lblSlikaPozadina.setBounds(0, -24, 662, 403);
		contentPane.add(lblSlikaPozadina);
		Image slika1 = new ImageIcon(this.getClass().getResource("/slika10.jpg")).getImage();
		lblSlikaPozadina.setIcon(new ImageIcon(slika1));
		
		
		Object[] kolone = { "ID aerodroma", "Naziv", "Ulica", "Grad", "Drzava", "ID adrese" };
		dtmAerodrom.addColumn(kolone[0]);
		dtmAerodrom.addColumn(kolone[1]);
		dtmAerodrom.addColumn(kolone[2]);
		dtmAerodrom.addColumn(kolone[3]);
		dtmAerodrom.addColumn(kolone[4]);
		dtmAerodrom.addColumn(kolone[5]);
		table.removeColumn(table.getColumnModel().getColumn(0));
		table.removeColumn(table.getColumnModel().getColumn(2));
		table.removeColumn(table.getColumnModel().getColumn(3));
		table.removeColumn(table.getColumnModel().getColumn(1));
		table.addMouseListener(new MouseListener() {

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
				int red = table.getSelectedRow();
				Aerodrom a = new Aerodrom();
				String id_aero = table.getModel().getValueAt(red, 0).toString();
				id_aerodrom = Integer.parseInt(id_aero);
				String naziv = table.getModel().getValueAt(red, 1).toString();
				String ulica = table.getModel().getValueAt(red, 2).toString();
				String grad = table.getModel().getValueAt(red, 3).toString();
				String drzava = table.getModel().getValueAt(red, 4).toString();
				String id_adre = table.getModel().getValueAt(red, 5).toString();
				id_adresa = Integer.parseInt(id_adre);
				/*
				 * tfNaziv.setText(naziv); tfUlicaBroj.setText(ulica);
				 * tfGrad.setText(grad); tfDrzava.setText(drzava);
				 */
				lblIspisIzabranogAerodroma.setText(naziv);
			}
		});

		ucitajAdrese();
		ucitajAerodrome();
		postaviPodatkeUtabeluAerodrom();
		ucitajKarakteristikeAerodroma();
		
		

	}

	

	private void ucitajKarakteristikeAerodroma() {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajListuKarakteristikaAerodroma();
			listaKarakteristika= (List<KarakteristikeAerodroma>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void postaviPodatkeUtabeluAerodrom() {
		dtmAerodrom.setRowCount(0);
		for (Aerodrom a : listaAerodroma) {
			for (Adresa a1 : listaAdresa) {
				if (a.getId_adresa() == a1.getId_adresa()) {
					Object[] red = { a.getId_aerodroma(), a.getNaziv(), a1.getNaziv_ulice_broj(), a1.getNaziv_grada(),
							a1.getNaziv_drzave(), a1.getId_adresa() };
					dtmAerodrom.addRow(red);
				}
			}
		}
	}

	protected void ucitajAerodrome() {
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

	protected void ucitajAdrese() {
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

	protected void posaljiAerodrom(Aerodrom a) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().posaljiAerodrom(a);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka().toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void posaljiAdresu(Adresa a) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().posaljiAdresu(a);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void posaljiKarakteristikeAerodroma(KarakteristikeAerodroma ka) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().posaljiKarakteristikeAerodroma(ka);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka().toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void izmeniKarakteristikeAerodroma(KarakteristikeAerodroma ka) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().izmeniKarakteristikeAerodroma(ka);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka().toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void osveziFieldove() {
		tfDrzava.setText("");
		tfGrad.setText("");
		tfNaziv.setText("");
		tfUlicaBroj.setText("");
		tfBrojPisti.setText("");
		tfBrojPoletanja.setText("");
		tfBrojSletanja.setText("");
		tfOznaka.setText("");
	}

	protected void obrisiAerodrom(Aerodrom a) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().obrisiAerodrom(a);
			tf.getServer_odgovor_poruka();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void obrisiAdresu(Adresa a) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().obrisiAdresu(a);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean provera(){
		for(KarakteristikeAerodroma ka:listaKarakteristika){
			if(id_aerodrom==ka.getId_aerodroma()){
				return true;
			}
		}
		return false;
	}
}
