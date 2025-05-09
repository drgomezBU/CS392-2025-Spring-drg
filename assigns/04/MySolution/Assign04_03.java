import java.util.Comparator;

public class Assign04_03 {

    // This class should not be instantiated.
    private Assign04_03() { }

    private static <T extends Comparable<T>> boolean less(T x, T y) {
        return (x.compareTo(y) < 0);
    }

    public static <T extends Comparable<T>> void listSort(LList<T> xs) {
        // Implements mergesort on a linked list
        // No extra heap memory is used for list-mergesort
        Comparator<T> comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        };

        xs = mergesort(comparator, xs);
    }

    private static <T extends Comparable<T>> LList<T> mergesort(Comparator<T> comparator, LList<T> list) {
        if (list == null || list.next == null) {
            return list; // Base case: single element or empty list
        }

        // Split the list into two halves
        LList<T> right = split(list);

        // Recursively sort both halves
        list = mergesort(comparator, list);
        right = mergesort(comparator, right);

        // Merge the sorted halves
        return merge(comparator, list, right);
    }

    private static <T extends Comparable<T>> LList<T> split(LList<T> list) {
        LList<T> slow = list, fast = list, mid;

        // Use two-pointer technique to find the middle
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast != null) {
                slow = slow.next;
            }
        }

        mid = slow.next;
        slow.next = null; // Break the list into two halves
        return mid;
    }

    private static <T extends Comparable<T>> LList<T> merge(Comparator<T> comparator, LList<T> left, LList<T> right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        // Compare elements and merge recursively
        if (comparator.compare(left.elem, right.elem) < 0) {
            left.next = merge(comparator, left.next, right);
            return left;
        } else {
            right.next = merge(comparator, left, right.next);
            return right;
        }
    }

    public static void main(String[] argv) {
        LList<Integer> list = new LList<>();
        list.elem = 4;
        list.next = new LList<>();
        list.next.elem = 2;
        list.next.next = new LList<>();
        list.next.next.elem = 5;
        list.next.next.next = new LList<>();
        list.next.next.next.elem = 1;

        listSort(list);
    }
}
