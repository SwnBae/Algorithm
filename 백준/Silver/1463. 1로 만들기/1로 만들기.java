import java.io.*;
public class Main {

	public static int[] dp; // Bottom - Up, 크기 [n+1]은 안되는 이유?

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int input = Integer.parseInt(br.readLine());
		
		dp = new int[1000001];
		
		dp[0] = dp[1] = 0;
		dp[2] = dp[3] = 1;

		for(int i = 4; i <= input; i++) {
			if (i % 6 == 0) {
				dp[i] = Math.min(dp[i- 1], Math.min(dp[i/2], dp[i/3])) + 1;
			} else if (i % 3 == 0) {
				dp[i] = Math.min(dp[i - 1],  dp[i/3]) + 1;
			} else if (i % 2 == 0) {
				dp[i] = Math.min(dp[i - 1],  dp[i/2]) + 1;
			} else {
				dp[i] = dp[i - 1] + 1;
			}

		}
		
		System.out.println(dp[input]);
	}
}
