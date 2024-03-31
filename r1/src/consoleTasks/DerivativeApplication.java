package consoleTasks;

import java.io.*;

public class DerivativeApplication {

	public static void main(String[] args) throws IOException {
		Evaluatable functs[] = new Evaluatable[4];
		functs[0] = new FFunction(0.5);
		functs[1] = new FFunction(1);
		functs[2] = new FFunction(1.5);
		functs[3] = new FileListInterpolation();

		try {
			((FileListInterpolation) functs[3]).readFromFile("TblFunc.dat");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		String fileName = "";
		for (Evaluatable f : functs) {
			fileName = f.getClass().getSimpleName();
			if (f.getClass() == FFunction.class) 
				fileName += ((FFunction) f).getA();
			
			System.out.println("Функція: " + fileName);
			fileName += ".dat";
			PrintWriter out = new PrintWriter(new FileWriter(fileName));
			out.printf("x\tf(x)\tdf(x)\n");
			for (double x = 1.5; x <= 6.5; x += 0.05) {
				System.out.println("x: " + x + "\tf: " + f.evalf(x) + "\tf': " + NumMethods.der(x, 1.0e-4, f));
				out.printf("%.6e\t%.6e\t%.6e\n", x, f.evalf(x), NumMethods.der(x, 1.0e-4, f));
			}
			System.out.println("\n");
			out.close();
		}
	}

}
