public class Assign03_02 {
    public static <T> boolean stableSort(ArraySorter<T> sorter, T[] A) {
        class Wrapper implements Comparable<Wrapper> {
            T value;
            int originalIndex;

            Wrapper(T value, int originalIndex) {
                this.value = value;
                this.originalIndex = originalIndex;
            }

            @Override
            public int compareTo(Wrapper other) {
                @SuppressWarnings("unchecked")
                Comparable<T> comparableValue = (Comparable<T>) this.value;
                return comparableValue.compareTo(other.value);
            }
        }

        Wrapper[] wrappedArray = new Wrapper[A.length];
        for (int i = 0; i < A.length; i++) {
            wrappedArray[i] = new Wrapper(A[i], i);
        }

        sorter.sort((T[]) wrappedArray);

        for (int i = 1; i < wrappedArray.length; i++) {
            if (wrappedArray[i - 1].value.equals(wrappedArray[i].value) &&
                wrappedArray[i - 1].originalIndex > wrappedArray[i].originalIndex) {
                return false;
            }
        }
        return true;
    }
}
