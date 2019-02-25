/**
 * @(#)Utility.java
 *
 *
 * @author 	Ryan Clemens
 * @version 1.00 
 */
package game2D.util;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 
 */
public class Utility {

//===================================================================================================
//					VIEWPORTS
//===================================================================================================
	
	/**
	 *	Converts WORLD to SCREEN coordinates.
	 */
	public static Matrix3x3f createViewport( float worldWidth, float worldHeight, float screenWidth, float screenHeight ) 
	{
		//Scale up to screen size
		float sx = ( screenWidth - 1 ) / worldWidth;
		float sy = ( screenHeight - 1 ) / worldHeight;
		float tx = 0;
		float ty = screenHeight - 1;
		Matrix3x3f viewport = Matrix3x3f.scale( sx, -sy );
		viewport = viewport.mul( Matrix3x3f.translate( tx, ty ) );
		return viewport;
		
	}
	
	/**
	 *	Converts SCREEN coordinates to WORLD coordinates.
	 */
	public static Matrix3x3f createReverseViewport( float worldWidth, float worldHeight, float screenWidth, float screenHeight ) 
	{
		//Scale down to world size (16 x 11)
		float sx = worldWidth / ( screenWidth - 1 );
		float sy = worldHeight / ( screenHeight - 1 );
		float tx = 0;
		float ty = screenHeight - 1;
		Matrix3x3f viewport = Matrix3x3f.translate(-tx, -ty);
		viewport = viewport.mul(Matrix3x3f.scale(sx, -sy));
		return viewport;
		
	}

//===================================================================================================
//					DRAW STRINGS - TOP LEFT CORNER
//===================================================================================================

	/**
	 * Draw Strings to screen with top left corner positioned at given
	 * x and y coordinates by accounts for Baseline, Ascent, Descent, and
	 * leadings values.
	 */
	public static int drawString(Graphics g, int x, int y, String str) 
	{
		return drawString(g, x, y, new String[] { str });
	}

	public static int drawString(Graphics g, int x, int y, List<String> str) 
	{
		return drawString(g, x, y, str.toArray(new String[0]));
	}

	public static int drawString(Graphics g, int x, int y, String... str) 
	{
		FontMetrics fm = g.getFontMetrics();
		int height = fm.getAscent() + fm.getDescent() + fm.getLeading();
		for (String s : str) 
		{
			g.drawString(s, x, y + fm.getAscent());
			y += height;
		}
		return y;
	}

//===================================================================================================
//					DRAW STRINGS - CENTERED ON SCREEN
//===================================================================================================


	/**
	 *	Draw Strings to screen centered on screen, at a given height, with
	 *	a desired width. This method calculates the width of String using 
	 *	the screen width.
	 */
	public static int drawCenteredString( Graphics g, int w, int y, String str )
	{
		return  drawCenteredString( g, w, y, new String[]{ str });
		
	}
	
	public static int drawCenteredString( Graphics g, int w, int y, List<String> str )
	{
		return drawCenteredString( g, w, y, str.toArray( new String[0] ) );
	
	}
	
	public static int drawCenteredString ( Graphics g, int w, int y, String ... str )
	{
		FontMetrics fm = g.getFontMetrics();
		int height = fm.getAscent() + fm.getDescent() + fm.getLeading();
		
		for ( String s : str ) 
		{
			Rectangle2D bounds = g.getFontMetrics().getStringBounds( s, g );
			int x = ( w - (int)bounds.getWidth() ) / 2;
			g.drawString( s, x, y + fm.getAscent() );
			y += height;
			
		}
		return y;
		
	}
	
	
//===================================================================================================
//					IMAGE SCALING 
//===================================================================================================
	
	/**
	 *	Scales images up or down determined by target size.
	 */
	public static BufferedImage scaleImage(BufferedImage toScale, int targetWidth, int targetHeight) 
	{
		//Original sizes
		int width = toScale.getWidth();
		int height = toScale.getHeight();
		
		//Scale down
		if ( targetWidth < width || targetHeight < height ) 
			return scaleDownImage(toScale, targetWidth, targetHeight);
		
		else 
			return scaleUpImage(toScale, targetWidth, targetHeight);
		
	}

	/**
	 *	Scale image up
	 */
	private static BufferedImage scaleUpImage(BufferedImage toScale, int targetWidth, int targetHeight) 
	{
		//Create new proper sized image
		BufferedImage image = new BufferedImage( targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB );
		//Create graphics object to copy to new image
		Graphics2D g2d = image.createGraphics();
		g2d.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
		//Draw old image to new image
		g2d.drawImage( toScale, 0, 0, image.getWidth(), image.getHeight(), null );
		g2d.dispose();
		return image;
	}

	/**
	 *	Scale image down
	 */
	private static BufferedImage scaleDownImage( BufferedImage toScale, int targetWidth, int targetHeight ) 
	{
		//Original size
		int w = toScale.getWidth();
		int h = toScale.getHeight();
		//Cut image down until proper size found
		do {
			w = w / 2;
			if (w < targetWidth) 
				w = targetWidth;
			
			h = h / 2;
			if (h < targetHeight) 
				h = targetHeight;
			
			BufferedImage tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = tmp.createGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2d.drawImage(toScale, 0, 0, w, h, null);
			g2d.dispose();
			toScale = tmp;
			
			
		} while (w != targetWidth || h != targetHeight);
		
		return toScale;
	}
	
//===================================================================================================

}
