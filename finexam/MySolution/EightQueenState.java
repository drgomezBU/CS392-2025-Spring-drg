package finexam.MySolution;

// State representation for the Eight Queens puzzle
class EightQueensState {
    static final int N = 8;
    final int[] positions;  // positions[r] = column index of queen at row r
    final int row;          // next row index to place a queen (number of queens already placed)

    EightQueensState() {
        this.positions = new int[N];
        this.row = 0;
    }
    EightQueensState(int[] positions, int row) {
        this.positions = positions;
        this.row = row;
    }

    // Goal is reached when 8 queens have been placed (row == N)
    boolean isGoal() {
        return this.row == N;
    }

    // Check if placing a queen at column 'col' on the current row is safe (no attacks)
    boolean isSafe(int col) {
        return isSafeRec(this.row - 1, col);
    }
    private boolean isSafeRec(int prevRow, int col) {
        if (prevRow < 0) {
            return true;
        }
        int prevCol = this.positions[prevRow];
        // Check column conflict or diagonal conflict
        if (prevCol == col || Math.abs(prevCol - col) == (this.row - prevRow)) {
            return false;
        }
        // Check next previous row
        return isSafeRec(prevRow - 1, col);
    }

    // Generate all successor states by placing a queen in the next row (this.row) at any safe column
    MyList<EightQueensState> successors() {
        return generateFromColumn(0);
    }
    private MyList<EightQueensState> generateFromColumn(int c) {
        if (c >= N) {
            return new MyList<>();  // no more columns to try, return empty list
        }
        MyList<EightQueensState> result = new MyList<>();
        if (isSafe(c)) {
            // Copy current positions and place a queen at column c of the current row
            int[] newPositions = new int[N];
            for (int k = 0; k < N; k++) {
                newPositions[k] = this.positions[k];
            }
            newPositions[this.row] = c;
            result.add(new EightQueensState(newPositions, this.row + 1));
        }
        // Try next column in this row
        MyList<EightQueensState> nextResults = generateFromColumn(c + 1);
        result.addAll(nextResults);
        return result;
    }
}

