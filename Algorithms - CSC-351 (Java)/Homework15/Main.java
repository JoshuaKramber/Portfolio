import java.util.*;

public class Main {
    public static void main(String[] args) {
        //creates the array
        int[] alist = new int[]{0,2,5,3,8,2,2,1};
        
        //calling the function
        Summer.solve(alist,10);

        //making a pretty output
        System.out.println("Starting array: " + Arrays.toString(alist) + ", Target Sum: " + Summer.get_target_sum());
        System.out.println("Choices: " + Summer.get_choices() + ", Cost: " + Summer.get_cost());
    }
}
