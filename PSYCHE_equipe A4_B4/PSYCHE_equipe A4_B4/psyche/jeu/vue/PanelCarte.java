package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Mine;
import psyche.jeu.metier.Minerai;
import psyche.jeu.metier.Piece;
import psyche.jeu.metier.Route;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelCarte extends JPanel
{

	private final ControleurJeu controleurJeu;
	private final Mine mineSelectionnee = null;

	public PanelCarte(ControleurJeu controleurJeu)
	{
		this.controleurJeu = controleurJeu;
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());

		GestionSouris gestionSouris = new GestionSouris();
		this.addMouseListener(gestionSouris);
		this.addMouseMotionListener(gestionSouris);
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		ImageIcon imageIcon = new ImageIcon("../psyche/theme/images/Plateau_vierge.png");
		Image imageDeFond = imageIcon.getImage();

		g.drawImage(imageDeFond, 0, 0, this.getWidth(), this.getHeight(), this);

		Graphics2D g2d = (Graphics2D) g;
		g.drawRect(0, 0, 1000, 800);


		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("INTER", Font.BOLD, 15));

		String pionSA = "../psyche/theme/images/pion_joueur_2.png";
		String pionCS = "../psyche/theme/images/pion_joueur_1.png";

		if (this.controleurJeu.getJoueur1() != null) {
			String pion = this.controleurJeu.getJoueur1().getNom().equals("CS") ? pionCS : pionSA;
			g.drawImage(getToolkit().getImage(pion), 10, 10, 20, 20, this);
			g2d.drawString(this.controleurJeu.getJoueur1().getNom() + " : " + this.controleurJeu.getJoueur1().getNbJetonPossession(), 40, 25);
		}

		if (this.controleurJeu.getJoueur2() != null) {
			String pion = this.controleurJeu.getJoueur2().getNom().equals("CS") ? pionCS : pionSA;
			g.drawImage(getToolkit().getImage(pion), 10, 40, 20, 20, this);
			g2d.drawString(this.controleurJeu.getJoueur2().getNom() + " : " + this.controleurJeu.getJoueur2().getNbJetonPossession(), 40, 55);
		}


		int largeurRect = 40;
		int hauteurRect = 65;

		for (Mine mine : this.controleurJeu.getMines())
		{
			if (!mine.getCouleur().name().equals("ROME"))
			{
				if (mine.estPrise())
				{
					g.drawImage(getToolkit().getImage(
									"../psyche/theme/images/transparent/" + mine.getCouleur().getLienImage() + "_clair.png"),
							mine.getX() - 10, mine.getY() - 10, largeurRect, hauteurRect, this);
				}
				else
				{
					g.drawImage(getToolkit().getImage(
									"../psyche/theme/images/transparent/" + mine.getCouleur().getLienImage() + ".png"),
							mine.getX() - 10, mine.getY() - 10, largeurRect, hauteurRect, this);
				}

				g2d.drawString(String.valueOf(mine.getPoint()), mine.getX() + 8, mine.getY() + 12);

				if (!mine.estPrise() && mine.getJeton() != null)
				{
					if (mine.getJeton().getType() instanceof Minerai minerai)
					{
						g.drawImage(
								getToolkit().getImage("../psyche/theme/images/ressources/" + minerai.getLienImage()),
								mine.getX() - 7, mine.getY() + 20, 35, 35, this);
					}
					if (mine.getJeton().getType() instanceof Piece piece)
					{
						g.drawImage(getToolkit().getImage("../psyche/theme/images/ressources/" + piece.name() + ".png"),
								mine.getX() - 7, mine.getY() + 20, 35, 35, this);
					}
				}
			}
			else
			{
				g.drawImage(getToolkit().getImage(
								"../psyche/theme/images/transparent/" + mine.getCouleur().getLienImage() + ".png"),
						mine.getX() - 15, mine.getY() - 5, 51, 55, this);
			}

//			g2d.drawString(String.valueOf(mine.getNom()), mine.getX(), mine.getY());
		}

		for (Route route : this.controleurJeu.getRoutes())
		{
			Mine depart = route.getDepart();
			Mine arrivee = route.getArrivee();

			int centreXDepart = depart.getX() + largeurRect / 2 - 10;
			int centreYDepart = depart.getY() + hauteurRect / 2 - 10;
			int centreXArrivee = arrivee.getX() + largeurRect / 2 - 10;
			int centreYArrivee = arrivee.getY() + hauteurRect / 2 - 10;

			double distance = Math.sqrt(
					Math.pow(centreXArrivee - centreXDepart, 2) + Math.pow(centreYArrivee - centreYDepart, 2));

			int ajustementOffset = 28;

			int bordXDepart = (int) (centreXDepart + (ajustementOffset * (centreXArrivee - centreXDepart) / distance));
			int bordYDepart = (int) (centreYDepart + (ajustementOffset * (centreYArrivee - centreYDepart) / distance));
			int bordXArrivee = (int) (centreXArrivee - (ajustementOffset * (centreXArrivee - centreXDepart) / distance));
			int bordYArrivee = (int) (centreYArrivee - (ajustementOffset * (centreYArrivee - centreYDepart) / distance));

			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(3.0f));
			g2d.drawLine(bordXDepart, bordYDepart, bordXArrivee, bordYArrivee);

			int nombreTroncons = route.getTroncons();
			double deltaX = (bordXArrivee - bordXDepart) / (double) (nombreTroncons + 1);
			double deltaY = (bordYArrivee - bordYDepart) / (double) (nombreTroncons + 1);

			if (nombreTroncons > 1)
			{
				int centreX = (centreXDepart + centreXArrivee) / 2;
				int centreY = (centreYDepart + centreYArrivee) / 2;
				g2d.fillOval(centreX - 5, centreY - 5, 10, 10);
			}
			else if (nombreTroncons == 1)
			{
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(2.0f));
			}

			g2d.fillOval(bordXDepart - 5, bordYDepart - 5, 10, 10);
			g2d.fillOval(bordXArrivee - 5, bordYArrivee - 5, 10, 10);

			if (route.getProprietaire() != null)
			{
				String cheminIcone = route.getProprietaire().getNom().equals("SA") ?
						"../psyche/theme/images/pion_joueur_2.png" :
						"../psyche/theme/images/pion_joueur_1.png";
				Image icone = getToolkit().getImage(cheminIcone);

				int milieuX = (bordXDepart + bordXArrivee) / 2;
				int milieuY = (bordYDepart + bordYArrivee) / 2;

				if (route.getTroncons() == 1)
				{
					g.drawImage(icone, milieuX - 10, milieuY - 10, 20, 20, this);
				}
				else if (route.getTroncons() == 2)
				{
					int quartX = (bordXDepart + milieuX) / 2;
					int quartY = (bordYDepart + milieuY) / 2;

					int troisQuartX = (bordXArrivee + milieuX) / 2;
					int troisQuartY = (bordYArrivee + milieuY) / 2;

					g.drawImage(icone, quartX - 10, quartY - 10, 20, 20, this);
					g.drawImage(icone, troisQuartX - 10, troisQuartY - 10, 20, 20, this);
				}

				this.controleurJeu.setProprietaire(route, route.getProprietaire());
			}
		}
		

	}

	private class GestionSouris extends MouseAdapter
	{
		private Mine mineSelectionnee = null;

		public void mousePressed(MouseEvent e)
		{
			int posX = e.getX();
			int posY = e.getY();

			for (Mine mine : controleurJeu.getMines())
			{
				int largeurRect = 40;
				int hauteurRect = 65;

				int rectX = mine.getX() - 10;
				int rectY = mine.getY() - 10;

				if (posX >= rectX && posX <= rectX + largeurRect && posY >= rectY && posY <= rectY + hauteurRect)
				{
					if ((mine.getJeton() == null || mine.getNom().equals("ROME")) && this.mineSelectionnee == null)
					{
						this.mineSelectionnee = mine;
						System.out.println("Mine sélectionnée 1: " + mine + mine.estPrise());
					}
					else if (this.mineSelectionnee != mine && controleurJeu.mineEstAdjacent(this.mineSelectionnee,
							mine))
					{
						System.out.println("Mine sélectionnée 2: " + mine + mine.estPrise());

						for (Route routeDepart : controleurJeu.getRoute(this.mineSelectionnee))
						{
							for (Route routeArrivee : controleurJeu.getRoute(mine))
							{
								if (routeDepart == routeArrivee && routeDepart.getProprietaire() == null)
								{
									if ((controleurJeu.getJoueurActuel()
											.getNbJetonPossession() - routeDepart.getTroncons()) >= 0)
									{
										controleurJeu.setProprietaire(routeDepart, controleurJeu.getJoueurActuel());
										controleurJeu.getJoueurActuel().setNbJetonPossession(routeDepart.getTroncons());

										if (!mine.estPrise())
										{
											controleurJeu.possederMine(mine);
											controleurJeu.changerTour();
										}

										System.out.println("Joueur " + controleurJeu.getJoueur1()
												.getNom() + " à " + controleurJeu.getJoueur1()
												.getNbJetonPossession() + controleurJeu.getJoueur1()
												.getJetonObtenues() + "\n" + "Joueur " + controleurJeu.getJoueur2()
												.getNom() + " à " + controleurJeu.getJoueur2()
												.getNbJetonPossession() + controleurJeu.getJoueur2()
												.getJetonObtenues());
									}
								}
							}
						}
						this.mineSelectionnee = null;
					}
				}
			}

			controleurJeu.verifierFinPartie();

			controleurJeu.majIHM();
		}

		public void mouseDragged(MouseEvent e)
		{
		}

		public void mouseReleased(MouseEvent e)
		{
		}
	}

	public void verifierFinPartie()
	{
		boolean toutesMinesPrise = true;
		for (Mine m : controleurJeu.getMines())
		{
			if (!m.estPrise())
			{
				toutesMinesPrise = false;
				break;
			}
		}

		boolean plusDeJeton = controleurJeu.getJoueurActuel().getNbJetonPossession() == 0;

		if (toutesMinesPrise || plusDeJeton)
		{
			controleurJeu.finPartie();
			System.out.println("Fin de la partie");
		}
	}

}
