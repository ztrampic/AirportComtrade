package com.comtrade.view.aviokompanija;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.comtrade.domen.Aerodrom;
import com.comtrade.domen.AvioKompanija;
import com.comtrade.domen.Avion;
import com.comtrade.domen.CenaKlasa;
import com.comtrade.domen.KarakteristikeAerodroma;
import com.comtrade.domen.Let;
import com.comtrade.domen.Profil;
import com.comtrade.domen.Radnik;
import com.comtrade.domen.Zahtev;
import com.comtrade.kontroler.korisnickoginterfejsa.KontrolerKI;
import com.comtrade.transfer.TransferKlasa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JInternalFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AviokompanijaForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfEmail;
	private JTextField tfUser;
	private JTextField tfPassword;
	private int id_status = 4;
	private int id_profila;
	private int Id_Aviokompanije;
	private int id_leta;
	private String naziv_aviokompanije;
	private List<Profil> listaProfila = new ArrayList<>();
	private List<Avion> listaAviona = new ArrayList<>();
	private List<Let> listaLetovaKOA = new ArrayList<>();
	private List<AvioKompanija> listaAviokompanija = new ArrayList<>();
	private List<Aerodrom> listaAerodroma = new ArrayList<>();
	private JTable tableAviona;
	private DefaultTableModel dtmAviona = new DefaultTableModel();
	private DefaultTableModel dtmLetova = new DefaultTableModel();
	private JComboBox comboBoxAerodromPoletanje;
	private JComboBox comboBoxAerodromSletanje;
	private ButtonGroup bg = new ButtonGroup();

	private JTable tableLetova;
	private JTextField tfAvion;
	private JTextField txtRedovan;
	private JTextField txtCharter;
	private JTextField tfekoCena;
	private JTextField tfBizCena;
	private JTextField tfACena;
	private JTextField tfIndex1;
	private JTextField tfIndex2;
	private JTextField tfIndex3;
	private JTextField tfOdrasli;
	private JTextField tfDeca;
	private JTextField tfBrojKarata;

	public AviokompanijaForma(int id_profil_aviokompanije) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(635, 250, 802, 567);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ucitajListuProfila();
		

		for (Profil p : listaProfila) {
			if (id_profil_aviokompanije == p.getId_profil()) {
				naziv_aviokompanije = p.getIme();

			}
		}
		
		JInternalFrame internalFrameCeneKarata = new JInternalFrame("Formiranje Cena");
		internalFrameCeneKarata.setBounds(465, 125, 267, 369);
		contentPane.add(internalFrameCeneKarata);
		internalFrameCeneKarata.getContentPane().setLayout(null);
		
		JButton btnZatvoriCene = new JButton("Zatvori");
		btnZatvoriCene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrameCeneKarata.setVisible(false);
			}
		});
		btnZatvoriCene.setBounds(147, 305, 89, 23);
		internalFrameCeneKarata.getContentPane().add(btnZatvoriCene);
		
		JLabel lblText = new JLabel("Formiranje cene karte z izabrani let:");
		lblText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblText.setBounds(10, 11, 226, 23);
		internalFrameCeneKarata.getContentPane().add(lblText);
		
		JLabel lblIdLeta = new JLabel("");
		lblIdLeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdLeta.setBounds(243, 11, 23, 23);
		internalFrameCeneKarata.getContentPane().add(lblIdLeta);
		
		JLabel lblOznakaLeta = new JLabel("");
		lblOznakaLeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOznakaLeta.setBounds(276, 11, 34, 23);
		internalFrameCeneKarata.getContentPane().add(lblOznakaLeta);
		
		JLabel lblCenaEkonomske = new JLabel("Cena Ekonomske");
		lblCenaEkonomske.setBounds(10, 45, 96, 14);
		internalFrameCeneKarata.getContentPane().add(lblCenaEkonomske);
		
		JLabel lblCenaBiznis = new JLabel("Cena Biznis");
		lblCenaBiznis.setBounds(10, 70, 96, 14);
		internalFrameCeneKarata.getContentPane().add(lblCenaBiznis);
		
		JLabel lblCenaAKlase = new JLabel("Cena A Klase");
		lblCenaAKlase.setBounds(10, 95, 96, 14);
		internalFrameCeneKarata.getContentPane().add(lblCenaAKlase);
		
		tfekoCena = new JTextField();
		tfekoCena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9' )&& (c!=KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		tfekoCena.setBounds(191, 45, 45, 17);
		internalFrameCeneKarata.getContentPane().add(tfekoCena);
		tfekoCena.setColumns(10);
		
		tfBizCena = new JTextField();
		tfBizCena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9' )&& (c!=KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		tfBizCena.setColumns(10);
		tfBizCena.setBounds(191, 68, 45, 17);
		internalFrameCeneKarata.getContentPane().add(tfBizCena);
		
		tfACena = new JTextField();
		tfACena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9' )&& (c!=KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		tfACena.setColumns(10);
		tfACena.setBounds(191, 93, 45, 17);
		internalFrameCeneKarata.getContentPane().add(tfACena);
		
		JLabel lblIndexZaPopust1 = new JLabel("Index za popust preko 30 dana");
		lblIndexZaPopust1.setBounds(10, 120, 183, 14);
		internalFrameCeneKarata.getContentPane().add(lblIndexZaPopust1);
		
		JLabel lblIndexZaPopust2 = new JLabel("Index za popust do 15 dana");
		lblIndexZaPopust2.setBounds(10, 145, 183, 14);
		internalFrameCeneKarata.getContentPane().add(lblIndexZaPopust2);
		
		JLabel lblIndexZaKartu1 = new JLabel("Index za kartu 3 dana pre leta");
		lblIndexZaKartu1.setBounds(10, 170, 183, 14);
		internalFrameCeneKarata.getContentPane().add(lblIndexZaKartu1);
		
		tfIndex1 = new JTextField();
		tfIndex1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9' )&& (c!=KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		tfIndex1.setText("");
		tfIndex1.setBounds(191, 120, 45, 17);
		internalFrameCeneKarata.getContentPane().add(tfIndex1);
		tfIndex1.setColumns(10);
		
		tfIndex2 = new JTextField();
		tfIndex2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9' )&& (c!=KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		tfIndex2.setText("");
		tfIndex2.setColumns(10);
		tfIndex2.setBounds(191, 143, 45, 17);
		internalFrameCeneKarata.getContentPane().add(tfIndex2);
		
		tfIndex3 = new JTextField();
		tfIndex3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9' )&& (c!=KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		tfIndex3.setText("");
		tfIndex3.setColumns(10);
		tfIndex3.setBounds(191, 168, 45, 17);
		internalFrameCeneKarata.getContentPane().add(tfIndex3);
		
		JButton btnUpisCene = new JButton("Upisi");
		btnUpisCene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(!tfACena.getText().equals("")&&!tfBizCena.getText().equals("")&&!tfBrojKarata.getText().equals("")&&!tfDeca.getText().equals("")&&!tfekoCena.getText().equals("")&&!tfIndex1.getText().equals("")&&!tfIndex2.getText().equals("")&&!tfIndex3.getText().equals("")&&!tfOdrasli.getText().equals("")){
				double ekonomska = Double.parseDouble(tfekoCena.getText().toString());
				double biznis = Double.parseDouble(tfBizCena.getText().toString());
				double a_klasa = Double.parseDouble(tfACena.getText().toString());
				double odrasli = Double.parseDouble(tfOdrasli.getText().toString());
				double deca = Double.parseDouble(tfDeca.getText().toString());
				double dan30 = Double.parseDouble(tfIndex1.getText().toString());
				double dan15 = Double.parseDouble(tfIndex2.getText().toString());
				double dan2 = Double.parseDouble(tfIndex3.getText().toString());
				int brojKarata = Integer.parseInt(tfBrojKarata.getText().toString());
				CenaKlasa ck = new CenaKlasa();
				ck.setId_leta(id_leta);
				System.out.println(id_leta);
				ck.setId_aviokompanije(Id_Aviokompanije);
				System.out.println(Id_Aviokompanije);
				ck.setEkonomska(ekonomska);
				ck.setBiznis(biznis);
				ck.setA_klasa(a_klasa);
				ck.setOdrasli(odrasli);
				ck.setDeca(deca);
				ck.setDan30(dan30);
				ck.setDan15(dan15);
				ck.setDan2(dan2);
				ck.setBroj_karata(brojKarata);
				if(id_leta == 0){
					JOptionPane.showMessageDialog(null, "izaberite let iz tabele");
				}else{
					upisiCenuUBazu(ck);
					//ocistiIFFieldove();
				}	
			}else{
				JOptionPane.showMessageDialog(null, "Popunite polja");
			}
			
				
				
			}
		});
		btnUpisCene.setBounds(10, 305, 89, 23);
		internalFrameCeneKarata.getContentPane().add(btnUpisCene);
		
		JLabel lblIndexZaOdrasle = new JLabel("Index za odrasle");
		lblIndexZaOdrasle.setBounds(10, 195, 183, 14);
		internalFrameCeneKarata.getContentPane().add(lblIndexZaOdrasle);
		
		JLabel lblIndexZaDecu = new JLabel("Index za decu ispod 7 god");
		lblIndexZaDecu.setBounds(10, 220, 183, 14);
		internalFrameCeneKarata.getContentPane().add(lblIndexZaDecu);
		
		tfOdrasli = new JTextField();
		tfOdrasli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9' )&& (c!=KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		tfOdrasli.setText("");
		tfOdrasli.setColumns(10);
		tfOdrasli.setBounds(191, 195, 45, 17);
		internalFrameCeneKarata.getContentPane().add(tfOdrasli);
		
		tfDeca = new JTextField();
		tfDeca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9' )&& (c!=KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		tfDeca.setText("");
		tfDeca.setColumns(10);
		tfDeca.setBounds(191, 220, 45, 17);
		internalFrameCeneKarata.getContentPane().add(tfDeca);
		
		JLabel lblBrojKarata = new JLabel("Broj karata u opticaju");
		lblBrojKarata.setBounds(10, 245, 183, 14);
		internalFrameCeneKarata.getContentPane().add(lblBrojKarata);
		
		tfBrojKarata = new JTextField();
		tfBrojKarata.setEditable(false);
		tfBrojKarata.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0' || c > '9' )) {
					e.consume();
				}
			}
		});
		tfBrojKarata.setText("");
		tfBrojKarata.setColumns(10);
		tfBrojKarata.setBounds(191, 245, 45, 17);
		internalFrameCeneKarata.getContentPane().add(tfBrojKarata);
		internalFrameCeneKarata.setVisible(false);

		JInternalFrame internalFramePristupniPodaci = new JInternalFrame("Kreiranje pristupnih podataka");
		internalFramePristupniPodaci.setBounds(499, 8, 277, 278);
		contentPane.add(internalFramePristupniPodaci);
		internalFramePristupniPodaci.getContentPane().setLayout(null);
		internalFramePristupniPodaci.setVisible(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Pristupni podaci");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				internalFramePristupniPodaci.setVisible(true);
			}
		});
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("Cene");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				internalFrameCeneKarata.setVisible(true);
			}
		});
		menuBar.add(mnNewMenu_1);

		comboBoxAerodromPoletanje = new JComboBox();
		comboBoxAerodromPoletanje.setBounds(10, 330, 191, 20);
		contentPane.add(comboBoxAerodromPoletanje);

		JRadioButton rbtnRedovan = new JRadioButton("Redovan");
		rbtnRedovan.setSelected(true);
		rbtnRedovan.setBounds(10, 422, 109, 23);
		contentPane.add(rbtnRedovan);
		bg.add(rbtnRedovan);

		JRadioButton rbtnCharter = new JRadioButton("Charter");
		rbtnCharter.setBounds(10, 448, 109, 23);
		contentPane.add(rbtnCharter);
		bg.add(rbtnCharter);

		JLabel lblNewLabel_1 = new JLabel("Novi let sa izabranog aerodroma");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 294, 203, 27);
		contentPane.add(lblNewLabel_1);

		tableAviona = new JTable(dtmAviona);
		tableAviona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red = tableAviona.getSelectedRow();
				int id_aviona = (int) tableAviona.getValueAt(red, 0);
				listaLetovaKOA = vratiLetoveKonkretnogAviona(id_aviona);
				postaviLetoveKOAuTabelu(id_aviona, listaLetovaKOA);
				tfAvion.setText(tableAviona.getValueAt(red, 1).toString());
				vratiBrojSedista(id_aviona);

			}
		});
		JScrollPane scrollPane = new JScrollPane(tableAviona);
		scrollPane.setBounds(10, 42, 469, 82);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(tableAviona);
		Object[] kolone = { "ID", "Oznaka", "Model", "Broj Sedista" };
		dtmAviona.addColumn(kolone[0]);
		dtmAviona.addColumn(kolone[1]);
		dtmAviona.addColumn(kolone[2]);
		dtmAviona.addColumn(kolone[3]);
		tableAviona.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableAviona.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableAviona.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableAviona.getColumnModel().getColumn(3).setPreferredWidth(120);
		tableAviona.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		JLabel lblNazivKompanije = new JLabel("");
		lblNazivKompanije.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNazivKompanije.setBounds(237, 125, 86, 20);
		contentPane.add(lblNazivKompanije);
		lblNazivKompanije.setText(naziv_aviokompanije);

		JButton btnOsveziTabele = new JButton("Osvezi Tabele");
		btnOsveziTabele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dtmAviona.setRowCount(0);
				listaAviona = ucitajListuAviona();
				postaviPodatkeUTabelUAvion(Id_Aviokompanije);
			}
		});
		btnOsveziTabele.setBounds(10, 8, 133, 23);
		contentPane.add(btnOsveziTabele);

		JDateChooser dateChooserZaProveruPoletanje = new JDateChooser();
		dateChooserZaProveruPoletanje.setBounds(74, 361, 127, 20);
		contentPane.add(dateChooserZaProveruPoletanje);

		JButton btnProveraOd = new JButton("Proveri Aerodrom za Poletanje");
		btnProveraOd.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnProveraOd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String naziv = comboBoxAerodromPoletanje.getSelectedItem().toString();
				int id_aerodroma = 0;
				for (Aerodrom a : listaAerodroma) {
					if (naziv.equals(a.getNaziv())) {
						id_aerodroma = a.getId_aerodroma();
					}
				}
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
				if (dateChooserZaProveruPoletanje.getDate() != null) {
					String datum = s.format(dateChooserZaProveruPoletanje.getDate());
					KarakteristikeAerodroma ka = new KarakteristikeAerodroma();
					ka.setId_aerodroma(id_aerodroma);
					Let l = new Let();
					l.setDatum(datum);
					int maxPoletanja = vratiMaxPoletanja(id_aerodroma);
					int brojZauzetihPoletanja = brojPoletanjaZaOdabraniDatum(datum, id_aerodroma);
					if (maxPoletanja == brojZauzetihPoletanja) {
						JOptionPane.showMessageDialog(null, "Aerodrom nema slobodnih termina za taj datum.");
					} else {
						int brojSlobodnihTermina = maxPoletanja - brojZauzetihPoletanja;
						JOptionPane.showMessageDialog(null,
								"Slobodna su" + " " + brojSlobodnihTermina + " " + "Termina. Konkurisite za liniju.");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Izaberite datum za proveru");
				}
			}
		});
		btnProveraOd.setBounds(10, 392, 191, 20);
		contentPane.add(btnProveraOd);

		tableLetova = new JTable(dtmLetova);
		tableLetova.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red = tableLetova.getSelectedRow();
				id_leta =Integer.parseInt(tableLetova.getValueAt(red, 0).toString());
				
			}
		});
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 148, 469, 135);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(tableLetova);
		Object[] kolone1 = { "Id Leta", "Oznaka", "OD", "DO", "id_aviona", "Datum", "Vreme" };
		dtmLetova.addColumn(kolone1[0]);
		dtmLetova.addColumn(kolone1[1]);
		dtmLetova.addColumn(kolone1[2]);
		dtmLetova.addColumn(kolone1[3]);
		dtmLetova.addColumn(kolone1[4]);
		dtmLetova.addColumn(kolone1[5]);
		dtmLetova.addColumn(kolone1[6]);
		/*tableLetova.removeColumn(tableLetova.getColumnModel().getColumn(0));
		tableLetova.removeColumn(tableLetova.getColumnModel().getColumn(3));*/

		JLabel lblNewLabel_2 = new JLabel("Trenutni Letovi AvioKompanije:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 125, 230, 20);
		contentPane.add(lblNewLabel_2);
		Image slika1 = new ImageIcon(this.getClass().getResource("/slika10.jpg")).getImage();

		JLabel lblDestinacijaNovogLeta = new JLabel("Destinacija novog leta");
		lblDestinacijaNovogLeta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDestinacijaNovogLeta.setBounds(223, 294, 203, 27);
		contentPane.add(lblDestinacijaNovogLeta);

		comboBoxAerodromSletanje = new JComboBox();
		comboBoxAerodromSletanje.setBounds(223, 330, 191, 20);
		contentPane.add(comboBoxAerodromSletanje);

		JDateChooser dateChooserZaProveruSletanje = new JDateChooser();
		dateChooserZaProveruSletanje.setBounds(287, 361, 127, 20);
		contentPane.add(dateChooserZaProveruSletanje);

		JButton btnProveriDo = new JButton("Proveri Aerodrom za Sletanje");
		btnProveriDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv = comboBoxAerodromSletanje.getSelectedItem().toString();
				int id_aerodroma = 0;
				for (Aerodrom a : listaAerodroma) {
					if (naziv.equals(a.getNaziv())) {
						id_aerodroma = a.getId_aerodroma();
					}
				}
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
				if (dateChooserZaProveruSletanje.getDate() != null) {
					String datum = s.format(dateChooserZaProveruSletanje.getDate());
					KarakteristikeAerodroma ka = new KarakteristikeAerodroma();
					ka.setId_aerodroma(id_aerodroma);
					Let l = new Let();
					l.setDatum(datum);
					int maxSletanja = vratiMAxSletanja(id_aerodroma);
					int brojZauzetihSletanja = brojSletanjaZaOdabraniDatum(datum, id_aerodroma);
					if (maxSletanja == brojZauzetihSletanja) {
						JOptionPane.showMessageDialog(null, "Aerodrom nema slobodnih termina za taj datum.");
					} else {
						int brojSlobodnihTermina = maxSletanja - brojZauzetihSletanja;
						JOptionPane.showMessageDialog(null,
								"Slobodna su" + " " + brojSlobodnihTermina + " " + "Termina. Konkurisite za liniju.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Izaberite datum za proveru");
				}
			}
		});
		btnProveriDo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnProveriDo.setBounds(223, 391, 191, 20);
		contentPane.add(btnProveriDo);

		tfAvion = new JTextField();
		tfAvion.setEditable(false);
		tfAvion.setBounds(313, 423, 101, 20);
		contentPane.add(tfAvion);
		tfAvion.setColumns(10);

		JButton btnUpisiZahtev = new JButton("Posalji zahtev za let");
		btnUpisiZahtev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!tfAvion.getText().equals("") && dateChooserZaProveruPoletanje.getDate() != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String datumPoletanja = sdf.format(dateChooserZaProveruPoletanje.getDate());
					// String
					// datumSletanja=sdf.format(dateChooserZaProveruSletanje.getDate());
					String oznaka = tfAvion.getText();
					String polet = comboBoxAerodromPoletanje.getSelectedItem().toString();
					String dolet = comboBoxAerodromSletanje.getSelectedItem().toString();
					String status;
					if (rbtnRedovan.isSelected()) {
						status = txtRedovan.getText();
					} else {
						status = txtCharter.getText();
					}
					String stringZaZahtev = datumPoletanja + ";" + oznaka + ";" + polet + ";" + dolet + ";" + status;
					Zahtev z = new Zahtev();
					z.setZahtev_txt(stringZaZahtev);
					posaljiZahtev(z);
					
				} else {
					JOptionPane.showMessageDialog(null,
							"Izaberite avion sa kojim aplicirate za let i datum poletanja.");
				}

			}
		});
		btnUpisiZahtev.setBounds(252, 448, 160, 23);
		contentPane.add(btnUpisiZahtev);

		JLabel lblNewLabel = new JLabel("Unos pristupnih podataka za radnike.");
		lblNewLabel.setBounds(10, 11, 235, 27);
		internalFramePristupniPodaci.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblIme = new JLabel("Ime");
		lblIme.setBounds(10, 49, 73, 20);
		internalFramePristupniPodaci.getContentPane().add(lblIme);
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(10, 80, 73, 20);
		internalFramePristupniPodaci.getContentPane().add(lblPrezime);
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 111, 73, 20);
		internalFramePristupniPodaci.getContentPane().add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(10, 142, 73, 20);
		internalFramePristupniPodaci.getContentPane().add(lblUser);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 173, 73, 20);
		internalFramePristupniPodaci.getContentPane().add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));

		tfIme = new JTextField();
		tfIme.setBounds(98, 49, 147, 20);
		internalFramePristupniPodaci.getContentPane().add(tfIme);
		tfIme.setColumns(10);

		tfPrezime = new JTextField();
		tfPrezime.setBounds(98, 82, 147, 20);
		internalFramePristupniPodaci.getContentPane().add(tfPrezime);
		tfPrezime.setColumns(10);

		tfEmail = new JTextField();
		tfEmail.setBounds(98, 113, 147, 20);
		internalFramePristupniPodaci.getContentPane().add(tfEmail);
		tfEmail.setColumns(10);

		tfUser = new JTextField();
		tfUser.setBounds(98, 144, 147, 20);
		internalFramePristupniPodaci.getContentPane().add(tfUser);
		tfUser.setColumns(10);

		tfPassword = new JTextField();
		tfPassword.setBounds(98, 175, 147, 20);
		internalFramePristupniPodaci.getContentPane().add(tfPassword);
		tfPassword.setColumns(10);

		JButton btnKreirajProfil = new JButton("Kreiraj profil");
		btnKreirajProfil.setBounds(10, 214, 127, 23);
		internalFramePristupniPodaci.getContentPane().add(btnKreirajProfil);

		JButton btnZatvori = new JButton("Zatvori");
		btnZatvori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFramePristupniPodaci.setVisible(false);
			}
		});
		btnZatvori.setBounds(156, 214, 89, 23);
		internalFramePristupniPodaci.getContentPane().add(btnZatvori);
				
						
				
						JLabel lblSlikaPozadina = new JLabel("");
						lblSlikaPozadina.setEnabled(false);
						lblSlikaPozadina.setBounds(0, 0, 786, 512);
						contentPane.add(lblSlikaPozadina);
						lblSlikaPozadina.setIcon(new ImageIcon(slika1));
						
								txtRedovan = new JTextField();
								txtRedovan.setHorizontalAlignment(SwingConstants.LEFT);
								txtRedovan.setEnabled(false);
								txtRedovan.setEditable(false);
								txtRedovan.setText("Redovan@");
								txtRedovan.setBounds(125, 423, 73, 20);
								contentPane.add(txtRedovan);
								txtRedovan.setColumns(10);
								
										txtCharter = new JTextField();
										txtCharter.setEnabled(false);
										txtCharter.setText("Charter@");
										txtCharter.setEditable(false);
										txtCharter.setColumns(10);
										txtCharter.setBounds(125, 449, 73, 20);
										contentPane.add(txtCharter);
		btnKreirajProfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tfEmail.getText().equals("") && !tfIme.getText().equals("") && !tfPassword.getText().equals("")
						&& !tfPrezime.getText().equals("") && !tfUser.getText().equals("")) {
					Profil p = new Profil();
					String ime = tfIme.getText();
					String prezime = tfPrezime.getText();
					String emial = tfEmail.getText();
					String user = tfUser.getText();
					String pass = tfPassword.getText();
					int id = id_status;
					p.setId_status(id);
					p.setIme(ime);
					p.setPrezime(prezime);
					p.setE_mail(emial);
					p.setUsername(user);
					p.setPassword(pass);
					registrujProfil(p);
					ucitajListuProfila();
					for (Profil profil : listaProfila) {
						if (tfEmail.getText().equals(profil.getE_mail())) {
							id_profila = profil.getId_profil();
						}
					}
					Radnik radnik = new Radnik();
					radnik.setId_profila(id_profila);
					radnik.setNaziv_aviokompanije(naziv_aviokompanije);
					upisiRadnikaUbazu(radnik);
					ocistiFieldove();
				} else {
					JOptionPane.showMessageDialog(null, "Niste popunili obavezbna polja");
				}

			}
		});
		

		listaAviona = ucitajListuAviona();
		ucitajAvioKompanije();
		Id_Aviokompanije = nadjiId(naziv_aviokompanije);
		ucitajAerodrome();
		postaviAerodromeUComBox();
		postaviAerodromeUComBoxDo();

	}

	private void upisiCenuUBazu(CenaKlasa ck) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().upisiCenu(ck);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka().toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void posaljiZahtev(Zahtev z) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().posaljiZahtev(z);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka().toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int brojSletanjaZaOdabraniDatum(String datum, int id_aerodroma) {
		TransferKlasa tf;
		int odgovor = 0;
		try {
			tf = KontrolerKI.getInstanca().vratiBrojSletanjaZaOdabraniDatum(datum, id_aerodroma);
			odgovor = (int) tf.getServer_odgovor();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return odgovor;
	}

	private int brojPoletanjaZaOdabraniDatum(String datum, int id_aerodroma) {
		TransferKlasa tf;
		int odgovor = 0;
		try {
			tf = KontrolerKI.getInstanca().vratiBrojPoletanjaZaOdabraniDatum(datum, id_aerodroma);
			odgovor = (int) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return odgovor;

	}

	protected int vratiMAxSletanja(int id) {
		TransferKlasa tf;
		int maxSletanja = 0;
		try {
			tf = KontrolerKI.getInstanca().vratiMaximumSletanja(id);
			maxSletanja = (int) tf.getServer_odgovor();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maxSletanja;
	}

	private int vratiMaxPoletanja(int id) {
		TransferKlasa tf;
		int maxPoletanja = 0;
		try {
			tf = KontrolerKI.getInstanca().vratiMaximumPoletanja(id);
			maxPoletanja = (int) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxPoletanja;
	}

	private List<Let> vratiLetoveKonkretnogAviona(int id_aviona) {
		List<Let> list = new ArrayList<>();
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajListuLetaKonkretnogAviona(id_aviona);
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

	private void postaviAerodromeUComBox() {
		for (Aerodrom a : listaAerodroma) {
			comboBoxAerodromPoletanje.addItem(a.getNaziv());
		}
	}

	private void postaviAerodromeUComBoxDo() {
		for (Aerodrom a : listaAerodroma) {
			comboBoxAerodromSletanje.addItem(a.getNaziv());
		}
	}

	private void postaviLetoveKOAuTabelu(int id, List<Let> listaLetovaKOA) {
		dtmLetova.setRowCount(0);
		for (Let l : listaLetovaKOA) {
			if (id == l.getId_aviona()) {
				Object[] red = { l.getId_leta(), l.getOznaka(), vratiNazivAerodroma(l.getId_aerodroma_iz()),
						vratiNazivAerodroma(l.getId_aerodroma_za()), l.getId_aviona(), l.getDatum(), l.getVreme() };
				dtmLetova.addRow(red);
			}
		}
	}

	private Object vratiNazivAerodroma(int id_aerodroma_za) {
		String naziv = null;
		for (Aerodrom a : listaAerodroma) {
			if (id_aerodroma_za == a.getId_aerodroma()) {
				naziv = a.getNaziv();
			}
		}
		return naziv;
	}

	private int nadjiId(String naziv) {
		int id = 0;
		for (AvioKompanija a : listaAviokompanija) {
			if (naziv.equals(a.getNaziv())) {
				id = a.getId_aviokompanija();
			}
		}
		return id;
	}

	private void postaviPodatkeUTabelUAvion(int id) {
		dtmAviona.setRowCount(0);
		for (Avion a : listaAviona) {
			if (id == a.getId_aviokompanija()) {
				Object[] red = { a.getId_avion(), a.getOznaka(), a.getModel_avion(), a.getBroj_sedista() };
				dtmAviona.addRow(red);
			}
		}
	}

	private void registrujProfil(Profil p) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().registracijaProfila(p);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void upisiRadnikaUbazu(Radnik r) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().upisiRadnikaUBazu(r);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka().toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<Avion> ucitajListuAviona() {
		List<Avion> listaAviona = new ArrayList<>();
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
		return listaAviona;
	}

	private void ucitajAvioKompanije() {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajListuAvioKompanija();
			listaAviokompanija = (List<AvioKompanija>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	private void ucitajListuProfila() {
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

	private void ocistiFieldove() {
		tfEmail.setText("");
		tfIme.setText("");
		tfPassword.setText("");
		tfPrezime.setText("");
		tfUser.setText("");
	}
	
	private void ocistiIFFieldove(){
		tfekoCena.setText("");
		tfBizCena.setText("");
		tfACena.setText("");
		tfOdrasli.setText("");
		tfDeca.setText("");
		tfIndex1.setText("");
		tfIndex2.setText("");
		tfIndex3.setText("");
		tfBrojKarata.setText("");
	}
	private void vratiBrojSedista(int id_aviona){
		for(Avion a : listaAviona){
			if(id_aviona == a.getId_avion()){
				tfBrojKarata.setText(String.valueOf(a.getBroj_sedista()));
			}
		}
	}
	
}
