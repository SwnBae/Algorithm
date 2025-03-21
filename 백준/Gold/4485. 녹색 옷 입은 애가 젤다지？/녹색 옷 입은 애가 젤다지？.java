import java.io.*;
import java.util.*;

public class Main {
	public static final int INF = 200000;
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1};
	
	public static int[][] space;
	public static int[][] cost;
	public static int n;
	
	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= n;
	}
	
	public static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		pq.add(new int[] {0,0,cost[0][0]});
		
		while(!pq.isEmpty()) {
			int[] tmp = pq.poll();
			
			int y = tmp[0];
			int x = tmp[1];
			int value = tmp[2];
			
			if(value > cost[y][x]) {
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int tmpY = y + dy[i];
				int tmpX = x + dx[i];
				
				if(outBound(tmpY, tmpX)) continue;
				
				if(cost[tmpY][tmpX] > value + space[tmpY][tmpX]) {
					cost[tmpY][tmpX] = value + space[tmpY][tmpX];
					pq.add(new int[] {tmpY,tmpX,cost[tmpY][tmpX]});
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = -1;
		
		int cnt = 1;
		
		while((n = Integer.parseInt(br.readLine())) != 0) {
			space = new int[n][n];
			cost = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				String[] input = br.readLine().split(" ");
				for(int j = 0; j < n; j++) {
					space[i][j] = Integer.parseInt(input[j]);
				}
				
				Arrays.fill(cost[i], INF);
			}
			
			cost[0][0] = space[0][0];
			
			dijkstra();
			
			System.out.println("Problem " + cnt +": "+cost[n-1][n-1]);
			
			cnt++;
		}

	}

}
