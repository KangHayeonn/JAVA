// 암기왕 (백준 2776번)

// ver1 : 시간초과
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2776 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		
		for(int i=0; i<count; i++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] arr1 = new int[N];
			for(int j=0; j<N; j++) {
				arr1[j] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int[] arr2 = new int[M];
			for(int k=0; k<M; k++) {
				arr2[k] = Integer.parseInt(st2.nextToken());
			}
			
			int[] answer = matching(arr1, arr2);
			
			for(int m=0; m < answer.length; m++) {
				System.out.println(answer[m]);
			}
		}
	}
	
	public static int[] matching(int[] arr1, int[] arr2) {
		int[] answer = new int[arr2.length];
	
		for(int i=0; i<arr2.length; i++) {
			boolean check = false;
			
			for(int j=0; j<arr1.length; j++) {
				if(arr2[i] == arr1[j]) check = true;
			}
			
			if(check==true) answer[i] =1;
			else answer[i] = 0;
		}
		
		return answer;
	}
}
*/

//ver2 : 시간초과
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2776 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		
		for(int i=0; i<count; i++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] arr1 = new int[N];
			for(int j=0; j<N; j++) {
				arr1[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr1);
			
			int M = Integer.parseInt(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int[] arr2 = new int[M];
			for(int k=0; k<M; k++) {
				int target = Integer.parseInt(st2.nextToken());
				if(binarySearch(arr1, target) < 0) arr2[k] = 0;
				else arr2[k] = 1;
			}
			for(int m=0; m < arr2.length; m++) {
				System.out.println(arr2[m]);
			}
		}
	}
	
	// ver1: for문으로 구현
	public static int binarySearch(int arr[], int target) {
		 int left = 0;
		 int right = arr.length-1;
	
		 while(left<=right) {
			 int mid = (left+right) /2;
			 
			 if(arr[mid]==target) return mid;
			 if(target > arr[mid]) left = mid+1;
			 else right = mid-1;
		 }
		 
		 return -1;
	}
}
*/

// ver3 : 성공
// System.out.print -> StringBuilder 로 수정
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2776 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		
		for(int i=0; i<count; i++) {
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] arr1 = new int[N];
			for(int j=0; j<N; j++) {
				arr1[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr1);
			
			int M = Integer.parseInt(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			for(int k=0; k<M; k++) {
				int target = Integer.parseInt(st2.nextToken());
				sb.append(binarySearch(arr1, target) ? 1 : 0).append('\n');
			}
			System.out.print(sb);
		}
	}
	
	// ver1: for문으로 구현
	public static boolean binarySearch(int arr[], int target) {
		 int left = 0;
		 int right = arr.length-1;
	
		 while(left<=right) {
			 int mid = (left+right) /2;
			 
			 if(arr[mid]==target) return true;
			 if(target > arr[mid]) left = mid+1;
			 else right = mid-1;
		 }
		 
		 return false;
	}
}*/

// ver4 : 성공
// set & contains() 함수 이용
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Q2776 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		
		for(int i=0; i<count; i++) {
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			Set<Integer> set = new HashSet<Integer>();
			for(int j=0; j<N; j++) {
				set.add(Integer.parseInt(st.nextToken()));
			}
			
			int M = Integer.parseInt(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			for(int k=0; k<M; k++) {
				int target = Integer.parseInt(st2.nextToken());
				sb.append(set.contains(target) ? 1 : 0).append('\n');
			}
			System.out.print(sb);
		}
	}
}