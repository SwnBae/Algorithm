import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int t = Integer.parseInt(sc.nextLine());
		
		for(int i=0;i<t;i++) {
			String[] input = sc.nextLine().split(" ");
			String temp = "";
			
			for(int p=0; p<input.length; p++) { //문장
				String str = input[p];
				
                 for (int q = str.length() - 1; q >= 0; q--) {
                     temp += str.charAt(q);
                }
				
				temp += " ";
			}
			System.out.println(temp);
		}
	}
}
