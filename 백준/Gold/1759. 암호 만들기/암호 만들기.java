import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int l;
	public static int c;
	public static char[] arr;
	public static char[] temp;
	public static boolean[] visited;
	
	public static StringBuilder sb = new StringBuilder();
	
	public static void dfs(int cnt, char preValue) {
		if(cnt == l) {
			StringBuilder aeiou = new StringBuilder();
			int a = 0;
			int b = 0;
			
			for(int i = 0; i < l; i++) {
				aeiou.append(temp[i]);
				
				if(temp[i] == 'a' || temp[i] == 'e' || temp[i] == 'i' || 
						temp[i] == 'o' || temp[i] == 'u') {
					a++;
				} else {
					b++;
				}
			}
			
			if(a >= 1 && b >= 2) {
				sb.append(aeiou.toString());
				sb.append("\n");
			}
			
			return;
		}
		
		for(int i = 0; i < c; i++) {
			if(preValue > arr[i] || visited[i]) continue;
			
			visited[i] = true;
			temp[cnt] = arr[i];
			dfs(cnt + 1, arr[i]);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] lc = br.readLine().split(" ");
		l = Integer.parseInt(lc[0]);
		c = Integer.parseInt(lc[1]);
		visited = new boolean[c];
		arr = new char[c];
		temp = new char[l];
		
		String[] input = br.readLine().split(" ");
		
		for(int i = 0; i < c; i++) {
			arr[i] = input[i].charAt(0);
		}
		
		Arrays.sort(arr);
		
		dfs(0,'0');
		
		System.out.println(sb.toString());

	}

}
