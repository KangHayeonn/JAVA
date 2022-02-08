// 1�� ����� 2 (���� 12852��)

/* [ �˰��� ]
 * 
 * 1. dp �̿��ؼ� ���� ���� ����
 * 2. 1���� �����ؼ� N���� ������ ä������
 * 3. 3���� ������, 2�� ������, 1�� �� ���߿� ������ ���� ���� ���� ��� �װ��� ����
 */

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q12852 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // �ڿ��� N
		
		int[] dp = new int[N+1]; // �ε����� ���ڸ� �ǹ� (1~N)
		String[] calc = new String[N+1]; // ���� ����Ʈ
		
		dp[1] = 0; calc[1] = "1";
		// dp[2] = 1; calc[2] = "2" + " " + "1";
		// dp[3] = 1; calc[3] = "3" + " " + "1";
		
		for(int i=2; i<=N; i++) {
			dp[i] = 1000001;
		}
		
		int check = 0; // 1: -1, 2: %2, 3: %3�� ���
		
		for(int i=2; i<=N; i++) { 
			
			if(i%3==0) {
				if(dp[i/3]+1 < dp[i]) {
					dp[i] = dp[i/3]+1;
					check = 3;
				}
			}
			
			if(i%2==0) {
				if(dp[i/2]+1 < dp[i]) {
					dp[i] = dp[i/2]+1;
					check = 2;
				}
			}

			if(dp[i-1]+1 < dp[i]) {
				dp[i] = dp[i-1] + 1;
				check = 1;
			}
			
			switch(check) {
				case 1: calc[i] = i + " " + calc[i-1]; break;
				case 2: calc[i] = i + " " + calc[i/2]; break;
				case 3: calc[i] = i + " " + calc[i/3]; break;
				default : break;
			}
			// System.out.println(i + " -> " +dp[i]);
		}
		
		System.out.println(dp[N]);
		System.out.println(calc[N]);
		
	}
}