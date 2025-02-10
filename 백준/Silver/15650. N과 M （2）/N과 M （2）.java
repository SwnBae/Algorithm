import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main { //백트래킹 2번, 오름차순으로 정렬하자.
	
	public static boolean[] visited;
	public static int[] output;
	public static int n;
	public static int m;
	
	public static StringBuilder sb = new StringBuilder();
	
	public static void dfs(int cnt, int preValue) {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				if(i > 0) sb.append(" "); // 마지막엔 공백을 삽입하지 않음
			    sb.append(output[i]);
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(visited[i] || i < preValue) continue;
			visited[i] = true;
			output[cnt] = i;
			dfs(cnt+1, i);
			visited[i] = false;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		String[] input = br.readLine().split(" ");
		
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		
		visited = new boolean[n+1];
		output = new int[m];
		
		dfs(0,0);
		
		System.out.println(sb.toString());
	}
}
