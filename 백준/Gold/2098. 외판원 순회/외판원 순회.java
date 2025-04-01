import java.io.*;
import java.util.Arrays;

public class Main {
	public static final int INF = 16000001;
	public static int[][] cost;
	public static int[][] dp;
	public static int n;
	public static int result;
	
	public static int dfs(int now, int visited) {
		if(visited == ((1 << n) - 1)) {
			if(cost[now][0] == 0) return INF;
			
			return cost[now][0];
		}
		
		if(dp[now][visited] != -1) return dp[now][visited];
		dp[now][visited] = INF;
		
		for(int i = 0; i < n; i++) {
			if((visited & (1 << i)) !=0 || cost[now][i] == 0) continue;
			
			dp[now][visited] = Math.min(dp[now][visited], cost[now][i] + dfs(i, visited | (1 <<i)));
		}
		
		return dp[now][visited];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		cost = new int[n][n];
		dp = new int[n][1 << n];
		result = INF;
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			Arrays.fill(dp[i], -1);
			for(int j = 0; j < n; j++) {
				cost[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		System.out.println(dfs(0,1));
	}
}
