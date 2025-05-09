
public class Assign03_05<T extends Comparable<T>> implements ArraySorter<T> {
    @Override
    public void sort(T[] A) {
        sortRec(A, A.length);
    }

    private void sortRec(T[] A, int n) {
        if (n <= 1) return;

        sortRec(A, n - 1);

        T last = A[n - 1];
        int j = n - 2;

        while (j >= 0 && A[j].compareTo(last) > 0) {
            A[j + 1] = A[j];
            j--;
        }
        A[j + 1] = last;
    }
}
