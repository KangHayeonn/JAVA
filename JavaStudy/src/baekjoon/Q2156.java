// 포도주 시식 (백준 2156번)

/* [ 알고리즘 ]
 * 
 * 1. N일 경우 : N + N-1 + N-3 가능
 * 2. N일 경우 : N + N-2 가능
 * 3. N-1일 경우
 * 4. 1-3번 중 제일 큰 값을 채택해서 메모이제이션에 저장
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q2156 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] grape = new int[n+1];
		int[] dp = new int[n+1];
		
		for(int i=0; i<n; i++) {
			grape[i] = Integer.parseInt(br.readLine());
		}
		
		
		System.out.println(DP(n, dp, grape));
	}
	
	public static int DP(int n, int[] dp, int[] grape) {
		if(n<0) {
			int x = Math.max(DP(n-2, dp, grape), DP(n-1, dp, grape) + DP(n-3, dp, grape)) + grape[n-1];
			dp[n] = Math.max(dp[n-1], x);
		}
		return dp[n];
	}
}
