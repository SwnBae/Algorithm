import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

import javax.management.Query;

public class Main {

	public static Queue<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			String[] input = br.readLine().split(" ");
			String option = input[0];
			
			switch (option) {
				case "push":
					queue.add(Integer.parseInt(input[1]));
					break;
				case "pop" :
					if(queue.isEmpty()) {
						bw.append("-1\n");
						break;
					}
					bw.append(queue.poll() + "\n");
					break;
					
				case "size":
					bw.append(queue.size() + "\n");
					break;
					
				case "empty":
					if(queue.isEmpty()) {
						bw.append("1\n");
						break;
					}
					bw.append("0\n");
					break;
					
				case "front":
					if(queue.isEmpty()) {
						bw.append("-1\n");
						break;
					}
					bw.append(queue.peek() + "\n");
					break;
					
				case "back":
					if(queue.isEmpty()) {
						bw.append("-1\n");
						break;
					}
					bw.append(((LinkedList<Integer>) queue).getLast() + "\n");
					break;
					
			}
				
		}
		bw.flush(); // 남아있는 데이터를 모두 출력시킴
		bw.close(); // 스트림을 닫음
	}

}
