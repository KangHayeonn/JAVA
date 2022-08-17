// ���� �κ� ���ڿ� (���� ���5)
package baekjoon;

import java.io.*;

public class Q5582 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int[][] dp = new int[str2.length()+1][str1.length()+1]; // ������ �迭
		int max = 0;
		
		for(int r=1; r<=str2.length(); r++) {     // ��
			for(int c=1; c<=str1.length(); c++) { // ��
				char c1 = str1.charAt(c-1);
				char c2 = str2.charAt(r-1);
				
				if(c1 == c2) {
					dp[r][c] = dp[r-1][c-1] + 1;
					max = Math.max(max, dp[r][c]);
				}
			}
		}
		
		System.out.println(max);
	}
}
