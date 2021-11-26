// 공유기 설치 (백준 2110번)

/* [ 알고리즘 ]
 * 
 * 1. 길이를 기준으로 이분탐색 (mid = (최대길이 + 최소길이) / 2)
 * 2. 각 길이에 해당하는 값을 count 해준 뒤 
 * 3. 내가 뽑아야할 갯수 보다 크면 간격을 넓혀주고 (left = mid+1)
 * 4. 뽑아야할 갯수 보다 작으면 간격을 줄여줌 (right = mid-1)
 * 5. 더이상 탐색할 길이가 없으면 마지막 길이를 return
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2110 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 집의 개수
		int C = Integer.parseInt(st.nextToken()); // 공유기의 개수
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		int left = 1; // 최소 길이
		int right = arr[arr.length-1] - arr[0];
		
		BinarySearch(arr, left, right, C);
	}
	
	public static void BinarySearch(int[] arr, int left, int right, int C) {
		int answer = 0;
		//int count = 0; // 여기다 넣으면 갱신이 안되서 오답
		
		while(left<=right) {
			int mid = (left+right)/2; // 기준 범위
			int start = arr[0];
			int count = 1; // 처음 집 카운트
			
			for(int i=0; i<arr.length; i++) {
				int dis = arr[i] - start;
				if(dis >= mid) { // 기준 범위를 넘은 값을 기준으로 값 갱신 
					start = arr[i];
					count++;
				}
			}
			
			if(count<C) {
				right = mid-1;
			} else {
				answer = Math.max(answer, mid);
				left = mid+1;
			}
		}
		
		System.out.print(answer);
		return;
	}
}
