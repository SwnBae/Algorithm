import java.io.*;
import java.util.*;

class Edge{
	int node;
	int weight;
	
	public Edge(int node, int weight) {
		this.node = node;
		this.weight = weight;
	}
}

public class Main {
	public static int v;
	public static List<List<Edge>> graph;
	public static int max;
	public static int start;
	
	public static int bfs(int st) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[v + 1];
		int result = 0;
		int maxNode = -1;
		
		queue.add(new int[] {st, 0});
		visited[st] = true;
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int node = tmp[0];
			int sum = tmp[1];
			
			if(sum > result) {
				result = sum;
				maxNode = node;
			}
			
			
			for(Edge e : graph.get(node)) {
				if(visited[e.node]) continue;
				
				visited[e.node] = true;
				queue.add(new int[] {e.node, sum + e.weight});
				
			}
		}
		
		start = maxNode;
		
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		v = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		max = 0;
		
		for(int i = 0; i <= v; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < v; i++) {
			String[] input = br.readLine().split(" ");
			
			int a = Integer.parseInt(input[0]);
			int idx = 1;
			
			while(!input[idx].equals("-1")) {
				int b = Integer.parseInt(input[idx]);
				int w = Integer.parseInt(input[idx + 1]);
				
				graph.get(a).add(new Edge(b,w));
				idx += 2;
			}
		}
		
		bfs(1);
		
		max = bfs(start);
		
		System.out.println(max);
	}
}
