/**
 * @(#)StateController.java
 *
 *
 * @author 	Ryan Clemens
 * @version 1.00
 */
package game2D.state;

import java.awt.*;
import java.util.*;

import game2D.util.Matrix3x3f;


/**
 *	Key class in the entire game. This class handles the transition
 *	from one state to another, passes all the game loop calls to the
 *	current state, and contains a map of game objects referenced by
 *	name. This is a very simple way to pass resources around, and
 *	new objects can be added and retrieved without ading any extra
 *	methods to the controller. As long as a class knows the name of
 *	the resource and the type of the object, that object can be
 *	accessed.
 *
 *
 */
public class StateController {

	//List of game objects referenced by name
	private Map<String, Object> attributes;
	protected State currentState;
	

	public StateController() 
	{
		//Synchronized map - only accessible by one thread at a time.
		this.attributes = Collections.synchronizedMap( new HashMap<String, Object>() );
	}

	public void setState( State newState ) 
	{		
		//Exit current state before changing
		if ( currentState != null ) 
			this.currentState.exit();
		
		//Enter new state
		if ( newState != null ) 
		{
			newState.setController( this );
			newState.enter();
		}
		
		this.currentState = newState;
	}

	public void processInput( float delta ) 
	{
		if (this.currentState != null)
			this.currentState.processInput(delta);
	}

	public void updateObjects( float delta ) 
	{
		if (this.currentState != null)
			this.currentState.updateObjects(delta);
	}

	public void render( Graphics2D g, Matrix3x3f view ) 
	{
		if (this.currentState == null)
			return;
		
		g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		this.currentState.render( g, view );
	}

	public Object getAttribute( String name ) 
	{
		return this.attributes.get( name );
	}

	public Object removeAttribute( String name ) 
	{
		return this.attributes.remove( name );
	}

	public void setAttribute( String name, Object attribute ) 
	{
		this.attributes.put( name, attribute );
	}

	public Set<String> getAttributeNames() 
	{
		return this.attributes.keySet();
	}
	
}
