import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int[] arr;
	public static int[] output;
	public static boolean[] visited;
	public static int n;
	public static int m;
	public static StringBuilder sb = new StringBuilder();

	public static void dfs(int cnt, int preValue) {
		if (cnt == m) {
			for (int i = 0; i < m; i++) {
				if (i > 0)
					sb.append(" ");
				sb.append(output[i]);
			}
			sb.append("\n");
			return;
		}

		for (int num : arr) {
			if (visited[num] || num < preValue)
				continue;

			visited[num] = true;
			output[cnt] = num;
			dfs(cnt + 1, num);
			visited[num] = false;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);

		arr = new int[n];
		visited = new boolean[10001];
		output = new int[m];

		String[] inputArr = br.readLine().split(" ");

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(inputArr[i]);
		}

		Arrays.sort(arr);

		dfs(0,0);

		System.out.println(sb.toString());
	}

}
