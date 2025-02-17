import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static final int SIZE = 100;
	public static final int COLOR = 10;
	public static int[] dy = { 0, 0, -1, 1 };
	public static int[] dx = { -1, 1, 0, 0 };
	public static boolean[][] space;

	public static boolean outBound(int y, int x) {
		return y < 0 || y >= SIZE || x < 0 || x >= SIZE;
	}

	public static int cal(int y, int x) {
		int cnt = 0;

		for (int i = 0; i < 4; i++) {
			int tmpY = y + dy[i];
			int tmpX = x + dx[i];

			if (outBound(tmpY, tmpX) || !space[tmpY][tmpX]) {
				cnt++;
			}
		}

		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int sum = 0;
		space = new boolean[SIZE][SIZE];

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);

			for (int p = y; p < y + COLOR; p++) {
				for (int j = x; j < x + COLOR; j++) {
					space[p][j] = true;
				}
			}
		}

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (space[i][j]) {
					sum += cal(i, j);
				}
			}
		}

		System.out.println(sum);

	}

}
