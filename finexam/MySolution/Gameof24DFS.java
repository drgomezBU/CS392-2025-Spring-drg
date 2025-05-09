package finexam.MySolution;

// Using DFS to find a solution for the 24 Game
public class Gameof24DFS {
    public static void main(String[] args) {
        // Initialize the numbers list (e.g., 8, 3, 8, 3)
        MyList<Double> numbers = new MyList<>();
        numbers.add(8.0);
        numbers.add(3.0);
        numbers.add(8.0);
        numbers.add(3.0);

        DFS<MyList<Double>> dfs = new DFS<>();
        MyList<Double> result = dfs.search(numbers, GameOf24State::isGoal, GameOf24State::successors);
        if (result != null) {
            System.out.println("Solution found");
        } else {
            System.out.println("No solution found with DFS.");
        }
    }
}

