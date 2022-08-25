// 나무 자르기 (백준 실버2)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q2805 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start=0, end=2000000000, mid=0, answer=0;
		long sum = 0;
		while(start <= end) {
			mid = (start+end)/2;
			
			sum = 0;
			for(int j=0; j<arr.length; j++) {
				if(arr[j] > mid) sum += arr[j]-mid;
			}
			
			if(sum >= M) {
				answer = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		System.out.println(answer);
	}
}
