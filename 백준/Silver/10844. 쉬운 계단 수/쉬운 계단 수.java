import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long[][] arr = new long[101][10];

		arr[1][0] = 0;

		for (int i = 1; i <= 9; i++) {
			arr[1][i] = 1;
		}

		int n = Integer.parseInt(br.readLine());

		for (int i = 2; i <= n; i++) {
			arr[i][0] = arr[i - 1][1] % 1000000000;
			arr[i][9] = arr[i - 1][8] % 1000000000;

			for (int j = 1; j <= 8; j++) {
				arr[i][j] = (arr[i - 1][j - 1] % 1000000000) + (arr[i - 1][j + 1] % 1000000000);
			}
		}

		int result = 0;

		for (int i = 0; i <= 9; i++) {
			result += (arr[n][i] % 1000000000);
			result %= 1000000000;
		}

		System.out.println(result);

	}

}
