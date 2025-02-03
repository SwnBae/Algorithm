import java.io.*;

public class Main { // 1912번 다시 생각해보기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");

		int[] arr = new int[n];
		int[][] dp = new int[n][2];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}

		int result = dp[0][0] = arr[0]; // 제거하지 않은 경우
		dp[0][1] = 0; // 제거했을 경우

		for (int i = 1; i < n; i++) {
			dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
			dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);

			result = Math.max(result, Math.max(dp[i][0], dp[i][1]));
		}
		
		System.out.println(result);

	}

}
