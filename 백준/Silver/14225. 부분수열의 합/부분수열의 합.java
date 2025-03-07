import java.io.*;
import java.util.*;

public class Main {
	public static boolean[] visited;
	public static int n;
	public static int[] arr;
	
	public static void dfs(int cnt, int sum) {
		if(cnt == n) {
			visited[sum] = true;
			return;
		}
		
		dfs(cnt + 1, sum);
		dfs(cnt + 1, sum + arr[cnt]);
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
       visited = new boolean[100000 * 20 + 1];
        
        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
        		.mapToInt((a) -> Integer.parseInt(a))
        		.toArray();
        
        dfs(0,0);
        
        for(int i = 0; i < visited.length; i++) {
        	if(visited[i]) continue;
        	
        	System.out.println(i);
        	return;
        }
    }
}
