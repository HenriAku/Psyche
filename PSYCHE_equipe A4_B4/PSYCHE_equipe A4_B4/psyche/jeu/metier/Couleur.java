package psyche.jeu.metier;

import java.awt.*;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas Bouquet Jules, Rougeolle
 * 		Henri, Yachir Yanis
 */

public enum Couleur
{

	/*--------------*/
	/*  Enums       */
	/*--------------*/

	JAUNE(255, 255, 0, "Mine_Jaune"), BLEU_CLAIR(173, 216, 230, "Mine_Bleu"), GRIS(128, 128, 128, "Mine_Gris"), VERT(34,
		139, 34, "Mine_Vert"), ROUGE(255, 192, 203, "Mine_Rouge"), MARRON(205, 133, 63, "Mine_Marron"), ROME(0, 0, 255,
		"NR");


	/*--------------*/
	/*  Données     */
	/*--------------*/

	private final int r;
	private final int v;
	private final int b;
	private final String lienImage;


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
	Couleur(int r, int v, int b, String lienImage)
	{
		this.r = r;
		this.v = v;
		this.b = b;
		this.lienImage = lienImage;
	}



	/*--------------*/
	/*      Get     */
	/*--------------*/

	public Color getColor()
	{
		return new Color(this.r, this.v, this.b);
	}

	public String getSymbole()
	{
		return this.name();
	}

	public static int getNbCouleur()
	{
		return values().length;
	}

	public String getLienImage()
	{
		return this.lienImage;
	}


	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	public static Couleur valueOf(int ordinal)
	{
		return values()[ordinal];
	}

}
