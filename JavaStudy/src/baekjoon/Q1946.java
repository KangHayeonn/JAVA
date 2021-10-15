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
// ver3 : bufferReader
// 성공 -> 다른 알고리즘 
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q1946 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int caseCount = Integer.parseInt(br.readLine());
		//Scanner sc = new Scanner(System.in);
		//int caseCount = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<caseCount; i++) {
			//int count = sc.nextInt();
			//int arr[][] = new int[count][2];
			int count = Integer.parseInt(br.readLine());
			int arr[][] = new int[count][2];
			
			for(int j=0; j<count; j++) {
				StringTokenizer parts = new StringTokenizer(br.readLine(), " ");
				arr[j][0] = Integer.parseInt(parts.nextToken());
				arr[j][1] = Integer.parseInt(parts.nextToken());
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
	}
	
	public static int selectionCount(int[][] arr) {
		int select = 1; // 1은 무조건 포함됨
		int min = arr[0][1];
		
		for(int i=1; i<arr.length; i++) {
			if(min > arr[i][1]) { 
				min = arr[i][1];
				select++;
			}
		}
		return select;
	}
}*/

// ver 4 : 메모리를 줄여보기 위해 이차원배열 대신 map을 만들 예정
// 성공 : 메모리는 그대로지만 시간은 반으로 감소

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Q1946 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int caseCount = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<caseCount; i++) {
			int count = Integer.parseInt(br.readLine());
			Map<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
			
			for(int j=0; j<count; j++) {
				StringTokenizer parts = new StringTokenizer(br.readLine(), " ");
				int docScore = Integer.parseInt(parts.nextToken());
				int meetScore = Integer.parseInt(parts.nextToken());
				hashmap.put(docScore, meetScore);
			}
			int[][] result = sortMapMyKey(hashmap, count);
			System.out.println(Arrays.deepToString(result));
			sb.append(selectionCount(result)).append('\n');
		}
		System.out.print(sb);
	}
	public static int[][] sortMapMyKey(Map<Integer, Integer> map, int count){
		List<Map.Entry<Integer, Integer>> entries = new LinkedList<>(map.entrySet());
		Collections.sort(entries, (a,b) -> a.getKey().compareTo(b.getKey())); 
		// 기준값.compareTo(비교값) -> 기준값이 더 작으면 음수
		
		int[][] result = new int[count][2];

		for(Map.Entry<Integer, Integer> entry : entries) {
			result[entry.getKey()-1][0] = entry.getKey();
			result[entry.getKey()-1][1] = entry.getValue();
		}
		return result;
	}
	public static int selectionCount(int[][] arr) {
		int select = 1; // 1은 무조건 포함됨
		int min = arr[0][1];
		
		for(int i=1; i<arr.length; i++) {
			if(min > arr[i][1]) { 
				min = arr[i][1];
				select++;
			}
		}
		return select;
	}
}


// ver 5 : 퀵정렬 직접 구현해서 풀어보기
// 실패 : 시간초과
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1946 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int caseCount = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<caseCount; i++) {
			int count = Integer.parseInt(br.readLine());
			int arr[][] = new int[count][2];
			
			for(int j=0; j<count; j++) {
				StringTokenizer parts = new StringTokenizer(br.readLine(), " ");
				arr[j][0] = Integer.parseInt(parts.nextToken());
				arr[j][1] = Integer.parseInt(parts.nextToken());
			}
			quickSort(arr, 0, count-1);
			sb.append(selectionCount(arr)).append('\n');
		}
		System.out.print(sb);
	}
	
	public static void quickSort(int[][] arr, int left, int right){
		if(left <= right) {
			int pivot = partition(arr, left, right);
			quickSort(arr, left, pivot-1);
			quickSort(arr, pivot+1, right);
		}
	}
	
	public static int partition(int[][] arr, int i, int right) {
		int pivot = arr[i][0]; // 왼쪽 피봇
		int pivot_idx = i;
		int j = i+1;
		while(j <= right) {
			if(pivot > arr[j][0]) {   // 오름차순 정렬
				i++;
				if(i != j) swap(arr, i, j);
			}
			j++;
		}
		swap(arr, pivot_idx, i);
		return i;
	}
	
	public static void swap(int[][] arr, int left, int right) {
		int temp_x, temp_y;
		temp_x = arr[left][0];
		temp_y = arr[left][1];
		arr[left][0] = arr[right][0];
		arr[left][1] = arr[right][1];
		arr[right][0] = temp_x;
		arr[right][1] = temp_y;
	}
	
	public static int selectionCount(int[][] arr) {
		int select = 1; // 1은 무조건 포함됨
		int min = arr[0][1];
		
		for(int i=1; i<arr.length; i++) {
			if(min > arr[i][1]) { 
				min = arr[i][1];
				select++;
			}
		}
		return select;
	}
}*/