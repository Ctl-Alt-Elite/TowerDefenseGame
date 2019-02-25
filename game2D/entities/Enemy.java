package game2D.entities;

/**
 * @author Jodi Lin
 *
 */
public abstract class Enemy {

	protected int hp;
	protected int damage;
	protected int attackSpeed;
	protected int rangeX; 
	protected int rangeY;
	protected Tower targetTower;
	protected int enemyPositionR;
	protected int enemyPositionC;
	protected boolean isDead;
	
	////Enemy Constructor////
	/** 
	 * @param hp
	 * @param damage
	 * @param speed
	 * @param rangeX
	 * @param rangeY
	 * @param row
	 * @param column
	 */
	public Enemy(int hp, int damage, int speed, int rangeX, int rangeY, int row, int column) {
		this.hp = hp;
		this.damage = damage;
		this.attackSpeed = speed;
		this.rangeX = rangeX;
		this.rangeY = rangeY;
		this.enemyPositionR = row;
		this.enemyPositionC = column;
		this.isDead = false;
	}

	/** @return HP */
	public int getHp() {
		return hp;
	}

	/** @return damage */
	public int getDamage() {
		return damage;
	}
 
	/** @return Speed */
	public int getAttackSpeed() {
		return attackSpeed;
	}
     
	/** @return rangeX*/
	public int getRangeX() {
		return rangeX;
	}

	/** @return rangeY */
	public int getRangeY() {
		return rangeY;
	}

	/** @return Tower TargetTower */
	public Tower getTargetTower() {
		return targetTower;
	}

	/** @return enemyPositionR*/
	public int getEnemyPositionR() {
		return enemyPositionR;
	}

	/** @return enemyPositionC */
	public int getEnemyPositionC() {
		return enemyPositionC;
	}
	
	/** @return boolean isDead()*/
	public boolean getIsDead() {
		return this.isDead;
	}
	 
	/** @param targetTower
	 */
	///set TargetTower ////
	public void setTargetTower(Tower targetTower) {
		this.targetTower = targetTower;
	}

	/// set Enemy Position Row
	public void setEnemyPositionR(int row) {
		this.enemyPositionR = row;
	}

	//// Set Enemy Position Column
	public void setEnemyPositionC(int column) {
		this.enemyPositionC = column;
	}
	
	/// Receive damage from opponent///
	public void receiveDamage(int amount) {
		this.hp -= amount; 
		if (this.hp <= 0) {
			this.isDead = true;
		}
	}
	
	/** How the enemy attacks*/
	////Override this method 
	abstract public void Attack();
	
	/** How the enemy moves according to position and speed **/
	////Override this method
	abstract public void Travel();
	
	
	


}
