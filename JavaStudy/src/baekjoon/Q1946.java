// ���� ��� (���� 1946��)

// ver1 : ��ü Ž���ϸ鼭 ���������� ���������� ���ϸ� üũ
// �ð��ʰ��� ����
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
			int docMin = arr[i][0];  // �� ���� (�����ɻ�)
			int meetMin = arr[i][1]; // �� ���� (�����ɻ�)
			
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

// ver2 : �˰��� ����
// ���� - �ð��ʰ�
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
			//System.out.println("����: "+Arrays.deepToString(arr));
			Arrays.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0]-o2[0];
				}
				
			});
			//System.out.println("����: "+Arrays.deepToString(arr));
			sb.append(selectionCount(arr)).append('\n');
		}
		System.out.print(sb);
	}
	
	public static int selectionCount(int[][] arr) {
		int select = 0;
		
		for(int i=0; i<arr.length; i++) {
			int check =0;
			int docMin = arr[i][0];  // �� ���� (�����ɻ�)
			int meetMin = arr[i][1]; // �� ���� (�����ɻ�)
			
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
// ���� -> �ٸ� �˰��� 
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
		int select = 1; // 1�� ������ ���Ե�
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

// ver 4 : �޸𸮸� �ٿ����� ���� �������迭 ��� map�� ���� ����
// ���� : �޸𸮴� �״������ �ð��� ������ ����

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
		// ���ذ�.compareTo(�񱳰�) -> ���ذ��� �� ������ ����
		
		int[][] result = new int[count][2];

		for(Map.Entry<Integer, Integer> entry : entries) {
			result[entry.getKey()-1][0] = entry.getKey();
			result[entry.getKey()-1][1] = entry.getValue();
		}
		return result;
	}
	public static int selectionCount(int[][] arr) {
		int select = 1; // 1�� ������ ���Ե�
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


// ver 5 : ������ ���� �����ؼ� Ǯ���
// ���� : �ð��ʰ�
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
		int pivot = arr[i][0]; // ���� �Ǻ�
		int pivot_idx = i;
		int j = i+1;
		while(j <= right) {
			if(pivot > arr[j][0]) {   // �������� ����
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
		int select = 1; // 1�� ������ ���Ե�
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