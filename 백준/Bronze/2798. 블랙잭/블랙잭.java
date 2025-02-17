import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		
		int[] arr = new int[n];
		String[] input = br.readLine().split(" ");
		int min = Integer.MAX_VALUE;
		boolean find = false;
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		
		for(int i = 0; i < n; i++) {
			if(find) break;
			
			for(int j = i+1; j < n; j++) {
				if(find) break;
				
				for(int p = j+1; p < n; p++) {
					int temp = m - (arr[i] + arr[j] + arr[p]);
					if(temp == 0) {
						find = true;
						min = 0;
						break;
					} else if(temp > 0 && temp < min) {
						min = temp;
					}
				}
			}
		}
		
		System.out.println(m - min);
	}

}
