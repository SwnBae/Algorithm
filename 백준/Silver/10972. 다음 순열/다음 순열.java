import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//1. 중복된 숫자 사용x
//2. 사전 방식으로 ( 미리 정렬하고 사전 수대로 하면)
// next_permutation 알고리즘

public class Main {

	public static int[] arr;
	public static int size;
	
	public static String info(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(arr[i]);
        }
        return sb.toString();
    }

	public static void swap(int idx1, int idx2) {
		int temp = arr[idx2];
		arr[idx2] = arr[idx1];
		arr[idx1] = temp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		size = Integer.parseInt(br.readLine());
		arr = new int[size];

		String[] input = br.readLine().split(" ");

		for (int i = 0; i < size; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}

		int idxI = -1;
		int idxJ = -1;
		boolean notFound = true;

		for (int i = arr.length - 1; i > 0; i--) {
			if (arr[i - 1] < arr[i]) {
				idxI = i - 1;
				notFound = false;
				break;
			}
		}

		if(notFound) {
			System.out.println(-1);
			return;
		}
		
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[idxI] < arr[i]) {
				idxJ = i;
				break;
			}
		}

		swap(idxI, idxJ);
		
		Arrays.sort(arr, idxI + 1, arr.length);

//		for (int i = idxI + 1; i < arr.length - 1; i++) {
//			for (int j = i + 1; i < arr.length - 1 - (i - (idxI + 1)); j++) {
//				if(arr[i] > arr[j]) {
//					swap(i,j);
//				}
//			}
//		}
//		
//		for(int i = arr.length - 1; i > idxI; i--) {
//			for(int j = idxI + 1; j < i; j++) {
//				if(arr[j] > arr[j + 1]) {
//					swap(j,j+1);
//				}
//			}
//		}
		
		System.out.println(info(arr));

	}

}
