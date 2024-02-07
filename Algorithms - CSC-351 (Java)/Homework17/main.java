import java.util.ArrayList;
import java.lang.Math;

public class main {
	public static void main(String[] args) {
	
	int[] coins = new int[2000];
	for(int i = 0;i<coins.length;i++){
		coins[i]=(int)(Math.random()*30)+1;
	}
	int change = (int)(Math.random()*100)+60;
	
	long startNS;
    long endNS;

	startNS=System.nanoTime();
	MakingChange ChangeMaker = new MakingChange(coins, change);
	ChangeMaker.solveGreedy();
	endNS=System.nanoTime();
	long timeNS = (endNS - startNS) / 1000;
	int sum=0;
	for(int i = 0;i<ChangeMaker.solveGreedy().size();i++){
		sum+=ChangeMaker.solveGreedy().get(i);
	}
	System.out.println("Greedy: Choices made: " + ChangeMaker.solveGreedy() + " Change Required: " + change + " Change Obtained: " + sum + " Time: " + timeNS + " Operations: " + ChangeMaker.getCount());

	
	
	long startNS2;
    long endNS2;

	startNS2=System.nanoTime();
	MakingChange ChangeMaker2 = new MakingChange(coins, change);
	ChangeMaker2.solveRecDP();
	endNS2=System.nanoTime();
	long timeNS2 = (endNS - startNS) / 1000;
	int sum2=0;
	for(int i = 0;i<ChangeMaker2.getChoices().size();i++){
		sum2+=ChangeMaker2.getChoices().get(i);
	}

	System.out.println("RecDP: Choices made: " + ChangeMaker2.getChoices() + " Change Required: " + change + " Change Obtained: " + sum2 + " Time: " + timeNS2 + " Operations: " + ChangeMaker.getCount());
	}
	
	//run in the terminal javac *.java
    //then run java Main > merge_reversed.csv
}
