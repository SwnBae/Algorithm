import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int k;
	public static int[] temp;
	public static char[] option;
	public static long max;
	public static long min;
	public static boolean[] visited;

	public static void dfs(int cnt, int preV) {
		if (cnt == k) {
			// 계산하기
			max = Math.max(max, cal());
			min = Math.min(min, cal());
			return;
		}

		if (option[cnt] == '<') {
			for (int i = preV + 1; i <= 9; i++) {
				if (visited[i])
					continue;

				visited[i] = true;
				temp[cnt + 1] = i;
				dfs(cnt + 1, i);
				visited[i] = false;
			}
		} else {
			for (int i = 0; i < preV; i++) {
				if (visited[i])
					continue;

				visited[i] = true;
				temp[cnt + 1] = i;
				dfs(cnt + 1, i);
				visited[i] = false;
			}
		}
	}

	public static long cal() {
		long sum = 0;
		for (int i = temp.length - 1; i >= 0; i--) {
			sum += temp[i] * Math.pow(10, temp.length - 1 - i);
		}

		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		k = Integer.parseInt(br.readLine());
		option = new char[k];
		temp = new int[k + 1];
		max = Long.MIN_VALUE;
		min = Long.MAX_VALUE;
		visited = new boolean[10];

		String[] op = br.readLine().split(" ");

		for (int i = 0; i < k; i++) {
			option[i] = op[i].charAt(0);
		}

		for (int i = 0; i <= 9; i++) {
			visited[i] = true;
			temp[0] = i;
			dfs(0, i);
			visited[i] = false;

		}

		System.out.println(max);
		System.out.printf("%0" + (k + 1) + "d", min);
	}

}
