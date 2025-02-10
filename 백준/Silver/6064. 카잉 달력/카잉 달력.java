import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1. count의 최댓값은 최소공배수
//2. year % m, year % n

public class Main {

	public static int getLCM(int a, int b) {
		int lcm = a * b;

		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}

		return lcm / a;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int i = 0; i < tc; i++) {
			String[] input = br.readLine().split(" ");

			int m = Integer.parseInt(input[0]);
			int n = Integer.parseInt(input[1]);

			int x = Integer.parseInt(input[2]) - 1;
			int y = Integer.parseInt(input[3]) - 1;

			int count = x;
			int lcm = getLCM(m, n);

			while (count <= lcm) {

				if (count % n == y) {
					count++;
					break;
				}

				count += m;
			}
			
			if (count > lcm) {
				count = -1;
			}

			System.out.println(count);
		}
	}
}
