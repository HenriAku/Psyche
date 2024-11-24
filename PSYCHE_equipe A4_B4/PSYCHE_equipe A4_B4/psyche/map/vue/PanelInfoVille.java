/**
 * Cette classe gère les routes.
 *
 * Auteurs : CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément
 */
package psyche.map.vue;

import psyche.map.ControleurMap;
import psyche.map.metier.Couleur;

import javax.swing.*;
import java.awt.*;

public class PanelInfoVille extends JPanel
{

	private final ControleurMap ctrlMap;

	private final JLabel lblX;
	private final JLabel lblCoordX;
	private final JLabel lblY;
	private final JLabel lblCoordY;
	private final JLabel lblCoul;
	private final JLabel lblAffCoul;
	private final JLabel lblPoint;
	private final JLabel lblAffPoint;
	private final JLabel lblNom;
	private final JLabel lblAffNom;

	private final JPanel panelDroite;

	/**
	 * Constructeur de la classe PanelInfoVille.
	 *
	 * @param ctrlMap
	 * 		Le contrôleur de l'application.
	 */
	public PanelInfoVille(ControleurMap ctrlMap)
	{

		this.ctrlMap = ctrlMap;

		// Utilisation d'un BorderLayout pour une taille stable du panel
		this.setLayout(new BorderLayout());
		this.setBackground(Color.lightGray);

		// Panneau central avec GridLayout pour aligner les composants verticalement
		JPanel centerPanel = new JPanel();
		this.panelDroite = new JPanel(new GridLayout(5, 2, 5, 5));

		centerPanel.setOpaque(false);
		this.panelDroite.setOpaque(false);

		this.panelDroite.setPreferredSize(new Dimension(150, 150));


		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.lblNom = new JLabel("<html><b>Nom :</b></html>");
		this.lblAffNom = new JLabel("<html><i>-</i></html>");

		this.lblX = new JLabel("<html><b>X :</b></html>");
		this.lblCoordX = new JLabel("<html><i>-</i></html>");

		this.lblY = new JLabel("<html><b>Y :</b></html>");
		this.lblCoordY = new JLabel("<html><i>-</i></html>");

		this.lblCoul = new JLabel("<html><b>Couleur :</b></html>");
		this.lblAffCoul = new JLabel("<html><i>-</i></html>");

		this.lblPoint = new JLabel("<html><b>Point :</b></html>");
		this.lblAffPoint = new JLabel("<html><i>-</i></html>");



		/*-------------------------*/
		/* Position des composants */
		/*-------------------------*/
		this.panelDroite.add(this.lblNom);
		this.panelDroite.add(this.lblAffNom);
		this.panelDroite.add(this.lblX);
		this.panelDroite.add(this.lblCoordX);
		this.panelDroite.add(this.lblY);
		this.panelDroite.add(this.lblCoordY);
		this.panelDroite.add(this.lblCoul);
		this.panelDroite.add(this.lblAffCoul);
		this.panelDroite.add(this.lblPoint);
		this.panelDroite.add(this.lblAffPoint);

		// Ajouter des marges autour du panneau central
		JPanel marginPanel = new JPanel(new BorderLayout());

		marginPanel.setOpaque(false);
		marginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		marginPanel.add(centerPanel, BorderLayout.CENTER);
		marginPanel.add(this.panelDroite, BorderLayout.EAST);

		// Ajouter le panneau avec marges au panneau principal
		this.add(marginPanel);
	}

	/**
	 * Met à jour les informations de la ville affichée.
	 *
	 * @param x
	 * 		La coordonnée X de la ville.
	 * @param y
	 * 		La coordonnée Y de la ville.
	 */
	public void majVilleInfo(int id, int x, int y, Couleur couleur, int point)
	{

		this.lblAffNom.setText("<html><i>" + this.ctrlMap.getSommet(id).getNom() + "</i></html>");
		this.lblCoordX.setText("<html><i>" + x + "</i></html>");
		this.lblCoordY.setText("<html><i>" + y + "</i></html>");
		this.lblAffCoul.setText("<html><i>" + couleur + "</i></html>");
		this.lblAffPoint.setText("<html><i>" + point + "</i></html>");

	}
}
