import java.io.*;

public class Main {
	public static int[] arr;
	public static int[] minTree;
	public static int[] maxTree;
	public static int n;
	
	public static int initMin(int node, int start, int end) {
		if(start == end) return minTree[node] = arr[start];
		
		int mid = (start + end) / 2;
		
		return minTree[node] = Math.min(initMin(node * 2, start, mid), 
				initMin(node * 2 + 1, mid + 1, end));
	}
	
	public static int findMin(int node, int start, int end, int left, int right) {
		if(end < left || start > right) return Integer.MAX_VALUE;
		
		if(left <= start && end <= right) return minTree[node];
		
		int mid = (start + end) / 2;
		return Math.min(findMin(node * 2, start, mid, left, right), 
				findMin(node * 2 + 1, mid + 1, end, left, right));
	}
	
	public static int initMax(int node, int start, int end) {
		if(start == end) return maxTree[node] = arr[start];
		
		int mid = (start + end) / 2;
		
		return maxTree[node] = Math.max(initMax(node * 2, start, mid), 
				initMax(node * 2 + 1, mid + 1, end));
	}
	
	public static int findMax(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return Integer.MIN_VALUE;
		
		if(left <= start && end <= right) return maxTree[node];
		
		int mid = (start + end) / 2;
		return Math.max(findMax(node * 2, start, mid, left, right), 
				findMax(node * 2 + 1, mid + 1, end, left, right));
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] nm = br.readLine().split(" ");
		
		n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		
		arr = new int[n];
		minTree = new int[4 * n];
		maxTree = new int[4 * n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		initMin(1, 0, n-1);
		initMax(1, 0, n-1);
		
		for(int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]) - 1;
			int b = Integer.parseInt(input[1]) - 1;
			
			sb.append(findMin(1, 0, n-1, a, b) + " " + findMax(1, 0, n-1, a, b) + "\n");
		}
		
		System.out.println(sb.toString());
	}

}
