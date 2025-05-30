import java.io.*;
import java.util.*;;

public class Main {
	public static final int BREAK = 1;
	public static final int UNBREAK = 0;
	public static int[] dy = {1, -1, 0, 0};
	public static int[] dx = {0, 0, 1, -1};
	
	public static int n;
	public static int m;
	public static int[][] space;
	public static boolean[][][] visited;
	
	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= m;
	}
	
	public static int bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.add(new int[] {0,0,1,UNBREAK});
		visited[0][0][UNBREAK] = true;
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			
			int y = tmp[0];
			int x = tmp[1];
			int cnt = tmp[2];
			int canBreak = tmp[3];
			
			if(y == n-1 && x == m-1) {
				return cnt;
			}
			
			for(int i = 0; i < 4; i++) {
				int tmpY = y + dy[i];
				int tmpX = x + dx[i];
				int nextBreak = canBreak;
				
				if(outBound(tmpY, tmpX)) continue;
				
				if(space[tmpY][tmpX] == 1) {
					if(nextBreak == BREAK || visited[tmpY][tmpX][BREAK]) continue;
					
					nextBreak = BREAK;
				}
				
				if(!visited[tmpY][tmpX][nextBreak]) {
					visited[tmpY][tmpX][nextBreak] = true;
					queue.add(new int[] {tmpY,tmpX,cnt + 1, nextBreak});
				}
			}
		}
		
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		space = new int[n][m];
		visited = new boolean[n][m][2];
		
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				space[i][j]= input.charAt(j) - '0'; 
			}
		}
		
		System.out.println(bfs());
	}
}
