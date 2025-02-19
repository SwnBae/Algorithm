import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Graph{
    private int numVertices;
    private List<List<Integer>> adjacencyList;

    public Graph(int numVertices){
        this.numVertices = numVertices;

        adjacencyList = new ArrayList<>(numVertices);

        for(int i = 0; i < numVertices; i++){
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int destination){
        adjacencyList.get(src).add(destination);
        adjacencyList.get(destination).add(src);
    }

    public List<Integer> getAdjacencyList(int vertex){
        return adjacencyList.get(vertex);
    }
}

public class Main {
    public static Graph graph;
    public static int numVer;
    public static int edge;
    public static boolean find = false;
    public static boolean[] visited;
//    public static int[] tmp; // 확인용


    public static void dfs(int ver, int cnt){
        if(find) return;

        if(cnt == 5){
//            System.out.println(Arrays.toString(tmp));
//            System.out.println(Arrays.toString(visited));
            find = true;
            return;
        }

        List<Integer> temp = graph.getAdjacencyList(ver);
//        System.out.println("cnt: " +cnt + " ver:"+ver +" list"+ temp);

        for(int num : temp){
            if(visited[num]) continue;

            visited[num] = true;
//            tmp[cnt] = num;
            dfs(num,cnt + 1);
            visited[num] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        numVer = Integer.parseInt(nm[0]);
        edge = Integer.parseInt(nm[1]);
        graph = new Graph(numVer);
//        tmp = new int[numVer];
        visited = new boolean[numVer];

        for(int i = 0; i < edge; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            graph.addEdge(a, b);
        }

        for(int i = 0; i < numVer; i++){
            if(find) break;
//            tmp[0] = i; // 확인용

            visited[i] = true;
            dfs(i,1);
            visited[i] = false;
        }

        System.out.println(find ? 1:0);
    }
}