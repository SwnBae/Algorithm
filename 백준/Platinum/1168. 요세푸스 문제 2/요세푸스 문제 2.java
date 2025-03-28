import java.io.*;

public class Main {
	public static int tree[];
	public static int n;
	public static int k;
	public static int size;
	
	public static int init(int node, int start, int end) {
		if(start == end) return tree[node] = 1;
		
		int mid = (start + end) / 2;
		
		return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
	}
	
	public static int remove(int node, int start, int end, int count) {
		tree[node]--;
		
		if(start == end) {
			return start;
		}
		
		int left = tree[node * 2];
		int mid = (start + end) / 2;
		
		if(count <= left) {
			return remove(node * 2, start, mid, count);
		} else {
			return remove(node * 2 + 1, mid + 1, end, count - left);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write("<");
		
		String[] nk = br.readLine().split(" ");
		n = Integer.parseInt(nk[0]);
		k = Integer.parseInt(nk[1]);
		
		tree = new int[4 * n];
		size = n;
		
		int idx = 1;
		
		init(1, 1, n);
		
		while(size != 0) {
			idx = (idx + k - 1) % size;
			
			if(idx == 0) {
				idx = size;
			}
			
			int val = remove(1, 1, n, idx);
			bw.write(Integer.toString(val));
			size--;
			
			if(size != 0) {
				bw.write(", ");
			}
		}
		
		bw.write(">");

		bw.flush();
		bw.close();
	}

}
