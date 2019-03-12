package com.comtrade.view.login;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.comtrade.domen.Profil;
import com.comtrade.kontroler.korisnickoginterfejsa.KontrolerKI;
import com.comtrade.putnik.PutnikForma;
import com.comtrade.salter.SalterForma;
import com.comtrade.transfer.TransferKlasa;
import com.comtrade.view.admin.AdminFormaPocetna;
import com.comtrade.view.aviokompanija.AviokompanijaForma;

public class Logovanje implements ILogin {
	private int id_profil;

	@Override
	public void login(int vrednost, String user, String pass) {
		if (vrednost == 0) {
			proveraProfila(user, pass);
			if (id_profil != 0) {
				PutnikForma pf = new PutnikForma(id_profil);
				pf.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Nisu dobri podaci");
			}

		} else if (vrednost == 1) {
			
			AdminFormaPocetna afp = new AdminFormaPocetna();
			afp.setVisible(true);
			
		} else if (vrednost == 2) {
			proveraProfila(user, pass);
			if (id_profil != 0) {
				AviokompanijaForma akf = new AviokompanijaForma(id_profil);
				akf.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Nisu dobri podaci");
			}

		} else if (vrednost == 3) {

		} else if (vrednost == 4) {
			proveraProfila(user, pass);
			if(id_profil !=0){
				SalterForma af=new SalterForma(id_profil);
				af.setVisible(true);
			}else
				JOptionPane.showMessageDialog(null, "Nisu dobri podaci");
		}

	}

	private void proveraProfila(String user, String pass) {
		Profil profilZaProveru = new Profil();
		profilZaProveru.setUsername(user);
		profilZaProveru.setPassword(pass);
		TransferKlasa tf;
		try {
			tf = KontrolerKI.getInstanca().vratiProfilZaLogin(profilZaProveru);
			id_profil = (int) tf.getServer_odgovor();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
