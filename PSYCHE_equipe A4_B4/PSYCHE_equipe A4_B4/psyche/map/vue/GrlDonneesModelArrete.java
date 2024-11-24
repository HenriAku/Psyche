/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément Cette classe gère les routes.
 */
package psyche.map.vue;

import psyche.map.ControleurMap;
import psyche.map.metier.Arrete;

import javax.swing.table.AbstractTableModel;

public class GrlDonneesModelArrete extends AbstractTableModel
{

	private final String[] tabEntetes;
	private final Object[][] tabDonnees;

	public GrlDonneesModelArrete(ControleurMap ctrlMap)
	{

		int cptVille = 0;

		this.tabEntetes = new String[] { "Mine Depart ID", "Mine Arrivé ID", "Nombres Tronçons" };
		this.tabDonnees = new Object[ctrlMap.getArretes().size()][this.tabEntetes.length];

		for (Arrete route : ctrlMap.getArretes())
		{
			this.tabDonnees[cptVille] = new Object[] { String.valueOf(route.getDepart().getId()),
					String.valueOf(route.getArrivee().getId()), route.getTroncons() };
			cptVille++;
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
