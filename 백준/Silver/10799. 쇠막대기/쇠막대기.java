import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {
	public static List<Integer> lsEnd = new ArrayList<>();
	public static List<int[]> pipe = new ArrayList<>();
	
	public static int lowerBound(List<Integer> arr, int target) {
        int start = 0;
        int end = arr.size();

    // 이진 탐색
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr.get(mid) >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split("");
		boolean ls = false;
		int count = 0;
		Stack<Integer> temp = new Stack<>();
		
		int num = 0;
		int lnum = 0;
		
		for(int i = 0; i<input.length; i++) {
			if(input[i].equals("(") && !ls ) { // 파이프 + 레이저 시작
				ls = true;
				temp.add(i);
				//레이저 + 파이프
			} else if(input[i].equals(")") && ls) { // 레이저 끝
				ls = false;
				temp.pop();
				lsEnd.add(i);
				//레이저 끝
			} else if(input[i].equals(")") && !ls){ // 파이프 끝
				int[] p = {temp.pop(),i};
				pipe.add(p);
			} else if(input[i].equals("(") && ls) { // 파이프 시작
				temp.add(i);
			}
		}
		
		// 파이프 시작 + 레이저 끝 / 레이저+ 파이프 - 레이저 끝
		//레이저 끝? -> 앞전과 레이저는 확정
		
		for(int[] i : pipe) {
			count++;
			int st = i[0];
			int end = i[1];
//			for(int lst : lsEnd) {
//				if(lst > st && lst < end) {
//					count++;
//				}
//			}
			int start = lowerBound(lsEnd, st);
	        int enend = lowerBound(lsEnd, end);
	        count += enend - start;
		}
		
		
		
		bw.write(count + "\n");
		bw.flush();
		bw.close();

	}

}
