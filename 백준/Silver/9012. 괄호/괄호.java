import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int t = Integer.parseInt(sc.nextLine());
		
		for(int i=0;i<t;i++) {
			String input = sc.nextLine();
			int count = 0;
			
			for(int j=0;j<input.length();j++) {
				if(input.charAt(j) == '(') {
					count++;
				} else if(input.charAt(j) == ')') {
					count--;
				}
				
				if(count < 0) {
					System.out.println("NO");
					break;
				}
			}
			
			if(count < 0) {
				continue;
			}
			
			if(count > 0) {
				System.out.println("NO");
			} else {
				System.out.println("YES");
			}
		}
	}
}
