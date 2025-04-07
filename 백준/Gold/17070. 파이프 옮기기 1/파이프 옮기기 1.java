import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
    public static int[] dy = {0, 1, 1};
    public static int[] dx = {1, 1, 0};
    public static int n;
    public static int[][] space;
    public static int[][] count;

    public static boolean outBound(int y, int x){
        return y < 0 || y >= n || x < 0 || x >= n;
    }

    public static boolean checkCross(int y, int x){
        for(int i = 0; i < 3; i++){
            int tmpY = y + dy[i];
            int tmpX = x + dx[i];

            if(outBound(tmpY,tmpX) || space[tmpY][tmpX] == 1) return true;
        }

        return false;
    }

    public static int bfs(){
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{0,1,0});

        while (!queue.isEmpty()){
            int[] tmp = queue.poll();
            int y = tmp[0];
            int x = tmp[1];
            int preDir = tmp[2];

            if(y == n - 1 && x == n - 1){
                continue;
            }

            for(int i = 0; i < 3; i++){
                if((i == 0 && preDir == 2) || (i == 2 && preDir == 0)) continue;

                if(i == 1){
                    if(checkCross(y,x)) continue;
                }

                int tmpY = y + dy[i];
                int tmpX = x + dx[i];

                if(outBound(tmpY,tmpX) || space[tmpY][tmpX] == 1) continue;

                count[tmpY][tmpX]++;

                queue.add(new int[]{tmpY,tmpX,i});
            }
        }

        return count[n-1][n-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        space = new int[n][n];
        count = new int[n][n];

        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                space[i][j] = Integer.parseInt(input[j]);
            }
        }

        if(space[n-1][n-1] == 1){
            System.out.println(0);
            return;
        }

        System.out.println(bfs());

    }
}