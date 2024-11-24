/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément Cette classe gère les routes.
 */
package psyche.map.vue;

import psyche.map.ControleurMap;
import psyche.map.metier.Sommet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class FrameAjouterArrete extends JFrame implements ActionListener//, ItemListener
{
	/*-----------------*/
	/*    Donnees      */
	/*-----------------*/

	private final boolean modificationComboBox;

	private final JButton btnAjouter;

	private final JPanel panelGauche;
	private final JPanel panelDroite;

	private final JTable tblDonnes;
	private final GrlDonneesModelArrete donnesTableau;

	private final JLabel idDepart;
	private final JLabel idArrive;
	private final JLabel troncons;
	private final JLabel lblVisu;

	private final JComboBox<String> jcbDeroulanteDepartId;
	private final JComboBox<String> jcbDeroulanteArriveId;
	private final JComboBox<String> jcbDeroulanteTroncons;

	private final ControleurMap ctrlMap;


	/*-----------------*/
	/*  Instruction    */
	/*-----------------*/

	public FrameAjouterArrete(ControleurMap ctrlMap)
	{
		this.setTitle("Ajouter Arrete");
		this.setSize(600, 300);
		this.setLayout(new GridLayout(1, 2, 10, 20));
		this.getContentPane().setBackground(Color.gray);

		this.setVisible(true);

		this.ctrlMap = ctrlMap;
		this.modificationComboBox = false;

		JScrollPane spTableau;

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/

		this.panelGauche = new JPanel(new BorderLayout());
		this.panelDroite = new JPanel(new GridLayout(4, 2, 0, 10));

		this.donnesTableau = new GrlDonneesModelArrete(this.ctrlMap);
		this.tblDonnes = new JTable(this.donnesTableau);
		this.tblDonnes.setFillsViewportHeight(true);

		spTableau = new JScrollPane(this.tblDonnes);

		this.lblVisu = new JLabel("Visualisation des routes");

		/*-------------------------------*/
		/*         Id mine               */
		/*-------------------------------*/

		List<String> tabMenuDeroulantId = new ArrayList<>();

		for (Sommet mine : this.ctrlMap.getSommets())
		{
			if (!tabMenuDeroulantId.contains(mine.getId()))
				tabMenuDeroulantId.add(String.valueOf(mine.getId()));
		}

		String[] tabId = new String[tabMenuDeroulantId.size()];
		tabId = tabMenuDeroulantId.toArray(tabId);

		this.jcbDeroulanteDepartId = new JComboBox<>(tabId);
		this.jcbDeroulanteArriveId = new JComboBox<>(tabId);

		String[] tabTroncon = new String[] { "1", "2" };
		this.jcbDeroulanteTroncons = new JComboBox<>(tabTroncon);

		this.idDepart = new JLabel(String.format("%21s", "Id mine de départ       : "));
		this.idDepart.setBackground(Color.lightGray);
		this.idDepart.setOpaque(true);

		this.idArrive = new JLabel(String.format("%21s", "Id mine de arrivée     : "));
		this.idArrive.setBackground(Color.lightGray);
		this.idArrive.setOpaque(true);

		this.troncons = new JLabel(String.format("%21s", "Nombre de tronçons : "));
		this.troncons.setBackground(Color.lightGray);
		this.troncons.setOpaque(true);

		this.btnAjouter = new JButton("Ajouter");
		this.btnAjouter.setBackground(Color.WHITE);


		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.panelGauche.add(spTableau, BorderLayout.CENTER);
		this.panelGauche.add(this.lblVisu, BorderLayout.SOUTH);

		this.panelDroite.add(this.idDepart);
		this.panelDroite.add(this.jcbDeroulanteDepartId);

		this.panelDroite.add(this.idArrive);
		this.panelDroite.add(this.jcbDeroulanteArriveId);

		this.panelDroite.add(this.troncons);
		this.panelDroite.add(this.jcbDeroulanteTroncons);

		this.panelDroite.add(new JLabel());
		this.panelDroite.add(this.btnAjouter);

		this.add(this.panelGauche);
		this.add(this.panelDroite);

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/

		this.jcbDeroulanteDepartId.addActionListener(this);
		this.jcbDeroulanteArriveId.addActionListener(this);
		this.btnAjouter.addActionListener(this);

		//this.jcbDeroulanteTroncons.addItemListener(this);

	}

	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource() == this.btnAjouter)
		{

			if (this.jcbDeroulanteDepartId.getSelectedItem() != this.jcbDeroulanteArriveId.getSelectedItem())
			{

				this.ctrlMap.ajouterArrete(
						this.ctrlMap.getSommet(Integer.parseInt((String) this.jcbDeroulanteDepartId.getSelectedItem())),
						this.ctrlMap.getSommet(Integer.parseInt((String) this.jcbDeroulanteArriveId.getSelectedItem())),
						Integer.parseInt(this.jcbDeroulanteTroncons.getSelectedItem().toString()));

				this.tblDonnes.setModel(new GrlDonneesModelArrete(this.ctrlMap));
			}
			else
				JOptionPane.showMessageDialog(null , "Ne pas sélectionner deux fois la même ville", "Erreur",JOptionPane.ERROR_MESSAGE);

		}
	}



	//	public void itemStateChanged(ItemEvent e)
	//	{
	//		if (modificationComboBox || e.getStateChange() != ItemEvent.SELECTED || e.getItem() == null)
	//		{
	//			return;
	//		}
	//
	//		if (e.getSource() == this.jcbDeroulanteArriveId || e.get
	//		{
	//			modificationComboBox = true;
	//
	//			try
	//			{
	//				// Suppression de tous les éléments existants dans les JComboBox
	//				this.jcbDeroulanteDepartPoint.removeAllItems();
	//				this.jcbDeroulanteArrivePoint.removeAllItems();
	//
	//				// Obtention des couleurs sélectionnées
	//				String idDepart  = (String) this.jcbDeroulanteDepartCouleur.getSelectedItem();
	//				String couleurArrivee = (String) this.jcbDeroulanteArriveCouleur.getSelectedItem();
	//
	//
	//				if ( !idDepart.equals("ROME") )
	//				{
	//					// Parcours de la liste des mines
	//					for (Sommet mine : this.ctrlMap.getSommets())
	//					{
	//						String mineCouleur = mine.getCouleur().name();
	//						String minePoint   = String.valueOf(mine.getPoint());
	//
	//						// Ajout des points aux JComboBox en fonction des couleurs sélectionnées
	//						if (idDepart.equals(mineCouleur))
	//						{
	//							this.jcbDeroulanteDepartPoint.addItem(minePoint);
	//						}
	//					}
	//				}
	//
	//				if ( !couleurArrivee.equals("ROME") )
	//				{
	//					// Parcours de la liste des mines
	//					for (Sommet mine : this.ctrlMap.getSommets())
	//					{
	//						String mineCouleur = mine.getCouleur().name();
	//						String minePoint = String.valueOf(mine.getPoint());
	//
	//						// Ajout des points aux JComboBox en fonction des couleurs sélectionnées
	//						if (couleurArrivee.equals(mineCouleur))
	//						{
	//							this.jcbDeroulanteArrivePoint.addItem(minePoint);
	//						}
	//					}
	//				}
	//			}
	//			finally
	//			{
	//				modificationComboBox = false;
	//			}
	//		}

	//}
}
