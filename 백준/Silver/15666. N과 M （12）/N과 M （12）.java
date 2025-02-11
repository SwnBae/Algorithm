import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main { // 1. 같은 수 여러번(중복된 선택지 제거), 2. 비내림차순(이전값)
	public static StringBuilder sb = new StringBuilder();
	
	public static int n;
	public static int m;
	public static int[] arr;
	public static int[] output;
	
	public static void dfs(int cnt, int prevNum) {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				if(i>0) sb.append(" ");
				sb.append(output[i]);
			}
			sb.append("\n");
			return;
		}
		
		int currentNum = -1;
		
		for(int i = 0; i < n; i++) {
			if(arr[i] == currentNum || arr[i] < prevNum) continue;
			
			currentNum = arr[i];
			output[cnt] = arr[i];
			dfs(cnt+1,arr[i]);
			
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nm = br.readLine().split(" ");
		
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		
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
