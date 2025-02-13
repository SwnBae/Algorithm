import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static StringBuilder sb = new StringBuilder();

	public static String line;
	public static int[] temp;
	public static int[] arr;
	public static boolean[] visited;

	public static void dfs(int cnt, int prev) {
		if (cnt == 6) {
			for (int i = 0; i < 6; i++) {
				if (i > 0)
					sb.append(" ");
				sb.append(temp[i]);
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (visited[i] || prev > arr[i])
				continue;

			visited[i] = true;
			temp[cnt] = arr[i];
			dfs(cnt + 1, arr[i]);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (!(line = br.readLine()).equals("0")) {

			String[] input = line.split(" ");
			temp = new int[6];
			arr = new int[input.length - 1];
			visited = new boolean[input.length - 1];

			for (int i = 1; i < input.length; i++) {
				arr[i - 1] = Integer.parseInt(input[i]);
			}

			dfs(0, 0);

			System.out.println(sb.toString());
			sb.setLength(0);
		}

	}

}
