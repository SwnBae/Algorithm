import java.io.*;
import java.util.*;

class Edge {
	int node;
	int value;

	public Edge(int node, int value) {
		this.node = node;
		this.value = value;
	}
}

public class Main {
	public static final int INF = 1000000000;
	public static int n;
	public static int m;
	public static List<List<Edge>> graph;

	public static int[] route;

	public static int dijk(int st, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));

		int[] dis = new int[n + 1];

		Arrays.fill(dis, INF);

		dis[st] = 0;
		
		pq.add(new Edge(st, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.node == end) {
				return dis[end];
			}

			if (cur.value > dis[cur.node]) {
				continue;
			}

			for (Edge next : graph.get(cur.node)) {
				if (dis[next.node] > dis[cur.node] + next.value) {
					dis[next.node] = dis[cur.node] + next.value;
					
					route[next.node] = cur.node;

					pq.add(new Edge(next.node, dis[next.node]));
				}
			}
		}

		return dis[end];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		route = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			graph.get(Integer.parseInt(input[0])).add(new Edge(Integer.parseInt(input[1]), Integer.parseInt(input[2])));
		}

		String[] rst = br.readLine().split(" ");

		int start = Integer.parseInt(rst[0]);
		int end = Integer.parseInt(rst[1]);

		int cost = dijk(start, end);
		int cnt = 1;
		
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		
		int idx = end;
		deque.add(end);

		while(route[idx] != 0) {
			cnt++;
			deque.add(route[idx]);
			
			idx = route[idx];
		}
		
		System.out.println(cost);
		System.out.println(cnt);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < cnt; i++) {
			if(i > 0) sb.append(" ");
			sb.append(deque.pollLast());
		}
		
		System.out.println(sb);

	}

}
