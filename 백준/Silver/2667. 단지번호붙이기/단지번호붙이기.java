import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
	// 1. dfs 풀이
	public static int cnt;
	public static int n;
	public static int[] dy = { 0, 0, -1, 1 };
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[][] space;
	public static boolean[][] visited;
	
	public static int tmp;
	public static List<Integer> list;
	
	public static void dfs(int y, int x) {
		tmp++;
		visited[y][x]= true;
		
		for(int i = 0; i < 4; i++) {
			int tmpY = y + dy[i];
			int tmpX = x + dx[i];
			
			if(outBound(tmpY,tmpX) || space[tmpY][tmpX] == 0 || visited[tmpY][tmpX]) continue;
			
			dfs(tmpY,tmpX);
		}
		
	}
	
	public static boolean outBound(int y, int x) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		cnt = 0;
		space = new int[n][n];
		visited = new boolean[n][n];
		list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < n; j++) {
				space[i][j] = Integer.parseInt(input[j]);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (space[i][j] == 1 && !visited[i][j]) {
					dfs(i, j);
					list.add(tmp);
					cnt++;
					tmp = 0;
				}
			}
		}
		
		System.out.println(cnt);
		
		Collections.sort(list);
		
		for(int i : list) {
			System.out.println(i);
		}
	}

}
