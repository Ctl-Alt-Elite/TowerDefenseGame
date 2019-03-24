package Entities;
import java.lang.*;

/**
 * @author Jodi
 *
 */

public abstract class Enemy extends Entity {
	
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
		super(hp, damage, speed, rangeX, rangeY, row, column);
	}

	@Override public void updatePossibleTargets(float f) {
		for(int i = 1; i < possibleTargets.size(); i++) {
			int x = this.positionC - possibleTargets.get(i).getPositionC();
			int y = this.positionR - possibleTargets.get(i).getPositionR();
			double a = Math.sqrt((x*x) + (y*y));
		}
	}
	
	@Override public void updateTarget(float f) {
		
	}
	/** How the enemy moves according to position and speed **/
	////Override this method
	abstract public void Travel();
	
	
	


}