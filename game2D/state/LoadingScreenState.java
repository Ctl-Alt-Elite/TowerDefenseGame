package game2D.state;

import java.awt.Graphics2D;

import com.sun.glass.events.KeyEvent;

import game2D.util.Matrix3x3f;

public class LoadingScreenState extends State {
	
	private MainMenuState menu;
	private boolean toMenu;
	
	
	public LoadingScreenState(){
		
		toMenu = false;
	}
	
	/**
	 * 	Handles setup when state is accessed
	 * 	at the beginning. 
	 */
	public void enter() 
	{
		System.out.println("Loading screen hello");
		
	}
	
	/**
	 * 	Processes input for this state
	 */
	public void processInput( float delta ) 
	{
		//Nothing to do yet
		if (keys.keyDownOnce(KeyEvent.VK_M)){
			toMenu=true;
		}
			
	}

	/**
	 * 	Updates all objects each iteration
	 * 	for this state.
	 */
	public void updateObjects( float delta ) 
	{
		//Nothing to do yet
		if (toMenu){
			menu = new MainMenuState();
			this.getController().setState(menu);
		}
	}

	/**s
	 * 	Renders this state to the screen
	 */
	public void render( Graphics2D g, Matrix3x3f view ) 
	{
		g.drawString("Loading Screen", 400, 300);
		g.drawString("Press M to go to Main Menu", 370, 310);
	}

	/**
	 * 	Handles when this state is exited
	 */
	public void exit() 
	{
		System.out.println("Loading screen goodbye");
	}
}
