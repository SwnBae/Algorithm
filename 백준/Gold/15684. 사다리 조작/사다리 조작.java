import java.io.*;

public class Main {
    public static int n;
    public static int h;
    public static boolean[][] ladder;
    public static boolean find;

    public static boolean isValid(int y, int x){
        if(ladder[y][x] || ladder[y][x - 1] || ladder[y][x + 1]){
            return false;
        }

        return true;
    }

    public static void dfs(int cnt, int limit, int startY){
        if(find) return;

        if(cnt == limit){
            if(run()){
                find = true;
            }
            return;
        }

        for(int i = startY; i <= h; i++){
            for(int j = 1; j < n; j++){
                if(isValid(i,j)){
                    ladder[i][j] = true;
                    dfs(cnt + 1, limit, i);
                    ladder[i][j] = false;

                    if(find) return;
                }
            }
        }
    }

    public static boolean run(){
        for(int i = 1; i <= n; i++){
            int x = i;
            for(int y = 1; y <= h; y++){
                if(ladder[y][x]) x++;
                else if(x > 1 && ladder[y][x - 1]) x--;
            }
            if(x != i) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmh = br.readLine().split(" ");
        n = Integer.parseInt(nmh[0]);
        h = Integer.parseInt(nmh[2]);
        int m = Integer.parseInt(nmh[1]);
        ladder = new boolean[h + 2][n + 1];
        find = false;

        for(int i = 0; i < m; i++){
            String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            ladder[a][b] = true;
        }

        if(m == 0 || run()){
            System.out.println(0);
            return;
        }

        for(int i = 1; i <= 3; i++){ // 갯수
            dfs(0,i, 1);

            if(find){
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }
}