// LCS (백준 9251번)

/* [ 알고리즘 ]
 * 
 * - LCS(Longest Common SubSequence) 알고리즘
 */

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9251 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int[][] dp = new int[str1.length()+1][str2.length()+1];
		
		for(int i=1; i<str1.length()+1; i++) { // 1부터 시작하는 이유 : 배열 인덱스에 음수 값 들어가는거 방지하기 위해
			for(int j=1; j<str2.length()+1; j++) {
				if(dp[i][j] == 0) {
					if(str1.charAt(i-1)==str2.charAt(j-1)) dp[i][j] = dp[i-1][j-1] +1; // 대각선 부분에서 +1
					else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[str1.length()][str2.length()]);
	}
}
