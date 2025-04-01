import java.io.*;
import java.util.*;

public class Main {
	public static List<List<Integer>> graph;
	public static long[] tree;
	
	public static int[] in;
	public static int[] out;
	public static int[] degree;
	
	public static boolean[] visited;
	public static int cnt;
	
	public static void dfs(int node, int depth) {
		visited[node] = true;
		in[node] = ++cnt;
		degree[node] = depth;
		
		for(int next : graph.get(node)) {
			if(visited[next]) continue;
			
			dfs(next, depth + 1);
		}
		
		out[node] = cnt;
	}
	
	public static void update(int node, int start, int end, int idx) {
		if(idx > end || idx < start) return;
		
		if(start == end) {
			tree[node]++;
			return;
		}
		
		int mid = (start + end) / 2;
		
		update(node * 2, start, mid, idx);
		update(node * 2 + 1, mid + 1, end, idx);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	public static long sum(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 0;
		
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		
		return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
	}
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] nc = br.readLine().split(" ");
		int n = Integer.parseInt(nc[0]);
		int c = Integer.parseInt(nc[1]);
		
		tree = new long[4 * n + 1];
		in = new int[n + 1];
		out = new int[n + 1];
		degree = new int[n + 1];
		visited = new boolean[n + 1];
		cnt = 0;
		
		graph = new ArrayList<>();
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < n- 1; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		dfs(c,1);
		
		int m = Integer.parseInt(br.readLine());

		for(int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			int op = Integer.parseInt(input[0]);
			int node = Integer.parseInt(input[1]);
			
			if(op == 1) {
				update(1, 1, n, in[node]);
			} else {
				long result = sum(1, 1, n, in[node], out[node]) * degree[node];
				sb.append(result + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
