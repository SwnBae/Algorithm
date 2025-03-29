import java.io.*;
import java.util.Arrays;

public class Main {
    public static int[] arr;
    public static int[] tree;
    public static int[] lazy;

    public static int init(int node, int start, int end) {
        if (start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;
        return tree[node] = init(node * 2, start, mid) ^ init(node * 2 + 1, mid + 1, end);
    }

    public static void xor(int node, int start, int end, int left, int right, int value) {
        lazy_update(node, start, end);

        if (end < left || start > right) return;

        if (left <= start && end <= right) {
            if ((end - start + 1) % 2 != 0) {
                tree[node] ^= value;
            }

            if (start != end) {
                lazy[node * 2] ^= value;
                lazy[node * 2 + 1] ^= value;
            }
            return;
        }

        int mid = (start + end) / 2;

        xor(node * 2, start, mid, left, right, value);
        xor(node * 2 + 1, mid + 1, end, left, right, value);

        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    public static int sumXor(int node, int start, int end, int left, int right) {
        lazy_update(node, start, end);

        if (end < left || start > right) return 0;

        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;

        return sumXor(node * 2, start, mid, left, right) ^ sumXor(node * 2 + 1, mid + 1, end, left, right);
    }

    public static void lazy_update(int node, int start, int end) {
        if (lazy[node] != 0) {
            if ((end - start + 1) % 2 != 0) {
                tree[node] ^= lazy[node];
            }

            if (start != end) {
                lazy[node * 2] ^= lazy[node];
                lazy[node * 2 + 1] ^= lazy[node];
            }

            lazy[node] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        tree = new int[4 * n];
        lazy = new int[4 * n];

        String[] inp = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(inp[i]);
        }

        init(1, 0, n - 1);

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            int op = Integer.parseInt(input[0]);
            int a = Integer.parseInt(input[1]);
            int b = Integer.parseInt(input[2]);

            if (op == 1) {
                int k = Integer.parseInt(input[3]);

                xor(1, 0, n - 1, a, b, k);
            } else {
                sb.append(sumXor(1, 0, n - 1, a, b) + "\n");
            }
        }

        System.out.println(sb.toString());
    }
}