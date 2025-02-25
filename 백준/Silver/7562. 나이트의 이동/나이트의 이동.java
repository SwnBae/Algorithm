import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static int[] dy = {-2, -2, -1, -1, 1, 1, 2, 2};
    public static int[] dx = {1, -1, 2, -2, 2, -2, 1, -1};
    public static int n;
    public static boolean[][] visited;
    public static int stY;
    public static int stX;
    public static int tgY;
    public static int tgX;

    public static boolean outBound(int y, int x){
        return y < 0 || y >= n || x < 0 || x >= n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++){
            int result = 0;

            n = Integer.parseInt(br.readLine());

            String[] st = br.readLine().split(" ");
            stY = Integer.parseInt(st[0]);
            stX = Integer.parseInt(st[1]);

            String[] tg = br.readLine().split(" ");
            tgY = Integer.parseInt(tg[0]);
            tgX = Integer.parseInt(tg[1]);

            visited = new boolean[n][n];

            Queue<int[]> queue = new ArrayDeque<>();

            queue.add(new int[] {stY,stX,0});

            while (!queue.isEmpty()){
                int[] tmp = queue.poll();

                int tmpY = tmp[0];
                int tmpX = tmp[1];
                int cnt = tmp[2];

                if(tmpY == tgY && tmpX == tgX){
                    result = cnt;
                    break;
                }

                //방문 + cnt처리
                if(visited[tmpY][tmpX]) continue;
                visited[tmpY][tmpX] = true;

                for(int i = 0; i < 8; i++){
                    int y = tmpY + dy[i];
                    int x = tmpX + dx[i];

                    if(outBound(y,x)) continue;

                    queue.add(new int[] {y,x,cnt + 1});
                }

            }

            System.out.println(result);

        }
    }
}