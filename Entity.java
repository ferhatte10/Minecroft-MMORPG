package projet_azzouz_saidoun;

public class Entity extends Object {
	private boolean isDead;
	private int life;
	
	//degres
	
	private int attack;
	private int defense;
	private int dodge;
	private int damage;
	private int protection;
	
	private int xPos;
	private int yPos;
	
	
	
	public Entity(String name, String image, int xPos, int yPos,int attack,  int defense, int dodge,  int damage, int protection, int life) {
		super(name, image, false, false);
		this.isDead = false;
		this.xPos = xPos;
		this.yPos = yPos;
		
		this.life = life;
		
		this.attack = attack;
		this.defense = defense;
		this.dodge = dodge;
		this.damage = damage;
		this.protection = protection;
		
		
		
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
	
	
	public int getAttack() {
		return this.attack;
	}
	public void setAttaque(int attaque) {
		this.attack = attaque;
	}
	public int getDefense() {
		return this.defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getDodge() {
		return this.dodge;
	}
	public void setDodge(int dodge) {
		this.dodge = dodge;
	}
	public int getDamage() {
		return this.damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getProtection() {
		return this.protection;
	}
	public void setProtection(int protection) {
		this.protection = protection;
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
			
			this.attack = 0;
			this.defense = 0;
			this.dodge = 0;
			this.damage = 0;	
			this.protection= 0;
			
						
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
	
	public void attack(Entity entity) {
		this.life -= entity.getDamage();
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















