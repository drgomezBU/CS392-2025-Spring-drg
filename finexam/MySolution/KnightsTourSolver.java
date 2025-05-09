package finexam.MySolution;

class KnightTourSolver {
    static KnightTourState searchTour(java.util.PriorityQueue<KnightTourState> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        KnightTourState state = queue.poll();
        if (state.isGoal()) {
            return state;
        }
        java.util.List<KnightTourState> children = state.successors();
        addAll(children, 0, queue);
        return searchTour(queue);
    }
    private static void addAll(java.util.List<KnightTourState> list, int index, java.util.PriorityQueue<KnightTourState> queue) {
        if (index < list.size()) {
            queue.add(list.get(index));
            addAll(list, index + 1, queue);
        }
    }
    public static void main(String[] args) {
        int startRow = 0, startCol = 0;
        KnightTourState start = new KnightTourState(startRow, startCol);
        java.util.PriorityQueue<KnightTourState> pq = new java.util.PriorityQueue<>(java.util.Comparator.comparingInt(s -> s.countNextMoves()));
        pq.add(start);
        KnightTourState result = searchTour(pq);
        if (result != null) {
            System.out.println("Knight's tour found:");
        } else {
            System.out.println("No tour found.");
        }
    }
}
