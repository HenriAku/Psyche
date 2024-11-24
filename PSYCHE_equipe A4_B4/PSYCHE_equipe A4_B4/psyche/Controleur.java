package psyche;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas Bouquet Jules, Rougeolle
 * 		Henri, Yachir Yanis
 */

import psyche.jeu.metier.Joueur;
import psyche.jeu.metier.Metier;
import psyche.jeu.metier.Mine;
import psyche.map.metier.Couleur;
import psyche.map.metier.Sommet;
import psyche.vue.FrameMenu;
import psyche.scenario.ControleurScenario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controleur
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	private final FrameMenu FrameMenu;
	private ControleurScenario ctrlScen;


	/*--------------*/
	/*  Méthodes    */
	/*--------------*/

	/**
	 * Constructeur de Controleur sans scénario
	 */
	public Controleur()
	{
		this.FrameMenu = new FrameMenu(this);
	}

	/**
	 * Constructeur de Controleur avec scénario
	 */
	public Controleur(String fichierScenario)
	{
		this.ctrlScen = new ControleurScenario(this, fichierScenario);
		this.FrameMenu = new FrameMenu(this);
	}

	public static void main(String[] args)
	{
		if (args.length > 0)
		{
			new Controleur(args[0]);
		}
		else
		{
			new Controleur();
		}

	}

	public void setVisible()
	{
		this.FrameMenu.setVisible(true);
		File file = new File("../psyche/theme/Map.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				System.out.println(line);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/*-------------------------*/
	/* Méthodes de scénarios */
	/*-------------------------*/

	public List<Sommet> getSommets()
	{
		return this.FrameMenu.getSommets();
	}

	public Sommet getSommet(int i)
	{
		return this.FrameMenu.getSommet(i);
	}

	public void supprimerSommets(int id)
	{
		this.FrameMenu.supprimerSommet(id);
	}

	public void supprimerSommets()
	{
		this.FrameMenu.supprimerSommets();
	}

	public void supprimerArretes()
	{
		this.FrameMenu.supprimerArretes();
	}

	public void ajouterSommet(int x, int y, int point, Couleur c)
	{
		this.FrameMenu.ajouterSommet(x, y, point, c);
	}

	public void ajouterArrete(Sommet s1, Sommet s2, int t)
	{
		this.FrameMenu.ajouterArrete(s1, s2, t);
	}

	public List<Mine> getMines()
	{
		return this.FrameMenu.getMines();
	}

	public void ouvrirFenetreModifier()
	{
		this.FrameMenu.ouvrirFenetreModifier();

	}

	public void ouvrirFenetreJouer()
	{
		this.FrameMenu.ouvrirFenetreJouer();
	}

	public void fermerFenetreModifier()
	{
		this.FrameMenu.fermerFenetreModifier();
	}

	public void choisirCamp(int campChoisi)
	{
		this.FrameMenu.choisirCamp(campChoisi);
	}

	public void suppDonneesMap()
	{
		this.FrameMenu.suppDonneesMap();
	}

	public void suppDonneesJeu()
	{
		this.FrameMenu.suppDonneesJeu();
	}

	public void fermerJeu()
	{
		this.FrameMenu.fermerFenetreJouer();
	}

	public void fermerJoueur()
	{
		this.FrameMenu.fermerJoueur();
	}

	public void fermerMap()
	{
		this.FrameMenu.fermerMap();
	}

	// public ArrayList<Joueur> getJoueurs() {
	// System.out.println("" + this.ctrlScen.getJoueurs());
	// return this.ctrlScen.getJoueurs();
	// }

	// public void setJoueurs(ArrayList<Joueur> joueurs) {
	// this.FrameMenu.setJoueurs(joueurs);
	// }

	public Metier getMetierJeu()
	{
		return this.FrameMenu.getMetierJeu();
	}

	public void setMetier(Metier metier)
	{
		this.FrameMenu.setMetier(metier);
	}

	public void ouvrirFenetreJouer(Metier metier)
	{
		this.FrameMenu.ouvrirFenetreJouer(metier);
	}

	public void possederMine(Mine mine)
	{
		this.FrameMenu.possederMine(mine);
	}

	public void verifierFinPartie()
	{
		this.FrameMenu.verifierFinPartie();
	}

	public ControleurScenario getCtrlScen() {
		return this.ctrlScen;
	}

}