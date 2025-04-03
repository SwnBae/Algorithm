import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nsm = br.readLine().split(" ");
		
		int n = Integer.parseInt(nsm[0]);
		int s = Integer.parseInt(nsm[1]);
		int m = Integer.parseInt(nsm[2]);
		
		int[] arr = new int[n + 1];
		boolean[][] dp = new boolean[n + 1][m + 1];
		
		String[] input = br.readLine().split(" ");
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(input[i - 1]);
		}
		
		dp[0][s] = true;
		
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j <= m; j++) {
				if(dp[i - 1][j]) {
					if(j + arr[i] <= m) {
						dp[i][j + arr[i]] = true;
					}
					if(j - arr[i] >= 0) {
						dp[i][j - arr[i]] = true;
					}
				}
			}
		}
		
		int result = -1;
		
		for(int i = 0; i <= m; i++) {
			if(!dp[n][i]) continue;
			
			result = Math.max(result, i);
		}
		
		System.out.println(result);

	}

}
