import java.io.*;
import java.util.*;

class Edge{
	int node;
	int cost;
	
	public Edge(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
}


public class Main {
	public static final int INF = 10000001;
	public static List<List<Edge>> graph;
	public static List<List<Edge>> revGraph;
	
	public static int n;
	public static int m;
	public static int[] distance;
	
	public static void dijk(int st, int ed) {
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.cost,o2.cost);
			}
		});
		
		distance = new int[n];
		
		Arrays.fill(distance, INF);
		distance[st] = 0;
		
		pq.add(new Edge(st, 0));
		
		while(!pq.isEmpty()) {
			Edge tmp = pq.poll();
			
			if(distance[tmp.node] < tmp.cost) {
				continue;
			}
			
			for(Edge next : graph.get(tmp.node)) {
				if(distance[next.node] > distance[tmp.node] + next.cost) {
					distance[next.node] = distance[tmp.node] + next.cost;
					
					pq.add(new Edge(next.node, distance[next.node]));
				}
			}
		}
	}
	
	public static void reverse(int end) {
		ArrayDeque<Edge> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];
		
		queue.add(new Edge(end, distance[end]));
		
		while(!queue.isEmpty()) {
			Edge tmp = queue.poll();
			
			for(Edge next : revGraph.get(tmp.node)) {
				if(visited[tmp.node][next.node]) continue;
				
				if(tmp.cost - distance[next.node] == next.cost) {
					visited[tmp.node][next.node] = true;
					graph.get(next.node).removeIf(edge -> edge.node == tmp.node);
					queue.add(new Edge(next.node, distance[next.node]));
					continue;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String nmT;
		
		while(!(nmT = br.readLine()).equals("0 0")) {
			String[] nm = nmT.split(" ");
			
			n = Integer.parseInt(nm[0]);
			m = Integer.parseInt(nm[1]);
			
			graph = new ArrayList<>();
			revGraph = new ArrayList<>();
			
			for(int i = 0; i < n; i++) {
				graph.add(new ArrayList<>());
				revGraph.add(new ArrayList<>());
			}
			
			String[] se = br.readLine().split(" ");
			
			int start = Integer.parseInt(se[0]);
			int end = Integer.parseInt(se[1]);
			
			for(int i = 0; i < m; i++) {
				String[] input = br.readLine().split(" ");
				int a = Integer.parseInt(input[0]);
				int b = Integer.parseInt(input[1]);
				int c = Integer.parseInt(input[2]);
				
				graph.get(a).add(new Edge(b, c));
				revGraph.get(b).add(new Edge(a, c));
			}
			
			dijk(start,end);
			
			reverse(end);
			
			dijk(start, end);

			System.out.println(distance[end] == INF ? -1 : distance[end]);
		}
		
		
		
	}

}
