import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[31];
		
		dp[1] = 0;
		dp[2] = 3;
		
		for(int i = 4; i <= n; i+=2) {
			dp[i] = dp[i-2] * 3 + 2;
			for(int j = 4; j <= i; j+=2) {
				dp[i] += 2 * dp[i-j];
			}
		}
		
		System.out.println(dp[n]);
	}
}
