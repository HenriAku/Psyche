package psyche.jeu.metier;

public enum Minerai implements IRessource
{
	Aluminium("Al", "AL.png"),
	Argent("Ag", "AG.png"),
	Or("Au", "AU.png"),
	Cobalt("Co", "CO.png"),
	Fer("Fe","FE.png"),
	Nickel("Ni", "NI.png"),
	Platine("Pt", "PT.png"),
	Titane("Ti", "TI.png");

	private final String nom;
	private final String lienMinerai;

	Minerai(String nom, String lien)
	{
		this.nom = nom;
		this.lienMinerai = lien;
	}

	public String getLienImage()
	{
		return this.lienMinerai;
	}

	public String getNom()
	{
		return this.nom;
	}

}
