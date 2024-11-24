package psyche.scenario;

import javax.swing.*;
import javax.swing.plaf.ActionMapUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameScenario extends JFrame implements ActionListener
{
	private final JButton btnSuivant;
	private final JButton btnPredecent;
	private final JTextField txtEtapes;
	private final JButton btnValider;

	private JLabel lblEtapes;

	private final JPanel panelScenario;

	private final ControleurScenario ctrl;

	public FrameScenario(ControleurScenario ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Scénario");

		this.setSize(300, 300);
		this.setResizable(false);
		this.setAlwaysOnTop(true);

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.panelScenario = new JPanel();

		this.panelScenario.setLayout(new GridLayout(2, 3));

		this.btnSuivant = new JButton("Suivant");
		this.btnPredecent = new JButton("Précédent");
		this.txtEtapes = new JTextField("0", 10);
		this.btnValider = new JButton("Valider");

		// Créer la fenetre centrée et en bas sur l'écran
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height - this.getSize().height / 2);

		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		/*-------------------------*/
		/* Position des composants */
		/*-------------------------*/
		this.panelScenario.add(this.btnPredecent);
		this.panelScenario.add(this.txtEtapes);
		this.panelScenario.add(this.btnSuivant);
		this.panelScenario.add(new JLabel(""));
		this.panelScenario.add(this.btnValider);
		this.panelScenario.add(new JLabel(""));

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		this.btnPredecent.addActionListener(this);
		this.btnSuivant.addActionListener(this);
		this.btnValider.addActionListener(this);

		this.add(this.panelScenario);
		this.setVisible(true);
		this.pack();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnSuivant)
		{
			this.txtEtapes.setText((Integer.parseInt(this.txtEtapes.getText()) + 1) + "");
			ctrl.effectuerAction((Integer.parseInt(this.txtEtapes.getText())));
		}

		if (e.getSource() == btnPredecent)
		{
			this.txtEtapes.setText((Integer.parseInt(this.txtEtapes.getText()) - 1) + "");
			ctrl.effectuerAction((Integer.parseInt(this.txtEtapes.getText())));
		}

		if (e.getSource() == btnValider)
		{
			ctrl.effectuerAction((Integer.parseInt(this.txtEtapes.getText())));
		}
	}

}
