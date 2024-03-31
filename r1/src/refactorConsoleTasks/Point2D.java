package refactorConsoleTasks;

public class Point2D implements Comparable<Point2D> {

	private double x;
	private double y;
	
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point2D() {
		this(0, 0);
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public int compareTo(Point2D pt) {
		return Double.compare(getX(), pt.getX());
	}
	
	@Override
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}

	public static void main(String[] args) {
		java.util.List<Point2D> data = new java.util.ArrayList<Point2D>();
		int num;
		double x;

		java.util.Scanner in = new java.util.Scanner(System.in);
		do {
			System.out.print("Кількість точок: ");
			num = in.nextInt();
		} while (num <= 0);

		in.close();
		
		for (int i = 0; i < num; i++) {
			x = 1.0 + (5.0 - 1.0) * Math.random();
			data.add(new Point2D(x, Math.sin(x)));
		}

		System.out.println("Несортовані дані: ");
		for (Point2D pt : data)
			System.out.println(pt);

		System.out.println("\nВідсортовані дані: ");
		java.util.Collections.sort(data);
		for (Point2D pt : data)
			System.out.println("x = " + pt.getX() + "\ty = " + pt.getY());
	}

}
