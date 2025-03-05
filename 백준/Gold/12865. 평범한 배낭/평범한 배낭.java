import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        int[] weight = new int[n + 1];
        int[] value = new int[n + 1];
        int[][] dp = new int[n + 1][k + 1]; // 가치

        for (int i = 1; i <= n; i++) {
            String[] wv = br.readLine().split(" ");
            weight[i] = Integer.parseInt(wv[0]);
            value[i] = Integer.parseInt(wv[1]);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) { // 무게
                if (weight[i] > j) {//물건 무게 > 배낭 무게 => continue;
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], value[i] + dp[i - 1][j - weight[i]]);
                }

            }
        }

        System.out.println(dp[n][k]);
    }
}