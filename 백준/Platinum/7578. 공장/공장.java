import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static long[] tree;
    public static Map<Integer,Integer> map;

    public static long sum(int node, int start, int end, int left, int right){
        if(right < start || left > end) return 0;

        if(left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        return sum(node * 2, start, mid,left,right) + sum(node * 2 + 1, mid + 1, end, left, right);
    }

    public static void update(int node, int start, int end, int idx){
        if(idx > end || idx < start) return;

        tree[node]++;
        if(start == end) return;

        int mid = (start + end) / 2;

        update(node * 2, start, mid, idx);
        update(node * 2 + 1, mid + 1, end, idx);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new long[4 * n];
        map = new HashMap<>();
        long result = 0;

        String[] front = br.readLine().split(" ");
        for(int i = 0; i < n; i++){
            map.put(Integer.parseInt(front[i]), i);
        }

        String[] back = br.readLine().split(" ");

        for(int i = 0; i < n; i++){
            int a = map.get(Integer.parseInt(back[i]));

            long val = sum(1,0,n-1,a,n - 1);
            result += val;

            update(1, 0, n - 1, a);
        }

        System.out.println(result);
    }
}