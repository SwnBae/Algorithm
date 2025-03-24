import java.io.*;
import java.util.Arrays;

public class Main {
	public static int[][] cost;
	public static int n;
	public static int m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		cost = new int[n + 1][n + 1];
		
		for(int i = 0; i < m; i++) {
			String[] ab = br.readLine().split(" ");
			int a = Integer.parseInt(ab[0]);
			int b = Integer.parseInt(ab[1]);
			
			cost[a][b] = 1;
			cost[b][a] = 1;
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(i == j) continue;
					
					if(cost[i][k] != 0 && cost[k][j] != 0 && ((cost[i][j] > cost[i][k] + cost[k][j]) || (cost[i][j] == 0))) {
						cost[i][j] = cost[i][k] + cost[k][j];
					}
				}
			}
		}
		
		int max = Integer.MAX_VALUE;
		int result = -1;
		
		for(int i = 1; i <= n; i++) {
			int sum = 0;
			for(int j = 1; j <= n; j++) {
				sum += cost[i][j];
			}
			
			if(max > sum) {
				max = sum;
				result = i;
			}
		}
		
		System.out.println(result);
	}
}
