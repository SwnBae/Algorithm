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

        int maxLength = dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int temp = 0;
            for (int j = 0; j < i; j++) {
                if(arr[i] < arr[j] && dp[j] > temp){
                    temp = dp[j];
                }

                dp[i] = temp + 1;
                maxLength = Math.max(dp[i],maxLength);
            }
        }

        System.out.println(maxLength);
    }
}