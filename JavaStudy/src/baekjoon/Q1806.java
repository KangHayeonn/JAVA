// 부분합 (백준 골드4)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q1806 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int end=0, sum=arr[0];
		int min = 100001;
		for(int start=0; start<N; start++) {
			while(sum < S && end < N-1) {
				end += 1;
				sum += arr[end];
			}
			
			if(sum >= S) {
				sum -= arr[start];
				min = Math.min(min, (end-start)+1);
			}
		}
		
		if(min == 100001) min = 0;
		
		System.out.println(min);
	}
}