import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n+1];
		
		String[] input = br.readLine().split(" ");
		
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(input[i-1]);
		}
		
		for(int i = 2; i<=n; i++) {
			for(int j = 1; j <= i/2; j++) {
				arr[i] = Math.min(arr[i], arr[i-j] + arr[j]);
			}
		}

		System.out.println(arr[n]);
	}

}
