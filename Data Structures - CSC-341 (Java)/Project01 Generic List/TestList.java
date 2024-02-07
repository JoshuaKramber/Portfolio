import java.lang.reflect.*;
import java.util.Scanner;
import java.io.*;

public class TestList {

	public static void main(String[] args) {

		// Initial add() and peek() test
		/* It is a bit of a chicken-and-egg problem.
		* To test add(), peek() needs to be correct to confirm face was added.
		* To test peek(), add() needs to be correct to confirm location of face.
		*/


		System.out.println("\n\nSanity check test of add, length, peek.");
		System.out.println("If these initial tests do not pass, the rest of the tests are irrelevant!\n");
		List<Integer> testList = new List<>(5);
		testList.add(1);
		Assert.assertEquals(1,testList.length(),"First Add (length)");
		Assert.assertEquals(1,testList.peek(0),"First Add (1)");
		System.out.println("\nSanity Check complete!");
		/* Once the above are confirmed to be working, the rest of the testing can proceed.
		*/
		System.out.println("***************************");
		System.out.println("for some reason the call doesnt work at the end of the try/catch sequence");
		System.out.println("testing order");
		TestOrder.run();
		System.out.println("***************************");


		try {
			TestConstructors.run();
		} catch(Exception e) {
			System.out.println("\n\n*** STOP HERE -- something wrong with constructor!!\n\n");
		}
		try {
			TestSearches.run();
		} catch(Exception e) {
			System.out.println("\n\nSEARCHES THROWING EXCEPTION.");
			e.printStackTrace();
			System.out.println("\n\n");
		}
		try {
			TestAdds.run();
		} catch(Exception e) {
			System.out.println("\n\nADDS THROWING EXCEPTION.");
			e.printStackTrace();
			System.out.println("\n\n");
		}
		try {
			TestRemoves.run();
		} catch(Exception e) {
			System.out.println("\n\nREMOVES THROWING EXCEPTION.");
			e.printStackTrace();
			System.out.println("\n\n");
		}
		try {
			TestConverts.run();
		} catch(Exception e) {
			System.out.println("\n\nCONVERT THROWING EXCEPTION.");
			e.printStackTrace();
			System.out.println("\n\n");
		}
		try {
			TestOrder.run();
		} catch(Exception e) {
			System.out.println("\n\nORDER THROWING EXCEPTION.");
			e.printStackTrace();
			System.out.println("\n\n");
		}
	
	
	} // end main
}
