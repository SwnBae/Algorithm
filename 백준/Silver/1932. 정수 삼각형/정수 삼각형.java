import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String[] input = br.readLine().split(" ");
            for (int p = 1; p <= input.length; p++) {
                dp[i][p] = Integer.parseInt(input[p - 1]);
            }
        }

        int result = dp[1][1];

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1) {
                    dp[i][j] += dp[i - 1][j];
                } else if (j == i) {
                    dp[i][j] += dp[i - 1][j - 1];
                } else {
                    dp[i][j] += Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                }

                result = Math.max(dp[i][j], result);
            }
        }

        System.out.println(result);
    }
}