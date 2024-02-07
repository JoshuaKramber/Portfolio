public class TestAdds {
    public static void run() {
		System.out.println("\n\n_________________________________________");
		System.out.println("Testing List add methods ...");

		List<Integer> testList;

        // ====================  TEST add(Integer)
		System.out.println("--------------add(Integer)");
		testList = new List<Integer>(3);

		Assert.assertEquals(0,testList.length(),"pre-add (length)");
		testList.add(0);
		Assert.assertEquals(1,testList.length(),"add first (length)");
		Assert.assertEquals(0,testList.peek(0),"add first (value)");
		testList.add(1);
		Assert.assertEquals(2,testList.length(),"add 2nd (length)");
		Assert.assertEquals(1,testList.peek(1),"add 2nd (value)");
		testList.add(2);
		Assert.assertEquals(3,testList.length(),"add to capacity (length)");
		Assert.assertEquals(2,testList.peek(2),"add to capacity (value)");
		testList.add(3);
		Assert.assertEquals(13,testList.capacity(),"add increased capacity");
		Assert.assertEquals(4,testList.length(),"add attempt over capacity (length)");
		Assert.assertEquals(3,testList.peek(3),"add attempt over capacity (value)");


        // ====================  TEST add(Integer, index)
		System.out.println("--------------add(Integer, index)");
		testList = new List<Integer>(6);
		testList.add(0,0);
		Assert.assertEquals(1,testList.length(),"add to empty (length)");
		Assert.assertEquals(0,testList.peek(0),"add to empty (value)");

		testList.add(1,0);	// order is now 1,0
		Assert.assertEquals(2,testList.length(),"add at 0 (length)");
		Assert.assertEquals(1,testList.peek(0),"add at 0 (value)");
		Assert.assertEquals(0,testList.peek(1),"add at 0 (2nd element)");

		testList.add(2,2);	// order is now 1, 0, 2
		Assert.assertEquals(3,testList.length(),"add at end (length)");
		Assert.assertEquals(2,testList.peek(2),"add at end (value)");
		Assert.assertEquals(1,testList.peek(0),"add at end (1st)");
		Assert.assertEquals(0,testList.peek(1),"add at end (2nd)");

		testList.add(3,1);	// order should now be 1, 3, 0, 2
		Assert.assertEquals(4,testList.length(),"add in middle (length)");
		Assert.assertEquals(3,testList.peek(1),"add in middle (value)");
		Assert.assertEquals(1,testList.peek(0),"add in middle (1st)");
		Assert.assertEquals(0,testList.peek(2),"add in middle (3rd)");
		Assert.assertEquals(2,testList.peek(3),"add in middle (4th)");

		// test invalid indices
		testList.add(4,-1);
		Assert.assertEquals(4,testList.length(),"add at -1 (length)");
		testList.add(4,5);
		Assert.assertEquals(4,testList.length(),"add beyond last (length)");


        // ====================  TEST addAll(Integer[])
		System.out.println("--------------addAll(Integer[])");

		testList = new List<Integer>(6);
        Integer[] testArray = {0,1,2};
		boolean results = testList.addAll(testArray);
        Assert.assertEquals(true,results,"addAll 3 (below capacity) (bool)");
		Assert.assertEquals(3,testList.length(),"addAll 3 (length)");
		Assert.assertEquals(0,testList.peek(0),"addAll 3 (1st value)");
		Assert.assertEquals(1,testList.peek(1),"addAll 3 (2nd value)");
		Assert.assertEquals(2,testList.peek(2),"addAll 3 (3rd value)");

        testList = new List<Integer>(3);
		results = testList.addAll(testArray);
        Assert.assertEquals(true,results,"addAll 3 (at capacity) (bool)");
		Assert.assertEquals(3,testList.length(),"addAll at cap (length)");
		Assert.assertEquals(0,testList.peek(0),"addAll at cap (1st value)");
		Assert.assertEquals(1,testList.peek(1),"addAll at cap (2nd value)");
		Assert.assertEquals(2,testList.peek(2),"addAll at cap (3rd value)");

        testList = new List<Integer>(2);
		results = testList.addAll(testArray);
        Assert.assertEquals(false,results,"addAll 3 (over capacity - fail) (bool)");
		Assert.assertEquals(0,testList.length(),"addAll 3 fail (length)");
		Assert.assertEquals(true,testList.isEmpty(),"addAll 3 faile (empty)");

        //========================= Testing set(Integer, index)
		System.out.println("--------------set(Integer, index)");
		testList = new List<Integer>(6);

		// †his assumes that add is working correctly!
		testList.add(0);
		testList.add(1);
		testList.add(2);

		testList.set(3,0); // order should now be 3, 1, 2
		Assert.assertEquals(3,testList.length(),"set(0) length");
		Assert.assertEquals(3,testList.peek(0),"set(0) value");
		Assert.assertEquals(1,testList.peek(1),"set(0) (2nd)");
		Assert.assertEquals(2,testList.peek(2),"set(0) (3rd)");

		testList.set(4,1);	// order should now be 3, 4, 2
		Assert.assertEquals(3,testList.length(),"set(1) length");
		Assert.assertEquals(4,testList.peek(1),"set(1) value");
		Assert.assertEquals(3,testList.peek(0),"set(1) (1st )");
		Assert.assertEquals(2,testList.peek(2),"set(1) (3rd )");

		testList.set(1,2);	// order shoule now be 3, 4, 1
		Assert.assertEquals(3,testList.length(),"set(2) length");
		Assert.assertEquals(1,testList.peek(2),"set(2) value");
		Assert.assertEquals(3,testList.peek(0),"set(2) (1st )");
		Assert.assertEquals(4,testList.peek(1),"set(2) (2nd )");

		testList.set(2,3);	// set 1 past the last element
		//  invalid indices. should not change {3,4,1}
		Assert.assertEquals(3,testList.length(),"set at length, bad index (length)");
		Assert.assertEquals(null,testList.peek(3),"set at length, bad index (value)");
		testList.set(2,5);
		Assert.assertEquals(3,testList.length(),"set at capacity, bad index (length)");
		Assert.assertEquals(null,testList.peek(5),"set at capacity, bad index (value)");
		testList.set(2,-1);
		Assert.assertEquals(3,testList.length(),"set at -1 (length)");
		Assert.assertEquals(3,testList.peek(0),"set at -1 (value)");
	}

}
