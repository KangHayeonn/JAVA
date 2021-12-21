// 타일 채우기 (백준 2133번)

/* [ 알고리즘 ]
 * 
 * 1. dp[2] = 3
 * 2. dp[4] = dp[2]*dp[2] + 2 (-> 특별한 케이스)
 * 3. dp[6] = dp[4]*dp[2] + dp[2]*2(-> dp[4]모양 중 중복되지 않는 케이스 2개) + 2 (-> 특별한 케이스)
 * 4. dp[8] = dp[6]*dp[2] + dp[4]*2(-> dp[4]모양 중 중복되지 않는 케이스 2개) + dp[2]*2(-> dp[6]모양 중 중복되지 않는 케이스 2개) + 2 (-> 특별한 케이스)
 * 5. dp[0] = 1 로 가정, dp[홀수] = 값 없음
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
				
		for(int i=2; i<=N; i=i+2) { // 홀수 부분 제외
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
