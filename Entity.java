package projet_azzouz_saidoun;

public class Entity extends Object {
	private boolean isDead;
	private int life;
	
	//degres
	private int initiative;
	private int attack;
	private int dodge;
	private int defense;
	private int damage;
	
	private int xPos;
	private int yPos;
	
	
	
	public Entity(String name, String image, int xPos, int yPos, int initiative, int attack, int dodge, int defense, int damage, int life) {
		super(name, image, false, false);
		this.isDead = false;
		this.xPos = xPos;
		this.yPos = yPos;
		
		this.initiative = initiative;
		this.attack = attack;
		this.dodge = dodge;
		this.defense = defense;
		this.damage = damage;
		this.life = life;
		
		
	}	
	
	//GETTERS SETTERS
	public boolean isDead() {
		return this.isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	public int getXPos() {
		return this.xPos;
	}
	public int getYPos() {
		return this.yPos;
	}
	
	public int getInitiative() {
		return this.initiative;
	}
	public int getAttack() {
		return this.attack;
	}
	public int getDodge() {
		return this.dodge;
	}
	public int getDefense() {
		return this.defense;
	}
	public int getDamage() {
		return this.damage;
	}
	public int getLife() {
		return this.life;
	}
	public void setLife(int newLife) {
		this.life = newLife;
	}
	
	//AUTRES METHODES
	//renvoie vrai si le personnage est mort et défini sa vie à 0 (pour les val negatives) et son etat à mort
	public boolean checkIfDead(){
		if (this.life <= 0) {
			this.life = 0;
			this.isDead = true;
			return true;
		} else {
			return false; 
		}
	}
	//avacner
	public void move(String way, int distance) {		
		switch (way) {
		case "LEFT":
			this.yPos -= distance; 
		break;
		case "UP":
			this.xPos -= distance;
		break;
		case "DOWN":
			this.xPos += distance;
		break;
		case "RIGHT":
			this.yPos += distance;
		break;
		}
	}
	
	//Attaque un personnage
	
	public void attack(double damages) {
		this.life -= damages;
		this.isDead = this.checkIfDead();
	}
	
	

	public int distanceBetween(Entity entity) {
		int dist = 0;
		if (entity.xPos > this.xPos) {
			dist = entity.xPos - this.xPos;
		} else {
			dist = this.xPos - entity.xPos;
		}
		
		if (entity.yPos > this.yPos) {
			dist += entity.yPos - this.yPos;
		} else {
			dist += this.yPos - entity.yPos;
		} 
		return dist;
	}
	
}















