package projet_azzouz_saidoun;

public class Item extends Object{
	private int xPos;
	private int yPos;
	
	public Item(String name, String image, boolean takeable, boolean walkable, int xPos, int yPos) {
		super(name, image, true, true);
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getXPos() {
		return this.xPos;
	}
	public int getYPos() {
		return this.yPos;
	}

}
