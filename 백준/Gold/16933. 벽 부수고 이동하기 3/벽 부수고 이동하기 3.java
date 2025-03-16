import java.io.*;
import java.util.*;

public class Main {
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,1,-1};
    public static int[][][] visited;
    public static int[][] space;
    public static int n;
    public static int m;
    public static int k;

    public static boolean outBound(int y, int x){
        return y < 0 || y >= n || x < 0 || x >= m;
    }

    public static int bfs(){
        int result = -1;

        ArrayDeque<int[]> deque = new ArrayDeque<>();

        visited[0][0][0] = 1;
        deque.add(new int[]{0,0,0,1,1}); // y, x, 부순 벽돌 수, 낮밤(1,2), cnt
        //00 방문 x, 01 낮, 10 밤
        while (!deque.isEmpty()){
            int[] tmp = deque.poll();
            int y = tmp[0];
            int x = tmp[1];
            int bC = tmp[2];
            int day = tmp[3];
            int cnt = tmp[4];

            if(y == n-1 && x == m-1){
                return cnt;
            }

            for(int i = 0; i < 4; i++){
                int tmpY = y + dy[i];
                int tmpX = x + dx[i];
                int tmpDay = day;
                int breakCount = bC;

                if(outBound(tmpY,tmpX)) continue;

                int nextDay = 0;

                if(tmpDay == 1){
                    nextDay = tmpDay << 1;
                } else if(tmpDay == 2){
                    nextDay = tmpDay >> 1;
                }


                if(space[tmpY][tmpX] == 1){ // 다음날이 밤이어야 한다.
                    if(breakCount == k ||
                            (visited[tmpY][tmpX][breakCount] & nextDay) > 0) continue;
                    if(nextDay == 2) {
                        breakCount++;
                    } else{
                        tmpY = y;
                        tmpX = x;
                    }
                }

                if((visited[tmpY][tmpX][breakCount] & nextDay) == 0){
                    visited[tmpY][tmpX][breakCount] |= nextDay;
                    deque.add(new int[]{tmpY,tmpX,breakCount,nextDay,cnt + 1});
                }

            }

        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nmk = br.readLine().split(" ");

        n = Integer.parseInt(nmk[0]);
        m = Integer.parseInt(nmk[1]);
        k = Integer.parseInt(nmk[2]);
        space = new int[n][m];
        visited = new int[n][m][k+1];

        for(int i = 0; i < n; i++){
            String input = br.readLine();
            for(int j = 0; j < m; j++){
                space[i][j] = input.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }
}