import java.io.*;
import java.util.*;

public class Main {
	public static List<int[]> list;
	public static int[][] space;
	public static boolean find;

	public static void dfs(int cnt) {
		//info();
		if (find) return;
		
		if (cnt == list.size()) {
			find = true;
			info();
			return;
		}
		
		int[] tmp = list.get(cnt);
		
		for(int num : getValidNums(tmp)) {
			space[tmp[0]][tmp[1]] = num;
			dfs(cnt + 1);
			space[tmp[0]][tmp[1]] = 0;
		}
	}
	
	public static void info() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(j > 0) System.out.print(" ");
				System.out.print(space[i][j]);
			}
			System.out.println();
		}
	}

	// 가로, 세로, 박스 검사해서 가능한 숫자 set으로 반환하기
	public static List<Integer> getValidNums(int[] yx) {
		Set<Integer> tmp = new HashSet<>();
		List<Integer> list = new ArrayList<>();

		int y = yx[0];
		int x = yx[1];

		for (int i = 0; i < 9; i++) {
			tmp.add(space[i][x]); // y
			tmp.add(space[y][i]); // x
		}

		int stY = (y / 3) * 3;
        int stX = (x / 3) * 3;
		
		for(int i = stY; i <stY + 3; i++) {
			for(int j = stX; j < stX + 3; j++) {
				tmp.add(space[i][j]);
			}
		}
		
		for(int i = 1; i <= 9; i++) {
			if(tmp.contains(i)) continue;
			
			list.add(i);
		}

		return list;
	}

	// 2,5,8 기준
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		space = new int[9][9];
		list = new ArrayList<>();
		find = false;

		for (int i = 0; i < 9; i++) {
			String[] input = br.readLine().split(" ");

			for (int j = 0; j < 9; j++) {
				space[i][j] = Integer.parseInt(input[j]);
				if (space[i][j] == 0) {
					list.add(new int[] { i, j });
				}
			}
		}
		
		dfs(0);

	}

}
