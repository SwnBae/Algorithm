//1. dfs 완탐

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static StringBuilder sb = new StringBuilder();
	public static boolean[] visited;
	public static int n;
	public static int[] output;
	
	public static void dfs(int cnt) {
		if(cnt == n) {
			for(int i = 0; i < n; i++) {
				if(i > 0) sb.append(" ");
				sb.append(output[i]);
			}
			sb.append("\n");
		}
		
		for(int i = 1; i <= n; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			output[cnt] = i;
			dfs(cnt + 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n + 1];
		output = new int[n];
		
		dfs(0);
		
		System.out.println(sb.toString());

	}

}
