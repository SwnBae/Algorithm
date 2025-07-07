import java.io.*;

public class Main {
    public static int n;
    public static int m;
    public static long[] tree;
    public static int[] idx;

    public static long init(int node, int start, int end){
        if(start == end) {
            if(start < m){
                return tree[node] = 0;
            } else {
                return tree[node] = 1;
            }
        }

        int mid = (start + end) / 2;

        return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1,mid + 1, end);
    }

    public static long sum(int node, int start, int end, int left, int right){
        if(right < start || left > end) return 0;

        if(left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
    }

    public static void update(int node, int start, int end, int idx, long diff){
        if(idx < start || idx > end) return;

        tree[node] += diff;
        if(start == end) return;
        int mid = (start + end) / 2;

        update(node * 2, start, mid, idx, diff);
        update(node * 2 + 1, mid + 1, end, idx, diff);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= tc; t++){
            String[] nm = br.readLine().split(" ");
            n = Integer.parseInt(nm[0]);
            m = Integer.parseInt(nm[1]);

            tree = new long[4 * (n + m)];
            idx = new int[n + 1];

            for(int i = 1; i <= n; i++){
                idx[i] = m + i - 1;
            }

            init(1, 0, n + m - 1);

            String[] input = br.readLine().split(" ");
            for(int i = m - 1; i >= 0; i--){
                int bn = Integer.parseInt(input[(m - 1) - i]);

                int beforeIdx = idx[bn];

                if(i < m - 1){
                    sb.append(" ");
                }
                sb.append(sum(1, 0, n + m - 1, 0, beforeIdx - 1));


                update(1,0, n + m - 1, beforeIdx, -1);
                update(1, 0, n + m - 1, i, 1);

                idx[bn] = i;
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}