import java.io.*;
import java.util.Arrays;

public class Main {
	//맥스 트리
	public static int[] tree;
	public static int[] arr;
	
	public static int init(int node, int start, int end) {
		if(start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		return tree[node] = Math.max(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
	}
	
	public static int findMax(int node, int start, int end, int left, int right) {
		if(end < left || right < start) return Integer.MIN_VALUE;
		
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		
		return Math.max(findMax(node * 2, start, mid, left, right), 
				findMax(node * 2 + 1, mid + 1, end, left, right));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		
		arr = new int[n];
		tree = new int[4 * n];
		Arrays.fill(tree, Integer.MIN_VALUE);
		
		arr = Arrays.stream(br.readLine().split(" "))
				.mapToInt((a) -> Integer.parseInt(a))
				.toArray();
		
		init(1, 0, n-1);
		
		for(int i = m-1; i < n - m + 1; i++) {
			sb.append(findMax(1, 0, n-1, i - (m - 1), i + (m - 1)) + " ");
		}
		System.out.println(sb.toString());
	}

}
