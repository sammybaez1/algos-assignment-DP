/**
 * Rod cutting problem described in Chapter 15 of textbook
 * * Author: Sammy Baez
 */
public class RodCutting {

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
	  if (rodLength <= 0){ 
		  return 0; 
	  }
	  
	  int result = Integer.MIN_VALUE; 
		
		   for (int currentRodLength = 0; currentRodLength < rodLength; currentRodLength++){
		         result = Math.max(result,lengthPrices[currentRodLength] + rodCuttingRecur(rodLength-currentRodLength-1,lengthPrices)); 
		   }
		   return result; 
  }
  
  
  public int rodCuttingRecurMemo(int rodLength, int[] lengthPrices) {
	  int solTable[] = new int[lengthPrices.length+1];
	  
	  for(int i = 0; i < solTable.length; i++){
		  solTable[i] = Integer.MIN_VALUE;
	  }
	  
	  return rodCuttingRecurMemo(rodLength,lengthPrices,solTable);
  
  }
  
  public int rodCuttingRecurMemo(int rodLength, int[] lengthPrices, int[] solTable) {
	  if(solTable[rodLength] >= 0){
		  return solTable[rodLength];
	  }
	  
	  int result;
	  
	  if (rodLength <= 0){ 
		  result = 0; 
	  }
	  
	  else{
		  result = Integer.MIN_VALUE;
		  for (int currentRodLength = 0; currentRodLength < rodLength; currentRodLength++){
		         result = Math.max(result,lengthPrices[currentRodLength] + rodCuttingRecurMemo(rodLength-currentRodLength-1,lengthPrices,solTable)); 
		   }
	  }
	  
	  solTable[rodLength] = result;
	  return result;
	  	  
	  
  }
  
  

  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
	  	int solTable[] = new int[rodLength+1];
	    solTable[0] = 0;
	    
	    for (int i = 1; i <= rodLength; i++){
	      int result = Integer.MIN_VALUE;
	      
	      for (int j = 1; j < i; j++){
	        result = Math.max(result, lengthPrices[j] + solTable[i-j-1]);
	      }
	      solTable[i] = result;
	    }

	    return solTable[rodLength];
	  
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
