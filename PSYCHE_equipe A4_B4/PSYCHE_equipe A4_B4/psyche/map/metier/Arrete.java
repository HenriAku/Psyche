package psyche.map.metier;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas Bouquet Jules, Rougeolle
 * 		Henri, Yachir Yanis La classe permet de gérer l'arrete
 */

public class Arrete
{

	/*--------------*/
	/* Données      */
	/*--------------*/

	private final Sommet sommetDepart;
	private final Sommet sommetArrivee;
	private int nbTroncons;



	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur d'arrête
	 *
	 * @param mineDepart
	 * 		une mine de départ
	 * @param mineArrivee
	 * 		une mine d'arrivé
	 * @param nbTroncons
	 * 		nombre de troncons
	 */
	private Arrete(Sommet mineDepart, Sommet mineArrivee, int nbTroncons)
	{
		this.sommetDepart = mineDepart;
		this.sommetArrivee = mineArrivee;
		this.nbTroncons = nbTroncons;
	}

	/**
	 * Factory du constructeur d'arrête pour vérifier certaines conditions si l'arrête peut être créé
	 *
	 * @param mineDepart
	 * 		une mine de départ
	 * @param mineArrivee
	 * 		une mine d'arrivé
	 * @param nombreTroncons
	 * 		nombre de troncons
	 * @return l'arrête si elle est créé sinon null
	 */
	public static Arrete creerArrete(Sommet mineDepart, Sommet mineArrivee, int nombreTroncons)
	{

		if (nombreTroncons < 0 || nombreTroncons > 10)
			return null;

		if (mineDepart == null || mineArrivee == null)
			return null;

		if (mineDepart.equals(mineArrivee))
			return null;

		return new Arrete(mineDepart, mineArrivee, nombreTroncons);
	}




	/*--------------*/
	/*     Get      */
	/*--------------*/

	public int getTroncons()
	{
		return this.nbTroncons;
	}

	public Sommet getDepart()
	{
		return this.sommetDepart;
	}

	public Sommet getArrivee()
	{
		return this.sommetArrivee;
	}



	/*--------------*/
	/*     Set      */
	/*--------------*/

	public void setTroncons(int troncons)
	{
		this.nbTroncons = troncons;
	}




	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	public String toString()
	{
		return "Arrête de " + this.nbTroncons + " troncons entre " + this.sommetDepart + " et " + this.sommetArrivee;
	}

}
