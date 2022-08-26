// 합이 0인 네 정수 (백준 골드2)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q7453 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		long[][] arr = new long[n][4];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Long.parseLong(st.nextToken());
			arr[i][1] = Long.parseLong(st.nextToken());
			arr[i][2] = Long.parseLong(st.nextToken());
			arr[i][3] = Long.parseLong(st.nextToken());
		}
		
		long[] AB = new long[n*n];
		long[] CD = new long[n*n];
		
		int idx=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				AB[idx] = arr[i][0] + arr[j][1];
				CD[idx] = arr[i][2] + arr[j][3];
				idx += 1;
			}
		}
		
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		int start=0, end=CD.length-1;
		long sum, a, b, countA, countB, count=0;
		while(start < AB.length && end >=0) {
			sum = AB[start] + CD[end];
			
			if(sum==0) {
				a = AB[start];
				b = CD[end];
				
				countA = 0;
				countB = 0;
				
				while(start < AB.length && AB[start]==a) {
					start++;
					countA++;
				}
				while(end >= 0 && CD[end]==b) {
					end--;
					countB++;
				}
				
				count += countA*countB;
			} 
			else if(sum<0) start+=1;
			else end-=1; 
		}
		
		System.out.println(count);
	}
}
