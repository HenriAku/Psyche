package psyche.jeu.vue;

import psyche.Controleur;
import psyche.jeu.ControleurJeu;

import javax.swing.*;
import java.awt.*;

public class PanelContour extends JPanel
{

	private int borne1, borne2;
	private final ControleurJeu ctrl;

	public PanelContour(ControleurJeu ctrl, int borne1, int borne2)
	{

		this.ctrl = ctrl;
		if (borne1 <= 0 && borne2 >= 25 || borne1 <= 75 && borne2 >= 51)
		{
			this.setLayout(new GridLayout(1, 25));
		}
		else
		{
			this.setLayout(new GridLayout(25, 1));
		}
		if (borne1 < borne2)
		{
			for (int i = borne1; i <= borne2; i++)
			{
				JLabel contour = new JLabel("" + i);
				this.add(contour);
				contour.setOpaque(false);
			}
		}
		else
		{
			for (int i = borne1; i >= borne2; i--)
			{
				JLabel contour = new JLabel("" + i);
				this.add(contour);
				contour.setOpaque(false);
			}
		}

	}
}