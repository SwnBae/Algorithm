import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int[] output;
	public static int n;
	public static int m;
	public static StringBuilder sb = new StringBuilder();
	
	public static void dfs(int cnt, int preValue) {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				if(i > 0) sb.append(" ");
				sb.append(output[i]);
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(i < preValue) continue;
			output[cnt] = i;
			dfs(cnt + 1, i);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		
		output = new int[m];
		
		dfs(0,0);
		
		System.out.println(sb.toString());
	}

}
