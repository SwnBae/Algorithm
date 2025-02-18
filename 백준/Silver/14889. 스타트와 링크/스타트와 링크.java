import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int[][] ability;
    public static int n;
    public static int[] temp;
    public static boolean[] visited;
    public static int min;

    public static void dfs(int cnt, int pre){
        if(cnt == n/2){
            min = Math.min(min,cal(temp));
            return;
        }

        for(int i = 0; i < n; i++){
            if(visited[i] || pre > i) continue;

            visited[i] = true;
            temp[cnt] = i;
            dfs(cnt + 1, i);
            visited[i] = false;
        }
    }

    public static int cal(int[] start){
        int[] link = new int[n/2];
        int cnt = 0;

        for(int j = 0; j < n; j++){
            boolean find = false;

            for(int i = 0; i < start.length; i++){
                if(j == start[i]){
                    find = true;
                    break;
                }
            }

            if(!find){
                link[cnt] = j;
                cnt++;
            }
        }

        int startSum = 0;
        int linkSum = 0;

        for(int i = 0; i < n/2; i++){
            for(int j = i + 1; j < n/2; j++){
                startSum += ability[start[i]][start[j]] + ability[start[j]][start[i]];
            }
        }

        for(int i = 0; i < n/2; i++){
            for(int j = i + 1; j < n/2; j++){
                linkSum += ability[link[i]][link[j]] + ability[link[j]][link[i]];
            }

        }

        return Math.abs(startSum - linkSum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        ability = new int[n][n];
        temp = new int[n / 2];
        visited = new boolean[n];
        min = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                ability[i][j] = Integer.parseInt(input[j]);
            }
        }

        dfs(0,0);

        System.out.println(min);
    }
}