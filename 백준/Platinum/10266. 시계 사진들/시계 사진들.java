import java.io.*;
import java.util.Arrays;

public class Main {
	public static int n;
	
	public static int[] table;
	public static int[] parent;
	public static int[] pattern;
	
	public static void makeTable() {
		int j = 0;
		
		for(int i = 1; i < n; i++) {
			while(j > 0 && pattern[i] != pattern[j]) {
				j = table[j - 1];
			}
			
			if(pattern[i] == pattern[j]) {
				table[i] = ++j;
			}
		}
	}
	
	public static void kmp() {
		int j = 0;
		
		for(int i = 0; i < n * 2; i++) {
			while(j > 0 && parent[i] != pattern[j]) {
				j = table[j - 1];
			}
			
			if(parent[i] == pattern[j]) {
				if(j == n - 1) {
					System.out.println("possible");
					return;
				} else {
					j++;
				}
			}
		}
		
		System.out.println("impossible");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		table = new int[n];
		pattern = new int[n];
		parent = new int[n * 2];
		
		int[] a = Arrays.stream(br.readLine().split(" "))
				.mapToInt((o) -> Integer.parseInt(o))
				.toArray();
		int[] b = Arrays.stream(br.readLine().split(" "))
				.mapToInt((o) -> Integer.parseInt(o))
				.toArray();
		
		Arrays.sort(a);
		Arrays.sort(b);
		
		for(int i = 0; i < n - 1; i++) {
			pattern[i] = a[i + 1] - a[i];
			parent[i] = parent[i + n] = b[i + 1] - b[i];
		}
		
		pattern[n - 1] = a[0] + 360000 - a[n - 1];
		parent[n - 1] = parent[(2 * n)-1] =  b[0] + 360000 - b[n - 1];

		makeTable();
		kmp();
	}

}
