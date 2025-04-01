import java.io.*;
import java.util.*;

public class Main {
	public static final int INF = 100001;
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1};
	
	public static int n;
	public static int m;
	
	public static boolean[][] space;
	public static int[][][] cost;
	
	public static int startY;
	public static int startX;
	public static int endY;
	public static int endX;
	
	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= m;
	}
	
	public static int bfs() {
		int result = INF;
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> Integer.compare(o1[3], o2[3]));
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				Arrays.fill(cost[i][j], INF);
			}
		}
		
		for(int i = 0; i < 4; i++) {
			cost[startY][startX][i] = 0;
		}
		
		pq.add(new int[] {startY,startX,-1, 0}); //y,x,방향, 방향cnt
		
		while(!pq.isEmpty()){
			int[] tmp = pq.poll();
			int y = tmp[0];
			int x = tmp[1];
			int dir = tmp[2];
			
			if(y == endY && x == endX) {
				result = Math.min(result, tmp[3]);
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int tmpY = y + dy[i];
				int tmpX = x + dx[i];
				int count = tmp[3];
				
				if(outBound(tmpY, tmpX) || space[tmpY][tmpX]) continue;
				
				if(dir != i) {
					count++;
				}
				
				if(y == startY && x == startX) {
					count--;
				}
				
				if(cost[tmpY][tmpX][i] > count) {
					cost[tmpY][tmpX][i] = count;
					pq.add(new int[] {tmpY, tmpX, i, count});
				}
			}
		}
		
		return result;
	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		m = Integer.parseInt(nm[0]);
		n = Integer.parseInt(nm[1]);
		
		space = new boolean[n][m];
		cost = new int[n][m][4];
		
		boolean started = false;
		
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				if(input.charAt(j) == '*') {
					space[i][j] = true;
				} else if(input.charAt(j) == 'C'){
					if(!started) {
						startY = i;
						startX = j;
						started = true;
					} else {
						endY = i;
						endX = j;
					}
				} 
			}
		}
		
		
		
		System.out.println(bfs());
	}

}
