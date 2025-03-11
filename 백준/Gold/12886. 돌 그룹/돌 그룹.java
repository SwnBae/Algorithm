import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr;
    public static Set<String> visited;

//    public static boolean outBound(int a, int b, int c){
//        return a < 0 || b < 0 || c < 0 || a > 2000 || b > 2000 || c > 2000;
//    }

    public static boolean bfs() {
        if((arr[0] + arr[1] + arr[2]) % 3 != 0){
            return false;
        }

        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{arr[0], arr[1], arr[2]});
        visited.add(arr[0] + " " + arr[1] + " " + arr[2]);

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

                    StringBuilder sb = new StringBuilder();
                    sb.append(next[0]).append(" ").append(next[1]).append(" ").append(next[2]);
                    // 다시 고려
                    if(visited.contains(sb.toString())) continue;

                    visited.add(sb.toString());
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
        visited = new HashSet<>();
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