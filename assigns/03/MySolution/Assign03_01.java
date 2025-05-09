public class Assign03_01 {
    public static int mystery(int x) {
        return (x < 100 ? 100 - x : mystery(mystery(x - 11)));
    }

    public static void main(String[] argv) {
        int low = 0, high = Integer.MAX_VALUE, result = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            try {
                mystery(mid);
                result = mid; // Update result if no exception
                low = mid + 1;
            } catch (StackOverflowError e) {
                high = mid - 1;
            }
        }

        System.out.println("Largest N such that mystery(N) does not throw StackOverflowError: " + result);
    }
}
