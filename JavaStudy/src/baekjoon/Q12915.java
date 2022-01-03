// ��ȸ ���� (���� 12915��)

/* [ �˰��� ]
 * 
 * 1. E���� ���̵����� ���� �̾��� (������ EM���� �̾���) -> E, EM ��� 0�� �� ��� break;
 * 2. 1���� ������ ��� M�� ���̵����� �̾��� (������ EM�� HM�߿��� �̾���)
 * 3. 2���� ��� M�� ���̵��� ���� ��� EM�� HM�� ������ �� ���� �Ϳ��� �̾��� -> ������ ������ ��� EM���� �̾���
 * 4. M, EM, HM�� ��� 0���� ��� break;
 * 5. 2-3���� ������ ��� H�� ���̵����� �̾��� (������ HM���� �̾���) -> H, HM ��� 0���� ��� break;
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12915 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] level = new int[5]; // E EM M MH H
		for(int i=0; i<5; i++) {
			level[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean E, M, H ;
		E = M = H = false;
		int sum = 0;
		
		while(true) {
			if(level[0] > 0) { // ���̵� E�� ����
				level[0]--;
				E = true;
			} else if(level[1] > 0) {
				level[1]--;
				E = true;
			} else {
				break;
			}
			
			if(E) {
				if(level[2] > 0) { // ���̵� M�� ����
					level[2]--;
					M = true;
				} else if(level[1] > 0 || level[3] > 0) {
					if(level[1] >= level[3]) {
						level[1]--;
						M = true;
					} else {
						level[3]--;
						M = true;
					}
				} else {
					break;
				}
			}
			
			if(E && M) { // ���̵� H�� ����
				if(level[4] > 0) {
					level[4]--;
					H = true;
				} else if(level[3] > 0) {
					level[3]--;
					H = true;
				} else {
					break;
				}
			}
			
			if(E && M && H) sum++;
			
			E = M = H = false;
		}
		
		System.out.println(sum);
	}
}
