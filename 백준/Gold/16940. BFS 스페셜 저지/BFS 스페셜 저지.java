import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//층별로 비교하자
public class Main {
	public static List<List<Integer>> graph;
	public static int[] result;
	public static int n;
	public static boolean[] visited;
	
	public static boolean bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		queue.add(1); // 노드번호, cnt
		visited[1] = true;
		int idx = 1;

		while (!queue.isEmpty() ) { 			
			int nd = queue.poll();
			int chCount = 0;

			List<Integer> temp = graph.get(nd);

			for (int i = 0; i < temp.size(); i++) {
				int next = temp.get(i);

				if (visited[next])
					continue;

				visited[next] = true;
				chCount++;
			}
			
			for(int i = idx; i < idx + chCount; i++) {
				if(!visited[result[i]]) {
					return false;
				} else {
					queue.add(result[i]);
				}
			}
			
			idx += chCount;
		}
		
		return true;
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		graph = new ArrayList<>();
		
		result = new int[n];
		visited = new boolean[n + 1];

		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 1; i < n; i++) {
			String[] input = br.readLine().split(" ");

			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		String[] input = br.readLine().split(" ");

		for (int i = 0; i < n; i++) {
			result[i] = Integer.parseInt(input[i]);
		}

		boolean find = bfs();
		
		if(result[0] != 1) {
			find = false;
		}
		
		System.out.println(find ? 1 : 0);

	}

}
