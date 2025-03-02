import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static boolean[][] visited;
    public static int s;

    public static int bfs(){
        Queue<int[]> queue = new ArrayDeque<>();
        int result = 0;

        queue.add(new int[]{1,0,0}); // 현재 개수, cnt, 클립보드

        while (!queue.isEmpty()){
            int[] tmp = queue.poll();
            int num = tmp[0];
            int cnt = tmp[1];
            int clip = tmp[2];

            if(visited[num][clip]){
                continue;
            }

            visited[num][clip] = true;

            if(num == s) {
                result = cnt;
                break;
            }

            if(clip != 0 && num + clip <= 10000){
                queue.add(new int[] {num + clip,cnt + 1, clip}); // 붙이기
            }

            if(num * 2 <= 10000){
                queue.add(new int[] {num,cnt + 1, num}); // 복사하기
            }

            if(num - 1 > 0){
                queue.add(new int[] {num - 1,cnt + 1, clip}); // 삭제하기
            }

        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = Integer.parseInt(br.readLine());

        visited = new boolean[10001][10001];

        System.out.println(bfs());
    }
}