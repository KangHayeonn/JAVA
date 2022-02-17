// ������ �ý� (���� 2156��)

/* [ �˰��� ]
 * 
 * 1. N�� ��� : N + N-1 + N-3 ����
 * 2. N�� ��� : N + N-2 ����
 * 3. N-1�� ���
 * 4. 1-3�� �� ���� ū ���� ä���ؼ� �޸������̼ǿ� ����
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
