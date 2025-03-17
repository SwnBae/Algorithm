import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;

class Animal{
	int y;
	int x;
	int[][] nextSpace;
	int cnt;
	
	public Animal(int y, int x, int[][] nextSpace, int cnt) {
		this.y = y;
		this.x = x;
		this.nextSpace = nextSpace;
		this.cnt = cnt;
	}
}

public class Main {
	public static int[] dy = { -1, 1, 0, 0 };
	public static int[] dx = { 0, 0, -1, 1 };

	public static int[][] initSpace;
	public static boolean[][] visited;
	public static int n;
	public static int m;

	// -1
	public static int stY;
	public static int stX;

	// -2
	public static int endY;
	public static int endX;

	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= m;
	}

	public static int bfs() {
		ArrayDeque<Animal> queue = new ArrayDeque<>();

		visited[stY][stX] = true;
		
		Animal start = new Animal (stY, stX, new int[n][m], 0);
		
		for(int i = 0; i < n; i++) {
			start.nextSpace[i] = Arrays.copyOf(initSpace[i], m);
			//System.out.println(Arrays.toString(start.nextSpace[i]));
		}
		
		queue.add(start);

		while (!queue.isEmpty()) {
			Animal tmp = queue.poll();

			if (tmp.y == endY && tmp.x == endX) {
				return tmp.cnt;
			}
			
			// 물 처리
			int[][] nxtSpace = water(tmp.nextSpace);
			

			for (int i = 0; i < 4; i++) {
				int tmpY = tmp.y + dy[i];
				int tmpX = tmp.x + dx[i];

				if (outBound(tmpY, tmpX) || visited[tmpY][tmpX] || 
						nxtSpace[tmpY][tmpX] == 1 || nxtSpace[tmpY][tmpX] == 2)
					continue;

				visited[tmpY][tmpX] = true;
				
				queue.add(new Animal(tmpY, tmpX, nxtSpace, tmp.cnt + 1));
			}
		}
		
		return -1;
	}

	public static int[][] water(int[][] space) {
		boolean[][] visitedW = new boolean[n][m];
		int[][] nextSpace = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			nextSpace[i] = Arrays.copyOf(space[i], m);
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (nextSpace[i][j] == 1 && !visitedW[i][j]) {
					visitedW[i][j] = true;
					nextSpace[i][j] = 1;
					
					for (int p = 0; p < 4; p++) {
						int tmpY = i + dy[p];
						int tmpX = j + dx[p];

						if (outBound(tmpY, tmpX) || visitedW[tmpY][tmpX] || 
								nextSpace[tmpY][tmpX] != 0)
							continue;
						
						visitedW[tmpY][tmpX] = true;
						nextSpace[tmpY][tmpX] = 1;
					}
				}
			}
		}
		
		return nextSpace;
	}

	// 이동가능 -> 0, 물 -> 1, 벽 -> 2
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nm = br.readLine().split(" ");

		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);

		initSpace = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				if (input.charAt(j) == 'D') {
					endY = i;
					endX = j;
					initSpace[i][j] = -2;
				} else if (input.charAt(j) == 'S') {
					stY = i;
					stX = j;
					initSpace[i][j] = -1;
				} else if (input.charAt(j) == '*') {
					initSpace[i][j] = 1;
				} else if (input.charAt(j) == 'X') {
					initSpace[i][j] = 2;
				}// 나머지는 0으로 초기화
			}
		}
		
		int cnt = bfs();
		
		System.out.println(cnt == -1 ? "KAKTUS" : cnt);
	}

}
