package towerDefense.entities;

import java.util.List;

public abstract class Entity {
	protected int hp;
	protected int damage;
	protected int attackSpeed;
	protected int rangeX;
	protected int rangeY;
	protected int positionR;
	protected int positionC;
	protected Entity target;
	protected List <Entity> possibleTargets;
	protected boolean isDead;

	
	public Entity( int hp, int damage, int attackSpeed, int rangeX, int rangeY, int positionR, int positionC) {
		this.hp = hp;
		this.damage = damage;
		this.attackSpeed = attackSpeed;
		this.rangeX = rangeX;
		this.rangeY = rangeY;
		this.positionR = positionR;
		this.positionC = positionC;
				
	}
    //////Getters and setters/////
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getRangeX() {
		return rangeX;
	}

	public void setRangeX(int rangeX) {
		this.rangeX = rangeX;
	}

	public int getRangeY() {
		return rangeY;
	}

	public void setRangeY(int rangeY) {
		this.rangeY = rangeY;
	}

	public int getPositionR() {
		return positionR;
	}

	public void setPositionR(int positionR) {
		this.positionR = positionR;
	}

	public int getPositionC() {
		return positionC;
	}

	public void setPositionC(int positionC) {
		this.positionC = positionC;
	}
	
	public Entity getTarget() {
		return target;
	}
	
	public List<Entity> getPossibleTargets() {
		return possibleTargets;
	}
	
	public boolean getIsDead() {
		return isDead;
	}
	
	/** checks if the entity is dead and set isdead to true (allows entity to die) **/
	public void die() {
		if (this.hp > 1) {
			this.isDead = true;
		}
	}
	
	/** Allows the enemy to receive damage from the attack @see Attack */
	public void receiveDamage(Attack attack) {
		this.hp -= attack.getDamage();
	}
	
	/** allows entity to attack others **/
	abstract public void attack();
	
	/** update the possibleTargets list by distance to this entity - insertion sort - **/
	abstract public void updatePossibleTargets(float f);
	
	/** updates the target to the next target after current target dies **/
	abstract public void updateTarget(float f);
	
	/** calls for updates of targets list and target **/
	public void update(float f) {
		this.updatePossibleTargets(f);
		this.updateTarget(f);
	}
}
