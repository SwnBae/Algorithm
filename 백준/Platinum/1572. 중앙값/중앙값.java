import java.io.*;

public class Main {
	public static final int SIZE = 250001;
	public static int[] tree;
	public static int[] arr;
	
	public static void update(int node, int start, int end, int idx, int diff) {
		if(idx < start || idx > end) return;
		
		tree[node] += diff;
		
		if(start == end) return;
		
		int mid = (start + end) / 2;
		
		update(node * 2, start, mid, idx, diff);
		update(node * 2 + 1, mid + 1, end, idx, diff);
	}
	
	public static int pick(int node, int start, int end, int count) {
		if(start == end) return start;
		
		int mid = (start + end) / 2;
		int left = tree[node * 2];
		
		if(count <= left) {
			return pick(node * 2 , start, mid, count);
		} else {
			return pick(node * 2 + 1, mid + 1, end, count - left);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nk = br.readLine().split(" ");
		
		int n = Integer.parseInt(nk[0]);
		int k = Integer.parseInt(nk[1]);
		int mid = (k + 1) / 2;
		int size = 0;
		long result = 0;
		
		tree = new int[SIZE * 4];
		arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			size++;
			arr[i] = Integer.parseInt(br.readLine());
			update(1, 0, SIZE - 1, arr[i], 1);
			
			if(size == k) {
				result += (long) pick(1, 0, SIZE - 1, mid);
				
				size--;
				update(1, 0, SIZE - 1, arr[i - k + 1], - 1);
			}
		}
		
		System.out.println(result);

	}

}
