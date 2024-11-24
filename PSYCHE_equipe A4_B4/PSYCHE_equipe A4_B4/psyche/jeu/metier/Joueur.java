package psyche.jeu.metier;

import psyche.jeu.vue.PanelJoueur;

import java.util.ArrayList;
import java.util.List;

public class Joueur
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	// Nombre de case total pour les jetons
	private static final int NB_PIECE_MAX = 8;
	private static final int NB_LIGNE_MAX = 4;
	private static final int NB_COLONNE_MAX = 8;

	private static int nbJoueur = 0;
	private final int numJoueur;

	private final List<Mine> minesObtenues;
	private final List<Route> routesJoueur;
	private List<Jeton> tabJetonPresent;

	private int nbPiece;
	private int score;
	private int nbJetonPossession;
	private String nom;

	private final int scoreCol;
	private final int scoreLig;

	private boolean estSonTour;

	private static final int[] TABLEAU_SCORES_PIECES = { 0, 0, 4, 9, 16, 25, 36, 49, 64 };
	private static final int[] TABLEAU_SCORES_LIGNE = { 0, 0, 2, 5, 9, 14, 20, 32, 46 };
	private static final int[] TABLEAU_SCORES_COLONNE = { 0, 0, 2, 10, 20 };


	/*--------------*/
	/*  Méthodes    */
	/*--------------*/

	/**
	 * Constructeur de Joueur
	 */
	public Joueur(String nom)
	{

		this.nbPiece = 0;
		this.nom = nom;
		this.score = 0;
		this.nbJetonPossession = 25;
		this.minesObtenues = new ArrayList<Mine>();
		this.tabJetonPresent = new ArrayList<Jeton>();
		this.routesJoueur = new ArrayList<Route>();
		this.estSonTour = false;

		this.numJoueur = ++Joueur.nbJoueur;

		this.scoreCol = 0;
		this.scoreLig = 0;
	}

	/*----------*/
	/*   Get    */
	/*----------*/

	public int getScore()
	{
		return this.score;
	}

	public List<Mine> getMinesObtenues()
	{
		return this.minesObtenues;
	}

	public List<Jeton> getJetonObtenues()
	{
		return this.tabJetonPresent;
	}

	public String getNom()
	{
		return this.nom;
	}

	public int getNumJoueur()
	{
		return this.numJoueur;
	}

	public int getNbJetonPossession()
	{
		return this.nbJetonPossession;
	}


	/*-------------------------*/
	/*   Set,     variateurs   */
	/*-------------------------*/

	/**
	 * Ajoute une mine à la liste des mines obtenues du joueur
	 *
	 * @param mine
	 * 		La mine à ajouter
	 */
	public void ajouterMine(Mine mine)
	{
		this.minesObtenues.add(mine);
	}

	public void ajouterRoute(Route route)
	{
		this.routesJoueur.add(route);
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public void setNbJetonPossession(int nbTroncons)
	{
		this.nbJetonPossession -= nbTroncons;
	}



	/*---------------------*/
	/*   Autres méthodes   */
	/*---------------------*/

	public boolean estSonTour()
	{
		return this.estSonTour;
	}

	/**
	 * La méthode ajoute la ressource au plateau, tout en vérifiant si elle peut y être ajouté ou non.
	 *
	 * @param ressource
	 * 		qui est soit une épice, soit une piece
	 * @return un boolean pour savoir si la ressource peut être ajouté
	 */
	public boolean ajouterRessource(Jeton ressource)
	{
		int valPiece = 0;

		if (ressource == null)
		{
			return false;
		}

		for (int i = 0; i < this.getListJeton().size() - 1; i++)
		{
			//			if ( this.getListJeton().get(i) != null )
			//				System.out.println("\n\n\n LE MINERAI AJOUTER" + ((Minerai) this.getListJeton().get(i).getType()).getNom() + "\n\n\n");
		}

		if (ressource.getType() instanceof Minerai)
		{
			if (this.compteurJetons(this.tabJetonPresent, ressource) == 0)
			{
				if (this.getNbMineraisDifferentPlateau() < Joueur.NB_COLONNE_MAX)
				{
					this.tabJetonPresent.add(ressource);
					this.tabJetonPresent = this.trieTab(this.tabJetonPresent);

					return true;
				}
			}
			else
			{

				if (this.compteurJetons(this.tabJetonPresent, ressource) < Joueur.NB_LIGNE_MAX)
				{
					this.tabJetonPresent.add(ressource);

					this.tabJetonPresent = this.trieTab(this.tabJetonPresent);

					return true;
				}
			}
		}

		else
		{
			if (ressource.getType() instanceof Piece piece)
			{

				switch (piece)
				{
				case NR ->
				{
					valPiece = 1;
				}
				}

				if (nbPiece + valPiece <= Joueur.NB_PIECE_MAX)
				{
					this.nbPiece += valPiece;
					return true;
				}
			}
		}

		return false;
	}

	public int compteurJetons(List<Jeton> listeJeton, Jeton jetonRechercher)
	{

		int compteurDoublons = 0;
		for (Jeton jeton : listeJeton)
		{
			if (jeton != null)
			{
				if (jeton.getType().equals(jetonRechercher.getType()))
				{
					compteurDoublons++;
				}
			}
		}
		return compteurDoublons;
	}

	public int getNbMineraisDifferentPlateau()
	{
		int nbEpicesDifferent = 0;
		List<Minerai> listeMinerais = new ArrayList<>();
		for (Jeton jeton : this.tabJetonPresent)
		{
			if (jeton != null)
			{
				Minerai minerai = (Minerai) jeton.getType();
				if (!listeMinerais.contains(minerai))
				{
					listeMinerais.add(minerai);
					nbEpicesDifferent++;
				}
			}

		}
		return nbEpicesDifferent;
	}

	public int score()
	{
		int scorePiece = calculerScorePiece();
		int scoreMinerai = calculerPointsColonnes() + calculerPointsLignes();
		int scoreMine = calculerScoreMinesTotale();

		this.score = scorePiece + scoreMinerai + scoreMine;

		return this.score;
	}

	//Les Mines sont divisées en plusieurs couleurs. Pour chaque couleur les joueurs gagnent la valeur de la Mine la plus
	//élevée.
	public int calculerScoreMines(Couleur couleur)
	{
		int scoreMines = 0;
		for (Mine mine : this.minesObtenues)
		{
			if (mine.getCouleur() == couleur)
			{
				scoreMines = Math.max(scoreMines, mine.getPoint());
			}
		}
		return scoreMines;
	}

	public int calculerScoreMinesTotale()
	{
		int scoreTotal = 0;
		for (Couleur c : Couleur.values())
		{
			scoreTotal += calculerScoreMines(c);
		}
		return scoreTotal;
	}

	// Calcul du score des pièces
	public int calculerScorePiece()
	{
		return TABLEAU_SCORES_PIECES[this.nbPiece];
	}

	// Calcul des points des colonnes
	public int calculerPointsColonnes()
	{
		List<String> tabTestJetonPresent = new ArrayList<>();
		List<Integer> tabNbElementJeton = new ArrayList<>();

		int[] scoreCols = new int[9];

		int score = 0;

		for (int nbElement = NB_LIGNE_MAX; nbElement > 0; nbElement--)
		{
			for (Jeton jeton : this.tabJetonPresent)
			{
				if (jeton != null && this.compteurJetons(this.tabJetonPresent,
						jeton) == nbElement && !tabTestJetonPresent.contains(((Minerai) jeton.getType()).getNom()))
				{
					tabTestJetonPresent.add(((Minerai) jeton.getType()).getNom());
					tabNbElementJeton.add(nbElement);
				}
			}
		}

		for (int cpt = 0; cpt < tabNbElementJeton.size(); cpt++)
		{
			score += scoreCols[cpt + 1] += TABLEAU_SCORES_COLONNE[tabNbElementJeton.get(cpt)];
		}

		return score;
	}

	// Calcul des points des lignes
	public int calculerPointsLignes()
	{

		List<String> tabCompteLigneEtage1 = new ArrayList<>();
		List<String> tabCompteLigneEtage2 = new ArrayList<>();
		List<String> tabCompteLigneEtage3 = new ArrayList<>();
		List<String> tabCompteLigneEtage4 = new ArrayList<>();

		for (Jeton jeton : this.tabJetonPresent)
		{
			if (jeton != null)
			{
				String symbole = ((Minerai) jeton.getType()).getNom();
				if (tabCompteLigneEtage1.contains(symbole))
				{
					if (tabCompteLigneEtage2.contains(symbole))
					{
						if (tabCompteLigneEtage3.contains(symbole))
						{
							tabCompteLigneEtage4.add(symbole);
						}
						else
						{
							tabCompteLigneEtage3.add(symbole);
						}
					}
					else
					{
						tabCompteLigneEtage2.add(symbole);
					}
				}
				else
				{
					tabCompteLigneEtage1.add(symbole);
				}
			}
		}

		int scoreLig1 = TABLEAU_SCORES_LIGNE[tabCompteLigneEtage1.size()];
		int scoreLig2 = TABLEAU_SCORES_LIGNE[tabCompteLigneEtage2.size()];
		int scoreLig3 = TABLEAU_SCORES_LIGNE[tabCompteLigneEtage3.size()];
		int scoreLig4 = TABLEAU_SCORES_LIGNE[tabCompteLigneEtage4.size()];

		return scoreLig1 + scoreLig2 + scoreLig3 + scoreLig4;

	}

	public List<Jeton> trieTab(List<Jeton> listeJeton)
	{
		// Liste finale trié
		List<Jeton> tabJetonPresentTriee = new ArrayList<>();

		// Liste des Epice dans l'ordre de la plus grande à la plus petite
		List<Jeton> tabJetonOrdreDecroissant = new ArrayList<>();

		// Liste des jetons déjà vérifier utilile pour ne pas refaire plusieurs fois les mêmes épices
		List<String> tabTestJetonPresent = new ArrayList<>();

		// liste qui compléte tabJetonOrdreDecroissant pour savoir combien de fois l'épice y est
		List<Integer> tabNbElementJeton = new ArrayList<>();

		int nbElementDansTabElement = 0;
		int nbElementNull = 0;

		// Rangement dans l'ordre Decroissant des épices dans tabJetonOrdreDecroissant.
		// Ajout du nombre de fois pour laquel l'épice est présente dans tabNbElementJeton
		// Ajout des épices déjà vu dans tabTestJetonPresent Obligé car les épice sont différentes mais pas leur nom

		for (int nbElement = Joueur.NB_LIGNE_MAX; nbElement > 0; nbElement--)
		{
			for (Jeton jeton : listeJeton)
			{
				if (jeton != null && this.compteurJetons(listeJeton,
						jeton) == nbElement && !tabTestJetonPresent.contains(((Minerai) jeton.getType()).getNom()))
				{
					tabTestJetonPresent.add(((Minerai) jeton.getType()).getNom());
					tabJetonOrdreDecroissant.add(jeton);
					tabNbElementJeton.add(nbElement);
				}
			}
		}

		// Pour chaque épice trié les complété avec le nombre d'épice qu'il y a en tous, pis mettre des null si nécéssaire
		for (int cpt = 0; cpt < tabJetonOrdreDecroissant.size(); cpt++)
		{
			nbElementDansTabElement = tabNbElementJeton.get(cpt);
			nbElementNull = 4 - nbElementDansTabElement;

			while (nbElementDansTabElement > 0)
			{
				tabJetonPresentTriee.add(tabJetonOrdreDecroissant.get(cpt));

				nbElementDansTabElement--;
			}

			while (nbElementNull > 0)
			{
				tabJetonPresentTriee.add(null);

				nbElementNull--;
			}

		}

		return tabJetonPresentTriee;
	}

	public List<Jeton> getListJeton()
	{
		return this.tabJetonPresent;
	}

	public int getNbPiece()
	{
		return this.nbPiece;
	}

	/**
	 * Renvoie un affichage contenant les informations du joueur.
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int cptLig = 2; cptLig >= 0; cptLig--)
		{
			sb.append("+-----+-----+-----+-----+-----+\n|");
			for (int cptCol = 0; cptCol < 5; cptCol++)
			{
				int index = cptCol * 3 + cptLig;
				if (index < tabJetonPresent.size() && tabJetonPresent.get(index) != null)
				{
					if (tabJetonPresent.get(index).getType() instanceof Minerai)
					{
						sb.append(" ").append(((Minerai) tabJetonPresent.get(index).getType()).getNom()).append(" |");
					}
					else if (tabJetonPresent.get(index).getType() instanceof Piece)
					{
						sb.append(" ").append(((Piece) tabJetonPresent.get(index).getType()).getValeur()).append(" |");
					}
				}
				else
				{
					sb.append("     |");
				}
			}
			sb.append("\n");
		}
		sb.append("+-----+-----+-----+-----+-----+\n").append(nbPiece).append(" pièces\n");
		return sb.toString();
	}

	public boolean PossedeMine(Mine mine)
	{

		for (Mine mineTraitee : this.minesObtenues)
		{
			if (mine == mineTraitee)
				return true;
		}
		return false;
	}

	public int getScoreLig()
	{
		return this.scoreLig;
	}

	public int getScoreCol()
	{
		return this.scoreCol;
	}

	public void setTour(boolean b)
	{
		this.estSonTour = b;
	}

	// Scénario
	public static void resetNbJoueur()
	{
		Joueur.nbJoueur = 0;
	}
}