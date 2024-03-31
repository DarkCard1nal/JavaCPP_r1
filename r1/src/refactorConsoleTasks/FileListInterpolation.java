package refactorConsoleTasks;

import java.io.*;
import java.util.*;

public class FileListInterpolation extends ListInterpolation {
	public FileListInterpolation() {
		super();
	}

	public void readFromFile(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');

		if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
			String fileType = fileName.substring(dotIndex + 1);

			/*
			 * Додайте власний список підтримуємих типів файлів для читання в swich з return
			 * в кінці блоку, наприклад:
			 * 
			 * case "csv":
				readCsv(fileName);
				return;
			 * 
			 */

			switch (fileType) {
			
			}

		}

		readTxt(fileName);
	}

	public void writeToFile(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');

		if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
			String fileType = fileName.substring(dotIndex + 1);

			/*
			 * Додайте власний список підтримуємих типів файлів для запису в swich з return
			 * в кінці блоку, наприклад:
			 * 
			 * case "csv":
				writeCsv(fileName);
				return;
			 * 
			 */

			switch (fileType) {
			
			}

		}
		
		writeTxt(fileName);
		
	}

	private void readTxt(String fileName) {
		try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
			String s = in.readLine(); // читання рядка із заголовками стовпців
			clear();
			while ((s = in.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s);
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				addPoint(new Point2D(x, y));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private void writeTxt(String fileName) {
		try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
			out.printf("%9s%25s\n", "x", "y");
			for (int i = 0; i < numPoints(); i++) {
				out.println(getPoint(i).getX() + "\t" + getPoint(i).getY());
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		FileListInterpolation fun = new FileListInterpolation();

		int num;
		double x;
		java.util.Scanner in = new java.util.Scanner(System.in);

		do {
			System.out.print("Кількість точок: ");
			num = in.nextInt();
		} while (num <= 0);

		for (int i = 0; i < num; i++) {
			x = 1.0 + (5.0 - 1.0) * Math.random();
			fun.addPoint(new Point2D(x, Math.sin(x)));
		}

		in.close();

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
		System.out.println("Зберігаємо у файл");

		fun.writeToFile("data.dat");

		System.out.println("Зчитуємо з файлу");
		fun.clear();

		fun.readFromFile("data.dat");

		System.out.println("Дані з файлу: ");
		fun.sort();
		for (int i = 0; i < fun.numPoints(); i++)
			System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));

		System.out.println("Мінімальне значення x: " + fun.getPoint(0).getX());
		System.out.println("Максимальне значення x: " + fun.getPoint(fun.numPoints() - 1).getX());
		x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
		System.out.println("Значення інтерполяції fun(" + x + ") = " + fun.evalf(x));
		System.out.println("Точне значення sin(" + x + ") = " + Math.sin(x));
		System.out.println("Абсолютна помилка = " + Math.abs(fun.evalf(x) - Math.sin(x)));

		System.out.println("Готуємо дані для розрахунку");
		fun.clear();
		for (x = 1.0; x <= 7.0; x += 0.1) {
			fun.addPoint(new Point2D(x, Math.sin(x)));
		}
		fun.writeToFile("TblFunc.dat");
	}

}
