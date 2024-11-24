//Bon

package psyche.scenario;

import psyche.Controleur;
import psyche.jeu.metier.Metier;
import psyche.map.metier.Sommet;
import psyche.jeu.metier.Route;
import psyche.jeu.metier.Joueur;
import psyche.map.metier.Couleur;
import psyche.jeu.metier.Mine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ControleurScenario
{
	/*
	 * Idée : avoir la liste des actions ligne par ligne dans scenario_1.run OU
	 * scenario_2.run
	 * Avoir aussi un int index pour savoir où on en est.
	 * Bouton suivant : Avancer l'index de 1 et effectuer l'action. / Recharger tout
	 * en ayant fait l'action suivante de notre index
	 * Bouton précédent : Reculer l'index de 1, recharger la carte de 0 et effectuer
	 * toutes les actions précedentes
	 *
	 *
	 * https://diw.iut.univ-lehavre.fr/pedago/info1/SAE_2_01/ressources/exercice_6/
	 * utilite_scenario.pdf
	 */

	private final Controleur ctrl;
	private int etape;
	private String fichierScenario;
	private final FrameScenario frameScenario;
	private Metier metier;

	private int[][] tabMinerai;

	public ControleurScenario(Controleur ctrl, String fichierScenario)
	{
		this.ctrl = ctrl;
		this.frameScenario = new FrameScenario(this);
		this.fichierScenario = fichierScenario;



		try
		{
			BufferedReader reader;
			if(this.fichierScenario.equals("scenario_2.run"))
			{
				reader = new BufferedReader(new FileReader("../psyche/scenario/" + "Minerais_2.txt"));
			}
			else
			{

				if(this.fichierScenario.equals("scenario_3.run"))
				{
					reader = new BufferedReader(new FileReader("../psyche/scenario/" + "Minerais_3.txt"));
				}
				else
					reader = new BufferedReader(new FileReader("../psyche/scenario/" + "Minerais.txt"));
			}
			int lines = 0;
			while (reader.readLine() != null)
				lines++;
			reader.close();

			this.tabMinerai = new int[lines][2]; // Initialize tabMinerai with the number of lines in the file

			if(this.fichierScenario.equals("scenario_2.run"))
			{
				reader = new BufferedReader(new FileReader("../psyche/scenario/" + "Minerais_2.txt"));
			}
			else
			{

				if(this.fichierScenario.equals("scenario_3.run"))
				{
					reader = new BufferedReader(new FileReader("../psyche/scenario/" + "Minerais_3.txt"));
				}
				else
					reader = new BufferedReader(new FileReader("../psyche/scenario/" + "Minerais.txt"));
			}
			String ligne;
			int numLigne = 0;
			while ((ligne = reader.readLine()) != null)
			{
				String[] parts = ligne.split(",");
				this.tabMinerai[numLigne][0] = Integer.parseInt(parts[0]);
				this.tabMinerai[numLigne][1] = Integer.parseInt(parts[1]);
				numLigne++;
			}
			reader.close();
		} catch (IOException e)
		{
			System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
		}

		//Print
		//for (int i = 0; i < this.tabMinerai.length ; i++) {
		//	for (int j = 0; j < this.tabMinerai[i].length; j++) {
		//		System.out.println(this.tabMinerai[i][j]);
		//	}
		//}

		this.metier = new Metier(this.tabMinerai);
	}

	public void effectuerAction(int etape)
	{
		System.out.println("Metier 4 : " + this.metier);

		try
		{
			this.ctrl.suppDonneesMap();
		} catch (Exception e)
		{
			//System.out.println("Aucune donnée à supprimer");
		}

		try
		{
			this.ctrl.suppDonneesJeu();
		} catch (Exception e)
		{
			//System.out.println("Aucune donnée à supprimer" + e);
		}

		try
		{
			this.ctrl.fermerMap();
		} catch (Exception e)
		{
			//System.out.println("Aucune fenêtre de map à supprimer");
		}

		try
		{
			this.metier = this.ctrl.getMetierJeu();
			this.ctrl.fermerJeu();
		} catch (Exception e)
		{
			//System.out.println("Aucune fenêtre de jeu à supprimer");
		}

		// try {
		// this.ctrl.fermerCamps();
		// } catch (Exception e) {
		// System.out.println("Erreur lors de la fermeture de la fenêtre du choix des
		// camps");
		// }

		try
		{
			//Joueur joueur1temp = this.metier.getJoueur1();
			//Joueur joueur2temp = this.metier.getJoueur2();
			int numJoueurTour = this.metier.getJoueurActuel().getNumJoueur();
			this.ctrl.fermerJoueur();
			if (numJoueurTour == 2)
				this.metier.changerTour();
			//this.metier.setJoueur1(joueur1temp);
			//this.metier.setJoueur1(joueur2temp);
		} catch (Exception e)
		{
			//System.out.println("Erreur lors de la fermeture de la fenêtre des joueurs");
		}

		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("../psyche/scenario/" + fichierScenario));
			String ligne;
			int numLigne = 0;
			while ((ligne = reader.readLine()) != null && numLigne < etape)
			{
				numLigne++;
				traiterLigne(ligne);
			}
			reader.close();
		} catch (IOException e)
		{
			System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
		}
	}

	private void traiterLigne(String ligne)
	{
		System.out.println("Metier un milliard " + this.metier);
		// Suppression des espaces blancs au début et à la fin de la ligne
		ligne = ligne.trim();

		// Vérification si la ligne n'est pas vide
		if (!ligne.isEmpty())
		{
			String[] parts = ligne.split("\\s+"); // Divise la ligne en mots en fonction des espaces
			if (parts.length > 0)
			{
				String command = parts[0].toLowerCase(); // Convertir en minuscules pour la comparaison
				switch (command)
				{
				case "ecrire":
					if (parts.length > 1)
					{
						int number = Integer.parseInt(parts[1]);
						System.out.println(number);
					}
					break;
				case "ouvrir":
					if (parts.length > 1)
					{
						String action = parts[1];
						switch (action)
						{
						case "modifier":
							this.ctrl.ouvrirFenetreModifier();
							break;
						case "jouer":
							System.out.println("Ouverture de la fenêtre de jeu. " + this.metier);
							this.ctrl.ouvrirFenetreJouer(this.metier);
							System.out.println("Fenêtre de jeu ouverte " + this.metier);
							break;
						}
					}
					break;
				case "selectionner":
					if (parts.length > 1)
					{
						String camp = parts[1];

						switch (camp)
						{
						case "SA":
							this.ctrl.choisirCamp(0);
							break;
						case "CS":
							this.ctrl.choisirCamp(1);
							break;
						}
					}
					break;
				case "fermer":
					if (parts.length > 1)
					{
						String action = parts[1];
						switch (action)
						{
						case "modifier":
							this.ctrl.fermerMap();
							break;
						}
					}
					break;
				case "creer":
					if (parts[1].equals("mine"))
					{
						this.ctrl.ajouterSommet(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
								Integer.parseInt(parts[4]), Couleur.valueOf(parts[5]));
					}
					else
					{
						Sommet s1 = null;
						Sommet s2 = null;

						for (int i = 0; i < this.ctrl.getSommets().size(); i++)
						{
							if (this.ctrl.getSommets().get(i).getX() == Integer.parseInt(
									parts[2]) && this.ctrl.getSommets().get(i).getY() == Integer.parseInt(
									parts[3]) && this.ctrl.getSommets().get(i).getPoint() == Integer.parseInt(
									parts[4]) && this.ctrl.getSommets().get(i).getCouleur() == Couleur.valueOf(
									parts[5]))
							{
								s1 = this.ctrl.getSommets().get(i);
							}

							if (this.ctrl.getSommets().get(i).getX() == Integer.parseInt(
									parts[6]) && this.ctrl.getSommets().get(i).getY() == Integer.parseInt(
									parts[7]) && this.ctrl.getSommets().get(i).getPoint() == Integer.parseInt(
									parts[8]) && this.ctrl.getSommets().get(i).getCouleur() == Couleur.valueOf(
									parts[9]))
							{
								s2 = this.ctrl.getSommets().get(i);
							}
						}

						if (s1 != null && s2 != null)
						{
							this.ctrl.ajouterArrete(s1, s2, Integer.parseInt(parts[10]));
						}
						else
						{
							System.err.println("Erreur: s1 ou s2 est null - s1=" + s1 + ", s2=" + s2);
						}
					}
					break;

				case "modifier":
					Sommet s1 = null;

					if (parts[1].equals("mine"))
					{

						if (parts[2].equals("posX"))
						{
							for (int i = 0; i < this.ctrl.getSommets().size(); i++)
							{
								if (this.ctrl.getSommets().get(i).getX() == Integer.parseInt(
										parts[3]) && this.ctrl.getSommets().get(i).getY() == Integer.parseInt(
										parts[4]) && this.ctrl.getSommets().get(i).getPoint() == Integer.parseInt(
										parts[5]) && this.ctrl.getSommets().get(i).getCouleur() == Couleur.valueOf(
										parts[6]))
								{
									s1 = this.ctrl.getSommets().get(i);
								}
							}

							s1.setX(Integer.parseInt(parts[7]));
						}

						if (parts[2].equals("posY"))
						{
							for (int i = 0; i < this.ctrl.getSommets().size(); i++)
							{
								if (this.ctrl.getSommets().get(i).getX() == Integer.parseInt(
										parts[3]) && this.ctrl.getSommets().get(i).getY() == Integer.parseInt(
										parts[4]) && this.ctrl.getSommets().get(i).getPoint() == Integer.parseInt(
										parts[5]) && this.ctrl.getSommets().get(i).getCouleur() == Couleur.valueOf(
										parts[6]))
								{
									s1 = this.ctrl.getSommets().get(i);
								}
							}

							s1.setY(Integer.parseInt(parts[7]));
						}
					}
					break;

				case "prendre":
					Mine m1 = null;
					Mine m2 = null;

					if (parts.length > 2)
					{

						for (int i = 0; i < this.ctrl.getMines().size(); i++)
						{
							if (this.ctrl.getMines().get(i).getPoint() == Integer.parseInt(
									parts[1]) && this.ctrl.getMines().get(i)
									.getCouleur() == psyche.jeu.metier.Couleur.valueOf(parts[2]))
							{
								m1 = this.ctrl.getMines().get(i);
							}

							if (this.ctrl.getMines().get(i).getPoint() == Integer.parseInt(
									parts[3]) && this.ctrl.getMines().get(i)
									.getCouleur() == psyche.jeu.metier.Couleur.valueOf(parts[4]))
							{
								m2 = this.ctrl.getMines().get(i);
							}
						}

						if (m1 != null && m2 != null)
						{
							this.ctrl.possederMine(m2);
							this.metier.setProprietaire(this.metier.getRoute(m1, m2), metier.getJoueurActuel());
							this.metier.getJoueurActuel().setNbJetonPossession(this.metier.getRoute(m1, m2).getTroncons());

							this.metier.changerTour();
						}
						else
						{
							System.err.println("Erreur: m1 ou m2 est null - m1=" + m1 + ", m2=" + m2);
						}
					}

					this.ctrl.verifierFinPartie();

					break;

				case "generer":
					this.metier.setFichierCharger("../psyche/theme/MapSave.txt");
					break;

				default:
					System.out.println("Commande non reconnue : " + command);
					break;
				}
			}
		}
	}

	public void setFichierScenario(String fichier)
	{
		this.fichierScenario = fichier;
	}

	// public ArrayList<Joueur> getJoueurs() {
	// return this.metierScenario.getJoueurs();
	// }

	public Metier getMetier()
	{
		return this.metier;
	}
}
