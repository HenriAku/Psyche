/**
 * @author CAUVIN Pierre, MONTAGNE Aubin, DELPECHE Nicolas, GUELLE Clément BOUQUET Jules, YACHIR Yanis, ROUGEOLLE
 * 		Henri
 */
package psyche.jeu;

import psyche.Controleur;
import psyche.jeu.metier.*;
import psyche.jeu.vue.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ControleurJeu
{
	/*--------------*/
	/* Données      */
	/*--------------*/

	private final Controleur ctrl;
	private Metier metier;
	private final FrameCarte frameCarte;
	private FrameJoueur frameJoueur1;
	private FrameJoueur frameJoueur2;
	private final FrameNom frameNom;
	private FrameFinPartie frameFinPartie;

	/*--------------*/
	/* Méthodes */
	/*--------------*/

	/**
	 * Constructeur de Controleur
	 */

	public ControleurJeu(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.metier = new Metier();
		this.frameCarte = new FrameCarte(this);
		this.frameNom = new FrameNom(this);
		this.frameJoueur1 = null;
		this.frameJoueur2 = null;

	}

	public ControleurJeu(Controleur ctrl, Metier metier)
	{
		this.ctrl = ctrl;
		this.metier = metier;
		this.frameCarte = new FrameCarte(this);
		this.frameNom = new FrameNom(this);
		this.frameJoueur1 = null;
		this.frameJoueur2 = null;

		this.metier.setFichierCharger(metier.getFichierCharger());
	}

	public Joueur getJoueurActuel()
	{
		return this.metier.getJoueurActuel();
	}

	public Joueur setJoueur1(String nom)
	{
		return this.metier.setJoueur1(nom);
	}

	public Joueur setJoueur2(String nom)
	{
		return this.metier.setJoueur2(nom);
	}

	public Joueur getJoueur1()
	{
		return this.metier.getJoueur1();
	}

	public Joueur getJoueur2()
	{
		return this.metier.getJoueur2();
	}

	public void approprierRoute(Joueur joueur, Route route)
	{
		joueur.ajouterRoute(route);
	}

	public Couleur getCouleur(String couleur)
	{
		for (Couleur c : Couleur.values())
		{
			if (c.name().equals(couleur.toUpperCase()))
			{
				return c;
			}
		}
		return null;
	}

	public Mine getMine(int i)
	{
		return this.metier.getMine(i);
	}

	/**
	 * Renvoie la liste des Mines
	 */
	public List<Mine> getMines()
	{
		return this.metier.getMines();
	}

	/**
	 * Renvoie la liste des Routes
	 */
	public List<Route> getRoutes()
	{
		return this.metier.getRoutes();
	}

	/**
	 * Renvoie une liste de Routes liées à une mine
	 *
	 * @param mine
	 * @return List<Route> Nom de la mine
	 */
	public List<Route> getRoute(Mine mine)
	{
		return mine.getRoutes();
	}

	/**
	 * Met à jour l'IHM
	 */
	public void majIHM()
	{
		this.frameCarte.getPanelCarte().repaint();
		this.frameJoueur1.majIHM();
		this.frameJoueur2.majIHM();
	}

	public Mine getMineIndice(int x, int y)
	{
		return metier.getMineIndice(x, y);
	}

	public void setVisible()
	{
		this.ctrl.setVisible();
	}

	/*------------*/
	/* Fichiers */
	/*------------*/

	/**
	 * Renvoie le chemin d'accès du fichier d'où charger les données
	 */
	public String getFichierCharger()
	{
		return this.metier.getFichierCharger();
	}

	/**
	 * Définit le chemin d'accès du fichier
	 *
	 * @param path
	 * 		Le chemin d'accès à définir.
	 */
	public void setFichierCharger(String path)
	{
		this.metier.setFichierCharger(path);
		this.majIHM();
	}

	public void changerTour()
	{

		this.metier.changerTour();
		this.majIHM();

	}

	public void possederMine(Mine mine)
	{

		if (!mine.estPrise())
		{
			this.getJoueurActuel().ajouterMine(mine);
			System.out.println("Ajout de la ressource : au joueur " + this.getJoueurActuel().getNom());
			this.getJoueurActuel().ajouterRessource(mine.getJeton());
		}
		else
		{
			System.out.println("Mine vide");

		}
		mine.enleverMinerai();

	}

	public boolean joueurPossedeMine(Mine mine)
	{
		return this.getJoueurActuel().PossedeMine(mine);
	}

	public boolean mineEstAdjacent(Mine mine1, Mine mine2)
	{
		if (mine1 == null || mine2 == null)
		{
			return false;
		}

		for (Route r1 : mine1.getRoutes())
		{
			for (Route r2 : mine2.getRoutes())
			{
				if (r1 == r2)
					return true;
			}
		}
		return false;
	}

	public void setProprietaire(Route route, Joueur joueur)
	{
		this.metier.setProprietaire(route, joueur);
	}

	public String getPiece()
	{
		return "../images/ressources/NR.png";
	}

	public String getMinerais(int indice)
	{
		Joueur joueurActuel = this.getJoueurActuel();

		if (joueurActuel.getListJeton().get(indice) != null && joueurActuel.getListJeton().size() > 1)
		{
			String lien = ((Minerai) joueurActuel.getListJeton().get(indice).getType()).getLienImage();
			return "../theme/images/ressources/" + lien;
		}

		return "";
	}

	public int getNbPiece()
	{
		return this.getJoueurActuel().getNbPiece();
	}

	public void finPartie()
	{
		this.frameCarte.setVisible(false);
		/*this.frameJoueur1.setVisible(false);
		this.frameJoueur2.setVisible(false); */
		this.frameFinPartie = new FrameFinPartie(this);
	}

	/*---------------------------SCORE--------------------------------*/

	public int scoreTotalJ1()
	{
		return this.metier.scoreTotalJ1();
	}

	public int scoreTotalJ2()
	{
		return this.metier.scoreTotalJ2();
	}

	public int calculerScoreMineJ1(Couleur couleur)
	{
		return this.metier.calculerScoreMineJ1(couleur);
	}

	public int calculerScoreMineJ2(Couleur couleur)
	{
		return this.metier.calculerScoreMineJ2(couleur);
	}

	public int calculerScoreMinesTotaleJ1()
	{
		return this.metier.calculerScoreMinesTotaleJ1();
	}

	public int calculerScoreMinesTotaleJ2()
	{
		return this.metier.calculerScoreMinesTotaleJ2();
	}

	public int calculerScorePiece1()
	{
		return this.metier.calculerScorePieceJ1();
	}

	public int calculerScorePiece2()
	{
		return this.metier.calculerScorePieceJ2();
	}

	public int calculerScoreMineraiJ1()
	{
		return this.metier.calculerScoreMineraiJ1();
	}

	public int calculerScoreMineraiJ2()
	{
		return this.metier.calculerScoreMineraiJ2();
	}

	public int getJetonPossessionJ1()
	{
		return this.metier.getJetonPossessionJ1();
	}

	public int getJetonPossessionJ2()
	{
		return this.metier.getJetonPossessionJ2();
	}

	public int pointBonusJ1()
	{
		return this.metier.pointBonusJ1();
	}

	public int pointBonusJ2()
	{
		return this.metier.pointBonusJ2();
	}

	public int getPointsColonnesJ1()
	{
		return this.metier.getPointsColonnesJ1();
	}

	public int getPointsColonnesJ2()
	{
		return this.metier.getPointsColonnesJ2();
	}

	public int getPointsLignesJ1()
	{
		return this.metier.getPointsLignesJ1();
	}

	public int getPointsLignesJ2()
	{
		return this.metier.getPointsLignesJ2();
	}

	public String getVictoire() { return this.metier.getVictoire(); }



	/*-------------------------*/
	/* Méthodes de scénarios */
	/*-------------------------*/

	//Scénarios
	public void fermerFenetre()
	{
		this.frameCarte  .dispose();
		this.frameJoueur1.dispose();
		this.frameJoueur2.dispose();
		this.frameNom    .dispose();
	}

	public void setFrameJoueur1(FrameJoueur frameJoueur)
	{
		this.frameJoueur1 = frameJoueur;
	}

	public void setFrameJoueur2(FrameJoueur frameJoueur)
	{
		this.frameJoueur2 = frameJoueur;
	}

	public void choisirCamp(int campChoisi)
	{
		if (campChoisi == 0)
			frameNom.selectionnerSA();
		if (campChoisi == 1)
			frameNom.selectionnerCS();
	}

	public void suppDonneesJeu()
	{
		this.metier.suppDonneesJeu();
	}

	public void fermerJeu()
	{
		this.frameCarte.fermerJeu();
		this.frameNom.fermerCamps();
		this.frameFinPartie.dispose();
	}

	public void fermerJoueur()
	{
		this.frameJoueur1.fermerJoueur();
		this.frameJoueur2.fermerJoueur();
	}

	public void setJoueurs(ArrayList<Joueur> joueurs)
	{
		//		this.metier.setJoueurs(joueurs);
		majSituation();

	}

	public void majSituation()
	{

		this.frameJoueur1.majIHM();
		this.frameJoueur2.majIHM();
	}


	public Metier getMetierJeu()
	{
		return this.metier;
	}

	public void setMetier(Metier metier)
	{
		this.metier = metier;
	}

	public void verifierFinPartie()
	{
		this.frameCarte.verifierFinPartie();
	}

}
