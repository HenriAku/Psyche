/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas Bouquet Jules, Rougeolle
 * 		Henri, Yachir Yanis La classe Metier gère les opérations liées aux mines et aux routes.
 */
package psyche.jeu.metier;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Metier
{
	/*--------------*/
	/* Données      */
	/*--------------*/

	//						          Al,Ag,Au,Co,Fe,Ni,Pt,Ti,NR
	private final int[] nbMinerais = { 4, 4, 4, 4, 4, 4, 4, 4, 8 };

	private int[][] tabMinerai;

	private final List<Mine> mines;
	private final List<Route> routes;
	private Joueur joueur1;
	private Joueur joueur2;

	private final GestionFichier gestionFichier = new GestionFichier(this);

	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur de la classe Metier. Initialise les listes de mines et de routes.
	 */
	public Metier()
	{
		this.mines = new ArrayList<>();
		this.routes = new ArrayList<>();
		this.joueur1 = null;
		this.joueur2 = null;
		this.copiMap("../psyche/theme/MapSave.txt");

		this.setFichierCharger(getFichierCharger());
		this.initMinerais();
	}

	private void copiMap(String s)
	{
		this.gestionFichier.copiMap(s);
	}

	/**
	 * Constructeur de la classe Metier pour les scénarios Initialise les listes de mines et de routes.
	 */
	public Metier(int[][] initMinerai)
	{
		this.mines = new ArrayList<>();
		this.routes = new ArrayList<>();
		this.joueur1 = null;
		this.joueur2 = null;

		this.tabMinerai = initMinerai;

		//Print
		for (int i = 0; i < this.tabMinerai.length; i++)
		{
			for (int j = 0; j < this.tabMinerai[i].length; j++)
			{
				System.out.print(this.tabMinerai[i][j] + " ");
			}
			System.out.println();
		}

		this.supprimerDonneeFichier(getFichierCharger());
		this.setFichierCharger(getFichierCharger());

	}

	/*------------*/
	/* Méthodes   */
	/*------------*/

	public Joueur setJoueur1(String nom)
	{
		return this.joueur1 = new Joueur(nom);
	}

	public Joueur setJoueur2(String nom)
	{
		return this.joueur2 = new Joueur(nom);
	}

	public void initMinerais()
	{
		for (int i = 0; i < this.getMines().size(); i++)
		{
			if (!this.getMines().get(i).getNom().equals("ROME"))
			{
				int rdm = (int) (Math.random() * 9);

				//System.out.println(rdm);
				if (this.nbMinerais[rdm] > 0)
				{
					this.nbMinerais[rdm]--;

					if (rdm < 8)
						this.getMines().get(i).setJeton(new Jeton(Minerai.values()[rdm]));
					else
						this.getMines().get(i).setJeton(new Jeton(Piece.values()[0]));
				}
				else
					i--;
			}
		}
	}

	public void initMinerais(int indexMine, int indexMinerais)
	{
		if (this.nbMinerais[indexMinerais] > 0)
		{
			if (indexMinerais < 8)
				this.getMine(indexMinerais).setJeton(new Jeton(Minerai.values()[indexMinerais]));
			else
				this.getMine(indexMinerais).setJeton(new Jeton(Piece.values()[0]));
		}
	}

	/*------*/
	/* Get  */
	/*------*/

	/**
	 * Renvoie le fichier chargé
	 *
	 * @return Le chemin du fichier chargé sous forme de String
	 */
	public String getFichierCharger()
	{
		return this.gestionFichier.getFichierCharger();
	}

	/**
	 * Récupère une mine précise selon son id
	 *
	 * @param id
	 * @return Une mine
	 */
	public Mine getMine(int id)
	{
		for (Mine mine : this.mines)
			if (mine.getId() == id)
				return mine;

		return null;
	}

	/**
	 * Récupère la liste de toutes les mines.
	 *
	 * @return La liste des mines.
	 */
	public List<Mine> getMines()
	{
		return this.mines;
	}

	/**
	 * Récupère la liste de toutes les routes.
	 *
	 * @return La liste des routes.
	 */
	public List<Route> getRoutes()
	{
		return this.routes;
	}

	public void resetId()
	{
		Mine.resetId();
	}

	public Mine getMineIndice(int x, int y)
	{
		for (Mine mine : this.mines)
			if (mine.getX() == x && mine.getY() == y)
				return mine;

		return null;
	}

	public void setFichierCharger(String path)
	{
		this.gestionFichier.setFichierCharger(path);
	}
	public void supprimerDonneeFichier(String path)
	{
		this.gestionFichier.supprimerDonneeFichier(path);
	}

	public Mine ajouterMine(int x, int y, int point, Couleur couleur)
	{

		System.out.println("x :" + x);
		System.out.println("y :" + y);

		if (x < 0 || x > 1000 || y < 0 || y > 800)
		{
			JOptionPane.showMessageDialog(null, "Les coordonnées ne sont pas valides", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

		System.out.println("Mine créée : " + couleur.name().charAt(0) + point);
		Mine mine = Mine.creerMine(x, y, point, couleur);

		if (tabMinerai != null)
			attribuerMinerai(mine);

		//System.out.println(mine);
		mines.add(mine);

		return mine;
	}

	public Route ajouterRoute(Mine depart, Mine arrivee, int troncons)
	{

		if (!estPossibleRoute(depart, arrivee, troncons))
		{
			JOptionPane.showMessageDialog(null, "La route existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		if (depart == null || arrivee == null)
		{
			throw new IllegalArgumentException("Depart or Arrivee mine is null");
		}
		Route route = new Route(depart, arrivee, troncons);
		this.routes.add(route);
		depart.addRoute(route);
		arrivee.addRoute(route);

		return route;
	}

	public boolean estPossibleRoute(Mine depart, Mine arrivee, int troncons)
	{
		for (Route route : this.routes)
			if (route.getDepart().equals(depart) && route.getArrivee().equals(arrivee))
				return false;

		for (Route route : this.routes)
			if (route.getDepart().equals(arrivee) && route.getArrivee().equals(depart))
				return false;

		if (troncons < 1 || troncons > 2)
		{
			JOptionPane.showMessageDialog(null, "Nombre de tronçon invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;

		}

		return true;

	}

	//------------------------------------------------Methodes Joueur-------------------------------------------------------------------------//

	public void setProprietaire(Route route, Joueur proprietaire)
	{
		route.setProprietaire(proprietaire);
	}

	public String toString()
	{
		return "\n\nMetier{ \n" + "MINE : \n" + mines + "\n\nROUTES : \n" + routes;
	}

	public void changerTour()
	{
		if (this.joueur1.estSonTour())
		{
			this.joueur1.setTour(false);
			this.joueur2.setTour(true);
		}
		else
		{
			this.joueur1.setTour(true);
			this.joueur2.setTour(false);
		}
		//		System.out.println("Changement de tour : " + this.joueur1.estSonTour()+ ": " + this.joueur1.getNom() + " " + this.joueur2.estSonTour() + ": " + this.joueur2.getNom());

	}

	public Joueur getJoueurActuel()
	{
		if (this.joueur1.estSonTour())
			return joueur1;
		return joueur2;
	}

	public Joueur getJoueur1()
	{
		return joueur1;
	}

	public Joueur getJoueur2()
	{
		return joueur2;
	}

	//---------------------------------------------------------------Score-----------------------------------------------------------------------------------------//

	public int scoreTotalJ1()
	{
		return this.joueur1.score() + this.pointBonusJ1();
	}

	public int scoreTotalJ2()
	{
		return this.joueur2.score() + this.pointBonusJ2();
	}

	public int calculerScoreMineJ1(Couleur couleur)
	{
		return this.joueur1.calculerScoreMines(couleur);
	}

	public int calculerScoreMineJ2(Couleur couleur)
	{
		return this.joueur2.calculerScoreMines(couleur);
	}

	public int calculerScoreMinesTotaleJ1()
	{
		return this.joueur1.calculerScoreMinesTotale();
	}

	public int calculerScoreMinesTotaleJ2()
	{
		return this.joueur2.calculerScoreMinesTotale();
	}

	public int calculerScorePieceJ1()
	{
		return this.joueur1.calculerScorePiece();
	}

	public int calculerScorePieceJ2()
	{
		return this.joueur2.calculerScorePiece();
	}

	public int calculerScoreMineraiJ1()
	{
		return this.joueur1.calculerPointsColonnes() + this.joueur1.calculerPointsLignes();
	}

	public int calculerScoreMineraiJ2()
	{
		return this.joueur2.calculerPointsColonnes() + this.joueur2.calculerPointsLignes();
	}

	public int getJetonPossessionJ1()
	{
		return this.joueur1.getNbJetonPossession();
	}

	public int getJetonPossessionJ2()
	{
		return this.joueur2.getNbJetonPossession();
	}

	public int pointBonusJ1()
	{
		if (this.joueur1.getNbJetonPossession() >= this.joueur2.getNbJetonPossession())
			return 10;
		else
			return 0;
	}

	public int pointBonusJ2()
	{
		if (this.joueur2.getNbJetonPossession() >= this.joueur1.getNbJetonPossession())
			return 10;
		else
			return 0;
	}

	public int getPointsColonnesJ1()
	{
		return this.joueur1.calculerPointsColonnes();
	}

	public int getPointsColonnesJ2()
	{
		return this.joueur2.calculerPointsColonnes();
	}

	public int getPointsLignesJ1()
	{
		return this.joueur1.calculerPointsLignes();
	}

	public int getPointsLignesJ2()
	{
		return this.joueur2.calculerPointsLignes();
	}

	public String getVictoire()
	{

		System.out.println( " J1 " + this.joueur1.getNom() + " score de " + this.scoreTotalJ1() + "     J2 " + this.joueur2.getNom() + " score de " + this.scoreTotalJ2() );

		if ( this.scoreTotalJ1() > this.scoreTotalJ2() )
			return this.joueur1.getNom();

		if ( this.scoreTotalJ1() < this.scoreTotalJ2() )
			return this.joueur2.getNom();

		if ( this.scoreTotalJ1() == this.scoreTotalJ2() )
		{
			if ( this.joueur1.getNbMineraisDifferentPlateau() > this.joueur2.getNbMineraisDifferentPlateau())
				return this.joueur1.getNom();
			if ( this.joueur1.getNbMineraisDifferentPlateau() < this.joueur2.getNbMineraisDifferentPlateau())
				return this.joueur2.getNom();
			if ( this.joueur1.getNbMineraisDifferentPlateau() == this.joueur2.getNbMineraisDifferentPlateau())
			{
				if ( this.joueur1.compteurJetons(this.joueur1.getListJeton(), new Jeton(Minerai.values()[2])) >  this.joueur2.compteurJetons(this.joueur2.getListJeton(), new Jeton(Minerai.values()[2])))
					return this.joueur1.getNom();
				if ( this.joueur1.compteurJetons(this.joueur1.getListJeton(), new Jeton(Minerai.values()[2])) <  this.joueur2.compteurJetons(this.joueur2.getListJeton(), new Jeton(Minerai.values()[2])))
					return this.joueur2.getNom();
				if ( this.joueur1.compteurJetons(this.joueur1.getListJeton(), new Jeton(Minerai.values()[2])) ==  this.joueur2.compteurJetons(this.joueur2.getListJeton(), new Jeton(Minerai.values()[2])))
				{
					if ( this.joueur1.getNbJetonPossession() > this.joueur2.getNbJetonPossession() )
						return this.joueur1.getNom();
					if ( this.joueur1.getNbJetonPossession() < this.joueur2.getNbJetonPossession() )
						return this.joueur2.getNom();
				}

			}

		}

		return this.joueur1.getNom() + " et " + this.joueur2.getNom();
	}





	/*-------------------------*/
	/* Méthodes de scénarios */
	/*-------------------------*/

	public void suppDonneesJeu()
	{

		Joueur.resetNbJoueur();

		for (Mine mine : mines)
		{
			mine = attribuerMinerai(mine);
		}

		for (Route route : routes)
		{
			route.retirerProprietaire();
		}
	}

	//	public void setJoueurs(ArrayList<Joueur> joueurs) {
	//
	//		.joueur1 = joueurs.get(0);
	//		this.joueur2 = joueurs.get(1);
	//	}

	public void setJoueur1(Joueur joueur1)
	{
		this.joueur1 = joueur1;
	}

	public void setJoueur2(Joueur joueur2)
	{
		this.joueur2 = joueur2;
	}

	public Mine attribuerMinerai(Mine mine)
	{
		if (mine.getNom().equals("ROME"))
			return mine;

		Mine mineRetour = mine;
		for (int i = 0; i < this.tabMinerai.length; i++)
		{
			if (mineRetour.getId() - 1 == i)
			{
				int indexMinerais = this.tabMinerai[i][1];
				if (indexMinerais < 8)
					mineRetour.setJeton(new Jeton(Minerai.values()[indexMinerais]));
				else
					mineRetour.setJeton(new Jeton(Piece.values()[0]));
			}
		}
		return mineRetour;
	}

	public Route getRoute(Mine depart, Mine arrivee)
	{
		for (Route route : this.routes)
		{
			if (route.getDepart().equals(depart) && route.getArrivee().equals(arrivee))
				return route;
			if (route.getDepart().equals(arrivee) && route.getArrivee().equals(depart))
				return route;
		}

		return null;
	}
}
