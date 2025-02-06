import java.io.*;

public class Main {

	public static boolean check(int channel, boolean[] breakDown) {
		String temp = Integer.toString(channel);

		for (char c : temp.toCharArray()) {
			if (breakDown[c - '0']) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		int target = Integer.parseInt(br.readLine());
		int bdSize = Integer.parseInt(br.readLine());
		
		boolean[] breakDown = new boolean[10];
		
		// 고장난 체널 입력
		if(bdSize>0) {
			String[] input = br.readLine().split(" ");
			
			for (int i = 0; i < bdSize; i++) {
				breakDown[Integer.parseInt(input[i])] = true; // 고장
			}
		}
		
		int min = Math.abs(target-100);
		
		if(target == 100) {
			System.out.println(0);
			return;
		}
		
		for(int i = 0; i <= 999999; i++) {
			if(check(i,breakDown)) {
				min = Math.min(min, Math.abs(target - i) + Integer.toString(i).length());
			}
			
		}
		
		System.out.println(min);

	}

}
