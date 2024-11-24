package psyche.map.vue;

import psyche.map.ControleurMap;
import psyche.map.metier.Arrete;
import psyche.map.metier.Sommet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Cette classe gère le panel où les villes sont représentées. Elle permet de dessiner des villes et des routes sur un
 * panneau, ainsi que de gérer les interactions avec la souris pour sélectionner et déplacer les villes.
 */
public class PanelGraph extends JPanel
{
	private final ControleurMap ctrlMap;
	private Sommet mineSelect = null;
	private int setX, setY = 0;

	/**
	 * Constructeur du panel graphique.
	 *
	 * @param ctrlMap
	 * 		Le contrôleur associé à ce panel.
	 */
	public PanelGraph(ControleurMap ctrlMap)
	{
		this.ctrlMap = ctrlMap;
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());

		GereSouris gereSouris = new GereSouris();
		this.addMouseListener(gereSouris);
		this.addMouseMotionListener(gereSouris);
	}

	/**
	 * Redessine les composants graphiques du panel.
	 *
	 * @param g
	 * 		L'objet Graphics utilisé pour dessiner.
	 */

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		//		// Charger l'image de fond
		//		ImageIcon imageIcon = new ImageIcon("../psyche/theme/images/img.png");
		//		Image backgroundImage = imageIcon.getImage();
		//
		//		// Dessiner l'image de fond
		//		g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);

		Graphics2D g2d = (Graphics2D) g;
		g.drawRect(0, 0, 1000, 800);

		// Diamètre des cercles représentant les mines
		int diameter = 30;
		int radius = diameter / 2;

		for (Sommet mine : this.ctrlMap.getSommets())
		{
			g2d.setColor(mine.getCouleur().getColor());
			g2d.fillOval(mine.getX() - radius, mine.getY() - radius, diameter, diameter);
			g2d.setColor(Color.BLACK);
			g2d.drawOval(mine.getX() - radius, mine.getY() - radius, diameter, diameter);
			g2d.drawString(mine.getNom() + " ID: " + mine.getId(), mine.getX() - radius, mine.getY() - radius - 10);
		}

		for (Arrete arrete : this.ctrlMap.getArretes())
		{
			Sommet ville1 = arrete.getDepart();
			Sommet ville2 = arrete.getArrivee();

			// Calculer les coordonnées centrales des cercles représentant les mines
			int ville1CenterX = ville1.getX();
			int ville1CenterY = ville1.getY();
			int ville2CenterX = ville2.getX();
			int ville2CenterY = ville2.getY();

			// Calculer les nouveaux points de départ et d'arrivée des arêtes
			double angle = Math.atan2(ville2CenterY - ville1CenterY, ville2CenterX - ville1CenterX);
			int ville1EdgeX = (int) (ville1CenterX + radius * Math.cos(angle));
			int ville1EdgeY = (int) (ville1CenterY + radius * Math.sin(angle));
			int ville2EdgeX = (int) (ville2CenterX - radius * Math.cos(angle));
			int ville2EdgeY = (int) (ville2CenterY - radius * Math.sin(angle));

			// Dessiner la ligne continue entre les centres des cercles
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(2.0f));
			g2d.drawLine(ville1CenterX, ville1CenterY, ville2CenterX, ville2CenterY);

			// Dessiner les points espacés le long de la ligne
			int troncons = arrete.getTroncons();

			// Vérifier s'il y a plus d'un tronçon pour dessiner les points intermédiaires
			if (troncons > 1)
			{
				int centerX = (ville1CenterX + ville2CenterX) / 2;
				int centerY = (ville1CenterY + ville2CenterY) / 2;
				g2d.fillOval(centerX - 5, centerY - 5, 10, 10);
			}
			else if (troncons == 1)
			{
				// Si un seul tronçon, ne pas dessiner de points intermédiaires
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(2.0f));
				g2d.drawLine(ville1CenterX, ville1CenterY, ville2CenterX, ville2CenterY);
			}

			// Ajouter le point de début
			g2d.fillOval(ville1CenterX - 5, ville1CenterY - 5, 10, 10);

			// Ajouter le point de fin
			g2d.fillOval(ville2CenterX - 5, ville2CenterY - 5, 10, 10);
		}
	}

	/**
	 * Classe interne pour gérer les événements de souris.
	 */
	private class GereSouris extends MouseAdapter
	{
		private int posX, posY;

		/**
		 * Gère l'événement lorsque la souris est pressée.
		 *
		 * @param e
		 * 		L'événement de souris.
		 */
		@Override
		public void mousePressed(MouseEvent e)
		{
			posX = e.getX();
			posY = e.getY();
			mineSelect = null;
			setX = 0;
			setY = 0;

			for (Sommet mine : ctrlMap.getSommets())
			{
				int diameter = 30;
				int radius = diameter / 2;
				int centerX = mine.getX();
				int centerY = mine.getY();
				int distX = Math.abs(e.getX() - centerX);
				int distY = Math.abs(e.getY() - centerY);
				double distance = Math.sqrt(distX * distX + distY * distY);

				if (distance <= radius)
				{
					mineSelect = mine;
					setX = e.getX() - mine.getX();
					setY = e.getY() - mine.getY();
					break;
				}
			}

			ctrlMap.majIHM();
		}

		@Override
		public void mouseDragged(MouseEvent e)
		{
			if (mineSelect != null)
			{
				int newX = e.getX() - setX;
				int newY = e.getY() - setY;

				ctrlMap.modifierSommet(mineSelect.getId(), newX, newY, mineSelect.getCouleur(), mineSelect.getPoint());
				ctrlMap.majIHM(mineSelect);
				ctrlMap.enregistrer();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			if (mineSelect != null)
			{
				int newX = e.getX() - setX;
				int newY = e.getY() - setY;

				ctrlMap.modifierSommet(mineSelect.getId(), newX, newY, mineSelect.getCouleur(), mineSelect.getPoint());
				ctrlMap.majIHM(mineSelect);
				ctrlMap.enregistrer();
			}
			ctrlMap.majIHM();
		}
	}
}
