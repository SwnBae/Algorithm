import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[][] dp = new int[n + 1][4];

		dp[1][0] = 0;

		if (n > 1) {
			dp[2][1] = 1;
			dp[2][2] = 1;
			dp[2][3] = 1;
			dp[2][0] = 3;
			
			
		}
		
		if (n > 2) {
			dp[2][1] = 1;
			dp[2][2] = 1;
			dp[2][3] = 1;
			dp[2][0] = 3;
			
			dp[3][1] = 1;
			dp[3][3] = 1;
		}

		for (int i = 4; i <= n; i++) {
			if (i % 2 == 0) {
				dp[i][1] = dp[i - 2][1] + dp[i - 2][2] + dp[i - 2][3] + dp[i - 1][3];
				dp[i][2] = dp[i - 2][1] + dp[i - 2][2] + dp[i - 2][3];
				dp[i][3] = dp[i][1];

				dp[i][0] = dp[i][1] + dp[i][2] + dp[i][3];
			} else {
				dp[i][1] = dp[i - 1][3];
				dp[i][3] = dp[i - 1][1];
			}
		}
		
		System.out.println(dp[n][0]);

	}

}
