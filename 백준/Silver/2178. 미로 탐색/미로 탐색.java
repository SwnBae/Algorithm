import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static int[] dy = {0,0,-1,1};
	public static int[] dx = {-1,1,0,0};
	public static int n;
	public static int m;
	public static int[][] space;
	public static boolean[][] visited;
	
	public static boolean check(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >=m || visited[y][x] || space[y][x] == 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		visited = new boolean[n][m];
		space = new int[n][m];
		int result = 0;
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split("");
			for(int j = 0; j < m; j++) {
				space[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		Queue<int[]> queue = new ArrayDeque<>();

		queue.offer(new int[] {0,0,1});
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int y = tmp[0];
			int x = tmp[1];
			int cnt = tmp[2];
			
			if(y == n-1 && x == m-1) {
				result = cnt;
				break;
			}
			
			if(visited[y][x]) continue;
			visited[y][x] = true;
			
			for(int i = 0; i < 4; i++) {
				int tmpY = y + dy[i];
				int tmpX = x + dx[i];
				
				if(check(tmpY,tmpX)) continue;
				queue.offer(new int[] {tmpY,tmpX,cnt + 1});
			}
		}
		
		System.out.println(result);
	}

}
