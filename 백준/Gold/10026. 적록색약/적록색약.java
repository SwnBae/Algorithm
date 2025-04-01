import java.io.*;
import java.util.ArrayDeque;

public class Main {
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1};
	
	public static int n;
	public static int[][] space;
	public static int[][] rgspace;
	public static boolean[][] visited;
	
	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= n;
	}
	
	public static void bfs(int by, int bx, int[][] sp) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		visited[by][bx] = true;
		queue.add(new int[] {by,bx});
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int y = tmp[0];
			int x = tmp[1];
			
			for(int i = 0; i < 4; i++) {
				int tmpY = y + dy[i];
				int tmpX = x + dx[i];
				
				if(outBound(tmpY, tmpX) || visited[tmpY][tmpX] || sp[tmpY][tmpX] != sp[y][x]) continue;
				
				visited[tmpY][tmpX] = true;
				queue.add(new int[] {tmpY,tmpX});
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		space = new int[n][n];
		rgspace = new int[n][n];
		visited = new boolean[n][n];
		
		int normal = 0;
		int rg = 0;
		
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < n; j++) {
				if(input.charAt(j) == 'R') {
					space[i][j] = 1;
					rgspace[i][j] = 1;
				} else if(input.charAt(j) == 'G') {
					space[i][j] = 2;
					rgspace[i][j] = 1;
				} else {
					space[i][j] = 3;
					rgspace[i][j] = 2;
				}
			}
		}
		
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visited[i][j]) {
					normal++;
					bfs(i, j, space);
				}
			}
		}
		
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visited[i][j]) {
					rg++;
					bfs(i, j,rgspace);
				}
			}
		}
		
		System.out.println(normal + " " + rg);
	}

}
