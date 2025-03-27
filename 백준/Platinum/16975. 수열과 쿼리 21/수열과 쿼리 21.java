import java.io.*;

public class Main {
	public static long[] arr;
	public static long[] tree;
	public static long[] lazy;
	
	public static long init(int node, int start, int end) {
		if(start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		
		return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
	}
	
	public static void update(int node, int start, int end, int left, int right, long diff) {
		lazy_update(node, start, end);
		
		if(start > right || end < left) return;
		
		if(left <= start && end <= right) {
			tree[node] += (end - start + 1) * diff;
			
			if(start != end) {
				lazy[node * 2] += diff;
				lazy[node * 2 + 1] += diff;
			}
			
			return;
		}
		
		int mid = (start + end) / 2;
		
		update(node * 2, start, mid, left, right, diff);
		update(node * 2 + 1, mid + 1, end, left, right, diff);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	public static long pick(int node, int start, int end, int idx) {
		lazy_update(node, start, end);
		
		if(start == end) return tree[node];
		
		int mid = (start + end) / 2;
		
		if(idx <= mid) {
			return pick(node * 2, start, mid, idx);
		} else {
			return pick(node * 2 + 1, mid + 1, end, idx);
		}
	}
	
	public static void lazy_update(int node, int start, int end) {
		if(lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];
			
			if(start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			
			lazy[node] = 0;
		}
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		arr = new long[n];
		tree = new long[4 * n];
		lazy = new long[4 * n];
		
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		
		init(1, 0, n-1);
		
		int m = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < m; i++) {
			String[] cal = br.readLine().split(" ");
			int op = Integer.parseInt(cal[0]);
			
			if(op == 1) {
				int a = Integer.parseInt(cal[1]) - 1;
				int b = Integer.parseInt(cal[2]) - 1;
				long val = Long.parseLong(cal[3]);
				
				update(1,0,n-1,a,b,val);
			} else {
				int idx = Integer.parseInt(cal[1]) - 1;
				
				sb.append(pick(1, 0, n - 1, idx) + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
