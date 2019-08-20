public class Solution {

  
public String solution;

  
public Double score;

  
public String getSolution() {

    
return solution;

  
}

  
public void setSolution(String solution) {

    
this.solution = solution;

  
}

  
public Double getScore() {

    
return score;

  
}

  
public void setScore(Double score) {

    
this.score = score;

  
}

  
@Override
  public String toString() {
    
return "Solution \n[solution=" + solution + ", score=" + score + "]\n";
  
}      

}

