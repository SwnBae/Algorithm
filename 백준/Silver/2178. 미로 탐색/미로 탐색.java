import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int[][] space = new int[101][101];
	public static boolean[][] visited = new boolean[101][101];

	
	public static boolean check(int y, int x ,int n, int m) {
		if(y<1 || y>n || x<1 || x>m) {
			return false;
		}
		if(space[y][x] == 0) {
			return false;
		}
		if(visited[y][x]) return false;
		
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] s = sc.nextLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		int result = 0;
		Queue<int[]> sp = new LinkedList<>();
//		Queue<Integer> y = new LinkedList<>();
//		Queue<Integer> x = new LinkedList<>();
//		Queue<Integer> count = new LinkedList<>();
		
		for(int i=1; i<=n;i++) {
			String input = sc.nextLine();
			for(int j=1;j<=m;j++) {
				space[i][j] = input.charAt(j-1) - '0';
			}
		}
		
		sp.add(new int[] {1,1,1});
		
//		y.add(1);
//		x.add(1);
//		count.add(1);
		
		while(sp.isEmpty() == false) {
			int[] qsp = sp.poll();
			int qy = qsp[0];
			int qx = qsp[1];
			int qc = qsp[2];
			
			if(qy == n && qx == m) {
				result = qc;
				break;
			}
			
			if(visited[qy][qx]) continue;
			visited[qy][qx] = true;
			
			if(check(qy-1,qx,n,m)) {
//				y.add(qy-1); x.add(qx); count.add(qc+1);
				sp.add(new int[] {qy-1,qx,qc+1});
			}
			
			if(check(qy+1,qx,n,m)) {
				//y.add(qy+1); x.add(qx); count.add(qc+1);
				sp.add(new int[] {qy+1,qx,qc+1});
			}
			
			if(check(qy,qx-1,n,m)) {
				//y.add(qy); x.add(qx-1); count.add(qc+1);
				sp.add(new int[] {qy,qx-1,qc+1});
			}
			
			if(check(qy,qx+1,n,m)) {
				//y.add(qy); x.add(qx+1); count.add(qc+1);
				sp.add(new int[] {qy,qx+1,qc+1});
			}
			
		}
		
		System.out.println(result);

	}

}
