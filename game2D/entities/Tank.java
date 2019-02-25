package game2D.entities;

public class Tank extends Enemy{
	
	public static final int MAXHP = 300;
	public static final int DAMAGEPERHIT = 30;
	public static final int ATTACKSPEED = 1;
	public static final int RANGEX = 5;
	public static final int RANGEY = 5;

	public Tank(int row, int column) {
		super(MAXHP, DAMAGEPERHIT, ATTACKSPEED, RANGEX, RANGEY, row, column);
		
	}

	@Override
	public void Attack() {
		super.getTargetTower().receiveDamage(this.getDamage());
		
	}

	@Override
	public void Travel() {
		// TODO 
		
	}

}
