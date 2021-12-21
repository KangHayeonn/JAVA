// Ÿ�� ä��� (���� 2133��)

/* [ �˰��� ]
 * 
 * 1. dp[2] = 3
 * 2. dp[4] = dp[2]*dp[2] + 2 (-> Ư���� ���̽�)
 * 3. dp[6] = dp[4]*dp[2] + dp[2]*2(-> dp[4]��� �� �ߺ����� �ʴ� ���̽� 2��) + 2 (-> Ư���� ���̽�)
 * 4. dp[8] = dp[6]*dp[2] + dp[4]*2(-> dp[4]��� �� �ߺ����� �ʴ� ���̽� 2��) + dp[2]*2(-> dp[6]��� �� �ߺ����� �ʴ� ���̽� 2��) + 2 (-> Ư���� ���̽�)
 * 5. dp[0] = 1 �� ����, dp[Ȧ��] = �� ����
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2133 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		
		dp[0] = 1;
				
		for(int i=2; i<=N; i=i+2) { // Ȧ�� �κ� ����
			if(i==2) dp[2] = 3;
			else {
				dp[i] = dp[i-2] * dp[2];
				
				for(int j = i-4; j>=0; j=j-2) {
					dp[i] += dp[j] * 2;
				}
			}
		}
		
		System.out.println(dp[N]);
	}
}
