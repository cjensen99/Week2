package week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Week2 {

	public static void main(String[] args) throws IOException {
		int[][] temperatures = new int[31][3];
		BufferedReader br;
		BufferedWriter bw;
		File file = new File("src/week2/Temperatures.csv");
		String line = "";
		
		try {
			br = new BufferedReader(new FileReader(file));
			for(int i = 0; i < 31; i++) {	
				line = br.readLine();
				String[] newLine = line.split(",");
				temperatures[i][0] = Integer.parseInt(newLine[0]);
				temperatures[i][1] = Integer.parseInt(newLine[1]);
				temperatures[i][2] = Integer.parseInt(newLine[2]);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		//display first report in the console and TemperaturesReport text file
		System.out.println(returnReportHead());
		bw = new BufferedWriter(new FileWriter(new File("src/week2/TemperaturesReport.txt")));
		bw.write(returnReportHead() + "\n");
		int highestDay = temperatures[0][0];
		int lowestDay = temperatures[0][0];
		int averageHi = 0;
		int averageLow = 0;
		for(int i = 0; i < 31; i++) {
			int variation = temperatures[i][1] - temperatures[i][2];
			if(temperatures[i][1] > temperatures[highestDay - 1][1]) highestDay = temperatures[i][0];
			if(temperatures[i][2] < temperatures[lowestDay - 1][2]) lowestDay = temperatures[i][0];
			averageHi += temperatures[i][1];
			averageLow += temperatures[i][2];
			System.out.printf("%-4d %-4d %-4d %d%n", temperatures[i][0], temperatures[i][1], 
					temperatures[i][2], variation);
			bw.append(String.format("%-4d %-4d %-4d %d%n", temperatures[i][0], temperatures[i][1], 
					temperatures[i][2], variation));
		}
		System.out.println(returnDecemberStats(temperatures, highestDay, lowestDay, averageHi, averageLow));
		bw.append(returnDecemberStats(temperatures, highestDay, lowestDay, averageHi, averageLow));
		
		//Display the second report on the console and the TemperaturesReport file
		System.out.println(returnGraphHead());
		System.out.println(returnGraph(temperatures));
		System.out.println(returnGraphBottom());
		bw.append("\n" + returnGraphHead());
		bw.append("\n" + returnGraph(temperatures));
		bw.append("\n" + returnGraphBottom());
		bw.close();
	}
	
	private static String returnReportHead() {
		String head = "--------------------------------------------------------------\n"
				+ "December 2020: Temperatures in Utah\n"
				+ "--------------------------------------------------------------\n"
				+ "Day  High Low  Variance\n"
				+ "--------------------------------------------------------------";
		return head;
	}
	
	private static String returnDecemberStats(int[][] temperatures, int highestDay, int lowestDay, 
			int averageHi, int averageLow) {
		double averageH = (double) averageHi / 31.0;
		double averageL = (double) averageLow / 31.0;
		String head = "--------------------------------------------------------------\n"
				+ "December Highest Temperature: 12/" + highestDay + ": " + temperatures[highestDay - 1][1]
				+ " Average Hi: " + String.format("%.1f", averageH) + "\n"
				+ "December Lowest Temperature:  12/" + lowestDay + ": " + temperatures[lowestDay - 1][2]
				+ " Average Lo: " + String.format("%.1f", averageL);
		return head;
	}
	
	private static String returnGraphHead() {
		String head = "--------------------------------------------------------------\n"
				+ "Graph\n"
				+ "--------------------------------------------------------------\n"
				+ "      1   5    10   15   20   25   30   35   40   45   50\n"
				+ "      |   |    |    |    |    |    |    |    |    |    |\n"
				+ "--------------------------------------------------------------";
		return head;
	}
	
	private static String returnGraphBottom() {
		String head = "--------------------------------------------------------------\n"
				+ "      1   5    10   15   20   25   30   35   40   45   50\n"
				+ "      |   |    |    |    |    |    |    |    |    |    |\n"
				+ "--------------------------------------------------------------";
		return head;
	}
	
	private static String returnGraph(int[][] temperatures) {
		String head = "";
		for(int i = 0; i < 31; i++) {
			head += String.format("%-2d %-2s ", i + 1, "Hi");
			for(int j = 0; j < temperatures[i][1]; j++) {
				head += "+";
			}
			head += "\n   Lo ";
			for(int j = 0; j < temperatures[i][2]; j++) {
				head += "-";
			}
			if(i < 30) head += "\n";
		}
		return head;
	}

}


