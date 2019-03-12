package com.comtrade.view.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminFormaPocetna extends JFrame {

	private JPanel contentPane;

	public AdminFormaPocetna() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(735, 350, 617, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAerodrom = new JButton("Administracija Aerodrom");
		btnAerodrom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminFormaAdministracijaAerodrom afaa = new AdminFormaAdministracijaAerodrom();
				afaa.setVisible(true);
				dispose();
			}
		});
		btnAerodrom.setBounds(10, 24, 582, 23);
		contentPane.add(btnAerodrom);

		JButton btnAdministracijaAviokompanija = new JButton("Administracija Aviokompanija");
		btnAdministracijaAviokompanija.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminFormaAdministracijaAviokompanija afak = new AdminFormaAdministracijaAviokompanija();
				afak.setVisible(true);
				dispose();
			}
		});
		btnAdministracijaAviokompanija.setBounds(10, 58, 582, 23);
		contentPane.add(btnAdministracijaAviokompanija);

		JButton btnAdministracijaLetova = new JButton("Administracija Letova");
		btnAdministracijaLetova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminFormaAdministracijaLetova afal=new AdminFormaAdministracijaLetova();
				afal.setVisible(true);
				dispose();
			}
		});
		btnAdministracijaLetova.setBounds(10, 92, 582, 23);
		contentPane.add(btnAdministracijaLetova);

		JButton btnKorisnickiNalozi = new JButton("Korisnicki nalozi");
		btnKorisnickiNalozi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminFormaKorisnickiNalog afkn = new AdminFormaKorisnickiNalog();
				afkn.setVisible(true);
				dispose();

			}
		});
		btnKorisnickiNalozi.setBounds(10, 126, 582, 23);
		contentPane.add(btnKorisnickiNalozi);

	}
}
