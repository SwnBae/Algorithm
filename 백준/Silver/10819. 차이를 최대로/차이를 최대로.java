import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int n;
	public static boolean[] visited;
	public static int[] tmp;
	public static int[] arr;
	public static int max;
	
	public static void dfs(int cnt) {
		if(cnt == n) {
			int num = 0;
			
			for(int i = 0; i < n - 1; i++) {
				num += Math.abs(tmp[i] - tmp[i + 1]);
			}
			
			max = Math.max(max, num);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			tmp[cnt] = arr[i];
			dfs(cnt+1);
			visited[i] = false;
			
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		tmp = new int[n];
		visited = new boolean[n];
		
		String[] input = br.readLine().split(" ");
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		
		dfs(0);
		
		System.out.println(max);
	}

}
