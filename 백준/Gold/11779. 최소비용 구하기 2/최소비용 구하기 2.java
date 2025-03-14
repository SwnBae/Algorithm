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

class Info {
	Edge edge;
	int cnt;
	int[] rt;

	public Info(Edge edge, int cnt) {
		this.edge = edge;
		this.cnt = cnt;
	}
}

public class Main {
	public static final int INF = 1000000000;
	public static int n;
	public static int m;
	public static List<List<Edge>> graph;

	public static Info dijk(int st, int end) {
		PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.edge.value, b.edge.value));

		int[] dis = new int[n + 1];

		Arrays.fill(dis, INF);
		
		dis[st] = 0;
		
		Info start = new Info(new Edge(st, 0), 1);
		start.rt = new int[n + 1];
		Arrays.fill(start.rt, -1);
		start.rt[0] = st;
		
		pq.add(start);

		while (!pq.isEmpty()) {
			Info info = pq.poll();
			Edge cur = info.edge;

			if (cur.node == end) {
				return info;
			}

			if (cur.value > dis[cur.node]) {
				continue;
			}

			for (Edge next : graph.get(cur.node)) {
				if (dis[next.node] > dis[cur.node] + next.value) {
					dis[next.node] = dis[cur.node] + next.value;
					Info nxtInfo = new Info(new Edge(next.node, dis[next.node]), info.cnt + 1);
					
					nxtInfo.rt = Arrays.copyOf(info.rt, info.rt.length);
					nxtInfo.rt[nxtInfo.cnt] = next.node;
					
					pq.add(nxtInfo);
				}
			}
		}

		return null;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();

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

		Info result = dijk(start, end);

		System.out.println(result.edge.value);
		System.out.println(result.cnt);

		for (int r : result.rt) {
			if(r == -1) continue;
			System.out.print(r + " ");
		}

	}

}
