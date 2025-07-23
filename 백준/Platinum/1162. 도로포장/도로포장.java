import java.io.*;
import java.util.*;

class Edge {
    int node;
    long weight;
    int cnt;

    public Edge(int node, long weight, int cnt) {
        this.node = node;
        this.weight = weight;
        this.cnt = cnt;
    }
}

public class Main {

    public static int n;
    public static int m;
    public static int k;
    public static List<List<Edge>> graph;
    public static long minTime;

    public static void dijk(){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.weight != o2.weight) return Long.compare(o1.weight, o2.weight);

            return Long.compare(o1.cnt, o2.cnt);
        });

        long[][] dist = new long[n + 1][k + 1];

        for(int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        pq.add(new Edge(1, 0, 0));
        dist[1][0] = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();
            int curNode = cur.node;
            long curVal = cur.weight;
            int curCnt = cur.cnt;

            if (dist[curNode][curCnt] < curVal) continue;

            if(curNode == n) {
                minTime = curVal;
                return;
            }

            for(Edge next : graph.get(curNode)){
                if(dist[next.node][curCnt] > curVal + next.weight) {
                    dist[next.node][curCnt] = curVal + next.weight;
                    pq.add(new Edge(next.node, curVal + next.weight, curCnt));
                }

                if(curCnt < k && dist[next.node][curCnt + 1] > curVal) {
                    dist[next.node][curCnt + 1] = curVal;
                    pq.add(new Edge(next.node, curVal, curCnt + 1));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] init = br.readLine().split(" ");

        graph = new ArrayList<>();
        n = Integer.parseInt(init[0]);
        m = Integer.parseInt(init[1]);
        k = Integer.parseInt(init[2]);
        minTime = Long.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");

            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            long value = Integer.parseInt(input[2]);

            graph.get(a).add(new Edge(b, value, 0));
            graph.get(b).add(new Edge(a, value, 0));
        }

        dijk();
        System.out.println(minTime);
    }
}
