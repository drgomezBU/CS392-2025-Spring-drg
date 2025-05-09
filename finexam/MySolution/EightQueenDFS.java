package finexam.MySolution;

// Solve Eight Queens using DFS (find one solution)
public class EightQueenDFS {
    public static void main(String[] args) {
        EightQueensState initial = new EightQueensState();
        DFS<EightQueensState> dfs = new DFS<>();
        EightQueensState solution = dfs.search(initial, state -> state.isGoal(), state -> state.successors());
        if (solution != null) {
            // Print the solution positions similarly
            int[] solPositions = solution.positions;
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < solPositions.length; i++) {
                sb.append(solPositions[i]);
                if (i < solPositions.length - 1) sb.append(", ");
            }
            sb.append("]");
            System.out.println("solution found: " + sb.toString());
        } else {
            System.out.println("No solution found with DFS.");
        }
    }
}
