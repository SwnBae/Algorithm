import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nk = br.readLine().split(" ");
		int n = Integer.parseInt(nk[0]);
		int k = Integer.parseInt(nk[1]);
		int[] cost = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			cost[i] = Integer.parseInt(br.readLine());
		}

		int[][] dp = new int[n + 1][k + 1];

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= k; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		dp[0][0] = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= k; j++) {
				dp[i][j] = dp[i - 1][j];
				
				if (j >= cost[i] && dp[i][j - cost[i]] != Integer.MAX_VALUE){
					dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - cost[i]] + 1);
				}
			}
		}

		if (dp[n][k] == Integer.MAX_VALUE) {
            System.out.println("-1"); 
        } else {
            System.out.println(dp[n][k]);
        }
	}
}
