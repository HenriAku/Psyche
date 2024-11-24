package psyche.map.metier;

import java.awt.*;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas Bouquet Jules, Rougeolle
 * 		Henri, Yachir Yanis cette classe permet de gérer les différentes couleurs
 */

public enum Couleur
{

	/*--------------*/
	/*  Enums       */
	/*--------------*/

	// Si l'utilisateur veut changer les couleurs pour son jeu il doit changer les couleurs ici

	JAUNE(255, 255, 0), BLEU_CLAIR(173, 216, 230), GRIS(128, 128, 128), VERT(34, 139, 34), ROUGE(255, 192, 203), MARRON(
		205, 133, 63), ROME(0, 0, 255);



	/*--------------*/
	/*  Données     */
	/*--------------*/

	private final int r;
	private final int v;
	private final int b;


	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur de la Couleur
	 *
	 * @param r
	 * 		entier de la couleur rouge
	 * @param v
	 * 		entier de la couleur verte
	 * @param b
	 * 		entier de la couleur bleu
	 */
	Couleur(int r, int v, int b)
	{
		this.r = r;
		this.v = v;
		this.b = b;
	}



	/*--------------*/
	/*      Get     */
	/*--------------*/

	public Color getColor()
	{
		return new Color(this.r, this.v, this.b);
	}



	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	public static Couleur valueOf(int ordinal)
	{
		return values()[ordinal];
	}

}
