import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int sum = 2;
		
		if(n == 1) {
			System.out.println(1);
			return;
		}
		if(n == 2) {
			System.out.println(2);
			return;
		}
		
		for(int i = 3; i <=n; i++) {
			for(int j = 1; j <= Math.sqrt(i); j++) {
				if(i % j != 0) {
					continue;
				}
				sum++;
			}
			
		}
		
		System.out.println(sum);

	}

}
