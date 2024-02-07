public class TestConstructors {
    public static void run() {
		System.out.println("\n\n_________________________________________");
		System.out.println("Testing List constructor ...");

		List<Integer> testList;

		//===============================================================
		// Test default constructor and getters
        // The DEFAULT_CAPACITY is 500
        System.out.println("--------------Testing default constructor");

		testList = new List<Integer>();

		Assert.assertEquals(500, testList.capacity(), "List() capacity");
		Assert.assertEquals(0, testList.length(), "List() length");
		Assert.assertEquals(true, testList.isEmpty(), "List() isEmpty");

		//===============================================================
        // Test constructor with size parameter
        System.out.println("--------------Testing List(int)");
		testList = new List<Integer>(5);

		Assert.assertEquals(0, testList.length(), "List(5) length");
		Assert.assertEquals(5, testList.capacity(), "List(5) capacity");
		Assert.assertEquals(true, testList.isEmpty(), "List(5) isEmpty");

        System.out.println("--------------Testing length getter");
		// Test length getter (tested at 0 above)
		testList.add(1);
		Assert.assertEquals(1,testList.length(),"length()");
		testList.add(2);
		Assert.assertEquals(2,testList.length(),"length()");

		// Test isEmpty and isFull.
        System.out.println("--------------Testing isFull isEmpty");
		testList = new List<Integer>(3);
		Assert.assertEquals(false, testList.isFull(), "isFull, but empty");
		Assert.assertEquals(true, testList.isEmpty(), "isEmpty");

		testList.add(1);
		Assert.assertEquals(false, testList.isFull(), "isFull - 1 of 3");
		Assert.assertEquals(false, testList.isEmpty(), "isEmpty - 1 of 3");

		testList.add(1);
		testList.add(1);
		Assert.assertEquals(true, testList.isFull(), "isFull - 3 of 3");
		Assert.assertEquals(false, testList.isEmpty(), "isEmpty - 3 of 3");

		// test length getter when full
		Assert.assertEquals(3,testList.length(),"length()");

		//===============================================================
        // Test copy constructor
        System.out.println("--------------Testing List(List)");
		testList = new List<Integer>(5);
        testList.add(0);
        testList.add(1);
        testList.add(2);

        List<Integer> copied = new List<>(testList);

        Assert.assertEquals(3, copied.length(), "copied length");
		Assert.assertEquals(5, copied.capacity(), "copied capacity");
        Assert.assertEquals(0,copied.peek(0), "copied item 0");
        Assert.assertEquals(1,copied.peek(1), "copied item 1");
        Assert.assertEquals(2,copied.peek(2), "copied item 2");


        //===============================================================
        // Test copy constructor
        System.out.println("--------------Testing increaseCapacity");
		testList = new List<Integer>(3);

        testList.add(0);
        testList.add(1);
        testList.add(2);
        //testList.add(3);
        //Assert.assertEquals(3,testList.length(),"filled to capacity at 3");

        testList.increaseCapacity(2);
        Assert.assertEquals(5,testList.capacity(),"increased capacity");
        Assert.assertEquals(0,testList.peek(0),"after increase (item 0)");
        Assert.assertEquals(2,testList.peek(2),"after increase (item 2)");
        
        testList.add(3);
        Assert.assertEquals(4,testList.length(),"add after increase (length)");
        Assert.assertEquals(3,testList.peek(3),"add after increase (value)");

	} // end testConstructorGetters
}
