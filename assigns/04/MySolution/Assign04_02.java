import java.util.Comparator;

public class Assign04_02 {

    // This class should not be instantiated.
    private Assign04_02() { }

    private static <T extends Comparable<T>> boolean less(T x, T y) {
        return (x.compareTo(y) < 0);
    }

    public static <T extends Comparable<T>> void listSort(LList<T> xs) {
        if (xs == null || xs.next == null) return;

        T pivot = xs.elem;
        LList<T> lessHead = null, lessTail = null;
        LList<T> equalHead = null, equalTail = null;
        LList<T> greaterHead = null, greaterTail = null;

        // Partition
        for (LList<T> curr = xs; curr != null; curr = curr.next) {
            if (less(curr.elem, pivot)) {
                if (lessHead == null) lessHead = lessTail = new LList<T>();
                else { lessTail.next = new LList<T>(); lessTail = lessTail.next; }
                lessTail.elem = curr.elem;
            } else if (less(pivot, curr.elem)) {
                if (greaterHead == null) greaterHead = greaterTail = new LList<T>();
                else { greaterTail.next = new LList<T>(); greaterTail = greaterTail.next; }
                greaterTail.elem = curr.elem;
            } else {
                if (equalHead == null) equalHead = equalTail = new LList<T>();
                else { equalTail.next = new LList<T>(); equalTail = equalTail.next; }
                equalTail.elem = curr.elem;
            }
        }

        // Recursively sort less and greater
        listSort(lessHead);
        listSort(greaterHead);

        // Concatenate: less + equal + greater
        LList<T> head = null, tail = null;
        for (LList<T> part : new LList[]{lessHead, equalHead, greaterHead}) {
            if (part != null) {
                if (head == null) { head = tail = part; }
                else { tail.next = part; }
                // Move tail to end of this part
                while (tail.next != null) tail = tail.next;
            }
        }

        LList<T> src = head, dst = xs;
        while (src != null) {
            dst.elem = src.elem;
            src = src.next;
            dst = dst.next;
        }
    }

    public static void main(String[] argv) {
        LList<Integer> xs = new LList<>();
        xs.elem = 3; xs.next = new LList<>(); xs.next.elem = 1; xs.next.next = new LList<>(); xs.next.next.elem = 2;
        listSort(xs);
        for (LList<Integer> curr = xs; curr != null; curr = curr.next)
            System.out.print(curr.elem + " ");
        System.out.println();
    }
}
