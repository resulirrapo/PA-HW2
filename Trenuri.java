import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Trenuri {

	public static void main(String[] args) throws IOException {
		MyScanner input = new MyScanner(new FileReader("trenuri.in"));

		String start = input.next();
		final String end = input.next();
		int M = input.nextInt();

		// map to store the graph
		Map<String, List<String>> graph = new HashMap<>();

		// read input
		for (int i = 0; i < M; i++) {
			String from = input.next();
			String to = input.next();
			graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
		}

		// perform topological sort
		List<String> topoSorted = topologicalSort(graph);

		// dp to store max cities visited
		Map<String, Integer> dp = new HashMap<>();
		dp.put(start, 1);  // start counting from the start city

		// process nodes in topological order
		for (String city : topoSorted) {
			if (dp.containsKey(city)) { // Only consider reachable cities from start
				int currentMax = dp.get(city);
				for (String next : graph.getOrDefault(city, new ArrayList<>())) {
					dp.put(next, Math.max(dp.getOrDefault(next, 0), currentMax + 1));
				}
			}
		}

		PrintWriter output = new PrintWriter("trenuri.out");

		// output the result
		output.println(dp.getOrDefault(end, 0));
		output.close();
	}

	static List<String> topologicalSort(Map<String, List<String>> graph) {
		// calculate in-degree of each node
		Map<String, Integer> inDegree = new HashMap<>();
		for (String node : graph.keySet()) {
			for (String adj : graph.get(node)) {
				inDegree.put(adj, inDegree.getOrDefault(adj, 0) + 1);
			}
		}

		// add nodes with in-degree 0 to the queue
		LinkedList<String> queue = new LinkedList<>();
		for (String node : graph.keySet()) {
			if (inDegree.getOrDefault(node, 0) == 0) {
				queue.add(node);
			}
		}

		// process nodes in topological order
		List<String> sorted = new ArrayList<>();
		while (!queue.isEmpty()) {
			String node = queue.poll();
			sorted.add(node);
			for (String adj : graph.getOrDefault(node, new ArrayList<>())) {
				int count = inDegree.get(adj) - 1;
				inDegree.put(adj, count);
				if (count == 0) {
					queue.add(adj);
				}
			}
		}
		return sorted;
	}
}

// Custom Scanner class
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
