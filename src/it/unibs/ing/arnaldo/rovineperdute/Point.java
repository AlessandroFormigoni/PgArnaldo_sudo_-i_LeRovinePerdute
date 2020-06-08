package it.unibs.ing.arnaldo.rovineperdute;

public class Point {
	private final static int SQR = 2;
	private int x;
	private int y;
	private int h;
	
	public Point(int x, int y, int h) {
		this.x = x;
		this.y = y;
		this.h = h;
	}
	
	public double distanceOnPlane(Point p) {
		return Math.sqrt(Math.pow(x-p.getX(), SQR)+Math.pow(y-p.getY(), SQR));
	}
	
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
	
	
	
	
}
