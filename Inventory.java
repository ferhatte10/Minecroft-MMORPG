package projet_azzouz_saidoun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Inventory {
	private Weapon equipedWeapon = new WeaponHand(-1, -1) ;
	private Armor equipedArmor = new ArmorNike(-1, -1);
	private ArrayList<Item> items;
	private Player owner;
	
	JFrame frame = new JFrame("Inventaire");
	JPanel inv = new JPanel();
	JComboBox<Consumable> itemsOptions = new JComboBox<Consumable>();
	JButton buttonLeave = new JButton("Quitter");
	JButton buttonUse = new JButton("Utiliser");
	
	
	public Inventory(Player player) {
		this.items = new ArrayList<Item>();
		this.owner = player;
		
	}
	public boolean isActive() {
		return frame.isVisible();
	}
	public Player getPlayer() {
		return this.owner;
	}
	
	public Weapon getEquipedWeapon() {
		return this.equipedWeapon;
	}

	public void setEquipedWeapon(Weapon equipedWeapon) {
		this.equipedWeapon = equipedWeapon;
	}
	public void setEquipedWeapon(Item item) {
		this.equipedWeapon = (Weapon) item;
	}

	public Armor getEquipedArmor() {
		return this.equipedArmor;
	}

	public void setEquipedArmor(Armor equipedArmor) {
		this.equipedArmor = equipedArmor;
	}
	public void setEquipedArmor(Item item) {
		this.equipedArmor = (Armor) item;
	}
	
	public Item getItem(int i) {
		return this.items.get(i);
	}
	public ArrayList<Item> getItems() {
		return this.items;
	}
	public void addItem(Item item) {
		this.items.add(item);
	}
	public void addItems(ArrayList<Item> items) {
		for (int i = 0; i < items.size(); i++) {
			this.items.add(items.get(i));
		}
	}
	
	public void toWindow() {
		
		frame.add(inv);
		frame.setBounds(200, 200, 200, 200);
		
		inv.add(new JLabel("Choisir un objet de l'inventaire"));
		
		
		
		
		itemsOptions.removeAllItems();
		
		
		//On ajoute les items ssi il s'agit de Consumable

		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) instanceof Consumable) {
				//On cast en Consumable pour pouvoir avoir les methides après
				itemsOptions.addItem((Consumable) items.get(i));
			}
			
		}
		
		//Si il y a pas 0 item dans la combobox, on affiche la combo, sinon un message
		if (itemsOptions.getItemCount() != 0) {
			inv.add(itemsOptions);
			
			
			buttonUse.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Consumable consumable = (Consumable) itemsOptions.getSelectedItem();
					getPlayer().use(consumable);
					items.remove(consumable);
					frame.setVisible(false);
					
					
							
				}
			});
			
			
			
			inv.add(buttonUse);
			
		} else {
			inv.add(new JLabel("Aucun consomable dans l'inventaire"));
			buttonLeave.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					frame.setVisible(false);
					
				}
			});
			inv.add(buttonLeave);
		}
		
		
		frame.setVisible(true);
		
	}
	
	public String toString() {
		// permet l'affichage de l'inventaire
		
		//L'arme équipée
		String inv = "Arme : ";
		String weapon = "aucune arme";
		if (this.equipedWeapon instanceof Weapon) {
			//On affiche le nom et la prtection de l'armure
			weapon = this.equipedWeapon.getName() + " (" +this.equipedWeapon.getDamages() + " dégats, " + this.equipedWeapon.getRange()+" de rayon)"; 
		}
		
		
		//L'armure équipée
		inv += weapon + "\nArmure :  ";
		String armor = "Aucune armure";
		if (this.equipedArmor instanceof Armor) {
			//On affiche le nom et la prtection de l'armure
			armor = this.equipedArmor.getName() + " (" + this.equipedArmor.getProtection() + " protection)"; 
		}
		
		
		//Le reste du sac
		inv += armor + "\n" + "Sac :\n";
		if (this.items.size() == 0) {
			//si la taill = 0 : pas d'objet
			inv += "Aucun objet";
		} else {
			//Sinon : on parcours la liste des objets pour les afficher 1 par 1
			for (int i = 0; i < this.items.size(); i++) {
				//On récèpère le nom grace a la methode getName de la classe item
				inv += " - " + this.items.get(i).getName() ;
				
				//Si l'ITEM est une instance de Weapon
				if (this.items.get(i) instanceof Weapon) {
					//On cast l'ITEM en WEAPON pour pouvoir utiliser la methode getDamages propre à la classe Weapon (fille de item)
					Weapon weaponToShow = (Weapon) this.items.get(i);
					inv += " (" + weaponToShow.getDamages() + "D)";
					
				//Si l'ITEM est une instance de ARMOR	
				} else if (this.items.get(i) instanceof Armor) {
					//On cast l'ITEM en ARMOR pour pouvoir utiliser la methode getProtection propre à la classe Armor (fille de item)
					Armor weaponToShow = (Armor) this.items.get(i);
					inv += " (" + weaponToShow.getProtection() + "P)";
				}
				inv += "\n";
			}
		}
		
		return inv;
	}
	
}









