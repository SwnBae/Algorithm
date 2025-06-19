import java.io.*;
import java.util.ArrayDeque;

public class Main {
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};

    public static int n;
    public static int m;
    public static int[][] space;
    public static ArrayDeque<int[]> ice;

    public static boolean[][] checked;
    public static ArrayDeque<int[]> melt;

    public static boolean outBound(int y, int x){
        return y < 0 || y >= n || x < 0 || x >= m;
    }

    public static void checkIce(int cy, int cx){
        ArrayDeque<int[]> q = new ArrayDeque<>();

        checked[cy][cx] = true;
        q.add(new int[] {cy,cx});
        int cm = calculateMeltCnt(cy,cx);
        melt.add(new int[] {cy,cx, cm});

        while (!q.isEmpty()){
            int[] tmp = q.pollFirst();
            int y = tmp[0];
            int x = tmp[1];

            for(int i = 0; i < 4; i++){
                int tmpY = y + dy[i];
                int tmpX = x + dx[i];

                if(outBound(tmpY,tmpX) || checked[tmpY][tmpX] || space[tmpY][tmpX] == 0) continue;

                checked[tmpY][tmpX] = true;
                int m = calculateMeltCnt(tmpY,tmpX);
                melt.add(new int[] {tmpY,tmpX, m});

                q.add(new int[]{tmpY,tmpX});
            }
        }
    }

    public static void melting(){
        while(!melt.isEmpty()) {
            int[] tmp = melt.pollFirst();
            int y = tmp[0];
            int x = tmp[1];
            int m = tmp[2];

            if(space[y][x] - m <= 0) space[y][x] = 0;
            else {
                space[y][x] -= m;
                ice.add(new int[] {y, x});
            }
        }
    }

    public static int calculateMeltCnt(int y, int x){
        int m = 0;

        for(int i = 0; i < 4; i++){
            int tmpY = y + dy[i];
            int tmpX = x + dx[i];

            if(outBound(tmpY,tmpX)) continue;

            if(space[tmpY][tmpX] == 0) m++;
        }

        return m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        space = new int[n][m];

        ice = new ArrayDeque<>();
        melt = new ArrayDeque<>();

        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < m; j++){
                space[i][j] = Integer.parseInt(input[j]);
                if(space[i][j] != 0) ice.add(new int[] {i,j});
            }
        }

        int year = 0;

        while (!ice.isEmpty()){
            checked = new boolean[n][m];
            int result = 0;
            while (!ice.isEmpty()){
                int[] tmp = ice.pollFirst();
                int y = tmp[0];
                int x = tmp[1];

                if(checked[y][x]) continue;

                checkIce(y,x);
                result++;
            }

            if(result >= 2) {
                System.out.println(year);
                return;
            }

            melting();
            year++;
        }

        System.out.println(0);
    }
}