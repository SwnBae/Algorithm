import java.io.*;
import java.util.*;

public class Solution {
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1};
	
	public static List<List<int[]>> idx;
	public static int[][] space;
	public static boolean[][] eaten; // 먹기
	public static boolean[][] visited; // 덩어리 세기
	public static int n;
	public static int maxDay;
	public static int maxCnt;
	
	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= n;
	}
	
	public static void eat(int day) {
		for(int[] yx : idx.get(day)) {
			eaten[yx[0]][yx[1]] = true;
		}
	}
	
	public static void bfs(int stY, int stX) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		visited[stY][stX] = true;
		queue.add(new int[] {stY, stX});
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int y = tmp[0];
			int x = tmp[1];
			
			for(int i = 0; i < 4; i++) {
				int tmpY = y + dy[i];
				int tmpX = x + dx[i];
				
				if(outBound(tmpY, tmpX) || eaten[tmpY][tmpX] ||visited[tmpY][tmpX]) continue;
				
				visited[tmpY][tmpX] = true;
				queue.add(new int[] {tmpY, tmpX});
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			space = new int[n][n];
			eaten = new boolean[n][n];
			idx = new ArrayList<>();
			maxDay = 0;
			maxCnt = 1;
			
			for(int i = 0; i <= 100; i++) {
				idx.add(new ArrayList<>());
			}
			
			for(int i = 0; i < n; i++) {
				String[] input = br.readLine().split(" ");
				for(int j = 0; j < n; j++) {
					space[i][j] = Integer.parseInt(input[j]);
					idx.get(space[i][j]).add(new int[] {i,j});
					maxDay = Math.max(maxDay, space[i][j]);
				}
			}
			
			for(int day = 1; day <= maxDay; day++) {
				int cnt = 0;
				
				eat(day);
				visited = new boolean[n][n];
				
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						if(eaten[i][j] || visited[i][j]) continue;
						
						cnt++;
						bfs(i,j);
					}
				}
				
				maxCnt = Math.max(maxCnt, cnt);
			}
			
			System.out.println("#" + t + " " + maxCnt);
		}

	}

}
