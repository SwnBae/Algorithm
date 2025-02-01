import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        int[][] input = new int[t+1][4];
        int[][] dp = new int[t+1][4];

        for(int i = 1; i <= t; i++) {
            String[] line = br.readLine().split(" ");
            input[i][1] = Integer.parseInt(line[0]);
            input[i][2] = Integer.parseInt(line[1]);
            input[i][3] = Integer.parseInt(line[2]);
        }

        dp[1][1] = input[1][1];
        dp[1][2] = input[1][2];
        dp[1][3] = input[1][3];

        for(int i = 2; i <= t; i++) {
            dp[i][1] = Math.min(dp[i-1][2],dp[i-1][3]) + input[i][1];
            dp[i][2] = Math.min(dp[i-1][1],dp[i-1][3]) + input[i][2];
            dp[i][3] = Math.min(dp[i-1][1],dp[i-1][2]) + input[i][3];
        }

        System.out.println(Math.min(dp[t][1],Math.min(dp[t][2],dp[t][3])));

    }
}