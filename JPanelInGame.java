
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;


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
	
	//--Panneaux capacites
	JPanel car;
	
	//vie
	final JLabel lblLifeTitle = new JLabel("Vie : ");
	JLabel lblLife;
	
	final JLabel lblCarTitle = new JLabel("Vos caractéristiques :");
	
	final JLabel lblStrenghtTitle    = new JLabel(" - Force : ");
	final JLabel lblSkillsTitle     = new JLabel(" - Adresse : ");
	final JLabel lblResistanceTitle = new JLabel(" - Résistance : ");
	
	final JLabel lblAttackTitle     = new JLabel(" + Attaque : ");
	final JLabel lblDefenseTitle    = new JLabel(" + Défense : ");
	final JLabel lblDodgeTitle      = new JLabel(" + Esquive : ");
	final JLabel lblDamageTitle     = new JLabel(" + Dégâts : ");
	final JLabel lblProtectionTitle = new JLabel(" + Protection : ");
	
	
	
	

	
	
	
	JLabel lblStrenght;
	JLabel lblSkills;
	JLabel lblResistance;
	
	
	
	JLabel lblAttack;
	JLabel lblDefense;
	JLabel lblDodge;
	JLabel lblDamage;
	JLabel lblProtection;
	
	
	//Panneaux action
	JPanel act;
	final JLabel lblActTitre = new JLabel("Vos points d'action : ");
	JLabel lblAct;
	
	//bouton joueur 1
	final JButton buttonMoveUpP1 = new JButton("↑");
	final JButton buttonMoveLeftP1 = new JButton("←");
	final JButton buttonEndP1 = new JButton("Finir") ;
	final JButton buttonMoveRightP1 = new JButton("→");
	final JButton buttonMoveDownP1 = new JButton("↓");
	
	final JButton buttonAttackP1 = new JButton("Attaquer (4PA)") ;
	final JButton buttonUseP1 = new JButton("Utiliser (2PA)") ;
	final JButton buttonPickP1 = new JButton("Ramasser (1PA)") ;
	

	
	
	final JLabel lblPaLeftTitle = new JLabel("PA Restants :");
	JLabel lblPaLeft = new JLabel("30");
	
	
	
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
	
	
	//joueur tour
	private int rounds = 0;
	
	///right pane
	private JPanel rightPane;
	
	
	//Penneau jeu
	private JPanel jeu;
	public Map map ;
	public JPanel txtJeu;
	
	
	//console
	private JTextArea console;
	
	public JPanelInGame() {
		super();
		this.initComposants();
		this.initListeners();
		this.map.getPlayer(0).updateAttributes();
		this.updateComponents(map.getPlayer(0));
		
		
	}
	
	public void initComposants() {
		inGame = new JPanel();
		
		this.setLayout(new BorderLayout());
		this.add(inGame);
		inGame.setLayout(new BorderLayout());
		
		
		inGame.add(buildLeftPane(), BorderLayout.LINE_START);
		inGame.add(buildRightPane(), BorderLayout.CENTER);
				
	}
	
	public JPanel buildLeftPane() {
		leftPane = new JPanel(new BorderLayout());
		pseudo = new JLabel("Joueur", SwingConstants.CENTER);
		pseudo.setFont(new Font("Impact", Font.PLAIN, 30));
		leftPane.add(pseudo, BorderLayout.PAGE_START);
		
		JPanel panCenter = new JPanel(new GridLayout(3,1));
		
		panCenter.add(buildInventaire());
		panCenter.add(buildCaracteristiques());
		panCenter.add(buildActions());
		
		leftPane.add(panCenter, BorderLayout.CENTER);
		
		
		JPanel paLeftPane = new JPanel();
		paLeftPane.add(lblPaLeftTitle);
		paLeftPane.add(lblPaLeft);
		leftPane.add(paLeftPane, BorderLayout.PAGE_END);
		return leftPane;
	}
	
	public JPanel buildInventaire() {
		inv = new JPanel(new BorderLayout());		
		//Inventaire
		inv.add(lblInv, BorderLayout.PAGE_START);
		txtInv = new JTextArea("Aucun objet pour le moment");
		txtInv.setEditable(false);
		inv.add(txtInv, BorderLayout.CENTER);
		
		return inv;
	}
	
	public JPanel buildCaracteristiques() {
		car = new JPanel(new GridLayout(-1,2));
		car.add(lblCarTitle);
		car.add(new JLabel());
		
		//Vie
		lblLifeTitle.setToolTipText("Vie restante de votre personnage. Maximum à 100");
		car.add(lblLifeTitle);
		lblLife = new JLabel();
		car.add(lblLife);
		
		//Force
		lblStrenghtTitle.setToolTipText("Force brute de votre personnage");
		car.add(lblStrenghtTitle);
		lblStrenght = new JLabel();
		car.add(lblStrenght);
		
		//Adresse
		lblSkillsTitle.setToolTipText("L'adresse de votre personnage, vous permet d'esquiver une attaque");
		car.add(lblSkillsTitle);
		lblSkills = new JLabel();
		car.add(lblSkills);
		
		//Resistance
		lblResistanceTitle.setToolTipText("Défense naturelle aux coups");
		car.add(lblResistanceTitle);
		lblResistance = new JLabel();
		car.add(lblResistance);
		
		
		
		

		//Attaque
		lblAttackTitle.setToolTipText("Dégats de l'arme équipée");
		car.add(lblAttackTitle);
		lblAttack = new JLabel();
		car.add(lblAttack);
		
		//Défense
		lblDefenseTitle.setToolTipText("Dégfense de l'armure équipée");
		car.add(lblDefenseTitle);
		lblDefense = new JLabel();
		car.add(lblDefense);		
		
		//Esquive
		lblDodgeTitle.setToolTipText("Capacité d'esquiver une attaque (adresse - encombrement)");
		car.add(lblDodgeTitle);
		lblDodge = new JLabel(); 
		car.add(lblDodge);

		
		//Dégats
		lblDamageTitle.setToolTipText("Dégats infligés (Force + attaque)");
		car.add(lblDamageTitle);
		lblDamage = new JLabel();
		car.add(lblDamage);
		
		//Initiative
		lblProtectionTitle.setToolTipText("Protection finale ((Résistance + défense)/2)");
		car.add(lblProtectionTitle);
		lblProtection = new JLabel();
		car.add(lblProtection);
		
		return car;
	}
	
	public JPanel buildActions() {
		act = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 1;
		c.gridy = 0;
		act.add(buttonMoveUpP1, c);
		
		c.gridx = 0;
		c.gridy = 1;
		act.add(buttonMoveLeftP1, c);
		
		c.gridx = 1;
		c.gridy = 1;
		act.add(buttonEndP1, c);
		
		c.gridx = 2;
		c.gridy = 1;
		act.add(buttonMoveRightP1, c);
		
		c.gridx = 1;
		c.gridy = 2;
		act.add(buttonMoveDownP1, c);
		
		c.gridx = 0;
		c.gridy = 3;	
		c.gridwidth = 3;
		act.add(buttonAttackP1, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 3;
		act.add(buttonUseP1, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 3;
		act.add(buttonPickP1, c);
		
		return act;
	}

	public JPanel buildRightPane() {		
		rightPane = new JPanel();
		rightPane.add(buildJeu());			
		return rightPane;
		
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
	
	public JScrollPane buildConsole() {
		
		console = new JTextArea();
		console.setOpaque(false);
		console.setBackground(new Color(0,0,0,0));

		JScrollPane scrollPane = new JScrollPane(console);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		
		return scrollPane;
		
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
		//int wImg = txtJeu.getWidth() / this.map.getWidth();
		//int hImg =  txtJeu.getHeight() / this.map.getHeight();
	
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
					File temp = new File("img/64/" + obj.getImage());
			        ImageIcon icon;
					
					if (temp.exists()) {
						icon  = new ImageIcon("img/64/" + obj.getImage());
					} else {
						icon  = new ImageIcon("img/64/unknow.png");
					}
			         
					
					
					
					Image image = icon.getImage();
					Image modifierImage = image.getScaledInstance(41,41, java.awt.Image.SCALE_FAST);
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
		
		this.buttonMoveLeftP1.addActionListener(new Listener(MOVE_ZONE, LEFT_BUTTON));
		this.buttonMoveUpP1.addActionListener(new Listener(MOVE_ZONE, UP_BUTTON));
		this.buttonMoveDownP1.addActionListener(new Listener(MOVE_ZONE, DOWN_BUTTON));
		this.buttonMoveRightP1.addActionListener(new Listener(MOVE_ZONE, RIGHT_BUTTON));
		
	
		this.buttonAttackP1.addActionListener(new Listener(ACTION_ZONE, ATTACK_BUTTON));
		this.buttonUseP1.addActionListener(new Listener(ACTION_ZONE, USE_BUTTON));
		this.buttonPickP1.addActionListener(new Listener(ACTION_ZONE, PICK_BUTTON));
		this.buttonEndP1.addActionListener(new Listener(ACTION_ZONE, END_BUTTON));
	
	}
	public void disableAllButtons() {
		this.buttonMoveLeftP1.setEnabled(false);
		this.buttonMoveUpP1.setEnabled(false);
		this.buttonMoveDownP1.setEnabled(false);
		this.buttonMoveRightP1.setEnabled(false);
		
		this.buttonAttackP1.setEnabled(false);
		this.buttonUseP1.setEnabled(false);
		this.buttonPickP1.setEnabled(false);
		this.buttonEndP1.setEnabled(false);
	}
	public void enableAllButtons() {
		this.buttonMoveLeftP1.setEnabled(true);
		this.buttonMoveUpP1.setEnabled(true);
		this.buttonMoveDownP1.setEnabled(true);
		this.buttonMoveRightP1.setEnabled(true);
		
		this.buttonAttackP1.setEnabled(true);
		this.buttonUseP1.setEnabled(true);
		this.buttonPickP1.setEnabled(true);
		this.buttonEndP1.setEnabled(true);
	}
	
	
	public void updateComponents(Player player) {
		int currentPa = player.getActionPoint();
		this.enableAllButtons();
		
		if (currentPa < 4) {
			this.buttonAttackP1.setEnabled(false);
		}
		if (currentPa < 3) {
			this.buttonMoveLeftP1.setEnabled(false);
			this.buttonMoveUpP1.setEnabled(false);
			this.buttonMoveDownP1.setEnabled(false);
			this.buttonMoveRightP1.setEnabled(false);
		}
		if (currentPa < 2) {
			this.buttonUseP1.setEnabled(false);
			this.buttonPickP1.setEnabled(false);
		}
		
		//Actualse le pseudo
		pseudo.setText(player.getName());
		
		if (player.isDead()) {
			txtInv.setText("!! Joueur mort !!");
			disableAllButtons();
			
		} else {
			//Actualise l'inventaiore
			txtInv.setText(player.getInventory().toString());
		}
		
		
		//Actualise al vie
		lblLife.setText(Integer.toString(player.getLife()));
		
		//Actualise les compétences
		lblStrenght.setText(Integer.toString(player.getStrenght()));
		lblSkills.setText(Integer.toString(player.getSkill()));
		lblResistance.setText(Integer.toString(player.getResistance()));
		
		//Actualise les trucs là jsp le nom
		lblAttack.setText(Integer.toString(player.getAttack()));
		lblDefense.setText(Integer.toString(player.getDefense()));
		lblDodge.setText(Integer.toString(player.getDodge()));
		lblDamage.setText(Integer.toString(player.getDamage()));
		lblProtection.setText(Integer.toString(player.getProtection()));
		
		
		
		
		//actualise le PA
		lblPaLeft.setText(Integer.toString(player.getActionPoint()));
	
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
			int currentPlayer = rounds%map.getPlayers().size();
			
			switch (zone) {
				case GAME_ZONE: 
					System.out.println(object.getName());
					
			
				break;
				case MOVE_ZONE:
					//ZONE MOVE (Haut, bas, gauche, droite)
					//Metgode qui dit si l'entiry peut se déplacer dans une direction
					//"bouton" contient directement la direction ("UP","DOWN","LEFT" et "RIGHT")
					if (map.canMove(map.getPlayer(currentPlayer), bouton)) {
						//Si on peut se déplacer, on met un "path" à la position du joueur grace à la méthode
						map.setPath(map.getPlayer(currentPlayer).getXPos(), map.getPlayer(currentPlayer).getYPos());
						//On déplacer le joueur dans le sens "bouton" dans une direction de 1
						map.getPlayer(currentPlayer).move(bouton,1);
						
					}
					map.getPlayer(currentPlayer).removeActionPoint(2);
					System.out.println("[OUT] Player " + bouton + " button");
				break;
				case ACTION_ZONE:
					switch (bouton) {
					case ATTACK_BUTTON:
						//On extrait TOUS les monstres autour du joueur (parametre) dans un rayon défini (parametre)
						// Mathode : map.getMobsRange(Joueur j, int range)
						ArrayList<Mob> mobsAround = map.getMobsRange(map.getPlayer(currentPlayer), map.getPlayer(currentPlayer).getInventory().getEquipedWeapon().getRange());
						ArrayList<Player> playersAround = map.getPlayersRange(map.getPlayer(currentPlayer), map.getPlayer(currentPlayer).getInventory().getEquipedWeapon().getRange());
						
						
						
						/*
						//Pour chaque mob autour (On pourrais changer et donner pour uniquement le 1ER mob ou UN MOB aléatori dans la liste
						//Pour faire en sorte que le personnage en attaque qu'un seul
						//Ou faire en sorte que ça dépende de l'arme (ex : épée peut dégat de zone, arc non)
						for (int i = 0; i < mobsAround.size(); i++) {
							//on attaque le Mob en foncion des dégats de l'amrme équipée par le joueur
							mobsAround.get(i).attack(map.getPlayer(currentPlayer).getInventory().getEquipedWeapon().getDamages());
							
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
		
							playersAround.get(0).attack(map.getPlayer(currentPlayer));
							
							
							if (playersAround.get(0).isDead()) {
	
								map.getPlayers().remove(playersAround.get(0));
								
								map.setPath(playersAround.get(0).getXPos(), playersAround.get(0).getYPos());
							}
							
							
						} else if (mobsAround.size() != 0) {
							mobsAround.get(0).attack(map.getPlayer(currentPlayer));
							
							if (mobsAround.get(0).isDead()) {
								//Si il est mot on enlve le mob de la liste
								map.getMobs().remove(mobsAround.get(0));
								//Et on place un chemin à sa place
								map.setPath(mobsAround.get(0).getXPos(), mobsAround.get(0).getYPos());
							}
						}
							
						
						
						map.getPlayer(currentPlayer).removeActionPoint(4);
						System.out.println("[OUT] Attack button");
					break;
					case USE_BUTTON:
							
						
						
						JFrame b = map.getPlayer(currentPlayer).getInventory().toWindow();
						b.setVisible(true);

						
						
						
						System.out.println("[OUT] Use button");
					break;
					case PICK_BUTTON:
						// MEME LOGIQUE QUE POUR LES MOBS
						 
						//On regarede tous les items autour d'un jouer
						ArrayList<Item> itemsAround = map.getItemsRange(map.getPlayer(currentPlayer), 1);
						
						
						//Pour chaque item
						for (int i = 0; i < itemsAround.size(); i++) {
							
							//On vérifie  qu'on a le PA suffisant
							if (map.getPlayer(currentPlayer).getActionPoint() >= 2) {
									
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
											map.getPlayer(currentPlayer).getInventory().addItem(map.getPlayer(currentPlayer).getInventory().getEquipedWeapon());
											//On met l'arme ramassée en tant qu'arme équipée
											map.getPlayer(currentPlayer).getInventory().setEquipedWeapon(itemsAround.get(i));
										} else {
											//Même chose pour l'armure
											map.getPlayer(currentPlayer).getInventory().addItem(map.getPlayer(currentPlayer).getInventory().getEquipedArmor());
											map.getPlayer(currentPlayer).getInventory().setEquipedArmor(itemsAround.get(i));
										}
									  
									} else {
										//SI il ne veut pas remplcer l'item, on le met dans l'inventaire
										map.getPlayer(currentPlayer).getInventory().addItem(itemsAround.get(i));
									}
									
								} else {
									//Si ça n'est pas une arme ou armure
									map.getPlayer(currentPlayer).getInventory().addItem(itemsAround.get(i));
								}
								
								//on enleve le pa
								map.getPlayer(currentPlayer).removeActionPoint(2);
								
								// on enleve l'item
								map.getItems().remove(itemsAround.get(i));
						
							}
						}
						
						//Maj du PA
						
						
						System.out.println("[OUT] Pickup button");
					break;
					case END_BUTTON:
						System.out.println("[OUT] End button");
					break;
				}
				break;
				
			}

			
			if (zone == ACTION_ZONE && bouton == END_BUTTON) {
				map.update();
				
				//Déplacement des mobs
				map.nextRound(map);
				
				if (map.isGameFinished()) {
					disableAllButtons();
					System.out.println("partie finie");
				} else {
					Player current = map.getPlayer((rounds+1)%map.getPlayers().size());
					
					//On ajoute les PA si le max est pas franchi et que ca n'est pas le 1er tour
					if (rounds != 0 && current.getActionPoint() < 30 ) {
						if (current.getActionPoint() + 3 > 30) {
							current.setActionPoint(30);
						} else {
							current.addActionPoint(3);
						}
					}
					
					//On met à jour les afficahges (joueure, inventaire, dégats et tt)
					updateComponents(map.getPlayer((rounds+1)%map.getPlayers().size()));
					
					//On maj la carte
					updateGame();
					
					//on met à jour les attributs
					map.getPlayer((rounds)%map.getPlayers().size()).updateAttributes();
					
					//on passe au tour suivant
					rounds++;
				}
				
			} else {
				map.getPlayer((rounds)%map.getPlayers().size()).updateAttributes();
				map.update();
				updateGame();
				updateComponents(map.getPlayer((rounds)%map.getPlayers().size()));
			}
			
			
		}
	}
}
