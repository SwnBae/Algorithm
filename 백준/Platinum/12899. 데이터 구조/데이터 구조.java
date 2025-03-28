import java.io.*;

public class Main {
	public static final int SIZE = 2000001;
	public static int[] tree;
	
	public static void add(int node, int start, int end, int idx) {
		if(idx < start || idx > end) return;
		
		tree[node]++;
		
		if(start == end) return;
		
		int mid = (start + end) / 2;
		
		add(node * 2, start, mid, idx);
		add(node * 2 + 1, mid + 1, end, idx);
	}
	
	public static int remove(int node, int start, int end, int count) {
		tree[node]--;
		
		if(start == end) {
			return start;
		}
		int mid = (start + end) / 2;
		
		if(count <= tree[node * 2]) {
			return remove(node * 2, start, mid, count);
		} else {
			return remove(node * 2 + 1, mid + 1, end, count - tree[node * 2]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		tree = new int[SIZE * 4];
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			int op = Integer.parseInt(input[0]);
			int val = Integer.parseInt(input[1]);
			
			if(op == 1) {
				add(1, 0, SIZE - 1, val);
			} else {
				bw.write(remove(1, 0, SIZE - 1, val) + "\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
}
