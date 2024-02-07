/** Creates AlphaNumeric arrays for testing. */
public class ArrayMaker {

    /** Different characteristics of the arrays to be sorted */
    public static enum ArrayOrder {
        REVERSED, SORTED, RANDOM
    };

    /** Create an array with specified ordering and size
     * @param order One of the enumerated characteristics
     * @param size Of the array
     * @return new AlphaNumeric array of specified size and ordering
     */
    public static AlphaNumeric[] makeArray(ArrayOrder order, int size) {

        // Call appropriate helper function based on ordering
        if (order == ArrayOrder.REVERSED) {
            return makeReversed(size);
        } else if (order == ArrayOrder.SORTED) {
            return makeSorted(size);
        } else if (order == ArrayOrder.RANDOM) {
            return makeRandom(size);
        }
        
        // Should not get here, because only valid enums would be accepted, but just in case
        return null;

    } // end makeArray()

    /** Reversed array with numbers in decreasing order and alphas in increasing order */
    private static AlphaNumeric[] makeReversed(int size) {

        AlphaNumeric[] array = new AlphaNumeric[size];

        // All Alphas have 6 characters. The numbers start at "size"
        array[0] = new AlphaNumeric("aaaaaa",size);
        for (int i=1; i<size; i++) {
            // Use the helper function to determine next string (ex: aaaaaa, aaaaab, ...)
            // The value at [i] is the "next" after [i-1]
            array[i] = new AlphaNumeric(
            AlphaNumeric.nextAlpha(array[i-1].alpha(),false), 
            array[i-1].number()-1);
        }
        return array;
    } // end makeReversed()

    /** Sorted array with numbers in increasing order and alphas in decreasing order */
    private static AlphaNumeric[] makeSorted(int size) {

        AlphaNumeric[] array = new AlphaNumeric[size];

        // All Alphas have 6 characters. The numbers start at 0
        array[0] = new AlphaNumeric("zzzzzz", 0);
        for (int i = 1; i < size; i++) {
            // Use the helper function to determine next string (ex: zzzzzz, zzzzzy, zzzzzx, ...)
            // The value at [i] is the "next" after [i-1]
            array[i] = new AlphaNumeric(
                    AlphaNumeric.nextAlpha(array[i - 1].alpha(), true),
                    array[i - 1].number() + 1);
        }
        return array;
    } // end makeSorted()
    
    /** Random array with both random numbers and random alphas */
    private static AlphaNumeric[] makeRandom(int size) {

        AlphaNumeric[] array = new AlphaNumeric[size];

        // The default constructor makes random 6-char alphas with numbers up to 1000000
        for (int i = 0; i < size; i++) {
            array[i] = new AlphaNumeric();
        }
        return array;
    } // end makeRandom()
}