/**
 * @(#)GameFramework.java
 *
 *
 * @author	Ryan Clemens
 * @version 1.00 
 */
package game2D.framework;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import game2D.io.KeyboardInput;
import game2D.io.RelativeMouseInput;
import game2D.util.Matrix3x3f;
import game2D.util.Utility;
import game2D.util.Vector2f;
import game2D.util.FrameRate;


/**
 *	
 */
public abstract class GameFramework extends JFrame implements Runnable {


	//BufferStrategy for canvas from which drawing Graphics is created 
 	private BufferStrategy bs;
 	//Indicates if game thread is currently running (volatile -> global copy)
 	private volatile boolean running;
 	//Game Thread from which all game updates are invoked
 	private Thread gameThread;
 	
 	//Positions of viewing window
 	protected int vx, vy, vw, vh;
 	
 	//Frame rate object calculating processing speed
 	protected FrameRate frameRate;
 	 	
 	//Mouse input
 	protected RelativeMouseInput mouse;
 	//Keyboard input
 	protected KeyboardInput keyboard;
 	
 	//Application details
 	protected Color appBackground = Color.BLACK;
	protected Color appBorder = Color.LIGHT_GRAY;
	protected Color appFPSColor = Color.RED;
	protected Font appFont = new Font("Courier New", Font.PLAIN, 14);
	protected String appTitle = "TBD-Title";
	protected float appBorderScale = 0.5f;	//Border to fill space when maintaining ratio
	protected int appWidth = 932;
	protected int appHeight = 640;
	protected float appWorldWidth = 16.0f;	//16.0 implies (0, 16) system
	protected float appWorldHeight = 2.0f;	//11.0 implies (0, 11) system
	protected long appSleep = 10L;
	protected boolean appMaintainRatio = false;	//Keep display area to given ratio
	protected boolean appDisableCursor = false;	//Disable mouse cursor when not needed for game
	protected int textPos = 0;	//Last position of line of text
	
	/**
	 *	Constructor - nothing to see here
	 */
	public GameFramework()
	{
		//ehh.....
	}
	
	
	/** Abstract Methods **/
	protected abstract void createFramework();	//Designed for game specific creation
	protected abstract void renderFrame( Graphics g );		//Renders game spefific frame
	public abstract int getScreenWidth();		//Depends on games dimensional setup
	public abstract int getScreenHeight();
	/** --------------   -- **/
	
	
	/**
	 *	Handles setting up GUI, intended to be invoked from 
	 *	Swing Event Dispatch Thread.
	 */
	protected void createAndShowGUI()
	{				
		//Create game specific game2D.framework
		this.createFramework();
		
		//Disable mouse cursor if necessary
		if (this.appDisableCursor)
		{
			this.disableCursor();
		}
				
		//Create and start game thread
		this.gameThread = new Thread(this, "Game Thread");
		gameThread.start();
		
	}
	
	/**
	 *	Creates and sets up any inputs for game
	 */
	protected void setupInput( Component component )
	{
		this.keyboard = new KeyboardInput();
		component.addKeyListener( this.keyboard );
		
		this.mouse = new RelativeMouseInput( component );
		component.addMouseListener( this.mouse );
		component.addMouseMotionListener( this.mouse );
		component.addMouseWheelListener( this.mouse );
		
	}
	
	/**
	 *	Creates buffer strategy for canvas. Defaults to
	 *	Double Buffer (2) unless overriden by children
	 */
	protected void createBufferStrategy( Canvas canvas )
	{
		//Set up a double buffer (2) strategy on canvas
		canvas.createBufferStrategy(2);
		//Assign the buffer strategy to the game2D.framework for reference
		this.bs = canvas.getBufferStrategy();
	}
	
	/**
	 *	Creates a buffer strategy for windowed games. Defaults
	 *	to Double Buffer (2) unless overriden by children.
	 */
	protected void createBufferStrategy( Window window )
	{		
		//Set up double buffer (2) strategy on window
		window.createBufferStrategy(2);
		//Assign buffer strategy to the game2D.framework for reference
		this.bs = window.getBufferStrategy();	
	}
	
	/**
	 *	Calculates the values for the viewport width, height, 
	 *	and upper-left coordinates. The actual creation of the 
	 *	viewport must be left to the subclasses.
	 */
	protected void setupViewport( int sw, int sh )
	{
		//Actual viewing port window width/height with border
		int w = (int)(sw * this.appBorderScale);
		int h = (int)(sh * this.appBorderScale);
		//Top left corner of viewing port window
		int x = ( sw - w ) / 2;
		int y = ( sh - h ) / 2;
		
		this.vw = w;
		this.vh = (int)( w * this.appWorldHeight / this.appWorldWidth );
		
		//Correction if one dimension is too big, switch dimensions
		if ( this.vh > h )
		{
			this.vw = (int)( h * this.appWorldWidth / this.appWorldHeight );
			this.vh = h;
		}
		
		this.vx = x + ( w - this.vw ) / 2;
		this.vy = y + ( h - this.vh ) / 2;
		
	}
	
	/**
	 *	Create viewport transform matrix:	World -> Screen
	 */
	protected Matrix3x3f getViewportTransform()
	{	
		return Utility.createViewport( this.appWorldWidth, this.appWorldHeight, this.getScreenWidth(), this.getScreenHeight() );
	}
	
	/**
	 *	Create reverse transform matrix:	Screen -> World
	 */
	protected Matrix3x3f getReverseViewportTransform() 
	{
		return Utility.createReverseViewport( this.appWorldWidth, this.appWorldHeight, this.getScreenWidth(), this.getScreenHeight());
		
	}
	
	/**
	 *	Convert screen mouse pointer coordinates to game world.
	 */
	protected Vector2f getWorldMousePosition() 
	{
		Matrix3x3f screenToWorld = this.getReverseViewportTransform();
		Point mousePos = this.mouse.getPosition();
		Vector2f screenPos = new Vector2f( mousePos.x, mousePos.y );
		return  screenToWorld.mul(screenPos);
		
	}

	/**
	 *	
	 */
	protected Vector2f getRelativeWorldMousePosition() 
	{
		float sx = this.appWorldWidth / ( this.getScreenWidth() - 1 );
		float sy = this.appWorldHeight / ( this.getScreenHeight() - 1 );
		Matrix3x3f viewport = Matrix3x3f.scale( sx, -sy );
		Point p = this.mouse.getPosition();
		return viewport.mul( new Vector2f(p.x, p.y) );
		
	}
	
	/**
	 *	Initialize game thread components when game thread is started.
	 */
	@Override public void run()
	{
		this.running = true;
		this.initialize();
		long curTime = System.nanoTime();
		long lastTime = curTime;
		double nsPerFrame;
		while ( this.running ) 
		{
			curTime = System.nanoTime();
			nsPerFrame = curTime - lastTime;
			//Invoke interation of game loop with measured processing speed
			this.gameLoop( (float)( nsPerFrame / 1.0E9 ) );
			lastTime = curTime;
			
		}
		//Loop no longer running, terminate everything
		this.terminate();
		
	}
	
	/**
	 *	Initialize game components
	 */
	protected void initialize()
	{
		this.frameRate = new FrameRate();
		this.frameRate.initialize();
		
	}
	
	/**
	 *	Handles safely terminating individual game threads
	 */
	protected void terminate()
	{
		//Nothing to terminate yet...
	}
	
	/**
	 *	Actual game loop - input, update, draw, pause, repeat.
	 */
	private void gameLoop( float delta )
	{
		this.processInput( delta );
		this.updateObjects( delta );
		this.renderFrame();
		this.sleep( this.appSleep );
	}
	
	/**
	 *	Renders each frame of game by accessing current Graphics object
	 *	from the assigned buffer strategy and swaps with the previous
	 *	Graphics object.
	 */
	private void renderFrame()
	{
		//Renders a single frame
		do {
			//Following loop ensures the contents of the
			//	drawing buffer are the same in case the
			//	underlying surface was recreated.
			do {
				Graphics g = null;
				try {
					//Retrieves the back buffer graphics
					g = this.bs.getDrawGraphics();
					this.renderFrame(g);
				
				//After rendering is completed...
				} finally {
					if ( g != null )
						//Release lock on graphics object
						//	(lookup "VolatileImage")
						g.dispose();
				}
			//Repeat if the drawing buffer was recently
			//	restored from a lost state
			//	(lookup "VolatileImage")
			} while ( this.bs.contentsRestored() );
			//Show newly drawn graphics
			//	i.e switches the current buffer with
			//		the one that was just drawn onto
			this.bs.show();
		//Repeat the rendering if the drawing buffer
		//	was lost... again, lookup "VolatileImage"
		} while ( this.bs.contentsLost() );
		
	}
	
	/**
	 *	Handles pausing game thread
	 */
	private void sleep( long sleep )
	{
		try {
			Thread.sleep( sleep );
		} catch (InterruptedException e) {
			//Nothing
		}
	}
	
	/**
	 *	Process inputs for each frame
	 */
	protected void processInput( float delta )
	{
		this.keyboard.poll();
		this.mouse.poll();
	}
	
	/**
	 *	Update game objects each frame
	 */
	protected void updateObjects( float delta )
	{
		//No objects yet...
	}
	
	/**
	 *	Render (draw) next game frame
	 */
	protected void render( Graphics g )
	{
		g.setFont( this.appFont );
		g.setColor( this.appFPSColor );
		this.frameRate.calculate();
		this.textPos = Utility.drawString( g, 20, 0, frameRate.getFrameRate() );
		
	}
 	
 	/**
 	 *	Disable mouse cursor from showing on screen
 	 */
 	private void disableCursor()
 	{
 		Toolkit tk = Toolkit.getDefaultToolkit();
 		Image image = tk.createImage( "" );
 		Point point = new Point(0, 0);
 		String name = "CanBeAnything";
 		Cursor cursor = tk.createCustomCursor( image, point, name );
 		this.setCursor( cursor ); //JFrame method
 		
 	}
 	
 	/**
 	 *	Handles shutting down game parts and killing threads
 	 */
 	protected void shutDown() 
 	{
 		//Cannont shut down from game thread
 		if ( Thread.currentThread() != this.gameThread )
 		{
 			try {
 				this.running = false;
 				this.gameThread.join();
 				this.onShutDown();
 				
 			} catch (InterruptedException e) {
 				e.printStackTrace();
 			}
 			//Exit
 			System.exit(0);
 		}
 		//Attempted shut down from game thread -> jump to Event Dispatch Thread and shut down
 		else
 		{
 			//Access EDT
 			SwingUtilities.invokeLater ( new Runnable() {
 				@Override public void run() {
 					//Shut down from EDT 
 					shutDown();
 				}
 			});
 		}
 		
 	}
 	
 	/**
 	 *	Intended to handle ending events when game is to be shut down
 	 */
 	protected void onShutDown()
 	{
 		//Nothing cool yet...
 	}
 	
//============================================================================
 	
 	protected static void launchApp(final GameFramework app) 
 	{
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				app.shutDown();
			}
		});
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				app.createAndShowGUI();
			}
		});
	}
	
}
