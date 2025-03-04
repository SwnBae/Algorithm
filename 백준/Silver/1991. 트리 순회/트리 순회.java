import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//char 배열 초기화값은 '\u0000'이다.
public class Main {
	public static int n;
	public static char[] tree;
	public static int max;
	public static StringBuilder sb = new StringBuilder();
	
	public static int findIdx(char c) {
		for(int i = 1; i < tree.length; i++) {
			if(tree[i] == c) {
				return i;
			}
		}
		
		return 0;
	}
	
	public static void preOrder(int node) {
		if(node <= max) {
			if(tree[node] != '\u0000') {
				sb.append(tree[node]);
			}
			
			preOrder(2 * node);
			
			preOrder(2 * node + 1);
		}
	}
	
	public static void inOrder(int node) {
		if(node <= max) {
			inOrder(2 * node);
			
			if(tree[node] != '\u0000') {
				sb.append(tree[node]);
			}
			
			inOrder(2 * node + 1);
		}
	}
	
	public static void postOrder(int node) {
		if(node <= max) {
			postOrder(2 * node);
			
			postOrder(2 * node + 1);
			
			if(tree[node] != '\u0000') {
				sb.append(tree[node]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		tree = new char[(int) Math.pow(2, 26) + 4];
		max = 0;
		
		for(int i = 1; i <= n; i++) {
			String[] input = br.readLine().split(" ");
			int idx = findIdx(input[0].charAt(0));
			
			if(idx == 0) {
				idx = input[0].charAt(0) - 64;
			}
			 
			
			tree[idx] = input[0].charAt(0);
			max = Math.max(max, idx);
			
			if(input[1].charAt(0) != '.') {
				tree[idx * 2] = input[1].charAt(0);
				max = Math.max(max, idx * 2);
			}
			
			if(input[2].charAt(0) != '.') {
				tree[idx * 2 + 1] = input[2].charAt(0);
				max = Math.max(max, idx * 2 + 1);
			}
		}
		
		preOrder(1);
		sb.append("\n");
		inOrder(1);
		sb.append("\n");
		postOrder(1);
		
		System.out.println(sb);

	}

}
