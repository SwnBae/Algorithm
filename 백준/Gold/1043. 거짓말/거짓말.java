import java.io.*;
import java.util.*;

/**
 * 1. 파티별로, 거짓말을 하는 경우와 안하는 경우로 DFS
 * 2. 필요한 자료구조 (진실을 아는 사람, 파티 참가 여부)
 * 3. DFS의 분기 -> 1) 거짓말을 할 수 있는 경우, 2) 거짓말을 못하는 경우
 * 4. 사람 -> 0: 평민, 1: 기존에 아는 사람, 2: 진실을 들은 사람 3: 거짓말을 들은 사람
 * 5. 상태 체크: 1,2 / 3이 섞여있으면 진행 불가
 */

public class Main {
    public static int n; // 사람 수
    public static int m; // 파티의 수
    public static boolean[] honest;
    public static List<List<Integer>> player;
    public static int result;

    public static int[] rank;
    public static int[] parent;

    public static int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void union(int x, int y){
        int tmpX = find(x);
        int tmpY = find(y);

        if(tmpX == tmpY) return;

        if(rank[tmpX] > rank[tmpY]){
            parent[tmpY] = tmpX;
            rank[tmpX] += rank[tmpY];
        } else {
            parent[tmpX] = tmpY;
            rank[tmpY] += rank[tmpX];
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        honest = new boolean[51];
        player = new ArrayList<>();
        result = 0;

        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        rank = new int[n + 1];
        parent = new int[n + 1];

        for(int i = 1; i <= n; i++){
            rank[i] = 1;
            parent[i] = i;
        }

        for(int i = 0; i <= m; i++){
            player.add(new ArrayList<>());
        }

        String[] hn = br.readLine().split(" ");
        for(int i = 1; i <= Integer.parseInt(hn[0]); i++){
            honest[Integer.parseInt(hn[i])] = true;
        }

        for(int i = 1; i <= m; i++){
            String[] players = br.readLine().split(" ");
            for(int j = 1; j <= Integer.parseInt(players[0]); j++){
                player.get(i).add(Integer.parseInt(players[j]));
            }
        }

        for(int i = 1; i <= m; i++){
            List<Integer> tmp = player.get(i);
            int init = -1;

            if(!tmp.isEmpty()) init = tmp.get(0);

            for(int j = 1; j < tmp.size(); j++){
                union(init, tmp.get(j));
            }
        }

        for(int i = 1; i <= n; i++){
            if(honest[i]) {
                honest[find(i)] = true;
            }
        }

        for(int i = 1; i <= m; i++){
            List<Integer> tmp = player.get(i);
            boolean canLie = true;

            for(int p : tmp){
                if(honest[find(p)]){
                    canLie = false;
                    break;
                }
            }

            if(canLie) result++;
        }

        System.out.println(result);
    }
}