package projet_azzouz_saidoun;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListDataListener;




public class JPanelInGame extends JPanel {
	/* CETTE CLASSE EST REPRISE DU TP SWING
	 * On créer nos variables
	 * On appelle la méthode initComposants qui appele :
	 * buildLeftPane(), buildActions() et buildJeu()
	 * 
	 * On appelle ensuite initListeners() qui initialise les boutons sur boutons des actions :
	 * Déplacements (Haut, bas, gauche, droite, ne rien faire), attaquer, utiliser et ramasser	
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel inGame;
	
	private JPanel leftPane;
	//Panneau inventaire
	private JLabel pseudo;
	
	private JPanel inv;
	
	final JLabel lblInv = new JLabel("Objets dans l'inventaire");
	private JTextArea txtInv;
	
	//Panneaux capacites
	JPanel car;
	
	final JLabel lblLifeTitle = new JLabel("Vie : ");
	JLabel lblLife;
	
	final JLabel lblCarTitle = new JLabel("Vos caractéristiques :");
	
	final JLabel lblStrenghtTitle    = new JLabel(" - Force : ");
	final JLabel lblSkillsTitle     = new JLabel(" - Adresse : ");
	final JLabel lblResistanceTitle = new JLabel(" - Résistance : ");
	
	
	final JLabel lblInitiativeTitle = new JLabel(" + Initiative : ");
	final JLabel lblAttackTitle     = new JLabel(" + Attaque : ");
	final JLabel lblDodgeTitle      = new JLabel(" + Esquive : ");
	final JLabel lblDefenseTitle    = new JLabel(" + Défense : ");
	final JLabel lblDamageTitle     = new JLabel(" + Dégâts : ");

	
	
	
	JLabel lblStrenght;
	JLabel lblSkills;
	JLabel lblResistance;
	
	
	JLabel lblInitiative;
	JLabel lblAttack;
	JLabel lblDodge;
	JLabel lblDefense;
	JLabel lblDamage;
	
	
	//Panneaux action
	JPanel act;
	final JLabel lblActTitre = new JLabel("Vos points d'action : ");
	JLabel lblAct;
	final JButton boutonDeplacerGauche = new JButton("←");
	final JButton boutonDeplacerHaut = new JButton("↑");
	final JButton boutonDeplacerBas = new JButton("↓");
	final JButton boutonDeplacerDroite = new JButton("→");
	
	final JButton boutonAttaquer = new JButton("Attaquer") ;
	final JButton boutonUtiliser = new JButton("Utiliser") ;
	final JButton boutonRamasser = new JButton("Ramasser") ;
	final JButton boutonFin = new JButton("Finir tour") ;
	
	static final String MOVE_ZONE = "MOVE";
	
	static final String LEFT_BUTTON = "LEFT";
	static final String UP_BUTTON = "UP";
	static final String DOWN_BUTTON = "DOWN";
	static final String RIGHT_BUTTON = "RIGHT";

	
	static final String ACTION_ZONE = "ACTION";
	
	static final String ATTACK_BUTTON = "ATTACK";
	static final String USE_BUTTON = "USE";
	static final String PICK_BUTTON = "PICK";
	static final String END_BUTTON = "END";
	
	static final String GAME_ZONE = "GAME";
	
	
	
	//Penneau jeu
	JPanel jeu;
	public Map map ;
	public JPanel txtJeu;
	
	
	public JPanelInGame() {
		super();
		this.initComposants();
		this.initListeners();
		this.updateComponents(map.getPlayer(0));
		
		
	}
	
	public void initComposants() {
		inGame = new JPanel();
		
		this.setLayout(new BorderLayout());
		this.add(inGame);
		inGame.setLayout(new BorderLayout());
		
		
		inGame.add(buildLeftPane(), BorderLayout.LINE_START);
		inGame.add(buildActions(), BorderLayout.PAGE_END);
		inGame.add(buildJeu(), BorderLayout.CENTER );
	}
	
	public JPanel buildLeftPane() {
		leftPane = new JPanel(new GridLayout(3,1));
		pseudo = new JLabel("Joueur");
		leftPane.add(pseudo);
		leftPane.add(buildInventaire());
		leftPane.add(buildCaracteristiques());
		return leftPane;
	}
	
	public JPanel buildInventaire() {
		inv = new JPanel(new GridLayout(-1,1));		
		//Inventaire
		inv.add(lblInv);
		txtInv = new JTextArea("Aucun objet pour le moment");
		txtInv.setEditable(false);
		inv.add(txtInv);
		
		return inv;
	}
	
	public JPanel buildCaracteristiques() {
		car = new JPanel(new GridLayout(-1,2));
		car.add(lblCarTitle);
		car.add(new JLabel());
		
		//Vie
		car.add(lblLifeTitle);
		lblLife = new JLabel();
		car.add(lblLife);
		
		//Force
		car.add(lblStrenghtTitle);
		lblStrenght = new JLabel();
		car.add(lblStrenght);
		
		//Adresse
		car.add(lblSkillsTitle);
		lblSkills = new JLabel();
		car.add(lblSkills);
		//Resistance
		
		
		//Endurance
		car.add(lblResistanceTitle);
		lblResistance = new JLabel();
		car.add(lblResistance);
		
		//Initiative
		car.add(lblInitiativeTitle);
		lblInitiative = new JLabel();
		car.add(lblInitiative);
		
		//Attaque
		car.add(lblAttackTitle);
		lblAttack = new JLabel();
		car.add(lblAttack);
		
		car.add(lblDodgeTitle);
		lblDodge = new JLabel();
		car.add(lblDodge);

		
		//Défense
		car.add(lblDefenseTitle);
		lblDefense = new JLabel();
		car.add(lblDefense);
		
		//Dégats
		car.add(lblDamageTitle);
		lblDamage = new JLabel();
		car.add(lblDamage);
		
		return car;
	}
	
	public JPanel buildActions() {
		act = new JPanel();
		act.add(boutonDeplacerGauche);
		act.add(boutonDeplacerHaut);
		act.add(boutonDeplacerBas);
		act.add(boutonDeplacerDroite);
		
		act.add(boutonAttaquer);
		act.add(boutonUtiliser);
		act.add(boutonRamasser);
		act.add(boutonFin);
		return act;
	}
	
	public JPanel buildJeu() {
		jeu = new JPanel();
		map = new Map();
		
		txtJeu = new JPanel() {
			//On ajoute une image de fond pour le design ^^ (lalilah bogoss)
			private static final long serialVersionUID = -2458428087359653242L;
			final ImageIcon imageIcon = new ImageIcon("./img/path.png");
			Image image = imageIcon.getImage();
			{
				setOpaque(false);
			}  
    	
			public void paintComponent (Graphics g) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				super.paintComponent(g);
			}
		};
		updateGame();
		
		return jeu;
	}
	
 	public void updateGame() {
 		txtJeu.removeAll();
 		//txtJeu.setBounds(0,0,1000,4000);
 		txtJeu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		
		txtJeu.setBackground(new Color(020200020, false));
		int wImg = txtJeu.getWidth() / this.map.getWidth();
		int hImg =  txtJeu.getHeight() / this.map.getHeight();
	
		//System.out.println(wImg);
		//System.out.println(hImg);
		
		for (int i = 0; i < this.map.getWidth(); i++) {
			for (int j = 0; j < this.map.getHeight(); j++) {
				gbc.gridx = i;
				gbc.gridy = j;
				JTextPane carte = new JTextPane();
				Object obj = this.map.getObject(j, i);
				carte.setOpaque(false);
				
				
				
				if (!(obj instanceof Path))  {
					ImageIcon icon  = new ImageIcon("img/16/" + obj.getImage());
					Image image = icon.getImage();
					Image modifierImage = image.getScaledInstance(20,20, java.awt.Image.SCALE_FAST);
					icon = new ImageIcon(modifierImage) ;
					
					carte.insertIcon(icon);	
				
				
				}
				
				
				carte.setEditable(false);
				txtJeu.add(carte, gbc);
			}
			
			
		}
		
		
		
		
		
		jeu.setLayout(new BorderLayout());
		jeu.add(new JScrollPane(txtJeu));
 	}
	
	
	public void initListeners() {
		
		this.boutonDeplacerGauche.addActionListener(new Listener(MOVE_ZONE, LEFT_BUTTON));
		this.boutonDeplacerHaut.addActionListener(new Listener(MOVE_ZONE, UP_BUTTON));
		this.boutonDeplacerBas.addActionListener(new Listener(MOVE_ZONE, DOWN_BUTTON));
		this.boutonDeplacerDroite.addActionListener(new Listener(MOVE_ZONE, RIGHT_BUTTON));
		
	
		this.boutonAttaquer.addActionListener(new Listener(ACTION_ZONE, ATTACK_BUTTON));
		this.boutonUtiliser.addActionListener(new Listener(ACTION_ZONE, USE_BUTTON));
		this.boutonRamasser.addActionListener(new Listener(ACTION_ZONE, PICK_BUTTON));
		this.boutonFin.addActionListener(new Listener(ACTION_ZONE, END_BUTTON));
	
	}
	
	public void updateComponents(Player player) {
		//Actualse le pseudo
		pseudo.setText(player.getName());
		
		//Actualise l'inventaiore
		txtInv.setText(player.getInventory().toString());
		
		//Actualise al vie
		lblLife.setText(Integer.toString(player.getLife()));
		
		//Actualise les compétences
		lblStrenght.setText(Integer.toString(player.getStrenght()));
		lblSkills.setText(Integer.toString(player.getSkill()));
		lblResistance.setText(Integer.toString(player.getResistance()));
		
		//Actualise les trucs là jsp le nom
		lblDodge.setText(Integer.toString(player.getDodge()));
		lblInitiative.setText(Integer.toString(player.getInitiative()));
		lblAttack.setText(Integer.toString(player.getAttack()));
		lblDefense.setText(Integer.toString(player.getDefense()));
		lblDamage.setText(Integer.toString(player.getDamage()));
	
	}
	
	class MouseAdapterMod extends MouseAdapter {

		   public void mousePressed(MouseEvent e) {
		       JTextPane btnPanel = (JTextPane)e.getSource();
		       System.out.println(btnPanel.getToolTipText());
		   }
		}  
	
	class Listener implements ActionListener {
		private String zone;
		private String bouton;
		private Object object;
		
		public Listener(String z, String c) {
			this.zone = z;
			this.bouton = c;
			this.object = null;
		}
		
		
		public Listener(String z, String c, Object object) {
			this.zone = z;
			this.bouton = c;
			this.object = object;
		}
		
		
		public void actionPerformed(ActionEvent e) {
			switch (zone) {
				case GAME_ZONE: 
					System.out.println(object.getName());
					
			
				break;
				case MOVE_ZONE:
					//ZONE MOVE (Haut, bas, gauche, droite)
					//Metgode qui dit si l'entiry peut se déplacer dans une direction
					//"bouton" contient directement la direction ("UP","DOWN","LEFT" et "RIGHT")
					if (map.canMove(map.getPlayer(0), bouton)) {
						//Si on peut se déplacer, on met un "path" à la position du joueur grace à la méthode
						map.setPath(map.getPlayer(0).getXPos(), map.getPlayer(0).getYPos());
						//On déplacer le joueur dans le sens "bouton" dans une direction de 1
						map.getPlayer(0).move(bouton,1);
						
					}
					
					System.out.println("[OUT] Player " + bouton + " button");
				break;
				case ACTION_ZONE:
					switch (bouton) {
					case ATTACK_BUTTON:
						//On extrait TOUS les monstres autour du joueur (parametre) dans un rayon défini (parametre)
						// Mathode : map.getMobsRange(Joueur j, int range)
						ArrayList<Mob> mobsAround = map.getMobsRange(map.getPlayer(0), map.getPlayer(0).getInventory().getEquipedWeapon().getRange());
						ArrayList<Player> playersAround = map.getPlayersRange(map.getPlayer(0), map.getPlayer(0).getInventory().getEquipedWeapon().getRange());
						
						/*
						//Pour chaque mob autour (On pourrais changer et donner pour uniquement le 1ER mob ou UN MOB aléatori dans la liste
						//Pour faire en sorte que le personnage en attaque qu'un seul
						//Ou faire en sorte que ça dépende de l'arme (ex : épée peut dégat de zone, arc non)
						for (int i = 0; i < mobsAround.size(); i++) {
							//on attaque le Mob en foncion des dégats de l'amrme équipée par le joueur
							mobsAround.get(i).attack(map.getPlayer(0).getInventory().getEquipedWeapon().getDamages());
							
							//On vériie si le mob est mort grace a la methoide
							if (mobsAround.get(i).isDead()) {
								//Si il est mot on enlve le mob de la liste
								map.getMobs().remove(mobsAround.get(i));
								//Et on place un chemin à sa place
								map.setPath(mobsAround.get(i).getXPos(), mobsAround.get(i).getYPos());
							}
							
						}
						
						* ON REMPLACE LE FAIT D'ATTAQUEZR TOUS LES MOBS AUTOUR POUR ATTAQUER LE 1ER MOB AUTOUR*/
						
						//On vérifie si la liste contient des joueurs
						if (playersAround.size() != 0) {
		
							playersAround.get(0).attack(map.getPlayer(0).getInventory().getEquipedWeapon().getDamages());
							
							
							if (playersAround.get(0).isDead()) {
	
								map.getPlayers().remove(playersAround.get(0));
								
								map.setPath(playersAround.get(0).getXPos(), playersAround.get(0).getYPos());
							}
							
							
						} else if (mobsAround.size() != 0) {
							mobsAround.get(0).attack(map.getPlayer(0).getInventory().getEquipedWeapon().getDamages());
							
							if (mobsAround.get(0).isDead()) {
								//Si il est mot on enlve le mob de la liste
								map.getMobs().remove(mobsAround.get(0));
								//Et on place un chemin à sa place
								map.setPath(mobsAround.get(0).getXPos(), mobsAround.get(0).getYPos());
							}
						}
							
						
						
						System.out.println("[OUT] Attack button");
					break;
					case USE_BUTTON:
						int nbItemsBefore = map.getPlayer(0).getInventory().getItems().size();
						map.getPlayer(0).getInventory().toWindow();						
						
						
						while (map.getPlayer(0).getInventory().isActive()) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								
							}
						}
						
						
						if (nbItemsBefore != map.getPlayer(0).getInventory().getItems().size()) {
							System.out.println("azdazddaz");
							
							map.update();
							
							//Déplacement des mobs
							map.nextRound(map);
							
							//On met à jour les afficahges (joueure, inventaire, dégats et tt)
							updateComponents(map.getPlayer(0));
							//On maj la carte
							updateGame();
						}
						
						System.out.println("[OUT] Use button");
					break;
					case PICK_BUTTON:
						// MEME LOGIQUE QUE POUR LES MOBS
						 
						//On regarede tous les items autour d'un jouer
						ArrayList<Item> itemsAround = map.getItemsRange(map.getPlayer(0), 1);
						
						
						//Pour chaque item
						for (int i = 0; i < itemsAround.size(); i++) {
							
							//On place un chemin à la place 
							map.setPath(itemsAround.get(i).getXPos(), itemsAround.get(i).getYPos());
							
							//Si l'item est une instance de WEAPON ou ARME 
							if (itemsAround.get(i) instanceof Weapon || itemsAround.get(i) instanceof Armor) {
								
								//On ouvre une boite de dialogue pour savoir si il veut remplacer l'arme ou l'armure équipée par celle ramassé
								int dialogButton = JOptionPane.YES_NO_OPTION;
								int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous remplacer cet item ("+ itemsAround.get(i).getName() + ") par l'actuel ?","Nouvel objet",dialogButton);
								
								//Si oui, on regarde si il s'agit d'un weapon ou d'un armor
								if (dialogResult == JOptionPane.YES_OPTION) {
									if (itemsAround.get(i) instanceof Weapon) {
										//On met l'actuelle arme équipée dans l'inventaire
										map.getPlayer(0).getInventory().addItem(map.getPlayer(0).getInventory().getEquipedWeapon());
										//On met l'arme ramassée en tant qu'arme équipée
										map.getPlayer(0).getInventory().setEquipedWeapon(itemsAround.get(i));
									} else {
										//Même chose pour l'armure
										map.getPlayer(0).getInventory().addItem(map.getPlayer(0).getInventory().getEquipedArmor());
										map.getPlayer(0).getInventory().setEquipedArmor(itemsAround.get(i));
									}
								  
								} else {
									//SI il ne veut pas remplcer l'item, on le met dans l'inventaire
									map.getPlayer(0).getInventory().addItem(itemsAround.get(i));
								}
								
							} else {
								//Si ça n'est pas une arme ou armure
								map.getPlayer(0).getInventory().addItem(itemsAround.get(i));
							}
							
						}
						//Un fois fini, on enleve tous les items de la map
						map.getItems().removeAll(itemsAround);
						
						System.out.println("[OUT] Pickup button");
					break;
					case END_BUTTON:
						System.out.println("[OUT] End button");
					break;
				}
				break;
				
			}

			
			if (!(zone == ACTION_ZONE && bouton == USE_BUTTON)) {
				map.update();
				
				//Déplacement des mobs
				map.nextRound(map);
				
				//On met à jour les afficahges (joueure, inventaire, dégats et tt)
				updateComponents(map.getPlayer(0));
				//On maj la carte
				updateGame();
			}
			
			
		}
	}
}
