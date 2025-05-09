package finexam.MySolution;

class GameOf24State {
    // Check if the current list of numbers is a goal state (single number approximately equal to 24)
    static boolean isGoal(MyList<Double> numbers) {
        return numbers.size() == 1 && Math.abs(numbers.get(0) - 24.0) < 1e-6;
    }

    // Generate all next states by picking two numbers and replacing them with the result of an operation
    static MyList<MyList<Double>> successors(MyList<Double> numbers) {
        int n = numbers.size();
        if (n < 2) {
            return new MyList<>();  // need at least two numbers to combine
        }
        return expandList(numbers, 0);
    }

    // Recursive helper to pick the first number (at index i) and combine with a second number
    private static MyList<MyList<Double>> expandList(MyList<Double> nums, int i) {
        if (i >= nums.size()) {
            return new MyList<>();
        }
        MyList<MyList<Double>> result = new MyList<>();
        // Combine number at index i with each number at index j > i
        result.addAll(expandPair(nums, i, i + 1));
        // Move to next index for the first number
        MyList<MyList<Double>> more = expandList(nums, i + 1);
        result.addAll(more);
        return result;
    }

    // Recursive helper to combine the number at index i with a number at index j (for j >= i+1)
    private static MyList<MyList<Double>> expandPair(MyList<Double> nums, int i, int j) {
        if (j >= nums.size()) {
            return new MyList<>();
        }
        MyList<MyList<Double>> result = new MyList<>();
        double a = nums.get(i);
        double b = nums.get(j);
        // Create a list of the remaining numbers after removing indices i and j
        MyList<Double> others = removeTwoRec(nums, i, j, 0);

        // Try all arithmetic operations on a and b, and add the resulting new state
        MyList<Double> list1 = new MyList<>();
        list1.addAll(others);
        list1.add(a + b);
        result.add(list1);

        MyList<Double> list2 = new MyList<>();
        list2.addAll(others);
        list2.add(a - b);
        result.add(list2);

        MyList<Double> list3 = new MyList<>();
        list3.addAll(others);
        list3.add(b - a);
        result.add(list3);

        MyList<Double> list4 = new MyList<>();
        list4.addAll(others);
        list4.add(a * b);
        result.add(list4);

        if (Math.abs(b) > 1e-9) {  // avoid division by zero
            MyList<Double> list5 = new MyList<>();
            list5.addAll(others);
            list5.add(a / b);
            result.add(list5);
        }
        if (Math.abs(a) > 1e-9) {  // avoid division by zero
            MyList<Double> list6 = new MyList<>();
            list6.addAll(others);
            list6.add(b / a);
            result.add(list6);
        }

        // Combine the same first number with the next possible second number
        MyList<MyList<Double>> nextPairs = expandPair(nums, i, j + 1);
        result.addAll(nextPairs);
        return result;
    }

    // Recursively build a list of all elements except those at index i and j
    private static MyList<Double> removeTwoRec(MyList<Double> list, int i, int j, int idx) {
        if (idx >= list.size()) {
            return new MyList<>();
        }
        if (idx == i || idx == j) {
            // Skip the elements at indices i and j
            return removeTwoRec(list, i, j, idx + 1);
        }
        // Include this element and recurse for the rest
        MyList<Double> rest = removeTwoRec(list, i, j, idx + 1);
        MyList<Double> newList = new MyList<>(rest.size() + 1);
        newList.add(list.get(idx));
        newList.addAll(rest);
        return newList;
    }
}
