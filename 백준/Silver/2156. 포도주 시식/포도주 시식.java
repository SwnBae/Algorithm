import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] cost = new int[n + 1];
        int[][] dp = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(br.readLine());
        }

        dp[1][0] = 0;
        dp[1][1] = cost[1];
        if (n > 1) {
            dp[2][0] = Math.max(dp[1][0], dp[1][1]);
            dp[2][1] = Math.max(dp[1][0], dp[1][1]) + cost[2];
        }

        for (int i = 3; i <= n; i++) {
            dp[i][1] = Math.max(dp[i - 2][0] + cost[i - 1], dp[i - 2][1]) + cost[i]; // 마시는 경우
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]); // 안마시는 경우
        }

        System.out.println(Math.max(dp[n][0], dp[n][1]));
    }
}