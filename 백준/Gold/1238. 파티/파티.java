import java.io.*;
import java.util.*;

class Edge{
    int node;
    int value;

    public Edge(int node, int value) {
        this.node = node;
        this.value = value;
    }
}

public class Main {
    public static final int INF = 20000000;
    public static List<List<Edge>> graph;
    public static int n;
    public static int m;
    public static int des;
    public static int max;

    public static int dijk(int st, int end){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) ->
                Integer.compare(o1.value,o2.value));
        int[] dis = new int[n + 1];
        Arrays.fill(dis,INF);

        dis[st] = 0;
        pq.add(new Edge(st,0));

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if(cur.node == end){
                return dis[cur.node];
            }

            if(cur.value > dis[cur.node]) continue;

            for(Edge next : graph.get(cur.node)){
                if(dis[next.node] > dis[cur.node] + next.value){
                    dis[next.node] = dis[cur.node] + next.value;
                    pq.add(new Edge(next.node, dis[next.node]));
                }
            }
        }
        return dis[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        des = Integer.parseInt(input[2]);
        max = 0;

        graph = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            String[] ipt = br.readLine().split(" ");
            int a = Integer.parseInt(ipt[0]);
            int b = Integer.parseInt(ipt[1]);
            int c = Integer.parseInt(ipt[2]);

            graph.get(a).add(new Edge(b,c));
        }

        for(int i = 1; i <=n; i++){
            int tmp = dijk(i,des) + dijk(des,i);

            max = Math.max(max, tmp);
        }

        System.out.println(max);
    }
}
