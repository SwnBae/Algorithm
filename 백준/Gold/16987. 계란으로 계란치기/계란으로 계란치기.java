import java.io.*;

public class Main {
    public static int n;
    public static int[] weight;
    public static int[] durability;

    public static int result;

    public static void dfs(int num, int broken){
        if(num == n){
            result = Math.max(result, broken);
            return;
        }

        if(durability[num] <= 0) {
            dfs(num + 1, broken);
        } else {
            boolean empty = true;
            for(int i = 0; i < n; i++){
                if(i == num || durability[i] <= 0) continue;
                empty = false;
                int bkCnt = 0;

                durability[num] -= weight[i];
                durability[i] -= weight[num];

                if(durability[num] <= 0) bkCnt++;
                if(durability[i] <= 0) bkCnt++;

                dfs(num + 1, broken + bkCnt);

                durability[num] += weight[i];
                durability[i] += weight[num];
            }

            if(empty) dfs(num + 1, broken);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        weight = new int[n];
        durability = new int[n];
        result = 0;

        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split(" ");
            int d = Integer.parseInt(input[0]);
            int w = Integer.parseInt(input[1]);
            durability[i] = d;
            weight[i] = w;
        }

        dfs(0,0);

        System.out.println(result);
    }
}