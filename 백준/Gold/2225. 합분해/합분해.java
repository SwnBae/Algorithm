import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        long[][] dp = new long[n+1][k+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if(i == 1){
                    dp[i][j] = j;
                    continue;
                }

                if(k == 1){
                    dp[i][j] = 1;
                    continue;
                }

                long temp = 0;

                for(int p = 1; p<=j; p++){
                    temp = (temp + dp[i-1][p]) % 1000000000;
                }
                dp[i][j] = temp % 1000000000;
            }
        }

        System.out.println(dp[n][k]);
    }
}