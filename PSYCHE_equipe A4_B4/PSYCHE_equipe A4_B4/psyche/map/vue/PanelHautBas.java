package psyche.map.vue;

import psyche.map.ControleurMap;

import javax.swing.*;
import java.awt.*;

public class PanelHautBas extends JPanel
{

	private int borne1, borne2;
	private final ControleurMap ctrlMap;

	public PanelHautBas(ControleurMap ctrlMap, int borne1, int borne2)
	{

		this.ctrlMap = ctrlMap;
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
