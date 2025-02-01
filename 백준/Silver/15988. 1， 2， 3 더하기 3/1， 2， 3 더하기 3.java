import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        int[] tCase = new int[t];
        int size = 0;

        for (int i = 0; i < t; i++) {
            tCase[i] = Integer.parseInt(br.readLine());
            size = Math.max(size, tCase[i]);
        }

        long[] dp = new long[size + 1];

        dp[1] = 1;

        if (size == 2) {
            dp[2] = 2;
        } else if (size == 3) {
            dp[3] = 4;
        } else{
            dp[2] = 2;
            dp[3] = 4;
        }

        for (int i = 4; i <= size; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
        }

        for(int i = 0; i < t; i++) {
            System.out.println(dp[tCase[i]] % 1000000009);
        }
    }
}