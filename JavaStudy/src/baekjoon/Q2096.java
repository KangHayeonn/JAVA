// 내려가기 (백준 골드5)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q2096 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		int[][] dp = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				dp[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dpMax = new int[N][3];
		int[][] dpMin = new int[N][3];
		
		dpMax[0][0] = dp[0][0]; dpMax[0][1] = dp[0][1]; dpMax[0][2] = dp[0][2];
		dpMin[0][0] = dp[0][0]; dpMin[0][1] = dp[0][1]; dpMin[0][2] = dp[0][2];
		
		for(int i=1; i<N; i++) {
			dpMax[i][0] = dp[i][0] + Math.max(dpMax[i-1][0], dpMax[i-1][1]);
			dpMin[i][0] = dp[i][0] + Math.min(dpMin[i-1][0], dpMin[i-1][1]);
			
			dpMax[i][1] = dp[i][1] + Math.max(dpMax[i-1][0], Math.max(dpMax[i-1][1], dpMax[i-1][2]));
			dpMin[i][1] = dp[i][1] + Math.min(dpMin[i-1][0], Math.min(dpMin[i-1][1], dpMin[i-1][2]));
			
			dpMax[i][2] = dp[i][2] + Math.max(dpMax[i-1][1], dpMax[i-1][2]);
			dpMin[i][2] = dp[i][2] + Math.min(dpMin[i-1][1], dpMin[i-1][2]);
		}
		
		System.out.print(Math.max(dpMax[N-1][0], Math.max(dpMax[N-1][1], dpMax[N-1][2])) + " ");
		System.out.print(Math.min(dpMin[N-1][0], Math.min(dpMin[N-1][1], dpMin[N-1][2])));
	}
}
