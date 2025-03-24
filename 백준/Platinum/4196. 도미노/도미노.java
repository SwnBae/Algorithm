import java.util.*;
import java.io.*;

public class Main {
    public static int id;
    public static int[] d;
    public static boolean[] finished;
    public static List<List<Integer>> graph;
    public static List<List<Integer>> sccList;
    public static ArrayDeque<Integer> stack;
    public static int[] sccIndex;
    public static int[] degree;

    public static int result;

    public static int dfs(int node) {
        d[node] = ++id;
        stack.add(node);

        int parent = d[node];

        for(int next : graph.get(node)) {
            if(d[next] == 0) {
                parent = Math.min(parent, dfs(next));
            } else if(!finished[next]) {
                parent = Math.min(parent, d[next]);
            }
        }

        if(parent == d[node]) {
            List<Integer> scc = new ArrayList<>();

            while(true) {
                int tmp = stack.pollLast();

                scc.add(tmp);
                finished[tmp] = true;
                sccIndex[tmp] = sccList.size();

                if(tmp == node) break;
            }

            sccList.add(scc);
        }

        return parent;
    }

    public static void calculateDegree() {
        degree = new int[sccList.size()];

        for(int n = 1; n < graph.size(); n++) {
            for(int node : graph.get(n)) {
                int uScc = sccIndex[n];
                int vScc = sccIndex[node];

                if(uScc != vScc) {
                    degree[vScc]++;
                }
            }
        }
    }

    public static void topologySort() {
        for(int i = 0; i < degree.length; i++) {
            if(degree[i] == 0) {
                result++;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++){
            String[] nm = br.readLine().split(" ");

            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            sccIndex = new int[n+1];
            d = new int[n + 1];
            finished = new boolean[n + 1];

            graph = new ArrayList<>();
            sccList = new ArrayList<>();
            stack = new ArrayDeque<>();
            result = 0;

            for(int i = 0; i <= n; i++){
                graph.add(new ArrayList<>());
            }

            for(int i = 0; i < m; i++){
                String[] ab = br.readLine().split(" ");
                int a = Integer.parseInt(ab[0]);
                int b = Integer.parseInt(ab[1]);

                graph.get(a).add(b);
            }

            for(int i = 1; i <= n; i++){
                if(!finished[i]) dfs(i);
            }

            calculateDegree();
            topologySort();

            System.out.println(result);
        }

    }

}