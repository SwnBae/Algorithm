import java.io.*;
import java.util.*;

public class Main {
	public static Set<Integer> set;
	public static int n;
	public static int[] arr;
	
	public static void dfs(int cnt, int sum) {
		if(cnt == n) {
			set.add(sum);
			return;
		}
		
		dfs(cnt + 1, sum);
		dfs(cnt + 1, sum + arr[cnt]);
		
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        set = new TreeSet<>();
        
        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
        		.mapToInt((a) -> Integer.parseInt(a))
        		.toArray();
        
        
        dfs(0,0);
        
        Iterator<Integer> iterator = set.iterator();
        
        int pre = iterator.next();
        
        for(int num : set) {
        	if(num - pre > 1) {
        		System.out.println(pre + 1);
        		return;
        	}
        	
        	pre = num;
        }
        
        System.out.println(pre + 1);
    }
}
