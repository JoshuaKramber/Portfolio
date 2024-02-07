public class TestConverts {
    public static void run() {
		System.out.println("\n\n_________________________________________");
		System.out.println("Testing List convert methods ...");

		List<Integer> testList;
		Comparable[] array;

		System.out.println("--------------Testing toArray()");
		testList = new List<Integer>(4);
        array = testList.toArray();
        Assert.assertEquals(null,array,"list empty, toArray null");

		testList.add(0);
		array = testList.toArray();
		if (null==array) {
			System.out.println("ERROR: toArray 1 element. Returns null.");
		} else {
			Assert.assertEquals(1,array.length,"toArray 1 element (length)");
			Assert.assertEquals(array[0], 0, "toArray 1 element (value)");
		}

		testList.add(1);
		array = testList.toArray();
		if (null==array) {
			System.out.println("ERROR: toArray 2 elements. Returns null.");
		} else {
			Assert.assertEquals(2,array.length,"toArray 2 elements (length)");
			Assert.assertEquals(array[0], 0, "toArray 2 elements (face 0)");
			Assert.assertEquals(array[1], 1, "toArray 2 elements (face 1)");
		}

        // ====================== Testing int toArray(T[])
        System.out.println("--------------Testing int toArray(T[])");
		testList = new List<Integer>(4);
        Integer[] toFill = new Integer[2];
        int count;

        count = testList.toArray(toFill);
        Assert.assertEquals(0,count,"list empty");

        testList.add(0);
        count = testList.toArray(toFill);
        Assert.assertEquals(1,count,"list 1 element, tofill 2");

        testList.add(1);
        count = testList.toArray(toFill);
        Assert.assertEquals(2,count,"list 2 elements, toFill 2");

        testList.add(2);
        count = testList.toArray(toFill);
        Assert.assertEquals(2,count,"list 3 elements, toFill 2");
	} // end testConvert
}
