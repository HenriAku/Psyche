package psyche.map.metier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas Bouquet Jules, Rougeolle
 * 		Henri, Yachir Yanis La classe permet de gérer la Sommet
 */

public class Sommet
{

	/*--------------*/
	/* Données      */
	/*--------------*/

	// constante :

	private static int nbArretes = 0;

	// variables :

	private final int id;
	private int x;
	private int y;
	private int point;
	private String nom;
	private final List<Arrete> arretes;
	private Couleur couleur;



	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Controleur permettant la création du sommet
	 *
	 * @param x
	 * 		coordonnée X
	 * @param y
	 * 		coordonnée Y
	 * @param point
	 * 		nombre de point du sommet
	 * @param couleur
	 * 		couleur du sommet
	 * @param nom
	 * 		nom du sommet ( choisi avec la première lettre de la couleur et son nombre de point )
	 */
	private Sommet(int x, int y, int point, Couleur couleur, String nom)
	{
		// Auto-incrémentation de l'id de la mine
		this.id = ++Sommet.nbArretes;
		this.x = x;
		this.y = y;
		this.arretes = new ArrayList<>();
		this.couleur = couleur;
		this.point = point;
		this.nom = nom;
	}

	/**
	 * Méthode permettant de créer une mine
	 *
	 * @param x
	 * 		la coordonnée x du sommet
	 * @param y
	 * 		la coordonnée y du sommet
	 * @param point
	 * 		le nombre de points que donne le sommet
	 * @param couleur
	 * 		la couleur du sommet
	 * @return le sommet créée
	 */
	public static Sommet creerSommet(int x, int y, int point, Couleur couleur)
	{
		if (x < 0 || x > 1000 || y < 0 || y > 800)
			return null;

		String nom = "";

		if (couleur.name().equals("ROME"))
			nom = couleur.name();
		else
			nom = couleur.name().substring(0, 1) + point;

		return new Sommet(x, y, point, couleur, nom);
	}


	/*------------------*/
	/* 		  get 		*/
	/*------------------*/

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

	public List<Arrete> getArretes()
	{
		return this.arretes;
	}

	public String getNom()
	{
		return this.nom;
	}

	/*-----------------*/
	/*       set       */
	/*-----------------*/

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public void setCouleur(Couleur couleur)
	{
		this.couleur = couleur;
	}

	public void setPoint(int point)
	{
		this.point = point;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	public static void resetId()
	{
		Sommet.nbArretes = 0;
	}

	public void addArrete(Arrete arrete)
	{
		this.arretes.add(arrete);
	}

	public void supprimerArreteSommet(Arrete arrrete)
	{
		this.arretes.remove(arrrete);
	}

	public String toString()
	{
		return "sommet{" + "id=" + this.id + ", x=" + this.x + ", y=" + this.y + ", point=" + this.point + ", couleur=" + this.couleur + '}';
	}
}
