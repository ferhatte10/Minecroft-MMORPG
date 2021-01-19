public class Object {
	protected String name;
	protected String image;
	protected boolean takeable;
	protected boolean walkable;
	protected String caption;
	
	public Object(String name, String image, boolean takeable, boolean walkable) {
		this.name = name;
		this.takeable = takeable;
		this.walkable = walkable;
		this.image = image;
		this.caption = name;
		
	}
	public String getCaption() {
		return this.caption;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isTakeable() {
		return this.takeable;
	}
	public boolean isWalkable() {
		return this.walkable;
	}
	
	public String getImage() {
		return this.image + ".png";
	}
	
	public String toString() {
		
	    return this.name;
	}

	
}
