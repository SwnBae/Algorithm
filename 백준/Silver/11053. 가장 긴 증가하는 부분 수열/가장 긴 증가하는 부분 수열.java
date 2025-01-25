import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] str = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        int[] length = new int[n];
        int maxLength = length[0] = 1;

        // 0~i-1까지, arr 숫자가 현재보다 작음 + 길이가 가장 김
        for(int i = 1; i < n; i++){
            int tempMax = 0;

            for(int j = 0; j < i; j++){
                if(arr[j] < arr[i] && length[j] > tempMax){
                    tempMax = length[j];
                }
            }

            length[i] = tempMax + 1;
            maxLength = Math.max(maxLength, length[i]);
        }

        System.out.println(maxLength);
    }
}