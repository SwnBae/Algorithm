import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[][] dp = new long[n + 1][10];

        int result = 0;

        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                if(j == 0){
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 10007;
            }
        }

        for (int i = 0; i <= 9; i++) {
            result += dp[n][i] % 10007;
        }

        System.out.println(result % 10007);
    }
}