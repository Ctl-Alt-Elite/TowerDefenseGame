/**
 * @(#)State.java
 *
 *
 * @author 	Ryan Clemens
 * @version 1.00
 */
package game2D.state;

import java.awt.Graphics2D;

import game2D.CompleteGame;
import game2D.io.KeyboardInput;
import game2D.util.Matrix3x3f;


/**
 *	The complete game is a little over-engineered for what it actually
 *	does, but it is a good demonstration of state management in a very
 *	large game. This class is the basis for each game state. There are
 *	methods that correspond to each method in the game loop, as well as
 *	an enter() method, and an exit() method.
 *
 *	A State object in the game (GameLoading, GamePlaying, GameOver, 
 *	etc. ...) also has reference to the StateController controlling
 *	when the game moves from one state to another. This way, once the
 *	given state is done doing it's job it can tell the controller what
 *	state to move to next.
 *
 */
public abstract class State {

	private StateController controller;
	protected CompleteGame app;
	protected KeyboardInput keys;

	
	/**
	 * 	Default constructor invokes onCreate()
	 * 	when instantiated.
	 */
	public State()
	{
		//Nothing to do yet
	}

	public void setController( StateController controller ) 
	{
		this.controller = controller;
		this.app = (CompleteGame) controller.getAttribute( "app" );
		this.keys = (KeyboardInput) controller.getAttribute( "keys" );
	}

	protected StateController getController() 
	{
		return this.controller;
	}
	
	/**
	 * 	Handles setup when state is accessed
	 * 	at the beginning. 
	 */
	public void enter() 
	{
		//Nothing to do yet
		
	}
	
	/**
	 * 	Processes input for this state
	 */
	public void processInput( float delta ) 
	{
		//Nothing to do yet
	}

	/**
	 * 	Updates all objects each iteration
	 * 	for this state.
	 */
	public void updateObjects( float delta ) 
	{
		//Nothing to do yet
	}

	/**s
	 * 	Renders this state to the screen
	 */
	public void render( Graphics2D g, Matrix3x3f view ) 
	{
		//Nothing to do yet
	}

	/**
	 * 	Handles when this state is exited
	 */
	public void exit() 
	{
		//Nothing to do yet
	}
	
}
