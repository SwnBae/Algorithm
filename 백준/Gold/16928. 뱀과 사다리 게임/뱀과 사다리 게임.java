import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static int[] option;
	public static int[] space;
	public static boolean[] visited;
	
	public static int l;
	public static int s;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] ls = br.readLine().split(" ");
		
		l = Integer.parseInt(ls[0]);
		s = Integer.parseInt(ls[1]);
		
		option = new int[101];
		space = new int[101];
		visited = new boolean[101];
		
		for(int i = 0; i < l + s; i++) {
			String[] op = br.readLine().split(" ");
			
			option[Integer.parseInt(op[0])] = Integer.parseInt(op[1]);
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.add(new int[] {1, 0}); // idx, cnt
		visited[1] = true;
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int idx = tmp[0];
			int cnt = tmp[1];
			
			if(idx == 100) {
				System.out.println(cnt);
				return;
			}
			
			for(int i = 1; i <=6; i++) {
				if(idx + i > 100 || idx + i < 0 || visited[idx + i]) continue;
				
				visited[idx + i] = true;
				
				if(option[idx + i] != 0) {
					int tmpIdx = option[idx + i];
					
					if(visited[tmpIdx]) continue;
					
					visited[tmpIdx] = true;
					queue.add(new int[] {tmpIdx, cnt + 1});
				} else {
					queue.add(new int[] {idx + i, cnt + 1});
				}
			}
		}

	}

}
