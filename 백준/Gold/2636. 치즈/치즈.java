import java.io.*;
import java.util.ArrayDeque;

public class Main {
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};

    public static int[][] space;
    public static boolean[][] visited;
    public static int lastCount;
    public static int totalCount;
    public static int sum;
    public static int time;

    public static int n;
    public static int m;

    public static boolean outBound(int y, int x){
        return y < 0 || y >= n || x <0 || x >= m;
    }

    public static void bfs(int y, int x){
        int cnt = 0;

        ArrayDeque<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{y, x});

        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            int curY = tmp[0];
            int curX = tmp[1];


            for(int i =0; i< 4; i++){
                int tmpY = curY + dy[i];
                int tmpX = curX + dx[i];

                if(outBound(tmpY,tmpX) || visited[tmpY][tmpX]) continue;

                visited[tmpY][tmpX] = true;

                if(space[tmpY][tmpX] == 1){
                    space[tmpY][tmpX] = 0;
                    cnt++;
                } else{
                    queue.add(new int[]{tmpY,tmpX});
                }
            }
        }

        if(cnt != 0){
            lastCount = cnt;
            sum += cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        space = new int[n][m];

        sum = 0;
        totalCount = 0;
        time = 0;

        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < m; j++){
                space[i][j] = Integer.parseInt(input[j]);
                if(space[i][j] == 1){
                    totalCount++;
                }
            }
        }

        while(sum != totalCount){
            visited = new boolean[n][m];
            bfs(0,0);
            time++;
        }

        System.out.println(time);
        System.out.println(lastCount);
    }
}