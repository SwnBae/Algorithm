import java.io.*;

//경로압축 + rank최적화
public class Main {
	public static int n;
	public static int m;
	public static int[] parents;
	public static int[] rank;
	
	public static int find(int x) {
		if (parents[x] != x) {
			parents[x] = find(parents[x]);
        }
        return parents[x];
	}
	
	public static void union(int x, int y) {
		int tmpX = find(x);
		int tmpY = find(y);
		
		if(tmpY == tmpX) return;
		
		if(rank[tmpX] > rank[tmpY]) {
			parents[tmpY] = tmpX;
			rank[tmpX] += rank[tmpY]; 
		} else {
			parents[tmpX] = tmpY;
			rank[tmpY] += rank[tmpX]; 
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		parents = new int[n + 1];
		rank = new int[n+1];
		
		for(int i = 1; i <= n; i++) {
			parents[i] = i;
			rank[i] = 1; 
		}
		
		for(int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[1]);
			int b = Integer.parseInt(input[2]);
			
			if(input[0].equals("0")) {
				union(a, b);
			} else {
				if(find(a) == find(b)) {
					bw.append("YES\n");
					//System.out.println("YES");
				} else {
					bw.append("NO\n");
					//System.out.println("NO");
				}
			}
		}
		
		bw.flush();
		bw.close();

	}

}
