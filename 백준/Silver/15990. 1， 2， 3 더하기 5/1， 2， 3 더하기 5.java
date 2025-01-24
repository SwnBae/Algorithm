import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		long[][] arr = new long[100001][4]; // 3개 더할 때 오버플로우 발생하므로, long형으로 바꿔줘야함
		
		int[] input = new int[n];
		
		arr[1][0] = 1;
		arr[1][1] = 1;
		arr[1][2] = 0;
		arr[1][3] = 0;
		
		arr[2][0] = 1;
		arr[2][1] = 0;
		arr[2][2] = 1;
		arr[2][3] = 0;
		
		arr[3][0] = 3;
		arr[3][1] = 1;
		arr[3][2] = 1;
		arr[3][3] = 1;
		
		int max = -1;
		
		for(int c = 0; c<n; c++) {
			input[c] = Integer.parseInt(br.readLine());
			max = Math.max(max, input[c]);
		}
		
		for(int i = 4; i<=max; i++) {
			arr[i][1] = (arr[i-1][2] + arr[i-1][3]) % 1000000009;
			arr[i][2] = (arr[i-2][1] + arr[i-2][3])% 1000000009;
			arr[i][3] = (arr[i-3][1] + arr[i-3][2])% 1000000009;
			
			arr[i][0] = (arr[i][1] + arr[i][2] + arr[i][3]) % 1000000009;
		}
		
		for(int c = 0; c<input.length; c++) {
			System.out.println(arr[input[c]][0]);
		}

	}

}
