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
	public static int start;
	
	public static int bfs(int node) {
		Queue<int[]> queue = new ArrayDeque<>();
		
		boolean[] visited = new boolean[n+1];
		int max = 0;
		
		// 해당 노드, 거리
		queue.add(new int[] {node, 0});
		visited[node] = true;
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int nd = tmp[0];
			int sum = tmp[1];
			
			if(sum > max) {
				start = nd;
				max = sum;
			}
			
			for(Edge e : graph.get(nd)) {
				if(visited[e.node]) continue;
				
				visited[e.node] = true;
				queue.add(new int[] {e.node, sum + e.value});
			}
			
		}
		
		// 거리반환
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		start = 0;
		
		graph = new ArrayList<>();
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < n - 1; i++) {
			String[] input = br.readLine().split(" ");
			
			int p = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			int v = Integer.parseInt(input[2]);
			
			graph.get(p).add(new Edge(c, v));
			graph.get(c).add(new Edge(p, v));
		}
		
		bfs(1);
		int st = start;
		System.out.println(bfs(st));
	}

}
