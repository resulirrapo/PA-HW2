import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Drumuri {
	static final long INF = Long.MAX_VALUE;
	ArrayList<int[]>[] adj;
	int n, m, x, y, z;

	private void solve() throws IOException {
		readInput();
		writeOutput();
	}

	private void readInput() throws IOException {
		MyScanner sc = new MyScanner(new FileReader("drumuri.in"));
		n = sc.nextInt();
		m = sc.nextInt();
		adj = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int from = sc.nextInt(), to = sc.nextInt(), cost = sc.nextInt();
			adj[to].add(new int[]{from, cost}); // Reverse the edge
		}

		x = sc.nextInt();
		y = sc.nextInt();
		z = sc.nextInt();
		sc.close();
	}

	private void writeOutput() throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("drumuri.out"));
		long[] costToZ = dijkstra(z);

		long distXtoZ = costToZ[x];
		long distYtoZ = costToZ[y];

		if (distXtoZ == INF || distYtoZ == INF) {
			pw.println("-1");
		} else {
			// Adjust calculation to better manage potential path overlaps
			long result = distXtoZ + distYtoZ;
			pw.println(result);
		}
		pw.close();
	}

	private long[] dijkstra(int start) {
		long[] dist = new long[n + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
		pq.add(new int[]{start, 0});

		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			int node = current[0];
			long currentDistance = current[1];

			if (currentDistance > dist[node]) {
				continue;
			}

			for (int[] edge : adj[node]) {
				int neighbor = edge[0];
				long weight = edge[1];
				long newDistance = dist[node] + weight;

				if (newDistance < dist[neighbor]) {
					dist[neighbor] = newDistance;
					pq.add(new int[]{neighbor, (int) newDistance});
				}
			}
		}
		return dist;
	}

	public static void main(String[] args) {
		try {
			new Drumuri().solve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//
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

		public void close() throws IOException {
			br.close();
		}
	}
}
