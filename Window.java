package projet_azzouz_saidoun;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Window extends JFrame{
	private static final long serialVersionUID = -8417756452763323253L;

	private JPanel main;
	
	private JPanelMenu menu;
	private JPanelInGame inGame;
	
	public Window() {
		super("Jeu 2D trop cool");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setMinimumSize(new Dimension(1280, 720));
		
		main = new JPanel() {
			//On ajoute une image de fond pour le design ^^ (lalilah bogoss)
			private static final long serialVersionUID = -2458428087359653242L;
			final ImageIcon imageIcon = new ImageIcon("bg.png");
			Image image = imageIcon.getImage();
			{
				setOpaque(false);
			}  // instance initializer
    	
			public void paintComponent (Graphics g) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				super.paintComponent(g);
			}
		};
		                                                                                    
		//Page d'accueil
		menu = new JPanelMenu();
		menu.initComposants();
		
		inGame = new JPanelInGame();
		
		
		//Vrai
		/*
		  main.add(menu);
		  
		 
		 
		 */
		
	
		//TEMPORAIORE
		main.setLayout(new BorderLayout());
		main.add(new JScrollPane(inGame));
		
		
		
		
		this.add(main);
		this.initListeners();
		this.setVisible(true);
	}

	
	public void initListeners() {
		JPanelMenu.boutonCommencer.addActionListener(new Listener(0));
	}

	
	
	class Listener implements ActionListener {
		private int bouton;
		public Listener(int bouton) {
			this.bouton = bouton;
		}
		
		public void actionPerformed(ActionEvent e) {
			switch (bouton) {
				case 0:
					//Si on appuie sur le buton "commencer", on créer une instance de JPanelInGame
					//On enleve la JPanel menu et on ajoute inGame
					//Ensutie on actualise main
					//VOIR DESSIN POUR MIEUX COMPRENDRE ^^'
					inGame = new JPanelInGame();
					main.removeAll();
					main.setLayout(new BorderLayout());
					main.add(new JScrollPane(inGame));
					main.updateUI();
				break;				
			}
		}
	}
	
	public static void main(String[] args) {
		new Window();
	}
	
	

		
		
		
	
}







