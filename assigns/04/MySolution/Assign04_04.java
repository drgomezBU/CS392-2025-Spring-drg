import java.util.Comparator;

public class Assign04_04 {

    // This class should not be instantiated.
    private Assign04_04() { }

    private static class Pair<T extends Comparable<T>> implements Comparable<Pair<T>> {
        T value;
        int index;
        Pair(T value, int index) { this.value = value; this.index = index; }
        public int compareTo(Pair<T> o) {
            int cmp = value.compareTo(o.value);
            if (cmp != 0) return cmp;
            return Integer.compare(index, o.index);
        }
    }

    public static <T extends Comparable<T>> void stableSort(T A[]) {
        // Wrap elements with their original indices
        Pair<T>[] pairs = new Pair[A.length];
        for (int i = 0; i < A.length; i++) pairs[i] = new Pair<>(A[i], i);
        // Use quicksort (from Assign04_01)
        Assign04_01.sort(pairs);
        // Unwrap
        for (int i = 0; i < A.length; i++) A[i] = pairs[i].value;
    }

    public static void main(String[] argv) {
        Integer[] arr = {2, 3, 2, 1, 3, 1};
        stableSort(arr);
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }
}
