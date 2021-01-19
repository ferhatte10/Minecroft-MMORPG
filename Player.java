
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.event.*;

public class Player extends Entity {
	private int strenght;
	private int skill;
	private int resistance;
	private int actionPoint;
	private int experience;
	
	private static int DEFAUT_ACTION_POINT = 30;
	
	private Inventory inventory;
	
	
	SpinnerNumberModel spiModelStrenght;
	SpinnerNumberModel spiModelSkills;
	SpinnerNumberModel spiModelResistance;
	
	JSpinner spiSkills;
	JSpinner  spiStrenght;
	JSpinner  spiResistance;
	
	JDialog characteristics;
	
	JButton button;
	JButton balanced;
	JButton stronger;
	JButton skilled;
	JButton resistant;
	
	private static int SKILLS_POINTS = 18;
	private JLabel remainingPoints;
	
	public Player(String name, String image, int xPos, int yPos) {
		super(name, image, xPos, yPos, 10, 10, 10, 10, 10, 100);
		this.inventory = new Inventory(this);
		this.initCharacteristics();
		this.strenght = (int) spiModelStrenght.getValue();
		this.skill = (int) spiModelSkills.getValue();
		this.resistance = (int) spiModelResistance.getValue();
		this.experience = 0;
		this.actionPoint = DEFAUT_ACTION_POINT;
		
		
		
		
		
		
	}
	
	
	public int getStrenght() {
		return this.strenght;
	}
	public int getSkill() {
		return this.skill;
	}
	public int getResistance() {
		return this.resistance;
	}
	public int getExperience() {
		return this.experience;
	}
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public int getActionPoint() {
		return this.actionPoint;
	}
	
	public void setActionPoint(int points) {
		this.actionPoint = points;
	}
	public void removeActionPoint(int points) {
		this.actionPoint -= points;
	}
	public void addActionPoint(int points) {
		this.actionPoint += points;
	}
	
	public void use(Consumable consumable) {
		this.setLife(this.getLife() + consumable.getLifeChange());
		this.actionPoint += consumable.getPaChange();
		this.strenght += consumable.getStrenghChange();
		
	}
	
	public void updateAttributes() {
		this.setAttaque(this.getInventory().getEquipedWeapon().getDamages());
		this.setDefense(this.getInventory().getEquipedArmor().getProtection());
		this.setDodge(this.skill - this.getOvercrowded());
		this.setDamage(this.strenght + this.getAttack());
		this.setProtection((this.resistance + this.getDefense())/2);

	}
	
	
	
	public int getOvercrowded() {
		return this.getInventory().getOvercrowded();
	}
	
	private void initCharacteristics() {
		characteristics = new JDialog();
		characteristics.setTitle("Choix des caracteristiques pour : " + this.name);
		characteristics.setModal (true);
		characteristics.setAlwaysOnTop (true);
		characteristics.setModalityType (ModalityType.APPLICATION_MODAL);
		characteristics.setEnabled(true);
		characteristics.setBounds(500,500,700,370);
		characteristics.setDefaultCloseOperation(0);
		
		JPanel main = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		//listener de changement du spiner
		
		class SliderListener implements ChangeListener {
		    public void stateChanged(ChangeEvent e) { 

		    	int somme = (int) spiStrenght.getValue() +  (int) spiSkills.getValue() + (int) spiResistance.getValue();
		        if (somme == SKILLS_POINTS) {
		        	button.setEnabled(true);
		        	remainingPoints.setText("Vous avez attribué tous les points.");
		        } else {
		        	button.setEnabled(false);
		        	if (somme > SKILLS_POINTS) {
		        		remainingPoints.setText("Vous avez attribué " +  String.valueOf(somme - SKILLS_POINTS) + " points en trop.");
					} else {
						remainingPoints.setText("Il reste encore " +  String.valueOf(SKILLS_POINTS-somme) + " points à attribuer.");
					}
	
		        }
		
		    }
		}
		
		

		characteristics.add(main);
		
		//Titre compétece
		final JLabel title = new JLabel("Répartissez vos 18 points entres les 3 compétences");
		title.setFont(new Font("Arial",Font.PLAIN, 20));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 20,0);
		main.add(title, c);
		
		//spiners
		//force
		JLabel lblStrenght = new JLabel("Force :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0,0);
		main.add(lblStrenght, c);
		
		spiModelStrenght = new SpinnerNumberModel(0,0,SKILLS_POINTS,1);
		spiModelStrenght.addChangeListener(new SliderListener());
		spiStrenght = new JSpinner(spiModelStrenght);
		spiStrenght.setEditor(new JSpinner.DefaultEditor(spiStrenght));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		main.add(spiStrenght, c);
		
		//adresse
		JLabel lblSkills = new JLabel("Adresse :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		main.add(lblSkills, c);
		
		spiModelSkills = new SpinnerNumberModel(0,0,SKILLS_POINTS,1);
		spiModelSkills.addChangeListener(new SliderListener());
		spiSkills = new JSpinner(spiModelSkills);
		spiSkills.setEditor(new JSpinner.DefaultEditor(spiSkills));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		main.add(spiSkills, c);
		
		
		//Résistance
		JLabel lblResistance = new JLabel("Résistance :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 20,0);
		main.add(lblResistance, c);
		
		

		spiModelResistance = new SpinnerNumberModel(0,0,SKILLS_POINTS,1);
		spiModelResistance.addChangeListener(new SliderListener());
		spiResistance = new JSpinner(spiModelResistance);
		spiResistance.setEditor(new JSpinner.DefaultEditor(spiResistance));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 20,0);
		main.add(spiResistance, c);
		//configurations par defaut
		
		

		balanced = new JButton("Equilibré");
		balanced.addActionListener(new Listener(1));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 0,0);
		main.add(balanced, c);
		
		stronger = new JButton("Fort");
		stronger.addActionListener(new Listener(2));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 20,0);
		main.add(stronger, c);
		
		skilled = new JButton("Compétant");
		skilled.addActionListener(new Listener(3));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 20,0);
		main.add(skilled, c);
		
		resistant = new JButton("Résistant");
		resistant.addActionListener(new Listener(4));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 6;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 20,0);
		main.add(resistant, c);
		
		
		
		
		//Buton valiuder
		button = new JButton("Valider");
		button.addActionListener(new Listener(0));
		button.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 0;
		main.add(button, c);
		
		remainingPoints = new JLabel("Il reste encore " +  String.valueOf(SKILLS_POINTS) + " points à attribuer.");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 2;
		main.add(remainingPoints, c);
		
		
		
		
		
		characteristics.setVisible(true);	
		
	}
	
	
	
	   
	 
	
	class Listener implements ActionListener {
		private int bouton;
		public Listener(int bouton) {
			this.bouton = bouton;
		}
		
		public void actionPerformed(ActionEvent e) {
			switch (bouton) {
				case 0:
					characteristics.dispose();
					characteristics.setVisible(false);
				break;	
				case 1:
					spiResistance.setValue(6);
					spiSkills.setValue(6);
					spiStrenght.setValue(6);
				break;
				case 2:
					spiResistance.setValue(10);
					spiSkills.setValue(4);
					spiStrenght.setValue(4);					
				break;
				case 3:
					spiResistance.setValue(4);
					spiSkills.setValue(10);
					spiStrenght.setValue(4);
				break;
				case 4:
					spiResistance.setValue(4);
					spiSkills.setValue(4);
					spiStrenght.setValue(10);
				break;
				
			}
		}
	}
}
