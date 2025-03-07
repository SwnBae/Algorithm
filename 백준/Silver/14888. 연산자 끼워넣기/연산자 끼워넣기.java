import java.io.*;
import java.util.Arrays;

// 계산 결과를 들고 가는 경우
public class Main {
	public static int[] op;
	public static int[] arr;
	public static int end;
	public static int max;
	public static int min;
	
	public static void dfs(int cnt, int sum) { // 시작 sum은 첫번째 무조건 포함
		if(cnt == end) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
		}
		
		for(int i = 0; i < 4; i++) {
			if(op[i] == 0) continue;
			
			op[i]--;
			dfs(cnt + 1, calculate(sum, cnt, i));
			op[i]++;
			
		}
	}
	
	public static int calculate(int sum, int idx, int option) {
		switch (option) {
		case 0:
			return sum + arr[idx + 1];
		case 1:
			return sum - arr[idx + 1];
		case 2:
			return sum * arr[idx + 1];
			
		default:
			return sum / arr[idx + 1];

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		end = n - 1;
		op = new int[4];
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		arr = Arrays.stream(br.readLine().split(" "))
				.mapToInt((a) -> Integer.parseInt(a))
				.toArray(); // 처음에 0이 아니여야함
		
		String[] input = br.readLine().split(" ");
		
		for(int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(input[i]);
		}
		
		dfs(0,arr[0]);
		
		System.out.println(max);
		System.out.println(min);
		
	}

}
