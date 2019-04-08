package Entities;

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
	
	/** Calculate the distance between two Entities **/
	public double calculateDistance(Entity e) {
		int x = this.positionC - e.getPositionC();
		int y = this.positionR - e.getPositionR();
		double result = Math.sqrt((x*x) + (y*y));
		return result;
	}
	
	/** Adds to the to the possibleTargets list **/
	public void addPossibleTarget(Entity e) {
		if (this.possibleTargets == null) {
			this.possibleTargets.add(e);
		}
		else{
			for (int i = 0; i< this.possibleTargets.size(); i++) {
				if (this.calculateDistance(this.possibleTargets.get(i))  > this.calculateDistance(e)){
					this.possibleTargets.add(i, e);
					break;
				}
			}
		}
		this.possibleTargets.add(e);
	}
	
	/** Allows the entity to receive damage from the attack @see Attack */
	public void receiveDamage(Attack attack) {
		this.hp -= attack.getDamage();
	}
	
	/** allows entity to attack others **/
	abstract public void attack();
	
	/** update the possibleTargets list by distance to this entity - insertion sort - **/
	public void updatePossibleTargets(float f) {
		for (int i = 1; i < this.possibleTargets.size(); i++) {
			Entity first = this.possibleTargets.get(i);
			int j = i-1;
			
			while(j>=0 && this.calculateDistance(first) < this.calculateDistance(this.possibleTargets.get(j))) {
				j--; 
			}
			this.possibleTargets.add(j,first);
		}
	}
	
	/** updates the target to the next target after current target dies **/
	public void updateTarget(float f) {
		if(this.target.isDead == true) {
			this.target = possibleTargets.get(0);
		}
		
	}
	
	/** calls for updates of targets list and target **/
	public void update(float f) {
		this.updatePossibleTargets(f);
		this.updateTarget(f);
	}
	
	///testing again 3///
}
