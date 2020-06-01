package projet_azzouz_saidoun;

import java.util.ArrayList;

public class Map {
	private int width = 35;
	private int height = 20;
	
	private int nbObstacles = 7;
	private int maxWallLength = 10;
	
	public Object map[][] = new Object[height][width];
	
	private ArrayList<Player> players;
	private ArrayList<Mob> mobs;
	private ArrayList<Item> items;
	
	private int nbMobs = 4;
	//private int nbArmors = 4;
	//private int nbWeapons = 4;
	
	
	private Wall wall = new Wall();
	private Path path = new Path();
	
	
	private boolean isGameStarted;
	
	public Map() {
		this.initMap();
		this.initMobs();
		this.initPlayers();
		this.initItems();
		this.update();
		this.isGameStarted = true;
	}
	
	//getters
	public int getWidth() {
 		return this.width;
 	}
	public int getHeight() {
 		return this.height;
 	}
	public boolean isGameStarted() {
		return this.isGameStarted;
	}
	
	public Object[][] getMap() {
		return this.map;
	}
	//Objets de la map
	public Object getObject(int x, int y) {
		return this.map[x][y];
	}
	public void setObject(int x, int y, Object obj) {
		this.map[x][y] = obj;
	}
	
	//Joueurs
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	public Player getPlayer(int i) {
		if (this.players.size() >= i) {
			return this.players.get(i);
		}
		return null;
		
	}
	
	//Mobs
	public ArrayList<Mob> getMobs() {
		return this.mobs;
	}
	public Mob getMob(int i) {
		return this.mobs.get(i);
	}
	
	//Items
	public ArrayList<Item> getItems() {
		return this.items;
	}
	public Item getItem(int i) {
		return this.items.get(i);
	}
	
	public void removeItem(Item item) {
		this.items.remove(item);
	}
	
	//Get items around player 
	public ArrayList<Item> getItemsRange(Player player, int range){
		ArrayList<Item> itemsRange = new ArrayList<Item>();
		
		for (int i = 0; i < this.items.size(); i++) {
			Item currentItem = this.items.get(i);
			if (
					currentItem.getXPos() <= player.getXPos() + range &&
					currentItem.getXPos() >= player.getXPos() - range &&
					currentItem.getYPos() <= player.getYPos() + range &&
					currentItem.getYPos() >= player.getYPos() - range
				) {
				itemsRange.add(currentItem);
			}
		}
		return itemsRange;
	}

	
	
	
	
	
	public ArrayList<Mob> getMobsRange(Player player, int range){
		ArrayList<Mob> mobsRange = new ArrayList<Mob>();
		
		for (int i = 0; i < this.mobs.size(); i++) {
			Mob currentMob = this.mobs.get(i);
			if (
					currentMob.getXPos() <= player.getXPos() + range &&
					currentMob.getXPos() >= player.getXPos() - range &&
					currentMob.getYPos() <= player.getYPos() + range &&
					currentMob.getYPos() >= player.getYPos() - range
				) {
				mobsRange.add(currentMob);
			}
		}
		return mobsRange;
	}
	
	public ArrayList<Player> getPlayersRange(Player player, int range){
		ArrayList<Player> playersRange = new ArrayList<Player>();
		
		for (int i = 0; i < this.players.size(); i++) {
			Player currentPlayer = this.players.get(i);
			if (
					currentPlayer.getXPos() <= player.getXPos() + range &&
					currentPlayer.getXPos() >= player.getXPos() - range &&
					currentPlayer.getYPos() <= player.getYPos() + range &&
					currentPlayer.getYPos() >= player.getYPos() - range &&
					player != currentPlayer
					
				) {
				playersRange.add(currentPlayer);
			}
		}
		return playersRange;
	}	
	
	public void setPath(int x, int y) {
		this.map[x][y] = this.path;
	}
	
	
	public Player getNearestPlayerFrom(Entity entity) {
		int nearestPos = this.players.get(0).distanceBetween(entity);
		
		Player nearestPlayer = this.players.get(0);
		
		int dist = 0;
		for (int i = 1; i < this.players.size(); i++) {
			dist = this.players.get(i).distanceBetween(entity);
			if (dist < nearestPos) {
				nearestPos = dist;
				nearestPlayer = this.players.get(i);
			}
		}
		return nearestPlayer;
	}
	
	public int[] getRandomEmptySlot() {
		int wPos = 0;
		int hPos = 0;
		
		
		while (!(this.map[hPos][wPos] instanceof Path)) {
			wPos = (int)(Math.random() * (this.width-6))+3;
			hPos = (int)(Math.random() * (this.height-6))+3;
			
		}
		int pos[] = {hPos,wPos};
		
		return pos;
		
		
		
	}
	
	
	public void initMap() {
		System.out.println("[OUT] Initialising Map");
		//On genre la map et on met un ■ si on est sur les bords
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {			
				if (y == 0 || x == 0 || x == this.width-1 || y == this.height-1) {
					this.map[y][x] = wall;
				} else {
					this.map[y][x] = path;
				}
			}
		}
		System.out.println("[OUT] Map Initialized");
		System.out.println("[OUT] Initialising Obstacles");
		for (int i = 0; i < nbObstacles; i++) {
			//On genere la position aleatoire du mur dans la grille
			int wPosWall = (int)(Math.random() * (this.width-6))+3;
			int hPosWall = (int)(Math.random() * (this.height-6))+3;
			
			//On genere la taille aleatoire du mur
			int tailleMur = (int)(Math.random() * this.maxWallLength-1)+3;
			
			//Taille courrante du mur
			int wallLength = 0;
			
			//Si pair : le mur est vertical
			if (i%2 == 0) {			
				
				//On s'arrete quand on touche une bordure ou que qu'on a atteint la taille définie du mur
				while (wallLength < tailleMur && (wallLength + hPosWall) < this.height-2 ) {
					this.map[wallLength+hPosWall][wPosWall] = wall;
					wallLength++;
				}	
			//Si impair : horizontal
			} else {
				//On s'arrete quand on touche une bordure ou que qu'on a atteint la taille définie du mur
				while (wallLength < tailleMur && (wallLength + wPosWall) < this.width-2) {
					this.map[hPosWall][wallLength+wPosWall] = wall;
					wallLength++;
				}
			}
			System.out.println("[OUT] Creating new wall : (" +  wPosWall + "," + hPosWall+ ") length : " + wallLength);
		}
		
		System.out.println("[OUT] Obstacles Initialized");
	}
	public void initItems() {
		System.out.println("[OUT] Initialising Items");
		this.items = new ArrayList<Item>();
		
		int pos[] = this.getRandomEmptySlot();
		this.items.add(new WeaponScissors(pos[0],pos[1]));
		
		pos = this.getRandomEmptySlot();
		this.items.add(new WeaponSword(pos[0],pos[1]));
		
		pos = this.getRandomEmptySlot();
		this.items.add(new WeaponBow(pos[0],pos[1]));
		
		
		this.items.add(new PotionHeal(1,2));
		this.items.add(new PotionActionPoint(2,1));
		this.items.add(new PotionStrength(2,2));
		
		System.out.println("[OUT] Items Initialized");
	}
	
	public void initMobs() {
		System.out.println("[OUT] Initialising Mobs");
		
		this.mobs = new ArrayList<Mob>();
		int pos[];
		
		for (int i = 0; i < this.nbMobs; i++) {
			pos = this.getRandomEmptySlot();
			
			if (i%3 == 0) {
				this.mobs.add(new MobZombie(pos[0],pos[1]));
			} else if (i%3 == 1) {
				this.mobs.add(new MobWitch(pos[0],pos[1]));
			} else {
				this.mobs.add(new MobSkeleton(pos[0],pos[1]));
			}
			
			
			
		
		
		
		}

			
		
		System.out.println("[OUT] Mobs Initialized");
		
	}
	public void initPlayers() {
		System.out.println("[OUT] Initialising Players");
		
		players = new ArrayList<Player>();
		
		players.add(new Player("Joueur 1 ", "steve", 1, 1));
		players.add(new Player("Joueur 2 ", "alex", this.height/2, this.width/2)); 
		
		
		System.out.println("[OUT] Players Initialized");
	}
	
	
	
	
	public void update() {
		for (int i = 0; i < this.items.size(); i++) {
			int x = this.getItem(i).getXPos();
			int y = this.getItem(i).getYPos();
			this.map[x][y] = this.getItem(i);
		}
		for (int i = 0; i < this.mobs.size(); i++) {
			int x = this.getMob(i).getXPos();
			int y = this.getMob(i).getYPos();
			this.map[x][y] = this.getMob(i);
		}
		for (int i = 0; i < this.players.size(); i++) {
			int x = this.getPlayer(i).getXPos();
			int y = this.getPlayer(i).getYPos();
			this.map[x][y] = this.getPlayer(i);
		}
	}
	
	public boolean canMove(Entity entity, String way) {
		int xMove = 0;
		int yMove = 0;
		switch (way) {
		case "UP":
			xMove = -1;
		break;
		case "DOWN":
			xMove = 1;
		break;
		case "LEFT":
			yMove = -1;
		break;
		case "RIGHT":
			yMove = 1;
		break;
		
		}
		return this.getObject(entity.getXPos()+xMove, entity.getYPos()+yMove).isWalkable();
	}
	
	public boolean isGameFinished() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isDead()) {
				this.isGameStarted = false;
				return true;
			}
		}
		
		return false;
	}
	
	public void nextRound(Map map) {
		
		 if (this.players.size() != 0) {
			 
			//On recupere tous les mob de la map
			for (int i = 0; i < map.getMobs().size(); i++) {
				
				//On recupère le joueur le plus proche du mob i
				
				Player near = map.getNearestPlayerFrom(map.getMob(i));
				
				if (near.distanceBetween(map.getMob(i)) == 1) {
					near.attack(map.getMob(i));
					if (near.isDead()) {
						this.setPath(near.getXPos(), near.getYPos());
					}
					
				} else if (near.distanceBetween(map.getMob(i)) <= 5) {
					
				
					/* Ne marche pas encore
					ArrayList<String> blockedWays = new ArrayList<String>();
					String[] ways = {"UP","DOWN","LEFt","RIGHT"};
					for (int j = 0; j < 4; j++) {
						if (!( map.canMove(map.getMob(i), ways[i]))){
							blockedWays.add(ways[i]);
						}
					}*/
					
					//On regarede le chemin pour aller aux coordonnées (haut, bas, gauche ou droite)
					String way = map.getMob(i).getWayTo(near.getXPos(), near.getYPos());//,blockedWays); //3 ieme parametre optionel
					
					//Si le mob peut faire se mouvement
					if (map.canMove(map.getMob(i), way)) {		
						
						//On met un chemiun aux corrdonnes du mob
						map.setPath(map.getMob(i).getXPos(), map.getMob(i).getYPos());
						//On déplace le mob
						map.getMob(i).move(way,1);
					}
					
					//On MAJ la map pour que les mobs ne se monte pas les un sur les autres
					
				} else {
					
					String[] ways = {"UP","DOWN","LEFT","RIGHT"};
					String way = ways[(int) (Math.random()*4)];
					int j = 0;
					
					while (!(map.canMove(map.getMob(i), way)) && j < 10) {
						way = ways[(int) (Math.random()*4)];
						j++;
					}
					
					if (j != 10) {
						//On met un chemiun aux corrdonnes du mob
						map.setPath(map.getMob(i).getXPos(), map.getMob(i).getYPos());
						//On déplace le mob
						map.getMob(i).move(way, 1);
						
					}				
					
				}
				map.update();
			}
			//return map; 
		 }
	}
	
	
	public String toString() {
		Object[][] mapMatrice = this.getMap();
		String mapTxt = "";
		
		for (int i = 0; i < this.height; i++) {
			String line = "";
			for (int j = 0; j < this.width; j++) {
				line += mapMatrice[i][j];
			}
			mapTxt += line + "\n";
		}
		return mapTxt;
	}

	
	
	
	
}
