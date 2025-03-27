import java.io.*;
import java.util.Arrays;

public class Main {
	public static int[] minTree;
	public static int[] maxTree;
	public static int[] arr;
	
	public static int initMin(int node, int start, int end) {
		if(start == end) return minTree[node] = arr[start];
		
		int mid = (start + end) / 2;
		return minTree[node] = Math.min(initMin(node * 2, start, mid),
				initMin(node * 2 + 1, mid + 1, end)) ;
	}
	
	public static int findMin(int node, int start, int end, int left, int right) {
		if(end < left || start > right) return Integer.MAX_VALUE;
		
		if(left <= start && end <= right) return minTree[node];
		
		int mid = (start + end) / 2;
		
		return Math.min(findMin(node * 2, start, mid, left, right), 
				findMin(node * 2 + 1, mid + 1, end, left, right));
	}
	
	public static void updateMin(int node, int start, int end, int idx, int value) {
		if(start == end) {
			minTree[node] = value;
			return;
		}
		
		int mid = (start + end) / 2;
		
		if(idx <= mid) {
			updateMin(node * 2, start, mid, idx, value);
		} else {
			updateMin(node * 2 + 1, mid + 1, end, idx, value);
		}
		
		minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
	}
	
	public static int initMax(int node, int start, int end) {
		if(start == end) return maxTree[node] = arr[start];
		
		int mid = (start + end) / 2;
		return maxTree[node] = Math.max(initMax(node * 2, start, mid),
				initMax(node * 2 + 1, mid + 1, end)) ;
	}
	
	public static int findMax(int node, int start, int end, int left, int right) {
		if(end < left || start > right) return Integer.MIN_VALUE;
		
		if(left <= start && end <= right) return maxTree[node];
		
		int mid = (start + end) / 2;
		
		return Math.max(findMax(node * 2, start, mid, left, right), 
				findMax(node * 2 + 1, mid + 1, end, left, right));
	}
	
	public static void updateMax(int node, int start, int end, int idx, int value) {
		if(start == end) {
			maxTree[node] = value;
			return;
		}
		
		int mid = (start + end) / 2;
		
		if(idx <= mid) {
			updateMax(node * 2, start, mid, idx, value);
		} else {
			updateMax(node * 2 + 1, mid + 1, end, idx, value);
		}
		
		maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			String[] nk = br.readLine().split(" ");
			
			int n = Integer.parseInt(nk[0]);
			int k = Integer.parseInt(nk[1]);
			arr = new int[n];
			minTree = new int[4 * n];
			maxTree = new int[4 * n];
			
			Arrays.fill(minTree, 100001);
			
			for(int i = 0; i < n; i++) {
				arr[i] = i;
			}
			
			initMin(1,0,n-1);
			initMax(1,0,n-1);
			
			for(int i = 0; i < k; i++) {
				String[] input = br.readLine().split(" ");
				int op = Integer.parseInt(input[0]);
				int a = Integer.parseInt(input[1]);
				int b = Integer.parseInt(input[2]);
				
				if(op == 0) {
					int tmpA = arr[a];
					int tmpB = arr[b];
					if(tmpA == tmpB) continue;
					
					updateMin(1, 0, n - 1, a, tmpB);
					updateMin(1, 0, n - 1, b, tmpA);
					
					updateMax(1, 0, n - 1, a, tmpB);
					updateMax(1, 0, n - 1, b, tmpA);
					
					arr[a] = tmpB;
					arr[b] = tmpA;
				} else {
					sb.append(findMin(1,0,n-1,a,b) == a && findMax(1,0,n-1,a,b) == b ? "YES" : "NO");
					sb.append("\n");
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
