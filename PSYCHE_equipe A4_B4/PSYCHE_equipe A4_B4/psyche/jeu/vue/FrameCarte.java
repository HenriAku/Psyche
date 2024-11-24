/**
 * @author CAUVIN Pierre, MONTAGNE Aubin, DELPECHE Nicolas, GUELLE Clément BOUQUET Jules, YACHIR Yanis, ROUGEOLLE
 * 		Henri
 */
package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;

import javax.swing.*;
import java.awt.*;

public class FrameCarte extends JFrame
{
	private final ControleurJeu ctrlJeu;
	private final PanelCarte panelCarte;
	private final JPanel panelHaut;
	private final JPanel panelBas;
	private final JPanel panelGauche;
	private final JPanel panelDroite;

	public FrameCarte(ControleurJeu ctrlJeu)
	{
		this.ctrlJeu = ctrlJeu;

		this.setTitle("Jeu");
		this.setSize(1000, 800);
		this.setResizable(false);

		this.panelCarte = new PanelCarte(this.ctrlJeu);
		this.add(this.panelCarte);
		this.setVisible(true);

		/*------------------------------------*/
		/*         Création du contour        */
		/*------------------------------------*/
		this.panelHaut = new PanelContour(this.ctrlJeu, 0, 25);
		this.panelHaut.setBackground(new Color(0, 0, 0, 0));
		this.panelHaut.setOpaque(false);

		this.panelBas = new PanelContour(this.ctrlJeu, 75, 51);
		this.panelBas.setBackground(new Color(0, 0, 0, 0));
		this.panelBas.setOpaque(false);

		this.panelGauche = new PanelContour(this.ctrlJeu, 100, 76);
		this.panelGauche.setBackground(new Color(0, 0, 0, 0));
		this.panelGauche.setOpaque(false);

		this.panelDroite = new PanelContour(this.ctrlJeu, 26, 50);
		this.panelDroite.setBackground(new Color(0, 0, 0, 0));
		this.panelDroite.setOpaque(false);

		/*------------------------------------*/
		/*         Position du contour        */
		/*------------------------------------*/
		this.add(panelHaut, BorderLayout.NORTH);
		this.add(panelBas, BorderLayout.SOUTH);
		this.add(panelGauche, BorderLayout.WEST);
		this.add(panelDroite, BorderLayout.EAST);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public PanelCarte getPanelCarte()
	{
		return this.panelCarte;
	}

	public void fermerJeu()
	{
		this.dispose();
	}

	public void verifierFinPartie()
	{
		this.panelCarte.verifierFinPartie();
	}

}
