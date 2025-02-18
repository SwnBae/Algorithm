import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//DFS
public class Main {
	
	public static int n;
	public static int count;
	
	public static void dfs(int num) {
		if(num == n) {
			count++;
			return;
		}
		
		for(int i = 1; i <=3; i++) {
			if(num > n) continue;
			
			dfs(num + i);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t= 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			count = 0;
			dfs(0);
			System.out.println(count);
		}
	}

}
