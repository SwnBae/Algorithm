import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static List<List<Integer>> graph;
    public static boolean[] visited;
    public static int n;
    public static int m;

    public static void dfs(int num) {
        for(Integer n : graph.get(num)){
            if(visited[n]) continue;

            visited[n] = true;
            dfs(n);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        int cnt = 0;

        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        visited = new boolean[n + 1];
        graph = new ArrayList<>();

        for(int i = 0; i <= n; i++) { // 그래프 초기화
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for(int i = 1; i <= n; i++){
            if(visited[i]) continue;

            dfs(i);
            cnt++;
        }

        System.out.println(cnt);

    }

}