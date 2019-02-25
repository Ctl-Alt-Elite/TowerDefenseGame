/**
 * @(#)WindowFramework.java
 *
 *
 * @author	Ryan Clemens 
 * @version 1.00 
 */
package game2D.framework;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Extends GameFramework to provide a game window.
 */
public class WindowFramework extends GameFramework {
	
	
	//Canvas game is drawn on
	protected Canvas canvas;

	/**
	 *	Override GameFramework to create the canvas
	 */
	@Override protected void createFramework() 
	{
		this.canvas = new Canvas();
		this.canvas.setBackground( this.appBackground );
		this.canvas.setIgnoreRepaint( true );
		this.getContentPane().add( this.canvas );
		this.setLocationByPlatform( true );
		this.setLocation(0, 0);
		//Set up app ratio size
		if ( this.appMaintainRatio ) 
		{
			this.getContentPane().setBackground( this.appBorder );
			this.setSize( this.appWidth, this.appHeight );
			this.setLayout(null);
			this.getContentPane().addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent e) {
					onComponentResized(e);
				}
			});
		} 
		else 
		{
			this.canvas.setSize( this.appWidth, this.appHeight );
			this.pack();
		}
		
		this.setTitle( this.appTitle );
		this.setupInput( this.canvas );			
		this.setVisible(true);
		this.createBufferStrategy( this.canvas );
		this.canvas.requestFocus();
	}

	protected void onComponentResized(ComponentEvent e) 
	{
		Dimension size = this.getContentPane().getSize();
		//Calculate start xy and sizes for canvas (app border applied)
		this.setupViewport( size.width, size.height );
		//Set canvas to position based off app border
		this.canvas.setLocation( this.vx, this.vy );
		//Set canvas size based off app border
		this.canvas.setSize( this.vw, this.vh );
		
	}

	public int getScreenWidth() 
	{
		return this.canvas.getWidth();
	}

	public int getScreenHeight() 
	{
		return this.canvas.getHeight();
	}

	@Override protected void renderFrame(Graphics g) 
	{
		g.clearRect(0, 0, this.getScreenWidth(), this.getScreenHeight());
		this.render(g);
		
	}
	
}
