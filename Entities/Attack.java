package Entities;

public class Attack {
	public static final int MELEE = 0;
	public static final int SHOOT = 1;
	public static final int MAGIC = 2;
	private int damage;
	
	protected Attack(int attackType, int damage) {
		damage = this.damage;
	}
	
	public static Attack MELEE (int damageAmount) {
		return new Attack(0, damageAmount);
	}
	
	public static Attack SHOOT (int damageAmount) {
		return new Attack(1, damageAmount);
	}
	
	public static Attack MAGIC (int damageAmount) {
		return new Attack(2, damageAmount);
	}
	
	public int getDamage() {
		return this.damage;
	}
}
	
	
	
	
	
	
	
	
	
	

