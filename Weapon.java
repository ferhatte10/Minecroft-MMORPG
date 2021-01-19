public class Weapon extends Item {
	private int damages;
	private int range;
	private int overcrowded;
	
	public Weapon(String name, String image, boolean takeable, boolean walkable, int xPos, int yPos, int damages, int range, int overcrowded) {
		super(name, image, takeable, walkable, xPos, yPos);
		this.damages = damages;
		this.range = range;
		this.overcrowded = overcrowded;
	}
	
	public int getDamages() {
		return this.damages;
	}
	public int getRange() {
		return this.range;
	}
	public int getOvercrowded() {
		return this.overcrowded;
		
	}

}
