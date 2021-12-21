// ���� �ﰢ�� (���� 1932��)

/* [ �˰��� ]
 * 
 * 1. ������ �ݴ�� �Ʒ����� ���� �ö���鼭 ū ���� �����ϸ鼭 �ö��
 * 2. ���� ��带 �������� ���� ������ ���� ū ���� ���ϰ� ���� ��带 ���ؼ� ���� ����� ���� �� ������ (��� �̿�)
 * 3. ó�� ���� �޾� �ִ� �Ϳ� �־ �¿� ���� ��� �������� ��� 
 * 4. Ʈ�� ó�� 2xn, 2xn+1 �� �� ��� ������ �� -> ���� ��� ���� �������� ���γ��([column][row])�� ���� �Ʒ� ����([column+1][row]), ���� �Ʒ� ������([column+1][row+1]) �� �Է°� �޾���
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1932 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // �ﰢ���� ũ��
		StringTokenizer st = new StringTokenizer("");
		Integer[][] arr = new Integer[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<i+1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Integer[][] dp = new Integer[n][n];

		System.out.print(DP(arr, dp,  n-1, 0, 0));
	}
	public static int DP(Integer[][] arr, Integer[][] dp, int n, int column, int row) {
		if(column==n) {
			return arr[column][row]; // ������ ���� �״�� ���
		}
		
		if(dp[column][row]==null) {
			dp[column][row] = Math.max(DP(arr, dp, n, column+1, row), DP(arr, dp, n, column+1, row+1)) + arr[column][row];
		}
		
		return dp[column][row];
	}
}
