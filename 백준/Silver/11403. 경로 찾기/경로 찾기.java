import java.io.*;

public class Main {
	public static int[][] cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		cost = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				cost[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(cost[i][k] == 1 && cost[k][j] == 1) {
						cost[i][j] = 1;
					}
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(j > 0) sb.append(" ");
				sb.append(cost[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

}
