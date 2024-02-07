public class TestOrder {
    public static void run() {
        System.out.println("\n\n_________________________________________");
        System.out.println("Testing List order methods ...");

        // ====================== Testing min
        System.out.println("--------------Testing min");
        List<Integer> l1 = new List<Integer>();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        //List order should be 1,2,3
        Assert.assertEquals(1,l1.min(),"Testing at index 0");
        l1.add(-1,0);
        //List order should be 1,2,3,-1
        Assert.assertEquals(-1,l1.min(),"Element is negative");
        l1.remove(-1);
        l1.set(3,0);
        //List order should be 3,1,3
        Assert.assertEquals(1,l1.min(),"Element in middle of list");
        l1.set(0,0);
        //List order should be 0,1,3
        Assert.assertEquals(0,l1.min(),"Element is 0");
        l1.set(3,0);
        l1.set(1,2);
        //List order should be 3,1,3
        Assert.assertEquals(1,l1.min(),"Element at end of list");
        l1.clear();
        //List should be empty
        Assert.assertEquals(null,l1.min(),"List is empty");




        // ====================== Testing max
        System.out.println("--------------Testing max");
        l1.add(4);
        l1.add(2);
        l1.add(3);
        //List order should be 4,2,3
        Assert.assertEquals(4,l1.max(),"Element is at index 0");
        l1.set(10,2);
        //List order should be 4,2,10
        Assert.assertEquals(10,l1.max(),"Element is at the end of the list");
        l1.set(11,1);
        //List order should be 4,11,10
        Assert.assertEquals(11,l1.max(),"Element at middle of list");
        l1.set(-1,0);
        l1.set(-2,1);
        l1.set(-3,2);
        //List order should be -1,-2,-3
        Assert.assertEquals(-1,l1.max(),"Element is negative");
        l1.add(0);
        //List order should be 0,-1,-2,-3
        Assert.assertEquals(0,l1.max(),"Element is 0");
        l1.clear();
        //List should be empty
        Assert.assertEquals(null,l1.max(),"List is empty");
    }
}
