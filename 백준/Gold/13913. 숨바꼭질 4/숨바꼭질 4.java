import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int k;
    public static boolean[] visited;
    public static int[] parent;
    public static Stack<Integer> move;

    public static boolean outBound(int idx){
        return idx < 0 || idx > 100000;
    }

    public static int bfs(){
        Queue<int[]> queue = new ArrayDeque<>();
        int result = 0;

        queue.add(new int[]{n,0,n});

        while (!queue.isEmpty()){
            int[] tmp = queue.poll();
            int idx = tmp[0];
            int time = tmp[1];
            int p = tmp[2];

            if(visited[idx]) continue;
            visited[idx] = true;
            parent[idx] = p;

            if(idx == k){
                result = time;
                setMove();
                break;
            }

            if(!outBound(idx - 1)) queue.offer(new int[]{idx - 1, time + 1,idx});
            if(!outBound(idx + 1)) queue.offer(new int[]{idx + 1, time + 1,idx});
            if(!outBound(idx * 2)) queue.offer(new int[]{idx * 2, time + 1,idx});
        }

        return result;
    }

    public static void setMove(){
        int start = n;
        int idx = k;

        move.push(idx);

        while(idx != start){
            move.push(parent[idx]);
            idx = parent[idx];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        visited = new boolean[100001];
        parent = new int[100001];
        move = new Stack<>();

        System.out.println(bfs());

        while(!move.isEmpty()){
            sb.append(move.pop() + " ");
        }

        System.out.println(sb.toString());
    }
}