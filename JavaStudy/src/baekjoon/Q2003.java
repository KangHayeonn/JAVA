// 수들의 합 2
package baekjoon;

import java.io.*;
import java.util.*;

public class Q2003 {
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
		
		int end=0, sum=0, count=0;
		for(int start=0; start<N; start++) {		
			while(end < N && sum < M) {
				sum += arr[end];
				end++;
			}
		
			if(sum >= M) {
				if(sum == M) count++;
				sum -= arr[start];
			}
		}
		
		System.out.println(count);
	}
}
