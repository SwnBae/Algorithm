import java.io.*;
import java.util.*;

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
	
	public static int dijkstra(int st, int ed) {
		boolean[] visited =  new boolean[n+1];;
		int[] distance = new int[n+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		distance[st] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) ->  {
			return Integer.compare(a.value, b.value);
		});
		
		pq.add(new Edge(st, 0));
		
		while(!pq.isEmpty()) {
			Edge tmp = pq.poll();
			
			if(visited[tmp.node]) continue;
			
			visited[tmp.node] = true;
			
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
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
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
		}
		
		String[] result = br.readLine().split(" ");
		int st = Integer.parseInt(result[0]);
		int end = Integer.parseInt(result[1]);
		
		System.out.println(dijkstra(st,end));

	}

}
