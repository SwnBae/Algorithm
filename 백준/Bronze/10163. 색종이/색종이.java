import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static final int SIZE = 1001;
	public static int space[][];
	public static int[] size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		space = new int[SIZE][SIZE];
		size = new int[n + 1];
		
		for(int i = 1; i <= n; i++) {
			String[] input = br.readLine().split(" ");
			int y = Integer.parseInt(input[0]);
			int x = Integer.parseInt(input[1]);
			int h = Integer.parseInt(input[2]);
			int w = Integer.parseInt(input[3]);
			
			for(int j = y; j < y+h; j++) {
				for(int p = x; p < x+w; p++) {
					if(space[j][p] != 0) {
						size[space[j][p]]--;
						space[j][p] = i;
						size[i]++;
					}else {
						space[j][p] = i;
						size[i]++;
					}
				}
				
			}
		}
		
		for(int t= 1; t<=n; t++) {
			System.out.println(size[t]);
		}	

	}

}
