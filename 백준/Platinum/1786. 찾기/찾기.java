import java.io.*;

public class Main {
	public static StringBuilder sb;
	public static int[] table;
	public static int count;
	public static String parent;
	public static String pattern;
	
	public static void makeTable() {
		table = new int[pattern.length()];
		int j = 0;
		
		for(int i = 1; i < pattern.length(); i++) {
			while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = table[j - 1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(j)) {
				table[i] = ++j;
			}
		}
	}
	
	public static void kmp() {
		int j = 0;
		
		for(int i = 0; i < parent.length(); i++) {
			while(j > 0 && parent.charAt(i) != pattern.charAt(j)) {
				j = table[j - 1];
			}
			
			if(parent.charAt(i) == pattern.charAt(j)) {
				if(j == pattern.length() - 1) {
					sb.append((i - pattern.length() + 2) + " ");
					count++;
					j = table[j];
				} else {
					j++;
				}
			}
		}
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		count = 0;
		parent = br.readLine();
		pattern = br.readLine();
		
		makeTable();
		kmp();
		
		System.out.println(count);
		System.out.println(sb.toString());
	}

}
