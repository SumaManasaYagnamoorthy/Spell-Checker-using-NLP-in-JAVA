import java.util.ArrayList;
import java.util.List;

public class Operations {

  public static List<Solution> applyReversal(String str) {

    List<Solution> reversalCandidateSoultions = new ArrayList<>();

    char[] rArray = str.toCharArray();

    for (int i = 0; i < rArray.length - 1; i++) {

	 String reversedString = swap(str, i, i + 1);

	 if (Main.hm.containsKey(reversedString)) {

	   Solution solution = new Solution();

	   solution.setSolution(reversedString);

	   solution.setScore(Scoring.reversescoring(reversedString, rArray[i], rArray[i + 1]));

	 }

    }

    return reversalCandidateSoultions;

  }

  public static List<Solution> applySubstitution(String str) {

    List<Solution> substitutionCandidateSolutions = new ArrayList<>();

    char[] sArray = str.toCharArray();

    for (int i = 0; i < sArray.length; i++) {

	 for (char j = 'a'; j <= 'z'; j++) {

	   String substitutedString = substitute(str, i, j);

	   if (Main.hm.containsKey(substitutedString)) {

		Solution solution = new Solution();

		solution.setSolution(substitutedString);

		solution.setScore(Scoring.substitutescoring(substitutedString, sArray[i], j));

		substitutionCandidateSolutions.add(solution);

	   }

	 }

    }

    return substitutionCandidateSolutions;

  }

  public static List<Solution> applyDeletion(String str) {

    List<Solution> deletionCandidateSolutions = new ArrayList<>();

    for (int i = 0; i < str.length(); i++) {

	 for (char j = 'a'; j <= 'z'; j++) {

	   String deletedString = str.substring(0, i) + String.valueOf(j) + str.substring(i);

	   if (Main.hm.containsKey(deletedString)) {

		Solution solution = new Solution();

		solution.setSolution(deletedString);

		solution.setScore(Scoring.deletionscoring(deletedString, str.charAt(i), j));

		deletionCandidateSolutions.add(solution);

	   }

	 }

    }

    return deletionCandidateSolutions;

  }

  public static List<Solution> applyInsertion(String str) {

    List<Solution> insertionCandidateSolutions = new ArrayList<>();
    String temp = "@" + str;

    for (int i = 0; i < str.length(); i++) {

	 String insertedString = str.substring(0, i) + str.substring(i + 1);

	 if (Main.hm.containsKey(insertedString)) {

	   Solution solution = new Solution();

	   solution.setSolution(insertedString);

	   solution.setScore(Scoring.insertscoring(insertedString, temp.charAt(i), temp.charAt(i + 1)));

	   insertionCandidateSolutions.add(solution);

	 }

    }

    return insertionCandidateSolutions;

  }

  private static String swap(String str, int i, int j) {

    char ch[] = str.toCharArray();

    char temp = ch[i];

    ch[i] = ch[j];

    ch[j] = temp;

    return String.valueOf(ch);

  }

  private static String substitute(String str, int i, char c) {

    char ch[] = str.toCharArray();

    ch[i] = c;

    return String.valueOf(ch);

  }

}