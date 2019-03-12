package com.comtrade.view.admin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.comtrade.domen.AvioKompanija;
import com.comtrade.domen.Profil;
import com.comtrade.domen.StatusProfila;
import com.comtrade.kontroler.korisnickoginterfejsa.KontrolerKI;
import com.comtrade.transfer.TransferKlasa;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class AdminFormaKorisnickiNalog extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxAvioKompanija;
	private List<AvioKompanija> listaAvioKompanija = new ArrayList<>();
	private List<StatusProfila> listaStatusa = new ArrayList<>();
	private JTable tabelaStatusa;
	private DefaultTableModel dtmStatusa = new DefaultTableModel();
	private JTextField tfNaziv;
	private JTextField tfPassword;
	private JTextField tfUser;
	private JTextField tfStatusKorisnika;
	private JTextField tfNazivNovogStatusa;
	private JTextField tfEmail;
	private int id_avioKompanije;

	public AdminFormaKorisnickiNalog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(635, 250, 666, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBoxAvioKompanija = new JComboBox();
		comboBoxAvioKompanija.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tfNaziv.setText(comboBoxAvioKompanija.getSelectedItem().toString());
				for (AvioKompanija a : listaAvioKompanija) {
					if (comboBoxAvioKompanija.getSelectedItem().toString().equals(a.getNaziv().toString())) {
						id_avioKompanije = a.getId_aviokompanija();
						System.out.println(id_avioKompanije);
					}
				}
			}
		});
		comboBoxAvioKompanija.setBounds(10, 11, 252, 20);
		contentPane.add(comboBoxAvioKompanija);

		tabelaStatusa = new JTable(dtmStatusa);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(363, 13, 277, 102);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(tabelaStatusa);

		JLabel lblIme = new JLabel("Naziv kompanijeje");
		lblIme.setBounds(10, 117, 91, 14);
		contentPane.add(lblIme);

		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(10, 67, 53, 14);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 92, 91, 14);
		contentPane.add(lblPassword);

		tfNaziv = new JTextField();
		tfNaziv.setEditable(false);
		tfNaziv.setBounds(111, 114, 151, 20);
		contentPane.add(tfNaziv);
		tfNaziv.setColumns(10);

		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(111, 89, 151, 20);
		contentPane.add(tfPassword);

		tfUser = new JTextField();
		tfUser.setColumns(10);
		tfUser.setBounds(111, 64, 151, 20);
		contentPane.add(tfUser);

		JLabel lblStatusKorisnika = new JLabel("Status Korisnika");
		lblStatusKorisnika.setBounds(10, 142, 76, 14);
		contentPane.add(lblStatusKorisnika);

		tfStatusKorisnika = new JTextField();
		tfStatusKorisnika.setEditable(false);
		tfStatusKorisnika.setBounds(111, 139, 45, 20);
		contentPane.add(tfStatusKorisnika);
		tfStatusKorisnika.setColumns(10);
		tfStatusKorisnika.setText("2");

		JLabel lblNewLabel = new JLabel("Unos novog statusa korisnika");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(363, 126, 277, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNazivStatusa = new JLabel("Naziv statusa");
		lblNazivStatusa.setBounds(363, 156, 81, 14);
		contentPane.add(lblNazivStatusa);

		tfNazivNovogStatusa = new JTextField();
		tfNazivNovogStatusa.setColumns(10);
		tfNazivNovogStatusa.setBounds(454, 151, 186, 20);
		contentPane.add(tfNazivNovogStatusa);

		JButton btnNapraviStatus = new JButton("Dodaj");
		btnNapraviStatus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!tfNazivNovogStatusa.getText().equals("")) {
					StatusProfila sp = new StatusProfila();
					String naziv = tfNazivNovogStatusa.getText();
					sp.setNaziv(naziv);
					posaljiNovStatus(sp);
					osveziFieldove();
					try {
						ucitajListuStatusaProfila();
						postaviStatuseProfila();
					} catch (Exception e) {
						// TODO: handle exception
					}

				} else {
					JOptionPane.showMessageDialog(null, "Niste Uneli Naziv");
				}

			}
		});
		btnNapraviStatus.setBounds(551, 177, 89, 23);
		contentPane.add(btnNapraviStatus);

		JButton btnNewButton = new JButton("Napravi nalog");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!tfUser.getText().equals("") && !tfPassword.getText().equals("") && !tfEmail.getText().equals("")) {
					Profil p = new Profil();
					String mail = tfEmail.getText();
					String ime = tfNaziv.getText();
					String user = tfUser.getText();
					String pass = tfPassword.getText();
					int id_statusa = Integer.parseInt(tfStatusKorisnika.getText());
					p.setIme(ime);
					p.setE_mail(mail);
					p.setId_status(id_statusa);
					p.setUsername(user);
					p.setPassword(pass);
					try {
						registracijaProfilaAvioKompanije(p);
					} catch (Exception e) {
						// TODO: handle exception
					}
					osveziFieldove1();
					JOptionPane.showMessageDialog(null, "Uspesno registrovana Kompanija");
				} else {
					JOptionPane.showMessageDialog(null, "Niste popinili polja");
				}

			}
		});
		btnNewButton.setBounds(136, 177, 126, 23);
		contentPane.add(btnNewButton);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 42, 46, 14);
		contentPane.add(lblEmail);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(111, 39, 151, 20);
		contentPane.add(tfEmail);
		Object[] kolone = { "Naziv Statusa", "ID statusa" };
		dtmStatusa.addColumn(kolone[0]);
		dtmStatusa.addColumn(kolone[1]);
		TableColumnModel tcm = tabelaStatusa.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(200);

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
		ucitajAvioKompanije();
		postaviAviokompanijeUCombo();
		ucitajListuStatusaProfila();
		postaviStatuseProfila();

	}

	protected void osveziFieldove() {
		dtmStatusa.setRowCount(0);
		tfNazivNovogStatusa.setText("");
		tfNaziv.setText("");
		tfPassword.setText("");
		tfUser.setText("");
		tfEmail.setText("");
	}

	protected void osveziFieldove1() {
		tfNazivNovogStatusa.setText("");
		tfNaziv.setText("");
		tfPassword.setText("");
		tfUser.setText("");
		tfEmail.setText("");
	}

	private void posaljiNovStatus(StatusProfila sp) {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().posaljiStatusProfila(sp);
			JOptionPane.showMessageDialog(null, tf.getServer_odgovor_poruka());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void ucitajAvioKompanije() {
		try {
			TransferKlasa tf = KontrolerKI.getInstanca().ucitajListuAvioKompanija();
			listaAvioKompanija = (List<AvioKompanija>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void postaviAviokompanijeUCombo() {
		for (AvioKompanija a : listaAvioKompanija) {
			comboBoxAvioKompanija.addItem(a.getNaziv());
		}
	}

	private void ucitajListuStatusaProfila() {
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().ucitajListuStatusaProfila();
			listaStatusa = (List<StatusProfila>) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void postaviStatuseProfila() {
		for (StatusProfila sp : listaStatusa) {
			Object[] red = { sp.getNaziv(), sp.getId_status() };
			dtmStatusa.addRow(red);
		}
	}

	private void registracijaProfilaAvioKompanije(Profil p) {
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
}
