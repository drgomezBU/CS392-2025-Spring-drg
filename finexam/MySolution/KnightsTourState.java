package finexam.MySolution;

class KnightTourState {
    static final int BOARD_SIZE = 8;
    static final int TOTAL_SQUARES = 64;
    static final int[][] MOVES = {
        {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
        {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };
    final long visitedMask;
    final int visitedCount;
    final int row;
    final int col;
    final int[] path;
    KnightTourState(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
        this.visitedCount = 1;
        long startIndex = startRow * BOARD_SIZE + startCol;
        this.visitedMask = 1L << startIndex;
        this.path = new int[TOTAL_SQUARES];
        this.path[0] = (int) startIndex;
    }
    KnightTourState(KnightTourState prev, int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
        this.visitedCount = prev.visitedCount + 1;
        long newIndex = newRow * BOARD_SIZE + newCol;
        this.visitedMask = prev.visitedMask | (1L << newIndex);
        this.path = prev.path.clone();
        this.path[prev.visitedCount] = (int) newIndex;
    }
    boolean isGoal() {
        return this.visitedCount == TOTAL_SQUARES;
    }
    java.util.List<KnightTourState> successors() {
        return generateMoves(0);
    }
    private java.util.List<KnightTourState> generateMoves(int k) {
        if (k >= MOVES.length) {
            return java.util.Collections.emptyList();
        }
        java.util.List<KnightTourState> result = new java.util.ArrayList<>();
        int newR = this.row + MOVES[k][0];
        int newC = this.col + MOVES[k][1];
        if (newR >= 0 && newR < BOARD_SIZE && newC >= 0 && newC < BOARD_SIZE) {
            int index = newR * BOARD_SIZE + newC;
            if ((this.visitedMask & (1L << index)) == 0) {
                result.add(new KnightTourState(this, newR, newC));
            }
        }
        result.addAll(generateMoves(k + 1));
        return result;
    }
    int countNextMoves() {
        return countMovesRec(0);
    }
    private int countMovesRec(int k) {
        if (k >= MOVES.length) {
            return 0;
        }
        int newR = this.row + MOVES[k][0];
        int newC = this.col + MOVES[k][1];
        int count = 0;
        if (newR >= 0 && newR < BOARD_SIZE && newC >= 0 && newC < BOARD_SIZE) {
            int index = newR * BOARD_SIZE + newC;
            if ((this.visitedMask & (1L << index)) == 0) {
                count = 1;
            }
        }
        return count + countMovesRec(k + 1);
    }
    void printPath() {
        printPathRec(0);
        System.out.println();
    }
    private void printPathRec(int idx) {
        if (idx < this.visitedCount) {
            int pos = this.path[idx];
            int r = pos / BOARD_SIZE;
            int c = pos % BOARD_SIZE;
            System.out.print("(" + r + "," + c + ")");
            if (idx < this.visitedCount - 1) {
                System.out.print(" -> ");
            }
            printPathRec(idx + 1);
        }
    }
}
