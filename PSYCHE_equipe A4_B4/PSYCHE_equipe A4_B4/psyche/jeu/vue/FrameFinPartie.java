package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;

import javax.swing.*;
//import psyche.jeu.metier.Joueur;

import java.awt.*;

public class FrameFinPartie extends JFrame
{
	private final PanelTabScore grille;
	private final ControleurJeu ctrlJeu;

	public FrameFinPartie(ControleurJeu ctrlJeu)
	{
		this.ctrlJeu = ctrlJeu;

		this.setTitle("Fin de Partie");
		this.setSize(500, 800);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*---------------------------*/
		/*  Création des composants  */
		/*---------------------------*/
		JPanel panelEntete = new JPanel();
		panelEntete.setLayout(new FlowLayout());

		JLabel entete = new JLabel("Fiche de Score");
		entete.setBackground(new Color(227, 201, 63));
		entete.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		this.grille = new PanelTabScore(this.ctrlJeu);


		/*-----------------------------------*/
		/*   Positionnement des composants  */
		/*----------------------------------*/
		panelEntete.add(entete);
		this.add(panelEntete, BorderLayout.NORTH);
		this.add(this.grille);

		this.setVisible(true);
	}
}
