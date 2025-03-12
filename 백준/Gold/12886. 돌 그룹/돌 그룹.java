import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr;
    public static boolean[][] visited;
    // A + B + C는 항상 동일하다.
    // 따라서, A,B만 체크하면 C는 항상 확정 (오름차순 기준)
    // 2차원 방문 체크만 해주면 된다.
    // 크기는 각 1500, 최대 합은 500*3이다.

    public static boolean bfs() {
        if((arr[0] + arr[1] + arr[2]) % 3 != 0){
            return false;
        }

        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{arr[0], arr[1], arr[2]});
        visited[arr[0]][arr[1]] = true;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            // 항상 정렬된 상태

            if (threeEq(tmp[0],tmp[1],tmp[2])){
                System.out.println(1);
                return true;
            }

            //0,1,2 -> 합이 3
            for(int i = 0; i < 3; i++){
                // 항상 tmp[j]가 큰 상태
                for(int j = i + 1; j < 3; j++){ // 두개 고르기
                    if(tmp[i] == tmp[j]) continue;

                    int a = tmp[i] * 2;
                    int b = tmp[3 - i - j];
                    int c = tmp[j] - tmp[i];

                    int[] next = new int[]{a, b, c};
                    Arrays.sort(next);

                    // 다시 고려
                    if(visited[next[0]][next[1]]) continue;

                    visited[next[0]][next[1]] = true;
                    queue.add(next);
                }
            }
        }

        return false;
    }

    public static boolean threeEq(int a, int b, int c) {
        return a == b && b == c;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        visited = new boolean[1501][1501];
        arr = new int[3];

        for (int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(arr);

        if(!bfs()){
            System.out.println(0);
        }
    }
}