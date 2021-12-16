// RGB거리 (백준 1149번)

/* [ 알고리즘 ]
 * 
 * 1. N의 최솟값을 구한다.
 * 2. N의 최솟값이 될 수 있는 경우 
 *  (1) (N-1)값 + N     -> N : RED, N-1 : GREEN or BLUE (최솟값) 선택 
 *  (2) (N-1)값 + N     -> N : GREEN, N-1 : RED or BLUE (최솟값) 선택 
 *  (2) (N-1)값 + N     -> N : BLUE, N-1 : RED or GREEN (최솟값) 선택
 * 3. DP의 메모이제이션을 이용해 품 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1149 {
	public static class type {
		private int R;
		private int G;
		private int B;
		public type(int R, int G, int B) {
			this.R = R;
			this.G = G;
			this.B = B;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 집의 수
		StringTokenizer st = new StringTokenizer("");
		type[] cost = new type[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int red = Integer.parseInt(st.nextToken());
			int green = Integer.parseInt(st.nextToken());
			int blue = Integer.parseInt(st.nextToken());
			cost[i] = new type(red, green, blue);
		}
		
		boolean[] check = new boolean[3]; // R, G, B
		/*
		for(int i=0; i<N; i++) {
			System.out.println(cost[i].R + " " + cost[i].G + " " + cost[i].B);
		}*/
		
		int[][] dp = new int[N][3]; // color -> 0 : R, 1 : G, 2: B
		
		dp[0][0] = cost[0].R;
		dp[0][1] = cost[0].G;
		dp[0][2] = cost[0].B;
		
		for(int i=1; i<N; i++) {  // 반복문의 순서 중요! (color를 먼저하게 되면 값이 변경되지 않음)
			for(int c=0; c<3; c++) { // color 3가지 경우의 수 모두 구하기 위해
				if(dp[i][c]==0) { 
					switch(c) {
						case 0: dp[i][c] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i].R; break; // RED 일 경우
						case 1: dp[i][c] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i].G; break; // GREEN 일 경우
						case 2: dp[i][c] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i].B; break; // BLUE 일 경우
						default : break;
					}
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int c=0; c<3; c++) {
			if(min > dp[N-1][c]) min = dp[N-1][c];
		}
		
		System.out.print(min);
	}
}
