package projet_azzouz_saidoun;

public class Weapon extends Item {
	private int damages;
	private int range;
	public Weapon(String name, String image, boolean takeable, boolean walkable, int xPos, int yPos, int damages, int range) {
		super(name, image, takeable, walkable, xPos, yPos);
		this.damages = damages;
		this.range = range;
	}
	
	public int getDamages() {
		return this.damages;
	}
	public int getRange() {
		return this.range;
	}

}
