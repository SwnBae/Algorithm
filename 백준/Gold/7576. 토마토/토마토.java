import java.io.*;
import java.util.*;

public class Main {
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0,0,-1,1};

    public static int m;
    public static int n;
    public static int[][] space;

    public static int totalDay;
    public static int yet;

    public static ArrayDeque<int[]> queue;

    public static boolean outBound(int y, int x){
        return y < 0 || y >= n || x < 0 || x >= m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] mn = br.readLine().split(" ");
        m = Integer.parseInt(mn[0]);
        n = Integer.parseInt(mn[1]);
        space = new int[n][m];

        totalDay = 0;
        yet = 0;

        queue = new ArrayDeque<>();

        int initCnt = 0; //초기에 익은 값 세주기

        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < m; j++){
                space[i][j] = Integer.parseInt(input[j]);

                if(space[i][j] == 1){
                    queue.add(new int[]{i,j});
                    initCnt++;
                } else if(space[i][j] == 0){
                    yet++;
                }
            }
        }

        //안익은 토마토가 없다면? 다 익었다!
        if(yet == 0){
            System.out.println(0);
            return;
        }

        //큐에서 나와야 하는 토마토의 개수
        int maxCount = yet + initCnt;
        int cnt = initCnt;

        while (!queue.isEmpty()){
            int size = queue.size();

            for(int t = 0; t < size; t++){
                int[] tmp = queue.poll();
                int y = tmp[0];
                int x = tmp[1];

                for(int i = 0; i < 4; i++){
                    int tmpY = y + dy[i];
                    int tmpX = x + dx[i];

                    if(outBound(tmpY,tmpX) || space[tmpY][tmpX] != 0) continue;

                    space[tmpY][tmpX] = 1;
                    cnt++;
                    queue.add(new int[] {tmpY,tmpX});
                }
            }

            if(!queue.isEmpty()) {
                totalDay++;
            }
        }

        if(cnt == maxCount){
            System.out.println(totalDay);
        } else{
            System.out.println(-1);
        }
    }
}