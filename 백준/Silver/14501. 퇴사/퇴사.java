import java.io.*;

public class Main {
	public static int n;
	public static int[] time;
	public static int[] cost;
	public static int max;
	public static int[] temp = new int[100];
	
	public static void dfs(int cnt, int cst) {
		if(cnt == n) {
			max = Math.max(max, cst);
			return;
			
		} else if(cnt > n) {
			return;
		}
		
		for(int i = cnt; i < n; i++) {
			dfs(i + time[i], cst + cost[i]); // 상담 그 날짜에 한다
			
			dfs(i + 1,cst); // 안한다
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		time = new int[n];
		cost = new int[n];
		max = 0;
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			
			time[i] = Integer.parseInt(input[0]);
			cost[i] = Integer.parseInt(input[1]);
		}
		
		dfs(0,0);
		
		System.out.println(max);

	}

}
