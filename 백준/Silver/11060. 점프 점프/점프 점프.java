import java.io.*;
import java.util.*;
import java.util.Arrays;

public class Main {
	public static int n;
	public static int[] arr;
	
	public static int bfs() {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		boolean[] visited = new boolean[n];
		
		visited[0] = true;
		queue.add(new int[] {0, 0});
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int idx = tmp[0];
			int cnt = tmp[1];
			
			if(idx == n - 1) {
				return cnt;
			}
			
			for(int i = 0; i <= arr[idx]; i++) {
				if(idx + i >= n || visited[idx + i]) continue;
				
				visited[idx + i] = true;
				queue.add(new int[] {idx + i, cnt + 1});
			}
		}
		
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr= Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		
		System.out.println(bfs());
	}

}
