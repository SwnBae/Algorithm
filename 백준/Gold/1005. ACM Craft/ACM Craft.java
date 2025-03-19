import java.util.*;
import java.io.*;

public class Main {
	public static int totalTime;
	public static int[] time;
	public static List<List<Integer>> graph;
	public static int[] degree;
	public static int[] dp;
	
	public static int n;
	public static int k;
	
	public static int end;
	
	
	//로직 최종으로, 해당 dp[end] + time[end] 해줘야함
	public static void calculate() {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		for(int i = 1; i <= n; i++) {
			if(degree[i] == 0) {
				queue.add(i);
			}
		}
		
		for(int nd : queue) {
			topologySort(nd);
		}
	}
	
	public static void topologySort(int nd) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		queue.add(nd);
		
		for(int i = 1; i <= n; i++) {
			if(queue.isEmpty()) {
				//이 조건은 주어지지 않음
				return;
			}
			
			if(queue.size() >= 1) {
				int size = queue.size();
				
				for(int s = 0; s < size; s++) {
					int x = queue.pop();
					
					for(int next : graph.get(x)) {
						degree[next]--;
						
						dp[next] = Math.max(dp[next], dp[x] + time[x]);
						
						if(degree[next] == 0) {
							queue.add(next);
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			String[] nk = br.readLine().split(" ");
			n = Integer.parseInt(nk[0]);
			k = Integer.parseInt(nk[1]);
			
			totalTime = 0;
			
			time = new int[n+1];
			degree = new int[n+1];
			dp = new int[n+1];
			graph = new ArrayList<>();
			
			for(int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}
			
			String[] tm = br.readLine().split(" ");
			for(int i = 1; i <= n; i++) {
				time[i] = Integer.parseInt(tm[i-1]);
			}
			
			for(int i = 0; i < k; i++) {
				String[] ab = br.readLine().split(" ");
				
				int a = Integer.parseInt(ab[0]);
				int b = Integer.parseInt(ab[1]);
				
				graph.get(a).add(b);
				
				degree[b]++;
			}
			
			end = Integer.parseInt(br.readLine());
			
			calculate();
			
			System.out.println(dp[end] + time[end]);
		}

	}

}
