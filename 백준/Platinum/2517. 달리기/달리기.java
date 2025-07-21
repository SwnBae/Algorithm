import java.io.*;
import java.util.*;

public class Main {

    public static long sum(int node, int start, int end, int left, int right){
        if(left > end || right < start) return 0;
        if(left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        return sum(node * 2, start, mid, left, right)
                + sum(node * 2 + 1,  mid + 1, end, left, right);
    }

    public static void update(int node, int start, int end, int index){
        if(index < start || index > end) return;

        tree[node]++;
        if(start == end) return;

        int mid = (start + end) / 2;

        update(node * 2, start, mid, index);
        update(node * 2 + 1, mid + 1, end, index);
    }

    public static int n;
    public static long[] tree;
    public static int[] skill;
    public static Set<Integer> set;
    public static int idx;
    public static Map<Integer, Integer> idxMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        tree = new long[500000 * 4 + 1];
        skill = new int[500001];
        set = new TreeSet<>();
        idxMap = new HashMap<>();
        idx = 1;

        for(int i = 1; i <= n; i++){
            skill[i] = Integer.parseInt(br.readLine());
            set.add(skill[i]);
        }

        for(int num : set){
            idxMap.put(num, idx++);
        }

        for(int i = 1; i <= n; i++){
            int comp = idxMap.get(skill[i]);
            update(1, 1, n , comp);

            sb.append(sum(1,1,n,comp,n)).append("\n");
        }

        System.out.println(sb);
    }
}
