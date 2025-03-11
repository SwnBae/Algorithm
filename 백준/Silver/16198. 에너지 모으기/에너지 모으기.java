import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static List<Integer> list;
	public static int max;
	
	public static void dfs(int len, int sum) {
		int size = list.size();
		
		if(len == 2) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int i = 1; i < size - 1; i++) {
			int tmp = list.get(i - 1) * list.get(i + 1);
			int num = list.remove(i);
			dfs(len - 1, sum + tmp);
			list.add(i, num);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		max = Integer.MIN_VALUE;
		
		int[] arr = Arrays.stream(br.readLine().split(" "))
				.mapToInt((a) -> Integer.parseInt(a))
				.toArray();
		
		for(int i = 0; i < n; i++) {
			list.add(arr[i]);
		}
		
		dfs(n, 0);
		
		System.out.println(max);

	}

}
