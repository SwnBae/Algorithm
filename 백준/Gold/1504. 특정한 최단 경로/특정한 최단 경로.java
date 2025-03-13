import java.io.*;
import java.util.*;

// MAX값 끼리 더함 -> 연결되지 않는 경로의 합 등에 의해 발생하는 오버플로우..!
class Edge{
	int node;
	int value;
	
	public Edge(int node, int value) {
		this.node = node;
		this.value = value;
	}
}

public class Main {
	public static List<List<Edge>> graph;
	public static int n;
	public static int m;
	public static final int INF = 200000000;
	
	public static int dijkstra(int st, int ed) {
		int[] distance = new int[n+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) ->  {
			return Integer.compare(a.value, b.value);
		});
		
		Arrays.fill(distance, INF);
		
		pq.add(new Edge(st, 0));
		distance[st] = 0;
		
		while(!pq.isEmpty()) {
			Edge tmp = pq.poll();
			
			if(tmp.value > distance[tmp.node]) continue;
			
			for(Edge next : graph.get(tmp.node)) {
				if(distance[next.node] > distance[tmp.node] + next.value) {
					distance[next.node] = distance[tmp.node] + next.value;
					pq.add(new Edge(next.node, distance[next.node]));
				}
			}
		}
		
		return distance[ed];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		
		graph = new ArrayList<>();
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			
			graph.get(a).add(new Edge(b, c));
			graph.get(b).add(new Edge(a, c));
		}
		
		String[] result = br.readLine().split(" ");
		
		int st = Integer.parseInt(result[0]);
		int end = Integer.parseInt(result[1]);
		
		int ans1 = dijkstra(1, st) + dijkstra(st,end) + dijkstra(end, n);
		int ans2 = dijkstra(1, end) + dijkstra(end, st) + dijkstra(st, n);
		
		int ans = Math.min(ans1, ans2);
		
		if(ans1 >= INF && ans2 >= INF) ans = -1;
		
		
		System.out.println(ans);

	}

}
