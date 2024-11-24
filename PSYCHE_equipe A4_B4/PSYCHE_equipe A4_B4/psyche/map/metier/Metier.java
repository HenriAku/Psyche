package psyche.map.metier;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas Bouquet Jules, Rougeolle
 * 		Henri, Yachir Yanis La classe Metier gère les opérations liées aux sommets et aux arretes.
 */
public class Metier
{
	/*--------------*/
	/*  Données     */
	/*--------------*/

	GestionFichier gestionFichier = new GestionFichier(this);

	private final List<Sommet> sommets;
	private final List<Arrete> arretes;



	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur de la classe Metier. Initialise les listes de sommets et d'arretes.
	 */
	public Metier()
	{
		this.sommets = new ArrayList<>();
		this.arretes = new ArrayList<>();

		this.setFichierCharger(getFicherCharger());
	}



	/*--------------*/
	/*     Get      */
	/*--------------*/

	public Sommet getSommet(int id)
	{
		for (Sommet sommet : this.sommets)
			if (sommet.getId() == id)
				return sommet;

		return null;
	}

	public List<Sommet> getSommets()
	{
		return this.sommets;
	}

	public List<Arrete> getArretes()
	{
		return this.arretes;
	}



	/*--------------*/
	/*     Set      */
	/*--------------*/


	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	/**
	 * Ajoute un sommet dans la list de sommets.
	 *
	 * @param x
	 * 		coordonnée X du sommet.
	 * @param y
	 * 		coordonnée Y du sommet.
	 * @param point
	 * 		score du sommet.
	 * @param couleur
	 * 		couleur du sommet
	 * @return le Sommet si l'ajout du sommet est réussi, null si les coordonnées sont invalides.
	 */
	public Sommet ajouterSommet(int x, int y, int point, Couleur couleur)
	{

		if (x < 0 || x > 1000 || y < 0 || y > 800)
		{
			JOptionPane.showMessageDialog(null, "Les coordonnées ne sont pas valides", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

		Sommet sommet = Sommet.creerSommet(x, y, point, couleur);
		this.sommets.add(sommet);

		this.enregistrer();
		return sommet;
	}

	/**
	 * Modifie les coordonnées d'une sommet existante.
	 *
	 * @param x
	 * 		La nouvelle coordonnée X du sommet.
	 * @param y
	 * 		La nouvelle coordonnée Y du sommet.
	 * @return true si la modification a réussi, false si les coordonnées sont invalides ou si le sommet n'existe pas.
	 */
	public boolean modifierSommet(int x, int y, Couleur couleur, int point, Sommet sommet)
	{
		if (x < 0 || x > 1000 || y < 0 || y > 800)
			return false;

		if (sommet == null)
			return false;

		sommet.setX(x);
		sommet.setY(y);
		sommet.setCouleur(couleur);
		sommet.setPoint(point);
		sommet.setNom(couleur.name().substring(0, 1) + point);

		return true;
	}

	/**
	 * Supprime le sommet existante.
	 *
	 * @param id
	 * 		L'ID du sommet à supprimer.
	 * @return true si le sommet a été supprimée, false si l'ID est invalide ou si la liste des sommets est vide.
	 */
	public boolean supprimerSommet(int id)
	{
		if (this.sommets.isEmpty() || id < 0 || id >= this.sommets.size())
			return false;

		Sommet sommetSupp = this.sommets.get(id);

		this.arretes.removeIf(
				Arretes -> Arretes.getDepart().equals(sommetSupp) || Arretes.getArrivee().equals(sommetSupp));
		this.sommets.remove(id);

		return true;
	}

	/**
	 * Ajoute une Arretes entre deux sommets.
	 *
	 * @param depart
	 * 		le sommet de départ.
	 * @param arrivee
	 * 		le sommet d'arrivée.
	 * @param troncons
	 * 		Le nombre de tronçons de la Arretes.
	 * @return L'Arretes ajoutée, ou null si une Arretes entre les mêmes sommets existe déjà.
	 */
	public Arrete ajouterArrete(Sommet depart, Sommet arrivee, int troncons)
	{
		if (!estPossibleArrete(depart, arrivee, troncons))
		{
			JOptionPane.showMessageDialog(null, "L'arrete existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		Arrete arrete = Arrete.creerArrete(depart, arrivee, troncons);

		this.arretes.add(arrete);
		depart.addArrete(arrete);
		arrivee.addArrete(arrete);

		this.enregistrer();
		return arrete;
	}

	/**
	 * Regarde si il y possibilité de crée une arrete.
	 *
	 * @param depart
	 * 		le sommet de depart de l'arrete.
	 * @param arrivee
	 * 		le sommet d'arrivee de l'arrete.
	 * @param troncons
	 * 		Le nombre de tronçons de la Arretes.
	 * @return true si l'on peut crée l'arrete, false si les troncons sont invalides ou si une arrete existe deja.
	 */
	public boolean estPossibleArrete(Sommet depart, Sommet arrivee, int troncons)
	{
		for (Arrete arrete : this.arretes)
			if (arrete.getDepart().equals(depart) && arrete.getArrivee().equals(arrivee))
				return false;

		for (Arrete arrete : this.arretes)
			if (arrete.getDepart().equals(arrivee) && arrete.getArrivee().equals(depart))
				return false;

		if (troncons < 1 || troncons > 2)
		{
			JOptionPane.showMessageDialog(null, "Nombre de tronçon invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;

		}

		return true;
	}

	/**
	 * Modifie le nombre de tronçons d'une Arretes entre deux sommets.
	 *
	 * @param depart
	 * 		Le sommet de départ.
	 * @param arrivee
	 * 		Le sommet d'arrivée.
	 * @param troncon
	 * 		Le nouveau nombre de tronçons.
	 * @return true si le nombre de tronçons a été modifié, false si le nombre de tronçons est invalide.
	 */
	public boolean modifierArrete(Sommet depart, Sommet arrivee, int troncon)
	{
		if (troncon != 1 && troncon != 2)
		{
			JOptionPane.showMessageDialog(null, "Nombre de tronçon invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		for (Arrete arrete : this.getArretes())
		{
			if (arrete.getDepart().equals(depart) && arrete.getArrivee().equals(arrivee))
			{
				arrete.setTroncons(troncon);
				return true;
			}
		}

		return false;
	}

	/**
	 * Supprime le sommet et les arrêtes lié
	 *
	 * @param sommet
	 * 		le sommet
	 * @param arrete
	 * 		l'arrête à supprimer
	 */
	public void supprimerArreteSommet(Sommet sommet, Arrete arrete)
	{
		sommet.supprimerArreteSommet(arrete);

		this.arretes.remove(arrete);
	}

	/**
	 * Réinitialise les ID des sommets.
	 */
	public void resetId()
	{
		Sommet.resetId();
	}

	public String toString()
	{
		return "\n\nMetier{ \n" + "SOMMET : \n" + sommets + "\n\nARRETE : \n" + arretes;
	}



	/*------------------------*/
	/* Gestion de fichier     */
	/*------------------------*/

	/**
	 * Enregistre les données actuelles dans un fichier.
	 */
	public void enregistrer()
	{
		this.gestionFichier.enregistrer();
	}

	/**
	 * Enregistre les données actuelles dans un nouveau fichier.
	 */
	public void enregistrerSous()
	{
		this.gestionFichier.enregistrerSous();
	}

	/**
	 * Définit le chemin du fichier à charger.
	 *
	 * @param path
	 * 		Le chemin du fichier.
	 */
	public void setFichierCharger(String path)
	{
		this.gestionFichier.setFichierCharger(path);
	}

	/**
	 * Récupère le chemin du fichier chargé.
	 *
	 * @return Le chemin du fichier.
	 */
	public String getFicherCharger()
	{
		return this.gestionFichier.getFichierCharger();
	}

	/*-------------------------*/
	/* Méthodes de scénarios */
	/*-------------------------*/
	public void supprimerSommets()
	{
		this.sommets.removeAll(this.sommets);
		Sommet.resetId();
		this.enregistrer();
	}

	public void supprimerArretes()
	{
		this.arretes.removeAll(this.arretes);
		this.enregistrer();
	}

	public void suppDonneesMap()
	{
		Sommet.resetId();
		this.sommets.removeAll(this.sommets);
		this.arretes.removeAll(this.arretes);
		this.enregistrer();
	}

}
