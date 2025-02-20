import java.io.*;
import java.util.*;

public class Main {
	public static StringBuilder stringDfs;
	public static StringBuilder stringBfs;

	public static List<List<Integer>> graph;
	public static int n;
	public static int m;
	public static int v;
	public static boolean[] visited;
	public static boolean endDfs;

	public static boolean checkAllTrue(List<Integer> temp) {
		for (Integer n : temp) { // 방문하지 않은 노드가 있다면?
			if (!visited[n])
				return false;
		}
		return true;
	}

	public static void dfs(int num) {
		//if (endDfs) return;

//		if (checkAllTrue(graph.get(num))) { // 더 내려갈 곳이 없으면 -> x, 나와 인접한 간선들이 모두 true여야 종료다.
//			stringDfs.append(num);
//			endDfs = true;
//			return;
//		} // 아니다..
		
		
		// 모든 정점을 탐색했거나, 모든 간선을 탐색했다면? -> 아니다..
//		if(cnt == n || cnt == m) { // 종료조건 -> 아니다.. 정점 > 간선이면?
//			stringDfs.append(num + " ");
//			endDfs = true;
//			return;
//		}
		
		

		stringDfs.append(num + " "); // 숫자 넣기

		visited[num] = true; // 방문처리
		
		for (Integer n : graph.get(num)) {
			if (visited[n])
				continue;

			dfs(n);
		}
		
		//visited[num] = false; // 헤제
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		v = Integer.parseInt(input[2]);

		graph = new ArrayList<>(); // 정점의 개수로 그래프 만들기
		visited = new boolean[n + 1]; // 1부터..
		stringDfs = new StringBuilder();
		stringBfs = new StringBuilder();
		endDfs = false;

		for (int i = 0; i < n + 1; i++) { // 각 정점 초기화
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) { // 정점마다 간선 추가, 오름차순 정렬.. -> 인접한 정점이 있다면, 작은 순서대로 탐색한다.
			String[] va = br.readLine().split(" ");
			int ver = Integer.parseInt(va[0]);
			int des = Integer.parseInt(va[1]);

			graph.get(ver).add(des);
			graph.get(des).add(ver);
			
			Collections.sort(graph.get(ver));
			Collections.sort(graph.get(des));
		}

		dfs(v);
		
		visited = new boolean[n + 1];
		
		//BFS를 구현할 큐 생성
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		queue.add(v);
		
		while(!queue.isEmpty()) {
			int tmp = queue.pollFirst();
			
			if(visited[tmp]) continue;
			
			List<Integer> tmpList = graph.get(tmp);
			
//			if(checkAllTrue(tmpList)) { // 종료조건, 더 나아갈 곳이 없다면? -> 아니다..
//				stringBfs.append(tmp);
//				break;
//			}
			
			visited[tmp] = true;
			stringBfs.append(tmp + " ");
			
			for(Integer n : tmpList) {
				queue.add(n);
			}
			
		}

		System.out.println(stringDfs.toString());
		System.out.println(stringBfs.toString());

	}

}
