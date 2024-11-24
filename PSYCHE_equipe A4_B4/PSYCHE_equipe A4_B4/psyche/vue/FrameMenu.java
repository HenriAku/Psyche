package psyche.vue;

import psyche.Controleur;
//import psyche.jeu.ControleurJeu;
//import psyche.jeu.ControleurJeu;
import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Joueur;
import psyche.jeu.metier.Mine;
import psyche.map.metier.Sommet;
import psyche.map.metier.Couleur;
import psyche.jeu.metier.Metier;
import psyche.map.ControleurMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FrameMenu extends JFrame implements ActionListener, MouseListener
{

	private final JButton btnJouer;
	private final JButton btnModifier;
	// private PanelGraph panelGraph;
	private final Controleur ctrl;
	// Necessaire pour scenarios
	private ControleurMap ctrlMap;
	private ControleurJeu ctrlJeu;

	public FrameMenu(Controleur ctrl)
	{
		this.ctrl = ctrl;
		/*---------------------------*/
		/* Configuration de la Frame */
		/*---------------------------*/
		setTitle("Menu de Lancement");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		// this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		/*------------------------------------*/
		/* Création des composant de la Frame */
		/*------------------------------------*/
		// this.panelGraph = new PanelGraph();

		/*---------------------------*/
		// Créer des sous-panneaux
		/*---------------------------*/
		JPanel panelBarre = new JPanel();
		JPanel panelTitre = new JPanel();

		/*---------------------------*/
		/* Sous Panel dans Menu */
		/*---------------------------*/
		panelBarre.setBackground(Color.LIGHT_GRAY);
		panelBarre.setLayout(new GridLayout(4, 1));

		panelTitre.setBackground(Color.LIGHT_GRAY);
		panelTitre.setLayout(new BorderLayout());

		/*--------------------------------------*/
		/* Création des composant de PanelTitre */
		/*--------------------------------------*/
		JLabel lblTitre = new JLabel("Plateau Selectionner");
		Font font = new Font("Lucida Console", Font.BOLD, 13);
		lblTitre.setFont(font);
		lblTitre.setOpaque(true);

		/*---------------------------------------*/
		/* Positon des coposants dans PanelTitre */
		/*---------------------------------------*/
		panelTitre.add(lblTitre, BorderLayout.CENTER);

		/*---------------------------------------*/
		/* Création des composants de PanelBarre */
		/*---------------------------------------*/
		this.btnJouer = new JButton("Jouer");
		this.btnJouer.setContentAreaFilled(false);
		this.btnJouer.setBorderPainted(false);
		this.btnJouer.setBackground(Color.WHITE);
		this.btnJouer.setOpaque(true);

		this.btnModifier = new JButton("Modifier");
		this.btnModifier.setContentAreaFilled(false);
		this.btnModifier.setBorderPainted(false);
		this.btnModifier.setBackground(Color.WHITE);
		this.btnModifier.setOpaque(true);

		JPanel panelHautContainer = new JPanel(new BorderLayout());
		panelHautContainer.add(panelTitre, BorderLayout.NORTH);
		panelHautContainer.setBackground(Color.LIGHT_GRAY);

		/*-----------------------------------------*/
		/* Position des composants dans PanelBarre */
		/*-----------------------------------------*/
		panelBarre.add(panelHautContainer);
		panelBarre.add(btnModifier);
		panelBarre.add(btnJouer);

		/*--------------------------------------*/
		/* Position des composants dans la Frame */
		/*--------------------------------------*/
		this.add(panelBarre, BorderLayout.WEST);
		// this.add(this.panelGraph, BorderLayout,CENTER);

		/*----------------------------*/
		/* Activation des composants */
		/*----------------------------*/

		this.btnJouer.addActionListener(this);
		this.btnModifier.addActionListener(this);
		this.btnJouer.addMouseListener(this);
		this.btnModifier.addMouseListener(this);

		setVisible(true);
	}

	public List<Mine> getMines()
	{
		return this.ctrlJeu.getMines();
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnModifier) {
			ouvrirFenetreModifier();
		} else if (e.getSource() == this.btnJouer) {
			if (this.ctrl.getCtrlScen() != null)
				ouvrirFenetreJouer(this.ctrl.getCtrlScen().getMetier()); //Cas où le metier a été lancé
			else
				ouvrirFenetreJouer();
		}
	}

	public void mouseEntered(MouseEvent e)
	{
		if (e.getSource() == this.btnJouer)
		{
			this.btnJouer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.btnJouer.setBackground(Color.GRAY);
			this.btnJouer.setForeground(Color.WHITE);
			this.btnJouer.setOpaque(true);
		}
		else if (e.getSource() == this.btnModifier)
		{
			this.btnModifier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.btnModifier.setBackground(Color.GRAY);
			this.btnModifier.setForeground(Color.WHITE);
			this.btnModifier.setOpaque(true);
		}
	}

	public void mouseExited(MouseEvent e)
	{
		if (e.getSource() == this.btnJouer)
		{
			this.btnJouer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			this.btnJouer.setBackground(Color.WHITE);
			this.btnJouer.setForeground(Color.BLACK);
			this.btnJouer.setOpaque(true);
		}
		else if (e.getSource() == this.btnModifier)
		{
			this.btnModifier.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			this.btnModifier.setBackground(Color.WHITE);
			this.btnModifier.setForeground(Color.BLACK);
			this.btnModifier.setOpaque(true);
		}
	}

	public void mouseClicked(MouseEvent e)
	{
		if (e.getSource() == this.btnModifier)
		{
			// this.setVisible(false);
		}

		if (e.getSource() == this.btnJouer)
		{
			// this.setVisible(false);
		}
	}

	public void mousePressed(MouseEvent e)
	{
	}

	public void mouseReleased(MouseEvent e)
	{
	}

	/*-------------------------*/
	/* Méthodes de scénarios */
	/*-------------------------*/

	public List<Sommet> getSommets()
	{
		return this.ctrlMap.getSommets();
	}

	public Sommet getSommet(int i)
	{
		return this.ctrlMap.getSommet(i);
	}

	public void ajouterSommet(int x, int y, int point, Couleur c)
	{
		this.ctrlMap.ajouterSommet(x, y, point, c);
	}

	public void ajouterArrete(Sommet s1, Sommet s2, int t)
	{
		this.ctrlMap.ajouterArrete(s1, s2, t);
	}

	public void ouvrirFenetreModifier()
	{
		ctrlMap = new ControleurMap(this.ctrl);
		System.out.println("Modifier");
		this.setVisible(false);
		this.dispose();
	}

	public void ouvrirFenetreJouer()
	{
		ctrlJeu = new ControleurJeu(this.ctrl);
		System.out.println("Jouer");
		this.setVisible(false);
		this.dispose();
	}

	public void ouvrirFenetreJouer(Metier metier)
	{
		ctrlJeu = new ControleurJeu(this.ctrl, metier);
		System.out.println("Jouer");
		this.setVisible(false);
		this.dispose();
	}

	public void fermerFenetreModifier()
	{
		this.ctrlMap.fermerFenetre();
	}

	public void fermerFenetreJouer()
	{
		this.ctrlJeu.fermerJeu();
	}

	public void choisirCamp(int campChoisi)
	{
		this.ctrlJeu.choisirCamp(campChoisi);
	}

	public void fermerJoueur()
	{
		this.ctrlJeu.fermerJoueur();
	}

	public void fermerMap()
	{
		this.ctrlMap.fermerMap();
		this.setVisible(true);
	}

	public void suppDonneesJeu()
	{
		this.ctrlJeu.suppDonneesJeu();
	}

	public void suppDonneesMap()
	{
		this.ctrlMap.suppDonneesMap();
	}

	public void supprimerSommets()
	{
		this.ctrlMap.supprimerSommets();
	}

	public void supprimerSommet(int id)
	{
		this.ctrlMap.majIHM();
		this.ctrlMap.supprimerSommet(id);
	}

	public void supprimerArretes()
	{
		this.ctrlMap.supprimerArretes();
	}

	public void setJoueurs(ArrayList<Joueur> joueurs)
	{
		this.ctrlJeu.setJoueurs(joueurs);
	}

	public Metier getMetierJeu()
	{
		return this.ctrlJeu.getMetierJeu();
	}

	public void setMetier(Metier metier)
	{
		this.ctrlJeu.setMetier(metier);
	}

	public void possederMine(Mine mine)
	{
		this.ctrlJeu.possederMine(mine);
	}

	public void verifierFinPartie()
	{
		this.ctrlJeu.verifierFinPartie();
	}

}