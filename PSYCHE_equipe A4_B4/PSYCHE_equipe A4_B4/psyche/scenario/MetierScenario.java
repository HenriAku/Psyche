package psyche.scenario;

import psyche.jeu.metier.Metier;

public class MetierScenario
{

	private final ControleurScenario ctrlScen;

	private Metier metierTemp;

	public MetierScenario(ControleurScenario ctrl)
	{
		this.ctrlScen = ctrl;
	}

	public Metier getMetier()
	{
		System.out.println(this.metierTemp);
		return this.metierTemp;
	}

	public void setMetier(Metier metier)
	{
		this.metierTemp = metier;
	}
}
