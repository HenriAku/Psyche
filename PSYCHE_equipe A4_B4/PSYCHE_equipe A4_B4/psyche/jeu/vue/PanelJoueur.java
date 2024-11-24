package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Jeton;
import psyche.jeu.metier.Joueur;
import psyche.jeu.metier.Mine;
import psyche.jeu.metier.Minerai;

import javax.swing.*;
import java.awt.*;

public class PanelJoueur extends JPanel
{
	private final Joueur joueur;
	private final ControleurJeu ctrlJeu;

	private Graphics2D g2;

	private Image imgFond;

	private final JLabel[][] tabMinerais;
	private final JLabel[] tabPiece;

	private final int[][] coordsMinerais = { { 64, 20 }, { 116, 20 }, { 167, 20 }, { 218, 20 }, { 270, 20 }, { 321, 20 },
			{ 373, 20 }, { 424, 20 }, { 64, 69 }, { 116, 69 }, { 167, 69 }, { 218, 69 }, { 270, 69 }, { 321, 69 },
			{ 373, 69 }, { 424, 69 }, { 64, 118 }, { 116, 118 }, { 167, 118 }, { 218, 118 }, { 270, 118 }, { 321, 118 },
			{ 373, 118 }, { 424, 118 }, { 64, 167 }, { 116, 224 }, { 167, 167 }, { 218, 167 }, { 270, 167 },
			{ 321, 167 }, { 373, 167 }, { 424, 167 } };

	private final int[] coordsPiece = { 64, 116, 167, 218, 270, 321, 373, 424, 763 }; //266

	public PanelJoueur(Joueur joueur, ControleurJeu ctrlJeu)
	{
		this.joueur = joueur;
		this.ctrlJeu = ctrlJeu;

		this.setLayout(null);

		this.tabMinerais = new JLabel[4][8];
		this.tabPiece = new JLabel[8];
		for (int lig = 0; lig < 4; lig++)
		{
			for (int col = 0; col < 8; col++)
			{
				this.tabMinerais[lig][col] = new JLabel();
				this.tabMinerais[lig][col].setBounds(this.coordsMinerais[lig][0], this.coordsMinerais[lig][1], 80,
						80); // Positionnement manuel
				this.add(this.tabMinerais[lig][col]);
			}
		}

		for (int cpt = 0; cpt < 8; cpt++)
		{
			this.tabPiece[cpt] = new JLabel();
			this.tabPiece[cpt].setBounds(this.coordsPiece[cpt], 266, 100, 100); // Positionnement manuel
			this.add(this.tabPiece[cpt]);
		}
	}

	public void paintComponent(Graphics g)
	{
		//		super.paintComponent(g);
		//
		// Charger l'image de fond

		Graphics2D g2 = (Graphics2D) g;

		if (this.joueur.getNom().equals("CS"))
		{
			ImageIcon imageIcon = new ImageIcon("../psyche/theme/images/plateau_joueur_1.png");

			Image backgroundImage = imageIcon.getImage();
			g2.drawImage(backgroundImage, 0, 0, 553, 397, this);

		}
		else
		{
			ImageIcon imageIcon = new ImageIcon("../psyche/theme/images/plateau_joueur_2.png");

			Image backgroundImage = imageIcon.getImage();

			g2.drawImage(backgroundImage, 0, 0, 553, 397, this);

		}

		int posXMine = 580;
		int posYMine = 50;

		// TODO : Récupérer les mines du joueur
		for (Mine m : this.joueur.getMinesObtenues())
		{
			if (!m.getNom().equals("ROME"))
			{

				g2.drawImage(getToolkit().getImage(
								"../psyche/theme/images/opaque/" + m.getCouleur().getLienImage() + "_clair.png"), posXMine,
						posYMine, 40, 65, this);
				g2.drawString(m.getPoint() + "", posXMine + 16, posYMine + 22);

				posXMine += 50;

				if (posXMine > 850)
				{
					posXMine = 580;
					posYMine += 70;
				}

			}

		}

		int posXPiece = 65;
		int posYPiece = 325;

		// TODO : Récupérer nb pièce depuis Joueur
		for (int i = 0; i < this.joueur.getNbPiece(); i++)
		{

			if (posXPiece < 460)
			{

				g2.drawImage(getToolkit().getImage("../psyche/theme/images/ressources/NR.png"), posXPiece, posYPiece,
						50, 50, this);

				posXPiece += 53;

			}

		}

		int posXMinerai = 11;
		int posYMinerai = 218;
		Jeton dernierElm = null;

		// afficher les minerais
		for (Jeton j : this.joueur.getListJeton())
		{
			if (j != null && j.getType() != null && j.getType() instanceof Minerai)
			{

				//				System.out.println((dernierElm != null && ((Minerai) j.getType()).getNom().equals(((Minerai) dernierElm.getType()).getNom())));

				if (dernierElm != null && ((Minerai) j.getType()).getNom()
						.equals(((Minerai) dernierElm.getType()).getNom()))
				{
					posYMinerai -= 54;

					g2.drawImage(getToolkit().getImage(
									"../psyche/theme/images/ressources/" + ((Minerai) j.getType()).getLienImage()), posXMinerai,
							posYMinerai, 50, 50, this);
				}
				else
				{
					posXMinerai += 53;
					posYMinerai = 218; // Reset posYMinerai to its initial value

					g2.drawImage(getToolkit().getImage(
									"../psyche/theme/images/ressources/" + ((Minerai) j.getType()).getLienImage()), posXMinerai,
							posYMinerai, 50, 50, this);
				}

				dernierElm = j;

			}
		}

	}

}