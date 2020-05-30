package projet_azzouz_saidoun;

public class Consumable extends Item {
	private int lifeChange;
	private int paChange;
	private int strengthChange;
	
	public Consumable(String name, String image, int xPos, int yPos, int lifeChange, int paChange, int strengthChange ) {
		super(name, image ,true, true, xPos, yPos);
		this.lifeChange = lifeChange;
		this.paChange = paChange;
		this.strengthChange = strengthChange;
		
	}
	public int getLifeChange() {
		return lifeChange;
	}
	public int getPaChange() {
		return paChange;
	}
	
	public int getStrenghChange() {
		return strengthChange;
	}
	
}
