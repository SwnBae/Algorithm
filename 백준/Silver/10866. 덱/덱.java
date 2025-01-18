import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {
	
	public static LinkedList<Integer> deque = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0;i<n;i++) {
			String[] input = br.readLine().split(" ");
			String option = input[0];
			
			switch (option) {
			case "push_front":
				deque.addFirst(Integer.parseInt(input[1]));
				break;
				
			case "push_back":
				deque.add(Integer.parseInt(input[1]));
				break;
				
			case "pop_front":
				if(deque.isEmpty()) {
					bw.append(-1 + "\n");
					break;
				}
				bw.append(deque.pollFirst()+"\n");
				break;
				
			case "pop_back":
				if(deque.isEmpty()) {
					bw.append(-1 + "\n");
					break;
				}
				bw.append(deque.pollLast()+"\n");
				break;
				
			case "size":
				bw.append(deque.size()+ "\n");
				break;
				
			case "empty":
				if(deque.isEmpty()) {
					bw.append(1 + "\n");
					break;
				}
				bw.append(0+ "\n");
				break;

			case "front":
				if(deque.isEmpty()) {
					bw.append(-1 + "\n");
					break;
				}
				bw.append(deque.getFirst()+"\n");
				break;
				
			case "back":
				if(deque.isEmpty()) {
					bw.append(-1 + "\n");
					break;
				}
				bw.append(deque.getLast() +"\n");
				break;
			}
		}
		
		bw.flush();
		bw.close();

	}

}
