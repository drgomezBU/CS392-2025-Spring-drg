import java.util.Comparator;

public class Assign04_01 {

    // This class should not be instantiated.
    private Assign04_01() { }

    private static <T> void exch(T A[], int i, int j) {
        T tmp;
        tmp = A[i]; A[i] = A[j]; A[j] = tmp; return;
    }

    private static <T extends Comparable<T>> boolean less(T x, T y) {
        return (x.compareTo(y) < 0);
    }

    public static <T extends Comparable<T>> void sort(T A[]) {
        final int n = A.length;
        sortRec(A, 0, n); return;
    }

    private static <T> int getPivot(int l, int r) {
        return l; // always use the first element as pivot
    }

    private static <T extends Comparable<T>> int split(T A[], int l, int r) {
        int p1 = l;
        T pvt = A[r-1];
        while (true) {
            if (less(A[p1], pvt)) p1 += 1; else break;
        }
        return splitRec(A, p1, p1+1, pvt); // HX: we have pvt <= A[p1]
    }

    private static <T extends Comparable<T>> int splitRec(T A[], int p1, int p2, T pvt) {
        if (p2 >= A.length) return p1;
        if (A[p2] == pvt) return splitRec(A, p1, p2+1, pvt); // skip the pivot itself
        if (less(A[p2], pvt)) {
            exch(A, p1, p2);
            return splitRec(A, p1+1, p2+1, pvt);
        } else {
            return splitRec(A, p1, p2+1, pvt);
        }
    }

    private static <T extends Comparable<T>> void sortRec(T A[], int l, int r) {
        if (r <= l+1) return;
        final int p = getPivot(l, r);
        exch(A, p, r-1); // move pivot to end
        final int m = split(A, l, r);
        exch(A, m, r-1); // put pivot in place
        sortRec(A, l, m);
        sortRec(A, m+1, r);
        return;
    }

    public static void main(String[] argv) {
        Integer[] arr = {5, 3, 8, 4, 2, 7, 1, 6};
        sort(arr);
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

}
