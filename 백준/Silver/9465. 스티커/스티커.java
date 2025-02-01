import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            int n = Integer.parseInt(br.readLine());

            int[][] cost = new int[3][n + 1];
            int[][] dp = new int[3][n + 1];

            for (int i = 1; i <= 2; i++) { // 배열 받기
                String[] input = br.readLine().split(" ");

                for (int p = 1; p <= input.length; p++) {
                    cost[i][p] = Integer.parseInt(input[p - 1]);
                }
            }

            dp[0][1] = 0;
            dp[1][1] = cost[1][1];
            dp[2][1] = cost[2][1];

            for (int i = 2; i <= n; i++) {
                dp[0][i] = Math.max(dp[0][i - 1], Math.max(dp[1][i - 1], dp[2][i - 1]));
                dp[1][i] = Math.max(dp[0][i - 1], dp[2][i - 1]) + cost[1][i];
                dp[2][i] = Math.max(dp[0][i - 1], dp[1][i - 1]) + cost[2][i];
            }

            System.out.println(Math.max(dp[0][n], Math.max(dp[1][n], dp[2][n])));
        }
    }
}