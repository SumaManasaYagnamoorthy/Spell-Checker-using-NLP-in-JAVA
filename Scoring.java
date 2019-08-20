import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Scoring {

  static int[][] reversal = new int[26][26];

  static int[][] substitute = new int[26][26];

  static int[][] delete = new int[27][26];

  static int[][] insert = new int[27][26];

  public static double reversescoring(String str, char m, char n) {

    Scanner sc = null;
    try {
	 sc = new Scanner(new BufferedReader(
	     new FileReader("E:\\srcnew\\ConfusionMatrixForReversal.txt")));

    } catch (FileNotFoundException e) {
	 e.printStackTrace();
    }
    while (sc.hasNext()) {
	 for (int i = 0; i < 26; i++) {

	   String[] line = sc.nextLine().trim().split(" ");

	   for (int j = 0; j < 26; j++) {

		try {
		  reversal[i][j] = Integer.parseInt(line[j]);
		} catch (NumberFormatException nfex) {
		}

	   }

	 }
    }

    long freq = Main.hm.get(str);

    double priorprobability = (double)freq + (double) 0.5;

    double cp = ((double)(reversal[Character.getNumericValue(m) - 10][Character.getNumericValue(n) - 10])
        / ((double)(Main.matrix[Character.getNumericValue(m) - 10][Character.getNumericValue(n) - 10])));

    double raw = (priorprobability * cp);

    return raw;
  }

  public static double substitutescoring(String str, char m, char n) {

    Scanner sc = null;
    try {
	 sc = new Scanner(new BufferedReader(
	     new FileReader("E:\\srcnew\\ConfusionMatrixForSubstitution.txt")));

    } catch (FileNotFoundException e) {
	 e.printStackTrace();
    }
    while (sc.hasNext()) {

	 for (int i = 0; i < 26; i++) {

	   String[] line = sc.nextLine().trim().split(" ");

	   for (int j = 0; j < 26; j++) {

		try {
		  substitute[i][j] = Integer.parseInt(line[j]);
		} catch (NumberFormatException nfex) {
		}

	   }

	 }
    }

    long freq = Main.hm.get(str);
    double priorprobability = (double)freq + (double) 0.5;

    double cp = (((double)(substitute[Character.getNumericValue(m) - 10][Character.getNumericValue(n) - 10]))
        / ((double)(Main.map.get(String.valueOf(n)))));

    double raw = (priorprobability * cp);

    return raw;
  }

  public static double deletionscoring(String str, char m, char n) {

    Scanner sc = null;
    try {
	 sc = new Scanner(new BufferedReader(
	     new FileReader("E:\\srcnew\\ConfusionMatrixForDeletion.txt")));

    } catch (FileNotFoundException e) {
	 e.printStackTrace();
    }

    while (sc.hasNext()) {

	 for (int i = 0; i < 27; i++) {

	   String[] line = sc.nextLine().trim().split(" ");

	   for (int j = 0; j < 26; j++) {
		try {

		  delete[i][j] = Integer.parseInt(line[j]);
		} catch (NumberFormatException nfex) {
		}

	   }
	 }
    }

    long freq = Main.hm.get(str);
    double priorprobability = (double)freq + (double) 0.5;

    double cp = (((double)(delete[Character.getNumericValue(m) - 10][Character.getNumericValue(n) - 10]))
        / ((double)(Main.matrix[Character.getNumericValue(m) - 10][Character.getNumericValue(n) - 10])));

    double raw = (priorprobability * cp);

    return raw;
  }

  public static double insertscoring(String str, char m, char n) {
    Scanner sc = null;
    try {
	 sc = new Scanner(new BufferedReader(
	     new FileReader("E:\\srcnew\\ConfusionMatrixForInsertion.txt")));

    } catch (FileNotFoundException e) {
	 e.printStackTrace();
    }
    while (sc.hasNext()) {

	 for (int i = 0; i < 27; i++) {

	   String[] line = sc.nextLine().trim().split(" ");
	   for (int j = 0; j < 26; j++) {

		try {
		  insert[i][j] = Integer.parseInt(line[j]);
		} catch (NumberFormatException nfex) {
		}

	   }

	 }

    }
    long freq = Main.hm.get(str);
    double priorprobability = (double)freq + (double) 0.5;

    int mValue = String.valueOf(m).equals("@") ? 26 : Character.getNumericValue(m) - 10;
    double cp= (((double)(insert[mValue][Character.getNumericValue(n) - 10]))
        / ((double)(Main.map.get(String.valueOf(n)))));

    double raw = (priorprobability * cp);
    return raw;
  }

}
