package psyche.map;

import psyche.Controleur;
import psyche.map.metier.Arrete;
import psyche.map.metier.Couleur;
import psyche.map.metier.Metier;
import psyche.map.metier.Sommet;
import psyche.map.vue.*;

import java.util.List;
import javax.swing.*;

public class ControleurMap
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	private final Metier metier;
	private final FrameMap frameMap;
	private final Controleur ctrl;

	private FrameAjouterSommet frameAjouterSommet;
	private FrameAjouterArrete frameAjouterArrete;
	private FrameModifierSommet frameModifierSommet;
	private FrameModifierArrete frameModifierArrete;
	private FrameSupprimerSommet frameSupprimerSommet;

	private final PanelGraph panelGraph;
	private final PanelInfoVille panelInfoVille;


	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur de Controleur
	 */

	public ControleurMap(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.metier = new Metier();
		this.frameMap = new FrameMap(this, this.ctrl);
		this.panelGraph = new PanelGraph(this);
		this.panelInfoVille = new PanelInfoVille(this);
	}



	/*--------------*/
	/* 		Get     */
	/*--------------*/

	public Sommet getSommet(int i)
	{
		return this.metier.getSommet(i);
	}

	public List<Sommet> getSommets()
	{
		return this.metier.getSommets();
	}

	public List<Arrete> getArretes()
	{
		return this.metier.getArretes();
	}

	public Couleur getCouleur(String couleur)
	{
		for (Couleur c : Couleur.values())
		{
			if (c.name().equals(couleur.toUpperCase()))
			{
				return c;
			}
		}
		return null;
	}



	/*-----------------*/
	/* Autres Méthodes */
	/*-----------------*/

	/**
	 * Crée la frame d'ajout de sommets
	 */
	public void ouvrirAjouterSommet()
	{
		this.frameAjouterSommet = new FrameAjouterSommet(this);
	}

	/**
	 * Crée la frame d'ajout de arretes
	 */
	public void ouvrirAjouterArrete()
	{
		this.frameAjouterArrete = new FrameAjouterArrete(this);
	}

	/**
	 * Crée la frame de modification de sommet
	 */
	public void ouvrirModifierSommet()
	{
		this.frameModifierSommet = new FrameModifierSommet(this);
	}

	/**
	 * Crée la frame de modification de arrete
	 */
	public void ouvrirModifierArrete()
	{
		this.frameModifierArrete = new FrameModifierArrete(this);
	}

	/**
	 * Crée la frame de supprimer de sommet
	 */
	public void ouvrirSupprimerSommet()
	{
		this.frameSupprimerSommet = new FrameSupprimerSommet(this);
	}

	/**
	 * Ajoute d'un sommet
	 *
	 * @param x
	 * 		Coordonnées X du sommet
	 * @param y
	 * 		Coordonnées Y du sommet
	 * @return le sommet créée
	 */
	public Sommet ajouterSommet(int x, int y, int point, Couleur couleur)
	{
		this.majIHM();
		return this.metier.ajouterSommet(x, y, point, couleur);
	}

	/**
	 * Ajoute une Arrete
	 *
	 * @param depart
	 * 		Sommet de départ
	 * @param arrivee
	 * 		Sommet d'arrivée
	 * @param troncons
	 * 		Nombre de tronçons de l'Arrete
	 */
	public Arrete ajouterArrete(Sommet depart, Sommet arrivee, int troncons)
	{
		this.majIHM();
		return this.metier.ajouterArrete(depart, arrivee, troncons);
	}

	/**
	 * Modifie d'un sommet
	 *
	 * @param x
	 * 		Coordonnées X du sommet
	 * @param y
	 * 		Coordonnées Y du sommet
	 */
	public void modifierSommet(int id, int x, int y, Couleur couleur, int point)
	{
		this.metier.modifierSommet(x, y, couleur, point, this.getSommet(id));
		this.majIHM();
	}

	/**
	 * Modifie une arrete
	 *
	 * @param depart
	 * 		Sommet de depart de l'arrete.
	 * @param arrivee
	 * 		Sommet de depart de l'arrete.
	 * @param nbTroncons
	 * 		Nombre de tronçons de l'Arrete.
	 */
	public boolean modifierArrete(Sommet depart, Sommet arrivee, int nbTroncons)
	{
		if (this.metier.modifierArrete(depart, arrivee, nbTroncons))
		{
			this.majIHM();
			return true;
		}

		if (this.metier.modifierArrete(arrivee, depart, nbTroncons))
		{
			this.majIHM();
			return true;
		}

		return false;
	}

	/**
	 * supprime l'arrete
	 *
	 * @param depart
	 * 		Sommet de depart de l'arrete.
	 * @param arrete
	 * 		L'Arrete lié au sommet.
	 */
	public void supprimerArrete(Sommet depart, Arrete arrete)
	{
		this.metier.supprimerArreteSommet(depart, arrete);
		this.majIHM();
	}

	/**
	 * supprime un sommet
	 *
	 * @param i
	 * 		indice du sommet de la liste .
	 */
	public void supprimerSommet(int i)
	{
		this.metier.supprimerSommet(i);
	}

	public boolean estPossibleArrete(Sommet depart, Sommet arrivee, int troncons)
	{
		return this.metier.estPossibleArrete(depart, arrivee, troncons);
	}

	/**
	 * Met à jour l'IHM
	 */
	public void majIHM()
	{
		this.frameMap.getPanelGraph().repaint();
	}

	/**
	 * Renvoie une Sommet à partir de son nom
	 *
	 * @param sommetSelect
	 * 		Nom de la mine dont les infos doivent être affichées.
	 */
	public void majIHM(Sommet sommetSelect)
	{
		this.frameMap.getPanelGraph().repaint();
		this.frameMap.getPanelInfoVille()
				.majVilleInfo(sommetSelect.getId(), sommetSelect.getX(), sommetSelect.getY(), sommetSelect.getCouleur(),
						sommetSelect.getPoint());
	}

	//Fichier

	/**
	 * Enregistre les données actuelles dans le fichier et met à jour l'IHM.
	 */
	public void enregistrer()
	{
		this.metier.enregistrer();
		this.majIHM();
	}

	/**
	 * Enregistre les données actuelles dans un fichier seléctionné par l'utilisateur et met à jour l'IHM.
	 */
	public void enregistrerSous()
	{
		this.majIHM();
		this.metier.enregistrerSous();
	}

	/**
	 * Renvoie le chemin d'accès du fichier d'où charger les données
	 */
	public String getFichierCharger()
	{
		return this.metier.getFicherCharger();
	}

	/**
	 * Définit le chemin d'accès du fichier
	 *
	 * @param path
	 * 		Le chemin d'accès à définir.
	 */
	public void setFichierCharger(String path)
	{
		this.metier.setFichierCharger(path);
		this.majIHM();
	}


	/*-------------------------*/
	/* Méthodes de scénarios */
	/*-------------------------*/


	/*-------------------------*/
	/* Méthodes de scénarios */
	/*-------------------------*/

	public void fermerFenetre()
	{
		this.frameMap.dispose();
	}

	public void supprimerSommets()
	{
		this.metier.supprimerSommets();
	}

	public void supprimerArretes()
	{
		this.metier.supprimerArretes();
	}

	public void suppDonneesMap()
	{
		this.metier.suppDonneesMap();
	}

	public void fermerMap()
	{
		this.frameMap.fermerMap();
	}

}
