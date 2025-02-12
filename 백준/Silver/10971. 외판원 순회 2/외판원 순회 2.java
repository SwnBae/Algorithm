import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static int n;
	public static int[][] cost;
	public static int[] channel;
	public static boolean[] visited;
	public static int min = Integer.MAX_VALUE;
	
	public static void dfs(int cnt) {
		if(cnt == n) {
			int tmpCost = 0;
			
			for(int i = 0; i < n; i++) {
				if(cost[channel[i % n]][channel[(i+1) % n]] == 0) { // 못 가는 경우
					//System.out.println(channel[i % n] + "," + channel[(i+1) % n]);
					return;
				}
				
				tmpCost += cost[channel[i % n]][channel[(i+1) % n]];
			}
			
//			for(int i = 0; i < n - 1; i++) {
//				if(cost[channel[i]][channel[i+1]] == 0) { // 못 가는 경우
//					return;
//				}
//				tmpCost += cost[channel[i]][channel[i+1]];
//			}
//			
//			if (cost[channel[n - 1]][channel[0]] == 0) {
//			    return;
//			}
//			
//			tmpCost += cost[channel[n-1]][channel[0]];
			
			
			min = Math.min(min, tmpCost);
			
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			channel[cnt] = i + 1;
			dfs(cnt + 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		cost = new int[n+1][n+1];
		channel = new int[n];
		visited = new boolean[n];
		
		for(int i = 1; i <= n; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 1; j <= n; j++) {
				cost[i][j] = Integer.parseInt(input[j - 1]);
			}
		}
		
		dfs(0);
		
		System.out.println(min);

	}

}
