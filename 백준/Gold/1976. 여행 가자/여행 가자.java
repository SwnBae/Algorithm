import java.io.*;

public class Main {
	public static int n;
	public static int[] parent;
	public static int[] rank;
	
	public static int find(int x) {
		if(parent[x]!= x) {
			parent[x]= find(parent[x]); 
		}
		
		return parent[x];
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) {
			return;
		}
		
		if(rank[x]> rank[y]) {
			parent[y] = x; 
			rank[x]+= rank[y]; 
		} else {
			parent[x] = y;
			rank[y] += rank[x]; 
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		parent = new int[n + 1];
		rank = new int[n + 1];
		
		for(int i = 1; i <= n; i++) {
			parent[i]= i;
			rank[i]= 1; 
		}
		
		for(int i = 1; i <= n; i++) {
			String[] input = br.readLine().split(" ");
			
			for(int j = 1; j <= n; j++) {
				if(Integer.parseInt(input[j-1]) == 0) continue;
				
				union(i, j);
			}
		}
		
		String[] input = br.readLine().split(" ");
		int pre = find(Integer.parseInt(input[0]));
		
		for(int i = 1; i < m; i++) {
			int tmp = find(Integer.parseInt(input[i]));
			
			if(pre != tmp) {
				System.out.println("NO");
				return;
			}
			
			pre = tmp;
		}
		
		System.out.println("YES");
	}
}
