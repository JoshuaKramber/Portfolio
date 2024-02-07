import java.util.ArrayList;
import java.util.Arrays;

public class MakingChange {

	/** make the needed tables and such **/
    public int[] Alist;
    public int c;
    public Integer[][] memo;
    public int count;

	public MakingChange(int[] coins, int change) {
        //initalizing vars
        count = 0;
        Alist = coins;
        c = change;
        Arrays.sort(Alist);
    }	

	public int solveRecDP() {
        //initializing vars
        int n = Alist.length;
        memo = new Integer[n][c+1];
        //calling recursive function
        return solver(n-1, c);
    }

     /**
     * fills in the memoization table
     * @param i indexing variable for the first part of the memo table
     * @param j indexing variable for the second part of the memo table
     * @return
     */
    public int solver(int i, int j) {
        count++;
        //null case
        if (memo[i][j]!=null) {
            return memo[i][j];
        }
		else if (i==0 || j==0) {
            //ends recursion
            //memo[i][j]=0;
            return 0;
        }
		else {
            if (Alist[i] > j) {
                //no take
                memo[i][j] = solver(i-1, j);
            }
			else {
                //take = 1+solver(i-1, j-alist[i])
                //chooses the max of the no take/take options
                memo[i][j] = Math.max(1+solver(i-1, j-Alist[i]), solver(i-1, j));
            }
        }
        return memo[i][j];
    }

    public int solveIterDP() {
        //create an iterator and a memo table
        int n = Alist.length;
        memo = new Integer[n][c];
        //iterate through a 2d array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= c; j++) {
                count++;
                if (Alist[i] > j) {
                //no take
                memo[i][j] = memo[i-1][j];
                } 
                else {
                //take
                memo[i][j] = Math.max(1+memo[i][j-Alist[i]], memo[i-1][j]);
              }
            }
          }
        return memo[n-1][c-1];
    }

	public ArrayList<Integer> solveGreedy() {
        //create the arraylist of choices
        ArrayList<Integer> choices = new ArrayList<>();
        //creates a var to keep track of our current sum
        count = (int)(Alist.length*Math.log(Alist.length)+Alist.length);
        int current_sum = 0;
        int i = Alist.length-1;
        //goes backwards through the array checking if we have hit our target sum yet
        while((current_sum!=c) && (i!=0)){
            //checks to see if our current item will go over the desired amount
            if(Alist[i]+current_sum<=c){
                //adds the item to our sum and choices arraylist
                current_sum+=Alist[i];
                choices.add(Alist[i]);
            }
            i--;
        }
        return choices;
    }

	public ArrayList<Integer> getChoices() {
        //creates our arraylist of choices and sets variables
        ArrayList<Integer> choices = new ArrayList<>();
        int i = Alist.length - 1;
        int j = c;
        //iterates backwards through the memo table to find our choices
        while (i > 0 && j > 0) {
            if (memo[i][j] != memo[i-1][j]) {
                choices.add(Alist[i]);
                j = j-Alist[i];
            }
            i--;
        }
        return choices; 
    }
	
    public int getCount(){
        return count;
    }

    public void printMemo() {
        for (int i = 0; i < Alist.length; i++) {
            for (int j = 0; j <= c; j++) {
                System.out.print(memo[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
