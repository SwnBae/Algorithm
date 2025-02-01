import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] arr = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        int max = dp[0] = arr[0];

        for (int i = 1; i < n; i++) {
            int maxTemp = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[j] > maxTemp) {
                    maxTemp = dp[j];
                }
            }
            dp[i] = arr[i] + maxTemp;
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
    }
}