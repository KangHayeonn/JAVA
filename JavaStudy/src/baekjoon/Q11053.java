// ���� �� �����ϴ� �κ� ����

/* [ �˰��� ]
 * 
 * 1. �ʱ� dp�� ��� 1�� ���� (�� �ε����� ��� ������ ������ �ּ� ������ ũ�� 1�̹Ƿ�)
 * 2. �� �迭���� �ڽź��� �ε����� ���� ������ �������� ���κ��� �� ���� ���� ������ �ش� dp���� +1 ����
 * 3. �� �� ���� max ���� 2���� �ε����� dp������ ����
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11053 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] A = new int[count];
		
		for(int i=0; i<count; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[count];
		int[] list = new int[count];
		Arrays.fill(dp, 1);
	
		for(int i=0; i<count; i++) {
			if(list[i] == 0) {
				list[i] = A[i];
				for(int j=i-1; j>=0; j--) {
					if(list[i] > list[j]) {
						dp[i] = Math.max(dp[i], dp[j]+1);
					}
				}
			}
		}
		
		int max = 0;
		for(int i=0; i<count; i++) {
			if(dp[i] > max) max = dp[i];
		}
		System.out.print(max);
	}
}
