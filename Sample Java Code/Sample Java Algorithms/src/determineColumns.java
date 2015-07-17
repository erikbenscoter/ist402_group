import java.util.Scanner;

public class determineColumns {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scnr = new Scanner(System.in);

		System.out.println("Enter how many teams for the tournament:");

		int totalTeams = scnr.nextInt();
		int brkSize;

		if ((totalTeams <= 32) && (totalTeams > 16)) {
			brkSize = 32;
		} else if ((totalTeams <= 16) && (totalTeams > 8)) {
			brkSize = 16;
		} else if ((totalTeams <= 8) && (totalTeams > 4)) {
			brkSize = 8;
		} else if ((totalTeams <= 4) && (totalTeams > 2)) {
			brkSize = 4;
		} else if ((totalTeams <= 2) && (totalTeams > 1)) {
			brkSize = 2;
		} else {
			brkSize = 1;
		}

		System.out.println("The brack size is " + brkSize + " teams.");

		int col = 1;
		int row = brkSize;

		while (brkSize > 1) {
			brkSize = brkSize / 2;
			col++;
			row = row + brkSize;
			System.out.println(col + " " + brkSize);

		}

		System.out.println("for a tournament with " + totalTeams
				+ " the table for the bracket " + "will have " + col
				+ " columns and " + row + " rows.");

	}

}
