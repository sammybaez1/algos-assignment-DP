/**
 * Glass Falling
 * Author: Sammy Baez
 */
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
	  //Worst case, you have to check every floor
      if (sheets == 1 ){ 
          return floors;
      }
      
      //Can't do trials without any floors or sheets
      if (sheets == 0 || floors == 0 ){
    	  return 0;
      }
      
      int minimumTrials = Integer.MAX_VALUE;  
      int subProblems;  
    
      for (int attemptFloor = 1; attemptFloor <= floors; attemptFloor++)  
      {  
          subProblems = 1 + Math.max(glassFallingRecur(attemptFloor-1 , sheets-1),glassFallingRecur(floors-attemptFloor, sheets));  
          minimumTrials = Math.min(subProblems, minimumTrials);
      }  
    
      return minimumTrials;  
	  
  }
  
  
  public int glassFallingMemoized(int floors, int sheets) {
	    int dpTable[][] = new int[floors + 1][sheets + 1];
	    for (int floor = 2;  floor <= floors; floor++) {
	        for (int sheet = 2; sheet <= sheets; sheet++) {
	          dpTable[floor][sheet] = Integer.MAX_VALUE;
	        }
	      }
	    return glassFallingMemoized(floors, sheets, dpTable);
	  }
  
  
  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int floors, int sheets, int dpTable[][]) {
	  //Worst case, you have to check every floor
      if (sheets == 1 ){ 
          return floors;
      }
      
      //Can't do trials without any floors or sheets
      if (sheets == 0 || floors == 0 ){
    	  return 0;
      }
      
      //Check if a subproblem already has a solution
      if (dpTable[floors][sheets] != Integer.MAX_VALUE) {
    	  return dpTable[floors][sheets];
      }
      
      
      int subProblems;  
    
      for (int attemptFloor = 1; attemptFloor <= floors; attemptFloor++)  
      {  
          subProblems = 1 + Math.max(glassFallingMemoized(attemptFloor-1 , sheets-1,dpTable),glassFallingMemoized(floors-attemptFloor, sheets,dpTable));  
          dpTable[floors][sheets] = Math.min(subProblems, dpTable[floors][sheets]);
      }  
    
      return dpTable[floors][sheets] + 1;  
  }

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {

    int dpTable[][] = new int[sheets + 1][floors + 1];

    //Base case, if you have 0 floors then no trials are needed, initialize to 0
    // if you have 1 floor then only 1 trial is needed, the number of sheets doesn't matter
    
    for (int sheet = 1; sheet <= sheets; sheet++) {
      dpTable[sheet][0] = 0;
      dpTable[sheet][1] = 1;
    }

    /*With only 1 sheet we have the worst case where we have to check every floor, 
     so the number of trials is the same as the number of floors 
    */
    for (int floor = 1; floor <= floors; floor++) {
      dpTable[1][floor] = floor;
    }

    for (int sheet = 2; sheet <= sheets; sheet++) {
      for (int floor = 2; floor <= floors; floor++) {
    	  
        dpTable[sheet][floor] = Integer.MAX_VALUE;

        for (int attemptFloor = 1; attemptFloor <= floor; attemptFloor++) { //Check every floor from 1st to current floor 

          int subProblems = 1 + Math.max(dpTable[sheet - 1][attemptFloor - 1], dpTable[sheet][floor - attemptFloor]);

          dpTable[sheet][floor] = Math.min(dpTable[sheet][floor], subProblems);

        }

      }
    }
    return dpTable[sheets][floors];
  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();
      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Memo = gf.glassFallingMemoized(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }
}

