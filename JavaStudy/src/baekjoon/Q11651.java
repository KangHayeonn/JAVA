// 좌표 정렬하기2 (백준 11651번)

// 이차원배열, 정렬
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Q11651 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		int arr[][] = new int[count][2];
		
		for(int i=0; i<count; i++) {
			String str = br.readLine();
			String[] parts = str.split(" ");
			int x = Integer.parseInt(parts[0]);
			int y = Integer.parseInt(parts[1]);
			
			arr[i][0] = x;
			arr[i][1] = y;
		}

		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) return o1[0]-o2[0];
				else return o1[1]-o2[1];
			}
			
		});
		
		for(int i=0; i<count; i++) {
			System.out.println(arr[i][0] + " "+ arr[i][1]);
		}
	}
}
