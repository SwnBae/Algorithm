import java.io.*;
import java.util.*;;

public class Main {
	public static int[] dy = {1, -1, 0, 0};
	public static int[] dx = {0, 0, 1, -1};
	
	public static int n;
	public static int m;
	public static int k;
	public static boolean[][] space;
	public static boolean[][][] visited; // 벽을 부순 횟수
	
	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= m;
	}
	
	public static int bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.add(new int[] {0,0,1,0});
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			
			int y = tmp[0];
			int x = tmp[1];
			int cnt = tmp[2];
			int bc = tmp[3];
			
			if(y == n-1 && x == m-1) {
				return cnt;
			}
			
			for(int i = 0; i < 4; i++) {
				int tmpY = y + dy[i];
				int tmpX = x + dx[i];
				int breakCount = bc;
				
				if(outBound(tmpY, tmpX)) continue;
				
				if(space[tmpY][tmpX] == true) {
					if(breakCount == k || visited[tmpY][tmpX][breakCount]) continue;
					
					breakCount++;
				}
				
				if(!visited[tmpY][tmpX][breakCount]) {
					visited[tmpY][tmpX][breakCount] = true;
					queue.add(new int[] {tmpY,tmpX,cnt + 1, breakCount});
				}
			}
		}
		
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nmk = br.readLine().split(" ");
		n = Integer.parseInt(nmk[0]);
		m = Integer.parseInt(nmk[1]);
		k = Integer.parseInt(nmk[2]);
		space = new boolean[n][m];
		visited = new boolean[n][m][k + 1];
		
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				if(input.charAt(j) == '1') {
					space[i][j] = true;
				}
			}
		}
		
		System.out.println(bfs());
	}
}
