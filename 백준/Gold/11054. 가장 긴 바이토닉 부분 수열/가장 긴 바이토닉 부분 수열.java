import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        int[] arr = new int[n];
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        int max = dp[0][0] = dp[0][1] = 1;

        for (int i = 1; i < n; i++) {
            int tempUp = 0;
            int tempDown = 0;

            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[j][0] > tempUp) {
                    tempUp = dp[j][0];
                } else if ((arr[i] < arr[j]) && (dp[j][0] > tempDown || dp[j][1] > tempDown)) {
                    tempDown = Math.max(dp[j][0], dp[j][1]);
                }
            }

            dp[i][0] = tempUp + 1;
            dp[i][1] = tempDown + 1;
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
            //System.out.println(arr[i] + ", up:" + dp[i][0] + " down:" + dp[i][1]);
        }

        System.out.println(max);
    }


}