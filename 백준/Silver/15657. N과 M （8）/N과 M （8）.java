import java.io.*;
import java.util.*;

public class Main {
	public static StringBuilder sb = new StringBuilder();
	
	public static int[] output;
	public static int n;
	public static int m;
	public static int[] arr;
	
	public static void dfs(int cnt, int preValue) {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				if(i > 0) sb.append(" ");
				sb.append(output[i]);
			}
			sb.append("\n");
			return;
		}
		
		for(int num : arr) {
			if(num < preValue) continue;
			output[cnt] = num;
			dfs(cnt + 1,num);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputNM = br.readLine().split(" "); 
		n = Integer.parseInt(inputNM[0]);
		m = Integer.parseInt(inputNM[1]);
		
		arr = new int[n];
		output = new int[m];
		
		String[] input = br.readLine().split(" "); 
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		
		Arrays.sort(arr);
		
		dfs(0,0);
		
		System.out.println(sb.toString());
	}
}
