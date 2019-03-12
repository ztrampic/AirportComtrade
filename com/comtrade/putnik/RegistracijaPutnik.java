package com.comtrade.putnik;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.domen.Profil;
import com.comtrade.kontroler.korisnickoginterfejsa.KontrolerKI;
import com.comtrade.transfer.TransferKlasa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class RegistracijaPutnik extends JFrame {

	private JPanel contentPane;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfEmail;
	private JTextField tfUser;
	private JTextField tfPassword;
	private List<Profil> listaProfila = new ArrayList<>();
	private JLabel lblSlikaPozadina;

	public RegistracijaPutnik() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(900, 450, 283, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIme = new JLabel("Ime");
		lblIme.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIme.setBounds(10, 11, 88, 23);
		contentPane.add(lblIme);

		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrezime.setBounds(10, 45, 88, 23);
		contentPane.add(lblPrezime);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(10, 79, 88, 23);
		contentPane.add(lblEmail);

		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUser.setBounds(10, 113, 88, 23);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(10, 147, 88, 23);
		contentPane.add(lblPassword);

		tfIme = new JTextField();
		tfIme.setBounds(108, 14, 136, 20);
		contentPane.add(tfIme);
		tfIme.setColumns(10);

		tfPrezime = new JTextField();
		tfPrezime.setColumns(10);
		tfPrezime.setBounds(108, 48, 136, 20);
		contentPane.add(tfPrezime);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(108, 82, 136, 20);
		contentPane.add(tfEmail);

		tfUser = new JTextField();
		tfUser.setColumns(10);
		tfUser.setBounds(108, 116, 136, 20);
		contentPane.add(tfUser);

		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(108, 150, 136, 20);
		contentPane.add(tfPassword);

		JButton btnRegistracija = new JButton("Napravi nalog");
		btnRegistracija.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tfIme.getText().equals("") && !tfPrezime.getText().equals("") && !tfEmail.getText().equals("")
						&& !tfUser.getText().equals("") && !tfPassword.getText().equals("")) {
					if (proveriMail() == false) {
						Profil p = new Profil();
						String ime = tfIme.getText();
						String prezime = tfPrezime.getText();
						String mail = tfEmail.getText();
						String pass = tfPassword.getText();
						String user = tfUser.getText();
						p.setIme(ime);
						p.setPrezime(prezime);
						p.setE_mail(mail);
						p.setUsername(user);
						p.setPassword(pass);
						registracijaProfila(p);
						JOptionPane.showMessageDialog(null, "Uspesno ste se registrovali");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Za uneti mail postoji korisnik");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Popunite sva polja");
				}

			}
		});
		btnRegistracija.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegistracija.setBounds(10, 204, 234, 23);
		contentPane.add(btnRegistracija);
		
		lblSlikaPozadina = new JLabel("");
		lblSlikaPozadina.setBounds(0, 0, 277, 271);
		contentPane.add(lblSlikaPozadina);
		Image slika1 = new ImageIcon(this.getClass().getResource("/slika10.jpg")).getImage();
		lblSlikaPozadina.setIcon(new ImageIcon(slika1));
		ucitajListuProfila();
		

	}

	protected boolean proveriMail() {
		for (Profil p : listaProfila) {
			if (tfEmail.getText().equals(p.getE_mail())) {
				return true;
			}
		}
		return false;
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

	protected void registracijaProfila(Profil p) {
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
