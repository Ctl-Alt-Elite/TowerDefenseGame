package Entities;

public class BasicEnemy extends Enemy {
	
	private final static int HP = 100;
	private final static int DAMAGEPERHIT = 10;
	private final static int ATTACKSPEED = 1;
	private final static int RANGEX = 3;
	private final static int RANGEY = 3;
	
	
	public BasicEnemy(int positionX, int positionY) {
		super(HP,DAMAGEPERHIT,ATTACKSPEED,RANGEX,RANGEY,positionX,positionY);
	}


	@Override
	public void Travel() {
		//TODO will know more when map is more definite. 
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
}
