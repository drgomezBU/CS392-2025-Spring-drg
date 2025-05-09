package finexam.MySolution;

// Solve Eight Queens using BFS (find one solution)
public class EightQueenBFS {
    public static void main(String[] args) {
        EightQueensState initial = new EightQueensState();
        BFS<EightQueensState> bfs = new BFS<>();
        EightQueensState solution = bfs.search(initial, state -> state.isGoal(), state -> state.successors());
        if (solution != null) {
            // Format the positions array (columns for each row) as a string "[col0, col1, ..., col7]"
            int[] solPositions = solution.positions;
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < solPositions.length; i++) {
                sb.append(solPositions[i]);
                if (i < solPositions.length - 1) sb.append(", ");
            }
            sb.append("]");
            System.out.println("solution found: " + sb.toString());
        } else {
            System.out.println("No solution found with BFS.");
        }
    }
}
