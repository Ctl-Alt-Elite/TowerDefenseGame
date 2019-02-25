/**
 * @(#)Vector2f.java
 *
 *
 * @author 	Ryan Clemens
 * @version 1.00 
 */
package game2D.util;


/**
 *	Used to represent a location in space or speed in a given direction,
 *	or anything else that has a directional x/y component. 
 */
public class Vector2f {

	/** X-component **/
	public float x;
	/** Y-component **/
	public float y;
	/** Extra coordinate for matrix math to work out **/
	public float w;
	
	/**
	 *	Default constructor, xy-components set to zero
	 */
	public Vector2f() 
	{
		this.x = 0.0f;
		this.y = 0.0f;
		this.w = 1.0f;
	}
	
	/**
	 *	Clone constructor. Creates new Vector2f from given
	 */
	public Vector2f(Vector2f v) 
	{
		this.x = v.x;
		this.y = v.y;
		this.w = v.w;
	}
	
	/**
	 *	Constructs a Vector2f with given x and y components
	 */
	public Vector2f(float x, float y) 
	{
		this.x = x;
		this.y = y;
		this.w = 1.0f;
	}
	
	/**
	 *	Constructs a Vector2f with all 3 components specified
	 */
	public Vector2f(float x, float y, float w) 
	{
		this.x = x;
		this.y = y;
		this.w = w;
	}
	
	/**
	 *	Shift the vector by the given xy-distances
	 */
	public void translate(float tx, float ty) 
	{
		this.x += tx;
		this.y += ty;
	}
	
	/**
	 *	Shift the vector by the given vector
	 */
	public void translate( Vector2f t )
	{
		this.x += t.x;
		this.y += t.y;
	}
	
	/**
	 *	Stretch/shrink the vector by given xy-scales.
	 */
	public void scale(float sx, float sy) 
	{
		this.x *= sx;
		this.y *= sy;
	}
	
	/**
	 *	Rotate the vector about the given number of radians
	 */
	public void rotate(float rad) 
	{
		float tmp = (float) (this.x * Math.cos(rad) - this.y * Math.sin(rad));
		this.y = (float) (this.x * Math.sin(rad) + this.y * Math.cos(rad));
		this.x = tmp;
	}
	
	/**
	 *	Stretch/shrink xy-component by given scales
	 */
	public void shear(float sx, float sy) 
	{
		float tmp = this.x + sx * this.y;
		this.y = this.y + sy * this.x;
		this.x = tmp;
	}
	
	/**
	 *	Adds this vector to given and returns result.
	 */
	public Vector2f add(Vector2f v) 
	{
		return new Vector2f(this.x + v.x, this.y + v.y);
	}
	
	/**
	 *	Subtracts given vector from this one and returns result.
	 */
	public Vector2f sub(Vector2f v) 
	{
		return new Vector2f(this.x - v.x, this.y - v.y);
	}
	
	/**
	 *	Multiplies this vector by given scalar value.
	 */
	public Vector2f mul(float scalar) 
	{
		return new Vector2f(scalar * this.x, scalar * this.y);
	}
	
	/**
	 *	Divide this vector by given scalar value
	 */
	public Vector2f div(float scalar) 
	{
		return new Vector2f(this.x / scalar, this.y / scalar);
	}
	
	/**
	 *	Referse direction of this vector
	 */
	public Vector2f inv() 
	{
		return new Vector2f(-this.x, -this.y);
	}
	
	/**
	 *	Produces a normal vector in same direction. Normal vector is
	 *	a vector with length of 1.
	 */
	public Vector2f norm() 
	{
		return this.div(this.len());
	}
	
	/**
	 *	Dot product of this vector with a given
	 */
	public float dot(Vector2f v) 
	{
		return this.x * v.x + this.y * v.y;
	}
	
	/**
	 *	Returns distance between this vector and a given vector
	 */
	public float distanceTo( Vector2f v )
	{
		return  (float) Math.sqrt( (this.x - v.x)*(this.x - v.x) + (this.y - v.y)*(this.y - v.y) );
	}
	
	/**
	 *	Returns the angle between this vector and a given vector
	 */
	public float directionTo( Vector2f v )
	{
		return  (float) Math.atan( this.y - v.y / this.x - v.x );
	}
	
	/**
	 *	Returns midpoint Vector between this vector and a given vector
	 */
	public Vector2f midpoint( Vector2f v )
	{
		return  new Vector2f( (this.x + v.x) / 2.0f, (this.y + v.y) / 2.0f );
	}
	
	/**
	 *	Returns the length of this vector.
	 */
	public float len() 
	{
		return (float) Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	/**
	 *	Returns the squared length of this vector.
	 */
	public float lenSqr() 
	{
		return this.x * this.x + this.y * this.y;
	}
	
	/**
	 *	Returns a vector perpendicular to this one
	 */
	public Vector2f perp() 
	{
		return new Vector2f(-this.y, this.x);
	}
	
	/**
	 *	Returns the angle(direction) of this vector
	 */
	public float angle() 
	{
		return (float) Math.atan2(this.y, this.x);
	}
	
	/**
	 *	Converts and returns an xy-component Vector2f from given polar coordinates (dir and radius).
	 */
	public static Vector2f polar(float angle, float radius) 
	{
		return new Vector2f(radius * (float) Math.cos(angle), radius * (float) Math.sin(angle));
	}
	
	/**
	 *	String representation of a Vector2f object
	 */
	@Override public String toString() 
	{
		return String.format("(%s,%s)", this.x, this.y);
	}
	
}
