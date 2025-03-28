import java.io.*;

public class Main {
	public static int[] tree;
	public static int[] lazy;

	public static void update(int node, int start, int end, int left, int right) {
		lazy_update(node, start, end);

		if (start > right || end < left)
			return;

		if (left <= start && end <= right) {
			tree[node] = (end - start + 1) - tree[node];

			if (start != end) {
				lazy[node * 2] += 1;
				lazy[node * 2 + 1] += 1;
			}
			return;
		}

		int mid = (start + end) / 2;

		update(node * 2, start, mid, left, right);
		update(node * 2 + 1, mid + 1, end, left, right);

		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	public static int sum(int node, int start, int end, int left, int right) {
		lazy_update(node, start, end);

		if (left > end || right < start)
			return 0;

		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;

		return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
	}

	public static void lazy_update(int node, int start, int end) {
		if (lazy[node] != 0) {
			if (lazy[node] % 2 != 0) {
				tree[node] = (end - start + 1) - tree[node];

				if (start != end) {
					lazy[node * 2] += lazy[node];
					lazy[node * 2 + 1] += lazy[node];
				}
			}

			lazy[node] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] nm = br.readLine().split(" ");

		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);

		tree = new int[4 * n];
		lazy = new int[4 * n];

		for (int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			int op = Integer.parseInt(input[0]);
			int a = Integer.parseInt(input[1]) - 1;
			int b = Integer.parseInt(input[2]) - 1;

			if (op == 0) {
				update(1, 0, n - 1, a, b);
			} else {
				sb.append(sum(1, 0, n - 1, a, b) + "\n");
			}
		}

		System.out.println(sb.toString());
	}

}
