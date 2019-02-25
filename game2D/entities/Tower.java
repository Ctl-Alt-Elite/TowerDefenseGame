package game2D.entities;

public abstract class Tower {

	private int hp;
	private int damage;
	private int attackSpeed;
	private int rangeX; 
	private int rangeY;
	private Enemy targetEnemy;
	private int towerPositionR;
	private int towerPositionC;
	private boolean isDown;
	
	/**
	 * @param hp
	 * @param damage
	 * @param attackSpeed
	 * @param rangeX
	 * @param rangeY
	 * @param enemyPositionR
	 * @param enemyPositionC
	 * @param isDead
	 */
	public Tower(int hp, int damage, int attackSpeed, int rangeX, int rangeY, int towerPositionR,int towerPositionC) {
		this.hp = hp;
		this.damage = damage;
		this.attackSpeed = attackSpeed;
		this.rangeX = rangeX;
		this.rangeY = rangeY;
		this.towerPositionC = towerPositionC;
		this.isDown = false; 
	}

	///Get tower HP///
	public int getHp() {
		return hp;
	}

	////Get tower damage per hit////
	public int getDamage() {
		return damage;
	}

	/////Get attack speed/////
	public int getAttackSpeed() {
		return attackSpeed;
	}

	////Get the tower's Range x////
	public int getRangeX() {
		return rangeX;
	}

	///Get the tower's Range Y////
	public int getRangeY() {
		return rangeY;
	}

	////get the targeting enemy////
	public Enemy getTargetEnemy() {
		return targetEnemy;
	}

	////Get the tower's Row position////
	public int getTowerPositionR() {
		return towerPositionR;
	}

	////Get the tower's Column position/
	public int getTowerPositionC() {
		return towerPositionC;
	}

	/////Get if the tower is down/////
	public boolean isDown() {
		return isDown;
	}

	/**
	 * @param targetEnemy
	 */
	///SET the target enemy
	public void setTargetEnemy(Enemy targetEnemy) {
		this.targetEnemy = targetEnemy;
	}
	
	////let this tower receive damage////
	public void receiveDamage(int amount) {
		this.hp -= amount; 
		if (this.hp <= 0) {
			this.isDown = true;
		}
	}
	
	/** How the tower attacks*/
	////Override this method 
	abstract public void Attack();
	
	/** How the enemy moves according to position and speed **/
	////Override this method
	abstract public void Travel();
	
	
}
