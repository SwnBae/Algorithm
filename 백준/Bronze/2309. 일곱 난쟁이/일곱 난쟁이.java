import java.io.*;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] arr = new int[9];
		int sum = 0;
		int[] result = new int[7];
		int count = 0;
		boolean check = false;
		
		int x = 0;
		int y = 0;

		for(int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		
		for(int i = 0; i < 9; i++) {
			for(int j = i+1; j < 9; j++) {
				if(sum - (arr[i] + arr[j]) == 100) {
					check = true;
					x = i;
					y = j;
					break;
				}
				if(check) break;
			}
			if(check) break;
		}
		
		
		
		if(check) {
			for(int p = 0; p < 9; p++) {
				if(p == x || p == y) {
					continue;
				}
				result[count++] = arr[p];
			}
		}
		
		
		Arrays.sort(result);
		
		for(int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

}
