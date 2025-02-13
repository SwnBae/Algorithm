import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 가지치기를 통해 구현해보자, 애초에 경우의 수 제외
public class Main {
	
	public static int n;
	public static int[][] cost;
	public static int[] channel;
	public static boolean[] visited;
	public static int min = Integer.MAX_VALUE;
	
	public static void dfs(int cnt, int prev) {
		if(cnt == n) {
			if(cost[channel[n - 1]][channel[0]] == 0) { // 못 가는 경우
				return;
			}
			
			int tmpCost = 0;
			
			for(int i = 0; i < n; i++) {
				tmpCost += cost[channel[i % n]][channel[(i+1) % n]];
			}
			
			
			min = Math.min(min, tmpCost);
			
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i] || (cost[prev][i+1] == 0)) continue;
			
			visited[i] = true;
			channel[cnt] = i + 1;
			dfs(cnt + 1,channel[cnt]);
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
		
		for(int i = 0; i <=n; i++) {
			cost[0][i] = -1;
			cost[i][0] = -1;
		}
		
		dfs(0,0);
		
		System.out.println(min);

	}

}
