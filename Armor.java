package projet_azzouz_saidoun;

public class Armor extends Item {
	private int protection;
	public Armor(String name, String image, boolean takeable, boolean walkable, int xPos, int yPos, int protection) {
		super(name, image, takeable, walkable, xPos, yPos);
		this.protection = protection;
	}
 
	public int getProtection() {
		return this.protection;
	}
}
