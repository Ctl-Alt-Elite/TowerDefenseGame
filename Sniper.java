package towerDefense.entities;

public class Sniper extends Enemy {
	public static final int HP = 75;
	public static final int DAMAGEPERHIT = 40;
	public static final int ATTACKSPEED = 1;
	public static final int RANGEX = 25;
	public static final int RANGEY = 25;
	
	public Sniper(int positionX, int positionY) {
		super(HP,DAMAGEPERHIT,ATTACKSPEED,RANGEX,RANGEY,positionX,positionY);
	}
	
	@Override
	public void Attack() {
		super.getTargetTower().receiveDamage(Sniper.DAMAGEPERHIT);
		
	}

	@Override
	public void Travel() {
		//TODO will know more when map is more definite. 
		
	}
	
}
