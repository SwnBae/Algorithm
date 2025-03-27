import java.io.*;

public class Main {
	public static final int DIV = 1000000007;
	public static long[] arr;
	public static long[] tree;
	public static int n;
	
	public static long init(int node, int start, int end) {
		if(start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		
		return tree[node] = init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end) % DIV; 
	}
	
	public static long multiple(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 1;
		
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		
		return multiple(node * 2, start, mid, left, right) * multiple(node * 2 + 1 , mid + 1, end, left, right) % DIV;
		
	}
	
	public static void update(int node, int start, int end, int idx, long value) {
		if(start == end) {
			tree[node] = value;
			return;
		}
		int mid = (start + end) / 2;
		
		if(idx <= mid) {
			update(node * 2, start, mid, idx, value);
		} else {
			update(node * 2 + 1, mid + 1, end, idx, value);
		}
		
		tree[node] = tree[2 * node] * tree[2 * node + 1] % DIV;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] nmk = br.readLine().split(" ");
		n = Integer.parseInt(nmk[0]);
		int calCnt = Integer.parseInt(nmk[1]) + Integer.parseInt(nmk[2]);
		
		arr = new long[n];
		tree = new long[4 * n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		init(1, 0, n - 1);
		
		for(int i = 0; i < calCnt; i++) {
			String[] input = br.readLine().split(" ");
			int option = Integer.parseInt(input[0]);
			
			if(option == 1) {
				int idx = Integer.parseInt(input[1]) - 1;
				long val = Long.parseLong(input[2]);
				
				update(1, 0, n - 1, idx, val);
				
			} else {
				int start = Integer.parseInt(input[1]) - 1;
				int end = Integer.parseInt(input[2]) - 1;
				
				sb.append(multiple(1, 0, n-1, start, end) % DIV + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
