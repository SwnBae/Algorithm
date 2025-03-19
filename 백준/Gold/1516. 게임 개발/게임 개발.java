import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int[] cost;
	public static int[] degree;
	public static int[] dp;
	public static List<List<Integer>> graph;
	
	public static void topologySort() {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		for(int i = 1; i <= n; i++) {
			if(degree[i] == 0) {
				dp[i] = cost[i];
				queue.add(i);
			}
		}
		
		for(int i = 1; i <= n; i++) {
			//이 조건은 주어지지 않는다.
			if(queue.isEmpty()) {
				return;
			}
			
			int node = queue.poll();
			
			for(int next : graph.get(node)) {
				degree[next]--;
				dp[next] = Math.max(dp[next], dp[node] + cost[next]);
				
				if(degree[next] == 0) {
					queue.add(next);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		dp = new int[n+1];
		degree = new int[n+1];
		cost = new int[n+1];
		
		graph = new ArrayList<>();
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= n; i++) {
			String[] input = br.readLine().split(" ");
			int inputCnt = 1;
			
			cost[i] = Integer.parseInt(input[0]);
			
			while(!input[inputCnt].equals("-1")) {
				int b = Integer.parseInt(input[inputCnt]);
				
				graph.get(b).add(i);
				degree[i]++;
				
				inputCnt++;
			}
		}
		
		topologySort();
		
		for(int i = 1; i <= n; i++) {
			System.out.println(dp[i]);
		}

	}

}
