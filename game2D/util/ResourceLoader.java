/**
 * @(#)ResourceLoader.java
 *
 *
 * @author 	Ryan Clemens
 * @version 1.00
 */
package game2D.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * Handles all dirty work of opening input
 * streams to load game data with.
 */
public class ResourceLoader {

	public static InputStream load(Class<?> clazz, String filePath, String resPath) 
	{
		// try the resource first
		InputStream in = null;
		if ( resPath != null && !resPath.isEmpty() ) 
		{
			in = clazz.getResourceAsStream(resPath);
		}
		if (in == null) 
		{
			// try the file path
			try {
				in = new FileInputStream( filePath );
			} catch ( FileNotFoundException e ) {
				System.out.println("\n\t>>>> FileNotFoundException: Error loading file: " + filePath + " <<<<");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("\n\t>>>> Exception: Generice Load Error: " + filePath + " <<<<");
				e.printStackTrace();
			}
		}
		return in;
		
	}
	
}
