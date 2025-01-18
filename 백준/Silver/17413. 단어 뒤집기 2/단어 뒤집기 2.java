import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
	
	public static LinkedList<String> result = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split("");
		Stack<String> temp = new Stack<>();
		
		int checkTag = 0;
		
		for(int i=0;i<input.length; i++) {
			if(input[i].equals("<")) {
				checkTag = -1;
			} else if(input[i].equals(">")) {
				checkTag = 1;
			}
			
			if(checkTag == -1) {
				while(!temp.isEmpty()) { // 1. '<'만났을 때, 전에 저장해둔 단어 뒤집기
					result.add(temp.pop());
				}
				temp.add(input[i]);
				result.add(temp.pop());
			} else  if(checkTag == 1) { // 1. '<'만났을 때, 전에 저장해둔 단어 뒤집기
				temp.add(input[i]);
				result.add(temp.pop());
				checkTag = 0;
			} else {
				if(input[i].equals(" ")) {
					while(!temp.isEmpty()) { // 전에 저장해둔 단어 뒤집기
						result.add(temp.pop());
					}
					temp.add(input[i]);
					result.add(temp.pop());
				} else  if (i == input.length -1) {
					temp.add(input[i]);
					while(!temp.isEmpty()) { // 전에 저장해둔 단어 뒤집기
						result.add(temp.pop());
					}
				} else {
					temp.add(input[i]);
				}
			}
		}
		
		for(String s : result) {
			bw.write(s);
		}
		
		bw.flush();
		bw.close();
	}

}
