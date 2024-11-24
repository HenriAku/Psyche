/**
 * @author CAUVIN Pierre, AUBIN Montagne, DELPECHE Nicolas, GUELLE Clément Cette classe gère les routes.
 */
package psyche.map.vue;

import psyche.Controleur;
import psyche.map.ControleurMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FrameMap extends JFrame implements ActionListener
{
	private final JMenuItem menuiAjouterSommet;
	private final JMenuItem menuiAjouterArrete;

	private final JMenuItem menuiModifierSommet;
	private final JMenuItem menuiModifierArrete;

	private final JMenuItem menuiEnregistrer;
	private final JMenuItem menuiEnregistrerSous;
	private final JMenuItem menuiCharger;

	private final JMenuItem menuiSupprimer;

	private ControleurMap ctrlMap;

	private final PanelInfoVille panelInfoVille;

	private PanelGraph panelGraph;

	public FrameMap(ControleurMap ctrlMap, Controleur ctrl)
	{
		this.setTitle("Créateur de MAP");
		this.setSize(1200, 860);
		this.setLocation(300, 75);
		this.setLayout(new BorderLayout());
		//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				if (ctrl != null)
				{
					ctrl.setVisible();
				}
				e.getWindow().setVisible(false);
			}
		});

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.panelGraph = new PanelGraph(this.ctrlMap);
		this.ctrlMap = ctrlMap;

		// Créer un panel
		this.panelInfoVille = new PanelInfoVille(this.ctrlMap);
		this.panelGraph = new PanelGraph(this.ctrlMap);

		JMenuBar menubMaBarre = new JMenuBar();

		JMenu menuOutils = new JMenu("Outils");
		menuOutils.setBackground(Color.LIGHT_GRAY);
		menuOutils.setOpaque(true);
		JMenu menuEdition = new JMenu("Edition");
		menuEdition.setBackground(Color.LIGHT_GRAY);
		menuEdition.setOpaque(true);
		JMenu menuEnregistrer = new JMenu("Enregistrer");
		menuEnregistrer.setBackground(Color.LIGHT_GRAY);
		menuEnregistrer.setOpaque(true);

		/* Item de Outils */
		this.menuiAjouterSommet = new JMenuItem("Ajouter Sommet");
		this.menuiAjouterArrete = new JMenuItem("Ajouter Arrete");

		/* Item de Edition */
		this.menuiModifierSommet = new JMenuItem("Modifier Sommet");
		this.menuiModifierArrete = new JMenuItem("Modifier Arrete");
		this.menuiSupprimer = new JMenuItem("Supprimer Sommet");

		/* Item de Enregistrer */
		this.menuiEnregistrer = new JMenuItem("Enregistrer");
		this.menuiEnregistrerSous = new JMenuItem("Enregistrer sous...");
		this.menuiCharger = new JMenuItem("Ouvrir");

		/* Raccourcis clavier */
		this.menuiEnregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		this.menuiEnregistrerSous.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		this.menuiCharger.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));



		/* Ajout des Items dans Outils  */
		menuOutils.add(this.menuiAjouterSommet);
		menuOutils.add(this.menuiAjouterArrete);

		/* Ajout des Items dans Edition  */
		menuEdition.add(this.menuiModifierSommet);
		menuEdition.add(this.menuiModifierArrete);
		menuEdition.add(this.menuiSupprimer);

		/* Ajout des Items dans Enregistrer  */
		menuEnregistrer.add(this.menuiEnregistrer);
		menuEnregistrer.add(this.menuiEnregistrerSous);
		menuEnregistrer.addSeparator();
		menuEnregistrer.add(this.menuiCharger);

		// ajout du menu 'Fichier' a la barre de menu
		menubMaBarre.add(menuOutils);
		menubMaBarre.add(menuEdition);
		menubMaBarre.add(menuEnregistrer);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.setJMenuBar(menubMaBarre);
		/* Ajouter Panel Info */
		this.add(panelInfoVille, BorderLayout.EAST);
		this.add(panelGraph, BorderLayout.CENTER);

		/*-------------------------------*/
		/*   Activation des composants   */
		/*-------------------------------*/
		this.menuiAjouterSommet.addActionListener(this);
		this.menuiAjouterArrete.addActionListener(this);
		this.menuiModifierArrete.addActionListener(this);
		this.menuiModifierSommet.addActionListener(this);
		this.menuiSupprimer.addActionListener(this);
		this.menuiEnregistrer.addActionListener(this);
		this.menuiEnregistrerSous.addActionListener(this);
		this.menuiCharger.addActionListener(this);

		this.setVisible(true);
	}

	public PanelGraph getPanelGraph()
	{
		return this.panelGraph;
	}

	public PanelInfoVille getPanelInfoVille()
	{
		return this.panelInfoVille;
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.menuiAjouterSommet)
		{
			this.ctrlMap.ouvrirAjouterSommet();
		}
		else if (e.getSource() == this.menuiAjouterArrete)
		{
			if (this.ctrlMap.getSommets().size() >= 2)
				this.ctrlMap.ouvrirAjouterArrete();
		}
		else if (e.getSource() == this.menuiModifierSommet)
		{
			if (this.ctrlMap.getSommets().size() >= 1)
				this.ctrlMap.ouvrirModifierSommet();
		}
		else if (e.getSource() == this.menuiModifierArrete)
		{
			System.out.println("test Frame modifier route 1");
			if (this.ctrlMap.getSommets().size() >= 2)
			{
				System.out.println("test Frame modifier route 2");
				this.ctrlMap.ouvrirModifierArrete();
			}
		}
		else if (e.getSource() == this.menuiSupprimer)
		{
			if (this.ctrlMap.getSommets().size() >= 1)
				this.ctrlMap.ouvrirSupprimerSommet();
		}
		else if (e.getSource() == this.menuiEnregistrer)
		{
			if (this.ctrlMap.getFichierCharger().equals("") || this.ctrlMap.getFichierCharger() == null)
				this.ctrlMap.enregistrerSous();
			this.ctrlMap.enregistrer();
		}
		else if (e.getSource() == this.menuiEnregistrerSous)
		{
			this.ctrlMap.enregistrerSous();
		}
		else if (e.getSource() == this.menuiCharger)
		{
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION)
			{
				File selectedFile = fileChooser.getSelectedFile();
				this.ctrlMap.setFichierCharger(selectedFile.getPath());
			}
		}
	}

	//	//Tentative de background
	//	protected void paintComponent(Graphics g) {
	//		super.paintComponent(g);
	//		try
	//		{
	//			String imageFond = ImageIO.read(new File("/home/saji/Cours/IUT/TP/s2/s2.01_dev_application/AppFinal/psyche/theme/img.png"));
	//			g.drawImage(imageFond, 0, 0, getWidth(), getHeight(), this);
	//		}
	//		catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//	}

	public void fermerMap()
	{
		this.dispose();
	}


}
