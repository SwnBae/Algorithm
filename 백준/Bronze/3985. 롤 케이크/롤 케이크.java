import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int len = Integer.parseInt(br.readLine());
		
		int[] cake = new int[len + 1];
		
		int n = Integer.parseInt(br.readLine());
		
		int[] customer = new int[n + 1];
		int preMaxIdx = 0;
		int preMax = 0;
		
		int maxIdx = 0;
		int max = 0;
		
		for(int i = 1; i < n + 1; i++) {
			String[] input = br.readLine().split(" ");
			int stIdx = Integer.parseInt(input[0]);
			int endIdx = Integer.parseInt(input[1]);
			
			int cnt = 0;
			
			if(preMax < endIdx - stIdx + 1) {
				preMax = endIdx - stIdx + 1;
				preMaxIdx = i;
			}
			
			for(int j = stIdx; j <= endIdx; j++) {
				if(cake[j] == 0) {
					cake[j] = i;
					cnt++;
				}
			}
			
			customer[i] = cnt;
			
			if(max < customer[i]) {
				max = customer[i];
				maxIdx = i;
			}
		}
		
		System.out.println(preMaxIdx);
		System.out.println(maxIdx);

	}

}
