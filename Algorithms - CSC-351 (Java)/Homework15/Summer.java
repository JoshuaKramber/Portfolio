import java.util.ArrayList;

public class Summer {

    private static int[] alist;
    private static int target_sum;
    private static Integer[][] memo;

    /**
     * sets variables and then calls the recursive functions
     * @param X input array
     * @param s target sum
     */
    public static void solve(int[] X, int s) {
        alist = X;
        int n = alist.length;
        target_sum = s;
        memo = new Integer[n][s+1];
        //calls recursive function
        solver(n-1, s);
    }

    /**
     * fills in the memoization table
     * @param i indexing variable for the first part of the memo table
     * @param j indexing variable for the second part of the memo table
     * @return
     */
    public static int solver(int i, int j) {
        //null case
        if (memo[i][j]!=null) {
            return memo[i][j];
        }
		else if (i==0 || j==0) {
            //ends recursion
            memo[i][j]=0;
        }
		else {
            if (alist[i] > j) {
                //no take
                memo[i][j] = solver(i-1, j);
            }
			else {
                //take = 1+solver(i-1, j-alist[i])
                //chooses the max of the no take/take options
                memo[i][j] = Math.max(1+solver(i-1, j-alist[i]), solver(i-1, j));
            }
        }
        return memo[i][j];
    }

    /**
     * gets the cost or the number of numbers needed to get the target sum
     * @return cost
     */
    public static int get_cost() {
        return memo[alist.length - 1][target_sum];
    }

    /**
     * creates a new arraylist that contains the choices used to get the target sum
     * @return arraylist of choices
     */
    public static ArrayList<Integer> get_choices() {
        //creates our arraylist of choices and sets variables
        ArrayList<Integer> choices = new ArrayList<>();
        int i = alist.length - 1;
        int j = target_sum;
        //iterates backwards through the memo table to find our choices
        while (i > 0 && j > 0) {
            if (memo[i][j] != memo[i-1][j]) {
                choices.add(alist[i]);
                j = j-alist[i];
            }
            i--;
        }
        //adds the choice to the choice arraylist
        if (i == 0 && memo[i][j] > 0) {
            choices.add(alist[i]);
        }
        return choices;
    }

    /**
     * Simple getter for the target sum
     * @return target_sum
     */
	public static int get_target_sum(){
		return target_sum;
	}
}
