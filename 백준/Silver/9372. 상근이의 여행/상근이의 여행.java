import java.io.*;

//최소의 길을 써서 모든 노드 방문 -> MST, 크루스칼 사용
public class Main {
	public static int[] parent;
	public static int[] rank;
	public static int n;
	public static int m;
	
	public static int find(int node) {
		if(node != parent[node]) {
			parent[node] = find(parent[node]);
		}
		
		return parent[node];
	}
	
	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		
		if(rank[a] > rank[b]) {
			parent[b] = a;
			rank[a] += rank[b];
		} else {
			parent[a] = b;
			rank[b] += rank[a];
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			String[] nm = br.readLine().split(" ");
			
			n = Integer.parseInt(nm[0]);
			m = Integer.parseInt(nm[1]);
			
			parent = new int[n+1];
			rank = new int[n+1];
			int cnt = 0;
			
			for(int i = 1; i <= n; i++) {
				parent[i] = i;
				rank[i] = 1;
			}
			
			for(int i = 0; i < m; i++) {
				String[] ab = br.readLine().split(" ");
				
				int a = Integer.parseInt(ab[0]);
				int b = Integer.parseInt(ab[1]);
				
				if(union(a, b)) {
					cnt++;
				}
			}
			
			System.out.println(cnt);
		}
	}
}
