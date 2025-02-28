import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static int n;
    public static int[][] space;
    public static boolean[][] visited;
    public static int idx;
    public static int min;
    public static Queue<int[]> coast;

    public static boolean outBound(int y, int x){
        return y < 0 || y >= n || x < 0 || x >= n;
    }

    //다음 위치 1이어야함
    public static void dfs(int y, int x, int idx){
        space[y][x] = idx;
        boolean cst = false;

        for(int i = 0; i < 4; i++){
            int tmpY = y + dy[i];
            int tmpX = x + dx[i];

            if(!outBound(tmpY,tmpX)){
                if(space[tmpY][tmpX] == 0 && !cst){
                    coast.offer(new int[]{y,x,idx});
                    cst = true;
                } else if(space[tmpY][tmpX] == 1){
                    dfs(tmpY,tmpX,idx);
                }
            }
        }
    }

    public static int bfs(int y, int x, int idx){
        Queue<int[]> queue = new ArrayDeque<>();

        int result = 0;
        visited = new boolean[n][n];

        queue.offer(new int[] {y,x,0});

        while (!queue.isEmpty()){
            int[] tmp = queue.poll();

            int tmpY = tmp[0];
            int tmpX = tmp[1];
            int cnt = tmp[2];

            // 최소 넘어가면, 그냥 탐색 중단
            if(cnt > min) {
                return Integer.MAX_VALUE;
            }

            if(visited[tmpY][tmpX]) continue;

            visited[tmpY][tmpX] = true;

            if(space[tmpY][tmpX] != 0 && space[tmpY][tmpX] != idx){
                result = cnt - 1;
                break;
            }

            for(int i = 0; i < 4; i++){
                int bY = tmpY + dy[i];
                int bX = tmpX + dx[i];

                if(outBound(bY,bX) || visited[bY][bX] || space[bY][bX] == idx) continue;

                queue.offer(new int[] {bY,bX,cnt+1});
            }
        }

        if(result == 0) return Integer.MAX_VALUE;

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        idx = 2;
        n = Integer.parseInt(br.readLine());
        space = new int[n][n];
        min = Integer.MAX_VALUE;
        coast = new ArrayDeque<>();

        for(int i = 0 ; i < n; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                space[i][j] = Integer.parseInt(input[j]);
            }
        }

        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n; j++){
                if(space[i][j] == 1){
                    dfs(i,j,idx);
                    idx++;
                }
            }
        }

        while(!coast.isEmpty()){
            int tmp[] = coast.poll();

            min = Math.min(min,bfs(tmp[0],tmp[1],tmp[2]));
        }

        System.out.println(min);
    }
}