import java.io.*;

public class Main {
	public static int[][] space;
	public static int[][] collect;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		
		space = new int[n][m];
		collect = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < m; j++) {
				space[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		collect[0][0] = space[0][0];
		
		for(int i = 1; i < m; i++) {
			collect[0][i] = collect[0][i - 1] + space[0][i];
		}
		
		for(int i = 1; i < n; i++) {
			collect[i][0] = collect[i-1][0] + space[i][0];
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 1; j < m; j++) {
				collect[i][j] = Math.max(collect[i-1][j], Math.max(collect[i][j-1], collect[i-1][j-1]))
									+ space[i][j];
			}
		}
		
		System.out.println(collect[n-1][m-1]);

	}

}
