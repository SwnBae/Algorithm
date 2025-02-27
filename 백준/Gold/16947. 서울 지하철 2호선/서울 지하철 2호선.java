import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {
	public static int n;
	public static List<List<Integer>> graph;
	public static boolean[] visited;
	public static boolean[] haveCycle;
	public static int[] parents;

	public static void dfs(int parent, int current) {
//		System.out.println(Arrays.toString(parents));
		if (visited[current])
			return;

		visited[current] = true;

		for (int node : graph.get(current)) {
			if (node == parent)
				continue;

			if (visited[node] && node != parent) {
				int startCycle = node;
				int endCycle = current;
				
				
				findCycle(startCycle, endCycle);
				//return 넣으면 무한루프, 다른 재귀에서, 이미 방문한 노드를 탐색할 가능성이 있다.
			}

			parents[node] = current;
			dfs(current, node);
		}
	}

	public static void findCycle(int st, int end) {
		int node = end;

		while (node != st) {
//			System.out.println(node + ", " + st);
			haveCycle[node] = true;
			node = parents[node];
		}

		haveCycle[node] = true;
		return;
	}
	
	public static int bfs(int node) {
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.add(new int[] {node, -1, 0});
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			
			int nd = tmp[0];
			int pr = tmp[1];
			int cnt = tmp[2];
			
			if(haveCycle[nd]) {
				return cnt;
			}
			
			for(int next : graph.get(nd)) {
				if(next == pr) continue;
				
				queue.add(new int[] {next,nd,cnt + 1});
			}
		}
		
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		visited = new boolean[n + 1];
		haveCycle = new boolean[n + 1];
		parents = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}

		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");

			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		dfs(-1, 1);

		for(int i = 1; i <= n; i++) {
			if(i > 1) sb.append(" ");
			
			if(haveCycle[i]) {
				sb.append(0);
			} else {
				sb.append(bfs(i));
			}
		}
		
		System.out.println(sb.toString());

	}

}
