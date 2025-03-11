import java.util.*;
import java.io.*;

public class Main {
    public static int[] dy = {1, -1, 0, 0};
    public static int[] dx = {0, 0, 1, -1};
    public static int n;
    public static int m;
    public static int minV;
    public static int[][] space;
    public static int count;

    public static List<int[]> virus;

    public static boolean outBound(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= m;
    }

    public static void dfs(int y, int x, int cnt) {
        if(cnt == 3) { // 감염공간 확인
            minV = Math.min(minV, bfs());
            return;
        }

        for(int i = y; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if((i == y && j < x) || space[i][j] != 0) continue;

                space[i][j] = 1;
                dfs(i, j, cnt+1);
                space[i][j] = 0; // 백트래킹
            }
        }
    }

    public static int bfs() { // 감염 수 확인
        boolean[][] visited = new boolean[n][m];
        int tmpCnt = 0;
        Queue<int[]> queue = new ArrayDeque<>();

        for(int t = 0; t < virus.size(); t++) {

            int y = virus.get(t)[0];
            int x = virus.get(t)[1];

            queue.add(new int[] {y, x});

            while(!queue.isEmpty()) {
                int[] tmp = queue.poll();
                int vY = tmp[0];
                int vX = tmp[1];

                if(tmpCnt == count) { // 모두 감영된다면 return;
                    return count;
                }

                if(minV < tmpCnt) { // 더 있다면 끝
                    return count;
                }

                for(int i = 0; i < 4; i++) {
                    int tmpY = vY + dy[i];
                    int tmpX = vX + dx[i];

                    if(outBound(tmpY,tmpX) || visited[tmpY][tmpX] || space[tmpY][tmpX] != 0) continue;

                    tmpCnt++;
                    visited[tmpY][tmpX] = true;

                    queue.add(new int[] {tmpY, tmpX});
                }
            }

        }
        return tmpCnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");

        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        minV = n * m;
        space = new int[n][m];
        count = 0;
        virus = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < m; j++) {
                if(input[j].equals("2")) {
                    virus.add(new int[] {i,j});
                } else if(input[j].equals("0")) {
                    count++; // 안전공간
                }

                space[i][j] = Integer.parseInt(input[j]);
            }
        }

        count -= 3;

        dfs(0,0,0);

        System.out.println(count - minV);

        //안전 공간 - 감염공간

    }

}
