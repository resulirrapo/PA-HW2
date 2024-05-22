--------------Numarare:-----------

I use a custom MyScanner1 class to read the input

At main function we read the number of nodes and number of edges and then initialize the adjacency list
We use topological sort to determine node processing order by traversing each node.

Topological sort:
Here we use the function to calculate in-degree of each node and 
add nodes with in-degree 0 to the queue and lasat process nodes in topological order.

The complexity of this algorithm is : O(N + M)


------------Trenuri:----------------

I use a custom MyScanner2 class to read the input

We start by reading the number of each route and use map to store the graph then we use topological sort
and dynamic programming to store the number of each city visited and then we process nodes in topological sort

Topological sort:
Here we use the function to calculate in-degree of each node and
add nodes with in-degree 0 to the queue and process the nodes in topological order.

The complexity of this algorithm is : O(V + M)


-------------Drumuri---------------


I use a custom MyScanner3 class to read the input

I started the implementation of this part from lab9 and used some of the methods there as write/read input.
We start by reading the number of nodes and edges and initialize the adjacency list.
Then i used dijkstra to find the shortest path from start to all other nodes.

The complexity of this algorithm is : O((v + M)log V)