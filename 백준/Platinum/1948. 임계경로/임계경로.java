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
	public static int n;
	public static int[] degree;
	public static int[] dp;
	public static int[] cnt;
	public static List<List<Edge>> graph;
	public static List<List<Edge>> reverseGraph;
	public static int result;
	
	public static void topologySort(int start) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		dp[start] = 0;
		queue.add(start);
		
		for(int i = 1; i <= n; i++) {
			//이 조건은 주어지지 않는다.
			if(queue.isEmpty()) {
				return;
			}
			
			int node = queue.poll();
			
			//같은 길을 쓰면 불가능하다.
			for(Edge next : graph.get(node)) {
				degree[next.node]--;
				
				if(dp[next.node] <= dp[node] + next.value) {
					dp[next.node] = dp[node] + next.value;
				}
				if(degree[next.node] == 0) {
					queue.add(next.node);
				}
			}
		}
	}
	
	public static void calculateCnt(int start, int end) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n+1][n+1];
		
		queue.add(end);
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			if(node == start) {
				continue;
			}
			
			for(Edge next : reverseGraph.get(node)) {
				if(visited[node][next.node]) continue;
				
				if(dp[node] - next.value == dp[next.node]) {
					result++;
					visited[node][next.node] = true;
					queue.add(next.node);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		dp = new int[n+1];
		degree = new int[n+1];
		cnt = new int[n+1];
		result = 0;
		
		graph = new ArrayList<>();
		reverseGraph = new ArrayList<>();
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
			reverseGraph.add(new ArrayList<>());
		}
		
		int m = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= m; i++) {
			String[] input = br.readLine().split(" ");
			
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int v = Integer.parseInt(input[2]);
			
			graph.get(a).add(new Edge(b, v));
			degree[b]++;
			
			reverseGraph.get(b).add(new Edge(a, v));
		}
		
		String[] se = br.readLine().split(" ");
		
		int start = Integer.parseInt(se[0]);
		int end = Integer.parseInt(se[1]);
		
		topologySort(start);
		calculateCnt(start, end);
		
		System.out.println(dp[end]);
		System.out.println(result);
		
		

	}

}
