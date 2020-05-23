package projet_azzouz_saidoun;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.xml.bind.Marshaller.Listener;

//import java.awt.GridLayout;

//import javax.swing.*;

public class Player extends Entity {
	private int strenght;
	private int skill;
	private int resistance;
	private Inventory inventory;
	
	
	SpinnerNumberModel spiModelStrenght;
	SpinnerNumberModel spiModelSkills;
	SpinnerNumberModel spiModelResistance;
	JDialog characteristics;
	
	public Player(String name, String image, int xPos, int yPos) {
		super(name, image, xPos, yPos, 10 , 10, 10, 10, 10, 100);
		this.inventory = new Inventory(this);
		this.initCharacteristics();
		this.strenght = (int) spiModelStrenght.getValue();
		this.skill = (int) spiModelSkills.getValue();;
		this.resistance = (int) spiModelResistance.getValue();;
		
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
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public void use(Consumable consumable) {
		this.setLife(this.getLife() + consumable.getLifeChange());
	}
	
	private void initCharacteristics() {
		characteristics = new JDialog();
		characteristics.setTitle("Choix des caracteristiques pour : " + this.name);
		characteristics.setModal (true);
		characteristics.setAlwaysOnTop (true);
		characteristics.setModalityType (ModalityType.APPLICATION_MODAL);
		characteristics.setEnabled(true);
		characteristics.setBounds(500,500,500,200);
		JPanel main = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		

		characteristics.add(main);
		
		//Titre compétece
		final JLabel title = new JLabel("Répartissez vos 18 points entres les 3 compétences");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		main.add(title, c);
		
		//spiners
		
		JLabel lblStrenght = new JLabel("Force :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		main.add(lblStrenght, c);
		
		spiModelStrenght = new SpinnerNumberModel(0,0,18,1);
		JSpinner spiStrenght = new JSpinner(spiModelStrenght);
		spiStrenght.setEditor(new JSpinner.DefaultEditor(spiStrenght));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		main.add(spiStrenght, c);
		
		JLabel lblSkills = new JLabel("Adresse :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		main.add(lblSkills, c);
		
		spiModelSkills = new SpinnerNumberModel(0,0,18,1);
		JSpinner spiSkills = new JSpinner(spiModelSkills);
		spiSkills.setEditor(new JSpinner.DefaultEditor(spiSkills));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		main.add(spiSkills, c);
		
		
		JLabel lblResistance = new JLabel("Résistance :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		main.add(lblResistance, c);
		
		spiModelResistance = new SpinnerNumberModel(0,0,18,1);
		JSpinner spiResistance = new JSpinner(spiModelResistance);
		spiResistance.setEditor(new JSpinner.DefaultEditor(spiResistance));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		main.add(spiResistance, c);
		
		
		//Buton valiuder
		final JButton button = new JButton("Valider");
		button.addActionListener(new Listener(0));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 0;
		main.add(button, c);
		
		
		
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
			}
		}
	}
}
