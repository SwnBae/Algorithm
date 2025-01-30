import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n+1];

        for(int i = 1; i <= n; i++){
            if(Math.sqrt(i) % 1 == 0){
                arr[i] = 1;
            } else {
                int temp = 2147483647;
                for(int j = 1; j <= i/2; j++){
                    temp = Math.min(temp, arr[i-j] + arr[j]);
                }
                arr[i] = temp;
            }
        }

        System.out.println(arr[n]);
    }
}