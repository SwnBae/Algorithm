import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;

class Player {
	int y;
	int x;
	boolean[][] nextSpace;
	

	public Player(int y, int x, boolean[][] nextSpace) {
		this.y = y;
		this.x = x;
		this.nextSpace = nextSpace;
	}
}

public class Main {
	public static int[] dy = { 0, 0, 1, -1, 1, 1, -1, -1 };
	public static int[] dx = { -1, 1, 0, 0, -1, 1, -1, 1 };
	public static boolean[][] initSpace;
	public static boolean[][] visited;

	public static boolean outBound(int y, int x) {
		return y < 0 || y >= 8 || x < 0 || x >= 8;
	}
	
	public static boolean bfs() {
		ArrayDeque<Player> queue = new ArrayDeque<>();

		Player start = new Player(7, 0, new boolean[8][8]);

		for (int i = 0; i < 8; i++) {
			start.nextSpace[i] = Arrays.copyOf(initSpace[i], 8);
		}

		visited[7][0] = true;

		queue.add(start);

		while (!queue.isEmpty()) {
			Player tmp = queue.poll();

			if (tmp.y == 0 && tmp.x == 7) {
				return true;
			}
			
			boolean[][] nextSpace = down(tmp.nextSpace);
			
			for(int i = 0; i < 8; i++) {
				int tmpY = tmp.y + dy[i];
				int tmpX = tmp.x + dx[i];
				
				//벽 + 방문
				if(outBound(tmpY,tmpX) || visited[tmpY][tmpX]) continue;
				
				//다음이 벽이면
				if(tmp.nextSpace[tmpY][tmpX]) {
					tmpY = tmp.y;
					tmpX = tmp.x;
				}
				
				// 다음에 벽이 내려온다면, 버린다.
				if(nextSpace[tmpY][tmpX]) continue;
				
				visited[tmpY][tmpX] = true;
				
				queue.add(new Player(tmpY, tmpX, nextSpace));
			}

		}

		return false;
	}

	public static boolean[][] down(boolean[][] space) {
		boolean[][] next = new boolean[8][8];
		
		for(int i = 0; i < 7; i++) {
			next[i + 1] = space[i];
		}
		
		
		return next;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		initSpace = new boolean[8][8];
		visited = new boolean[8][8];
		
		for (int i = 0; i < 8; i++) {
			String input = br.readLine();

			for (int j = 0; j < 8; j++) {
				if (input.charAt(j) == '.') {
					initSpace[i][j] = false;
				} else {
					initSpace[i][j] = true;
				}
			}
		}
		
		System.out.println(bfs() ? 1 : 0);

	}

}
