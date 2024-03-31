package refactorConsoleTasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListInterpolation extends Interpolator {

	private List<Point2D> data = null;
	
	public ListInterpolation() {
		this.data = new ArrayList<Point2D>();
	}

	public ListInterpolation(Point2D[] data) {
		this.data = Arrays.asList(data);
	}
	
	public ListInterpolation(List<Point2D> data) {
		this.data = data;
	}

	@Override
	public void clear() {
		this.data.clear();
	}

	@Override
	public int numPoints() {
		return this.data.size();
	}

	@Override
	public void addPoint(Point2D pt) {
		this.data.add(pt);
	}

	@Override
	public Point2D getPoint(int i) {
		return this.data.get(i);
	}

	@Override
	public void setPoint(int i, Point2D pt) {
		this.data.set(i, pt);
	}

	@Override
	public void removeLastPoint() {
		this.data.remove(this.data.size() - 1);
	}

	@Override
	public void sort() {
		java.util.Collections.sort(this.data);
	}

	public static void main(String[] args) {
		ListInterpolation fun = new ListInterpolation();
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
			fun.addPoint(new Point2D(x, Math.sin(x)));
		}
		System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");
		System.out.println("Несортований набір: ");
		for (int i = 0; i < fun.numPoints(); i++)
			System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));

		fun.sort();
		System.out.println("Відсортований набір: ");
		for (int i = 0; i < fun.numPoints(); i++)
			System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));

		System.out.println("Мінімальне значення x: " + fun.getPoint(0).getX());
		System.out.println("Максимальне значення x: " + fun.getPoint(fun.numPoints() - 1).getX());

		x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
		System.out.println("Значення інтерполяції fun(" + x + ") = " + fun.evalf(x));
		System.out.println("Точне значення sin(" + x + ") = " + Math.sin(x));
		System.out.println("Абсолютна помилка = " + Math.abs(fun.evalf(x) - Math.sin(x)));
		
		System.out.println("Замінними 0 точку в наборі на (-2.5;-4.78) :");
		fun.setPoint(0, new Point2D(-2.5, -4.78));
		System.out.println("Змінений набір: ");
		for (int i = 0; i < fun.numPoints(); i++)
			System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
		
		System.out.println("Видалемо останню точку в наборі:");
		fun.removeLastPoint();;
		System.out.println("Змінений набір: ");
		for (int i = 0; i < fun.numPoints(); i++)
			System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
		
		System.out.println("Очистемо набір:");
		fun.clear();
		System.out.println("Змінений набір: ");
		for (int i = 0; i < fun.numPoints(); i++)
			System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
	}

}
