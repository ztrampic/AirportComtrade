package com.comtrade.view.pocetna;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.comtrade.domen.Aerodrom;
import com.comtrade.domen.Let;
import com.comtrade.kontroler.korisnickoginterfejsa.KontrolerKI;
import com.comtrade.nitsat.NitSat;
import com.comtrade.putnik.PutnikForma;
import com.comtrade.salter.SalterForma;
import com.comtrade.transfer.TransferKlasa;
import com.comtrade.view.admin.AdminFormaAdministracijaLetova;
import com.comtrade.view.aviokompanija.AviokompanijaForma;
import com.comtrade.view.login.LoginForma;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class PocetnaForma {

	private JFrame frame;
	private DefaultTableModel dtm = new DefaultTableModel();
	private DefaultTableModel dtm2 = new DefaultTableModel();
	private JScrollPane scrollPaneOdlasci;
	private JScrollPane scrollPaneDolasci;
	private JTable tableOdlasci;
	private JTable tableDolasci;
	private JLabel lblDolasciDanas;
	private JButton btnPutnikIAdminLogin;
	private JLabel lblDanasnjiDatum;
	private JLabel lblSat;
	private JComboBox comboBoxAerodrom;
	private List<Aerodrom>listaAerodroma = new ArrayList<>();
	private int id_aerodroma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PocetnaForma window = new PocetnaForma();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PocetnaForma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(400, 100, 1289, 839);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblDolasciDanas = new JLabel("Dolasci danas");
		lblDolasciDanas.setHorizontalAlignment(SwingConstants.LEFT);
		lblDolasciDanas.setForeground(new Color(0, 0, 204));
		lblDolasciDanas.setFont(new Font("Imprint MT Shadow", Font.BOLD, 20));
		lblDolasciDanas.setBounds(675, 82, 260, 43);
		frame.getContentPane().add(lblDolasciDanas);

		JLabel lblOdlasciDanas = new JLabel("Odlasci danas");
		lblOdlasciDanas.setForeground(new Color(0, 0, 204));
		lblOdlasciDanas.setFont(new Font("Imprint MT Shadow", Font.BOLD, 20));
		lblOdlasciDanas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOdlasciDanas.setBounds(340, 82, 260, 43);
		frame.getContentPane().add(lblOdlasciDanas);

		tableDolasci = new JTable(dtm);
		scrollPaneDolasci = new JScrollPane(tableDolasci);
		scrollPaneDolasci.setBounds(675, 136, 260, 243);
		frame.getContentPane().add(scrollPaneDolasci);
		scrollPaneDolasci.setViewportView(tableDolasci);
		Object[] koloneDolasci = { "Vreme", "Let", "Iz", "Status" };
		dtm.addColumn(koloneDolasci[0]);
		dtm.addColumn(koloneDolasci[1]);
		dtm.addColumn(koloneDolasci[2]);
		dtm.addColumn(koloneDolasci[3]);

		tableOdlasci = new JTable(dtm2);
		scrollPaneOdlasci = new JScrollPane(tableOdlasci);
		scrollPaneOdlasci.setBounds(340, 136, 260, 243);
		frame.getContentPane().add(scrollPaneOdlasci);
		scrollPaneOdlasci.setViewportView(tableOdlasci);
		Object[] koloneOdlasci = { "Vreme", "Let", "Za", "Status" };
		dtm2.addColumn(koloneOdlasci[0]);
		dtm2.addColumn(koloneOdlasci[1]);
		dtm2.addColumn(koloneOdlasci[2]);
		dtm2.addColumn(koloneOdlasci[3]);

		btnPutnikIAdminLogin = new JButton("Login");
		btnPutnikIAdminLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginForma lp = new LoginForma();
				lp.setVisible(true);
			}
		});
		btnPutnikIAdminLogin.setBounds(523, 721, 252, 23);
		frame.getContentPane().add(btnPutnikIAdminLogin);
		Image slika1 = new ImageIcon(this.getClass().getResource("/slika10.jpg")).getImage();

		lblDanasnjiDatum = new JLabel("");
		lblDanasnjiDatum.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDanasnjiDatum.setBounds(1155, 11, 118, 27);
		frame.getContentPane().add(lblDanasnjiDatum);
		Date danasnjiDatum = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datum = sdf.format(danasnjiDatum);
		lblDanasnjiDatum.setText(datum);
		
		lblSat = new JLabel("");
		lblSat.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSat.setBounds(1027, 11, 118, 27);
		frame.getContentPane().add(lblSat);
		NitSat ns =new NitSat(lblSat);
		ns.start();
		
		comboBoxAerodrom = new JComboBox();
		comboBoxAerodrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv = comboBoxAerodrom.getSelectedItem().toString();
				for (Aerodrom a : listaAerodroma) {
					if (naziv.equals(a.getNaziv())) {
						id_aerodroma = a.getId_aerodroma();
						String datum =lblDanasnjiDatum.getText().toString();
						if(id_aerodroma!=0){
							List<Let>listaP=ucitajPolaske(id_aerodroma,datum);
							postaviPodatkeUTabeluOdlasci(listaP);
							List<Let>listD = ucitajDolaske(id_aerodroma,datum);
							postaviPodatkeUTabeluDolasci(listD);
						}
					}
				}
			}
		});
		comboBoxAerodrom.setBounds(498, 16, 260, 20);
		frame.getContentPane().add(comboBoxAerodrom);
		
		
		

		JLabel lblSlikaPozadina = new JLabel("");
		lblSlikaPozadina.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlikaPozadina.setBounds(0, 0, 1283, 810);
		frame.getContentPane().add(lblSlikaPozadina);
		lblSlikaPozadina.setIcon(new ImageIcon(slika1));
		
		listaAerodroma = ucitajAerodrome();
		postaviuComboAerodrome(listaAerodroma);

	}

	protected void postaviPodatkeUTabeluDolasci(List<Let> listD) {
		dtm.setRowCount(0);
		for(Let l:listD){
			Object [] red ={l.getVreme(),l.getOznaka(),postaviNazivAerodroma(l.getId_aerodroma_za())};
			dtm.addRow(red);
		}
		
	}

	protected List<Let> ucitajDolaske(int id, String datum) {
		List<Let>list = new ArrayList<>();
		try {
			TransferKlasa tf =KontrolerKI.getInstanca().ucitajListuLetovaZaIdAerodromaDolasci(id,datum);
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

	protected void postaviPodatkeUTabeluOdlasci(List<Let> listaP) {
		dtm2.setRowCount(0);
		for(Let l:listaP){
			Object [] red ={l.getVreme(),l.getOznaka(),postaviNazivAerodroma(l.getId_aerodroma_za())};
			dtm2.addRow(red);
		}
		
	}

	private Object postaviNazivAerodroma(int id) {
		String naziv = null ;
		for(Aerodrom a : listaAerodroma){
			if(id == a.getId_aerodroma()){
				naziv = a.getNaziv();
			}
		}
		return naziv;
	}

	protected List<Let> ucitajPolaske(int id, String datum) {
		List<Let>list = new ArrayList<>();
		try {
			TransferKlasa tf =KontrolerKI.getInstanca().ucitajListuLetovaZaIdAerodroma(id,datum);
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

	

	private void postaviuComboAerodrome(List<Aerodrom> listaAerodroma) {
		comboBoxAerodrom.addItem("------------------------------------------------------");
		for(Aerodrom a: listaAerodroma){
			comboBoxAerodrom.addItem(a.getNaziv().toString());
		}
	}

	private List<Aerodrom> ucitajAerodrome() {
		List<Aerodrom>list =new ArrayList<>();
		try {
			TransferKlasa tf =KontrolerKI.getInstanca().ucitajListuAerodroma();
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
}
