import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static List<Integer> stackT = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.nextLine());
		int count = 0;
		
		for(int i=0;i<T;i++) {
			String input = sc.nextLine();
			String[] option = input.split(" ");
			String op = option[0];
			
			switch (op) {
				case "push" :
					int n = Integer.parseInt(option[1]);
					stackT.add(n);
					break;
				case "pop" :
					if(stackT.isEmpty()) {
						System.out.println(-1);
					} else {
						System.out.println(stackT.remove(stackT.size()-1));
					}
					break;
				case "size" :
					System.out.println(stackT.size());
					break;
				case "empty" :
					if(stackT.isEmpty()) {
						System.out.println(1);
					} else {
						System.out.println(0);
					}
					break;
				case "top" :
					if(stackT.isEmpty()) {
						System.out.println(-1);
					} else {
						System.out.println(stackT.get(stackT.size()-1));
					}
					break;
			}
				
		}

	}

}
