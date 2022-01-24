// 치킨 TOP N (백준 11582번)

/* [ 알고리즘 ]
 * 
 * 1. 입력된 치킨집의 개수 N에서 /2를 하면서 k명이 정렬을 진행할 때까지 반복
 * 
 * - 정렬: 합병정렬
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11582 {
	static int N, k;
	static int[] sorted;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] list = new int[N];
		sorted = new int[N];
		
		for(int i=0; i<N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		k = Integer.parseInt(br.readLine());
		//System.out.println(Arrays.toString(list));
		merge_sort(list, 0, N-1);
		for(int i=0; i<list.length; i++) {
			System.out.print(list[i]+" ");
		}
		//System.out.println(Arrays.toString(list));
		
	}
	
	// Top-Down 방식
	public static void merge_sort(int[] list, int left, int right) {
		if(left == right) return;
		
		int mid = (left+right)/2;
		
		// 분할 과정
		merge_sort(list, left, mid);
		merge_sort(list, mid+1, right);
		
		merge(list, left, mid, right);
	}
	
	// Buttom-Up 방식
	public static void mergeSort(int[] list, int left, int right) {
		/*
		 * 1 - 2 - 4 - 8 ... : 1부터 서브리스트를 나누는 기준을 두 배씩 늘림
		 */
		for( int size = 1; size <= right; size += size) {
			for(int l=0; l<=right-size; l+=(2*size)) {
				int low = l;
				int mid = l+size-1;
				int high = Math.min(l+ (2*size)-1, right);
				merge(list, low, mid, high);
			}
			//System.out.println(Arrays.toString(list));
			//System.out.println("N, K : " + N +" " +k + " -> " +size);
		}
	}
	
	// 병합 과정
	public static void merge(int[] list, int left, int mid, int right) {
		// right-left : 현재 정렬중인 리스트 크기
		if(right - left > N / k) {
			return;
		}
		int l = left;
		int r = mid+1;
		int idx = left;
		
		while(l <= mid && r <= right) {
			
			if(list[l] <= list[r]) {
				sorted[idx] = list[l];
				idx++;
				l++;
			} else {
				sorted[idx] = list[r];
				idx++;
				r++;
			}
		}
		// 왼쪽 부분리스트가 먼저 새 배열에 모두 채워졌을 경우 (오른쪽 부분리스트는 아직 남아있음)
		if(l > mid) {
			while(r <= right) {
				sorted[idx] = list[r];
				idx++;
				r++;
			}
		} 
		// 오른쪽 부분리스트가 먼저 새 배열에 모두 채워졌을 경우 (왼쪽 부분리스트는 아직 남아있음)
		else {
			while(l <= mid) {
				sorted[idx] = list[l];
				idx++;
				l++;
			}
		}
		for(int i=left; i<=right; i++) {
			list[i] = sorted[i];
		}
	}
}
