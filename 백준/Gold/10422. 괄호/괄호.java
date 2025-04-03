import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		long[] dp = new long[5001];
		dp[0] = 1;
		dp[2] = 1;
		
		for(int i = 4; i <= 5000; i+=2) {
			for(int j = 2; j <= i; j+=2) {
				dp[i] += dp[j - 2] * dp[i - j];
				dp[i] %= 1000000007;
			}
		}
		
		for(int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n] + "\n");
		}
		
		System.out.println(sb.toString());
	}

}
