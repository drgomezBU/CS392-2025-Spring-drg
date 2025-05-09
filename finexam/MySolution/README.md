This final contains Java implementations of solutions to three puzzle problems:
the Eight Queens puzzle, the Game of 24, and the Knight's Tour.
Each solution demonstrates the use of state-space search algorithms – including depth-first search (DFS), breadth-first search (BFS), and heuristic-guided search – to find a valid solution for the given problem.

The solution to Problem 1 can be found in the EightQueensProblem1.java file. Unfortunatly, running it results in a Stack Overflow error. This can be overcome by increasing the stack size to at least 3mb which can be done by adding the following java flag '-Xss3M'

The Solution to Problem 2 can be found in the DFS.java and BFS.java files.

The solutions to Problem 3 can be found in the files "EightQueenState.java", "EightQueenDFS.java", and "EightQueenBFS.java". To run the answer for this problem run the main methods in the "EightQueenDFS.java" and "EightQueenBFS.java" files. The High Level description of my solution can be found in the "8QueensSolutionHighLevel.txt" file

The solutions to Problem 4 can be found in the files "GameOf24State.java", "GameOf24DFS.java", and "GameOf24BFS.java". To run the answer for this problem run the main methods in the "GameOf24DFS.java" and "GameOf24BFS.java" files. The High Level description of my solution can be found in the "GameOf24SolutionHighLevel.txt" file

The solutions to Problem 5 can be found in the files "KnightsTourSolver.java" and "KnightsTourState.java". To run the answer for this problem run the main method in the "KnightsTourSolver.java" files. The High Level description of my solution can be found in the "KnightsTourSolutionHighLevel.txt" file. Unfortunatly, running this solution results in a stack overflow error but hypothetically be able to solve the problem given a large enough stack.

This code was written and tested in java version 17.0.5.8.