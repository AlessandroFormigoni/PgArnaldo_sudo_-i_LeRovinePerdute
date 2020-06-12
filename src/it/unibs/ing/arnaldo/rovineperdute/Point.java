package it.unibs.ing.arnaldo.rovineperdute;

public class Point {
	private final static int SQR = 2;
	private int x;
	private int y;
	private int h;
	/**
     *coordinates from XML files for a city
	 * @param x
	 * @param y
	 * @param h
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
	 * see City
	 */
	public double distanceOnPlane(Point p) {
		return Math.sqrt(Math.pow(x-p.getX(), SQR)+Math.pow(y-p.getY(), SQR));
	}
	/** 
     *  calculate altitude difference values ( ​​h)
	 * @param p
	 * @return distance
	 * see City
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
