package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import psyche.jeu.metier.Couleur;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelTabScore extends JPanel
{
	private final JLabel lblMineJaune;
	private final JLabel lblMineBleu;
	private final JLabel lblMineGris;
	private final JLabel lblMineVert;
	private final JLabel lblMineRouge;
	private final JLabel lblMineMarron;

	private final ControleurJeu ctrlJeu;

	public PanelTabScore(ControleurJeu ctrlJeu)
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(2, 2, 2, 2);

		this.ctrlJeu = ctrlJeu;

		// Dimensions souhaitées pour les images
		int imgWidth = 30;
		int imgHeight = 30;

		// Chargement et redimensionnement des images depuis le dossier theme/transparent
		ImageIcon mineJaune = resizeImageIcon(new ImageIcon("../psyche/theme/images/transparent/Mine_Jaune_lite.png"),
				imgWidth, imgHeight);
		ImageIcon mineBleu = resizeImageIcon(new ImageIcon("../psyche/theme/images/transparent/Mine_Bleu_lite.png"),
				imgWidth, imgHeight);
		ImageIcon mineGris = resizeImageIcon(new ImageIcon("../psyche/theme/images/transparent/Mine_Gris_lite.png"),
				imgWidth, imgHeight);
		ImageIcon mineVert = resizeImageIcon(new ImageIcon("../psyche/theme/images/transparent/Mine_Vert_lite.png"),
				imgWidth, imgHeight);
		ImageIcon mineRouge = resizeImageIcon(new ImageIcon("../psyche/theme/images/transparent/Mine_Rouge_lite.png"),
				imgWidth, imgHeight);
		ImageIcon mineMarron = resizeImageIcon(new ImageIcon("../psyche/theme/images/transparent/Mine_Marron_lite.png"),
				imgWidth, imgHeight);

		// Chargement et redimensionnement des images pour les labels "Corporation Solaire" et "Syndicat Astral"
		ImageIcon corpSolImage = resizeImageIcon(new ImageIcon("../psyche/theme/images/pion_joueur_1.png"), imgWidth,
				imgHeight);
		ImageIcon syndAstralImage = resizeImageIcon(new ImageIcon("../psyche/theme/images/pion_joueur_2.png"), imgWidth,
				imgHeight);

		// Initialisation des JLabels avec les images redimensionnées
		this.lblMineJaune = createLabeledIcon(mineJaune);
		this.lblMineBleu = createLabeledIcon(mineBleu);
		this.lblMineGris = createLabeledIcon(mineGris);
		this.lblMineVert = createLabeledIcon(mineVert);
		this.lblMineRouge = createLabeledIcon(mineRouge);
		this.lblMineMarron = createLabeledIcon(mineMarron);

		// Création des labels avec texte et icône
		JLabel corpSolLabel = createLabeledIconWithText(corpSolImage, "Corporation Solaire");
		JLabel syndAstralLabel = createLabeledIconWithText(syndAstralImage, "Syndicat Astral");

		// Ajout des composants dans le JPanel avec des bordures pour chaque cellule
		addComponent(gbc, createTitleLabel("Fiche de Score", new Color(232, 183, 36), true), 0, 0, 3, 1);

		addComponent(gbc, createEmptyLabel(), 0, 1, 1, 1);

		if ( this.ctrlJeu.getJoueur1().getNom().equals("CS"))
		{
			addComponent(gbc, corpSolLabel, 1, 1, 1, 1);
			addComponent(gbc, syndAstralLabel, 2, 1, 1, 1);
		}
		else
		{
			addComponent(gbc, syndAstralLabel, 1, 1, 1, 1);
			addComponent(gbc, corpSolLabel, 2, 1, 1, 1);
		}

		addComponent(gbc, createLabeledComponent("Points des Mines", new Color(232, 221, 36), true), 0, 4, 3, 1);

		addComponent(gbc, this.lblMineJaune, 0, 5, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScoreMineJ1(Couleur.JAUNE), null, false), 1,
				5, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScoreMineJ2(Couleur.JAUNE), null, false), 2,
				5, 1, 1);

		addComponent(gbc, this.lblMineBleu, 0, 6, 1, 1);
		addComponent(gbc,
				createLabeledComponent("" + this.ctrlJeu.calculerScoreMineJ1(Couleur.BLEU_CLAIR), null, false), 1, 6, 1,
				1);
		addComponent(gbc,
				createLabeledComponent("" + this.ctrlJeu.calculerScoreMineJ2(Couleur.BLEU_CLAIR), null, false), 2, 6, 1,
				1);

		addComponent(gbc, this.lblMineGris, 0, 7, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScoreMineJ1(Couleur.GRIS), null, false), 1,
				7, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScoreMineJ2(Couleur.GRIS), null, false), 2,
				7, 1, 1);

		addComponent(gbc, this.lblMineVert, 0, 8, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScoreMineJ1(Couleur.VERT), null, false), 1,
				8, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScoreMineJ2(Couleur.VERT), null, false), 2,
				8, 1, 1);

		addComponent(gbc, this.lblMineRouge, 0, 9, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScoreMineJ1(Couleur.ROUGE), null, false), 1,
				9, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScoreMineJ2(Couleur.ROUGE), null, false), 2,
				9, 1, 1);

		addComponent(gbc, this.lblMineMarron, 0, 10, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScoreMineJ1(Couleur.MARRON), null, false), 1,
				10, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScoreMineJ2(Couleur.MARRON), null, false), 2,
				10, 1, 1);

		addComponent(gbc, createLabeledComponent("S/Total", new Color(232, 221, 36), true), 0, 11, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScoreMinesTotaleJ1(), null, false), 1, 11, 1,
				1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScoreMinesTotaleJ2(), null, false), 2, 11, 1,
				1);

		addComponent(gbc, createLabeledComponent("Plateau Individuel", new Color(232, 221, 36), true), 0, 12, 3, 1);

		addComponent(gbc, createLabeledComponent("Score Pièces", null, false), 0, 13, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScorePiece1(), null, false), 1, 13, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.calculerScorePiece2(), null, false), 2, 13, 1, 1);

		addComponent(gbc, createLabeledComponent("Scores des Colonnes", null, false), 0, 14, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.getPointsColonnesJ1(), null, false), 1, 14, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.getPointsColonnesJ2(), null, false), 2, 14, 1, 1);

		addComponent(gbc, createLabeledComponent("Scores des Lignes", null, false), 0, 15, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.getPointsLignesJ1(), null, false), 1, 15, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.getPointsLignesJ2(), null, false), 2, 15, 1, 1);

		addComponent(gbc, createLabeledComponent("S/Totale", new Color(232, 221, 36), true), 0, 16, 1, 1);
		addComponent(gbc, createLabeledComponent(
						"" + (this.ctrlJeu.calculerScorePiece1() + this.ctrlJeu.calculerScoreMineraiJ1()), null, false), 1, 16,
				1, 1);
		addComponent(gbc, createLabeledComponent(
						"" + (this.ctrlJeu.calculerScorePiece2() + this.ctrlJeu.calculerScoreMineraiJ2()), null, false), 2, 16,
				1, 1);

		addComponent(gbc, createLabeledComponent("Jetons Possession restants", null, false), 0, 17, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.getJetonPossessionJ1(), null, false), 1, 17, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.getJetonPossessionJ2(), null, false), 2, 17, 1, 1);

		addComponent(gbc, createLabeledComponent("Bonus", new Color(232, 221, 36), true), 0, 18, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.pointBonusJ1(), null, false), 1, 18, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.pointBonusJ2(), null, false), 2, 18, 1, 1);

		addComponent(gbc, createLabeledComponent("Total", new Color(232, 183, 36), true), 0, 19, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.scoreTotalJ1(), null, false), 1, 19, 1, 1);
		addComponent(gbc, createLabeledComponent("" + this.ctrlJeu.scoreTotalJ2(), null, false), 2, 19, 1, 1);

		addComponent(gbc, createLabeledComponent("Victoire de " + this.ctrlJeu.getVictoire(), new Color(0, 200, 0), true), 0, 20, 3, 1);

	}

	private JLabel createLabeledIcon(ImageIcon icon)
	{
		JLabel label = new JLabel();
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		label.setIcon(icon);
		label.setOpaque(true);
		return label;
	}

	private JLabel createLabeledIconWithText(ImageIcon icon, String text)
	{
		JLabel label = new JLabel(text, JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		label.setIcon(icon);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setOpaque(true);
		return label;
	}

	private JLabel createLabeledComponent(String text, Color backgroundColor, boolean isHeader)
	{
		JLabel label = new JLabel(text, JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		if (backgroundColor != null)
		{
			label.setBackground(backgroundColor);
			label.setOpaque(true);
		}
		if (isHeader)
		{
			label.setFont(label.getFont().deriveFont(Font.BOLD));
		}
		return label;
	}

	private JLabel createEmptyLabel()
	{
		JLabel emptyLabel = new JLabel();
		emptyLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		return emptyLabel;
	}

	private JLabel createTitleLabel(String text, Color backgroundColor, boolean isBold)
	{
		JLabel label = new JLabel(text, JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		label.setOpaque(true);
		label.setBackground(backgroundColor);
		if (isBold)
		{
			label.setFont(label.getFont().deriveFont(Font.BOLD));
		}
		return label;
	}

	private void addComponent(GridBagConstraints gbc, Component comp, int x, int y, int width, int height)
	{
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		this.add(comp, gbc);
	}

	private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height)
	{
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImg);
	}
}
