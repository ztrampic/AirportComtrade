package com.comtrade.view.login;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.domen.Profil;
import com.comtrade.kontroler.korisnickoginterfejsa.KontrolerKI;
import com.comtrade.putnik.RegistracijaPutnik;
import com.comtrade.transfer.TransferKlasa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JPasswordField tfPassword;
	private int vrednost;

	public LoginForma() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(900, 450, 339, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUser.setBounds(29, 11, 88, 25);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(29, 47, 88, 25);
		contentPane.add(lblPassword);

		tfUser = new JTextField();
		tfUser.setBounds(145, 13, 156, 25);
		contentPane.add(tfUser);
		tfUser.setColumns(10);

		tfPassword = new JPasswordField();
		tfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode()==KeyEvent.VK_ENTER){
					akcijaLogovanja();
				}
				
			}
		});
		tfPassword.setBounds(145, 49, 156, 24);
		contentPane.add(tfPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Lucida Bright", btnLogin.getFont().getStyle() | Font.ITALIC, btnLogin.getFont().getSize()));
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				akcijaLogovanja();		
			}
		});
		btnLogin.setBounds(116, 83, 106, 23);
		contentPane.add(btnLogin);

		JButton btnRegistracija = new JButton("Registracija");
		btnRegistracija.setFont(btnRegistracija.getFont().deriveFont(btnRegistracija.getFont().getStyle() | Font.ITALIC, 11f));
		btnRegistracija.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistracijaPutnik rp = new RegistracijaPutnik();
				rp.setVisible(true);
				dispose();
			}
		});
		btnRegistracija.setBounds(116, 115, 106, 23);
		contentPane.add(btnRegistracija);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 333, 149);
		contentPane.add(lblNewLabel);
		Image slika1 = new ImageIcon(this.getClass().getResource("/slika10.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(slika1));
		
	}

	protected void proveriLogovanje(String user, String pass) {
		Profil p = new Profil();
		p.setUsername(user);
		p.setPassword(pass);
		TransferKlasa tf = null;
		try {
			tf = KontrolerKI.getInstanca().vratiVrednostStatusaProfila(p);
			vrednost = (int) tf.getServer_odgovor();
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	private void akcijaLogovanja(){
		
		String user = tfUser.getText();
		String pass = tfPassword.getText();
		if(!user.equals("")&&!pass.equals("")){
			proveriLogovanje(user, pass);
			ILogin il=new Logovanje();
			il.login(vrednost, user, pass);
			dispose();
		}else{
			JOptionPane.showMessageDialog(null, "Popunite polja");
		}
	}
}
