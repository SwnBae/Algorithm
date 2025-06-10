import java.io.*;
import java.util.*;

class Edge{
    int node;
    int cost;

    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}

public class Main {
    public static int n;
    public static int m;
    public static List<List<Edge>> graph;

    public static int start;
    public static int end;

    public static int result;

    public static void dijk(){
        PriorityQueue<Edge> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o2.cost, o1.cost)));

        int[] maxWeight = new int[n + 1];
        maxWeight[start] = Integer.MAX_VALUE;

        for(Edge eg : graph.get(start)){
            pq.add(new Edge(eg.node, eg.cost));
            maxWeight[eg.node] = eg.cost;
        }

        while (!pq.isEmpty()){
            Edge edge = pq.poll();

            if(edge.node == end){
                result = edge.cost;
                return;
            }

            for(Edge next : graph.get(edge.node)){
                if(edge.cost <= next.cost){
                    if(maxWeight[next.node] < edge.cost){
                        pq.add(new Edge(next.node, edge.cost));
                        maxWeight[next.node] = edge.cost;
                    }
                } else{
                    if(maxWeight[next.node] < next.cost){
                        pq.add(new Edge(next.node, next.cost));
                        maxWeight[next.node] = next.cost;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        result = 0;

        graph = new ArrayList<>();

        for(int i = 0 ; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            String[] input = br.readLine().split(" ");

            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);

            graph.get(start).add(new Edge(end, c));
            graph.get(end).add(new Edge(start, c));
        }

        String[] se = br.readLine().split(" ");

        start = Integer.parseInt(se[0]);
        end = Integer.parseInt(se[1]);

        dijk();
        System.out.println(result);
    }
}