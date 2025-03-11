import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

class Number{
	int value;
	String command;
	
	public Number(int value, String command) {
		this.value = value;
		this.command = command;
	}
}

public class Main {
	
	public static String bfs(int st, int end) {
		boolean[] visited = new boolean[10000];
		
		Queue<Number> queue = new ArrayDeque<>();
		
		queue.add(new Number(st, "")); // idx, cnt
		
		visited[st] = true; 
		
		while(!queue.isEmpty()) {
			Number tmp = queue.poll();
			
			//System.out.println(tmp.command);
			
			if(tmp.value == end) {
				return tmp.command;
			}
			
			
			if(tmp.value * 2 > 9999) { // D
				int d = (tmp.value * 2) % 10000;
				
				if(!visited[d]) {
					visited[d]= true; 
					queue.add(new Number(d, tmp.command + "D"));
				}
			} else {
				if(!visited[tmp.value * 2]) {
					visited[tmp.value * 2] = true;
					queue.add(new Number(tmp.value * 2, tmp.command + "D"));
				}
			}
			
			if(tmp.value == 0) { // S
				if(!visited[9999]) {
					visited[9999] = true;
					queue.add(new Number(9999, tmp.command + "S"));
				}
			} else {
				if(!visited[tmp.value - 1]) {
					visited[tmp.value - 1] = true;
					queue.add(new Number(tmp.value - 1, tmp.command + "S"));
				}
			}
			
			int l = (tmp.value % 1000) * 10 + (tmp.value / 1000);
			int r = (tmp.value / 10) + ((tmp.value % 10) * 1000);
			
			if(!visited[l]) {
				visited[l] = true;
				queue.add(new Number(l, tmp.command + "L"));
			}
			
			if(!visited[r]) {
				visited[r]= true; 
				queue.add(new Number(r, tmp.command + "R"));
			}
			
		}
		
		return null;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			System.out.println(bfs(a,b));
		}

	}

}
