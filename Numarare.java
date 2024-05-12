import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Numarare {
	static final int MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		MyScanner input = new MyScanner(new FileReader("numarare.in"));

		// Read input

		// nunmber of nodes
		int N = input.nextInt();
		// number of edges
		int M = input.nextInt();

		ArrayList<Integer>[] graph1 = new ArrayList[N + 1];
		ArrayList<Integer>[] graph2 = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			graph1[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int before = input.nextInt();
			int after = input.nextInt();
			graph1[before].add(after);
		}

		for (int i = 0; i < M; i++) {
			int before = input.nextInt();
			int after = input.nextInt();
			graph2[before].add(after);
		}

		int[] dp = new int[N + 1];
		dp[1] = 1; // start at node 1

		// topological sort to determine node processing order
		List<Integer> topoSorted = topologicalSort(graph1, N);
		Set<Integer>[] intersectGraph = new HashSet[N + 1];

		for (int i = 1; i <= N; i++) {
			intersectGraph[i] = new HashSet<>(graph1[i]);
			intersectGraph[i].retainAll(graph2[i]);
		}

		// traverse nodes in topological order
		for (int node : topoSorted) {
			for (int next : intersectGraph[node]) {
				dp[next] = (dp[next] + dp[node]) % MOD;
			}
		}
		PrintWriter output = new PrintWriter("numarare.out");

		output.println(dp[N]);
		output.close();
	}

	// Topological sort
	static List<Integer> topologicalSort(ArrayList<Integer>[] graph, int N) {
		//calculate in-degree of each node
		int[] inDegree = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			for (int node : graph[i]) {
				inDegree[node]++;
			}
		}

		// add nodes with in-degree 0 to the queue
		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}

		// process nodes in topological order
		List<Integer> sorted = new ArrayList<>();
		while (!queue.isEmpty()) {
			int node = queue.poll();
			sorted.add(node);
			for (int next : graph[node]) {
				inDegree[next]--;
				if (inDegree[next] == 0) {
					queue.add(next);
				}
			}
		}
		return sorted;
	}
}

// custom Scanner class
class MyScanner {
	private BufferedReader br;
	private StringTokenizer st;

	public MyScanner(Reader reader) {
		br = new BufferedReader(reader);
	}

	public String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}