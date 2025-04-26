import java.io.*;
import java.util.ArrayDeque;
 
public class Solution {
    public static int[] dy = {-1, -1, 1, 1, 0, 0, 1, -1};
    public static int[] dx = {-1, 1, -1, 1, -1, 1, 0, 0};
 
    public static int n;
    public static boolean[][] space;
    public static boolean[][] visited;
    public static int totalCnt;
 
    public static boolean outBound(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= n;
    }
 
    public static boolean notBomb(int y, int x) {
        for (int i = 0; i < 8; i++) {
            int tmpY = y + dy[i];
            int tmpX = x + dx[i];
 
            if (outBound(tmpY, tmpX)) continue;
            if (space[tmpY][tmpX]) return false;
        }
 
        return true;
    }
 
    public static void bfs(int curY, int curX) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
 
        visited[curY][curX] = true;
        queue.add(new int[]{curY, curX});
 
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int y = tmp[0];
            int x = tmp[1];
 
            for (int i = 0; i < 8; i++) {
                int tmpY = y + dy[i];
                int tmpX = x + dx[i];
 
                if (outBound(tmpY, tmpX) || visited[tmpY][tmpX] || space[tmpY][tmpX]) continue;
 
                visited[tmpY][tmpX] = true;
                //한번 눌렀을 때, 주변에 폭탄이 발견될 동안 퍼진다.
                if (notBomb(tmpY, tmpX)) {
                    queue.add(new int[]{tmpY, tmpX});
                }
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int tc = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= tc; t++) {
            n = Integer.parseInt(br.readLine());
            space = new boolean[n][n];
            visited = new boolean[n][n];
            totalCnt = 0;
 
            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                for (int j = 0; j < n; j++) {
                    space[i][j] = input.charAt(j) == '*' ? true : false;
                }
            }
 
            //1차 탐색
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 방문x, 폭탄x, 8방향 폭탄 x
                    if(!visited[i][j] && !space[i][j] && notBomb(i,j)){
                        totalCnt++;
                        bfs(i,j);
                    }
                }
            }
 
            // 2차 탐색
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //폭탄이 아니면서 방문하지 않은 곳 세준다.
                    if(!visited[i][j] && !space[i][j]){
                        totalCnt++;
                    }
                }
            }
 
            System.out.println("#" + t + " " + totalCnt);
        }
    }
}