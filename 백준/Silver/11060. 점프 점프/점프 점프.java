import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int[] dp = new int[n];

		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 0; i < n; i++) {
			if (dp[i] == Integer.MAX_VALUE)
				continue; // 도달 불가능한 위치는 패스

			// i에서 점프 가능한 모든 위치 갱신
			for (int j = 1; j <= arr[i] && i + j < n; j++) {
				dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
			}
		}

		System.out.println(dp[n - 1] == Integer.MAX_VALUE ? -1 : dp[n - 1]);
	}
}
