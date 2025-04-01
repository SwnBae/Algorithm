import java.io.*;
import java.util.*;

public class Main {
	public static List<List<Integer>> graph;
	public static int[] tree;
	
	public static int[] in;
	public static int[] out;
	public static boolean[] visited;
	public static int cnt;
	
	public static void dfs(int node) {
		visited[node] = true;
		in[node] = ++cnt;
		
		for(int next : graph.get(node)) {
			if(visited[next]) continue;
			
			dfs(next);
		}
		
		out[node] = cnt;
	}
	
	public static void update(int node, int start, int end, int idx, int diff) {
		if(idx < start || idx > end) return;
		
		if(start == end) {
			tree[node] += diff;
			return;
		}
		
		int mid = (start + end) / 2;
		
		update(node * 2, start, mid, idx, diff);
		update(node * 2 + 1, mid + 1, end, idx, diff);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	public static int sum(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 0;
		
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		
		return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		
		graph = new ArrayList<>();
		tree = new int[4 * n + 1];
		in = new int[n + 1];
		out = new int[n + 1];
		visited = new boolean[n + 1];
		cnt = 0;
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		String[] input = br.readLine().split(" ");
		
		for(int i = 2; i <= n; i++) {
			graph.get(Integer.parseInt(input[i - 1])).add(i);
		}
		
		dfs(1);
		
		for(int i = 0; i < m; i++) {
			String[] query = br.readLine().split(" ");
			int option = Integer.parseInt(query[0]);
			int node = Integer.parseInt(query[1]);
			
			if(option == 1) {
				int cost = Integer.parseInt(query[2]);
				
				update(1, 1, n, in[node], cost);
			} else {
				sb.append(sum(1,1,n, in[node], out[node]) + "\n");
			}
		}
		
		System.out.println(sb.toString());

	}

}
