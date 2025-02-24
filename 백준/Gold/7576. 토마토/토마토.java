import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static int n;
	public static int m;
	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};
	public static int[][] space;
	public static boolean cantFill;
	
	public static boolean check(int y, int x) {
		return y < 0 || y >= m || x < 0 || x >= n || space[y][x] == 1 || space[y][x] == -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		cantFill = true;
		space = new int[m][n];
		int maxcnt = 0;
		int result = 0;
		int cnt = 0;
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		for(int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				space[i][j] = Integer.parseInt(input[j]);
				
				if(space[i][j] == 1) {
					queue.offer(new int[] {i,j,0});
					maxcnt++;
					cantFill = false;
				} else if(space[i][j] == 0) {
					maxcnt++;
				}
			}
		}
		
		while(!queue.isEmpty()) {
			
			int[] tmp = queue.poll();
			int y = tmp[0];
			int x = tmp[1];
			int day = tmp[2];
			
			if(space[y][x] == 1 && day != 0) { // 이미 방문한 경우
				if(queue.isEmpty()) {
					result = -1;
					break;
				} else {
					continue;
				}
			}
			
			space[y][x] = 1;
			cnt++;
			
//			System.out.println(cnt + "," + maxcnt);
//			System.out.println("y : "+y + ", x : " + x);
//			for(int i = 0; i < m; i++) {
//				System.out.println(Arrays.toString(space[i]));
//			}
			
			
			if(cnt == maxcnt) {
				result = day;
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int tmpY = y + dy[i];
				int tmpX = x + dx[i];
				
				if(check(tmpY, tmpX)) continue;
				
				queue.offer(new int[] {tmpY, tmpX, day + 1});
			}
			
			if(queue.isEmpty()) {
				result = -1;
				break;
			}
		}
		
		if(cantFill) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
		
	}

}
