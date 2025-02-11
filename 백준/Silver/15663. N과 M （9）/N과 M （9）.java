import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static StringBuilder sb = new StringBuilder();

	public static int[] output;
	public static int n;
	public static int m;
	public static int[] arr;
	public static boolean[] visited;

	public static void dfs(int cnt) {
		if (cnt == m) {
			for (int i = 0; i < m; i++) {
				if (i > 0)
					sb.append(" ");
				sb.append(output[i]);
			}
			sb.append("\n");
			return;
		}
		
		int prevNum = -1;

		for (int idx = 0; idx < n; idx++) { // 전에 갔던 선택지로 갈 필요가 없음
			if (visited[idx] || arr[idx] == prevNum) {
				continue;
			}
				
			prevNum = arr[idx];
			visited[idx] = true;
			output[cnt] = arr[idx];
			dfs(cnt + 1);
			visited[idx] = false;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputNM = br.readLine().split(" ");
		n = Integer.parseInt(inputNM[0]);
		m = Integer.parseInt(inputNM[1]);

		arr = new int[n];
		output = new int[m];
		visited = new boolean[n];
		
		String[] input = br.readLine().split(" ");

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}

		Arrays.sort(arr);

		dfs(0);

		System.out.println(sb.toString());

	}

}
