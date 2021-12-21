// ������

/* [ �˰��� ]
 * 
 * 1. ������ ����� ���� ����
 *  (1) ���ڸ� �Ѹ����� ��ġ���� ���� ��� (lion[n][0])
 *  (2) ���ڸ� ����ĭ�� ��ġ�� ���      (lion[n][1])
 *  (3) ���ڸ� ���� ��ĭ�� ��ġ�� ���    (lion[n][2])
 * 2. ���� ���� �޸������̼� �� ���� ��°��� ������ ��
 * 3. �� ����� �� ���� 9901�� ���� �� ���
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1309 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // �츮�� ���� ũ��
		
		int[][] lion = new int[N][3];
		lion[0][0] = 1; // ó�� �Ѹ����� ��ġ���� ���� ����� �� �Ѱ����� �ǹ�
		lion[0][1] = 1;
		lion[0][2] = 1;
		
		for(int i=1; i<N; i++) {
			lion[i][0] = (lion[i-1][0] + lion[i-1][1] + lion[i-1][2]) % 9901; // 9901 �� �Ѿ��� ��� �������� ������ �����÷ο츦 ����.
			lion[i][1] = (lion[i-1][0] + lion[i-1][2]) % 9901;
			lion[i][2] = (lion[i-1][0] + lion[i-1][1]) % 9901;
		}
		
		int sum = 0;
		
		for(int i=0; i<3; i++) {
			sum += lion[N-1][i];
		}
		
		System.out.println((sum%9901));
	}
}
