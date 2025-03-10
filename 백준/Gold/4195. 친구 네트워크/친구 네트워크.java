import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int[] rank;
	public static int[] parent;
	public static Map<String, Integer> map;
	
	public static int find(int x) {
		if(x != parent[x]) {
			parent[x] = find(parent[x]); 
		}
		
		return parent[x];
	}
	
	public static int union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) {
			return rank[x];
		}
		
		if(rank[x] > rank[y]) {
			parent[y] = x;
			rank[x]+= rank[y];
			return rank[x];
		} else {
			parent[x] = y;
			rank[y]+= rank[x];
			return rank[y];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			StringBuilder sb = new StringBuilder();
			
			int cnt = 1;
			
			n = Integer.parseInt(br.readLine());
			rank = new int[2*n + 1];
			parent = new int[2*n + 1];
			map = new HashMap<>();
			
			for(int i = 0; i < n; i++) {
				if(i > 0) sb.append("\n");
				
				String[] input = br.readLine().split(" ");
				
				for(int j = 0; j < 2; j++) {
					if(map.containsKey(input[j])) {
						continue;
					} else {
						rank[cnt] = 1;
						parent[cnt] = cnt;
						map.put(input[j], cnt++);
					}
				}
				
				int a = map.get(input[0]);
				int b = map.get(input[1]);
				
				sb.append(union(a, b));
			}
			
			System.out.println(sb.toString());
		}
	}
}
