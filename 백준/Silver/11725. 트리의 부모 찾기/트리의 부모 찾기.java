import java.io.*;
import java.util.*;

public class Main {
	public static int[] parent;
	public static List<List<Integer>> graph;
	public static int n;
	
	public static void dfs(int node, int preNode) {
		if(parent[node] != 0) return;
		
		parent[node] = preNode;
		
		for(int next : graph.get(node)) {
			if(parent[next] != 0) continue;
			
			dfs(next, node);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		parent = new int[n + 1];
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < n-1; i++) {
			String[] input = br.readLine().split(" ");
			
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		dfs(1, -1);
		for(int i = 2; i < n + 1; i++) {
			System.out.println(parent[i]);
		}

	}

}
