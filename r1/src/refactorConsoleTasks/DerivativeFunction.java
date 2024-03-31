package refactorConsoleTasks;

public class DerivativeFunction implements Evaluatable {

	private Evaluatable function;
	private double accuracy;

	public DerivativeFunction(Evaluatable function, double accuracy) {
		this.function = function;
		this.accuracy = accuracy;
	}

	public DerivativeFunction(Evaluatable function) {
		this(function, 1.0e-4);
	}

	@Override
	public double evalf(double x) {
		final int MAX = 100;
		double h = 0.1;
		double one = meth(x, h, this.function);
		h = 0.1 * h;
		double two = meth(x, h, this.function);
		int i = 0;
		double tmp;
		boolean ok;
		do {
			h = 0.1 * h;
			tmp = meth(x, h, this.function);
			ok = (Math.abs(tmp - two) >= Math.abs(two - one)) || (Math.abs(two - one) < accuracy);
			if (i > MAX) {
				System.out.print("Занадто багато кроків обчислень");
				System.exit(-1);
			}
			i += 1;
			one = two;
			two = tmp;
		} while (!ok);

		return two;
	}

	private static double meth(double x, double h, Evaluatable f) {
		return 0.5 * (f.evalf(x + h) - f.evalf(x - h)) / h;
	}

	public static void main(String[] args) {
		// Перевірка методу диференціювання
		ListInterpolation fun = new ListInterpolation();

		int num;
		double x = -0.5 * Math.PI;
		double step = 0.1;
		java.util.Scanner in = new java.util.Scanner(System.in);

		do {
			System.out.print("Кількість точок: ");
			num = in.nextInt();
		} while (num <= 0);

		in.close();

		for (int i = 0; i < num; i++) {
			x += step;
			fun.addPoint(new Point2D(x, Math.sin(x)));
		}

		x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
		
		DerivativeFunction derFun = new DerivativeFunction(fun);
		
		double res = derFun.evalf(x);
		System.out.println("Чисельне значення sin'(" + x + ") = " + res);
		System.out.println("Символьне значення sin'(" + x + ") = " + Math.cos(x));
		System.out.println("Абсолютна помилка = " + Math.abs(res - Math.cos(x)));
	}
}
