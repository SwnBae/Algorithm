import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static List<int[]> ladder = new ArrayList<>();
	public static List<int[]> snake = new ArrayList<>();
	public static boolean[] visited = new boolean[101];
	public static int[] dice = {1,2,3,4,5,6};
	
	public static boolean check(int num) {
		if(num>100) return false;
		if(visited[num]) return false;
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] input = sc.nextLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		Queue<int[]> space = new LinkedList<>(); //
		int result = 0;
		
		for(int i=0;i<n;i++) {
			String[] input2 = sc.nextLine().split(" ");
			int start = Integer.parseInt(input2[0]);
			int end = Integer.parseInt(input2[1]);
			ladder.add(new int[] {start,end});
		}
		
		for(int i=0;i<m;i++) {
			String[] input2 = sc.nextLine().split(" ");
			int start = Integer.parseInt(input2[0]);
			int end = Integer.parseInt(input2[1]);
			snake.add(new int[] {start,end});
		}
		
		space.add(new int[] {1,0});
		
		while(!space.isEmpty()) {
			int[] present = space.poll();
			int now = present[0];
			int pcount = present[1];
			
			if(now == 100) {
				result = pcount;
				break;
			}
			
			if(visited[now]) continue;
			visited[now] = true;
			
			for(int i=0;i<6;i++) {
				if(check(now + dice[i])) {
					boolean meet = false;
					
					for(int[] arr: ladder) {
						if(arr[0] == now + dice[i]) {
							meet = true;// 사다리 만났을 때
							space.add(new int[] {arr[1],pcount+1});
							//System.out.println(arr[1]);
							break;
							}
					}
					
					for(int[] arr: snake) {
						if(arr[0] == now + dice[i]) {
							meet = true;// 뱀 만났을 때
							space.add(new int[] {arr[1],pcount+1});
							break;
						}
					}
					if (!meet) space.add(new int[] {now + dice[i],pcount+1});
					//System.out.println(now + dice[i]);
				}
			}
		}

		System.out.println(result);
	}

}
