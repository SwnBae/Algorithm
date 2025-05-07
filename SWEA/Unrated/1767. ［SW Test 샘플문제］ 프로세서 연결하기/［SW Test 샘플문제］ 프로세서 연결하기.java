import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static int[] dy = {-1,0,1,0};
    public static int[] dx = {0,-1,0,1};

    public static List<int[]> cores;
    public static boolean[][] space;
    public static int[] minCon;
    public static int n;
    public static int coreCnt;

    public static int maxConnect;

    public static boolean outBound(int y, int x){
        return y < 0 || y >= n || x < 0 || x >= n;
    }

    public static void dfs(int idx, int count, int connect){
        if (connect + (coreCnt - idx) < maxConnect) return;

        if(idx == coreCnt){
            if(connect >= maxConnect){
                maxConnect = connect;
                minCon[maxConnect] = Math.min(minCon[maxConnect], count);
            }
            return;
        }

        int[] tmp = cores.get(idx);
        int y = tmp[0];
        int x = tmp[1];

        for(int i = 0; i < 4; i++){
            int tmpCnt = check(y,x,i);
            if(tmpCnt == -1) continue;

            dfs(idx + 1, count + tmpCnt, connect + 1);
            uncheck(y,x,i,tmpCnt);
        }

        dfs(idx + 1, count, connect);
    }

    public static int check(int y, int x, int dir){
        int count = 0;

        for(int i = 1; i <= n; i++){
            int tmpY = y + (i * dy[dir]);
            int tmpX = x + (i * dx[dir]);

            if(outBound(tmpY,tmpX)) {
                break;
            }

            if(space[tmpY][tmpX]) return -1;

            count++;
        }

        for(int i = 1; i <= count; i++){
            int tmpY = y + (i * dy[dir]);
            int tmpX = x + (i * dx[dir]);

            space[tmpY][tmpX] = true;
        }

        return count;
    }

    public static void uncheck(int y, int x, int dir, int count){
        for(int i = 1; i <= count; i++){
            int tmpY = y + (i * dy[dir]);
            int tmpX = x + (i * dx[dir]);

            space[tmpY][tmpX] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++){
            n = Integer.parseInt(br.readLine());
            cores = new ArrayList<>();
            space = new boolean[n][n];

            for(int i = 0; i < n; i++){
                String[] input = br.readLine().split(" ");
                for(int j = 0; j < n; j++){
                    if(Integer.parseInt(input[j]) == 1){
                        space[i][j] = true;
                        if(i != 0 && i != n - 1 && j != 0 && j != n - 1){
                            cores.add(new int[] {i,j});
                        }
                    }
                }
            }

            coreCnt = cores.size();
            maxConnect = 0;
            minCon = new int[coreCnt + 1];

            for(int i = 0; i <= coreCnt; i++){
                minCon[i] = Integer.MAX_VALUE;
            }

            dfs(0,0,0);

            System.out.println("#" + t + " " + minCon[maxConnect]);
        }
    }
}