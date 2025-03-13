import java.io.*;
import java.util.*;

public class Main {
	public static ArrayDeque<Character> left;
	public static ArrayDeque<Character> right;
	
	public static String input;
	public static String bomb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		left = new ArrayDeque<>();
		right = new ArrayDeque<>();
		
		input = br.readLine();
		bomb = br.readLine();
		char bL = bomb.charAt(bomb.length() - 1);
		
		for(int i = 0; i < input.length(); i++) {
			left.add(input.charAt(i));
			
			if(!left.isEmpty() && left.peekLast() == bL) {
				for(int j = bomb.length() - 1; j >= 0; j--) {
					// 폭발 문자열에 대한 모든 길이를 비교해야한다.
					if(!left.isEmpty()) {
						right.add(left.pollLast());
					} else {
						right.add(' ');
					}
						
					// right와 비교하면 안될듯..? -> peek해야함
					if(!right.isEmpty() && (right.peekLast() != bomb.charAt(j))) {
						if(right.peekLast() == ' ') right.pollLast();
						
						while(!right.isEmpty()) {
							left.add(right.pollLast());
						}
						
						break;
					}
				}
				
				//비워주기
				while(!right.isEmpty()) {
					right.pollLast();
				}
			}
		}
		
		if(left.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			
			while(!left.isEmpty()) {
				sb.append(left.poll());
			}
			
			System.out.println(sb);
		}
	}

}
