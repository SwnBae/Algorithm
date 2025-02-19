import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int n;
	public static int m;
	public static int[][] space;
	public static int[][] temp;
	public static int max;

	// 0은 가로, 1은 세로
	public static int cal() {
		int sum = 0;

		for (int i = 0; i < n; i++) {
			StringBuilder sb = new StringBuilder();

			for (int j = 0; j < m; j++) {
				if (temp[i][j] == 0) {
					sb.append(space[i][j]);
				} else {// 1을 만났는데, 문자가 빈 경우 OR 문자가 있는 경우
					if (sb.length() == 0) {
						continue;
					} else {
						sum += Integer.parseInt(sb.toString());
						sb.setLength(0);
					}
				}
			}
			
			if (sb.length() == 0) {
				continue;
			} else {
				sum += Integer.parseInt(sb.toString());
				sb.setLength(0);
			}
		}

		for (int i = 0; i < m; i++) {
			StringBuilder sb = new StringBuilder();

			for (int j = 0; j < n; j++) {
				if (temp[j][i] == 1) {
					sb.append(space[j][i]);
				} else {// 0을 만났는데, 문자가 빈 경우 OR 문자가 있는 경우
					if (sb.length() == 0) {
						continue;
					} else {
						sum += Integer.parseInt(sb.toString());
						sb.setLength(0);
					}
				}
			}
			
			if (sb.length() == 0) {
				continue;
			} else {
				sum += Integer.parseInt(sb.toString());
				sb.setLength(0);
			}
		}

		return sum;
	}

	public static void dfs(int cntN, int cntM) {
		//System.out.println(cntN + "," + cntM);
		
		if(cntN == n) {
//			for(int i = 0; i < n; i++) {
//				System.out.println(Arrays.toString(temp[i]));
//			}
//			System.out.println();
			max = Math.max(max, cal());
			return;
		}
		
		if (cntM == m && cntN < n) {
			dfs(cntN + 1, 0);
			return;
		}

		for (int i = 0; i <= 1; i++) {
			temp[cntN][cntM] = i;

			dfs(cntN, cntM + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nm = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		space = new int[n][m];
		temp = new int[n][m];
		max = 0;

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				space[i][j] = Integer.parseInt(input[j]);
			}
		}

		dfs(0, 0);

		System.out.println(max);

	}

}
