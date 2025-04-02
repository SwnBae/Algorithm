import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] day = new int[n + 2];
		int[] cost = new int[n + 2];
		int[] dp = new int[n + 2];
		
		for(int i = 1; i <= n; i++) {
			String[] input = br.readLine().split(" ");
			
			int d = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			
			day[i] = d;
			cost[i] = c;
		}
		
		int max = -1;
		
		for(int i = 1; i <= n + 1; i++) {
			if(max < dp[i]) {
				max = dp[i];
			}
			
			int nxtDay = i + day[i];
			if(nxtDay < n + 2) {
				dp[nxtDay] = Math.max(dp[nxtDay], cost[i] + max);
			}
		}
		
		System.out.println(dp[n + 1]);
	}
}
