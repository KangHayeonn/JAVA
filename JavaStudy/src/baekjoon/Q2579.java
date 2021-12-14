// ��� ������ (���� 2579��)

/* [ �˰��� ]
 * 
 * 1. ���� ������ n ���� �� �� �ִ� ����� ���� 2����
 *   (1) n-2 �� ���� ���� ���
 *   (2) n-3�� ���� n-1���� ��ġ�� ���� ���
 * 2. ������ ��� �� �ִ��� ������ n�� �����ش�.
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2579 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		int[] step = new int[count+1];
		
		for(int i=1; i<count+1; i++) {
			step[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dpList = new int[count+1];
		dpList[1] = step[1];
		if(count > 1) dpList[2] = step[1] + step[2]; // count==1�� ��, ��Ÿ�� ���� (ArrayIndexOutOfBounds) �߻�
		int answer = DP(count, step, dpList);
		System.out.print(answer);
	}
	public static int DP(int N, int[] step, int[] dpList) {

		if(dpList[N] == 0 && N >= 3) {
			dpList[N] = Math.max(DP(N-2, step, dpList), DP(N-3, step, dpList) + step[N-1]) + step[N]; 
		}
		return dpList[N];
	}
}
