/**
 * @(#)CompleteGame.java
 *
 *
 * @author 	Ryan Clemens
 * @version 1.00
 */
package game2D;

import java.awt.Graphics;

import game2D.framework.WindowFramework;


/**
 *	Extension of the GameFramework. This is the class that is
 *	launched when the game is first started. However, there isn't
 *	much for this class to do, because all of the game items are
 *	loaded during the loading screen. This class's main job is to
 *	be the bridge between the setup of the window and controlling
 *	all of the game specific objects. The processInput(), 
 *	updateObjects(), and render() methods pass control to the 
 *	controller, and other than that this class does little else.
 *
 */
public final class CompleteGame extends WindowFramework {

	
	
	public CompleteGame() 
	{
		// (1) Set up starting app values from GameFramework class
	}

	@Override protected void initialize() 
	{
		// (1) Initializes parent objects
		super.initialize();
		
		// (2) Initialize all controlling objects for the game....
		//		.....
	}  

	public void shutDownGame() 
	{
		// (1) Handles what to do on shutdonw()
		this.shutDown();
	}

	@Override protected void processInput( float delta ) 
	{
		// (1) Call super class, poll keyboard & mouse
		super.processInput( delta );
		
		// (2) Handle processing game inputs....
	}

	@Override protected void updateObjects( float delta ) 
	{
		// (1) Update all game objects
	}

	@Override protected void render(Graphics g) 
	{
		// (1) Render all objects for this frame...
		
		
		// (2) Render the frame rate counter from parent
		//		over the top of the game...
		super.render(g);
	}

	@Override protected void terminate() 
	{
		System.out.println("\n\t**** Thread Pools Still Need To Be TERMINATED! ****\n");
		super.terminate();
	}


//=================================================================================================

	public static void main(String[] args) 
	{
		launchApp(new CompleteGame());
	}
	
}
