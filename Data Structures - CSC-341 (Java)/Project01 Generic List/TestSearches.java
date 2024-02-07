public class TestSearches {
    public static void run() {
		System.out.println("\n\n_________________________________________");
		System.out.println("Testing List search methods ...");

		List<Integer> testList;

		testList = new List<Integer>(5);
		testList.add(0);
		testList.add(1);
		testList.add(2);

		System.out.println("------------testing peek()");
		Assert.assertEquals(0,testList.peek(0),"Peek at index 0");
		Assert.assertEquals(1,testList.peek(1),"Peek at index 1");
		Assert.assertEquals(2,testList.peek(2),"Peek at index 2");
		Assert.assertEquals(null,testList.peek(3), "Peek invalid >count");
		Assert.assertEquals(null,testList.peek(5), "Peek invalid >length");
		Assert.assertEquals(null,testList.peek(-1), "Peek invalid -1");

		System.out.println("------------Testing recordNo()");
		Assert.assertEquals(0, testList.recordNo(0), "recordNo first");
		Assert.assertEquals(1, testList.recordNo(1), "recordNo middle");
		Assert.assertEquals(2, testList.recordNo(2), "recordNo last");
		Assert.assertEquals(-1, testList.recordNo(10), "recordNo but not in list");

	} // end testSearches
}
