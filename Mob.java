package projet_azzouz_saidoun;

import java.util.ArrayList;


public class Mob extends Entity  {
	
	public Mob(String name, String image, int xPos, int yPos, int attack, int defense, int dodge, int damage, int protection, int life) {
		super(name, image, xPos, yPos, attack, defense, dodge, damage, protection, life);
	}
	
	
	
	public String getWayTo(int xTarget, int yTarget, ArrayList<String> blockedWays) {
		return getWayTo(xTarget, yTarget);
	}
	
	
	public String getWayTo(int xTarget, int yTarget) {
		//j'ai (ilian) trouvé le moyen rapide mais peu efficace pour calculer le chemin
		//Ils se rendent bien vers un joueur MAIS
		//Ils restent bloqués derriere les murs mdr
		//pas très malin ^^ 
		//Les plus grand jeux ont commencés comme ça ? N'est-ce pas ?
		int xToGo = this.getXPos() - xTarget;
		int yToGo = this.getYPos() - yTarget;
		

		if (Math.abs(xToGo) >= Math.abs(yToGo)) {
			if (xToGo > 0) return "UP";
			else return "DOWN";
		} else {
			if (yToGo > 0) return "LEFT";
			else return "RIGHT";
		}	
		
	}
	
}
