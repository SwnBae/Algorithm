import java.io.*;
import java.util.*;

public class Main {
	public static int[] dy = { 1, 0, -1, 0 };
	public static int[] dx = { 0, 1, 0, -1 };
	public static int n;
	public static int m;
	public static int[][] space;
	public static List<int[]> start;

	public static boolean outBound(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= m;
	}

	public static boolean isWall(int y, int x) {
		return space[y][x] == -1;
	}

	// 매번 16가지 검사해야함..
	public static int bfs() {
		int result = -1;

		Queue<int[]> queue = new ArrayDeque<>();

		int[] f = start.remove(0);
		int[] s = start.remove(0);

		queue.add(new int[] { f[0], f[1], s[0], s[1], 0 , -101}); // one, two, cnt, dir

		while (!queue.isEmpty()) {
			int[] tmp = queue.poll();
			//System.out.println(Arrays.toString(tmp));
			
			int oY = tmp[0];
			int oX = tmp[1];
			
			int tY = tmp[2];
			int tX = tmp[3];
			
			int cnt = tmp[4];
			
			int dir = tmp[5];
			
			if (cnt > 10) {
				result = -1;
				return result;
			}
			
			for (int i = 0; i < 4; i++) {
				//System.out.println((dir + 2) % 4);
				if((dir + 2) % 4 == i) continue;
				
				int tmpOY = oY + dy[i];
				int tmpOX = oX + dx[i];

				int tmpTY = tY + dy[i];
				int tmpTX = tX + dx[i];

				int out = 0;
				int wall = 0;
				
				if (outBound(tmpOY, tmpOX))
					out++;
				if (outBound(tmpTY, tmpTX))
					out++;

				//System.out.println(out);
				if (out == 1) {
					if (cnt >= 10) {
						result = -1;
						return result;
					}
					
					result = cnt + 1;
					return result;
				} else if (out == 2) {
					continue;
				}
				
				if(isWall(tmpOY, tmpOX)) {
					tmpOY = oY;
					tmpOX = oX;
					wall++;
				}
				
				if(isWall(tmpTY, tmpTX)) {
					tmpTY = tY;
					tmpTX = tX;
					wall++;
				}
				
				if(wall == 2) continue; // 둘 다 가만히 있는 경우
				if(wall == 1) {// 벽이 하나라도 있는 경우, 다음엔 모든 방향으로 이동하면 다른 경우임
					queue.add(new int[] { tmpOY, tmpOX, tmpTY, tmpTX, cnt + 1, -101});
				} else {
					queue.add(new int[] { tmpOY, tmpOX, tmpTY, tmpTX, cnt + 1, i});
				}
			}

		}

		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		start = new LinkedList<>();

		space = new int[n][m];

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				if (input[j].equals(".")) {
					space[i][j] = 0;
				} else if (input[j].equals("#")) {
					space[i][j] = -1;
				} else {
					space[i][j] = 0;
					start.add(new int[] { i, j });
				}
			}
		}

		System.out.println(bfs());

	}

}
