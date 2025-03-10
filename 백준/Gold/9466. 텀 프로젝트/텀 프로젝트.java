import java.io.*;

public class Main {
    public static int[] arr;
    public static int n;
    public static boolean[] visited;
    public static boolean[] checked;
    public static int sum;

    public static void dfs(int num) {
        if(visited[num]){
            checked[num] = true;
            sum++;
        }else{
            visited[num] = true;
        }

        if(!checked[arr[num]]) {
            dfs(arr[num]);
        }

        visited[num] = false; //원복, 사이클 탐색시 사용해야함
        checked[num] = true; // 사이클 탐지 끝
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for (int t = 1; t <= tc; t++) {
            n = Integer.parseInt(br.readLine());
            sum = 0;
            visited = new boolean[n + 1];
            checked = new boolean[n + 1];
            arr = new int[n + 1];

            String[] input = br.readLine().split(" ");

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(input[i - 1]);

                if (i == arr[i]) {
                    checked[i] = true;
                    sum++;
                }
            }

            for (int i = 1; i <= n; i++) {
                if (checked[i]) continue;

                dfs(i);
            }

            System.out.println(n - sum);
        }
    }
}