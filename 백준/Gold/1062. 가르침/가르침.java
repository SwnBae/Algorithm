import java.io.*;

public class Main {
	public static boolean[] numSet; // 후보군
	public static boolean[] visited;
	public static String[] word;
	
	public static int n;
	public static int k;
	public static int max;
	public static int maxLength;
	
	public static void dfs(int cnt, int pre) { // 중복 없이, 순서 상관 없음 => 오름차순
		if(cnt == k - 5) {
			max = Math.max(max, check());
			return;
		}
		
		boolean end = true;
		
		for(int i = 0; i < 26; i++) { // numSet안에 있는 것들 + 방문 안한 것만 검사, 
			if(!numSet[i] || visited[i] || i <= pre) continue;
			
			end = false;
			visited[i] = true;
			dfs(cnt + 1, i);
			visited[i] = false; 
		}
		
		if(end) {
			max = Math.max(max, check());
			return;
		}
	}
	
	public static int check() {
		int result = 0;
		
		for(String s : word) {
			boolean can = true;
			
			for(int i = 4; i < s.length() - 4; i++) {
				if(!visited[s.charAt(i) - 'a']) { // 현재 배운 단어로는 읽지 못한다.
					can = false;
					break;
				}
			}
			
			if(can) {
				result++;
			}
		}
		
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nk =  br.readLine().split(" ");
		n = Integer.parseInt(nk[0]);
		k = Integer.parseInt(nk[1]);
		max = 0;
		
		numSet = new boolean[26];
		visited = new boolean[26];
		word = new String[n];
		
		numSet['a' - 'a'] = true;
		numSet['n' - 'a'] = true;
		numSet['t' - 'a'] = true;
		numSet['i' - 'a'] = true;
		numSet['c' - 'a'] = true;
		
		visited['a' - 'a'] = true;
		visited['n' - 'a'] = true;
		visited['t' - 'a'] = true;
		visited['i' - 'a'] = true;
		visited['c' - 'a'] = true;
		
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			word[i] = input;
			maxLength = Math.max(maxLength, input.length());
			
			for(int j = 4; j < input.length() - 4; j++) {
				numSet[input.charAt(j) - 'a'] = true;
			}
		}
		
		//System.out.println(Arrays.toString(numSet));
		if(k >=5) {
			dfs(0, -1);
		}
		
		System.out.println(max);
	}
}
