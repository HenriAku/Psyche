/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément Cette classe gère les routes.
 */

package psyche.map.vue;

import psyche.map.ControleurMap;
import psyche.map.metier.Sommet;

import javax.swing.table.AbstractTableModel;

public class GrlDonneesModelSommet extends AbstractTableModel
{

	private final String[] tabEntetes;
	private final Object[][] tabDonnees;

	public GrlDonneesModelSommet(ControleurMap ctrlMap)
	{

		int cptMine = 0;

		this.tabEntetes = new String[] { "Numero", "Couleur", "Point", "X", "Y" };
		this.tabDonnees = new Object[ctrlMap.getSommets().size()][this.tabEntetes.length];

		for (Sommet mine : ctrlMap.getSommets())
		{
			this.tabDonnees[cptMine] = new Object[] { mine.getId(), mine.getCouleur(), mine.getPoint(), mine.getX(),
					mine.getY() };
			cptMine++;
		}
	}

	public String getColumnName(int col)
	{
		return this.tabEntetes[col];
	}

	public int getRowCount()
	{
		return this.tabDonnees.length;
	}

	public int getColumnCount()
	{
		return this.tabEntetes.length;
	}

	public Object getValueAt(int lig, int col)
	{
		return this.tabDonnees[lig][col];
	}

}
