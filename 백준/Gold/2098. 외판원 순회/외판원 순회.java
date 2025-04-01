import java.io.*;
import java.util.Arrays;

public class Main {
	public static int[][] cost;
	public static int[][] dp;
	public static int n;
	public static int result;
	
	public static void dfs(int now, int visited) {
		if(visited == ((1 << n) - 1)) {
			if(cost[now][0] == 0) return;
			int tmp = dp[now][visited] + cost[now][0];
			result = Math.min(result, tmp);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			int visit = (1 << i);
			int nxtVisit = (visited | visit);
			
			if(nxtVisit == visited) continue;
			if(cost[now][i] == 0) continue;
			
			if(dp[now][visited] + cost[now][i] < dp[i][nxtVisit]) {
				dp[i][nxtVisit] = dp[now][visited] + cost[now][i];
				dfs(i, nxtVisit);
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		cost = new int[n][n];
		dp = new int[n][1 << n];
		result = Integer.MAX_VALUE;
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			for(int j = 0; j < n; j++) {
				cost[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		dp[0][1] = 0;
		dfs(0,1);
		
		System.out.println(result);
	}
}
