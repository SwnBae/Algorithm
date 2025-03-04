import java.io.*;
import java.util.*;

//0 - 1 BFS
public class Main {
	public static int[] dy = {1,-1,0,0};
	public static int[] dx = {0,0,1,-1};
	public static int n;
	public static int m;
	public static int[][] space;
	public static boolean[][] visited;
	public static int[][] count;
	
	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= m;
	}
	
	//항상 가중치가 낮은 것 부터..
	public static int bfs() {
		Deque<int[]> queue = new ArrayDeque<>();
		int result = 0;
		
		queue.add(new int[] {0,0,0}); // y,x, 벽 부순 횟수
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int y = tmp[0];
			int x = tmp[1];
			int cnt = tmp[2];
			
			
			if(y == n - 1 && x == m - 1) {
				result = cnt;
				break;
			}
			
			if(visited[y][x]) {
				continue;
			}
			
			visited[y][x] = true;
			
			
			// 가중치가 낮은 것 부터 우선이다.
			for(int i = 0; i < 4; i++) {
				int tmpY = y + dy[i];
				int tmpX = x + dx[i];
				
				if(outBound(tmpY,tmpX) || visited[tmpY][tmpX]) continue;
				
				if(space[tmpY][tmpX] == 0) {
					queue.addFirst(new int[] {tmpY,tmpX, cnt});
				} else {
					queue.add(new int[] {tmpY,tmpX, cnt + 1});
				}
			}
			
		}
		
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] mn = br.readLine().split(" ");
		n = Integer.parseInt(mn[1]);
		m = Integer.parseInt(mn[0]);
		space = new int[n][m];
		visited = new boolean[n][m];
		count = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split("");
			for(int j = 0; j < m; j++) {
				space[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		System.out.println(bfs());
	}

}
