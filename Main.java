import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 
* Added comments for your reference

* Made some code changes. Delete the comments after you understand what I have changed

*/

public class Main {

  public static final Map<String, Long> hm = new HashMap<String, Long>();

  public static final Map<String, Long> map = new HashMap<String, Long>();

  public static final long[][] matrix = new long[26][26];

  public static void main(String[] args) throws Exception {

    // Declaring Scanner outside so that I can close it in finally block

    Scanner reader = null;

    File filename = new File("E:\\srcnew\\corpus.txt");

    // You have declared the matrix incorrectly. This is the right way

    try {

	 reader = new Scanner(filename);

	 System.out.println("Loading the maps");
	 while (reader.hasNext()) {

	   // You have to use reader.nextLine(), reader.next() just gives you next word

	   String line = reader.nextLine();

	   String[] array = line.split("	");

	   hm.put(array[0], new Long(array[1]));

	   // Use Long instead of Integer

	   Long count = Long.valueOf(array[1]);

	   for (char i = 'a'; i <= 'z'; i++) {

		// You used CONTINUE in IF clause, then there is no need for ELSE, as CONTINUE

		// is used. I have re factored it to just if with the negative condition

		if ((array[0].indexOf(i)) != -1) {

		  if (map.containsKey(String.valueOf(i))) {

		    map.put(String.valueOf(i), addLong(map.get(String.valueOf(i)), count));

		  } else {

		    map.put(String.valueOf(i), addLong(Long.valueOf(0), count));

		  }

		}

	   }

	   for (char i = 'a'; i <= 'z'; i++) {

		for (char j = 'a'; j <= 'z'; j++) {

		  // You used CONTINUE in IF clause, then there is no need for ELSE, as CONTINUE

		  // is used. I have re factored it to just if with the negative condition

		  if ((array[0].indexOf(String.valueOf(i) + String.valueOf(j))) != -1) {

		    matrix[Character.getNumericValue(i) - 10][Character.getNumericValue(j) - 10] += count.longValue();

		  }

		}

	   }

	 }

	 System.out.println("Map is loaded");
	 System.out.println("Matrix is Loaded");
    } catch (FileNotFoundException e) {

	 System.out.println("File doesn't exist");

    } finally {

	 if (reader != null) {

	   reader.close();

	 }

    }

    System.out.println("Enter the input typo:");
    reader = new Scanner(System.in);

    String inputTypo = reader.nextLine();

    reader.close();
    
    if(hm.containsKey(inputTypo)){
     
     System.out.println("The inputTypo is already present in corpus. So no Operations takes place.");
    
     }
	
    else{

    // do other check here-------------------------------

    List<Solution> candidateSolutions = new ArrayList<>();

    candidateSolutions.addAll(Operations.applyReversal(inputTypo));

    candidateSolutions.addAll(Operations.applySubstitution(inputTypo));

    candidateSolutions.addAll(Operations.applyDeletion(inputTypo));

    candidateSolutions.addAll(Operations.applyInsertion(inputTypo));
    
    // candidateSolutions will have your corrected solutions list and scores. Print
   
    // them
   
    candidateSolutions.sort(Comparator.comparingDouble(Solution::getScore).reversed());
    
    System.out.println(candidateSolutions.toString());
    
    }
  
  }
  
  private static Long addLong(Long a, Long b) {

    return Long.valueOf(a.longValue() + b.longValue());

  }
}

