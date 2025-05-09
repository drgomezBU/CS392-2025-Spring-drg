package finexam.MySolution;

public class EightQueensProblem1 {
    static final int N = 8;

    // print_dots(i: int): void
    public static void printDots(int i) {
        if (i > 0) {
            System.out.print(". ");
            printDots(i - 1);
        }
    }

    // print_row(i: int): void
    public static void printRow(int i) {
        printDots(i);
        System.out.print("Q ");
        printDots(N - i - 1);
        System.out.println();
    }

    // print_board(bd: int[]): void
    public static void printBoard(int[] bd) {
        for (int row = 0; row < N; row++) {
            printRow(bd[row]);
        }
        System.out.println();
    }

    // board_get(bd: int[], i: int): int
    public static int boardGet(int[] bd, int i) {
        if (i >= 0 && i < N) {
            return bd[i];
        } else {
            return -1;
        }
    }

    // board_set(bd: int[], i: int, j: int): int[]
    public static int[] boardSet(int[] bd, int i, int j) {
        int[] newBd = new int[N];
        for (int k = 0; k < N; k++) {
            newBd[k] = bd[k];
        }
        if (i >= 0 && i < N) {
            newBd[i] = j;
        }
        return newBd;
    }

    // safety_test1(i0, j0, i, j): boolean
    public static boolean safetyTest1(int i0, int j0, int i, int j) {
        return j0 != j && Math.abs(i0 - i) != Math.abs(j0 - j);
    }

    // safety_test2(i0, j0, bd, i): boolean
    public static boolean safetyTest2(int i0, int j0, int[] bd, int i) {
        if (i >= 0) {
            if (safetyTest1(i0, j0, i, boardGet(bd, i))) {
                return safetyTest2(i0, j0, bd, i - 1);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    // search(bd, i, j, nsol): int
    public static int search(int[] bd, int i, int j, int nsol) {
        if (j < N) {
            if (safetyTest2(i, j, bd, i - 1)) {
                int[] bd1 = boardSet(bd, i, j);
                if (i + 1 == N) {
                    System.out.println("Solution #" + (nsol + 1) + ":");
                    System.out.println();
                    printBoard(bd1);
                    return search(bd, i, j + 1, nsol + 1);
                } else {
                    return search(bd1, i + 1, 0, nsol);
                }
            } else {
                return search(bd, i, j + 1, nsol);
            }
        } else {
            if (i > 0) {
                return search(bd, i - 1, boardGet(bd, i - 1) + 1, nsol);
            } else {
                return nsol;
            }
        }
    }

    public static void main(String[] args) {
        int[] board = new int[N];
        // initialize to -1
        for (int k = 0; k < N; k++) {
            board[k] = -1;
        }

        int totalSolutions = search(board, 0, 0, 0);
        System.out.println("Total solutions: " + totalSolutions);
    }
}
