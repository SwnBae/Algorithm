import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int cnt = 0;
		boolean[][] space = new boolean[100][100];
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			
			for(int p = y; p < y+10; p++) {
				for(int j = x; j < x + 10; j++) {
					space[p][j] = true;
				}
			}
		}
		
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(space[i][j]) cnt++;
			}
		}
		
		System.out.println(cnt);

	}

}
