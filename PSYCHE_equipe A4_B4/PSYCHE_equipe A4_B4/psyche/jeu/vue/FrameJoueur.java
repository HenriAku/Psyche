package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Joueur;

import javax.swing.*;

public class FrameJoueur extends JFrame
{
	private final PanelJoueur panelJ;
	private final ControleurJeu ctrlJeu;

	public FrameJoueur(Joueur j, ControleurJeu ctrlJeu)
	{
		switch (j.getNumJoueur())
		{
		case 1:
			this.setTitle("Syndicat Astral");
			this.setLocation(1050, 20);
			break;
		case 2:
			this.setTitle("Corporation Solaire");
			this.setLocation(1050, 450);
			break;
		}

		this.setSize(900, 425);

		this.ctrlJeu = ctrlJeu;

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/

		this.panelJ = new PanelJoueur(j, this.ctrlJeu);

		/*-------------------------*/
		/* Position des composants */
		/*-------------------------*/

		this.add(this.panelJ);

		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	public void majIHM()
	{
		this.panelJ.repaint();
	}

	public void fermerJoueur()
	{
		this.dispose();
	}

}
