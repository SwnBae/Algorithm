import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int[] arr, prefixSum;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for (int t = 1; t <= tc; t++) {
            n = Integer.parseInt(br.readLine());
            arr = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

            dp = new int[n][n];
            prefixSum = new int[n + 1];  // 구간 합 저장용

            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + arr[i];
            }

            // DP 초기화
            for (int len = 2; len <= n; len++) { // 부분 배열 길이
                for (int left = 0; left + len - 1 < n; left++) {
                    int right = left + len - 1;
                    dp[left][right] = Integer.MAX_VALUE;

                    for (int k = left; k < right; k++) { // 가능한 모든 분할 지점 탐색
                        int cost = dp[left][k] + dp[k + 1][right] + (prefixSum[right + 1] - prefixSum[left]);
                        dp[left][right] = Math.min(dp[left][right], cost);
                    }
                }
            }

            System.out.println(dp[0][n - 1]);
        }
    }
}
