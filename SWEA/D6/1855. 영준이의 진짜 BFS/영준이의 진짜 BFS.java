import java.io.*;
import java.util.*;

public class Solution {
    public static List<List<Integer>> graph;
    public static int[][] parent;
    public static int[] depth;
    public static int n;

    public static long bfs(){
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // 현재, 이전노드
        boolean[] visited = new boolean[n + 1];

        long totalCount = 0;
        int tmp = 1;
        visited[1] = true;
        queue.add(1);

        while (!queue.isEmpty()){
            int now = queue.poll();

            for(int next : graph.get(now)){
                if(visited[next]) continue;

                visited[next] = true;
                totalCount += lca(next, tmp);

                queue.add(next);
                tmp = next;
            }
        }

        return totalCount;
    }

    public static long lca(int x, int y){
        long dist = 0;

        if(depth[x] > depth[y]){
            int tmp = y;
            y = x;
            x = tmp;
        }

        if(parent[y][0] == x) return 1;

        /**
         * 큰 값부터 (1 << i)만큼 점프
         * 결국 모든 수는 2^n의 합으로 만들 수 있음
         */
        for (int i = 19; i >= 0; i--) {
            if (depth[y] - (1 << i) >= depth[x]) {
                y = parent[y][i];         // y를 2^i만큼 위로 올림
                dist += (1 << i);         // 거리 누적
            }
        }

        if(x == y) return dist;

        /**
         * 공통 조상 직전 노드까지 찾기
         */
        for (int i = 19; i >= 0; i--) {
            if (parent[x][i] != parent[y][i]) {
                x = parent[x][i];
                y = parent[y][i];
                dist += 2 * (1 << i);
            }
        }

        return dist + 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++){
            n = Integer.parseInt(br.readLine());
            graph = new ArrayList<>();
            parent = new int[n + 1][20];
            depth = new int[n + 1];

            parent[1][0] = 0;
            depth[1] = 0;

            for(int i = 0; i <= n; i++){
                graph.add(new ArrayList<>());
            }

            String[] input = br.readLine().split(" ");

            for(int i = 2; i <= n; i++){
                int p = Integer.parseInt(input[i-2]);
                graph.get(p).add(i);
                parent[i][0] = p;
                depth[i] = depth[p] + 1;
            }

            for(int i = 1; i < 20; i++){
                for(int j = 1; j <= n; j++){
                    parent[j][i] = parent[parent[j][i-1]][i-1];
                }
            }

            System.out.println("#" + t + " " + bfs());
        }
    }
}