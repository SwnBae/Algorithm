import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static int[] dy = { 0, 1 };
	public static int[] dx = { 1, 0 };
	public static String[][] space;
	public static int size;

	public static int calculateMax(int y, int x) {
		String currentC = space[y][0]; 
		int currentSum = 0;
		int max = 0;

		for (int i = 0; i < size; i++) {
			if (space[y][i].equals(currentC)) {
				currentSum++;
			} else {
				currentC = space[y][i];
				max = Math.max(max, currentSum);
				currentSum = 1;
			}
		}
		max = Math.max(max, currentSum); // 반목문 끝날 때 한번 더 비교

		currentC = space[0][x];
		currentSum = 0;

		for (int i = 0; i < size; i++) {
			if (space[i][x].equals(currentC)) {
				currentSum++;
			} else {
				currentC = space[i][x];
				max = Math.max(max, currentSum);
				currentSum = 1;
			}
		}
		
		max = Math.max(max, currentSum); // 반목문 끝날 때 한번 더 비교

		return max;
	}

	public static boolean checkDiff(int y, int x, int tempY, int tempX) {
		if (space[y][x].equals(space[tempY][tempX])) {
			return false;
		}
		return true;
	}

	public static int search(int y, int x) {
		int max = 0;

		for (int i = 0; i < 2; i++) {
			int tempY = y + dy[i];
			int tempX = x + dx[i];

			if (checkOut(tempY, tempX) && checkDiff(y, x, tempY, tempX)) {
				String temp = space[tempY][tempX];
				space[tempY][tempX] = space[y][x];
				space[y][x] = temp;
				
				max = Math.max(max, calculateMax(tempY, tempX));
				max = Math.max(max, calculateMax(y, x));
				
				temp = space[tempY][tempX];
				space[tempY][tempX] = space[y][x];
				space[y][x] = temp;
			}
		}

		return max;
	}

	public static boolean checkOut(int y, int x) {
		if (y < 0 || y >= size || x < 0 || x >= size) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		size = Integer.parseInt(br.readLine());

		space = new String[size][size];
		
		int max = 0;
		// 한번도 바꾸지 않았을 떄?...
		for (int i = 0; i < size; i++) {
			String[] input = br.readLine().split("");
			space[i] = input;
		}
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				max = Math.max(max, calculateMax(i, j));
			}
		}
		

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				max = Math.max(max, search(i, j));
			}
		}
		
		System.out.println(max);

	}

}
