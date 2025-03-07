import java.io.*;
import java.util.*;

public class Main {
    public static List<int[]> list;
    public static int[][] space;
    public static boolean find;

    public static void dfs(int cnt) {
        if (cnt == list.size()) {
            find = true;
            info();
            return;
        }

        int[] tmp = list.get(cnt);

        for(int i = 1; i <=9; i++){
            if(isValid(tmp[0],tmp[1], i)){
                space[tmp[0]][tmp[1]] = i;
                dfs(cnt + 1);
                if (find) return;
                space[tmp[0]][tmp[1]] = 0;
            }
        }
    }

    public static boolean isValid(int y, int x, int num){
        for(int i = 0; i < 9; i++){
            if(space[y][i] == num || space[i][x] == num){
                return false;
            }
        }

        int stY = (y / 3) * 3;
        int stX = (x / 3) * 3;

        for(int i = stY; i <stY + 3; i++) {
            for(int j = stX; j < stX + 3; j++) {
                if(space[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void info() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(j > 0) System.out.print(" ");
                System.out.print(space[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        space = new int[9][9];
        list = new ArrayList<>();
        find = false;

        for (int i = 0; i < 9; i++) {
            String[] input = br.readLine().split(" ");

            for (int j = 0; j < 9; j++) {
                space[i][j] = Integer.parseInt(input[j]);
                if (space[i][j] == 0) {
                    list.add(new int[] { i, j });
                }
            }
        }

        dfs(0);
    }
}
