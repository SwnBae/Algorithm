import java.io.*;

public class Main {
    public static int n;
    public static int result;
    public static int[] queens; // 각 층마다 퀸의 x좌표

    public static void dfs(int y) {
        if (y == n) {
            result++;
            return;
        }

        for (int x = 0; x < n; x++) {
            if (isValid(y, x)) { // 대각선이나, 같은 x면 안간다.
                queens[y] = x;
                dfs(y + 1);
            }
        }
    }

    public static boolean isValid(int y, int x) {
        for (int i = 0; i < y; i++) { // 현재 퀸들 대상으로 검사
            if (queens[i] == x) return false; // 같은 x (세로)
            if (Math.abs(i - y) == Math.abs(queens[i] - x)) return false; // 대각선
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        queens = new int[n]; // 층별로 퀸의 x좌표 저장
        result = 0;

        dfs(0); // 0층부터 검사
        System.out.println(result);
    }
}
