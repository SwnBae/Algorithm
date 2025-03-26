import java.io.*;
import java.util.*;

public class Main {
	public static long[] arr;
	public static long[] tree;
	public static int n;
	
	public static long init(int start, int end, int node) {
		if(start == end) return tree[node] = arr[start];
		int mid = (start + end) / 2;
		
		return tree[node] = init(start,mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}
	
	public static long sum(int start, int end, int node, int left, int right) {
		if(left > end || right < start) return 0;
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
	
	public static void update(int start, int end, int node, int index, long diff) {
		if(index < start || index > end) return;
		
		tree[node] += diff;
		if(start == end) return;
		int mid = (start + end) / 2;
		
		update(start, mid, node * 2, index, diff);
		update(mid + 1, end, node * 2 + 1, index, diff);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nmk = br.readLine().split(" ");
		
		n = Integer.parseInt(nmk[0]);
		int calCnt = Integer.parseInt(nmk[1]) + Integer.parseInt(nmk[2]);
		arr = new long[n];
		tree = new long[4 * n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		//초기화
		init(0, n - 1, 1);
		
		for(int i = 0; i < calCnt; i++) {
			String[] input = br.readLine().split(" ");
			int option = Integer.parseInt(input[0]);
			
			if(option == 1) {
				int idx = Integer.parseInt(input[1]) - 1;
				long num = Long.parseLong(input[2]);
				long dif = num - arr[idx];
				
				update(0, n - 1, 1, idx, dif);
				
				arr[idx] = num;
				
			} else {
				int left = Integer.parseInt(input[1]) - 1;
				int right = Integer.parseInt(input[2]) - 1;
				
				System.out.println(sum(0, n - 1, 1, left, right));
			}
		}
		
	}

}
