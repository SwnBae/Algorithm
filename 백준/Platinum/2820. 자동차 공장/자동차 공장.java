import java.io.*;
import java.util.*;

public class Main {
	public static List<List<Integer>> graph;
	public static int[] tree;
	public static int[] lazy;

	public static int[] in;
	public static int[] out;
	public static int[] idx;
	public static int[] arr;

	public static boolean[] visited;
	public static int cnt;

	public static void dfs(int node) {
		visited[node] = true;
		in[node] = ++cnt;
		idx[in[node]] = node;
		
		for(int next : graph.get(node)) {
			if(visited[next]) continue;
			
			dfs(next);
		}
		
		out[node] = cnt;
	}
	
	public static int init(int node, int start, int end) {
		if(start == end) return tree[node] = arr[idx[start]];
		
		int mid = (start + end) / 2;
		
		return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
	}
	
	public static void update(int node, int start, int end, int left, int right, int diff) {
		lazy_propagation(node, start, end);
		
		if(left > end || right < start) return;
		
		if(left <= start && end <= right) {		
			tree[node] += (end - start + 1) * diff;
			
			if(start != end) {
				lazy[node * 2] += diff;
				lazy[node * 2 + 1] += diff;
			}
			
			return;
		}
		
		int mid = (start + end) / 2;
		
		update(node * 2, start, mid, left, right, diff);
		update(node * 2 + 1, mid + 1, end, left, right, diff);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	public static int pick(int node, int start, int end, int idx) {
		lazy_propagation(node, start, end);
		
		if(start == end) return tree[node];
		
		int mid = (start + end) / 2;
		
		if(idx <= mid) {
			return pick(node * 2, start, mid, idx);
		} else {
			return pick(node * 2 + 1, mid + 1, end, idx);
		}
	}
	
	public static void lazy_propagation(int node, int start, int end) {
		if(lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];
			
			if(start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			
			lazy[node] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] nm = br.readLine().split(" ");

		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);

		tree = new int[(4 * n) + 1];
		lazy = new int[(4 * n) + 1];
		
		in = new int[n + 1];
		out = new int[n + 1];
		idx = new int[n + 1];
		arr = new int[n + 1];
		
		cnt = 0;
		visited = new boolean[n + 1];
		graph = new ArrayList<>();
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			if(i == 0) {
				arr[i + 1] = Integer.parseInt(input[0]);
				continue;
			}
			
			int cost = Integer.parseInt(input[0]);
			int node = Integer.parseInt(input[1]);
			
			graph.get(node).add(i+1);
			arr[i + 1] = cost;
		}
		
		dfs(1);
		init(1,1,n);
		
		for(int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			int node = Integer.parseInt(input[1]);
			
			if(input[0].charAt(0) == 'p') {
				int cost = Integer.parseInt(input[2]);
				
				update(1, 1, n, in[node], out[node], cost);
				update(1, 1, n, in[node], in[node], -cost);
			} else {
				sb.append(pick(1, 1, n, in[node]) + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
