import java.io.*;

public class Main {
	public static int n;
	public static int m;
	public static int[] parents;
	
	public static int find(int x) {
		if(parents[x]== x) {
			return x;
		} else {
			int y = find(parents[x]);
			parents[x] = y;
			return y;
		}
	}
	
	public static void union(int x, int y) {
		int tmpX = find(x);
		int tmpY = find(y);
		
		if(tmpX != tmpY) {
			parents[tmpY] = tmpX;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		parents = new int[n + 1];
		
		for(int i = 1; i <= n; i++) {
			parents[i]= i; 
		}
		
		for(int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[1]);
			int b = Integer.parseInt(input[2]);
			
			if(input[0].equals("0")) {
				union(a, b);
			} else {
				if(find(a) == find(b)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
		
		

	}

}
