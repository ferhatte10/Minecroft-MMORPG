package projet_azzouz_saidoun;

import java.awt.*;
import javax.swing.*;

public class JPanelMenu extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel menu;

	static final JPanel boutons = new JPanel();
	static final JButton boutonCommencer = new JButton("Commencer à jouer");
	
	static final int BOUTON_COMMENCER = 0;
	
	public JPanelMenu() {
		super();

		
	}
	
	public void initComposants() {
		
		menu = new JPanel();
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(menu));
		menu.setLayout(new BorderLayout());
		
		
		menu.add(buildBoutons(), BorderLayout.LINE_START);
	}
	public JPanel buildBoutons() {
		
		boutons.add(boutonCommencer);
		boutonCommencer.setOpaque(false);
		return boutons;
	}
	
	
	
}
