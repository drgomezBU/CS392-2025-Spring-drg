
public class Assign02_02 {
    /*
      HX-2025-02-13: 10 points
      Recursion is a fundamental concept in programming.
      However, the support for recursion in Java is very limited.
      Nontheless, we will be making extensive use of recursion in
      this class.
     */

    public static <T extends Comparable<T>> int indexOf(T[] a, T key) {
        return indexOf(a, key, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> int indexOf(T[] a, T key, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(a[mid]);
        if (cmp < 0) return indexOf(a, key, lo, mid - 1);
        else if (cmp > 0) return indexOf(a, key, mid + 1, hi);
        else return mid;
    }

    public static void main(String[] argv) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int index = indexOf(array, 5);
        System.out.println("Index of 5: " + index + " (Expected: 4)");
    }
}
