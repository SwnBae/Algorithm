import java.io.*;
import java.util.*;

class Node{
    int y;
    int x;
    int dist;

    Node(int y, int x, int dist){
        this.y = y;
        this.x = x;
        this.dist = dist;
    }
}

public class Main {
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};

    public static int n;
    public static int m;
    public static int playerCount;
    public static int[][] space;

    public static Deque<Node>[] deques;

    public static int[] canMoveCnt;
    public static int[] castleCount;

    public static boolean outBound(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= m;
    }

    public static boolean canMove(int y, int x) {
        if(space[y][x] == 0) return true;

        return false;
    }

    public static void expand(int player) {
        Deque<Node> q = deques[player];
        Deque<Node> next = new ArrayDeque<>();

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.dist == canMoveCnt[player]) {
                next.add(new Node(cur.y, cur.x,0));
                continue;
            }

            for(int d = 0; d < 4; d++){
                int tmpY = cur.y + dy[d];
                int tmpX = cur.x + dx[d];

                if(outBound(tmpY,tmpX) || !canMove(tmpY, tmpX)) continue;

                castleCount[player]++;
                space[tmpY][tmpX] = player;

                q.add(new Node(tmpY,tmpX,cur.dist + 1));
            }
        }

        deques[player] = next;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] init = br.readLine().split(" ");

        n = Integer.parseInt(init[0]);
        m = Integer.parseInt(init[1]);
        playerCount = Integer.parseInt(init[2]);

        space = new int[n][m];
        canMoveCnt = new int[playerCount + 1];
        castleCount = new int[playerCount + 1];

        deques = new ArrayDeque[playerCount + 1];

        for (int i = 1; i <= playerCount; i++) {
            deques[i] = new ArrayDeque<>();
        }

        String[] pmc = br.readLine().split(" ");

        for(int i = 1; i <= playerCount; i++) {
            canMoveCnt[i] = Integer.parseInt(pmc[i - 1]);
        }

        for(int i = 0; i < n; i++) {
            String spaceLine = br.readLine();
            for(int j = 0; j < m; j++) {
                char c = spaceLine.charAt(j);

                if(c == '#') {
                    space[i][j] = -1;
                } else if(Character.isDigit(c)) {
                    space[i][j] = c - '0';
                    deques[space[i][j]].add(new Node(i,j,0));
                    castleCount[space[i][j]]++;
                }
            }
        }

        boolean updated = true;

        while(updated) {
            updated = false;
            for(int i = 1; i <= playerCount; i++) {
                int before = castleCount[i];
                expand(i);

                if(castleCount[i] > before) updated = true;
            }
        }

        for(int i = 1; i <= playerCount; i++) {
            if(i > 1) sb.append(" ");

            sb.append(castleCount[i]);
        }

        System.out.println(sb);
    }
}
