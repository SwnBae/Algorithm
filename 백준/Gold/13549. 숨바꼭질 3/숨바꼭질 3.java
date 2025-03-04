import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

//0 - 1 bfs, 낮은 가중치부터 수행해야한다..!
public class Main {
	public static int n;
	public static int k;
	public static boolean[] visited;
	
	public static boolean outBound(int idx) {
		return idx < 0 || idx > 100000;
	}
	
	public static int bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		int result = 0;
		
		queue.add(new int[] {n,0});
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int idx = tmp[0];
			int cnt = tmp[1];
			
			if(idx == k) {
				result = cnt;
				break;
			}
			
			if(visited[idx]) continue;
			visited[idx] = true;
			
			if(!outBound(idx * 2)) queue.add(new int[] {idx * 2, cnt});
			if(!outBound(idx - 1)) queue.add(new int[] {idx - 1, cnt + 1});
			if(!outBound(idx + 1)) queue.add(new int[] {idx + 1, cnt + 1});
		}
		
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		n = Integer.parseInt(input[0]);
		k = Integer.parseInt(input[1]);
		visited = new boolean[100001];
		
		System.out.println(bfs());

	}

}
