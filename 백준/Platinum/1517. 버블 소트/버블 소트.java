import java.io.*;
import java.util.*;

class Node{
	int idx;
	int value;
	
	public Node(int idx, int value) {
		this.idx = idx;
		this.value = value;
	}
}

public class Main {
	public static int[] arr;
	public static long[] tree;
	public static List<Node> list;
	
	public static long sum(int node, int start, int end, int left, int right) {
		if(end < left || right < start) return 0;
		
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		
		return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
	}
	
	public static void update(int node, int start, int end, int idx) {
		if(start > idx || end < idx) return;
		
		tree[node] += 1;
		if(start == end) return;
		
		int mid = (start + end)/ 2;
		
		update(node * 2, start, mid, idx);
		update(node * 2 + 1, mid + 1, end, idx);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		
		int n = Integer.parseInt(br.readLine());
		tree = new long[n * 4];
		arr = new int[n];
		long result = 0;
		
		String[] input = br.readLine().split(" ");
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(input[i]);
			
			list.add(new Node(i, arr[i]));
		}
		
		Collections.sort(list, (a,b) -> Long.compare(a.value, b.value));
		
		for(int i = 0; i < list.size(); i++) {
			Node node = list.get(i);
			
			result += sum(1, 0, n-1, node.idx + 1, n - 1);
			
			update(1, 0, n - 1, node.idx);
		}
		
		
		System.out.println(result);
	}
}
