import java.io.*;

class Node {
	char value;
	Node left;
	Node right;

	public Node(char value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
}

public class Main {
	public static int n;
	public static Node[] tree;
	public static StringBuilder sb = new StringBuilder();

	public static void preOrder(Node node) {
		if (node == null)
			return;

		sb.append(node.value);
		preOrder(node.left);
		preOrder(node.right);
	}

	public static void inOrder(Node node) {
		if (node == null)
			return;
		
		inOrder(node.left);
		sb.append(node.value);
		inOrder(node.right);
	}

	public static void postOrder(Node node) {
		if (node == null)
			return;
		
		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.value);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		tree = new Node[n + 1];

		for (int i = 1; i <= n; i++) {
			String[] input = br.readLine().split(" ");

			char pr = input[0].charAt(0);
			char l = input[1].charAt(0);
			char r = input[2].charAt(0);

			if (tree[pr - 'A'] == null) {
				tree[pr - 'A'] = new Node(pr);
			}

			if (l != '.') {
				tree[l - 'A'] = new Node(l);
				tree[pr - 'A'].left = tree[l - 'A'];
			}

			if (r != '.') {
				tree[r - 'A'] = new Node(r);
				tree[pr - 'A'].right = tree[r - 'A'];
			}
		}
		
		preOrder(tree[0]);
		sb.append("\n");
		inOrder(tree[0]);
		sb.append("\n");
		postOrder(tree[0]);

		System.out.println(sb.toString());
	}

}
