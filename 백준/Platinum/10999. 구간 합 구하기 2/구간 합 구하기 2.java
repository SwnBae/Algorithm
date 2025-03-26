import java.io.*;
import java.util.*;

public class Main {
	public static long[] arr;
	public static long[] tree;
	public static long[] lazy;
	public static int n;
	
	public static long init(int start, int end, int node) {
		if(start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}
	
	public static long sum(int start, int end, int node, int left, int right) {
		lazy_update(node, start, end);
		
		if(left > end || right < start) return 0;
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
	
	public static void update(int start, int end, int node, int left, int right, long diff) {
		lazy_update(node, start, end);
		
		if(right < start || left > end) return;
		//구간에 속한다면? => 나만 업데이트 해주고, 리프노드에 lazy 넘긴다.
		if(left <= start && end <= right) {
			tree[node] = tree[node] + (end - start + 1) * diff; // 내 아래의 리프 노드 개수만큼
			
			if(start != end) {
				lazy[node * 2] = lazy[node * 2] + diff;
				lazy[node * 2 + 1] = lazy[node * 2 + 1] + diff;
			}
			return;
		}
		
		// 구간에 속하지 않는다면?
		
		int mid = (start + end) / 2;
		update(start, mid, node * 2, left, right, diff);
		update(mid + 1, end, node * 2 + 1, left, right, diff);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	public static void lazy_update(int node, int start, int end) {
		if(lazy[node] != 0) {
			tree[node] = tree[node] + (end - start + 1) * lazy[node];
			
			//내가 자식들 값이 당장 필요 없는 경우, 즉 리프노드가 아닐 경우?
			if(start != end) {
				lazy[node * 2] = lazy[node * 2] + lazy[node];
				lazy[node * 2 + 1] = lazy[node * 2 + 1] + lazy[node];
			}
			
			lazy[node] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nmk = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(nmk[0]);
		int calCnt = Integer.parseInt(nmk[1]) + Integer.parseInt(nmk[2]);
		arr = new long[n];
		tree = new long[4 * n];
		lazy = new long[4 * n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		init(0, n-1, 1);
		
		for(int i = 0; i < calCnt; i++) {
			String[] input = br.readLine().split(" ");
			int option = Integer.parseInt(input[0]);
			
			if(option == 1) {
				int left = Integer.parseInt(input[1]) - 1;
				int right = Integer.parseInt(input[2]) - 1;
				long diff = Long.parseLong(input[3]);
				
				update(0, n - 1, 1, left, right, diff);
				
				
			} else {
				int left = Integer.parseInt(input[1]) - 1;
				int right = Integer.parseInt(input[2]) - 1;
				
				
				sb.append(sum(0, n - 1, 1, left, right) + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
