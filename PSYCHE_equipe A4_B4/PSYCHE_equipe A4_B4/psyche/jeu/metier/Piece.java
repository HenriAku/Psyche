package psyche.jeu.metier;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément , Cauvin Pierre , Montagne Aubin , Delpech Nicolas
 * @version 1.0
 */

public enum Piece implements IRessource
{
	NR(1);

	private final int valeur;

	Piece(int valeur)
	{
		this.valeur = valeur;
	}

	public int getValeur()
	{
		return this.valeur;
	}

	public String toString()
	{
		return this.name();
	}
}
