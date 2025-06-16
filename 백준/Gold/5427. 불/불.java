import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};

    public static final int EMPTY = 0;
    public static final int FIRE = 1;
    public static final int WALL = 2;

    public static ArrayDeque<int[]> fire;
    public static int[][] space;
    public static int n;
    public static int m;

    public static int stY;
    public static int stX;

    public static int result;

    public static boolean outBound(int y, int x){
        return y < 0 || y >= n || x < 0 || x >= m;
    }

    public static void bfs(){
        ArrayDeque<int[]> people = new ArrayDeque<>();

        boolean visited[][] = new boolean[n][m];

        visited[stY][stX] = true;
        people.add(new int[]{stY, stX});

        while(!people.isEmpty()){
            int fSize = fire.size();

            for(int f = 0; f < fSize; f++){
                int[] location = fire.pollFirst();
                int y = location[0];
                int x = location[1];

                for(int i = 0; i < 4; i++){
                    int tmpY = y + dy[i];
                    int tmpX = x + dx[i];

                    if(outBound(tmpY,tmpX) || space[tmpY][tmpX] == WALL || space[tmpY][tmpX] == FIRE) continue;

                    space[tmpY][tmpX] = FIRE;
                    fire.add(new int[]{tmpY,tmpX});
                }
            }
            result++;

            int pSize = people.size();

            for(int p = 0; p < pSize; p++){
                int[] location = people.pollFirst();
                int y = location[0];
                int x = location[1];

                for(int i = 0; i < 4; i++){
                    int tmpY = y + dy[i];
                    int tmpX = x + dx[i];

                    if(outBound(tmpY,tmpX)){
                        System.out.println(result);
                        return;
                    }

                    if(visited[tmpY][tmpX] || space[tmpY][tmpX] == WALL || space[tmpY][tmpX] == FIRE) continue;

                    visited[tmpY][tmpX] = true;
                    people.add(new int[] {tmpY,tmpX});
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++){
            String[] wh = br.readLine().split(" ");

            n = Integer.parseInt(wh[1]);
            m = Integer.parseInt(wh[0]);
            space = new int[n][m];
            result = 0;
            fire = new ArrayDeque<>();

            for(int i = 0; i < n; i++){
                String input = br.readLine();
                for(int j = 0; j < m; j++){
                    char chr = input.charAt(j);

                    if(chr == '#') {
                        space[i][j] = WALL;
                    } else if(chr == '*') {
                        space[i][j] = FIRE;
                        fire.add(new int[]{i,j});
                    } else if(chr == '@') {
                        stY = i;
                        stX = j;
                    }
                }
            }

            bfs();
        }
    }
}