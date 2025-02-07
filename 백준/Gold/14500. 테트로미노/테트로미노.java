import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static int[] dy = { -1, 1, 0, 0 };
	public static int[] dx = { 0, 0, -1, 1 };
	public static int[][] space;
	public static boolean[][] visited;

	public static int calculateT(int y, int x) {
		int max = -1;

		for (int i = 0; i < 4; i++) {
			int shapeT = 0;
			for (int j = i; j < i + 3; j++) {
				if (!checkBound(y + dy[j % 4], x + dx[j % 4])) {
					shapeT = 0;
					break;
				}
				shapeT += space[y + dy[j % 4]][x + dx[j % 4]];
			}
			max = Math.max(max, shapeT);
		}
		return max + space[y][x];
	}

	public static int dfs(int cnt, int y, int x) {
		int max = -1;

		if (cnt == 4) {
			return space[y][x];
		}
		
		visited[y][x] = true;
		
		// 이전에 탐색했던 블록을 계속 탐색한다... -> visited를 추가, 메서드 종료시 미방문으로 체크
		for (int i = 0; i < 4; i++) {
			if (!checkBound(y + dy[i], x + dx[i]) || visited[y + dy[i]][x + dx[i]])
				continue;
			max = Math.max(max, space[y][x] + dfs(cnt + 1, y + dy[i], x + dx[i]));
		}
		
		visited[y][x] = false;
		return max;

	}

	public static boolean checkBound(int y, int x) {
		if (y < 0 || y >= space.length || x < 0 || x >= space[0].length) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nm = br.readLine().split(" ");

		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);

		space = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");

			for (int j = 0; j < m; j++) {
				space[i][j] = Integer.parseInt(input[j]);
			}
		}

		int max = -1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				max = Math.max(max, dfs(1, i, j));
				max = Math.max(max, calculateT(i,j));
			}
		}

		System.out.println(max);

	}

}
