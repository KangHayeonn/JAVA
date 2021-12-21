// LCS (���� 9251��)

/* [ �˰����� ]
 * 
 * - LCS(Longest Common SubSequence) �˰�����
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
		
		for(int i=1; i<str1.length()+1; i++) { // 1���� �����ϴ� ���� : �迭 �ε����� ���� �� ���°� �����ϱ� ����
			for(int j=1; j<str2.length()+1; j++) {
				if(dp[i][j] == 0) {
					if(str1.charAt(i-1)==str2.charAt(j-1)) dp[i][j] = dp[i-1][j-1] +1; // �밢�� �κп��� +1
					else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[str1.length()][str2.length()]);
	}
}