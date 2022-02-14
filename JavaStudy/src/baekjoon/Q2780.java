// ��й�ȣ (���� 2780��)

/* [ �˰��� ]
 *
 * 1. 0~9���� ������ ���� ���̽��� �����ش�. ex 0 -> 7 , 5 -> 2, 4, 6, 8
 * 2. ���� ���, 0���� ������ ���ڰ� 4�ڸ� ���� ��� (�̶�, 0���� 4�ڸ� ���� ���� ���� ����)
 * 3. 0�� ������ 7�� �Ѿ �� 3�ڸ� ���ڷ� �����Ѵ�.(�̶�, 7���� 3�ڸ� ���� ���� ���� ����)
 * 4. �������� 7�� ������ 4, 8, 0 ���ڷ� �Ѿ �� 2�ڸ� ���ڷ� �����Ѵ�. (�̶�, 4, 8, 0���� 2�ڸ� ���� ���� ���� ����)
 * 5. ���������� 4, 8, 0�� ������ ���� (1,5,7) , (5,7,9) , (7) �� ������ ���ڷ� �Ѿ �� 1�ڸ� ���ڷ� �����Ѵ�. (�̶�, ������ ��쿡�� 1�ڸ� ���� ���� ���� ����)
 * 6. ���������� 1�ڸ� ���ڸ� ������ ��� +1�� ���� �������ش�.
 * 7. 2-6���� ������ ��ͷ� ����
 * 
 * (����, for���� �̿��� �����ϰ� �ʹٸ� 0~9������ ���ڰ� 1�ڸ� ���� ��� 1�� ���� �������� ��, �ڸ� ���� �þ ������ ����� �޸������̼� ���� �ҷ��ͼ� �߰�����) 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Q2780 {
	static int count = 0; 
	static int[][] dp;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine()); // �׽�Ʈ���̽� ����
		
		dp = new int[10][1001]; // �޸������̼� �� �� ([����][����])
		for(int i=0; i<10; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for(int i=0; i<T; i++) {
			int maxCount = Integer.parseInt(br.readLine());
			count = 0; // �ʱ�ȭ �ȵǼ� �ڲ� ���� �߰��� ���� BufferedWriter�� �����
			
			for(int num=0; num<10; num++) {
				count += FindPW(num, maxCount);
			}
			bw.write((count % 1234567)+"\n");
		}
		
		bw.flush();
	}
	
	public static int FindPW(int num, int maxCount) {
		
		count = 0;
		
		if(maxCount == 1) {
			count = count+1;
		} else if(dp[num][maxCount] > 0) { // �̹� ����� ���� �ִٸ� �޸������̼� �� �״�� ���
			return dp[num][maxCount];
		} else { 
			// ����� �� ���� ���� 2�ڸ� �� �̻���
			switch(num) {
				case 0: {
					count += FindPW(7, maxCount-1);
					break;
				}
				case 1: {
					count += FindPW(2, maxCount-1) + FindPW(4, maxCount-1);
					break;
				}
				case 2: {
					count += FindPW(1, maxCount-1) + FindPW(3, maxCount-1) + FindPW(5, maxCount-1);
					break;
				}
				case 3: {
					count += FindPW(2, maxCount-1) + FindPW(6, maxCount-1);
					break;
				}
				case 4: {
					count += FindPW(1, maxCount-1) + FindPW(5, maxCount-1) + FindPW(7, maxCount-1);
					break;
				}
				case 5: {
					count += FindPW(2, maxCount-1) + FindPW(4, maxCount-1) + FindPW(6, maxCount-1) + FindPW(8, maxCount-1);
					break;
				}
				case 6: {
					count += FindPW(3, maxCount-1) + FindPW(5, maxCount-1) + FindPW(9, maxCount-1);
					break;
				}
				case 7: {
					count += FindPW(0, maxCount-1) + FindPW(4, maxCount-1) + FindPW(8, maxCount-1);
					break;
				}
				case 8: {
					count += FindPW(5, maxCount-1) + FindPW(7, maxCount-1) + FindPW(9, maxCount-1);
					break;
				}
				case 9: {
					count += FindPW(6, maxCount-1) + FindPW(8, maxCount-1);
					break;
				}
				default: break;
			}
		}
	
		count = count % 1234567;
		dp[num][maxCount] = count;
		return count;
	}
}
