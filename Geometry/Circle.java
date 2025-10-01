package Geometry;

public class Circle {
	private float radius;
	private float x;
	private float y;
	
	public Circle(float rad) {
		this(rad, 0, 0);
	}
	
	public Circle(float rad, float x, float y) {
		this.radius = rad;
		this.x = x;
		this.y = y;
	}

	public float getRadius() {
		return radius;
	}	

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	@Override
	public String toString() {
		return "Radius is " + radius;
	}
	
	@Override
	public Circle clone() {
		return new Circle(radius);
	}
}
