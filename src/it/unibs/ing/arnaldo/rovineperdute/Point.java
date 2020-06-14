package it.unibs.ing.arnaldo.rovineperdute;

/**
 * Represents a point object to store the coordinates of a {@linkplain City}
 * @author Simone, Alessandro, Francesca
 *
 */
public class Point {
	private final static int POW = 2;
	private int x;
	private int y;
	private int h;
	
	/**
     * Needs coordinates of position and altitude
	 * @param x x position
	 * @param y y position
	 * @param h altitude
	 */
	public Point(int x, int y, int h) {
		this.x = x;
		this.y = y;
		this.h = h;
	}
	
	/** 
     *calculate linear distance between two coordinate points (x,y) 
	 * @param p
	 * @return distance
	 */
	public double distanceOnPlane(Point p) {
		return Math.sqrt(Math.pow(x-p.getX(), POW)+Math.pow(y-p.getY(), POW));
	}
	
	/** 
     *  calculate altitude difference values ( ​​h)
	 * @param p
	 * @return distance
	 */
	public double altitudeDifference(Point p) {
		return Math.abs(h-p.getH());
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getH() {
		return h;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setH(int h) {
		this.h = h;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", h=" + h + "]";
	}
	
	
	
	
}
