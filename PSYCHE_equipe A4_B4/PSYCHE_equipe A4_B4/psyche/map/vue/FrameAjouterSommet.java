/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément Cette classe gère les routes.
 */
package psyche.map.vue;

import psyche.map.ControleurMap;
import psyche.map.metier.Couleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FrameAjouterSommet extends JFrame implements ActionListener, ItemListener
{
	private final ControleurMap ctrlMap;

	private final JPanel panelGauche;
	private final JPanel panelDroite;

	private final JTable tblDonnes;
	private final GrlDonneesModelSommet donnesTableau;

	private final JButton btnAjouter;

	private final JTextField txtcordX;
	private final JTextField txtcordY;

	private final JLabel lblVisu;
	private final JLabel lblcordX;
	private final JLabel lblcordY;

	private final JComboBox<Couleur> jcbDeroulanteCouleur;
	private final JComboBox<String> jcbDeroulantePoint;

	private final String[] tabPointMine1;
	private final String[] tabPointMine2;
	private final String[] tabPointMine3;

	public FrameAjouterSommet(ControleurMap ctrlMap)
	{

		this.setTitle("Ajouter Sommet");
		this.setSize(600, 300);
		this.setLayout(new GridLayout(1, 2, 10, 20));
		this.getContentPane().setBackground(Color.gray);

		this.ctrlMap = ctrlMap;

		JScrollPane spTableau;

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/

		/*-------------------------------*/
		/*         Point des mines       */
		/*-------------------------------*/
		tabPointMine1 = new String[] { "1", "2", "3", "4", "5" };    //or  , rouge , marron
		tabPointMine2 = new String[] { "2", "3", "4", "6", "8" };    //bleu, vert
		tabPointMine3 = new String[] { "0", "1", "2", "3", "4" }; //gris

		Font font = new Font("Roboto Slab", Font.BOLD, 10);

		this.jcbDeroulanteCouleur = new JComboBox<>(Couleur.values());
		this.jcbDeroulantePoint = new JComboBox<>(tabPointMine1);

		this.panelGauche = new JPanel(new BorderLayout());
		this.panelDroite = new JPanel(new GridLayout(5, 2, 0, 10));

		this.donnesTableau = new GrlDonneesModelSommet(this.ctrlMap);
		this.tblDonnes = new JTable(this.donnesTableau);
		this.tblDonnes.setFillsViewportHeight(true);

		spTableau = new JScrollPane(this.tblDonnes);

		this.lblVisu = new JLabel("Visualisation des mines");

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

		this.txtcordX = new JTextField();
		this.txtcordX.setFont(font);

		this.txtcordY = new JTextField();
		this.txtcordY.setFont(font);

		this.btnAjouter = new JButton("Ajouter");
		this.btnAjouter.setBackground(Color.WHITE);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.panelGauche.add(spTableau, BorderLayout.CENTER);
		this.panelGauche.add(this.lblVisu, BorderLayout.SOUTH);

		this.panelDroite.add(this.lblcordX);
		this.panelDroite.add(this.txtcordX);

		this.panelDroite.add(this.lblcordY);
		this.panelDroite.add(this.txtcordY);

		this.panelDroite.add(this.jcbDeroulanteCouleur);
		this.panelDroite.add(this.jcbDeroulantePoint);

		this.panelDroite.add(new JLabel());
		this.panelDroite.add(this.btnAjouter);

		this.add(this.panelGauche);
		this.add(this.panelDroite);


		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/

		this.jcbDeroulanteCouleur.addItemListener(this);
		this.jcbDeroulantePoint.addItemListener(this);

		this.btnAjouter.addActionListener(this);
		this.txtcordX.addActionListener(this);
		this.txtcordY.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnAjouter)
		{
			String cordX = this.txtcordX.getText();
			String cordY = this.txtcordY.getText();

			if (cordX == null && cordY == null)
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

			if (this.jcbDeroulanteCouleur.getSelectedItem().toString().equals("ROME"))
				this.ctrlMap.ajouterSommet(Integer.parseInt(cordX), Integer.parseInt(cordY), -1,
						Couleur.valueOf(this.jcbDeroulanteCouleur.getSelectedItem().toString()));
			else
				this.ctrlMap.ajouterSommet(Integer.parseInt(cordX), Integer.parseInt(cordY),
						Integer.parseInt(this.jcbDeroulantePoint.getSelectedItem().toString()),
						Couleur.valueOf(this.jcbDeroulanteCouleur.getSelectedItem().toString()));

			this.txtcordX.setText("");
			this.txtcordY.setText("");
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