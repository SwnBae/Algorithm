import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n + 1];
		int[][] dp = new int[n + 1][n + 1]; // 구간
		
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			arr[i + 1] = Integer.parseInt(input[i]);
		}
		
		for(int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }

        for(int i = 1; i < n; i++) {
            if(arr[i] == arr[i + 1]) dp[i][i + 1] = 1;
        }
        
        for(int i = 2; i <= n; i++) { //길이
        	for(int left = 1; left + i <= n; left++) {
        		int right = left + i;
        		
        		if(arr[left] == arr[right] && dp[left + 1][right - 1] == 1) {
        			dp[left][right] = 1;
        		}
        	}
        }
		
		int m = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < m; i++) {
			String[] ab = br.readLine().split(" ");
			int a = Integer.parseInt(ab[0]);
			int b = Integer.parseInt(ab[1]);
			
			sb.append(dp[a][b] + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
