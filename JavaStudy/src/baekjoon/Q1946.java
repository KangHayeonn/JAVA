// 신입 사원 (백준 1946번)

// ver1 : 전체 탐색하면서 서류순위와 면접순위를 비교하며 체크
// 시간초과로 실패
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1946 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int caseCount = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<caseCount; i++) {
			int count = Integer.parseInt(br.readLine());
			int arr[][] = new int[count][2];
			
			for(int j=0; j<count; j++) {
				String str = br.readLine();
				String parts[] = str.split(" ");
				int docScore = Integer.parseInt(parts[0]);
				int meetScore = Integer.parseInt(parts[1]);
				arr[j][0] = docScore;
				arr[j][1] = meetScore;
			}
			sb.append(selectionCount(arr)).append('\n');
		}
		System.out.print(sb);
	}
	
	public static int selectionCount(int[][] arr) {
		int select = 0;
		
		for(int i=0; i<arr.length; i++) {
			int check =0;
			int docMin = arr[i][0];  // 비교 기준 (서류심사)
			int meetMin = arr[i][1]; // 비교 기준 (면접심사)
			
			for(int j=0; j<arr.length; j++) {
				if(docMin > arr[j][0]) {
					if(meetMin < arr[j][1]) check++;
					else continue;
				}
				else if(docMin < arr[j][0]) check++;
			}
			if(check==arr.length-1) select++;
		}
		return select;
	}
}*/

// ver2 : 알골리즘 수정
// 실패 - 시간초과
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Q1946 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int caseCount = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<caseCount; i++) {
			int count = Integer.parseInt(br.readLine());
			int arr[][] = new int[count][2];
			
			for(int j=0; j<count; j++) {
				String str = br.readLine();
				String parts[] = str.split(" ");
				int docScore = Integer.parseInt(parts[0]);
				int meetScore = Integer.parseInt(parts[1]);
				arr[j][0] = docScore;
				arr[j][1] = meetScore;
			}
			//System.out.println("이전: "+Arrays.deepToString(arr));
			Arrays.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0]-o2[0];
				}
				
			});
			//System.out.println("이후: "+Arrays.deepToString(arr));
			sb.append(selectionCount(arr)).append('\n');
		}
		System.out.print(sb);
	}
	
	public static int selectionCount(int[][] arr) {
		int select = 0;
		
		for(int i=0; i<arr.length; i++) {
			int check =0;
			int docMin = arr[i][0];  // 비교 기준 (서류심사)
			int meetMin = arr[i][1]; // 비교 기준 (면접심사)
			
			if(docMin==1 || meetMin==1) {
				select++;
				continue;
			}
			
			for(int j=0; j<i; j++) {
				if(meetMin < arr[j][1]) check++;
				else continue;
			}
			
			if(check==i) select++;
		}
		return select;
	}
}
*/
// ver3 : bufferReader -> Scanner
// 실패 -> 다른 알고리즘 고민해봐야할듯

package baekjoon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Q1946 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int caseCount = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<caseCount; i++) {
			int count = sc.nextInt();
			int arr[][] = new int[count][2];
			
			for(int j=0; j<count; j++) {
				arr[j][0] = sc.nextInt();
				arr[j][1] = sc.nextInt();
			}
			Arrays.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0]-o2[0];
				}
				
			});
			sb.append(selectionCount(arr)).append('\n');
		}
		System.out.print(sb);
		sc.close();
	}
	
	public static int selectionCount(int[][] arr) {
		int select = 0;
		
		for(int i=0; i<arr.length; i++) {
			int check =0;
			int docMin = arr[i][0];  // 비교 기준 (서류심사)
			int meetMin = arr[i][1]; // 비교 기준 (면접심사)
			
			if(docMin==1 || meetMin==1) {
				select++;
				continue;
			}
			
			for(int j=0; j<i; j++) {
				if(meetMin < arr[j][1]) check++;
				else continue;
			}
			
			if(check==i) select++;
		}
		return select;
	}
}