package com.comtrade.view.panel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelSlika extends JPanel {
	private BufferedImage slika;
	
	public PanelSlika(){
		setSize(400,400);
		setVisible(true);
		ucitajSliku("/slika10panel.png");
		
	}
	
	private void ucitajSliku(String string) {
		try {
			slika = ImageIO.read((PanelSlika.class.getResource(string)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(slika, 0, 0, this);
	}
}
