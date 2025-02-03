import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[][] dp = new int[n + 1][4];
		int[][] arr = new int[n + 1][4];

		int result = Integer.MAX_VALUE;

		for (int i = 1; i <= n; i++) {
			String[] input = br.readLine().split(" ");
			for (int p = 1; p <= 3; p++) {
				arr[i][p] = Integer.parseInt(input[p - 1]);
			}
		}

		dp[1][1] = arr[1][1];
		dp[1][2] = 1001;
		dp[1][3] = 1001;

		for (int i = 2; i <= n; i++) { // 1번 집이 R
			dp[i][1] = Math.min(dp[i - 1][2], dp[i - 1][3]) + arr[i][1];
			dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][3]) + arr[i][2];
			dp[i][3] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][3];
		}

		result = Math.min(result, Math.min(dp[n][2], dp[n][3]));

		dp[1][1] = 1001;
		dp[1][2] = arr[1][2];
		dp[1][3] = 1001;

		for (int i = 2; i <= n; i++) { // 1번 집이 G
			dp[i][1] = Math.min(dp[i - 1][2], dp[i - 1][3]) + arr[i][1];
			dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][3]) + arr[i][2];
			dp[i][3] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][3];
		}

		result = Math.min(result, Math.min(dp[n][1], dp[n][3]));

		dp[1][1] = 1001;
		dp[1][2] = 1001;
		dp[1][3] = arr[1][3];

		for (int i = 2; i <= n; i++) { // 1번 집이 B
			dp[i][1] = Math.min(dp[i - 1][2], dp[i - 1][3]) + arr[i][1];
			dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][3]) + arr[i][2];
			dp[i][3] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][3];
		}

		result = Math.min(result, Math.min(dp[n][1], dp[n][2]));

		System.out.println(result);

	}

}
