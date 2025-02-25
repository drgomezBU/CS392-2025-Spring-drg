import java.util.HashSet;
import java.util.Set;

public class Assign02_05 {
    public static boolean solve_3sum(Integer[] A) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (set.contains(A[i] + A[j])) {
                    return true;
                }
            }
            set.add(A[i]);
        }
        return false;
    }

    public static void main(String[] argv) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        boolean result = solve_3sum(array);
        System.out.println("3-sum result: " + result + " (Expected: true)");
    }
}
