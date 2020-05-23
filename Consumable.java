package projet_azzouz_saidoun;

public class Consumable extends Item {
	private int lifeChange;
	
	public Consumable(String name, String image, int xPos, int yPos, int lifeChange) {
		super(name, image ,true, true, xPos, yPos);
		this.lifeChange = lifeChange;
	}

	public int getLifeChange() {
		return lifeChange;
	}
	
	
}
