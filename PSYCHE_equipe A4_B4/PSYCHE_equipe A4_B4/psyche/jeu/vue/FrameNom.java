package psyche.jeu.vue;

import psyche.jeu.ControleurJeu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameNom extends JFrame implements ActionListener
{
	private final ControleurJeu ctrlJeu;

	private final JButton btnCS;
	private final JButton btnSA;

	public FrameNom(ControleurJeu ctrlJeu)
	{
		this.ctrlJeu = ctrlJeu;

		// Configuration de la fenêtre
		this.setTitle("Choix des camps");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Utilisation de GridLayout
		this.setLayout(new GridLayout(3, 1, 10, 10));

		// Création des composants
		JLabel lblText = new JLabel("Choix du premier joueur", SwingConstants.CENTER);
		lblText.setFont(new Font("Arial", Font.BOLD, 18));

		this.btnCS = new JButton("Corporation Solaire");
		this.btnCS.setForeground(Color.WHITE);
		this.btnCS.setBackground(new Color(130, 246, 131, 160));
		this.btnCS.setFocusPainted(false);

		this.btnSA = new JButton("Syndicat Astral");
		this.btnSA.setForeground(Color.WHITE);
		this.btnSA.setBackground(new Color(246, 130, 130, 160));
		this.btnSA.setFocusPainted(false);

		// Ajout des composants à la fenêtre
		this.add(lblText);
		this.add(btnCS);
		this.add(btnSA);

		// Activation des composants
		this.btnCS.addActionListener(this);
		this.btnSA.addActionListener(this);

		// Fermeture
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				if (ctrlJeu != null)
				{
					ctrlJeu.setVisible();
				}
				e.getWindow().setVisible(false);
			}
		});

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnCS)
		{
			this.selectionnerCS();
		}
		if (e.getSource() == this.btnSA)
		{
			this.selectionnerSA();
		}
		this.ctrlJeu.majIHM();
	}

	public void selectionnerCS()
	{
		this.ctrlJeu.setJoueur1("CS").setTour(true);
		this.ctrlJeu.setFrameJoueur1(new FrameJoueur(this.ctrlJeu.getJoueur1(), this.ctrlJeu));

		this.ctrlJeu.setJoueur2("SA");
		this.ctrlJeu.setFrameJoueur2(new FrameJoueur(this.ctrlJeu.getJoueur2(), this.ctrlJeu));

		this.setResizable(false);

		this.fermerCamps();
	}

	public void selectionnerSA()
	{
		this.ctrlJeu.setJoueur1("SA").setTour(true);
		this.ctrlJeu.setFrameJoueur1(new FrameJoueur(this.ctrlJeu.getJoueur1(), this.ctrlJeu));

		this.ctrlJeu.setJoueur2("CS");
		this.ctrlJeu.setFrameJoueur2(new FrameJoueur(this.ctrlJeu.getJoueur2(), this.ctrlJeu));

		this.setResizable(false);

		this.fermerCamps();
	}

	/*-------------------------*/
	/* Méthodes de scénarios */
	/*-------------------------*/

	public void fermerCamps()
	{
		this.dispose();
	}
}
