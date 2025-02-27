import java.io.*;
import java.util.*;

public class Main {
	public static List<List<Integer>> graph;
	public static Queue<Integer> ans;
	public static int n;
	public static boolean[] visited;
	public static boolean isAns;
	
	public static void dfs(int node) {
		if(visited[node] || !isAns) return;
		
		visited[node] = true;
		
		List<Integer> nxtList = graph.get(node);
		Set<Integer> set = new HashSet<>();
		
		for(int n : nxtList) {
			if(!visited[n]) {
				set.add(n);
			}
		}
		
		while(!set.isEmpty()) {
			int peeknd = ans.peek();
			
			if(!set.contains(peeknd)) {
				isAns = false;
				return;
			}
			
			set.remove(peeknd);
			
			dfs(ans.poll());
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		ans = new ArrayDeque<>();
		visited = new boolean[n+1];

		graph = new ArrayList<>();

		isAns = true;

		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 1; i < n; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		String[] input = br.readLine().split(" ");

		for (int i = 0; i < n; i++) {
			ans.add(Integer.parseInt(input[i]));
		}
		
		if(ans.poll() == 1) {
			dfs(1);
		} else {
			isAns = false;
		}
		
		System.out.println(isAns ? 1 : 0);

	}

}
