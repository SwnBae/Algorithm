import java.io.*;

public class Main {
    public static final int HASH_SIZE = (int) Math.pow(2, 30) - 1;
    public static int h;
    public static int w;
    public static int n;
    public static int m;

    public static int[][] pattern;
    public static int[][] space;

    public static int getMaxPower(int len, int shift) {
        long result = 1;
        for (int i = 0; i < len - 1; i++) {
            result = (result << shift) + result;
        }
        return (int) (result & HASH_SIZE);
    }

    public static int getPatternHash() {
        int[][] horHashArr = new int[h][1];

        for (int i = 0; i < h; i++) {
            int hash = getHorHash(pattern, w, i, 0);
            horHashArr[i][0] = hash;
        }

        return getVerHash(horHashArr, h, 0, 0);
    }

    public static int[][] getSpaceHashArr() {
        int[][] horHashArr = new int[n][m - w + 1];
        int xMaxP = getMaxPower(w, 4);

        for (int i = 0; i < n; i++) {
            int hash = getHorHash(space, w, i, 0);
            horHashArr[i][0] = hash;
            for (int j = 1; j <= m - w; j++) {
                horHashArr[i][j] = getNext(hash, space[i][j - 1], xMaxP, space[i][j - 1 + w], 4);
                hash = horHashArr[i][j];
            }
        }

        int[][] verHashArr = new int[n - h + 1][m - w + 1];
        int yMaxP = getMaxPower(h, 5);

        for (int j = 0; j <= m - w; j++) {
            int hash = getVerHash(horHashArr, h, 0, j);
            verHashArr[0][j] = hash;
            for (int i = 1; i <= n - h; i++) {
                verHashArr[i][j] = getNext(hash, horHashArr[i - 1][j], yMaxP, horHashArr[i - 1 + h][j], 5);
                hash = verHashArr[i][j];
            }
        }

        return verHashArr;
    }

    public static int getHorHash(int[][] arr, int width, int y, int x) {
        long result = 0;
        for (int i = 0; i < width; i++) {
            result = (result << 4) + result + arr[y][x + i];
        }
        return (int) (result & HASH_SIZE);
    }

    public static int getVerHash(int[][] arr, int height, int y, int x) {
        long result = 0;
        for (int i = 0; i < height; i++) {
            result = (result << 5) + result + arr[y + i][x];
        }
        return (int) (result & HASH_SIZE);
    }

    public static int getNext(int prev, int del, int maxP, int add, int shift) {
        long result = prev - (del * maxP);
        result = (result << shift) + result + add;
        return (int) (result & HASH_SIZE);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        h = Integer.parseInt(input[0]);
        w = Integer.parseInt(input[1]);
        n = Integer.parseInt(input[2]);
        m = Integer.parseInt(input[3]);

        pattern = new int[h][w];
        space = new int[n][m];
        int cnt = 0;

        for (int i = 0; i < h; i++) {
            String line = br.readLine();
            for (int j = 0; j < w; j++) {
                if (line.charAt(j) == 'o') {
                    pattern[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                if (line.charAt(j) == 'o') {
                    space[i][j] = 1;
                }
            }
        }

        int hashVar = getPatternHash();
        int[][] hashArr = getSpaceHashArr();

        for (int i = 0; i <= n - h; i++) {
            for (int j = 0; j <= m - w; j++) {
                if (hashArr[i][j] == hashVar) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}