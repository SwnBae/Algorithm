import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main { //백트래킹 기본 문제, output을 수단으로 출력하자.
	
	public static boolean[] visited;
	public static int[] output;
	public static int n;
	public static int m;
	
	public static StringBuilder sb = new StringBuilder();
	
	public static void dfs(int cnt) {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				sb.append(output[i] + " ");
			}
			sb.append("\n");
		}
		
		for(int i = 1; i <= n; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			output[cnt] = i;
			dfs(cnt+1);
			visited[i] = false;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		String[] input = br.readLine().split(" ");
		
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		
		visited = new boolean[n+1];
		output = new int[n+1];
		
		dfs(0);
		
		System.out.println(sb.toString());
	}
}
