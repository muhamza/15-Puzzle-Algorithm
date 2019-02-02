# 15-Puzzle-Algorithm
This algorithm solves the 15 Puzzle problem using either the Best first approach or the A* search approach. The user specifies what method to use. 

Input: 
The program accepts the instructions from the command line. The program should
accept the following inputs in the following format:
“[initial state]” [searchmethod] [heuristicFunction]

[initialstate] must contain sixteen characters, namely the digits 1-9, letters A-F, and a space, in any order.
[searchmethod] can be: BFS, AStar.
[heuristicFunction] can be 1 or 2. Where 1 is to count the number of misplaced tiles, while 2 is to count the Manhattan distance (moves required for each tile to get) to the goal states. 

Examples inputs:

“123456789AB DEFC” BFS 1

“123456789AB DEFC” AStar 1

“123456789AB DEFC” AStar 2

-----------------------------------------------------------------------------------------------------------------------------------------------

Output: 
The output file shows all states as the algoritm progresses. The last line contains the goal state or the last state if a solution is not possible.
[currentState],
[depth], [numCreated], [totalCost], [visited]

[currentState] contains sixteen characters, namely the digits 1-9, letters A-F, and a space, resulting from the operation.

[depth] represents the depth in the search tree where the solution is found. The
integer will be zero if the solution is at the root and it will be “-1” if a solution was not found.

[numCreated] is the counter that is incremented every time a node of the search
tree is created (output 0 if depth == -1).

[totalCost] is total cost of the node. It will comprise of heuristic value in case of BFS and a sum of heuristic value and depth of the tree in case of A Star.

[visited] is 1 if algorithm has decided to visit otherwise 0.
