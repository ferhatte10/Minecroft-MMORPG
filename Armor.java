

public class Armor extends Item {
	private int protection;
	private int overcrowded;
	
	public Armor(String name, String image, boolean takeable, boolean walkable, int xPos, int yPos, int protection, int overcrowded) {
		super(name, image, takeable, walkable, xPos, yPos);
		this.protection = protection;
		this.overcrowded = overcrowded;
		
	}
 
	public int getProtection() {
		return this.protection;
	}
	
	public int getOvercrowded() {
		return this.overcrowded;
	}
}
