/**
 * @(#)KeyboardInput.java
 *
 *
 * @author 	Ryan Clemens
 * @version 1.00 
 */
package game2D.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;


/**
 * 	Thread safe keyboard class
 */
public class KeyboardInput implements KeyListener {

	
	//Events waiting to be processed for next frame
	private LinkedList<Integer> eventThread;
	//Events the game thread is currently working on
	private LinkedList<Integer> gameThread;
	
	//Keys typed that are waiting to be processed
	private LinkedList<Character> eventThreadTyped;
	//Keys typed game is currently processing
	private LinkedList<Character> gameThreadTyped;
	
	//True if a key is currently pressed
	private boolean[] keys;
	//Counts number of consecutive frames a key is pressed
	private int[] polled;
	
	
	
	public KeyboardInput() 
	{
		this.polled = new int[256];
		this.keys = new boolean[256];
		this.eventThread = new LinkedList<Integer>();
		this.gameThread = new LinkedList<Integer>();
		this.eventThreadTyped = new LinkedList<Character>();
		this.gameThreadTyped = new LinkedList<Character>();
	}
	
	/**
	 *	Returns true if this key has been
	 *	polled at least once.
	 */
	public boolean keyDown( int keyCode ) 
	{
		return this.polled[ keyCode ] > 0;
	}
	
	/**
	 *	Returns true if this key has been
	 *	polled exactly once.
	 */
	public boolean keyDownOnce( int keyCode ) 
	{
		return this.polled[ keyCode ] == 1;
	}
	
	/**
	 *	Swaps the eventThread (listening for events) to the
	 *	gameThread (accessible) so that they can be 
	 *	processed by the game thread. Then polls all keys
	 *	that have been pressed and updates their counters.
	 */
	public synchronized void poll() 
	{
		this.swapQueues();
		this.pollKeys();
	}
	
	/**
	 *	Swaps the eventThread with the gameThread, so the 
	 *	game can process all key events in order of
	 *	occurence
	 */
	private void swapQueues()
	{
		//Key events
		LinkedList<Integer> swap = this.eventThread;
		(this.eventThread = this.gameThread).clear();
		this.gameThread = swap;
		//Typed keys
		LinkedList<Character> swapTyped = this.eventThreadTyped;
		(this.eventThreadTyped = this.gameThreadTyped).clear();
		this.gameThreadTyped = swapTyped;
	}
	
	/**
	 *	Polls and updates counters for each key 
	 *	pressed or release last frame.
	 */
	private void pollKeys()
	{
		for( int i = 0; i < this.keys.length; i++ )
		{
			if( this.keys[i] )
				this.polled[i] ++;
			else
				this.polled[i] = 0;
		}
	}
	
	/**
	 *  Returns the list of all keys pressed
	 *	the previous frame.
	 */
	public List<Integer> getKeysPressed()
	{
		return this.gameThread;
	}
	
	/**
	 * 	Returns the list of all keys typed
	 * 	the previous frame
	 */
	public List<Character> getKeysTyped()
	{
		return this.gameThreadTyped;
	}
	
	/**
	 * 	Activates the flag for the key press
	 *	and adds the key code to the list for
	 *	processing
	 */
	@Override public synchronized void keyPressed( KeyEvent e ) 
	{
		//Key code for press
		int keyCode = e.getKeyCode();
		if( keyCode >= 0 && keyCode < this.keys.length )
		{
			//Activate key for polling
			this.keys[ keyCode ] = true;
			//Add key to list for processing
			this.eventThread.add( keyCode );
		}
	}
	
	/**
	 *	Deactivates the key press flag
	 */
	@Override public synchronized void keyReleased( KeyEvent e ) 
	{
		int keyCode = e.getKeyCode();
		if( keyCode >= 0 && keyCode < this.keys.length ) 
			this.keys[ keyCode ] = false;
		
	}
	
	@Override public synchronized void keyTyped( KeyEvent e ) 
	{
		this.eventThreadTyped.add( e.getKeyChar() );
	}
}
