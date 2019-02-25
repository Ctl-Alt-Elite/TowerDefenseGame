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
	
	public List getPossibleTargets() {
		return possibleTargets;
	}
	
	public boolean getIsDead() {
		return isDead;
	}
	
	public void toggleIsDead() {
		if(isDead == true) {
			isDead = false;
		}
		if(isDead == false) {
			isDead = true;
		}
	}
	
	public void receiveDamage(Attack attack) {
		this.damage -= attack.getDamage();
	}
	
	abstract public void attack();
	
	abstract public void updatePossibleTargets(float f);
	
	abstract public void updateTarget(float f);
	
	public void update(float f) {
		this.updatePossibleTargets(f);
		this.updateTarget(f);
	}
}
