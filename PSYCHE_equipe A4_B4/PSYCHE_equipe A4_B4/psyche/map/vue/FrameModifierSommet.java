/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément Cette classe gère les routes.
 */
package psyche.map.vue;

import psyche.map.ControleurMap;
import psyche.map.metier.Sommet;
import psyche.map.metier.Couleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class FrameModifierSommet extends JFrame implements ActionListener, ItemListener
{
	private final ControleurMap ctrlMap;

	private final boolean modificationComboBox;

	private final JPanel panelGauche;
	private final JPanel panelDroite;

	private final JTable tblDonnes;
	private final GrlDonneesModelSommet donnesTableau;

	private final JButton btnModifier;

	private final JTextField txtCoordX;
	private final JTextField txtCoordY;

	private final JLabel lblVisu;
	private final JLabel lblcordX;
	private final JLabel lblcordY;
	private final JLabel lblCouleur;
	private final JLabel lblPoint;
	private final JLabel lblId;

	private final JComboBox<String> jcbDeroulanteId;
	private final JComboBox<Couleur> jcbDeroulanteCouleur;
	private final JComboBox<String> jcbDeroulantePoint;

	private final String[] tabPointMine1;
	private final String[] tabPointMine2;
	private final String[] tabPointMine3;

	public FrameModifierSommet(ControleurMap ctrlMap)
	{

		this.setTitle("Modifier Sommet");
		this.setSize(600, 300);
		this.setLayout(new GridLayout(1, 2, 10, 20));
		this.getContentPane().setBackground(Color.gray);

		this.ctrlMap = ctrlMap;
		this.modificationComboBox = false;

		JScrollPane spTableau;

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/

		Font font = new Font("Roboto Slab", Font.BOLD, 10);


		/*-------------------------------*/
		/*       ID des mines            */
		/*-------------------------------*/

		List<String> tabMenuDeroulantID = new ArrayList<>();

		for (Sommet sommet : this.ctrlMap.getSommets())
		{
			tabMenuDeroulantID.add(String.valueOf(sommet.getId()));
		}

		String[] tabId = new String[tabMenuDeroulantID.size()];
		tabId = tabMenuDeroulantID.toArray(tabId);



		/*-------------------------------*/
		/*         Point des mines       */
		/*-------------------------------*/
		tabPointMine1 = new String[] { "1", "2", "3", "4", "5" };    //or  , rouge , marron
		tabPointMine2 = new String[] { "2", "3", "4", "6", "8" };    //bleu, vert
		tabPointMine3 = new String[] { "0", "1", "2", "3", "4" }; //gris

		this.jcbDeroulanteId = new JComboBox<>(tabId);
		this.jcbDeroulanteCouleur = new JComboBox<>(Couleur.values());
		this.jcbDeroulantePoint = new JComboBox<>(tabPointMine1);

		this.panelGauche = new JPanel(new BorderLayout());
		this.panelDroite = new JPanel(new GridLayout(6, 2, 0, 10));

		this.donnesTableau = new GrlDonneesModelSommet(this.ctrlMap);
		this.tblDonnes = new JTable(this.donnesTableau);
		this.tblDonnes.setFillsViewportHeight(true);

		spTableau = new JScrollPane(this.tblDonnes);

		this.lblVisu = new JLabel("Visualisation des Sommets");

		/*---------------------------------------*/
		/* Création des Labels et leurs couleurs */
		/*---------------------------------------*/

		this.lblcordX = new JLabel("CoordX   :");
		this.lblcordX.setBackground(Color.lightGray);
		this.lblcordX.setFont(new Font("Outfit", Font.BOLD, 12));
		this.lblcordX.setOpaque(true);

		this.lblcordY = new JLabel("CoordY   :");
		this.lblcordY.setBackground(Color.lightGray);
		this.lblcordY.setFont(new Font("Outfit", Font.BOLD, 12));
		this.lblcordY.setOpaque(true);

		this.lblCouleur = new JLabel("Couleur   :");
		this.lblCouleur.setBackground(Color.lightGray);
		this.lblCouleur.setFont(new Font("Outfit", Font.BOLD, 12));
		this.lblCouleur.setOpaque(true);

		this.lblPoint = new JLabel("Point   :");
		this.lblPoint.setBackground(Color.lightGray);
		this.lblPoint.setFont(new Font("Outfit", Font.BOLD, 12));
		this.lblPoint.setOpaque(true);

		this.lblId = new JLabel("ID   :");
		this.lblId.setBackground(Color.lightGray);
		this.lblId.setFont(new Font("Outfit", Font.BOLD, 12));
		this.lblId.setOpaque(true);

		this.txtCoordX = new JTextField();
		this.txtCoordX.setFont(font);

		this.txtCoordY = new JTextField();
		this.txtCoordY.setFont(font);

		this.btnModifier = new JButton("Modifier");
		this.btnModifier.setBackground(Color.WHITE);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.panelGauche.add(spTableau, BorderLayout.CENTER);
		this.panelGauche.add(this.lblVisu, BorderLayout.SOUTH);

		this.panelDroite.add(this.lblId);
		this.panelDroite.add(this.jcbDeroulanteId);

		this.panelDroite.add(this.lblCouleur);
		this.panelDroite.add(this.jcbDeroulanteCouleur);

		this.panelDroite.add(this.lblPoint);
		this.panelDroite.add(this.jcbDeroulantePoint);

		this.panelDroite.add(this.lblcordX);
		this.panelDroite.add(this.txtCoordX);

		this.panelDroite.add(this.lblcordY);
		this.panelDroite.add(this.txtCoordY);

		this.panelDroite.add(new JLabel());
		this.panelDroite.add(this.btnModifier);

		this.add(this.panelGauche);
		this.add(this.panelDroite);


		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/

		this.jcbDeroulanteCouleur.addItemListener(this);
		this.jcbDeroulantePoint.addItemListener(this);

		this.btnModifier.addActionListener(this);
		this.txtCoordX.addActionListener(this);
		this.txtCoordY.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnModifier)
		{
			String cordX = this.txtCoordX.getText();
			String cordY = this.txtCoordY.getText();

			if (cordX == null || cordY == null || !cordX.matches("\\d+") || !cordY.matches("\\d+"))
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			try
			{
				cordX = String.valueOf(Integer.parseInt(cordX));
				cordY = String.valueOf(Integer.parseInt(cordY));
			} catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Erreur pour la saisie des coordonnées", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			this.ctrlMap.modifierSommet(
					this.ctrlMap.getSommet(Integer.parseInt(this.jcbDeroulanteId.getSelectedItem().toString())).getId(),
					Integer.parseInt(cordX), Integer.parseInt(cordY),
					this.ctrlMap.getCouleur(this.jcbDeroulanteCouleur.getSelectedItem().toString()),
					(Integer.parseInt((String) this.jcbDeroulantePoint.getSelectedItem())));

			this.txtCoordX.setText("");
			this.txtCoordY.setText("");

			this.ctrlMap.majIHM();

			this.majIHM();
		}
	}

	public void majIHM()
	{
		this.tblDonnes.setModel(new GrlDonneesModelSommet(this.ctrlMap));
	}

	public void itemStateChanged(ItemEvent e)
	{

		if (e.getSource() == this.jcbDeroulanteCouleur)
		{

			switch (this.jcbDeroulanteCouleur.getSelectedItem().toString())
			{
			case "VERT", "BLEU_CLAIR" ->
			{
				this.jcbDeroulantePoint.setModel(new DefaultComboBoxModel<>(tabPointMine2));
			}
			case "GRIS" ->
			{
				this.jcbDeroulantePoint.setModel(new DefaultComboBoxModel<>(tabPointMine3));
			}
			case "ROME" ->
			{
				this.jcbDeroulantePoint.setModel(new DefaultComboBoxModel<>());
			}
			default ->
			{
				this.jcbDeroulantePoint.setModel(new DefaultComboBoxModel<>(tabPointMine1));
			}
			}

		}
	}
}