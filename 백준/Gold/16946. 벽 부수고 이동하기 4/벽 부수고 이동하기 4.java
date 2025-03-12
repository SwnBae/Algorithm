import java.io.*;
import java.util.*;

public class Main {
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	
	public static int[][] space;
	public static Map<Integer, Integer> zeroBundle;
	public static int n;
	public static int m;
	public static int id;
	
	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= m;
	}
	
	public static void bfs(int y, int x) {
		Queue<int[]> queue = new ArrayDeque<>();
		int result = 1;
		
		queue.add(new int[] {y, x});
		space[y][x] = id;
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int bY = tmp[0];
			int bX = tmp[1];
			
			for(int i = 0; i < 4; i++) {
				int tmpY = bY + dy[i];
				int tmpX = bX + dx[i];
				
				if(outBound(tmpY, tmpX) || space[tmpY][tmpX] != 0) continue;
				
				result++;
				space[tmpY][tmpX] = id;
				queue.add(new int[] {tmpY,tmpX});
			}
		}
		
		zeroBundle.put(id++, result);
	}
	
	public static int calculate(int y, int x) {
		int result = 1;
		
		Set<Integer> set = new HashSet<>();
		
		for(int i = 0; i < 4; i++) {
			int tmpY = y + dy[i];
			int tmpX = x + dx[i];
			
			if(!outBound(tmpY, tmpX) && space[tmpY][tmpX] != 1) {
				set.add(space[tmpY][tmpX]);
			}
		}
		
		for (int key : set) {
			result += zeroBundle.get(key);
		}
		
		return result % 10;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		space = new int[n][m];
		zeroBundle = new HashMap<>();
		id = 2;

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				space[i][j] = input.charAt(j) - '0';
			}
		}
		
		//ZeroBundle 계산
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(space[i][j] == 0) {
					bfs(i, j);
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(space[i][j] == 1) {
					sb.append(calculate(i,j));
				} else {
					sb.append("0");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
