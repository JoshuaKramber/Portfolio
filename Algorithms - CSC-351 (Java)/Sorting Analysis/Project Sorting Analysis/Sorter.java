public interface Sorter<T> {

    public void sort(T[] array);
    public void sort(T[] inArray, T[] outArray);

    public long getCount();

}
