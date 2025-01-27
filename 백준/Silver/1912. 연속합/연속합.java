import java.io.*;

public class Main { //Kadane's Algorithm
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        int maxResult = arr[0];
        int currentSum = arr[0];

        for (int i = 1; i < n; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]); // 현재 요소 포함 여부 결정
            maxResult = Math.max(maxResult, currentSum); // 최대값 갱신
        }

        System.out.println(maxResult);
    }
}