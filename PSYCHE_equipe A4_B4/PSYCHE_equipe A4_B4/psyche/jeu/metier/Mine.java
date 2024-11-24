package psyche.jeu.metier;

import java.util.ArrayList;
import java.util.List;

import psyche.jeu.metier.Route;
import psyche.jeu.metier.Couleur;
import psyche.jeu.metier.Minerai;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas Bouquet Jules, Rougeolle
 * 		Henri, Yachir Yanis La classe permet de gérer la Sommet
 */

public class Mine
{

	private static int nbRoutes = 0;

	private final int id;
	private int x;
	private int y;
	private final int point;
	private final String nom;
	private Jeton jeton;

	private final List<Route> routes;

	private final Couleur couleur;

	private Mine(int x, int y, int point, Couleur couleur, String nom)
	{
		// Auto-incrémentation de l'id de la mine
		this.id = ++Mine.nbRoutes;
		this.x = x;
		this.y = y;
		this.routes = new ArrayList<>();
		this.couleur = couleur;
		this.point = point;
		this.nom = nom;
		this.jeton = null;
	}

	/**
	 * Méthode permettant de créer une mine
	 *
	 * @param x
	 * 		la coordonnée x de la mine
	 * @param y
	 * 		la coordonnée y de la mine
	 * @param point
	 * 		le nombre de points que donne la mine
	 * @param couleur
	 * 		la couleur de la mine
	 * @return la mine créée
	 */
	public static Mine creerMine(int x, int y, int point, Couleur couleur)
	{

		if (x < 0 || x > 1000 || y < 0 || y > 800)
			return null;

		String nom = "";
		if (couleur.name().equals("ROME"))
			nom = couleur.name();
		else
			nom = couleur.name().substring(0, 1) + point;

		System.out.println("Mine créée : " + nom);
		return new Mine(x, y, point, couleur, nom);
	}

	public void enleverMinerai()
	{
		//		System.out.println("Le jeton de la mine n'esqt pas la ");
		this.jeton = null;
	}

	public boolean estPrise()
	{
		return this.jeton == null;
	}

	public int getId()
	{
		return this.id;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public int getPoint()
	{
		return this.point;
	}

	public Couleur getCouleur()
	{
		return this.couleur;
	}

	public List<Route> getRoutes()
	{
		return this.routes;
	}

	public String getNom()
	{
		return this.nom;
	}

	public Jeton getJeton()
	{
		return this.jeton;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public void setJeton(Jeton jeton)
	{
		this.jeton = jeton;
	}

	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	public static void resetId()
	{
		Mine.nbRoutes = 0;
	}

	public void addRoute(Route route)
	{
		this.routes.add(route);
	}

	public void supprimerRouteMine(Mine mine, Route arrrete)
	{
		this.routes.remove(arrrete);
	}

	public String toString()
	{
		return "mine{" + "id=" + this.id + ", x=" + this.x + ", y=" + this.y + ", point=" + this.point + ", couleur=" + this.couleur + '}';
	}
}
