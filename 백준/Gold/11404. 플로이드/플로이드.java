import java.io.*;
import java.util.Arrays;

public class Main {
	public static final long INF = 10000000001L;
	public static long[][] cost;
	public static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		int m = Integer.parseInt(br.readLine());
		cost = new long[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			Arrays.fill(cost[i], INF);
			
			cost[i][i] = 0;
		}
		
		for(int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			
			cost[a][b] = Math.min(cost[a][b], c);
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(j > 1) sb.append(" ");
				if(cost[i][j] == INF) {
					sb.append(0);
				} else {
					sb.append(cost[i][j]);
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}

}
