import java.util.Scanner;

public class Main {

	public static void main(String args[]) throws Exception
	{
		 Scanner sc = new Scanner(System.in);
		 
		 int n = Integer.parseInt(sc.nextLine());
		 
		 int[] arr= new int[1001]; // X, ex) n = 1,d[2]는 존재하지 않는데 d2를 접근하게 된다.
		 
		 arr[0] = arr[1] = 1;
		 arr[2] = 2;
		 
		 for(int i = 3; i <= n; i++) {
			 arr[i] = arr[i-1] + arr[i-2];
			 arr[i] %= 10007; // 입력값이 커지면 int형으로 결과값 담을 수 없음. 미리 나눠줘야함
		 }
		 
		 System.out.println(arr[n] % 10007);
	}

}
