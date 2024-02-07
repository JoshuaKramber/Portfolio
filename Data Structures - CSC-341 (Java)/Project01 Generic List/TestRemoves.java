public class TestRemoves {
	public static void run() {
		System.out.println("\n\n_________________________________________");
		System.out.println("Testing List remove methods ...");

        List<Integer> testList;

        // ====================  TEST remove(int) (at position)
		System.out.println("--------------remove(int)");
		testList = new List<Integer>(5);
        Integer value;

        testList.add(0);
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);

        value = testList.remove(0); // should now be 1,2,3,4
        Assert.assertEquals(0,value,"remove first (returned)");
        Assert.assertEquals(4,testList.length(),"remove first (length)");
		Assert.assertEquals(1,testList.peek(0),"remove first (item 0)");
		Assert.assertEquals(2,testList.peek(1),"remove first (item 1)");

        value = testList.remove(3); // should now be 1,2,3
        Assert.assertEquals(4,value,"remove last (returned)");
        Assert.assertEquals(3,testList.length(),"remove last (length)");
		Assert.assertEquals(1,testList.peek(0),"remove last (item 0)");
		Assert.assertEquals(3,testList.peek(2),"remove last (item 2)");

        value = testList.remove(1); // should now be 1,3
        Assert.assertEquals(2,value,"remove middle (returned)");
        Assert.assertEquals(2,testList.length(),"remove middle (length)");
		Assert.assertEquals(1,testList.peek(0),"remove middle (item 0)");
		Assert.assertEquals(3,testList.peek(1),"remove middle (item 1)");
        

        testList.remove(0);
        testList.remove(0); // should now be empty
        Assert.assertEquals(0,testList.length(),"remove all (length)");

        testList.add(0);
        testList.add(1);
        value = testList.remove(2);
        Assert.assertEquals(null,value,"remove at count (value)");
        Assert.assertEquals(2,testList.length(),"remove at count.");
        value = testList.remove(4);
        Assert.assertEquals(null,value,"remove beyond count (value)");
        Assert.assertEquals(2,testList.length(),"remove beyond count.");
        value = testList.remove(10);
        Assert.assertEquals(null,value,"remove beyond capacity (value)");
        Assert.assertEquals(2,testList.length(),"remove beyond capacity.");
	 

        // ====================  TEST remove(Integer) (find value)
		System.out.println("--------------remove(Integer)");
		testList = new List<Integer>(5);

        boolean result;

        testList.add(0);
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);

        result = testList.remove((Integer)0); // should now be 1,2,3,4
        Assert.assertEquals(true,result,"remove first (t/f)");
        Assert.assertEquals(4,testList.length(),"remove first (length)");
		Assert.assertEquals(1,testList.peek(0),"remove first (item 0)");
		Assert.assertEquals(2,testList.peek(1),"remove first (item 1)");

        result = testList.remove((Integer)4); // should now be 1,2,3
        Assert.assertEquals(true,result,"remove last (t/f)");
        Assert.assertEquals(3,testList.length(),"remove last (length)");
		Assert.assertEquals(1,testList.peek(0),"remove last (item 0)");
		Assert.assertEquals(3,testList.peek(2),"remove last (item 2)");

        result = testList.remove((Integer)2); // should now be 1,3
        Assert.assertEquals(true,result,"remove middle (t/f)");
        Assert.assertEquals(2,testList.length(),"remove middle (length)");
		Assert.assertEquals(1,testList.peek(0),"remove middle (item 0)");
		Assert.assertEquals(3,testList.peek(1),"remove middle (item 1)");
        

        testList.remove((Integer)1);
        testList.remove((Integer)3); // should now be empty
        Assert.assertEquals(0,testList.length(),"remove all (length)");

        testList.add(0);
        testList.add(1);
        result = testList.remove((Integer)2);
        Assert.assertEquals(false,result,"remove not in List (t/f)");

        testList.add(2);
        testList.add(1);    // should now by {0 1 2 1}
        result = testList.remove((Integer)1);   // should now be {0 2 1}
        Assert.assertEquals(3,testList.length(),"remove duplicate.");
        Assert.assertEquals(2,testList.peek(1),"remove duplicate (value at 1)");
        Assert.assertEquals(1,testList.peek(2),"remove duplicate (value at 2)");


        // ====================  TEST clear() 
		System.out.println("--------------clear()");
		testList = new List<Integer>(5);

        testList.add(0);
        testList.add(1);
        testList.add(2);
        int capacity = testList.capacity();

        testList.clear(); // should now be empty
        Assert.assertEquals(0,testList.length(),"clear 3");
        Assert.assertEquals(capacity,testList.capacity(),"clear 3 capacity");

        testList.clear();
        Assert.assertEquals(0,testList.length(),"clear 0");
    }
}
