package psyche.map.metier;

import javax.swing.*;
import java.io.*;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas Bouquet Jules, Rougeolle
 * 		Henri, Yachir Yanis La classe permet de gérer la gestion des fichiers
 */

public class GestionFichier
{

	/*--------------*/
	/* Données      */
	/*--------------*/

	private final Metier metier;
	private String fichierCharger;



	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur de gestion de fichier
	 *
	 * @param metier
	 * 		la class métier
	 */
	public GestionFichier(Metier metier)
	{
		this.metier = metier;
		this.fichierCharger = "../psyche/theme/Map.txt";
	}


	/*--------------*/
	/*     Get      */
	/*--------------*/

	public String getFichierCharger()
	{
		return this.fichierCharger;
	}



	/*--------------*/
	/*     Set      */
	/*--------------*/

	public void setFichierCharger(String path)
	{
		this.fichierCharger = path;
		this.chargerSommetsArretes(path);
	}

	private void setNouveauFichier(String absolutePath)
	{
		this.fichierCharger = absolutePath;
		this.sauvegarderSommetsArretes(absolutePath);
	}



	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	/**
	 * Enregistre les données actuelles dans le fichier chargé au demarrage
	 */
	public void enregistrer()
	{
		this.sauvegarderSommetsArretes(this.getFichierCharger());
	}

	/**
	 * Ouvre la fenêtre d'enregistrement de fichiers
	 */
	public void enregistrerSous()
	{
		File selectedFile;
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION)
		{
			selectedFile = fileChooser.getSelectedFile();
			this.setNouveauFichier(selectedFile.getAbsolutePath());
		}
	}

	/**
	 * Methode permettant de sauvegarder les information des sommets et des arrêtes dans le fichier
	 *
	 * @param path
	 * 		String le fichier passé en paramètre
	 */
	private void sauvegarderSommetsArretes(String path)
	{
		if (path == null || path.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Veuillez enregistrer dans votre fichier", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try (PrintWriter writer = new PrintWriter(new FileOutputStream(path, false)))
		{
			StringBuilder sb = new StringBuilder();
			String sommetLigne;
			String arreteLigne;

			sb.append("[Sommets] :\n");
			for (Sommet sommet : this.metier.getSommets())
			{
				sommetLigne = String.format("%-3d, %-4s, %-5d, %-5d, %-10s, %-5d\n", sommet.getId(), sommet.getNom(),
						sommet.getX(), sommet.getY(), sommet.getCouleur().name(), sommet.getPoint());
				sb.append(sommetLigne);
			}

			sb.append("---------------------------------\n");
			sb.append("[Arrêtes] :\n");

			for (Arrete arrete : this.metier.getArretes())
			{
				arreteLigne = String.format("%-4d, %-4d, %-5d\n", arrete.getDepart().getId(),
						arrete.getArrivee().getId(), arrete.getTroncons());
				sb.append(arreteLigne);
			}

			writer.println(sb);
		} catch (IOException i)
		{
			i.printStackTrace();
		}
	}

	/**
	 * Methode permettant de charger les informations des sommets et des arrêtes dans le fichier passé en paramètre
	 *
	 * @param path
	 * 		String le chemin du fichier
	 */
	private void chargerSommetsArretes(String path)
	{
		File fichier;
		String ligne;
		boolean lireSommets = false;
		boolean lireArretes = false;

		if (path == null || path.isEmpty())
		{
			return;
		}

		fichier = new File(path);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fichier))))
		{
			if (!this.metier.getArretes().isEmpty() && !this.metier.getSommets().isEmpty())
			{
				this.metier.getSommets().clear();
				this.metier.getArretes().clear();
				this.metier.resetId();
			}

			while ((ligne = reader.readLine()) != null)
			{
				if (ligne.equals("[Sommets] :"))
				{
					lireSommets = true;
					lireArretes = false;
					continue;
				}
				else if (ligne.equals("[Arrêtes] :"))
				{
					lireArretes = true;
					lireSommets = false;
					continue;
				}

				if (lireSommets)
				{

					if (ligne.equals("---------------------------------"))
						continue;

					String[] sommet = ligne.split(",");
					if (sommet.length == 6)
					{
						try
						{
							int id = Integer.parseInt(sommet[0].trim());
							String nom = sommet[1].trim();
							int x = Integer.parseInt(sommet[2].trim());
							int y = Integer.parseInt(sommet[3].trim());
							Couleur couleur = Couleur.valueOf(sommet[4].trim());
							int point = Integer.parseInt(sommet[5].trim());

							this.metier.ajouterSommet(x, y, point, couleur);

						} catch (NumberFormatException e)
						{
							System.err.println("Erreur de format pour les coordonnées : " + ligne);
						}
					}
				}
				else if (lireArretes)
				{

					if (ligne.equals("---------------------------------"))
						continue;

					String[] arrete = ligne.split(",");
					if (arrete.length == 3)
					{
						String arreteDepart = arrete[0].trim();
						String arreteArrivee = arrete[1].trim();

						try
						{
							Sommet depart = this.metier.getSommet(Integer.parseInt(arreteDepart));
							Sommet arrivee = this.metier.getSommet(Integer.parseInt(arreteArrivee));
							int nombreTroncons = Integer.parseInt(arrete[2].trim());

							System.out.println(depart + " " + arrivee + " " + nombreTroncons);
							this.metier.ajouterArrete(depart, arrivee, nombreTroncons);

						} catch (NumberFormatException e)
						{
							System.err.println("Erreur de format pour le nombre de tronçons : " + ligne);
						}
					}
				}
			}
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}
