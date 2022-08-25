// 게임 (백준 실버3)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q1072 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long X = Long.parseLong(st.nextToken());
		long Y = Long.parseLong(st.nextToken());
		
		long Z = (Y * 100 / X);
		
		if(X==Y) {
			System.out.println(-1);
			return;
		}
		
		long start=0, end=1000000000, mid=0, currZ, currX, currY, answer=-1;
		while(start <= end) {
			mid = (start+end)/2;
			
			currX = X + mid;
			currY = Y + mid;
			currZ = (currY * 100) / currX;
			
			if(currZ > Z) {
				answer = mid;
				end = mid-1;
			} else {
				start = mid+1;
			}
		}
		System.out.println(answer);
	}
}
