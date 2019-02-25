package game2D.state;

import java.awt.Graphics2D;

import com.sun.glass.events.KeyEvent;

import game2D.util.Matrix3x3f;

public class MainMenuState extends State {
	
	private GamePlayingState mainGame;
	private LoadingScreenState loadingScreen;
	private boolean toMainGame;
	private boolean toLoadingScreen;
	
	
	public MainMenuState(){
		toMainGame = false;
		toLoadingScreen = false;
	}
	
	/**
	 * 	Handles setup when state is accessed
	 * 	at the beginning. 
	 */
	public void enter() 
	{
		System.out.println("Menu hello");
		
	}
	
	/**
	 * 	Processes input for this state
	 */
	public void processInput( float delta ) 
	{
		//Nothing to do yet 
		if (keys.keyDownOnce(KeyEvent.VK_L)){
			toLoadingScreen=true;
		}else if (keys.keyDownOnce(KeyEvent.VK_G)){
			toMainGame=true;
		}
	}

	/**
	 * 	Updates all objects each iteration
	 * 	for this state.
	 */
	public void updateObjects( float delta ) 
	{
		//Nothing to do yet
		if (toLoadingScreen){
			loadingScreen = new LoadingScreenState();
			this.getController().setState(loadingScreen);
		}else if (toMainGame){
			mainGame = new GamePlayingState();
			this.getController().setState(mainGame);
		}
	}

	/**s
	 * 	Renders this state to the screen
	 */
	public void render( Graphics2D g, Matrix3x3f view ) 
	{
		g.drawString("Main Menu", 400, 300);
		g.drawString("Press L to go to Loading Screen", 370, 310);
		g.drawString("Press G to go to Main Game", 370, 320);
	}

	/**
	 * 	Handles when this state is exited
	 */
	public void exit() 
	{
		System.out.println("Menu goodbye");
	}
}
