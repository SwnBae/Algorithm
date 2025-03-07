import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int s;
	public static int[] arr;
	public static int result;
	
	public static void dfs(int cnt, int sum, int visitCount) {
		if(cnt == n) {
			if(sum == s && visitCount > 0) {
				result++;
			}
			return;
		}
		
		dfs(cnt+1, sum, visitCount);
		
		dfs(cnt+1, sum + arr[cnt], visitCount + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] ns = br.readLine().split(" ");
		n = Integer.parseInt(ns[0]);
		s = Integer.parseInt(ns[1]);
		
		arr = Arrays.stream(br.readLine().split(" "))
				.mapToInt((a) -> Integer.parseInt(a))
				.toArray();
		
		// 방문 횟수..!!
		dfs(0, 0, 0);
		System.out.println(result);
		
	}

}
