package psyche.jeu.metier;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément , Cauvin Pierre , Montagne Aubin , Delpech Nicolas
 * @version 1.0
 */

public class Jeton
{
	private final IRessource type;

	public Jeton(IRessource type)
	{
		this.type = type;
	}

	public IRessource getType()
	{
		return type;
	}

	public String toString()
	{
		return "Jeton {  type = " + type.toString() + " }";
	}
}
