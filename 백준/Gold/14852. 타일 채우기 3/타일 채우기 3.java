import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		long[] dp = new long[1000001];
		long[] memo = new long[1000001];
		memo[0] = 2;
		memo[1] = 6;
		memo[2] = 20;
		
		dp[1] = 2;
		dp[2] = 7;
		
		for(int i = 3; i <= n; i++) {
			dp[i] = (3 * dp[i-2] + 2 * dp[i-1] + memo[i - 3]) % 1000000007;
			
			memo[i] = (memo[i - 1] + 2 * dp[i]) % 1000000007;
		}
		
		System.out.println(dp[n]);
	}
}
