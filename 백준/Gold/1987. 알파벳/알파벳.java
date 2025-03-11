import java.io.*;
import java.util.*;

public class Main {
	public static int[] dy = { 1, -1, 0, 0 };
	public static int[] dx = { 0, 0, -1, 1 };

	public static Map<String, Integer> map;
	public static boolean[] visited;
	public static int[][] space;
	public static int n;
	public static int m;
	public static int max;
	
	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= m;
	}
	
	public static void dfs(int y, int x, int cnt) {
		boolean end = true;
		
		for(int i = 0; i < 4; i++) {
			int tmpY = y + dy[i];
			int tmpX = x + dx[i];
			
			if(outBound(tmpY, tmpX) || visited[space[tmpY][tmpX]]) continue;
			
			end = false;
			visited[space[tmpY][tmpX]] = true;
			dfs(tmpY, tmpX, cnt + 1);
			visited[space[tmpY][tmpX]] = false;
		}
		
		if(end) {
			max = Math.max(max, cnt);
			return;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		space = new int[n][m];
		map = new HashMap<>();
		
		int cnt = 0;
		max = 1;
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split("");
			for(int j = 0; j < m; j++) {
				if(!map.containsKey(input[j])) {
					map.put(input[j], cnt++);
				}
				
				space[i][j] = map.get(input[j]); 
			}
		}
		
		visited = new boolean[cnt];
		
		visited[space[0][0]] = true;
		dfs(0, 0, 1);

		System.out.println(max);
	}

}
