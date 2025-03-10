import java.io.*;

public class Main {
	public static int n;
	public static int m;
	public static int[] parent;
	public static int[] rank;
	
	public static int find(int x) {
		if(parent[x] != x) {
			parent[x]= find(parent[x]); 
		}
		return parent[x];
	}
	
	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) {
			return true;
		}
		
		if(rank[x]> rank[y]) {
			parent[y]= x; 
		} else if(rank[x]< rank[y]) {
			parent[x]= y; 
		} else {
			parent[y] = x;
			rank[x]++;
		}
		
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		
		parent = new int[n];
		rank = new int[n];
		
		for(int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 1; 
		}
		
		for(int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			if(union(a,b)) {
				System.out.println(i + 1);
				return;
			}
		}
		
		System.out.println(0);
	}

}
